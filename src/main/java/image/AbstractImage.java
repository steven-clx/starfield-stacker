package image;

import java.awt.image.BufferedImage;


public abstract class AbstractImage implements Image {


    public static final int DEFAULT_TYPE_RGB = BufferedImage.TYPE_3BYTE_BGR;

    public static final int DEFAULT_TYPE_RGBA = BufferedImage.TYPE_4BYTE_ABGR;



    private static final String DEFAULT_FILE_NAME = "unnamed_blank_image";

    private static final String DEFAULT_DIRECTORY = "";



    protected int type;

    protected int width, height;
    protected int dataLength;

    protected String fileName;
    protected String directory;



    protected AbstractImage() {
        fileName = DEFAULT_FILE_NAME;
        directory = DEFAULT_DIRECTORY;
    }


    protected AbstractImage(String fileName, String directory) {
        this.fileName = fileName;
        this.directory = directory;
    }



    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void appendSuffix(String suffix) {
        if (suffix.length() > 0)
            fileName += "_" + suffix;
    }

    @Override
    public void setDirectory(String directory) {
        this.directory = directory;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public String getDirectory() {
        return directory;
    }



    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getDataLength() {
        return dataLength;
    }



    @Override
    public AbstractImage clone() {

        try {

            AbstractImage cloned = (AbstractImage) super.clone();

            cloned.appendSuffix("cloned");

            return cloned;

        } catch (CloneNotSupportedException e) {

            e.printStackTrace();

            throw new Error("clone not supported");
        }
    }

}
