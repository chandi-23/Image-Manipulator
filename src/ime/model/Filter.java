/**
 * The Filter class implements the ImageProcessor interface and represents a filter
 * operation to process an image using a specified convolution kernel.
 */

package ime.model;

/**
 * The Filter class is responsible for applying a convolution operation on an input
 * image using a given kernel.
 */
class Filter implements ImageProcessor {

  private final double[][] kernel;

  /**
   * Constructs a new Filter with the specified convolution kernel.
   *
   * @param kernel The 2D array representing the convolution kernel used for image processing.
   */
  public Filter(double[][] kernel) {
    this.kernel = kernel;
  }

  /**
   * Applies the convolution operation on the input image using the convolution kernel.
   *
   * @param inputImage The input image to be processed.
   * @return An Image object representing the result of the convolution operation.
   */
  @Override
  public Image process(Image inputImage) {
    int width = inputImage.getWidth();
    int height = inputImage.getHeight();
    int kernelSize = kernel.length;
    RGBImage outputImage = new RGBImage(width, height, inputImage.getMax());

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        double red = 0;
        double green = 0;
        double blue = 0;
        int kMin = Math.max(0, i - kernelSize / 2);
        int kMax = Math.min(height - 1, (i + kernelSize / 2));
        int lMin = Math.max(0, j - kernelSize / 2);
        int lMax = Math.min(width - 1, (j + kernelSize / 2));
        int mMin = (kernelSize / 2) - (i - kMin);
        int nMin = (kernelSize / 2) - (j - lMin);
        for (int k = kMin, m = mMin; k <= kMax; k++, m++) {
          for (int l = lMin, n = nMin; l <= lMax; l++, n++) {
            RGBPixel pixel = (RGBPixel) inputImage.getPixel(k, l);
            red += kernel[m][n] * pixel.getRed();
            green += kernel[m][n] * pixel.getGreen();
            blue += kernel[m][n] * pixel.getBlue();
          }
        }
        outputImage.setPixel(i, j,
            new RGBPixel((int) Math.round(red), (int) Math.round(green), (int) Math.round(blue),
                inputImage.getMax()));
      }
    }

    return outputImage;
  }
}
