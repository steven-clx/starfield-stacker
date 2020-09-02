package image;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;


public class IntRgbImage extends AbstractImage implements Image {


    protected int[] data;



    public IntRgbImage(BufferedImage bi, String fileName, String directory) {

        super(fileName, directory);

        type = bi.getType();

        width = bi.getWidth();
        height = bi.getHeight();

        dataLength = width * height;
        data = new int[dataLength];

        ColorModel cm = bi.getColorModel();

        Raster raster = bi.getRaster();

        for (int i = 0; i < dataLength; i++)
            data[i] = cm.getRGB(raster.getDataElements(i % width, i / width, null));
    }


    public IntRgbImage(int width, int height) {

        super();

        setDefaultType();

        this.width = width;
        this.height = height;

        dataLength = width * height;
        data = new int[dataLength];
    }


    protected void setDefaultType() {
        type = DEFAULT_TYPE_RGB;
    }



    public int[] getData() {
        return data;
    }



    @Override
    public boolean hasAlpha() {
        return false;
    }



    @Override
    public int getR(int x, int y) {
        return getR(y * width + x);
    }

    @Override
    public int getR(int i) {
        return data[i] >> 16 & 0xff;
    }

    @Override
    public int getG(int x, int y) {
        return getG(y * width + x);
    }

    @Override
    public int getG(int i) {
        return data[i] >> 8 & 0xff;
    }

    @Override
    public int getB(int x, int y) {
        return getB(y * width + x);
    }

    @Override
    public int getB(int i) {
        return data[i] & 0xff;
    }

    @Override
    public int getA(int x, int y) {
        return 255;
    }

    @Override
    public int getA(int i) {
        return 255;
    }

    @Override
    public int getEncoded(int x, int y) {
        return getEncoded(y * width + x);
    }

    @Override
    public int getEncoded(int i) {
        return data[i];
    }

    @Override
    public int getEncodedRGBA(int x, int y) {
        return getEncodedRGBA(y * width + x);
    }

    @Override
    public int getEncodedRGBA(int i) {
        return -16777216 | data[i];
    }

    @Override
    public int getEncodedAlphaMultipliedRGB(int x, int y) {
        return getEncodedAlphaMultipliedRGB(y * width + x);
    }

    @Override
    public int getEncodedAlphaMultipliedRGB(int i) {
        return -16777216 | data[i];
    }

    @Override
    public int getBrightness(int x, int y) {
        return getBrightness(y * width + x);
    }

    @Override
    public int getBrightness(int i) {
        int encoded = data[i];
        return Math.round(((encoded >> 16 & 0xff) + (encoded >> 8 & 0xff) + (encoded & 0xff)) / 3f);
    }

    @Override
    public void setR(int x, int y, int r) {
        setR(y * width + x, r);
    }

    @Override
    public void setR(int i, int r) {
        data[i] = r << 16 | (data[i] & 0xffff);
    }

    @Override
    public void setG(int x, int y, int g) {
        setG(y * width + x, g);
    }

    @Override
    public void setG(int i, int g) {
        int old = data[i];
        data[i] = old & -65536 | g << 8 | (old & 0xff);
    }

    @Override
    public void setB(int x, int y, int b) {
        setB(y * width + x, b);
    }

    @Override
    public void setB(int i, int b) {
        data[i] = data[i] & -256 | b;
    }

    @Override
    public void setA(int x, int y, int a) {}

    @Override
    public void setA(int i, int a) {}

    @Override
    public void setRGB(int x, int y, int r, int g, int b) {
        setRGB(y * width + x, r, g, b);
    }

    @Override
    public void setRGB(int i, int r, int g, int b) {
        data[i] = r << 16 | g << 8 | b;
    }

    @Override
    public void setRGBA(int x, int y, int r, int g, int b, int a) {
        setRGBA(y * width + x, r, g, b, a);
    }

    @Override
    public void setRGBA(int i, int r, int g, int b, int a) {
        data[i] = r << 16 | g << 8 | b;
    }

    @Override
    public void setEncoded(int x, int y, int encoded) {
        setEncoded(y * width + x, encoded);
    }

    @Override
    public void setEncoded(int i, int encoded) {
        data[i] = encoded;
    }

    @Override
    public void setEncodedRGB(int x, int y, int encodedRGB) {
        setEncodedRGB(y * width + x, encodedRGB);
    }

    @Override
    public void setEncodedRGB(int i, int encodedRGB) {
        data[i] = encodedRGB;
    }



    @Override
    public BufferedImage render() {
        return render(type);
    }

    @Override
    public BufferedImage renderAlphaMultiplied() {
        return render(type);
    }

    @Override
    public BufferedImage renderAlphaDiscarded() {
        return render(type);
    }


    protected BufferedImage render(int type) {

        BufferedImage rendered = new BufferedImage(width, height, type);

        for (int i = 0; i < dataLength; i++)
            rendered.setRGB(i % width, i / width, data[i]);

        return rendered;
    }



    @Override
    public IntRgbImage clone() {

        IntRgbImage cloned = (IntRgbImage) super.clone();

        cloned.data = new int[dataLength];
        System.arraycopy(data, 0, cloned.data, 0, dataLength);

        return cloned;
    }

}
