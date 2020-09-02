package effects.algorithm;

import image.Image;
import util.MathUtil;

public class Brighten {

    public static void brighten(Image base, Image overlay, float overlayOpacity) {
        for (int i = 0; i < base.getDataLength(); i++) {
            int baseR = base.getR(i);
            int baseG = base.getG(i);
            int baseB = base.getB(i);
            base.setR(i, MathUtil.blend(baseR, Math.max(baseR, overlay.getR(i)), overlayOpacity));
            base.setG(i, MathUtil.blend(baseG, Math.max(baseG, overlay.getG(i)), overlayOpacity));
            base.setB(i, MathUtil.blend(baseB, Math.max(baseB, overlay.getB(i)), overlayOpacity));
        }
    }


    /**
     * When stacking and brightening a series of timelapse images using a gradually decreasing opacity from 1 to 0,
     * this method is used to calculate the opacity applied to each overlay
     *
     * For n total frames, the first frame will be used as the base image whose opacity should be 1. The base image
     * will not be stacked so it is excluded from this algorithm. The current count of frames, starting from 1 and
     * ending at n - 1, includes the immediate frame to be stacked next, is be used to calculate the opacity applied
     * to that frame
     *
     * This algorithm calculates the opacity using a rectangle in the first quadrant of an XOY plane coordinate
     * system. The rectangle has a width of totalFrames and a height of 1. The algorithm computes a monotonic
     * decreasing function y = f(currFrames) defined on [1, totalFrames - 1]. When currFrames has a given value
     * the corresponding y value will be used as the opacity. When stacking the last frame, currFrames is still
     * less than totalFrames, so the calculated opacity will always be greater than 0
     *
     * The function uses a straight line from 1 to 0 as the baseline. When concavity is set to 0, the baseline
     * is used to calculate y.
     *
     * @param currFrames the current number of frames (including the immediate next frame) stacked to the base image
     * @param totalFrames the total number of frames (including the base image) to be stacked
     * @param concavity
     * @param coefficient
     * @return the opacity of the immediate frame to be stacked next
     */
    public static float calculateOpacity(int currFrames, int totalFrames, float concavity, float coefficient) {

        double ratio = (double) currFrames / totalFrames;
        double baseline = 1 - ratio;

        if (concavity > 0)
            return (float) (baseline - (Math.sqrt(ratio * (2 - ratio)) - ratio) * concavity) * coefficient;
        else
            return (float) (baseline - (Math.sqrt(1 - ratio * ratio) - baseline) * concavity) * coefficient;
    }



    public static double calculateAlphaExplained(int currFrames, int totalFrames, float concavity) {

        double ratio = (double) currFrames / totalFrames;
        double baseline = 1 - ratio;
        double quarterCircleUpper = Math.sqrt(ratio * (2 - ratio));
        double quarterCircleLower = 1 - quarterCircleUpper;

        double res;

        if (concavity >= 0) {
            double diff;
            diff = baseline - quarterCircleLower;
            diff = 1 - ratio - 1 + quarterCircleUpper;
            diff = quarterCircleUpper - ratio;
            diff = Math.sqrt(ratio * (2 - ratio)) - ratio;

            res = 1 - ratio - diff * concavity;
            res = 1 - ratio - (Math.sqrt(ratio * (2 - ratio)) - ratio) * concavity;
        } else {
            double quarterCircleUpperRight;
            quarterCircleUpperRight = Math.sqrt((1 - ratio) * (2 - (1 - ratio)));
            quarterCircleUpperRight = Math.sqrt((1 - ratio) * (1 + ratio));
            quarterCircleUpperRight = Math.sqrt(1 - ratio * ratio);

            double diff;
            diff = baseline - quarterCircleUpperRight;
            diff = baseline - Math.sqrt(1 - ratio * ratio);
            diff = 1 - ratio - Math.sqrt(1 - ratio * ratio);

            res = baseline + diff * concavity;
            res = 1 - ratio + (1 - ratio - Math.sqrt(1 - ratio * ratio)) * concavity;
        }

        return res;
    }



    private Brighten() {}

}
