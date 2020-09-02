package reader;

import image.ByteRgbImage;
import image.ByteRgbaImage;
import image.Image;

import java.awt.image.BufferedImage;

public class ByteImageCreator implements ImageCreator {

    private static ByteImageCreator instance;

    private ByteImageCreator() {}

    @Override
    public Image createImage(BufferedImage bi, String fileName, String directory) {
        if (bi.getColorModel().hasAlpha())
            return new ByteRgbaImage(bi, fileName, directory);
        else
            return new ByteRgbImage(bi, fileName, directory);
    }

    @Override
    public Image createImage(int width, int height, boolean hasAlpha) {
        if (hasAlpha)
            return new ByteRgbaImage(width, height);
        else
            return new ByteRgbImage(width, height);
    }


    public static ByteImageCreator getInstance() {
        if (instance == null)
            instance = new ByteImageCreator();
        return instance;
    }

}
