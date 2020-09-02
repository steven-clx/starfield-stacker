package util;


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


    public static int multiplyAlpha(int encodedRGBA) {

        int alpha = encodedRGBA >>> 24;

        int addend = 255 - alpha;
        float multiplier = alpha / 255f;

        return                                                         (-16777216 |
               Math.round(addend + (encodedRGBA >> 16 & 0xff) * multiplier) << 16 |
               Math.round(addend + (encodedRGBA >> 8  & 0xff) * multiplier) << 8  |
               Math.round(addend + (encodedRGBA       & 0xff) * multiplier));
    }


    public static int multiplyAlpha(int r, int g, int b, int a) {

        int addend = 255 - a;
        float multiplier = a / 255f;

        return                                (-16777216 |
               Math.round(addend + r * multiplier) << 16 |
               Math.round(addend + g * multiplier) << 8  |
               Math.round(addend + b * multiplier));
    }


    public static float computeCompositeAlpha(int alpha, float opacity) {
        return alpha * opacity / 255;
    }


    /**
     * v1 * (1 - a2) + v2 * a2
     *
     * @param v1
     * @param v2
     * @param a2
     * @return
     */
    public static int blend(int v1, int v2, float a2) {
        return Math.round(v1 - (v1 - v2) * a2);
    }


    /**
     * (v1 * a1 * (1 - a2) + v2 * a2) / a12
     *
     * @param v1
     * @param v2
     * @param a1
     * @param a2
     * @param a12
     * @return
     */
    public static int blend(int v1, int v2, float a1, float a2, float a12) {
        return Math.round((v1 * a1 * (1 - a2) + v2 * a2) / a12);
    }


    /**
     * 1 - (1 - a1) * (1 - a2)
     *
     * @param a1
     * @param a2
     * @return
     */
    public static float blendAlpha(float a1, float a2) {
        return a1 + a2 - a1 * a2;
    }













//    To delete
    public static int brighten(int v1, int v2, float a2) {
        return blend(v1, Math.max(v1, v2), a2);
    }



    private MathUtil() {}

}
