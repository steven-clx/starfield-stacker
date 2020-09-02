package writer;

import image.Image;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;


/**
 * A ImageWriter delegate implementing the Writer interface for writing jpg/jpeg files
 *
 * @author steven-clx
 */
public class JpgWriter implements Writer {


    /**
     * The singleton for the JpgWriter class
     */
    private static JpgWriter instance;


    /**
     * An ImageWriter instance that performs the actual write operation
     */
    private ImageWriter imageWriter;


    /**
     * A JPEGImageWriteParam instance that stores the compression quality as specified by the configuration
     */
    private JPEGImageWriteParam param;


    /**
     * A JpgWriteConfig instance that specifies how the image should be exported
     */
    private JpgWriteConfig config;



    /**
     * Create a default JpgWriter instance with the following tasks:
     *     - get the ImageWriter
     *     - create a JPEGImageWriteParam instance
     *     - store any necessary information from the default configuration
     */
    private JpgWriter() {
        imageWriter = ImageIO.getImageWritersByFormatName("jpg").next();
        param = new JPEGImageWriteParam(null);
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        initFromDefaultConfig();
    }



    @Override
    public void readConfig(WriteConfig config) {
        if (config instanceof JpgWriteConfig)
            readConfigInternal((JpgWriteConfig) config);
    }


    @Override
    public void write(Image image, String outputDir, boolean isSuffixIncluded) {

        try {

            String canonicalDir = getCanonicalDir(outputDir);

            FileImageOutputStream stream = new FileImageOutputStream(
                    new File(canonicalDir +
                            (canonicalDir.endsWith(File.separator) ? "" : File.separator) +
                            image.getFileName() +
                            (isSuffixIncluded ? config.getSuffix() : "") +
                            ".jpg"));

            imageWriter.setOutput(stream);
            imageWriter.write(null, new IIOImage(image.renderAlphaMultiplied(), null, null), param);

            stream.close();

            imageWriter.setOutput(null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Initialize the imageWriter with the default configuration for jpg/jpeg files
     */
    private void initFromDefaultConfig() {
        readConfigInternal(JpgWriteConfig.DEFAULT);
    }


    /**
     * Specify what information from the specified configuration is stored, and how
     *
     * @param config the specified configuration
     */
    private void readConfigInternal(JpgWriteConfig config) {
        this.config = config;
        param.setCompressionQuality(config.quality);
    }



    /**
     * Get the singleton JpgWriter, and create a default instance if it is null (lazy initialization)
     *
     * @return the singleton JpgWriter
     */
    static JpgWriter getInstance() {
        if (instance == null)
            instance = new JpgWriter();
        return instance;
    }


    /**
     * Reset the singleton JpgWriter to its default configuration, or create a default instance if it is null, and return it
     *
     * @return the default instance
     */
    static JpgWriter resetAndGet() {

        if (instance == null)
            instance = new JpgWriter();

        else
            instance.initFromDefaultConfig();

        return instance;
    }

}
