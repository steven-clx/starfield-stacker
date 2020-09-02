package image;

import util.MathUtil;

import java.awt.image.BufferedImage;


public class IntRgbaImage extends IntRgbImage implements Image {


    public IntRgbaImage(BufferedImage bi, String fileName, String directory) {
        super(bi, fileName, directory);
    }

    public IntRgbaImage(int width, int height) {
        super(width, height);
    }

    @Override
    protected void setDefaultType() {
        type = DEFAULT_TYPE_RGBA;
    }



    @Override
    public boolean hasAlpha() {
        return true;
    }



    @Override
    public int getA(int x, int y) {
        return getA(y * width + x);
    }

    @Override
    public int getA(int i) {
        return data[i] >>> 24;
    }

    @Override
    public int getEncodedRGBA(int i) {
        return data[i];
    }

    @Override
    public int getEncodedAlphaMultipliedRGB(int i) {
        return MathUtil.multiplyAlpha(data[i]);
    }

    @Override
    public void setR(int i, int r) {
        int old = data[i];
        data[i] = old & -16777216 | r << 16 | (old & 0xffff);
    }

    @Override
    public void setA(int x, int y, int a) {
        setA(y * width + x, a);
    }

    @Override
    public void setA(int i, int a) {
        data[i] = a << 24 | (data[i] & 0xffffff);
    }

    @Override
    public void setRGB(int i, int r, int g, int b) {
        data[i] = data[i] & -16777216 | r << 16 | g << 8 | b;
    }

    @Override
    public void setRGBA(int i, int r, int g, int b, int a) {
        data[i] = a << 24 | r << 16 | g << 8 | b;
    }

    @Override
    public void setEncodedRGB(int i, int encodedRGB) {
        data[i] = data[i] & -16777216 | (encodedRGB & 0xffffff);
    }



    @Override
    public BufferedImage renderAlphaMultiplied() {

        BufferedImage rendered = new BufferedImage(width, height, DEFAULT_TYPE_RGB);

        for (int i = 0; i < dataLength; i++)
            rendered.setRGB(i % width, i / width, MathUtil.multiplyAlpha(data[i]));

        return rendered;
    }

    @Override
    public BufferedImage renderAlphaDiscarded() {
        return render(DEFAULT_TYPE_RGB);
    }



    @Override
    public IntRgbaImage clone() {
        return (IntRgbaImage) super.clone();
    }

}
