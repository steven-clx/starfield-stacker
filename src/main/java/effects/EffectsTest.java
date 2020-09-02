package effects;

import effects.algorithm.Brighten;
import image.DataType;
import image.Image;
import image.ImageReader;
import util.TestUtil;
import writer.RgbaPngWriteConfig;
import writer.WriteConfig;
import writer.Writer;
import writer.WriterDispatcher;

import java.io.File;

public class EffectsTest {

    public static void main(String[] args) {
        brighten();
    }

    public static void brighten() {

        TestUtil.init();

        ImageReader r = ImageReader.getInstance();
        r.setDataType(DataType.BYTE);

        int start = 7947;
        int end = 8054;

        String folder = "/Volumes/Backup Plus/2020 Perseids/all_jpg/";
        String filePref = "_DSC";

        String pathPref = folder + filePref;

        Image base = r.read(new File(pathPref + start + ".jpg"));

        for (int i = start + 1; i <= end; i++) {
            Brighten.brighten(
                    base,
                    r.read(new File(pathPref + i + ".jpg")),
                    Brighten.calculateOpacity(i - start, end - start + 1, 0.7f, 0.16f));
        }

        base.setFileName("brighten_blend_" + start + "_" + end);

        WriteConfig wc = RgbaPngWriteConfig.DEFAULT;
        Writer w = WriterDispatcher.getWriter(wc);

        w.write(base, true);

        TestUtil.printTimeInfo();
        TestUtil.printMemoryInfo();
    }

}
