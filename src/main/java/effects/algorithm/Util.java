package effects.algorithm;

import image.Image;

class Util {

    static void checkImageSize(Image base, Image overlay) throws IllegalArgumentException {
        if (base.getWidth() != overlay.getWidth() || base.getHeight() != overlay.getHeight()) {
            throw new IllegalArgumentException("image size not compatible: " +
                    "base - " + base.getFileName() +
                    ", overlay - " + overlay.getFileName());
        }
    }

    private Util() {}
}
