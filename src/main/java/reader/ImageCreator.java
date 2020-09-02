package reader;

import image.Image;

import java.awt.image.BufferedImage;

public interface ImageCreator {

    Image createImage(BufferedImage bi, String fileName, String directory);
    Image createImage(int width, int height, boolean hasAlpha);

}
