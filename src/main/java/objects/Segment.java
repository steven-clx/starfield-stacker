package objects;

import java.util.ArrayList;
import java.util.List;


/**
 * A segment of pixels, representing a continuous pixel sequence on the same y coordinate (horizontal line),
 * is useful for the connectivity-based image segmentation algorithm
 *
 * @author steven-clx
 */
public class Segment {


     /**
     * The ordered sequence of pixels (from left to right)
     */
    private final List<Pixel> pixels;


    /**
     * The star which the segment belongs to
     */
    private Star star;


    /**
     * The x coordinate of the left end of the segment
     */
    private final short left;


    /**
     * The x coordinate of the right end of the segment
     */
    private short right;


    /**
     * The segment's y coordinate
     */
    private final short y;



    /**
     * Always construct the segment from the first star pixel encountered when scanning from left to right
     *
     * @param firstPix the first star pixel encountered when scanning from left to right
     */
    public Segment(Pixel firstPix) {

        pixels = new ArrayList<>();
        pixels.add(firstPix);

        firstPix.setSegment(this);

        y = firstPix.getY();
        left = firstPix.getX();
        right = left;
    }



    /**
     * Add another pixel to the segment
     *
     * @param newPix the next encountered star pixel in the segment (only if next to the previous one)
     */
    public void addPix(Pixel newPix) {
        pixels.add(newPix);
        newPix.setSegment(this);
        right = newPix.getX();
    }



    public Iterable<Pixel> pixels() {
        return pixels;
    }

    public List<Pixel> getPixels() {
        return pixels;
    }

    public Star getStar() {
        return star;
    }

    public short getLeft() {
        return left;
    }

    public short getRight() {
        return right;
    }

    public short getY() {
        return y;
    }



    public void setStar(Star star) {
        this.star = star;
    }

}
