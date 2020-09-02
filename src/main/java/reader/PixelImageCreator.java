package reader;

import image.Image;
import image.PixelImage;

import java.awt.image.BufferedImage;

public class PixelImageCreator implements ImageCreator {

    private static PixelImageCreator instance;

    private PixelImageCreator() {}

    @Override
    public Image createImage(BufferedImage bi, String fileName, String directory) {
        return new PixelImage(bi, fileName, directory);
    }

    @Override
    public Image createImage(int width, int height, boolean hasAlpha) {

        PixelImage image = new PixelImage(width, height);

        image.setHasAlpha(hasAlpha);

        return image;
    }


    public static PixelImageCreator getInstance() {
        if (instance == null)
            instance = new PixelImageCreator();
        return instance;
    }

}
