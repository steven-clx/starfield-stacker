package objects;

import util.ListUtil;
import util.MathUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * A connected area of pixels with luminosity above a specified threshold represents a star
 * or light pollution
 *
 * @author steven-clx
 */
public class Star {


    /**
     * The ordered sequence of segments constituting the star (from top to bottom)
     */
    private final List<Segment> segments;


    /**
     * The x coordinate of the star's left boundary
     */
    private short left;


    /**
     * The y coordinate of the star's right boundary
     */
    private short right;


    /**
     * The y coordinate of the star's upper boundary
     */
    private short top;


    /**
     * The y coordinate of the star's lower boundary
     */
    private short bottom;


    /**
     * Flag that indicates whether this star is a source of light pollution
     */
    private boolean isLightPollution;


    /**
     * The star that this star is tracking in the previous image in a series of time-lapse images
     */
    private Star tracked;



    /**
     * Always construct the star from the first segment encountered when scanning from top to bottom, left to right
     *
     * @param firstSegment the first star pixel encountered when scanning from left to right
     */
    public Star(Segment firstSegment) {

        segments = new ArrayList<>();
        segments.add(firstSegment);

        firstSegment.setStar(this);

        left = firstSegment.getLeft();
        right = firstSegment.getRight();
        top = firstSegment.getY();
        bottom = top;
    }



    /**
     * Merge the other star into this star if two distinct stars become connected
     * by a segment when scanning from top to bottom, and then add this segment
     *
     * @param other the other star being merged
     * @param link the segment that links the two previously distinct stars
     */
    public void merge(Star other, Segment link) {

        ListUtil.mergeList(segments, other.segments, (ths, oth) -> {
            int compareY = Integer.compare(ths.getY(), oth.getY());
            if (compareY == 0) {
                return Integer.compare(ths.getLeft(), oth.getLeft());
            } else {
                return compareY;
            }
        });

        for (Segment segment : other.segments)
            segment.setStar(this);

        top = MathUtil.min(top, other.top);
        right = other.right;

        addSegment(link);
    }


    /**
     * Add a segment added to the existing star when scanning from top to bottom.
     * Segments must be added to a star sequentially (from top to bottom, left to right)
     *
     * @param newSegment the added segment
     */
    public void addSegment(Segment newSegment) {

        segments.add(newSegment);
        newSegment.setStar(this);

        left = MathUtil.min(left, newSegment.getLeft());
        right = MathUtil.max(right, newSegment.getRight());
        bottom = newSegment.getY();
    }


    /**
     * Calculate the x coordinate of the center of the star
     *
     * @return the x coordinate of the center of the star
     */
    public short calcX() {
        return (short) ((left + right) / 2);
    }


    /**
     * Calculate the y coordinate of the center of the star
     *
     * @return the y coordinate of the center of the star
     */
    public short calcY() {
        return (short) ((top + bottom) / 2);
    }



    public Iterable<Segment> segments() {
        return segments;
    }

    public Iterable<Pixel> pixels() {
        List<Pixel> pixels = new ArrayList<>();
        segments.forEach(s -> pixels.addAll(s.getPixels()));
        return pixels;
    }

    public short getLeft() {
        return left;
    }

    public short getRight() {
        return right;
    }

    public short getTop() {
        return top;
    }

    public short getBottom() {
        return bottom;
    }

    public boolean isLightPollution() {
        return isLightPollution;
    }

    public Star getTracked() {
        return tracked;
    }



    public void setLightPollution(boolean lightPollution) {
        isLightPollution = lightPollution;
    }

    public void setTracked(Star tracked) {
        this.tracked = tracked;
    }

}
