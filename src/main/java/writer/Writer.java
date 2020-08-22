package writer;

import image.Image;

import java.io.IOException;


/**
 * An interface for writing an Image instance into an output file using different WriteConfig configurations
 *
 * @author steven-clx
 */
public interface Writer {


    /**
     * Read a specified configuration and retain any necessary information in the corresponding implementation classes
     *
     * @param config the specified configuration
     */
    void readConfig(WriteConfig config);


    /**
     * Write the image into the output file using the retained data from the latest specified configuration.
     * If no configuration has been provided, a default configuration should be used
     *
     * @param image the image to be exported
     * @param outputDir the path to the output directory where the image should be exported
     * @param isSuffixIncluded whether the suffix for the write configuration should be appended to the output file name
     */
    void write(Image image, String outputDir, boolean isSuffixIncluded);



    /**
     * Perform an update to the BufferedImage in the image by the Pixel array before executing the write operation
     *
     * @see #write(Image, String, boolean)
     */
    default void writeUpdated(Image image, String outputDir, boolean isSuffixIncluded) {
        image.updateBufferedImageByPixels();
//        write(image, outputDir, isSuffixIncluded);
    }

}
