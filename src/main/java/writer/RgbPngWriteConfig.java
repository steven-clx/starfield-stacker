package writer;


/**
 * RgbPngWriteConfig is a configuration for exporting an image without alpha channel into a png file
 *
 * @author steven-clx
 */
public class RgbPngWriteConfig implements PngWriteConfig {


    /**
     * The default configuration for exporting an image without alpha channel into a png file
     */
    public static final RgbPngWriteConfig DEFAULT = new RgbPngWriteConfig();



    @Override
    public String getSuffix() {
        return "";
    }

}
