package star;


import image.Pixel;
import image.RgbPixel;

/**
 * An extension to ImagePixel with reference to its parent segment, referring to a pixel inside a star
 *
 * @see RgbPixel
 * @author steven-clx
 */
public class StarPixel implements Pixel {


    /**
     *
     */
    private final Pixel pixel;


    /**
     * The segment which the pixel belongs to
     */
    private Segment segment;



    public StarPixel(Pixel pixel) {
        this.pixel = pixel;
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



    @Override
    public boolean hasAlpha() {
        return pixel.hasAlpha();
    }



    @Override
    public int getR() {
        return pixel.getR();
    }

    @Override
    public int getG() {
        return pixel.getG();
    }

    @Override
    public int getB() {
        return pixel.getB();
    }

    @Override
    public int getA() {
        return pixel.getA();
    }

    @Override
    public int getEncoded() {
        return pixel.getEncoded();
    }

    @Override
    public int getEncodedRGBA() {
        return pixel.getEncodedRGBA();
    }

    @Override
    public int getEncodedAlphaMultipliedRGB() {
        return pixel.getEncodedAlphaMultipliedRGB();
    }

    @Override
    public int getBrightness() {
        return pixel.getBrightness();
    }

    @Override
    public void setR(int r) {
        pixel.setR(r);
    }

    @Override
    public void setG(int g) {
        pixel.setG(g);
    }

    @Override
    public void setB(int b) {
        pixel.setB(b);
    }

    @Override
    public void setA(int a) {
        pixel.setA(a);
    }

    @Override
    public void setRGB(int r, int g, int b) {
        pixel.setRGB(r, g, b);
    }

    @Override
    public void setRGBA(int r, int g, int b, int a) {
        pixel.setRGBA(r, g, b, a);
    }

    @Override
    public void setEncoded(int encoded) {
        pixel.setEncoded(encoded);
    }

    @Override
    public void setEncodedRGB(int encodedRGB) {
        pixel.setEncodedRGB(encodedRGB);
    }



    @Override
    public String toString() {
        return "Star-" + pixel.toString();
    }

}
