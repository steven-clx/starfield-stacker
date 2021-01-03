package image;

import util.MathUtil;


/**
 * A pixel with 8-bit RGBA channels in byte type
 *
 * @author steven-clx
 */
public class RgbaPixel extends RgbPixel implements Pixel {


    public byte a;



    public RgbaPixel() {
        this.a = 127;
    }


    public RgbaPixel(int r, int g, int b, int a) {
        this.r = (byte) r;
        this.g = (byte) g;
        this.b = (byte) b;
        this.a = (byte) a;
    }


    public RgbaPixel(RgbaPixel other) {
        r = other.r;
        g = other.g;
        b = other.b;
        a = other.a;
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
        return (a & 0xff) << 24 | (r & 0xff) << 16 | (g & 0xff) << 8 | (b & 0xff);
    }

    @Override
    public int getEncodedRGBA() {
        return (a & 0xff) << 24 | (r & 0xff) << 16 | (g & 0xff) << 8 | (b & 0xff);
    }

    @Override
    public int getEncodedAlphaMultipliedRGB() {
        return MathUtil.multiplyAlpha(getR(), getG(), getB(), getA());
    }

    @Override
    public void setA(int a) {
        this.a = (byte) a;
    }

    @Override
    public void setRGBA(int r, int g, int b, int a) {
        this.r = (byte) r;
        this.g = (byte) g;
        this.b = (byte) b;
        this.a = (byte) a;
    }

    @Override
    public void setEncoded(int encoded) {
        a = (byte) (encoded >>> 24);
        r = (byte) ((encoded >> 16) & 0xff);
        g = (byte) ((encoded >> 8)  & 0xff);
        b = (byte) (encoded         & 0xff);
    }



    @Override
    public void blend(RgbPixel other, float opacity) {
        float a1 = (a & 0xff) / 255f;
        float a12 = MathUtil.blendAlpha(a1, opacity);
        r = (byte) MathUtil.blend(r & 0xff, other.r & 0xff, a1, opacity, a12);
        g = (byte) MathUtil.blend(g & 0xff, other.g & 0xff, a1, opacity, a12);
        b = (byte) MathUtil.blend(b & 0xff, other.b & 0xff, a1, opacity, a12);
        a = (byte) Math.round(a12 * 255);
    }

    @Override
    public void blend(RgbaPixel other, float opacity) {
        float compositeAlpha = MathUtil.computeCompositeAlpha(other.a & 0xff, opacity);
        float a1 = (a & 0xff) / 255f;
        float a12 = MathUtil.blendAlpha(a1, compositeAlpha);
        r = (byte) MathUtil.blend(r & 0xff, other.r & 0xff, a1, compositeAlpha, a12);
        g = (byte) MathUtil.blend(g & 0xff, other.g & 0xff, a1, compositeAlpha, a12);
        b = (byte) MathUtil.blend(b & 0xff, other.b & 0xff, a1, compositeAlpha, a12);
        a = (byte) Math.round(a12 * 255);
    }



    @Override
    public String toString() {
        return String.format("RGBA(%d, %d, %d, %.2f)", getR(), getG(), getB(), getA() / 255f);
    }

}
