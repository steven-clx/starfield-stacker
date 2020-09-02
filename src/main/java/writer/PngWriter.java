package writer;

import image.Image;
import util.FileUtil;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * A ImageWriter delegate implementing the Writer interface for writing png files
 *
 * @author steven-clx
 */
public class PngWriter implements Writer {


    /**
     * The singleton for the PngWriter class
     */
    private static PngWriter instance;


    /**
     * An ImageWriter instance that performs the actual write operation
     */
    private ImageWriter imageWriter;


    /**
     * A PngWriteConfig instance that specifies how the image should be exported
     */
    private PngWriteConfig config;



    /**
     * Create a default PngWriter instance with the following tasks:
     *     - get the ImageWriter
     *     - store any necessary information from the default configuration
     */
    private PngWriter() {
        imageWriter = ImageIO.getImageWritersByFormatName("png").next();
        initFromDefaultConfig();
    }



    @Override
    public void readConfig(WriteConfig config) {
        if (config instanceof PngWriteConfig)
            readConfigInternal((PngWriteConfig) config);
    }


    /**
     * If the input image does not contain the alpha channel, or it does and the write configuration
     * saves the alpha channel, the input image will render a default BufferedImage for the export;
     * otherwise, the alpha channel should be either multiplied to the r, g, b channels or discarded,
     * according to the configuration
     *
     * If the input image contains the alpha channel, but the supplied write configuration is not
     * intended for image with alpha channel, the default instance of RgbaPngWriteConfig will be used
     */
    @Override
    public void write(Image image, String outputDir, boolean isSuffixIncluded) {

        BufferedImage exportedBufferedImage;
        PngWriteConfig appliedConfig;

        if (!image.hasAlpha()) {

            exportedBufferedImage = image.render();
            appliedConfig = config instanceof RgbPngWriteConfig ? config : RgbPngWriteConfig.DEFAULT;

        } else if (image.hasAlpha() &&
                config instanceof RgbaWriteConfig &&
                ((RgbaWriteConfig) config).isAlphaSaved) {

            exportedBufferedImage = image.render();
            appliedConfig = config;

        } else {

            appliedConfig = config instanceof RgbaWriteConfig ? config : RgbaPngWriteConfig.DEFAULT;

            if (((RgbaWriteConfig) appliedConfig).isAlphaMultiplied)
                exportedBufferedImage = image.renderAlphaMultiplied();
            else
                exportedBufferedImage = image.renderAlphaDiscarded();
        }

        try {

            String canonicalDir = FileUtil.getCanonicalDir(outputDir);

            FileImageOutputStream stream = new FileImageOutputStream(
                    new File(canonicalDir +
                            (canonicalDir.length() == 0 || canonicalDir.endsWith(File.separator) ? "" : File.separator) +
                            image.getFileName() +
                            (isSuffixIncluded ? appliedConfig.getSuffix() : "") +
                            ".png"));

            imageWriter.setOutput(stream);
            imageWriter.write(new IIOImage(exportedBufferedImage, null, null));

            stream.close();

            imageWriter.setOutput(null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Initialize the imageWriter with the default configuration for png files
     */
    private void initFromDefaultConfig() {
        readConfigInternal(RgbPngWriteConfig.DEFAULT);
    }


    /**
     * Specify what information from the specified configuration is stored, and how
     *
     * @param config the specified configuration
     */
    private void readConfigInternal(PngWriteConfig config) {
        this.config = config;
    }



    /**
     * Get the singleton PngWriter, and create a default instance if it is null (lazy initialization)
     *
     * @return the singleton PngWriter
     */
    static PngWriter getInstance() {
        if (instance == null)
            instance = new PngWriter();
        return instance;
    }


    /**
     * Reset the singleton PngWriter to its default configuration, or create a default instance if it is null, and return it
     *
     * @return the default instance
     */
    static PngWriter resetAndGet() {

        if (instance == null)
            instance = new PngWriter();

        else
            instance.initFromDefaultConfig();

        return instance;
    }

}
