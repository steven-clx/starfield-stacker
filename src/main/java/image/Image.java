package image;

import java.awt.image.BufferedImage;

public interface Image extends Cloneable {

    void setFileName(String fileName);
    void appendSuffix(String suffix);
    void setDirectory(String directory);

    String getFileName();
    String getDirectory();

    int getWidth();
    int getHeight();
    int getDataLength();

    boolean hasAlpha();

    int getR(int x, int y);
    int getR(int i);

    int getG(int x, int y);
    int getG(int i);

    int getB(int x, int y);
    int getB(int i);

    int getA(int x, int y);
    int getA(int i);

    /**
     * Get the encoded integer of the pixel at (x, y)
     *
     * For opaque images, the first 8 bits in the return value can be any arbitrary
     * value that may be automatically generated in the implementation classes and
     * tampered during the manipulation of image, and therefore the return value
     * cannot be used to extract the alpha channel
     *
     * @param x the x coordinate of the assigned pixel
     * @param y the y coordinate of the assigned pixel
     * @return the encoded integer of the pixel at (x, y)
     */
    int getEncoded(int x, int y);
    int getEncoded(int i);

    /**
     * Get the encoded integer of the pixel at (x, y)
     *
     * For opaque images, the alpha channel is filled with 255
     *
     * @param x the x coordinate of the assigned pixel
     * @param y the y coordinate of the assigned pixel
     * @return the encoded integer of the pixel at (x, y)
     */
    int getEncodedRGBA(int x, int y);
    int getEncodedRGBA(int i);

    int getEncodedAlphaMultipliedRGB(int x, int y);
    int getEncodedAlphaMultipliedRGB(int i);

    int getBrightness(int x, int y);
    int getBrightness(int i);

    void setR(int x, int y, int r);
    void setR(int i, int r);

    void setG(int x, int y, int g);
    void setG(int i, int g);

    void setB(int x, int y, int b);
    void setB(int i, int b);

    void setA(int x, int y, int a);
    void setA(int i, int a);

    /**
     * Assign the r, g, b channels to the pixel at (x, y)
     *
     * For translucent images, the alpha channel will not be affected by this operation
     *
     * @param x the x coordinate of the assigned pixel
     * @param y the y coordinate of the assigned pixel
     * @param r the red channel
     * @param g the green channel
     * @param b the blue channel
     */
    void setRGB(int x, int y, int r, int g, int b);
    void setRGB(int i, int r, int g, int b);

    /**
     * Assign the r, g, b, a channels to the pixel at (x, y)
     *
     * For opaque images, the alpha channel can be any arbitrary value and will not be used
     *
     * @param x the x coordinate of the assigned pixel
     * @param y the y coordinate of the assigned pixel
     * @param r the red channel
     * @param g the green channel
     * @param b the blue channel
     * @param a the alpha channel
     */
    void setRGBA(int x, int y, int r, int g, int b, int a);
    void setRGBA(int i, int r, int g, int b, int a);

    /**
     * Assign the encoded integer to the pixel at (x, y)
     *
     * For opaque images, the encoded integer does not need to specify the first 8
     * bits which is used for the alpha channel, and the value of the first 8 bits
     * will not be used, therefore the first 8 bits can be any value
     *
     * For translucent images, the encoded integer must specify the value of the
     * alpha channel in the first 8 bits
     *
     * @param x the x coordinate of the assigned pixel
     * @param y the y coordinate of the assigned pixel
     * @param encoded the encoded integer (alpha | red | green | blue), each channel occupies 8 bits
     */
    void setEncoded(int x, int y, int encoded);
    void setEncoded(int i, int encoded);

    /**
     * Assign the encoded integer to the pixel at (x, y)
     *
     * For both opaque and translucent images, the encoded integer does not need to
     * specify the first 8 bits which is used for the alpha channel, and the value
     * of the first 8 bits will not be used
     *
     * @param x the x coordinate of the assigned pixel
     * @param y the y coordinate of the assigned pixel
     * @param encodedRGB the encoded integer (alpha | red | green | blue), each channel occupies 8 bits
     */
    void setEncodedRGB(int x, int y, int encodedRGB);
    void setEncodedRGB(int i, int encodedRGB);

    BufferedImage render();

    BufferedImage renderAlphaMultipliedImage();

    BufferedImage renderAlphaDiscardedImage();

    Image clone();

}
