package image;

import util.MathUtil;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;


public class ByteRgbaImage extends ByteRgbImage implements Image {


    protected byte[] as;



    ByteRgbaImage(BufferedImage bi, String fileName, String directory) {
        super(bi, fileName, directory);
    }

    ByteRgbaImage(int width, int height) {
        super(width, height);
    }

    @Override
    protected void setDefaultType() {
        type = DEFAULT_TYPE_RGBA;
    }

    @Override
    protected void initData() {
        rs = new byte[dataLength];
        gs = new byte[dataLength];
        bs = new byte[dataLength];
        as = new byte[dataLength];
    }

    @Override
    protected void fillData(ColorModel cm, Raster raster) {

        Object dataElements;

        for (int i = 0; i < dataLength; i++) {

            dataElements = raster.getDataElements(i % width, i / width, null);

            rs[i] = (byte) cm.getRed   (dataElements);
            gs[i] = (byte) cm.getGreen (dataElements);
            bs[i] = (byte) cm.getBlue  (dataElements);
            as[i] = (byte) cm.getAlpha (dataElements);
        }
    }



    public byte[] getDataA() {
        return as;
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
        return as[i] & 0xff;
    }

    @Override
    public int getEncoded(int i) {
        return (as[i] & 0xff) << 24 | (rs[i] & 0xff) << 16 | (gs[i] & 0xff) << 8 | bs[i] & 0xff;
    }

    @Override
    public int getEncodedRGBA(int i) {
        return (as[i] & 0xff) << 24 | (rs[i] & 0xff) << 16 | (gs[i] & 0xff) << 8 | bs[i] & 0xff;
    }

    @Override
    public int getEncodedAlphaMultipliedRGB(int i) {
        return MathUtil.multiplyAlpha(rs[i] & 0xff, gs[i] & 0xff, bs[i] & 0xff, as[i] & 0xff);
    }

    @Override
    public void setA(int x, int y, int a) {
        setA(y * width + x, a);
    }

    @Override
    public void setA(int i, int a) {
        as[i] = (byte) a;
    }

    @Override
    public void setRGBA(int i, int r, int g, int b, int a) {
        rs[i] = (byte) r;
        gs[i] = (byte) g;
        bs[i] = (byte) b;
        as[i] = (byte) a;
    }

    @Override
    public void setEncoded(int i, int encoded) {
        as[i] = (byte) (encoded >>> 24);
        rs[i] = (byte) (encoded >>  16 & 0xff);
        gs[i] = (byte) (encoded >>  8  & 0xff);
        bs[i] = (byte) (encoded        & 0xff);
    }



    @Override
    public BufferedImage renderAlphaMultipliedImage() {

        BufferedImage rendered = new BufferedImage(width, height, DEFAULT_TYPE_RGB);

        for (int i = 0; i < dataLength; i++)
            rendered.setRGB(i % width, i / width, MathUtil.multiplyAlpha(rs[i] & 0xff, gs[i] & 0xff, bs[i] & 0xff, as[i] & 0xff));

        return rendered;
    }

    @Override
    public BufferedImage renderAlphaDiscardedImage() {
        return super.dataToImage(DEFAULT_TYPE_RGB);
    }

    @Override
    protected BufferedImage dataToImage(int type) {

        BufferedImage bi = new BufferedImage(width, height, type);

        for (int i = 0; i < dataLength; i++)
            bi.setRGB(i % width, i / width, (as[i] & 0xff) << 24 | (rs[i] & 0xff) << 16 | (gs[i] & 0xff) << 8 | (bs[i] & 0xff));

        return bi;
    }



    @Override
    public ByteRgbaImage clone() {

        ByteRgbaImage cloned = (ByteRgbaImage) super.clone();

        cloned.as = new byte[dataLength];

        System.arraycopy(as, 0, cloned.as, 0, dataLength);

        return cloned;
    }

}
