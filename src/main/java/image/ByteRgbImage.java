package image;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;


public class ByteRgbImage extends AbstractImage implements Image {


    protected byte[] rs, gs, bs;



    ByteRgbImage(BufferedImage bi, String fileName, String directory) {

        super(fileName, directory);

        type = bi.getType();

        width = bi.getWidth();
        height = bi.getHeight();

        dataLength = width * height;

        initData();

        fillData(bi.getColorModel(), bi.getRaster());
    }


    ByteRgbImage(int width, int height) {

        super();

        setDefaultType();

        this.width = width;
        this.height = height;

        dataLength = width * height;

        initData();
    }


    protected void setDefaultType() {
        type = DEFAULT_TYPE_RGB;
    }


    protected void initData() {
        rs = new byte[dataLength];
        gs = new byte[dataLength];
        bs = new byte[dataLength];
    }


    protected void fillData(ColorModel cm, Raster raster) {

        Object dataElements;

        for (int i = 0; i < dataLength; i++) {

            dataElements = raster.getDataElements(i % width, i / width, null);

            rs[i] = (byte) cm.getRed   (dataElements);
            gs[i] = (byte) cm.getGreen (dataElements);
            bs[i] = (byte) cm.getBlue  (dataElements);
        }
    }



    public byte[] getDataR() {
        return rs;
    }

    public byte[] getDataG() {
        return gs;
    }

    public byte[] getDataB() {
        return bs;
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
        return rs[i] & 0xff;
    }

    @Override
    public int getG(int x, int y) {
        return getG(y * width + x);
    }

    @Override
    public int getG(int i) {
        return gs[i] & 0xff;
    }

    @Override
    public int getB(int x, int y) {
        return getB(y * width + x);
    }

    @Override
    public int getB(int i) {
        return bs[i] & 0xff;
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
        return (rs[i] & 0xff) << 16 | (gs[i] & 0xff) << 8 | bs[i] & 0xff;
    }

    @Override
    public int getEncodedRGBA(int x, int y) {
        return getEncodedRGBA(y * width + x);
    }

    @Override
    public int getEncodedRGBA(int i) {
        return -16777216 | (rs[i] & 0xff) << 16 | (gs[i] & 0xff) << 8 | bs[i] & 0xff;
    }

    @Override
    public int getEncodedAlphaMultipliedRGB(int x, int y) {
        return getEncodedAlphaMultipliedRGB(y * width + x);
    }

    @Override
    public int getEncodedAlphaMultipliedRGB(int i) {
        return -16777216 | (rs[i] & 0xff) << 16 | (gs[i] & 0xff) << 8 | bs[i] & 0xff;
    }

    @Override
    public int getBrightness(int x, int y) {
        return getBrightness(y * width + x);
    }

    @Override
    public int getBrightness(int i) {
        return Math.round(((rs[i] & 0xff) + (gs[i] & 0xff) + (bs[i] & 0xff)) / 3f);
    }

    @Override
    public void setR(int x, int y, int r) {
        setR(y * width + x, r);
    }

    @Override
    public void setR(int i, int r) {
        rs[i] = (byte) r;
    }

    @Override
    public void setG(int x, int y, int g) {
        setG(y * width + x, g);
    }

    @Override
    public void setG(int i, int g) {
        gs[i] = (byte) g;
    }

    @Override
    public void setB(int x, int y, int b) {
        setB(y * width + x, b);
    }

    @Override
    public void setB(int i, int b) {
        bs[i] = (byte) b;
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
        rs[i] = (byte) r;
        gs[i] = (byte) g;
        bs[i] = (byte) b;
    }

    @Override
    public void setRGBA(int x, int y, int r, int g, int b, int a) {
        setRGBA(y * width + x, r, g, b, a);
    }

    @Override
    public void setRGBA(int i, int r, int g, int b, int a) {
        rs[i] = (byte) r;
        gs[i] = (byte) g;
        bs[i] = (byte) b;
    }

    @Override
    public void setEncoded(int x, int y, int encoded) {
        setEncoded(y * width + x, encoded);
    }

    @Override
    public void setEncoded(int i, int encoded) {
        rs[i] = (byte) (encoded >> 16 & 0xff);
        gs[i] = (byte) (encoded >> 8  & 0xff);
        bs[i] = (byte) (encoded       & 0xff);
    }

    @Override
    public void setEncodedRGB(int x, int y, int encodedRGB) {
        setEncodedRGB(y * width + x, encodedRGB);
    }

    @Override
    public void setEncodedRGB(int i, int encodedRGB) {
        rs[i] = (byte) (encodedRGB >> 16 & 0xff);
        gs[i] = (byte) (encodedRGB >> 8  & 0xff);
        bs[i] = (byte) (encodedRGB       & 0xff);
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
            rendered.setRGB(i % width, i / width, (rs[i] & 0xff) << 16 | (gs[i] & 0xff) << 8 | bs[i] & 0xff);

        return rendered;
    }



    @Override
    public ByteRgbImage clone() {

        ByteRgbImage cloned = (ByteRgbImage) super.clone();

        cloned.rs = new byte[dataLength];
        cloned.gs = new byte[dataLength];
        cloned.bs = new byte[dataLength];

        System.arraycopy(rs, 0, cloned.rs, 0, dataLength);
        System.arraycopy(gs, 0, cloned.gs, 0, dataLength);
        System.arraycopy(bs, 0, cloned.bs, 0, dataLength);

        return cloned;
    }

}
