package writer;


/**
 * RgbaWriteConfig is a configuration for exporting an image with alpha channel
 *
 * @author steven-clx
 */
public abstract class RgbaWriteConfig implements WriteConfig {


    /**
     * Specify whether the alpha channel should be saved in the output
     */
    public final boolean isAlphaSaved;


    /**
     * If isAlphaSaved is set to false, this parameter specifies whether the alpha channel should be multiplied
     * to the r, g, b channels or discarded when the image being written contains the alpha channel
     */
    public final boolean isAlphaMultiplied;



    /**
     * This constructor saves the alpha channel in the output
     */
    protected RgbaWriteConfig() {
        isAlphaSaved = true;
        isAlphaMultiplied = false;
    }


    /**
     * This constructor does not save the alpha channel in the output
     *
     * @param isAlphaMultiplied whether the alpha channel should be multiplied or discarded in the output
     */
    protected RgbaWriteConfig(boolean isAlphaMultiplied) {
        isAlphaSaved = false;
        this.isAlphaMultiplied = isAlphaMultiplied;
    }



    /**
     * Generate the abbreviation for how the alpha channel is handled:
     * "_as" for "alpha saved", "_am" for alpha multiplied, "_ad" for "alpha discarded"
     *
     * @return the abbreviation for how the alpha channel is handled
     */
    @Override
    public String getSuffix() {
        return isAlphaSaved ? "_as" : isAlphaMultiplied ? "_am" : "_ad";
    }

}
