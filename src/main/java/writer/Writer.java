package writer;

import image.Image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


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


    /**
     * Check if the parent directory of the destination file exists, and return the canonical path to that directory
     * If it doesn't, create one or throw IOException if it cannot be created
     *
     * @param outputDir the parent directory of the destination file where the image will be written to
     * @return the canonical path to the parent directory of the destination file
     * @throws IOException thrown if the output directory is invalid,
     *                     or if the parent directory does not exist and cannot be created
     */
    default String getCanonicalDir(String outputDir) throws IOException {

        File canonicalDir = new File(outputDir).getCanonicalFile();
        String canonicalPath = canonicalDir.getCanonicalPath();

        if (canonicalDir.exists()) {
            if (canonicalDir.isFile())
                throw new IOException("cannot write image into directory: " + canonicalPath);
        } else {
            if (!canonicalDir.mkdirs())
                throw new IOException("cannot create folder at: " + canonicalPath);
        }

        return canonicalPath;
    }

}
