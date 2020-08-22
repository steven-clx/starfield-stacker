package image;

class Resource {

    private Image image;
    private Image copy;

    Resource(Image image) {
        this.image = image;
    }

    Image getImage() {
        copy = new Image(image.getBufferedImage());
        copy.setFileName(image.getFileName());



//        copy = new Image(image.getWidth(), image.getHeight());


        return copy;
    }

}