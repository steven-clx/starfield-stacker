package objects;

import image.IPixel;


/**
 * An extension to Pixel with reference to its parent segment, used to represent a pixel in a star
 *
 * @see image.IPixel
 * @author steven-clx
 */
public class StarPixel extends Pixel {


    /**
     * The segment which the pixel belongs to
     */
    private Segment segment;



    public StarPixel(IPixel pixel, short x, short y) {
        super(pixel, x, y);
    }



    @Override
    public boolean isStar() {
        return true;
    }



    public Segment getSegment() {
        return segment;
    }

    public Star getStar() {
        return segment.getStar();
    }



    public void setSegment(Segment segment) {
        this.segment = segment;
    }

}
