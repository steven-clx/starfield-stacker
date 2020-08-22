package image;


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
    public short getX() {
        return pixel.getX();
    }

    @Override
    public short getY() {
        return pixel.getY();
    }



    @Override
    public int getEncoded() {
        return pixel.getEncoded();
    }

    @Override
    public int getEncodedRGB() {
        return pixel.getEncodedRGB();
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
    public void setMaxCheckedR(int r) {
        pixel.setMaxCheckedR(r);
    }

    @Override
    public void setMaxCheckedG(int g) {
        pixel.setMaxCheckedG(g);
    }

    @Override
    public void setMaxCheckedB(int b) {
        pixel.setMaxCheckedB(b);
    }

    @Override
    public void setMaxCheckedA(int a) {
        pixel.setMaxCheckedA(a);
    }



    @Override
    public void setMinCheckedR(int r) {
        pixel.setMinCheckedR(r);
    }

    @Override
    public void setMinCheckedG(int g) {
        pixel.setMinCheckedG(g);
    }

    @Override
    public void setMinCheckedB(int b) {
        pixel.setMinCheckedB(b);
    }

    @Override
    public void setMinCheckedA(int a) {
        pixel.setMinCheckedA(a);
    }



    @Override
    public void setX(int x) {
        pixel.setX(x);
    }

    @Override
    public void setY(int y) {
        pixel.setY(y);
    }

    @Override
    public void setXY(int x, int y) {
        pixel.setXY(x, y);
    }



    @Override
    public void setCodedInt(int codedInt) {
        pixel.setCodedInt(codedInt);
    }



    @Override
    public String toString() {
        return pixel.hasAlpha()
                ? String.format("RGBA(%d, %d, %d, %d)", getR(), getG(), getB(), getA())
                : String.format("RGB(%d, %d, %d)", getR(), getG(), getB());
    }

}
