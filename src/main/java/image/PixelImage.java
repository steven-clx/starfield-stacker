package image;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;


public class PixelImage extends AbstractImage implements Image {


    protected RgbPixel[] pixels;

    protected boolean hasAlpha;



    public PixelImage(BufferedImage bi, String fileName, String directory) {

        super(fileName, directory);

        type = bi.getType();

        width = bi.getWidth();
        height = bi.getHeight();

        dataLength = width * height;
        pixels = new RgbPixel[dataLength];

        ColorModel cm = bi.getColorModel();
        hasAlpha = cm.hasAlpha();

        Raster raster = bi.getRaster();


        Object dataElement = null;

        if (hasAlpha) {

            for (int i = 0; i < dataLength; i++) {

                dataElement = raster.getDataElements(i % width, i / width, dataElement);
                int alpha = cm.getAlpha(dataElement);

                if (alpha == 255) {
                    pixels[i] = new RgbPixel(
                            cm.getRed   (dataElement),
                            cm.getGreen (dataElement),
                            cm.getBlue  (dataElement));
                } else {
                    pixels[i] = new RgbaPixel(
                            cm.getRed   (dataElement),
                            cm.getGreen (dataElement),
                            cm.getBlue  (dataElement),
                            alpha);
                }
            }

        } else {

            for (int i = 0; i < dataLength; i++) {
                dataElement = raster.getDataElements(i % width, i / width, dataElement);
                pixels[i] = new RgbPixel(
                        cm.getRed   (dataElement),
                        cm.getGreen (dataElement),
                        cm.getBlue  (dataElement));
            }
        }
    }


    public PixelImage(int width, int height, boolean hasAlpha) {

        super();

        this.width = width;
        this.height = height;

        dataLength = width * height;

        this.hasAlpha = hasAlpha;

        if (hasAlpha) {
            type = DEFAULT_TYPE_RGBA;
            pixels = new RgbaPixel[dataLength];
            for (int i = 0; i < dataLength; i++)
                pixels[i] = new RgbaPixel(0, 0, 0, 255);
        } else {
            type = DEFAULT_TYPE_RGB;
            pixels = new RgbPixel[dataLength];
            for (int i = 0; i < dataLength; i++)
                pixels[i] = new RgbPixel(0, 0, 0);
        }
    }



    public RgbPixel getPixel(int x, int y) {
        return pixels[y * width + x];
    }

    public RgbPixel[] getData() {
        return pixels;
    }

    public void setHasAlpha(boolean hasAlpha) {

        if (!hasAlpha && this.hasAlpha)
            type = DEFAULT_TYPE_RGB;

        else if (hasAlpha && !this.hasAlpha)
            type = DEFAULT_TYPE_RGBA;

        this.hasAlpha = hasAlpha;
    }



    @Override
    public boolean hasAlpha() {
        return hasAlpha;
    }



    @Override
    public int getR(int x, int y) {
        return getR(y * width + x);
    }

    @Override
    public int getR(int i) {
        return pixels[i].getR();
    }

    @Override
    public int getG(int x, int y) {
        return getG(y * width + x);
    }

    @Override
    public int getG(int i) {
        return pixels[i].getG();
    }

    @Override
    public int getB(int x, int y) {
        return getB(y * width + x);
    }

    @Override
    public int getB(int i) {
        return pixels[i].getB();
    }

    @Override
    public int getA(int x, int y) {
        return getA(y * width + x);
    }

    @Override
    public int getA(int i) {
        return pixels[i].getA();
    }

    @Override
    public int getEncoded(int x, int y) {
        return getEncoded(y * width + x);
    }

    @Override
    public int getEncoded(int i) {
        return pixels[i].getEncoded();
    }

    @Override
    public int getEncodedRGBA(int x, int y) {
        return getEncodedRGBA(y * width + x);
    }

    @Override
    public int getEncodedRGBA(int i) {
        return pixels[i].getEncodedRGBA();
    }

    @Override
    public int getEncodedAlphaMultipliedRGB(int x, int y) {
        return getEncodedAlphaMultipliedRGB(y * width + x);
    }

    @Override
    public int getEncodedAlphaMultipliedRGB(int i) {
        return pixels[i].getEncodedAlphaMultipliedRGB();
    }

    @Override
    public int getBrightness(int x, int y) {
        return getBrightness(y * width + x);
    }

    @Override
    public int getBrightness(int i) {
        return pixels[i].getBrightness();
    }

    @Override
    public void setR(int x, int y, int r) {
        setR(y * width + x, r);
    }

    @Override
    public void setR(int i, int r) {
        pixels[i].setR(r);
    }

    @Override
    public void setG(int x, int y, int g) {
        setG(y * width + x, g);
    }

    @Override
    public void setG(int i, int g) {
        pixels[i].setG(g);
    }

    @Override
    public void setB(int x, int y, int b) {
        setB(y * width + x, b);
    }

    @Override
    public void setB(int i, int b) {
        pixels[i].setB(b);
    }

    @Override
    public void setA(int x, int y, int a) {
        setA(y * width + x, a);
    }

    @Override
    public void setA(int i, int a) {
        pixels[i].setA(a);
    }

    @Override
    public void setRGB(int x, int y, int r, int g, int b) {
        setRGB(y * width + x, r, g, b);
    }

    @Override
    public void setRGB(int i, int r, int g, int b) {
        pixels[i].setRGB(r, g, b);
    }

    @Override
    public void setRGBA(int x, int y, int r, int g, int b, int a) {
        setRGBA(y * width + x, r, g, b, a);
    }

    @Override
    public void setRGBA(int i, int r, int g, int b, int a) {
        pixels[i].setRGBA(r, g, b, a);
    }

    @Override
    public void setEncoded(int x, int y, int encoded) {
        setEncoded(y * width + x, encoded);
    }

    @Override
    public void setEncoded(int i, int encoded) {
        pixels[i].setEncoded(encoded);
    }

    @Override
    public void setEncodedRGB(int x, int y, int encodedRGB) {
        setEncodedRGB(y * width + x, encodedRGB);
    }

    @Override
    public void setEncodedRGB(int i, int encodedRGB) {
        pixels[i].setEncodedRGB(encodedRGB);
    }



    @Override
    public BufferedImage render() {

        BufferedImage rendered = new BufferedImage(width, height, type);

        if (hasAlpha)
            for (int i = 0; i < dataLength; i++)
                rendered.setRGB(i % width, i / width, pixels[i].getEncodedRGBA());

        else
            for (int i = 0; i < dataLength; i++)
                rendered.setRGB(i % width, i / width, pixels[i].getEncoded());

        return rendered;
    }

    @Override
    public BufferedImage renderAlphaMultiplied() {

        if (!hasAlpha) return render();

        BufferedImage rendered = new BufferedImage(width, height, DEFAULT_TYPE_RGB);

        for (int i = 0; i < dataLength; i++)
            rendered.setRGB(i % width, i / width, pixels[i].getEncodedAlphaMultipliedRGB());

        return rendered;
    }

    @Override
    public BufferedImage renderAlphaDiscarded() {

        if (!hasAlpha) return render();

        BufferedImage rendered = new BufferedImage(width, height, DEFAULT_TYPE_RGB);

        for (int i = 0; i < dataLength; i++)
            rendered.setRGB(i % width, i / width, pixels[i].getEncoded());

        return rendered;
    }



    @Override
    public PixelImage clone() {

        PixelImage cloned = (PixelImage) super.clone();

        RgbPixel[] clonedPixels = new RgbPixel[dataLength];

        RgbPixel toClone;

        for (int i = 0; i < dataLength; i++) {
            toClone = pixels[i];
            clonedPixels[i] = toClone.hasAlpha() ? new RgbaPixel((RgbaPixel) toClone) : new RgbPixel(toClone);
        }

        cloned.pixels = clonedPixels;

        return cloned;
    }

}
