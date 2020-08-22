package image;

class Resource {

    private Image image;

    Resource(Image image) {
        this.image = image;
    }

    Image getImage() {
//        Image copy = new Image(image.getBufferedImage());
//        copy.setFileName(image.getFileName());

        return (Image) image.clone();



//        Image copy = new Image(image.getWidth(), image.getHeight(), image.type);
//
//        RgbPixel[] cpyPixels = copy.getPixels();
//        RgbPixel[] srcPixels = image.getPixels();
//
//        RgbPixel srcPixel;
//
//        for (int i = 0; i < image.getWidth() * image.getHeight(); i++) {
//            srcPixel = srcPixels[i];
//            cpyPixels[i] = srcPixel.hasAlpha() ? new RgbaPixel((RgbaPixel) srcPixel) : new RgbPixel(srcPixel);
//        }
//
//        copy.setHasAlpha(image.hasAlpha());
//        copy.setFileName(image.getFileName());

//        return copy;
    }

}
