package reader;

import image.PixelImage;

import java.awt.image.BufferedImage;

public class PixelImageCreator implements ImageCreator {

    private static PixelImageCreator instance;

    private PixelImageCreator() {}

    @Override
    public PixelImage createImage(BufferedImage bi, String fileName, String directory) {
        return new PixelImage(bi, fileName, directory);
    }

    @Override
    public PixelImage createImage(int width, int height, boolean hasAlpha) {
        return new PixelImage(width, height, hasAlpha);
    }


    public static PixelImageCreator getInstance() {
        if (instance == null)
            instance = new PixelImageCreator();
        return instance;
    }

}
