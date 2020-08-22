package writer;


/**
 * An interface for the specification of the write configuration for images
 *
 * @author steven-clx
 */
public interface WriteConfig {


    /**
     * Return the ImageFormat for the output
     *
     * @return the ImageFormat for the output
     */
    ImageFormat getImageFormat();


    /**
     * Generate the suffix that can be used to identify this write configuration
     *
     * @return the suffix that can be used to identify this write configuration
     */
    String getSuffix();

}
