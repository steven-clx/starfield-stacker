package objects;

import image.IPixel;


/**
 * An IPixel wrapper with x, y coordinates. Cannot be used to represent a star
 *
 * @see image.IPixel
 * @author steven-clx
 */
public class Pixel {


    /**
     * The wrapped pixel object
     */
    protected final IPixel pixel;


    /**
     * The pixel's x coordinate in the image (from left to right)
     */
    protected final short x;


    /**
     * The pixel's y coordinate in the image (from top to bottom)
     */
    protected final short y;



    public Pixel(IPixel pixel, short x, short y) {
        this.pixel = pixel;
        this.x = x;
        this.y = y;
    }



    public boolean isStar() {
        return false;
    }



    public IPixel getPixel() {
        return pixel;
    }

    public short getX() {
        return x;
    }

    public short getY() {
        return y;
    }

}
