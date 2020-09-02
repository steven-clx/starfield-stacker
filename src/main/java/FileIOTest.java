import image.ByteImageCreator;
import image.DataType;
import image.Image;
import image.ImageReader;
import writer.RgbaPngWriteConfig;
import writer.WriteConfig;
import writer.Writer;
import writer.WriterDispatcher;

import java.io.File;

public class FileIOTest {

    public static void main2(String[] args) {
        Image image = ByteImageCreator.getInstance().createImage(300, 200, true);
        for (int i = 0; i < image.getDataLength(); i++) {
            image.setRGBA(i,
                    (int) Math.round(Math.random() * 256),
                    (int) Math.round(Math.random() * 256),
                    (int) Math.round(Math.random() * 256),
                    (int) Math.round(Math.random() * 256));
        }
        image.setFileName("random_image");
        image.setDirectory("../../Downloads/random_image_as的副本.png/test");

        WriteConfig wc = RgbaPngWriteConfig.DEFAULT;
        Writer w = WriterDispatcher.getWriter(wc);

        w.write(image, true);
    }

    public static void main(String[] args) {
        ImageReader r = ImageReader.getInstance();
        r.setDataType(DataType.INT);

        Image image = r.read(new File("/Users/steven/Downloads/../Downloads/12-3月翻译"));

        WriteConfig wc = RgbaPngWriteConfig.DEFAULT;
        Writer w = WriterDispatcher.getWriter(wc);

        w.write(image, true);
    }

}
