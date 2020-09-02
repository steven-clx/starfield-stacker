package reader;

import image.Image;
import util.FileUtil;

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

        String directory = null;
        Image image = null;

        String fileName = null;

        try {

            File canonicalFile = null;
            File canonicalParentFile = null;

            /*
             * Try to get the canonical file and the file name
             * Do not throw exception if the canonical file cannot be obtained,
             * as the path to its parent directory may still be valid and will
             * be saved as the directory of the (corrupted) image
             */
            try {
                canonicalFile = file.getCanonicalFile();
                fileName = FileUtil.getFileName(canonicalFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            /*
             * If the canonical file cannot be obtained,
             * get the file name using the original file
             */
            if (fileName == null)
                fileName = FileUtil.getFileName(file);

            /*
             * Try to get the parent file of file
             * If canonicalFile has been obtained, get the parent
             * of canonicalFile, otherwise get the parent of file
             */
            File parentFile = canonicalFile == null
                              ? file.getParentFile()
                              : canonicalFile.getParentFile();

            // Try to get the canonical file of the parent file if it exists
            if (parentFile != null) {
                try {
                    canonicalParentFile = parentFile.getCanonicalFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            /*
             * If the canonical parent file has been obtained and is valid,
             * save the canonical path of the canonical parent file as the
             * directory of the image even if the canonical file may not
             * have been obtained
             *
             * If the canonical parent file cannot be obtained or is invalid,
             * do nothing and throw exception
             */
            if (canonicalParentFile != null && canonicalParentFile.exists() && canonicalParentFile.isDirectory())
                directory = canonicalParentFile.getCanonicalPath();
            else
                throw new IOException("cannot get parent folder of file at path: " + file.getAbsolutePath());

            /*
             * If the canonical file cannot be obtained and is invalid,
             * do nothing and throw exception
             */
            if (canonicalFile == null || !canonicalFile.exists() || canonicalFile.isDirectory())
                throw new IOException("cannot read file at path: " + file.getAbsolutePath());

            BufferedImage bi = ImageIO.read(canonicalFile);

            if (bi != null)
                image = imageCreator.createImage(bi, fileName, directory);
            else
                throw new IIOException("cannot read input image at: " + file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (image != null)
            return image;


        /*
         * The image cannot created by reading the file at the specified path
         * due to various reasons (invalid path, wrong file or corrupt file),
         * therefore create a blank image representing the corrupt image file
         */
        image = imageCreator.createImage(1, 1, false);

        image.setFileName(fileName + "_corrupted");

        if (directory != null)
            image.setDirectory(directory);

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
