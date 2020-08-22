package image;

public interface Pixel {

    boolean hasAlpha();

    int getR();
    int getG();
    int getB();
    int getA();

    short getX();
    short getY();

    int getEncoded();
    int getEncodedRGB();
    int getEncodedRGBA();
    int getEncodedAlphaMultipliedRGB();

    int getBrightness();

    void setR(int r);
    void setG(int g);
    void setB(int b);
    void setA(int a);
    void setRGB(int r, int g, int b);
    void setRGBA(int r, int g, int b, int a);

    void setMaxCheckedR(int r);
    void setMaxCheckedG(int g);
    void setMaxCheckedB(int b);
    void setMaxCheckedA(int a);

    void setMinCheckedR(int r);
    void setMinCheckedG(int g);
    void setMinCheckedB(int b);
    void setMinCheckedA(int a);

    void setX(int x);
    void setY(int y);
    void setXY(int x, int y);

    void setCodedInt(int codedInt);

}
