package star;

import java.util.ArrayList;
import java.util.List;


/**
 * A segment of star pixels, representing a continuous pixel sequence on the same y coordinate (horizontal line),
 * is useful for the connectivity-based image segmentation algorithm
 *
 * @author steven-clx
 */
public class Segment {


     /**
     * The ordered sequence of pixels (from left to right)
     */
    private final List<StarPixel> pixels;


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
     * @param firstPixel the first star pixel encountered when scanning from left to right
     */
    public Segment(StarPixel firstPixel, int x, int y) {

        pixels = new ArrayList<>();
        pixels.add(firstPixel);

        firstPixel.setSegment(this);

        this.y = (short) y;
        left = (short) x;
        right = left;
    }



    /**
     * Add a pixel to the segment
     *
     * @param newPixel the next encountered star pixel in the segment (only if next to the previous one)
     */
    public void addPixel(StarPixel newPixel, int x) {
        pixels.add(newPixel);
        newPixel.setSegment(this);
        right = (short) x;
    }



    public Iterable<StarPixel> pixels() {
        return pixels;
    }

    public List<StarPixel> getPixels() {
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
