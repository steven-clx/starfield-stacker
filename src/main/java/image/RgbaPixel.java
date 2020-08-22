package image;


import util.MathUtil;

/**
 * A pixel with 8-bit RGBA channels in byte type
 *
 * @author steven-clx
 */
public class RgbaPixel extends RgbPixel implements Pixel {


    byte a;



    public RgbaPixel() {
        this.a = 0x7f;
    }


    public RgbaPixel(int r, int g, int b, int a) {
        this.r = (byte) r;
        this.g = (byte) g;
        this.b = (byte) b;
        this.a = (byte) a;
    }


    public RgbaPixel(int x, int y) {
        super(x, y);
    }


    public RgbaPixel(int r, int g, int b, int a, int x, int y) {
        this.r = (byte) r;
        this.g = (byte) g;
        this.b = (byte) b;
        this.a = (byte) a;
        this.x = (short) x;
        this.y = (short) y;
    }


    public RgbaPixel(RgbaPixel other) {
        r = other.r;
        g = other.g;
        b = other.b;
        a = other.a;
        x = other.x;
        y = other.y;
    }



    @Override
    public boolean hasAlpha() {
        return true;
    }



    @Override
    public int getA() {
        return a & 0xff;
    }



    @Override
    public int getEncoded() {
        return ((a & 0xff) << 24) | super.getEncoded();
    }

    @Override
    public int getEncodedRGB() {
        return super.getEncoded();
    }

    @Override
    public int getEncodedRGBA() {
        return ((a & 0xff) << 24) | super.getEncoded();
    }

    @Override
    public int getEncodedAlphaMultipliedRGB() {
        int a = getA();
        int addend = 255 - a;
        double multiplier = a / 255d;
        return (int) ((Math.round(addend + getR() * multiplier) << 16) |
                      (Math.round(addend + getG() * multiplier) << 8)  |
                      (Math.round(addend + getB() * multiplier)));
    }



    @Override
    public void setA(int a) {
        this.a = (byte) a;
    }

    @Override
    public void setRGBA(int r, int g, int b, int a) {
        super.setRGB(r, g, b);
        this.a = (byte) a;
    }



    @Override
    public void setMaxCheckedA(int a) {
        this.a = (byte) Math.min(255, a);
    }

    @Override
    public void setMinCheckedA(int a) {
        this.a = (byte) Math.max(0, a);
    }



    @Override
    public void setCodedInt(int codedInt) {
        super.setCodedInt(codedInt);
        this.a = (byte) ((codedInt >> 24) & 0xff);
    }



    @Override
    public void blend(RgbPixel other, double opacity) {
        double a1 = getA() / 255d;
        double a12 = MathUtil.blendAlpha(a1, opacity);
        setR(MathUtil.blend(getR(), other.getR(), a1, opacity, a12));
        setG(MathUtil.blend(getG(), other.getG(), a1, opacity, a12));
        setB(MathUtil.blend(getB(), other.getB(), a1, opacity, a12));
        setA((int) Math.round(a12 * 255));
    }


    @Override
    public void blend(RgbaPixel other, double opacity) {
        blend((RgbPixel) other, MathUtil.computeCompositeAlpha(other, opacity));
    }



    @Override
    public String toString() {
        return String.format("RGBA(%d, %d, %d, %.2f)", getR(), getG(), getB(), getA() / 255d);
    }

}
