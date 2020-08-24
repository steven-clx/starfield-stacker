package image;


import util.MathUtil;

/**
 * A pixel with 8-bit RGB channels in byte type
 *
 * @author steven-clx
 */
public class RgbPixel implements Pixel {


    byte r, g, b;
    short x, y;



    public RgbPixel() {}


    public RgbPixel(int r, int g, int b) {
        this.r = (byte) r;
        this.g = (byte) g;
        this.b = (byte) b;
    }


    public RgbPixel(int x, int y) {
        this.x = (short) x;
        this.y = (short) y;
    }


    public RgbPixel(int r, int g, int b, int x, int y) {
        this.r = (byte) r;
        this.g = (byte) g;
        this.b = (byte) b;
        this.x = (short) x;
        this.y = (short) y;
    }


    public RgbPixel(RgbPixel other) {
        r = other.r;
        g = other.g;
        b = other.b;
        x = other.x;
        y = other.y;
    }



    @Override
    public boolean hasAlpha() {
        return false;
    }



    @Override
    public int getR() {
        return r & 0xff;
    }

    @Override
    public int getG() {
        return g & 0xff;
    }

    @Override
    public int getB() {
        return b & 0xff;
    }

    @Override
    public int getA() {
        return 0xff;
    }

    @Override
    public short getX() {
        return x;
    }

    @Override
    public short getY() {
        return y;
    }



    @Override
    public int getEncoded() {
        return ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
    }

    @Override
    public int getEncodedRGB() {
        return ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
    }

    @Override
    public int getEncodedRGBA() {
        return -16777216 | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
    }

    @Override
    public int getEncodedAlphaMultipliedRGB() {
        return ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
    }

    @Override
    public int getBrightness() {
        return Math.round(((r & 0xff) + (g & 0xff) + (b & 0xff)) / 3f);
    }



    @Override
    public void setR(int r) {
        this.r = (byte) r;
    }

    @Override
    public void setG(int g) {
        this.g = (byte) g;
    }

    @Override
    public void setB(int b) {
        this.b = (byte) b;
    }

    @Override
    public void setA(int a) {}

    @Override
    public void setRGB(int r, int g, int b) {
        this.r = (byte) r;
        this.g = (byte) g;
        this.b = (byte) b;
    }

    @Override
    public void setRGBA(int r, int g, int b, int a) {
        this.r = (byte) r;
        this.g = (byte) g;
        this.b = (byte) b;
    }



    @Override
    public void setMaxCheckedR(int r) {
        this.r = (byte) Math.min(255, r);
    }

    @Override
    public void setMaxCheckedG(int g) {
        this.g = (byte) Math.min(255, g);
    }

    @Override
    public void setMaxCheckedB(int b) {
        this.b = (byte) Math.min(255, b);
    }

    @Override
    public void setMaxCheckedA(int a) {}



    @Override
    public void setMinCheckedR(int r) {
        this.r = (byte) Math.max(0, r);
    }

    @Override
    public void setMinCheckedG(int g) {
        this.g = (byte) Math.max(0, g);
    }

    @Override
    public void setMinCheckedB(int b) {
        this.b = (byte) Math.max(0, b);
    }

    @Override
    public void setMinCheckedA(int a) {}



    @Override
    public void setX(int x) {
        this.x = (short) x;
    }

    @Override
    public void setY(int y) {
        this.y = (short) y;
    }

    @Override
    public void setXY(int x, int y) {
        this.x = (short) x;
        this.y = (short) y;
    }



    @Override
    public void setCodedInt(int codedInt) {
        this.r = (byte) ((codedInt >> 16) & 0xff);
        this.g = (byte) ((codedInt >> 8)  & 0xff);
        this.b = (byte) (codedInt         & 0xff);
    }



    public void blend(RgbPixel other, double opacity) {
        setR(MathUtil.blend(getR(), other.getR(), opacity));
        setG(MathUtil.blend(getG(), other.getG(), opacity));
        setB(MathUtil.blend(getB(), other.getB(), opacity));
    }


    public void blend(RgbaPixel other, double opacity) {
        blend((RgbPixel) other, MathUtil.computeCompositeAlpha(other, opacity));
    }



    @Override
    public String toString() {
        return String.format("RGB(%d, %d, %d)", getR(), getG(), getB());
    }

}
