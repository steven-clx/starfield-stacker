import image.RgbPixel;
import image.RgbaPixel;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

public class Main {

    private static Map<Integer, String> typeMap;
    private static Map<String, BufferedImage> imageMap;
    private static int x1 = 10, y1 = 10, x2 = 300, y2 = 10, x3 = 10, y3 = 289, x4 = 589, y4 = 289, x5 = 589, y5 = 10;


    public static void main(String[] args) {
        methodOverloadingTest();
    }


    public static void methodOverloadingTest() {
        RgbPixel rgb = new RgbPixel();
        RgbaPixel rgba = new RgbaPixel();
        RgbPixel rgbaAsRgb = new RgbaPixel();



//        rgb.blend(rgb.hasAlpha() ? (RgbaPixel) rgb : rgb, 1);
//        rgb.blend(rgba.hasAlpha() ? (RgbaPixel) rgba : rgba, 1);
//        rgb.blend(rgbaAsRgb.hasAlpha() ? (RgbaPixel) rgbaAsRgb : rgbaAsRgb, 1);
//
//        System.out.println();
//
//        rgba.blend(rgb.hasAlpha() ? (RgbaPixel) rgb : rgb, 1);
//        rgba.blend(rgba.hasAlpha() ? (RgbaPixel) rgba : rgba, 1);
//        rgba.blend(rgbaAsRgb.hasAlpha() ? (RgbaPixel) rgbaAsRgb : rgbaAsRgb, 1);
//
//        System.out.println();
//
//        rgbaAsRgb.blend(rgb.hasAlpha() ? (RgbaPixel) rgb : rgb, 1);
//        rgbaAsRgb.blend(rgba.hasAlpha() ? (RgbaPixel) rgba : rgba, 1);
//        rgbaAsRgb.blend(rgbaAsRgb.hasAlpha() ? (RgbaPixel) rgbaAsRgb : rgbaAsRgb, 1);
//
//        System.out.println();
//
//        System.out.println(rgb.hasAlpha());
//        System.out.println(rgba.hasAlpha());
//        System.out.println(rgbaAsRgb.hasAlpha());



        blend(rgb, rgb);
        blend(rgb, rgba);
        blend(rgb, rgbaAsRgb);

        System.out.println();

        blend(rgba, rgb);
        blend(rgba, rgba);
        blend(rgba, rgbaAsRgb);

        System.out.println();

        blend(rgbaAsRgb, rgb);
        blend(rgbaAsRgb, rgba);
        blend(rgbaAsRgb, rgbaAsRgb);

        System.out.println();

        System.out.println(rgb.hasAlpha());
        System.out.println(rgba.hasAlpha());
        System.out.println(rgbaAsRgb.hasAlpha());
    }

    public static void blend(RgbPixel pixel, RgbPixel otherPixel) {
        if (otherPixel.hasAlpha()) {
            pixel.blend((RgbaPixel) otherPixel, 1);
        } else {
            pixel.blend(otherPixel, 1);
        }
    }


    public static void dynamicDispatchAndDeclaredTypeTest() {
        RgbPixel rgb = new RgbPixel();
        RgbaPixel rgba = new RgbaPixel();
        RgbPixel rgbaAsRgb = new RgbaPixel();


        rgb.blend(rgb, 1);  //                     应当触发 rgb + rgb
        rgb.blend(rgba, 1);  //                    应当触发 rgb + rgba
        rgb.blend(rgbaAsRgb, 1);  //               应当触发 rgb + rgb
        rgb.blend((RgbPixel) rgba, 1);  //         应当触发 rgb + rgb
        rgb.blend((RgbaPixel) rgbaAsRgb, 1);  //   应当触发 rgb + rgba

        System.out.println();

        rgba.blend(rgb, 1);  //                    应当触发 rgba + rgb
        rgba.blend(rgba, 1);  //                   应当触发 rgba + rgba
        rgba.blend(rgbaAsRgb, 1);  //              应当触发 rgba + rgb
        rgba.blend((RgbPixel) rgba, 1);  //        应当触发 rgba + rgb
        rgba.blend((RgbaPixel) rgbaAsRgb, 1);  //  应当触发 rgba + rgba

        System.out.println();

        ((RgbPixel) rgba).blend(rgb, 1);  //                    应当触发 rgba + rgb
        ((RgbPixel) rgba).blend(rgba, 1);  //                   应当触发 rgba + rgba
        ((RgbPixel) rgba).blend(rgbaAsRgb, 1);  //              应当触发 rgba + rgb
        ((RgbPixel) rgba).blend((RgbPixel) rgba, 1);  //        应当触发 rgba + rgb
        ((RgbPixel) rgba).blend((RgbaPixel) rgbaAsRgb, 1);  //  应当触发 rgba + rgba

        System.out.println();

        rgbaAsRgb.blend(rgb, 1);  //                    应当触发 rgba + rgb
        rgbaAsRgb.blend(rgba, 1);  //                   应当触发 rgba + rgba
        rgbaAsRgb.blend(rgbaAsRgb, 1);  //              应当触发 rgba + rgb
        rgbaAsRgb.blend((RgbPixel) rgba, 1);  //        应当触发 rgba + rgb
        rgbaAsRgb.blend((RgbaPixel) rgbaAsRgb, 1);  //  应当触发 rgba + rgba

        System.out.println();

        ((RgbaPixel) rgbaAsRgb).blend(rgb, 1);  //                    应当触发 rgba + rgb
        ((RgbaPixel) rgbaAsRgb).blend(rgba, 1);  //                   应当触发 rgba + rgba
        ((RgbaPixel) rgbaAsRgb).blend(rgbaAsRgb, 1);  //              应当触发 rgba + rgb
        ((RgbaPixel) rgbaAsRgb).blend((RgbPixel) rgba, 1);  //        应当触发 rgba + rgb
        ((RgbaPixel) rgbaAsRgb).blend((RgbaPixel) rgbaAsRgb, 1);  //  应当触发 rgba + rgba

    }



    public static void testDecodeImage() throws IOException {

        System.out.println("Hello Stars!");

        String dir = "/Users/steven/Downloads/RGB测试/";

        typeMap = new HashMap<Integer, String>() {{
            put(0, "TYPE_CUSTOM");
            put(1, "TYPE_INT_RGB");
            put(2, "TYPE_INT_ARGB");
            put(3, "TYPE_INT_ARGB_PRE");
            put(4, "TYPE_INT_BGR");
            put(5, "TYPE_3BYTE_BGR");
            put(6, "TYPE_4BYTE_ABGR");
            put(7, "TYPE_4BYTE_ABGR_PRE");
            put(8, "TYPE_USHORT_565_RGB");
            put(9, "TYPE_USHORT_555_RGB");
            put(10, "TYPE_BYTE_GRAY");
            put(11, "TYPE_USHORT_GRAY");
            put(12, "TYPE_BYTE_BINARY");
            put(13, "TYPE_BYTE_INDEXED");
        }};

        List<String> files = new ArrayList<String>() {{

            // 8位不带A通道
//            add("RGB-8.jpg");
            add("RGB-8.png");

            // 16位不带A通道
//            add("RGB-16.png");

            // 8位带A通道
//            add("RGBA-8.png");

            // 16位带A通道
//            add("RGBA-16.png");
        }};

        imageMap = new HashMap<>();

        for (String f : files)
            imageMap.put(f, ImageIO.read(new File(dir + f)));

//        files.forEach(Main::printImageMode);

        BufferedImage bi = ImageIO.read(new File(dir + "RGB-8副本.jpg"));
        System.out.println(getPixelDetailsByColorModel(bi, x1, y1));
        System.out.println(getPixelDetailsByColorModel(bi, x2, y2));
        System.out.println(getPixelDetailsByColorModel(bi, x3, y3));
        System.out.println(getPixelDetailsByColorModel(bi, x4, y4));
        System.out.println(getPixelDetailsByColorModel(bi, x5, y5));

        BufferedImage bi2 = ImageIO.read(new File(dir + "RGB-8副本2.jpg"));
        System.out.println(getPixelDetailsByColorModel(bi2, x1, y1));
        System.out.println(getPixelDetailsByColorModel(bi2, x2, y2));
        System.out.println(getPixelDetailsByColorModel(bi2, x3, y3));
        System.out.println(getPixelDetailsByColorModel(bi2, x4, y4));
        System.out.println(getPixelDetailsByColorModel(bi2, x5, y5));


        BufferedImage bi3 = ImageIO.read(new File(dir + "RGBA-16.png"));
        System.out.println(getPixelDetailsByColorModel(bi3, x1, y1));
        System.out.println(getPixelDetailsByColorModel(bi3, x2, y2));
        System.out.println(getPixelDetailsByColorModel(bi3, x3, y3));
        System.out.println(getPixelDetailsByColorModel(bi3, x4, y4));
        System.out.println(getPixelDetailsByColorModel(bi3, x5, y5));
    }

    private static void printImageMode(String file) {
        BufferedImage image = imageMap.get(file);
        if (image == null) {
            System.out.println(file + " : null");
            return;
        }

//        ColorModel colorModel = image.getColorModel();
//        ColorSpace colorSpace = colorModel.getColorSpace();

        Raster raster = image.getData();
        WritableRaster writableRaster = image.getRaster();
        WritableRaster alphaRaster = image.getAlphaRaster();

        DataBuffer rasterDataBuffer = raster.getDataBuffer();
        DataBuffer writableRasterDataBuffer = writableRaster.getDataBuffer();
        DataBuffer alphaRasterDataBuffer = alphaRaster == null ? null : alphaRaster.getDataBuffer();

        System.out.println(file + "\t: " +

//                "imageType = " + typeMap.get(image.getType()) +

                "byRasterDataBuffer = ( " + getDataBufferString(rasterDataBuffer) + " )" +
                ", byWritableRasterDataBuffer = ( " + getDataBufferString(writableRasterDataBuffer) + " )" +
                (alphaRasterDataBuffer == null ? "" : ", byAlphaRasterDataBuffer = ( " + getDataBufferString(alphaRasterDataBuffer) + " )") +

                ", byRasterDataElements = ( " + getDataElementsString(raster) + " )" +
                ", byWritableRasterDataElements = ( " + getDataElementsString(writableRaster) + " )" +
                (alphaRaster == null ? "" : ", byAlphaRasterDataElements = ( " + getDataElementsString(alphaRaster) + " )")

//                ", byColorModel = ( " +
//                    "white = " + getPixelDetailsByColorModel(image, x1, y1) +
//                    ", black = " + getPixelDetailsByColorModel(image, x2, y2) +
//                    ", grey = " + getPixelDetailsByColorModel(image, x3, y3) +
//                    ", red240 = " + getPixelDetailsByColorModel(image, x4, y4) +
//                    ", green250 = " + getPixelDetailsByColorModel(image, x5, y5) +
//                " )" +
//
//                ", byGetRGB = ( " +
//                    "white = " + getPixelDetailsByGetRGB(image, x1, y1) +
//                    ", black = " + getPixelDetailsByGetRGB(image, x2, y2) +
//                    ", grey = " + getPixelDetailsByGetRGB(image, x3, y3) +
//                    ", red240 = " + getPixelDetailsByGetRGB(image, x4, y4) +
//                    ", green250 = " + getPixelDetailsByGetRGB(image, x5, y5) +
//                ")"

//                ", ColorModel = ( " +
//                "numComponents = " + colorModel.getNumComponents() +
//                ", numColorComponents = " + colorModel.getNumColorComponents() +
//                ", transparency = " + colorModel.getTransparency() +
//                ", hasAlpha = " + colorModel.hasAlpha() +
//                ", isAlphaPre = " + colorModel.isAlphaPremultiplied() +
//                " )" +
//                ", ColorSpace = ( " +
//                "type = " + colorSpace.getType() +
//                ", numComponents = " + colorSpace.getNumComponents() +
//                " )"
        );
    }

    private static String getDataElementsString(Raster raster) {
        Object zero = raster.getDataElements(0, 0, null);
        int length = processDataElements(zero, objects -> objects.length);
        return "type = " + zero.getClass() +
                ", length = " + length +
                ", white = " + processDataElements(raster.getDataElements(x1, y1, null), getDataFromDataElements()) +
                ", black = " + processDataElements(raster.getDataElements(x2, y2, null), getDataFromDataElements()) +
                ", grey = " + processDataElements(raster.getDataElements(x3, y3, null), getDataFromDataElements()) +
                ", red240 = " + processDataElements(raster.getDataElements(x4, y3, null), getDataFromDataElements()) +
                ", green250 = " + processDataElements(raster.getDataElements(x5, y5, null), getDataFromDataElements());
    }


    private static <R> R processDataElements(Object dataElement, Function<Object[], R> f) {
        if (dataElement.getClass().equals(byte[].class)) {
            byte[] data = (byte[]) dataElement;
            Byte[] wrapped = new Byte[data.length];
            for (int i = 0; i < data.length; i++) wrapped[i] = data[i];
            return f.apply(wrapped);
        } else if (dataElement.getClass().equals(short[].class)) {
            short[] data = (short[]) dataElement;
            Short[] wrapped = new Short[data.length];
            for (int i = 0; i < data.length; i++) wrapped[i] = data[i];
            return f.apply(wrapped);
        } else if (dataElement.getClass().equals(int[].class)) {
            int[] data = (int[]) dataElement;
            Integer[] wrapped = new Integer[data.length];
            for (int i = 0; i < data.length; i++) wrapped[i] = data[i];
            return f.apply(wrapped);
        } else if (dataElement.getClass().equals(long[].class)) {
            long[] data = (long[]) dataElement;
            Long[] wrapped = new Long[data.length];
            for (int i = 0; i < data.length; i++) wrapped[i] = data[i];
            return f.apply(wrapped);
        } else {
            Object[] wrapped = (Object[]) dataElement;
            return f.apply(wrapped);
        }
    }

    private static Function<Object[], String> getDataFromDataElements() {
        return dataElements -> {
            String dataElementString = "(";
            for (int i = 0; i < dataElements.length - 1; i++) dataElementString += dataElements[i] + ", ";
            dataElementString += dataElements[dataElements.length - 1];
            return dataElementString + ")";
        };
    }



    private static String getDataBufferString(DataBuffer dataBuffer) {
        int length = processDataBuffer(dataBuffer, objects -> objects.length);
        int numComponents = length / 180000;
        return "type = " + dataBuffer.getClass() +
                ", length = " + length +
                ", white = " + processDataBuffer(dataBuffer, getPixelDataFromDataBuffer(x1, y1, numComponents)) +
                ", black = " + processDataBuffer(dataBuffer, getPixelDataFromDataBuffer(x2, y2, numComponents)) +
                ", grey = " + processDataBuffer(dataBuffer, getPixelDataFromDataBuffer(x3, y3, numComponents)) +
                ", red240 = " + processDataBuffer(dataBuffer, getPixelDataFromDataBuffer(x4, y4, numComponents)) +
                ", green250 = " + processDataBuffer(dataBuffer, getPixelDataFromDataBuffer(x5, y5, numComponents));

    }

    private static Function<Object[], String> getPixelDataFromDataBuffer(int x, int y, int numComponents) {
        return objects -> {
            int first = (x + y * 600) * numComponents;
            String result = "(";
            for (int i = 0; i < numComponents - 1; i++) {
                int index = first + i;
                Object data = objects[index];
                result += data.toString() + ", ";
            }
            result += objects[first + numComponents - 1].toString() + ")";
            return result;
        };
    }


    private static <R> R processDataBuffer(DataBuffer dataBuffer, Function<Object[], R> f) {
        if (dataBuffer.getClass().equals(DataBufferByte.class)) {
            DataBufferByte db = (DataBufferByte) dataBuffer;
            byte[] data = db.getData();
            Byte[] wrapped = new Byte[data.length];
            for (int i = 0; i < data.length; i++) wrapped[i] = data[i];
            return f.apply(wrapped);
        } else if (dataBuffer.getClass().equals(DataBufferShort.class)) {
            DataBufferShort db = (DataBufferShort) dataBuffer;
            short[] data = db.getData();
            Short[] wrapped = new Short[data.length];
            for (int i = 0; i < data.length; i++) wrapped[i] = data[i];
            return f.apply(wrapped);
        } else if (dataBuffer.getClass().equals(DataBufferInt.class)) {
            DataBufferInt db = (DataBufferInt) dataBuffer;
            int[] data = db.getData();
            Integer[] wrapped = new Integer[data.length];
            for (int i = 0; i < data.length; i++) wrapped[i] = data[i];
            return f.apply(wrapped);
        } else if (dataBuffer.getClass().equals(DataBufferUShort.class)) {
            DataBufferUShort db = (DataBufferUShort) dataBuffer;
            short[] data = db.getData();
            Short[] wrapped = new Short[data.length];
            for (int i = 0; i < data.length; i++) wrapped[i] = data[i];
            return f.apply(wrapped);
        } else {
            return null;
        }
    }

    private static String getPixelDetailsByColorModel(BufferedImage bi, int x, int y) {
        ColorModel cm = bi.getColorModel();
        WritableRaster wr = bi.getRaster();
        Object dataElement = wr.getDataElements(x, y, null);

        int a = cm.getAlpha(dataElement);
        int r = cm.getRed(dataElement);
        int g = cm.getGreen(dataElement);
        int b = cm.getBlue(dataElement);
        int pixel = bi.getRGB(x, y);

        return "(" + pixel + ", " + a + ", " + r + ", " + g + ", " + b + ")";
    }

    private static String getPixelDetailsByGetRGB(BufferedImage bi, int x, int y) {
        int pixel = bi.getRGB(x, y);
        int a = (pixel>>24) & 0xff;
        int r = (pixel>>16) & 0xff;
        int g = (pixel>>8) & 0xff;
        int b = pixel & 0xff;
        return "(" + pixel + ", " + a + ", " + r + ", " + g + ", " + b + ")";
    }

}
