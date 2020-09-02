package effects.algorithm;

import image.*;
import util.MathUtil;


public class Blend {


    public static void blend(Image base, Image overlay, float overlayOpacity) {

        if (base instanceof ByteRgbImage && overlay instanceof ByteRgbImage) {
            blend((ByteRgbImage) base, (ByteRgbImage) overlay, overlayOpacity);
            return;
        }

        if (base instanceof IntRgbImage && overlay instanceof IntRgbImage) {
            blend((IntRgbImage) base, (IntRgbImage) overlay, overlayOpacity);
            return;
        }

        if (base instanceof PixelImage && overlay instanceof PixelImage) {
            blend((PixelImage) base, (PixelImage) overlay, overlayOpacity);
            return;
        }

        throw new IllegalArgumentException("image type not compatible: " +
                "base - " + base.getFileName() + " - "  + base.getClass() +
                ", overlay - " + overlay.getFileName() + " - "  + overlay.getClass());
    }


    public static void blend(PixelImage base, PixelImage overlay, float overlayOpacity) {

        Util.checkImageSize(base, overlay);

        RgbPixel[] basePixels = base.getData();
        RgbPixel[] overlayPixels = overlay.getData();

        int dataLength = base.getDataLength();


        // If the overlay is opaque, replace the base image's data with the overlay's data
        if (!overlay.hasAlpha() && overlayOpacity >= 1.0f) {

            // If the overlay is opaque, it should be converted to opaque
            // A blank BufferedImage with DEFAULT_TYPE_RGB will be created to hold the data
            if (base.hasAlpha()) {

                for (int i = 0; i < dataLength; i++) {

                    // If this pixel in the base image has alpha, then convert it to an RgbPixel
                    if (basePixels[i].hasAlpha()) {
                        basePixels[i] = new RgbPixel(overlayPixels[i]);

                    // Otherwise replace the pixel's values
                    } else {
                        basePixels[i].r = overlayPixels[i].r;
                        basePixels[i].g = overlayPixels[i].g;
                        basePixels[i].b = overlayPixels[i].b;
                    }
                }

                base.setHasAlpha(false);

            // If the base image is opaque, replace the pixels' values
            } else {
                for (int i = 0; i < dataLength; i++) {
                    basePixels[i].r = overlayPixels[i].r;
                    basePixels[i].g = overlayPixels[i].g;
                    basePixels[i].b = overlayPixels[i].b;
                }
            }

            return;
        }


        // If the overlay is translucent, its pixels might or might not have alpha
        if (overlay.hasAlpha()) {

            for (int i = 0; i < dataLength; i++) {

                // If the overlay pixel has alpha, it needs to be cast to RgbaPixel to call blend(RgbaPixel other, float opacity)
                if (overlayPixels[i].hasAlpha())
                    basePixels[i].blend((RgbaPixel) overlayPixels[i], overlayOpacity);

                // If the overlay pixel does not have alpha, call blend(RgbPixel other, float opacity)
                else
                    basePixels[i].blend(overlayPixels[i], overlayOpacity);
            }

        // If the overlay is opaque, it is guaranteed that its pixels do not have alpha,
        // therefore call blend(RgbPixel other, float opacity)
        } else {
            for (int i = 0; i < dataLength; i++)
                basePixels[i].blend(overlayPixels[i], overlayOpacity);
        }
    }



    public static void blend(ByteRgbImage base, ByteRgbImage overlay, float overlayOpacity) {

        Util.checkImageSize(base, overlay);

        byte[] baseDataR = base.getDataR();
        byte[] baseDataG = base.getDataG();
        byte[] baseDataB = base.getDataB();

        byte[] overlayDataR = overlay.getDataR();
        byte[] overlayDataG = overlay.getDataG();
        byte[] overlayDataB = overlay.getDataB();

        int dataLength = base.getDataLength();


        // If the overlay is opaque, replace the base image's data with the overlay's data
        if (!overlay.hasAlpha() && overlayOpacity >= 1.0f) {

            System.arraycopy(overlayDataR, 0, baseDataR, 0, dataLength);
            System.arraycopy(overlayDataG, 0, baseDataG, 0, dataLength);
            System.arraycopy(overlayDataB, 0, baseDataB, 0, dataLength);

            // If the base image is translucent, set the values in the alpha channel to (byte) 255
            if (base.hasAlpha()) {

                byte[] baseDataA = ((ByteRgbaImage) base).getDataA();

                for (int i = 0; i < dataLength; i++)
                    baseDataA[i] = -1;
            }

            return;
        }


        // If both images are translucent, use the alpha blend algorithm,
        // and calculate each overlay pixel's true alpha by multiplying the overlay opacity
        if (base.hasAlpha() && overlay.hasAlpha()) {

            byte[] baseDataA = ((ByteRgbaImage) base).getDataA();
            byte[] overlayDataA = ((ByteRgbaImage) overlay).getDataA();

            for (int i = 0; i < dataLength; i++) {

                float a1 = (baseDataA[i] & 0xff) / 255f;
                float a2 = MathUtil.computeCompositeAlpha(overlayDataA[i] & 0xff, overlayOpacity);
                float a12 = MathUtil.blendAlpha(a1, a2);

                baseDataR[i] = (byte) MathUtil.blend(baseDataR[i] & 0xff, overlayDataR[i] & 0xff, a1, a2, a12);
                baseDataG[i] = (byte) MathUtil.blend(baseDataG[i] & 0xff, overlayDataG[i] & 0xff, a1, a2, a12);
                baseDataB[i] = (byte) MathUtil.blend(baseDataB[i] & 0xff, overlayDataB[i] & 0xff, a1, a2, a12);
                baseDataA[i] = (byte) Math.round(a12 * 255);
            }


        // If only the base image is translucent, use the alpha blend algorithm
        } else if (base.hasAlpha()) {

            byte[] baseDataA = ((ByteRgbaImage) base).getDataA();

            for (int i = 0; i < dataLength; i++) {

                float a1 = (baseDataA[i] & 0xff) / 255f;
                float a12 = MathUtil.blendAlpha(a1, overlayOpacity);

                baseDataR[i] = (byte) MathUtil.blend(baseDataR[i] & 0xff, overlayDataR[i] & 0xff, a1, overlayOpacity, a12);
                baseDataG[i] = (byte) MathUtil.blend(baseDataG[i] & 0xff, overlayDataG[i] & 0xff, a1, overlayOpacity, a12);
                baseDataB[i] = (byte) MathUtil.blend(baseDataB[i] & 0xff, overlayDataB[i] & 0xff, a1, overlayOpacity, a12);
                baseDataA[i] = (byte) Math.round(a12 * 255);
            }


        // If only the overlay is translucent, calculate each overlay pixel's true alpha by multiplying the overlay opacity
        } else if (overlay.hasAlpha()) {

            byte[] overlayDataA = ((ByteRgbaImage) overlay).getDataA();

            for (int i = 0; i < dataLength; i++) {

                float a2 = MathUtil.computeCompositeAlpha(overlayDataA[i] & 0xff, overlayOpacity);

                baseDataR[i] = (byte) MathUtil.blend(baseDataR[i] & 0xff, overlayDataR[i] & 0xff, a2);
                baseDataG[i] = (byte) MathUtil.blend(baseDataG[i] & 0xff, overlayDataG[i] & 0xff, a2);
                baseDataB[i] = (byte) MathUtil.blend(baseDataB[i] & 0xff, overlayDataB[i] & 0xff, a2);
            }


        // If both images are opaque, blend each pixel with the overlay opacity
        } else {
            for (int i = 0; i < dataLength; i++) {
                baseDataR[i] = (byte) MathUtil.blend(baseDataR[i] & 0xff, overlayDataR[i] & 0xff, overlayOpacity);
                baseDataG[i] = (byte) MathUtil.blend(baseDataG[i] & 0xff, overlayDataG[i] & 0xff, overlayOpacity);
                baseDataB[i] = (byte) MathUtil.blend(baseDataB[i] & 0xff, overlayDataB[i] & 0xff, overlayOpacity);
            }
        }
    }




    public static void blend(IntRgbImage base, IntRgbImage overlay, float overlayOpacity) {

        Util.checkImageSize(base, overlay);

        int[] baseData = base.getData();
        int[] overlayData = overlay.getData();

        int dataLength = base.getDataLength();


        // If the overlay is opaque, replace the base image's data with the overlay's data
        if (!overlay.hasAlpha() && overlayOpacity >= 1.0f) {

            // If the base image is translucent, replace the values in r, g, b channels,
            // and set the values in the alpha channel to 255
            if (base.hasAlpha())
                for (int i = 0; i < dataLength; i++)
                    baseData[i] = -16777216 | overlayData[i];

            // If the base image is opaque, replace the values in its data
            else
                System.arraycopy(overlayData, 0, baseData, 0, dataLength);

            return;
        }


        // If both images are translucent, use the alpha blend algorithm,
        // and calculate each overlay pixel's true alpha by multiplying the overlay opacity
        if (base.hasAlpha() && overlay.hasAlpha()) {

            for (int i = 0; i < dataLength; i++) {

                float a1 = (baseData[i] >>> 24) / 255f;
                float a2 = MathUtil.computeCompositeAlpha(overlayData[i] >>> 24, overlayOpacity);
                float a12 = MathUtil.blendAlpha(a1, a2);

                int basePixel = baseData[i];
                int overlayPixel = overlayData[i];

                baseData[i] = Math.round(a12 * 255) << 24 |
                              MathUtil.blend(basePixel >> 16 & 0xff, overlayPixel >> 16 & 0xff, a1, a2, a12) << 16 |
                              MathUtil.blend(basePixel >> 8  & 0xff, overlayPixel >> 8  & 0xff, a1, a2, a12) << 8  |
                              MathUtil.blend(basePixel       & 0xff, overlayPixel       & 0xff, a1, a2, a12);
            }


        // If only the base image is translucent, use the alpha blend algorithm
        } else if (base.hasAlpha()) {

            for (int i = 0; i < dataLength; i++) {

                float a1 = (baseData[i] >>> 24) / 255f;
                float a12 = MathUtil.blendAlpha(a1, overlayOpacity);

                int basePixel = baseData[i];
                int overlayPixel = overlayData[i];

                baseData[i] = Math.round(a12 * 255) << 24 |
                              MathUtil.blend(basePixel >> 16 & 0xff, overlayPixel >> 16 & 0xff, a1, overlayOpacity, a12) << 16 |
                              MathUtil.blend(basePixel >> 8  & 0xff, overlayPixel >> 8  & 0xff, a1, overlayOpacity, a12) << 8  |
                              MathUtil.blend(basePixel       & 0xff, overlayPixel       & 0xff, a1, overlayOpacity, a12);
            }


        // If only the overlay is translucent, calculate each overlay pixel's true alpha by multiplying the overlay opacity
        } else if (overlay.hasAlpha()) {

            for (int i = 0; i < dataLength; i++) {

                float a2 = MathUtil.computeCompositeAlpha(overlayData[i] >>> 24, overlayOpacity);

                int basePixel = baseData[i];
                int overlayPixel = overlayData[i];

                baseData[i] = MathUtil.blend(basePixel >> 16 & 0xff, overlayPixel >> 16 & 0xff, a2) << 16 |
                              MathUtil.blend(basePixel >> 8  & 0xff, overlayPixel >> 8  & 0xff, a2) << 8  |
                              MathUtil.blend(basePixel       & 0xff, overlayPixel       & 0xff, a2);
            }


        // If both images are opaque, blend each pixel with the overlay opacity
        } else {

            for (int i = 0; i < dataLength; i++) {

                int basePixel = baseData[i];
                int overlayPixel = overlayData[i];

                baseData[i] = MathUtil.blend(basePixel >> 16 & 0xff, overlayPixel >> 16 & 0xff, overlayOpacity) << 16 |
                              MathUtil.blend(basePixel >> 8  & 0xff, overlayPixel >> 8  & 0xff, overlayOpacity) << 8  |
                              MathUtil.blend(basePixel       & 0xff, overlayPixel       & 0xff, overlayOpacity);
            }
        }
    }



    private Blend() {}

}
