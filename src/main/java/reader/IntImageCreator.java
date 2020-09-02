package reader;

import image.Image;
import image.IntRgbImage;
import image.IntRgbaImage;

import java.awt.image.BufferedImage;

public class IntImageCreator implements ImageCreator {

    private static IntImageCreator instance;

    private IntImageCreator() {}

    @Override
    public Image createImage(BufferedImage bi, String fileName, String directory) {
        if (bi.getColorModel().hasAlpha())
            return new IntRgbaImage(bi, fileName, directory);
        else
            return new IntRgbImage(bi, fileName, directory);
    }

    @Override
    public Image createImage(int width, int height, boolean hasAlpha) {
        if (hasAlpha)
            return new IntRgbaImage(width, height);
        else
            return new IntRgbImage(width, height);
    }


    public static IntImageCreator getInstance() {
        if (instance == null)
            instance = new IntImageCreator();
        return instance;
    }

}
