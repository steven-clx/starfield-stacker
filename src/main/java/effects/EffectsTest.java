package effects;

import effects.algorithm.Blend;
import effects.algorithm.Brighten;
import reader.DataType;
import image.Image;
import reader.ImageReader;
import util.TestUtil;
import writer.RgbaPngWriteConfig;
import writer.WriteConfig;
import writer.Writer;
import writer.WriterDispatcher;

import java.io.File;

public class EffectsTest {

    public static void main(String[] args) {
        blendTimelapse();
    }


    public static void blendTimelapse() {

        ImageReader r = ImageReader.getInstance();

        int start = 7947;
        int end = 8054;

        String folder = "/Volumes/Backup Plus/2020 Perseids/all_jpg/";
        String sourceFilePref = "_DSC";

        String pathPref = folder + sourceFilePref;

        Image base = r.read(new File(pathPref + start + ".jpg"));

        WriteConfig wc = RgbaPngWriteConfig.DEFAULT;
        Writer w = WriterDispatcher.getWriter(wc);

        String dest = "/Users/steven/Downloads/out/";
        String destFilePref = "blend_" + start + "_";

        base.setFileName(destFilePref + start);
        w.write(base, dest, false);

        TestUtil.init();

        for (int i = start + 1; i <= end; i++) {
            Blend.blend(
                    base,
                    r.read(new File(pathPref + i + ".jpg")),
                    Brighten.calculateOpacity(i - start, end - start + 1, 0, 1));

            base.setFileName(destFilePref + i);

            w.write(base, dest, false);

            TestUtil.printTimeInfo();
            TestUtil.printMemoryInfo();
        }
    }


    public static void brightenAnyFormat() {

        ImageReader r = ImageReader.getInstance();

        String folder = "/Volumes/Backup Plus/2020 Perseids/all_jpg/";
        String filePref = "_DSC";

        String pathPref = folder + filePref;

        int start = 7947;
        int end = 8054;

        int num = end - start + 1;

        int steps = 1;
        int step = num / steps;

        int[] stops = new int[steps + 1];

        for (int i = 0; i < stops.length; i++)
            stops[i] = start + step * i;

        Image[] images = new Image[steps];

        DataType[] dataTypes = DataType.values();
        r.setDataType(dataTypes[0]);

        TestUtil.init();

        for (int i = 0; i < stops.length - 1; i++) {
            int s = stops[i];
            int e = stops[i + 1];
            r.setDataType(dataTypes[i % 3]);
            Image base = r.read(new File(pathPref + s + ".jpg"));
            images[i] = base;
            for (int j = s + 1; j < e; j++) {
                r.setDataType(dataTypes[(j - (s + 1)) % 3]);
                Image overlay = r.read(new File(pathPref + j + ".jpg"));
                System.out.println("BLENDING " + base.getClass() + " + " + overlay.getClass());
                Blend.blend(
                        base,
                        overlay,
                        Brighten.calculateOpacity(j - s, e - s, 0, 1));
                TestUtil.printTimeInfo();
                TestUtil.printMemoryInfo();
            }
        }

        Image base = images[0];

        for (int i = 1; i < images.length; i++) {
            Image overlay = images[i];
            System.out.println("BLENDING " + base.getClass() + " + " + overlay.getClass());
            Brighten.brighten(base, overlay, Brighten.calculateOpacity(i, images.length, 0, 1));
            TestUtil.printTimeInfo();
            TestUtil.printMemoryInfo();
        }

        base.setFileName("crazy_blend_" + start + "_" + end);

        WriteConfig wc = RgbaPngWriteConfig.DEFAULT;
        Writer w = WriterDispatcher.getWriter(wc);

        w.write(base, true);

        TestUtil.printTimeInfo();
        TestUtil.printMemoryInfo();
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
