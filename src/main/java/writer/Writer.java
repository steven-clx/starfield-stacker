package writer;

import image.Image;


/**
 * An interface for writing an image into an output file using different WriteConfig configurations
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
     * Write the image into a file at the output directory using the last specified configuration
     * If no configuration has been provided, a default configuration should be used
     *
     * @param image the image to be exported
     * @param outputDir the path of the output directory where the image should be exported
     * @param isSuffixIncluded whether the suffix for the write configuration should be appended to the output file name
     */
    void write(Image image, String outputDir, boolean isSuffixIncluded);


    /**
     * Write the image into a file at the image's original directory using the last specified configuration
     * If no configuration has been provided, a default configuration should be used
     *
     * @param image the image to be exported
     * @param isSuffixIncluded whether the suffix for the write configuration should be appended to the output file name
     */
    default void write(Image image, boolean isSuffixIncluded) {
        write(image, image.getDirectory(), isSuffixIncluded);
    }

}
