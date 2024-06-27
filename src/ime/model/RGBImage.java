/**
 * The RGBImage class represents an image with RGB (Red, Green, Blue) pixels. It implements the
 * Image interface and provides methods to access and manipulate individual pixels.
 */

package ime.model;

/**
 * The RGBImage class represents an image with RGB (Red, Green, Blue) pixels. It implements the
 * Image interface and provides methods to access and manipulate individual pixels.
 */
class RGBImage implements Image, Cloneable {

  private final Pixel[][] pixels;
  private final int width;
  private final int height;
  private final int max;

  /**
   * Constructs an RGBImage with the specified width, height, and maximum color value.
   *
   * @param width The width of the image.
   * @param height The height of the image.
   * @param max The maximum color value.
   */
  public RGBImage(int width, int height, int max) {
    this.width = width;
    this.height = height;
    this.max = max;
    this.pixels = new RGBPixel[height][width];
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getMax() {
    return max;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public Pixel getPixel(int row, int col) throws IndexOutOfBoundsException {
    try {
      return pixels[row][col];
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Please input valid pixel indices");
    }
  }

  @Override
  public void setPixel(int row, int col, Pixel pixel) throws IndexOutOfBoundsException {
    try {
      pixels[row][col] = (RGBPixel) pixel;
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Please input valid pixel indices");
    }
  }

  @Override
  public Image getPortion(int rowStart, int rowEnd, int columnStart, int columnEnd) {
    Image portion = new RGBImage(columnEnd - columnStart + 1,
            rowEnd - rowStart + 1, max);
    for (int i = rowStart; i <= rowEnd; i++) {
      for (int j = columnStart; j <= columnEnd; j++) {
        RGBPixel pixel = (RGBPixel) pixels[i][j];
        portion.setPixel(i - rowStart, j - columnStart,
                new RGBPixel(pixel.getRed(), pixel.getGreen(), pixel.getBlue(), pixel.getMax()));
      }
    }
    return portion;
  }

  @Override
  public void replacePortion(int rowStart, int colStart, Image newPortion) {

    int portionWidth = newPortion.getWidth();
    int portionHeight = newPortion.getHeight();

    for (int i = rowStart; i < rowStart + portionHeight; i++) {
      for (int j = colStart; j < colStart + portionWidth; j++) {
        RGBPixel pixel = (RGBPixel) newPortion.getPixel(i - rowStart, j - colStart);
        setPixel(i, j, new RGBPixel(pixel.getRed(), pixel.getGreen(),
                pixel.getBlue(), pixel.getMax()));
      }
    }

  }

  /**
   * Creates a copy of the calling RGBImage object.
   * @return the copy of the calling RGBImage object.
   * @throws CloneNotSupportedException If cloning of the model is not supported.
   */
  @Override
  public Image clone() throws CloneNotSupportedException {
    super.clone();
    Image copy = new RGBImage(width, height, max);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        RGBPixel pixel = (RGBPixel) pixels[i][j];
        copy.setPixel(i, j, new RGBPixel(pixel.getRed(), pixel.getGreen(),
                pixel.getBlue(), pixel.getMax()));
      }
    }
    return copy;
  }
}
