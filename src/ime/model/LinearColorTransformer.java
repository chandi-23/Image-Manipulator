/**
 * The LinearColorTransformer class implements the ImageProcessor interface and applies a linear
 * color transformation to an input image using a provided transformation matrix.
 */

package ime.model;

/**
 * The LinearColorTransformer class performs a linear color transformation on an input image using
 * the specified matrix.
 */
class LinearColorTransformer implements ImageProcessor {

  private final double[][] matrix;

  /**
   * Constructs an instance of the LinearColorTransformer class with the specified
   * transformation matrix.
   *
   * @param matrix The transformation matrix used for the color transformation.
   */
  public LinearColorTransformer(double[][] matrix) {
    this.matrix = matrix;
  }

  /**
   * Applies the linear color transformation to the input image using the provided matrix.
   *
   * @param inputImage The Image object on which the color transformation is applied.
   * @return A new Image object representing the transformed image.
   */
  @Override
  public Image process(Image inputImage) {
    return new PixelTransformer((Pixel pixel) -> {
      RGBPixel rgbPixel = (RGBPixel) pixel;
      return new RGBPixel(
          (int) Math.round(matrix[0][0] * rgbPixel.getRed() + matrix[0][1] * rgbPixel.getGreen()
              + matrix[0][2] * rgbPixel.getBlue()),
          (int) Math.round(matrix[1][0] * rgbPixel.getRed() + matrix[1][1] * rgbPixel.getGreen()
              + matrix[1][2] * rgbPixel.getBlue()),
          (int) Math.round(matrix[2][0] * rgbPixel.getRed() + matrix[2][1] * rgbPixel.getGreen()
              + matrix[2][2] * rgbPixel.getBlue()),
          rgbPixel.getMax()
      );
    }).process(inputImage);
  }
}
