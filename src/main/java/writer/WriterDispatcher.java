package writer;


/**
 * The WriterDispatcher creates, manages and provides Writer instances, and dispatches
 * the corresponding writer for a specified write configuration
 *
 * @author steven-clx
 */
public class WriterDispatcher {


    /**
     * Keep a reference to the current writer being used
     */
    private static Writer currentWriter;



    /**
     * Get the latest writer used. If no writer has been used, get a default one
     *
     * @return the latest writer used, or a default one if no writer has been used
     */
    public static Writer getWriter() {
        return getCurrentOrDefault();
    }


    /**
     * Dispatch the corresponding writer for a specified write configuration. If null
     * is passed in, or the ImageFormat of the configuration is null or not supported
     * by this software, dispatch the current writer or a default one when no writer
     * has been used
     *
     * @param config the specified configuration
     * @return the corresponding writer for the specified configuration, or
     *         the current writer or a default one when no writer has been used if
     *             - the configuration is null, or
     *             - the ImageFormat of the configuration is null, or
     *             - the ImageFormat of the configuration is not supported by this software
     */
    public static Writer getWriter(WriteConfig config) {

        if (config == null || config.getImageFormat() == null)
            return getCurrentOrDefault();

        Writer writer;

        switch (config.getImageFormat()) {
            case JPG:
                writer = JpgWriter.getInstance();
                break;
            case PNG:
                writer = PngWriter.getInstance();
                break;
            default:
                writer = null;
        }

        if (writer == null) {
            return getCurrentOrDefault();
        } else {
            currentWriter = writer;
            currentWriter.readConfig(config);
            return currentWriter;
        }

    }


    /**
     * Get the current writer being used. If no writer has been used, get the
     * JpgWriter instance (requires instantiating a JpgWriter object)
     *
     * @return the current writer being used, or a new JpgWriter instance if no
     *         writer has been used
     */
    private static Writer getCurrentOrDefault() {
        if (currentWriter == null)
            currentWriter = JpgWriter.getInstance();
        return currentWriter;
    }


    /**
     * Get the default JpgWriter. The JpgWriter will be set to its default configuration
     *
     * @return the default JpgWriter
     */
    public static JpgWriter getDefaultJpgWriter() {
        currentWriter = JpgWriter.resetAndGet();
        return (JpgWriter) currentWriter;
    }


    /**
     * Get the default PngWriter. The PngWriter will be set to its default configuration
     *
     * @return the default PngWriter
     */
    public static PngWriter getDefaultPngWriter() {
        currentWriter = PngWriter.resetAndGet();
        return (PngWriter) currentWriter;
    }



    private WriterDispatcher() {}

}
