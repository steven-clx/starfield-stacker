package writer;


/**
 * RgbaPngWriteConfig is a configuration for exporting an image with alpha channel into a png file
 *
 * @author steven-clx
 */
public class RgbaPngWriteConfig extends RgbaWriteConfig implements PngWriteConfig {


    /**
     * The default configuration for exporting an image with alpha channel into a png file which discards the alpha channel
     */
    public static final RgbaPngWriteConfig DEFAULT = new RgbaPngWriteConfig();



    public RgbaPngWriteConfig() {
        super();
    }


    public RgbaPngWriteConfig(boolean isAlphaMultiplied) {
        super(isAlphaMultiplied);
    }

}
