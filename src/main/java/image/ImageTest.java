package image;

import effects.algorithm.Blend;
import reader.DataType;
import reader.ImageReader;
import writer.RgbaPngWriteConfig;
import writer.WriteConfig;
import writer.Writer;
import writer.WriterDispatcher;

import java.io.File;

public class ImageTest {

    public static void main(String[] args) {
        testWritingTranslucentToJPG();
    }

    public static void testWritingTranslucentToJPG() {

        ImageReader r = ImageReader.getInstance();
        r.setDataType(DataType.PIXEL);

        Image translucent = r.read(new File("/Volumes/Backup Plus/2020 Perseids/blend_test/_DSC7947.png"));
        translucent.appendSuffix("test");

        Writer w = WriterDispatcher.getDefaultJpgWriter();

        w.write(translucent, true);
    }

    public static void testCoveringTranslucentByOpaque() {

        ImageReader r = ImageReader.getInstance();
        r.setDataType(DataType.PIXEL);

        Image translucent = r.read(new File("/Volumes/Backup Plus/2020 Perseids/blend_test/_DSC7947.png"));
        Image opaque = r.read(new File("/Volumes/Backup Plus/2020 Perseids/blend_test/_DSC7949.png"));

        Blend.blend(translucent, opaque, 1);
        translucent.setFileName("opaque");

        boolean all255 = true;

        for (int i = 0; i < translucent.getDataLength(); i++) {
            if (translucent.getA(i) != 255) {
                all255 = false;
                break;
            }
        }

        System.out.println(translucent.hasAlpha());
        System.out.println(all255);

        WriteConfig wc = RgbaPngWriteConfig.DEFAULT;
        Writer w = WriterDispatcher.getWriter(wc);

        w.write(translucent, true);
    }

}
