package util;


import image.Pixel;
import image.RgbaPixel;

/**
 * Utility methods for math operations
 */
public class MathUtil {


    public static short max(short a, short b) {
        return a > b ? a : b;
    }


    public static short min(short a, short b) {
        return a < b ? a : b;
    }


    /**
     * v1 * (1 - a2) + v2 * a2
     *
     * @param v1
     * @param v2
     * @param a2
     * @return
     */
    public static int blend(int v1, int v2, double a2) {
        return (int) Math.round(v1 - (v1 - v2) * a2);
    }


    public static int blend(int v1, int v2, double a1, double a2, double a12) {
        return (int) Math.round((v1 * a1 * (1 - a2) + v2 * a2) / a12);
    }


    /**
     * 1 - (1 - a1) * (1 - a2)
     *
     * @param a1
     * @param a2
     * @return
     */
    public static double blendAlpha(double a1, double a2) {
        return a1 + a2 - a1 * a2;
    }


    public static int brighten(int v1, int v2, double a2) {
        return blend(v1, Math.max(v1, v2), a2);
    }


    public static double computeCompositeAlpha(RgbaPixel pixel, double layerOpacity) {
        return layerOpacity * pixel.getA() / 255;
    }



    private MathUtil() {}

}
