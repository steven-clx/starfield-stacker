package image;

import util.MathUtil;


/**
 * A pixel with 8-bit RGB channels in byte type
 *
 * @author steven-clx
 */
public class RgbPixel implements Pixel {


    public byte r, g, b;



    public RgbPixel() {}


    public RgbPixel(int r, int g, int b) {
        this.r = (byte) r;
        this.g = (byte) g;
        this.b = (byte) b;
    }


    public RgbPixel(RgbPixel other) {
        r = other.r;
        g = other.g;
        b = other.b;
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
    public int getEncoded() {
        return (r & 0xff) << 16 | (g & 0xff) << 8 | (b & 0xff);
    }

    @Override
    public int getEncodedRGBA() {
        return -16777216 | (r & 0xff) << 16 | (g & 0xff) << 8 | (b & 0xff);
    }

    @Override
    public int getEncodedAlphaMultipliedRGB() {
        return -16777216 | (r & 0xff) << 16 | (g & 0xff) << 8 | (b & 0xff);
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
    public void setEncoded(int encoded) {
        r = (byte) ((encoded >> 16) & 0xff);
        g = (byte) ((encoded >> 8)  & 0xff);
        b = (byte) (encoded         & 0xff);
    }

    @Override
    public void setEncodedRGB(int encodedRGB) {
        r = (byte) ((encodedRGB >> 16) & 0xff);
        g = (byte) ((encodedRGB >> 8)  & 0xff);
        b = (byte) (encodedRGB         & 0xff);
    }



    public void blend(RgbPixel other, float opacity) {
        r = (byte) MathUtil.blend(r & 0xff, other.r & 0xff, opacity);
        g = (byte) MathUtil.blend(g & 0xff, other.g & 0xff, opacity);
        b = (byte) MathUtil.blend(b & 0xff, other.b & 0xff, opacity);
    }

    public void blend(RgbaPixel other, float opacity) {
        float compositeAlpha = MathUtil.computeCompositeAlpha(other.a & 0xff, opacity);
        r = (byte) MathUtil.blend(r & 0xff, other.r & 0xff, compositeAlpha);
        g = (byte) MathUtil.blend(g & 0xff, other.g & 0xff, compositeAlpha);
        b = (byte) MathUtil.blend(b & 0xff, other.b & 0xff, compositeAlpha);
    }



    @Override
    public String toString() {
        return String.format("RGB(%d, %d, %d)", getR(), getG(), getB());
    }

}
