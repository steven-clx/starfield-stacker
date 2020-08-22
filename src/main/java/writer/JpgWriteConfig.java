package writer;


/**
 * JpgWriteConfig is a configuration for exporting an image into a jpg/jpeg file
 *
 * @author steven-clx
 */
public class JpgWriteConfig implements WriteConfig {


    /**
     * The default configuration for exporting an image into a jpg/jpeg file
     */
    public static final JpgWriteConfig DEFAULT = new JpgWriteConfig(1.0f);



    /**
     * The quality specification (0.00-1.00) for the output
     */
    public final float quality;



    public JpgWriteConfig(float quality) {
        this.quality = quality;
    }



    @Override
    public ImageFormat getImageFormat() {
        return ImageFormat.JPG;
    }


    @Override
    public String getSuffix() {
        return "_" + quality;
    }

}
