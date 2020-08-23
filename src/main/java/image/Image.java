package image;

import util.MathUtil;
import writer.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;


public class Image implements Cloneable {


    private BufferedImage bufferedImage;
    private RgbPixel[] pixels;
    private int width;
    private int height;
    private boolean hasAlpha;
    private String fileName;

    private static final int DEFAULT_RGB_TYPE = BufferedImage.TYPE_3BYTE_BGR;


    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        productionTest();
        System.out.println(System.currentTimeMillis());
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

        String folder = "/Volumes/Backup Plus/2020 Perseids/2020/2020-08-13/jpg/all/blend_test/";

        WriteConfig wc = RgbaPngWriteConfig.DEFAULT;
        Writer w = WriterDispatcher.getWriter(wc);

//        Resource _DSC7945_不透明 = new Resource(new Image(new File(folder + "_DSC7945.png")));
//        Resource _DSC7946_半透明 = new Resource(new Image(new File(folder + "_DSC7946.png")));
//        Resource _DSC7947_半透明 = new Resource(new Image(new File(folder + "_DSC7947.png")));
//        Resource _DSC7948_半透明 = new Resource(new Image(new File(folder + "_DSC7948.png")));
//        Resource _DSC7949_不透明 = new Resource(new Image(new File(folder + "_DSC7949.png")));

//        File _DSC7945_不透明 = new File(folder + "_DSC7945.png");
//        File _DSC7946_半透明 = new File(folder + "_DSC7946.png");
//        File _DSC7947_半透明 = new File(folder + "_DSC7947.png");
//        File _DSC7948_半透明 = new File(folder + "_DSC7948.png");
//        File _DSC7949_不透明 = new File(folder + "_DSC7949.png");

        Image o_7945_不透明, o_7946_半透明, o_7947_半透明, o_7948_半透明, o_7949_不透明;
        Image _7945_不透明, _7946_半透明, _7947_半透明, _7948_半透明, _7949_不透明;


//        _7945_不透明 = _DSC7945_不透明.getImage();
//        _7946_半透明 = _DSC7946_半透明.getImage();
//        _7947_半透明 = _DSC7947_半透明.getImage();
//        _7948_半透明 = _DSC7948_半透明.getImage();
//        _7949_不透明 = _DSC7949_不透明.getImage();

        System.out.println(System.currentTimeMillis());

        o_7945_不透明 = new Image(new File(folder + "_DSC7945.png"));
        o_7946_半透明 = new Image(new File(folder + "_DSC7946.png"));
        o_7947_半透明 = new Image(new File(folder + "_DSC7947.png"));
        o_7948_半透明 = new Image(new File(folder + "_DSC7948.png"));
        o_7949_不透明 = new Image(new File(folder + "_DSC7949.png"));

        System.out.println(System.currentTimeMillis());


        _7945_不透明 = (Image) o_7945_不透明.clone();
        _7946_半透明 = (Image) o_7946_半透明.clone();
        _7947_半透明 = (Image) o_7947_半透明.clone();
        _7948_半透明 = (Image) o_7948_半透明.clone();
        _7949_不透明 = (Image) o_7949_不透明.clone();

        System.out.println(System.currentTimeMillis());

//        _7945_不透明 = new Image(_DSC7945_不透明);
//        _7946_半透明 = new Image(_DSC7946_半透明);
//        _7947_半透明 = new Image(_DSC7947_半透明);
//        _7948_半透明 = new Image(_DSC7948_半透明);
//        _7949_不透明 = new Image(_DSC7949_不透明);


        _7947_半透明.blend(_7945_不透明, 1.0);
        _7947_半透明.setFileName("7947_半透明 + 7945_不透明 * 1.0");
        w.writeUpdated(_7947_半透明, folder, true);
//        Image _7947_半透明2 = _DSC7947_半透明.getImage();
//        Image _7947_半透明2 = (Image) o_7947_半透明.clone();
//        _7947_半透明 = new Image(_DSC7947_半透明);
        _7947_半透明 = (Image) o_7947_半透明.clone();

        System.out.println(System.currentTimeMillis());


        _7949_不透明.blend(_7945_不透明, 1.0);
        _7949_不透明.setFileName("7949_不透明 + 7945_不透明 * 1.0");
        w.writeUpdated(_7949_不透明, folder, true);
//        Image _7949_不透明2 = _DSC7949_不透明.getImage();
//        Image _7949_不透明2 = (Image) o_7949_不透明.clone();
//        _7949_不透明 = new Image(_DSC7949_不透明);
        _7949_不透明 = (Image) o_7949_不透明.clone();

        System.out.println(System.currentTimeMillis());


        _7945_不透明.blend(_7946_半透明, 0.72);
        _7945_不透明.setFileName("7945_不透明 + 7946_半透明 * 0.72");
        w.writeUpdated(_7945_不透明, folder, true);
//        Image _7945_不透明2 = _DSC7945_不透明.getImage();
//        Image _7945_不透明2 = (Image) o_7945_不透明.clone();
        _7945_不透明 = (Image) o_7945_不透明.clone();
//        _7945_不透明 = new Image(_DSC7945_不透明);
//        _7945_不透明 = new Image(new File(folder + "_DSC7945.png"));

        System.out.println(System.currentTimeMillis());



        _7945_不透明.blend(_7946_半透明, 1.0);
        _7945_不透明.setFileName("7945_不透明 + 7946_半透明 * 1.0");
        w.writeUpdated(_7945_不透明, folder, true);
//        Image _7945_不透明3 = _DSC7945_不透明.getImage();
//        Image _7945_不透明3 = (Image) o_7945_不透明.clone();
        _7945_不透明 = (Image) o_7945_不透明.clone();
//        _7945_不透明 = new Image(_DSC7945_不透明);
//        _7945_不透明 = new Image(new File(folder + "_DSC7945.png"));

        System.out.println(System.currentTimeMillis());


        _7945_不透明.blend(_7949_不透明, 0.58);
        _7945_不透明.setFileName("7945_不透明 + 7949_不透明 * 0.58");
        w.writeUpdated(_7945_不透明, folder, true);
//        Image _7945_不透明4 = _DSC7945_不透明.getImage();
//        Image _7945_不透明4 = (Image) o_7945_不透明.clone();
        _7945_不透明 = (Image) o_7945_不透明.clone();
//        _7945_不透明 = new Image(_DSC7945_不透明);
//        _7945_不透明 = new Image(new File(folder + "_DSC7945.png"));

        System.out.println(System.currentTimeMillis());


        _7947_半透明.blend(_7948_半透明, 0.83);
        _7947_半透明.setFileName("7947_半透明 + 7948_半透明 * 0.83");
        w.writeUpdated(_7947_半透明, folder, true);
//        Image _7947_半透明3 = _DSC7947_半透明.getImage();
//        Image _7947_半透明3 = (Image) o_7947_半透明.clone();
        _7947_半透明 = (Image) o_7947_半透明.clone();
//        _7947_半透明 = new Image(_DSC7947_半透明);
//        _7947_半透明 = new Image(new File(folder + "_DSC7947.png"));

        System.out.println(System.currentTimeMillis());


        _7947_半透明.blend(_7948_半透明, 1.0);
        _7947_半透明.setFileName("7947_半透明 + 7948_半透明 * 1.0");
        w.writeUpdated(_7947_半透明, folder, true);
//        Image _7947_半透明4 = _DSC7947_半透明.getImage();
//        Image _7947_半透明4 = (Image) o_7947_半透明.clone();
        _7947_半透明 = (Image) o_7947_半透明.clone();
//        _7947_半透明 = new Image(_DSC7947_半透明);
//        _7947_半透明 = new Image(new File(folder + "_DSC7947.png"));

        System.out.println(System.currentTimeMillis());


        _7947_半透明.blend(_7949_不透明, 0.37);
        _7947_半透明.setFileName("7947_半透明 + 7949_不透明 * 0.37");
        w.writeUpdated(_7947_半透明, folder, true);
//        Image _7947_半透明5 = _DSC7947_半透明.getImage();
//        Image _7947_半透明5 = (Image) o_7947_半透明.clone();
        _7947_半透明 = (Image) o_7947_半透明.clone();
//        _7947_半透明 = new Image(_DSC7947_半透明);
//        _7947_半透明 = new Image(new File(folder + "_DSC7947.png"));

        System.out.println(System.currentTimeMillis());


        _7946_半透明.blend(_7947_半透明, 0.7);
        _7946_半透明.blend(_7948_半透明, 0.4);
        _7946_半透明.setFileName("7946_半透明 + 7947_半透明 * 0.7 + 7948_半透明 * 0.4");
        w.writeUpdated(_7946_半透明, folder, true);
//        Image _7946_半透明2 = _DSC7946_半透明.getImage();
//        Image _7946_半透明2 = (Image) o_7946_半透明.clone();
        _7946_半透明 = (Image) o_7946_半透明.clone();
//        _7946_半透明 = new Image(_DSC7946_半透明);
//        _7946_半透明 = new Image(new File(folder + "_DSC7946.png"));

        System.out.println(System.currentTimeMillis());


        _7945_不透明.blend(_7946_半透明, 0.8);
        _7945_不透明.blend(_7947_半透明, 0.6);
        _7945_不透明.blend(_7948_半透明, 0.4);
        _7945_不透明.blend(_7949_不透明, 0.2);
        _7945_不透明.setFileName("7945_不透明 + 7946_半透明 * 0.8 + 7947_半透明 * 0.6 + 7948_半透明 * 0.4 + 7949_不透明 * 0.2");
        w.writeUpdated(_7945_不透明, folder, true);

        System.out.println(System.currentTimeMillis());
    }




    public static void testTranslucent() {
        testTranslucent("_DSC7840_custom_erased");
        testTranslucent("_DSC7840_custom_erased2");
        testTranslucent("_DSC7840_custom_erased3");
        testTranslucent("_DSC7840_half_erased_1.0");
        testTranslucent("_DSC7840_translucent_0.8");
        testTranslucent("_DSC7840_translucent_erased_0.2");
        testTranslucent("_DSC7840_translucent_half_erased_0.2");
        testTranslucent("_DSC7840_translucent_half_erased_0.8");
    }


    public static void testTranslucent(String fileName) {
        File erasedFile = new File("/Volumes/Backup Plus/2020 Perseids/2020/2020-08-13/jpg/all/" + fileName + ".png");

        Image erased = new Image(erasedFile);
        erased.setFileName(erased.getFileName() + "_reprocessed");

        WriteConfig wc = new RgbaPngWriteConfig();
        Writer w = WriterDispatcher.getWriter(wc);

        w.writeUpdated(erased, erasedFile.getParent(), true);


//        Image reprocessed = new Image(new File("/Volumes/Backup Plus/2020 Perseids/2020/2020-08-13/jpg/all/_DSC7840_custom_erased_reprocessed_as.png"));
//
//        Pixel solid1 = erased.getPixel(2439, 1431);
//        Pixel solid2 = reprocessed.getPixel(2439, 1431);
//
//        Pixel edge1 = erased.getPixel(2493, 1437);
//        Pixel edge2 = reprocessed.getPixel(2493, 1437);
//
//        Pixel inner1 = erased.getPixel(2531, 1442);
//        Pixel inner2 = reprocessed.getPixel(2531, 1442);
//
//        Pixel empty1 = erased.getPixel(2937, 275);
//        Pixel empty2 = reprocessed.getPixel(2937, 275);
//
//        Pixel center1 = erased.getPixel(2663, 1423);
//        Pixel center2 = reprocessed.getPixel(2663, 1423);
//
//        System.out.println("solid1 = " + solid1);
//        System.out.println("solid2 = " + solid2);
//        System.out.println();
//
//        System.out.println("edge1 = " + edge1);
//        System.out.println("edge2 = " + edge2);
//        System.out.println();
//
//        System.out.println("inner1 = " + inner1);
//        System.out.println("inner2 = " + inner2);
//        System.out.println();
//
//        System.out.println("empty1 = " + empty1);
//        System.out.println("empty2 = " + empty2);
//        System.out.println();
//
//        System.out.println("center1 = " + center1);
//        System.out.println("center2 = " + center2);
//        System.out.println();
    }

    public static void alphaStackBlockTest() {
        Image red = new Image(new File("/Volumes/Backup Plus/2020 Perseids/含流星/红.png"));
        Image blue = new Image(new File("/Volumes/Backup Plus/2020 Perseids/含流星/蓝.png"));
        blue.blend(red, 0.8);
        blue.setFileName("蓝100%红80%");

        WriteConfig wc = RgbaPngWriteConfig.DEFAULT;

        Writer w = WriterDispatcher.getWriter(wc);
        w.writeUpdated(blue, "/Volumes/Backup Plus/2020 Perseids/含流星/", true);
    }

    public static void alphaStackNumberTest2() {
        for (int perc = 100; perc >= 50; perc -= 10) {
            Image red = new Image(new File("/Volumes/Backup Plus/2020 Perseids/含流星/r" + perc + ".png"));
            Image green = new Image(new File("/Volumes/Backup Plus/2020 Perseids/含流星/g60.png"));
            Image redgreen = new Image(new File("/Volumes/Backup Plus/2020 Perseids/含流星/r" + perc + "g60.png"));

            System.out.println(red.getPixel(0, 0));
            System.out.println(green.getPixel(0, 0));
            System.out.println(redgreen.getPixel(0, 0));
            System.out.println();
        }
    }




    public static void alphaStackNumberTest1(int perc) {
        Image red = new Image(new File("/Volumes/Backup Plus/2020 Perseids/含流星/r100-" + perc + "%.png"));
        Image green = new Image(new File("/Volumes/Backup Plus/2020 Perseids/含流星/g100-50%.png"));
        Image redgreen = new Image(new File("/Volumes/Backup Plus/2020 Perseids/含流星/r100-" + perc + "%-g100-50%.png"));

        System.out.println(red.getPixel(0, 0));
        System.out.println(green.getPixel(0, 0));
        System.out.println(redgreen.getPixel(0, 0));
        System.out.println();
    }

    public static void testStackWithAlpha_Test() {
        Image red = new Image(new File("/Volumes/Backup Plus/2020 Perseids/含流星/r100-100%.png"));
        Image green = new Image(new File("/Volumes/Backup Plus/2020 Perseids/含流星/g100-60%.png"));

        red.blend(green, 255);

        red.setFileName("r100-100%-g100-60%-my");

        WriteConfig wc = new RgbaPngWriteConfig();
        Writer w = WriterDispatcher.getWriter(wc);

        w.writeUpdated(red, "/Volumes/Backup Plus/2020 Perseids/含流星/", true);
    }

    public static void testStackWithAlpha_Production() {
        File baseFile = new File("/Volumes/Backup Plus/2020 Perseids/2020/2020-08-13/jpg/all/_DSC7839.jpg");
        File stackedFile = new File("/Volumes/Backup Plus/2020 Perseids/2020/2020-08-13/jpg/all/_DSC7840.png");

        Image base = new Image(baseFile);
        Image stacked = new Image(stackedFile);

        base.blend(stacked, 204);

        base.setFileName(base.getFileName() + "_stacked");

        WriteConfig wc = new RgbaPngWriteConfig();
        Writer w = WriterDispatcher.getWriter(wc);

        w.writeUpdated(base, baseFile.getParent(), true);
    }



    public static void stackTest() {

        String prefix = "/Volumes/Backup Plus/2020 Perseids/2020/2020-08-13/jpg/all/_DSC";
        int first = 7947;
        int last = 8054;

        File baseFile = new File(prefix + first + ".jpg");

        Image base = new Image(baseFile);

        int total = last - first + 1;
        double opacityStepping = 255d / total;

        for (int i = first + 1; i <= last; i++) {  // TODO 和旧版比一下速度
            int opacity = (int) Math.round(255 - (i - first) * opacityStepping);
            base.brighten(new Image(new File(prefix + i + ".jpg")), opacity);
            System.out.println(i + ", " + (i - first) + " / " + (total - 1) + ", opacity: " + opacity);
        }

        base.setFileName(base.getFileName() + "_stacked");

        WriteConfig wc = JpgWriteConfig.DEFAULT;

        Writer w = WriterDispatcher.getWriter(wc);
        w.writeUpdated(base, baseFile.getParent(), true);
    }






    public Image(int width, int height) {
        initBySize(width, height);
        fileName = "unnamed_blank_image";
    }


    public Image(File file) {

        String fullFileName = file.getName();
        int extIndex = fullFileName.lastIndexOf('.');
        fileName = extIndex == -1 ? fullFileName : fullFileName.substring(0, extIndex);


        try {

            if ((bufferedImage = ImageIO.read(file)) != null)
                initByBufferedImage(bufferedImage);

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if (bufferedImage == null) {
                initBySize(1, 1);
                fileName += "_corrupted";
            }
        }
    }


    public Image(BufferedImage bi) {
        initByBufferedImage(bi);
        bufferedImage = new BufferedImage(width, height, bi.getType());
        fileName = "unnamed_image";
    }


    private void initBySize(int width, int height) {

        this.width = width;
        this.height = height;

        hasAlpha = false;

        bufferedImage = new BufferedImage(width, height, DEFAULT_RGB_TYPE);

        pixels = new RgbPixel[width * height];
        for (int i = 0; i < width * height; i++)
            pixels[i] = new RgbPixel(0, 0, 0, i % width, i / width);
    }









    private void initByBufferedImage(BufferedImage bi) {

        width = bi.getWidth();
        height = bi.getHeight();

        int length = width * height;
        pixels = new RgbPixel[length];

        ColorModel cm = bi.getColorModel();
        hasAlpha = cm.hasAlpha();

        Raster raster = bi.getRaster();


        Object dataElement = null;

        if (hasAlpha) {

            for (int i = 0; i < length; i++) {

                int x = i % width;
                int y = i / width;

                dataElement = raster.getDataElements(x, y, dataElement);
                int alpha = cm.getAlpha(dataElement);

                if (alpha == 255) {
                    pixels[i] = new RgbPixel(cm.getRed(dataElement), cm.getGreen(dataElement), cm.getBlue(dataElement), x, y);
                } else {
                    pixels[i] = new RgbaPixel(cm.getRed(dataElement), cm.getGreen(dataElement), cm.getBlue(dataElement), alpha, x, y);
                }
            }

        } else {

            for (int i = 0; i < length; i++) {

                int x = i % width;
                int y = i / width;

                dataElement = raster.getDataElements(x, y, dataElement);
                pixels[i] = new RgbPixel(cm.getRed(dataElement), cm.getGreen(dataElement), cm.getBlue(dataElement), x, y);
            }
        }
    }


    public void updateBufferedImageByPixels() {
        if (hasAlpha) {
            for (RgbPixel p : pixels)
                bufferedImage.setRGB(p.x, p.y, p.getEncodedRGBA());
        } else {
            for (RgbPixel p : pixels)
                bufferedImage.setRGB(p.x, p.y, p.getEncodedRGB());
        }
    }







    public boolean hasAlpha() {
        return hasAlpha;
    }


    public String getFileName() {
        return fileName;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }


    public Pixel getPixel(int x, int y) {
        return pixels[y * width + x];
    }


    public BufferedImage renderAlphaMultipliedImage() {

        if (!hasAlpha) return bufferedImage;

        BufferedImage rendered = new BufferedImage(width, height, DEFAULT_RGB_TYPE);

        for (RgbPixel p : pixels)
            rendered.setRGB(p.x, p.y, p.getEncodedAlphaMultipliedRGB());

        return rendered;
    }


    public BufferedImage renderAlphaDiscardedImage() {

        if (!hasAlpha) return bufferedImage;

        BufferedImage rendered = new BufferedImage(width, height, DEFAULT_RGB_TYPE);

        for (RgbPixel p : pixels)
            rendered.setRGB(p.x, p.y, p.getEncodedRGB());

        return rendered;
    }


    public void blend(Image other, double otherOpacity) {

        // If the other image is opaque, replace this image's data with the other image
        if (!other.hasAlpha && otherOpacity == 1.0) {

            RgbPixel pixel, otherPixel;

            // If this image is translucent, it should be converted to opaque
            // A blank BufferedImage with DEFAULT_RGB_TYPE will be created to hold the data
            if (hasAlpha) {

                for (int i = 0; i < pixels.length; i++) {

                    pixel = pixels[i];
                    otherPixel = other.pixels[i];

                    // If this pixel in the current image has alpha, then convert it to an RgbPixel
                    if (pixel.hasAlpha()) {
                        pixels[i] = new RgbPixel(otherPixel);

                    // Otherwise replace the pixels' values
                    } else {
                        pixel.r = otherPixel.r;
                        pixel.g = otherPixel.g;
                        pixel.b = otherPixel.b;
                    }
                }

                bufferedImage = new BufferedImage(width, height, DEFAULT_RGB_TYPE);
                hasAlpha = false;

            // If this image is opaque, replace the pixels' values
            } else {

                for (int i = 0; i < pixels.length; i++) {

                    pixel = pixels[i];
                    otherPixel = other.pixels[i];

                    pixel.r = otherPixel.r;
                    pixel.g = otherPixel.g;
                    pixel.b = otherPixel.b;
                }
            }

            return;
        }


        // If the other image is translucent, its pixels might or might not have alpha
        if (other.hasAlpha) {

            RgbPixel otherPixel;

            for (int i = 0; i < pixels.length; i++) {

                otherPixel = other.pixels[i];

                // If the other pixel has alpha, it needs to be cast to RgbaPixel to call blend(RgbaPixel other, double opacity)
                if (otherPixel.hasAlpha()) {
                    pixels[i].blend((RgbaPixel) otherPixel, otherOpacity);

                // If the other pixel does not have alpha, call blend(RgbPixel other, double opacity)
                } else {
                    pixels[i].blend(otherPixel, otherOpacity);
                }
            }

        // If the other image is opaque, it is guaranteed that its pixels do not have alpha,
        // therefore call blend(RgbPixel other, double opacity)
        } else {
            for (int i = 0; i < pixels.length; i++)
                pixels[i].blend(other.pixels[i], otherOpacity);
        }
    }



    public void brighten(Image other, double otherOpacity) {
        RgbPixel pixel, otherPixel;
        double opacityRatio = otherOpacity / 255d;
        if (hasAlpha) { // TODO 验证这个算法的对错
            // 我有你没有，你的不透明度为255；=> 新图像不含alpha
            // 我有你没有，你的不透明度<255；
            for (int i = 0; i < pixels.length; i++) {
                pixel = pixels[i];
                otherPixel = other.pixels[i];
                pixel.setR(MathUtil.brighten(pixel.getR(), otherPixel.getR(), opacityRatio));
                pixel.setG(MathUtil.brighten(pixel.getG(), otherPixel.getG(), opacityRatio));
                pixel.setB(MathUtil.brighten(pixel.getB(), otherPixel.getB(), opacityRatio));
                pixel.setA(MathUtil.brighten(pixel.getA(), otherPixel.getA(), opacityRatio));
            }
        } else {
            // 我没有你有，你的不透明度为255；
            // 我没有你有，你的不透明度<255；
            for (int i = 0; i < pixels.length; i++) {
                pixel = pixels[i];
                otherPixel = other.pixels[i];
                pixel.setR(MathUtil.brighten(pixel.getR(), otherPixel.getR(), opacityRatio));
                pixel.setG(MathUtil.brighten(pixel.getG(), otherPixel.getG(), opacityRatio));
                pixel.setB(MathUtil.brighten(pixel.getB(), otherPixel.getB(), opacityRatio));
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    @Override
    public Object clone() {
        try {
            Image cloned = (Image) super.clone();
            cloned.width = width;
            cloned.height = height;
            cloned.hasAlpha = hasAlpha;
            Hashtable<String, Object> properties = new Hashtable<>();
            String[] propertyNames;
            if ((propertyNames = bufferedImage.getPropertyNames()) != null) {
                for (String key : propertyNames) {
                    properties.put(key, bufferedImage.getProperty(key));
                }
            }
            cloned.bufferedImage = new BufferedImage(bufferedImage.getColorModel(), bufferedImage.getRaster(), bufferedImage.isAlphaPremultiplied(), properties);




            int length = width * height;
            cloned.pixels = new RgbPixel[length];

            ColorModel cm = cloned.bufferedImage.getColorModel();
            cloned.hasAlpha = cm.hasAlpha();

            Raster raster = cloned.bufferedImage.getRaster();


            Object dataElement = null;

            if (hasAlpha) {

                for (int i = 0; i < length; i++) {

                    int x = i % width;
                    int y = i / width;

                    dataElement = raster.getDataElements(x, y, dataElement);
                    int alpha = cm.getAlpha(dataElement);

                    if (alpha == 255) {
                        cloned.pixels[i] = new RgbPixel(cm.getRed(dataElement), cm.getGreen(dataElement), cm.getBlue(dataElement), x, y);
                    } else {
                        cloned.pixels[i] = new RgbaPixel(cm.getRed(dataElement), cm.getGreen(dataElement), cm.getBlue(dataElement), alpha, x, y);
                    }
                }

            } else {
                for (int i = 0; i < length; i++) {

                    int x = i % width;
                    int y = i / width;

                    dataElement = raster.getDataElements(x, y, dataElement);
                    cloned.pixels[i] = new RgbPixel(cm.getRed(dataElement), cm.getGreen(dataElement), cm.getBlue(dataElement), x, y);
                }
            }





            cloned.fileName = fileName;
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new Error();
        }
    }


//    @Override
//    public Object clone() {
//        try {
//            Image cloned = (Image) super.clone();
//            cloned.width = width;
//            cloned.height = height;
//            cloned.hasAlpha = hasAlpha;
//            cloned.bufferedImage = new BufferedImage(width, height, bufferedImage.getType());
//            RgbPixel[] clonedPixels = new RgbPixel[pixels.length];
//            for (int i = 0; i < pixels.length; i++) {
//                RgbPixel toClone = pixels[i];
//                clonedPixels[i] = toClone.hasAlpha() ? new RgbaPixel((RgbaPixel) toClone) : new RgbPixel(toClone);
//            }
//            cloned.pixels = clonedPixels;
//            cloned.fileName = fileName;
//            return cloned;
//        } catch (CloneNotSupportedException e) {
//            throw new Error();
//        }
//    }


//    @Override
//    public Object clone() {
//        try {
//            updateBufferedImageByPixels();
//            Image cloned = (Image) super.clone();
//            cloned.initByBufferedImage(bufferedImage);
//            cloned.bufferedImage = new BufferedImage(cloned.width, cloned.height, bufferedImage.getType());
//            cloned.fileName = fileName;
//            return cloned;
//        } catch (CloneNotSupportedException e) {
//            throw new Error();
//        }
//    }
}
