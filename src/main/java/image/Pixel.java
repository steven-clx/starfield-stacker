package image;

public interface Pixel {

    boolean hasAlpha();

    int getR();
    int getG();
    int getB();
    int getA();

    int getEncoded();
    int getEncodedRGBA();
    int getEncodedAlphaMultipliedRGB();

    int getBrightness();

    void setR(int r);
    void setG(int g);
    void setB(int b);
    void setA(int a);

    void setRGB(int r, int g, int b);
    void setRGBA(int r, int g, int b, int a);

    void setEncoded(int encoded);
    void setEncodedRGB(int encodedRGB);

}
