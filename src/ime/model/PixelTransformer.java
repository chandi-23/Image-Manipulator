/**
 * The PixelTransformer class is an ImageProcessor that transforms individual pixels of an image
 * using a user-defined function.
 */

package ime.model;

import java.util.function.Function;

/**
 * The PixelTransformer class allows the transformation of individual pixels in an image using a
 * user-defined function.
 */
class PixelTransformer implements ImageProcessor {

  private final Function<Pixel, Pixel> transformer;

  /**
   * Constructs a PixelTransformer with the specified pixel transformation function.
   *
   * @param transformer A function that takes a Pixel as input and returns a transformed Pixel.
   */
  public PixelTransformer(Function<Pixel, Pixel> transformer) {
    this.transformer = transformer;
  }

  /**
   * Applies the pixel transformation function to each pixel in the input image, producing an
   * output image with transformed pixels.
   *
   * @param inputImage The input image to be transformed.
   * @return An image with pixels transformed according to the provided function.
   */
  @Override
  public Image process(Image inputImage) {
    int width = inputImage.getWidth();
    int height = inputImage.getHeight();
    Image outputImage = new RGBImage(width, height, inputImage.getMax());
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        outputImage.setPixel(i, j, transformer.apply(inputImage.getPixel(i, j)));
      }
    }
    return outputImage;
  }
}
