package writer;


/**
 * PngWriteConfig is a configuration for exporting an image into a png file
 *
 * @author steven-clx
 */
public interface PngWriteConfig extends WriteConfig {


    @Override
    default ImageFormat getImageFormat() {
        return ImageFormat.PNG;
    }

}
