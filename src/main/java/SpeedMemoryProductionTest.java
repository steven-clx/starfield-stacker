import effects.algorithm.Blend;
import image.*;
import reader.DataType;
import reader.ImageReader;
import reader.IntImageCreator;
import util.TestUtil;
import writer.RgbaPngWriteConfig;
import writer.WriteConfig;
import writer.Writer;
import writer.WriterDispatcher;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class SpeedMemoryProductionTest {


    public static void main(String[] args) {
        productionTest();
    }


    public static void productionTest() {

        /*
            另一张不透明且叠加不透明度为1.0
            	本张为半透明
            	本张为不透明

            本张不透明，另一张半透明，叠加半透明
            本张不透明，另一张半透明，叠加1.0
            本张不透明，另一张不透明，叠加半透明


            本张半透明，另一张半透明，叠加半透明
            本张半透明，另一张半透明，叠加1.0
            本张半透明，另一张不透明，叠加半透明


            46～48 全部叠加后还是半透明

            45～49
         */

        String folder = "/Volumes/Backup Plus/2020 Perseids/blend_test/";

        WriteConfig wc = RgbaPngWriteConfig.DEFAULT;
        Writer w = WriterDispatcher.getWriter(wc);

        ImageReader r = ImageReader.getInstance();
        r.setDataType(DataType.INT);

        File f_7945_不透明 = new File(folder + "_DSC7945.png");
        File f_7946_半透明 = new File(folder + "_DSC7946.png");
        File f_7947_半透明 = new File(folder + "_DSC7947.png");
        File f_7948_半透明 = new File(folder + "_DSC7948.png");
        File f_7949_不透明 = new File(folder + "_DSC7949.png");

        Image o_7945_不透明, o_7946_半透明, o_7947_半透明, o_7948_半透明, o_7949_不透明;
        Image _7945_不透明, _7946_半透明, _7947_半透明, _7948_半透明, _7949_不透明;


        TestUtil.printTimeInfo("初始化");
        TestUtil.printMemoryInfo(0);

        o_7945_不透明 = r.read(f_7945_不透明);
        o_7946_半透明 = r.read(f_7946_半透明);
        o_7947_半透明 = r.read(f_7947_半透明);
        o_7948_半透明 = r.read(f_7948_半透明);
        o_7949_不透明 = r.read(f_7949_不透明);

//        _7945_不透明 = r.read(f_7945_不透明);
//        _7946_半透明 = r.read(f_7946_半透明);
//        _7947_半透明 = r.read(f_7947_半透明);
//        _7948_半透明 = r.read(f_7948_半透明);
//        _7949_不透明 = r.read(f_7949_不透明);


        TestUtil.printTimeInfo("读取图片");
        TestUtil.printMemoryInfo(5);


        _7945_不透明 = o_7945_不透明.clone();
        _7946_半透明 = o_7946_半透明.clone();
        _7947_半透明 = o_7947_半透明.clone();
        _7948_半透明 = o_7948_半透明.clone();
        _7949_不透明 = o_7949_不透明.clone();


        TestUtil.printTimeInfo("克隆图片");
        TestUtil.printMemoryInfo(10);


//        increaseBrightness(_7945_不透明);
//        increaseBrightness(_7946_半透明);
//        increaseBrightness(_7947_半透明);
//        increaseBrightness(_7948_半透明);
//        increaseBrightness(_7949_不透明);
//
//        w.write(_7945_不透明, folder, true);
//        w.write(_7946_半透明, folder, true);
//        w.write(_7947_半透明, folder, true);
//        w.write(_7948_半透明, folder, true);
//        w.write(_7949_不透明, folder, true);
//
//        w.write(o_7945_不透明, folder + "original", true);
//        w.write(o_7946_半透明, folder + "original", true);
//        w.write(o_7947_半透明, folder + "original", true);
//        w.write(o_7948_半透明, folder + "original", true);
//        w.write(o_7949_不透明, folder + "original", true);
//
//
//        util.TestUtil.printTimeInfo("写入图片");
//        util.TestUtil.printMemoryInfo(10);


        Blend.blend(_7947_半透明, _7945_不透明, 1.0f);
        _7947_半透明.setFileName("7947_半透明 + 7945_不透明 * 1.0");
        w.write(_7947_半透明, folder, true);

//        _7947_半透明 = r.read(f_7947_半透明);
        _7947_半透明 = o_7947_半透明.clone();


        TestUtil.printTimeInfo("堆栈一张");
        TestUtil.printMemoryInfo(10);


        Blend.blend(_7949_不透明, _7945_不透明, 1.0f);
        _7949_不透明.setFileName("7949_不透明 + 7945_不透明 * 1.0");
        w.write(_7949_不透明, folder, true);

//        _7949_不透明 = r.read(f_7949_不透明);
        _7949_不透明 = o_7949_不透明.clone();


        TestUtil.printTimeInfo("堆栈一张");
        TestUtil.printMemoryInfo(10);


        Blend.blend(_7945_不透明, _7946_半透明, 0.72f);
        _7945_不透明.setFileName("7945_不透明 + 7946_半透明 * 0.72");
        w.write(_7945_不透明, folder, true);

//        _7945_不透明 = r.read(f_7945_不透明);
        _7945_不透明 = _7945_不透明.clone();


        TestUtil.printTimeInfo("堆栈一张");
        TestUtil.printMemoryInfo(10);


        Blend.blend(_7945_不透明, _7946_半透明, 1.0f);
        _7945_不透明.setFileName("7945_不透明 + 7946_半透明 * 1.0");
        w.write(_7945_不透明, folder, true);

//        _7945_不透明 = r.read(f_7945_不透明);
        _7945_不透明 = o_7945_不透明.clone();


        TestUtil.printTimeInfo("堆栈一张");
        TestUtil.printMemoryInfo(10);


        Blend.blend(_7945_不透明, _7949_不透明, 0.58f);
        _7945_不透明.setFileName("7945_不透明 + 7949_不透明 * 0.58");
        w.write(_7945_不透明, folder, true);

//        _7945_不透明 = r.read(f_7945_不透明);
        _7945_不透明 = o_7945_不透明.clone();


        TestUtil.printTimeInfo("堆栈一张");
        TestUtil.printMemoryInfo(10);


        Blend.blend(_7947_半透明, _7948_半透明, 0.83f);
        _7947_半透明.setFileName("7947_半透明 + 7948_半透明 * 0.83");
        w.write(_7947_半透明, folder, true);

//        _7947_半透明 = r.read(f_7947_半透明);
        _7947_半透明 = o_7947_半透明.clone();


        TestUtil.printTimeInfo("堆栈一张");
        TestUtil.printMemoryInfo(10);


        Blend.blend(_7947_半透明, _7948_半透明, 1.0f);
        _7947_半透明.setFileName("7947_半透明 + 7948_半透明 * 1.0");
        w.write(_7947_半透明, folder, true);

//        _7947_半透明 = r.read(f_7947_半透明);
        _7947_半透明 = o_7947_半透明.clone();


        TestUtil.printTimeInfo("堆栈一张");
        TestUtil.printMemoryInfo(10);


        Blend.blend(_7947_半透明, _7949_不透明, 0.37f);
        _7947_半透明.setFileName("7947_半透明 + 7949_不透明 * 0.37");
        w.write(_7947_半透明, folder, true);

//        _7947_半透明 = r.read(f_7947_半透明);
        _7947_半透明 = o_7947_半透明.clone();


        TestUtil.printTimeInfo("堆栈一张");
        TestUtil.printMemoryInfo(10);


        Blend.blend(_7946_半透明, _7947_半透明, 0.7f);
        Blend.blend(_7946_半透明, _7948_半透明, 0.4f);
        _7946_半透明.setFileName("7946_半透明 + 7947_半透明 * 0.7 + 7948_半透明 * 0.4");
        w.write(_7946_半透明, folder, true);

//        _7946_半透明 = r.read(f_7946_半透明);
        _7946_半透明 = o_7946_半透明.clone();


        TestUtil.printTimeInfo("堆栈两张");
        TestUtil.printMemoryInfo(10);


        Blend.blend(_7945_不透明, _7946_半透明, 0.8f);
        Blend.blend(_7945_不透明, _7947_半透明, 0.6f);
        Blend.blend(_7945_不透明, _7948_半透明, 0.4f);
        Blend.blend(_7945_不透明, _7949_不透明, 0.2f);
        _7945_不透明.setFileName("7945_不透明 + 7946_半透明 * 0.8 + 7947_半透明 * 0.6 + 7948_半透明 * 0.4 + 7949_不透明 * 0.2");
        w.write(_7945_不透明, folder, true);
//        w.write(_7945_不透明, folder, true);


        TestUtil.printTimeInfo("堆栈三张");
        TestUtil.printMemoryInfo(10);
    }


    private static void increaseBrightness(Image image) {
        for (int i = 0; i < image.getDataLength(); i++) {
            image.setRGB(i,
                    Math.min(255, image.getR(i) + 20),
                    Math.min(255, image.getG(i) + 20),
                    Math.min(255, image.getB(i) + 20));
        }
    }



    public static void testClone() {
        WriteConfig wc = new RgbaPngWriteConfig();
        Writer w = WriterDispatcher.getWriter(wc);

        ImageReader r = ImageReader.getInstance();
        r.setDataType(DataType.INT);

        Image image = r.read(new File("/Users/steven/Downloads/zoom-background-illustration-blue-translucent.png"));
        Image cloned = image.clone();

        System.out.println(cloned.getClass());

        w.write(image, "/Users/steven/Downloads/", true);
        w.write(cloned, "/Users/steven/Downloads/", true);
    }


    public static void testReadWriteCreate() {
        WriteConfig wc = new RgbaPngWriteConfig(true);
        Writer w = WriterDispatcher.getWriter(wc);

        ImageReader r = ImageReader.getInstance();
        r.setDataType(DataType.INT);

        w.write(IntImageCreator.getInstance().createImage(10, 10, true), "/Users/steven/Downloads/", true);
//        w.write(r.read(new File("/Volumes/Backup Plus/2020 Perseids/blend_test/_DSC7947.png")), "/Users/steven/Downloads/", true);
    }


    public static void testSpeed() {

        List<Image> images = new ArrayList<>();
        File root = new File("/Volumes/Backup Plus/2020 Perseids/2020/2020-08-13/jpg/all/");

        Arrays.stream(root.listFiles()).filter(f -> f.isFile() && f.getName().endsWith(".jpg") && f.getName().startsWith("_DSC") && f.getName().length() == 12).limit(50).forEach(create());

        TestUtil.printTimeInfo("创建耗时");
        TestUtil.printMemoryInfo(50);

        images.forEach(read());

        TestUtil.printTimeInfo("读取耗时");
        TestUtil.printMemoryInfo(50);

        images.forEach(write());

        TestUtil.printTimeInfo("写入耗时");
        TestUtil.printMemoryInfo(50);

        images.forEach(save());

        TestUtil.printTimeInfo("输出耗时");
        TestUtil.printMemoryInfo(50);

    }



    public static Consumer<File> create() {
        ImageReader reader = ImageReader.getInstance();
        List<Image> images = new ArrayList<>();
        return f -> {
            images.add(reader.read(f));
//            System.out.println((- time + (time = System.currentTimeMillis())) / 1000d);
//            printMemoryInfo();
        };
    }


    public static Consumer<Image> save() {
        File f = new File("/Users/steven/Downloads/test.jpg");
        return image -> {
            WriterDispatcher.getWriter().write(image, "/Users/steven/Downloads/", true);
//            System.out.println((- time + (time = System.currentTimeMillis())) / 1000d);
//            printMemoryInfo();
        };
    }


    public static Consumer<Image> write() {
        return image -> {
//            image.write();
//            System.out.println((- time + (time = System.currentTimeMillis())) / 1000d);
//            printMemoryInfo();
        };
    }


    public static Consumer<Image> read() {
        return image -> {
//            image.read();
//            System.out.println((- time + (time = System.currentTimeMillis())) / 1000d);
//            printMemoryInfo();
        };
    }

}
