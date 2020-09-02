package image;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageReader {


    private static ImageReader instance;

    private ImageCreator imageCreator;

    private ImageReader() {
        setDataType(DataType.INT);
    }


    public Image read(File file) {

        String fullFileName = file.getName();
        int extIndex = fullFileName.lastIndexOf('.');
        String fileName = extIndex == -1 ? fullFileName : fullFileName.substring(0, extIndex);

        File parentFile = file.getParentFile();
        String directory = null;

        Image image = null;

        try {

            if (parentFile != null && parentFile.isDirectory())
                directory = parentFile.getCanonicalPath();
            else
                throw new IOException("cannot get parent folder of file at path: " + file.getAbsolutePath());

            if (file.isDirectory())
                throw new IOException("cannot read file at path: " + file.getAbsolutePath());

            BufferedImage bi = ImageIO.read(file);

            if (bi != null)
                image = imageCreator.createImage(bi, fileName, directory);
            else
                throw new IIOException("cannot read input image at: " + file.getAbsolutePath());

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (image == null) {

                image = imageCreator.createImage(1, 1, false);

                image.setFileName(fileName + "_corrupted");

                if (directory != null)
                    image.setDirectory(directory);
            }
        }

        return image;
    }


    public void setDataType(DataType dataType) {
        switch (dataType) {
            case INT:
                imageCreator = IntImageCreator.getInstance();
                break;
            case BYTE:
                imageCreator = ByteImageCreator.getInstance();
                break;
            case PIXEL:
                imageCreator = PixelImageCreator.getInstance();
                break;
        }
    }


    public static ImageReader getInstance() {
        if (instance == null)
            instance = new ImageReader();
        return instance;
    }


}
