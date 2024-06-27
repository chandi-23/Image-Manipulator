/**
 * The MIMEFacade interface extends the IMEFacade interface and defines additional methods
 * for image manipulation and enhancement.
 * <p>
 * This interface is designed to be used within the IME framework for image processing.
 * </p>
 *
 * @see IMEFacade
 */

package ime.model;

/**
 * The MIMEFacade interface extends the IMEFacade interface and defines additional methods
 * for image manipulation and enhancement. This interface is designed to be used within
 * the IME framework for image processing.
 *
 * @see IMEFacade
 */
public interface MIMEFacade extends IMEFacade {

  /**
   * Compresses the input image by the specified percentage and saves the
   * result as a new image.
   *
   * @param percentage       The percentage by which to compress the image.
   * @param inputImageName   The name of the input image.
   * @param outputImageName  The name of the output image where the
   *                         compressed image will be saved.
   * @throws IllegalArgumentException If the compression percentage is invalid.
   */
  void compress(double percentage, String inputImageName, String outputImageName)
          throws IllegalArgumentException;

  /**
   * Generates a histogram for the input image and saves the result as a new image.
   *
   * @param inputImageName   The name of the input image.
   * @param outputImageName  The name of the output image where the
   *                         histogram image will be saved.
   * @return A two-dimensional array representing the histogram data.
   */
  int[][] histogram(String inputImageName, String outputImageName);

  /**
   * Applies color correction to the input image and saves the
   * result as a new image.
   *
   * @param inputImageName   The name of the input image.
   * @param outputImageName  The name of the output image where the
   *                         color-corrected image will be saved.
   */
  void colorCorrect(String inputImageName, String outputImageName);

  /**
   * Applies color correction to the input image with the specified
   *        split percentage and saves the result as a new image.
   *
   * @param inputImageName   The name of the input image.
   * @param outputImageName  The name of the output image where the
   *                         color-corrected image will be saved.
   * @param splitPercentage  The percentage of the image to apply color correction.
   * @throws CloneNotSupportedException If cloning is not supported.
   * @throws IllegalArgumentException    If the provided arguments are invalid or out of range.
   */
  void colorCorrect(String inputImageName, String outputImageName, double splitPercentage)
          throws CloneNotSupportedException, IllegalArgumentException;

  /**
   * Adjusts the levels of the input image and saves the result as a new image.
   *
   * @param black            The black level for adjustment.
   * @param mid              The mid level for adjustment.
   * @param white            The white level for adjustment.
   * @param inputImageName   The name of the input image.
   * @param outputImageName  The name of the output image
   *                         where the adjusted image will be saved.
   * @throws IllegalArgumentException If the level adjustment
   *        parameters are invalid.
   */
  void adjustLevels(int black, int mid, int white, String inputImageName,
                    String outputImageName) throws IllegalArgumentException;

  /**
   * Adjusts the levels of the input image with the specified split
   *        percentage and saves the result as a new image.
   *
   * @param black            The black level for adjustment.
   * @param mid              The mid level for adjustment.
   * @param white            The white level for adjustment.
   * @param inputImageName   The name of the input image.
   * @param outputImageName  The name of the output image where the
   *                         adjusted image will be saved.
   * @param splitPercentage  The percentage of the image to apply level adjustment.
   * @throws CloneNotSupportedException If cloning is not supported.
   * @throws IllegalArgumentException    If the provided arguments are invalid or out of range.
   */
  void adjustLevels(int black, int mid, int white, String inputImageName,
                    String outputImageName, double splitPercentage)
          throws CloneNotSupportedException, IllegalArgumentException;

  /**
   * Applies blur to the input image with the specified split percentage
   *      and saves the result as a new image.
   *
   * @param inputImageName   The name of the input image.
   * @param outputImageName  The name of the output image where the
   *                         blurred image will be saved.
   * @param splitPercentage  The percentage of the image to apply the blur.
   * @throws CloneNotSupportedException If cloning is not supported.
   * @throws IllegalArgumentException    If the provided arguments are invalid or out of range.
   */
  void blur(String inputImageName, String outputImageName, double splitPercentage)
          throws CloneNotSupportedException, IllegalArgumentException;

  /**
   * Applies sharpening to the input image with the specified split
   *        percentage and saves the result as a new image.
   *
   * @param inputImageName   The name of the input image.
   * @param outputImageName  The name of the output image where the
   *                         sharpened image will be saved.
   * @param splitPercentage  The percentage of the image to apply the sharpening.
   * @throws CloneNotSupportedException If cloning is not supported.
   * @throws IllegalArgumentException    If the provided arguments are invalid or out of range.
   */
  void sharpen(String inputImageName, String outputImageName, double splitPercentage)
          throws CloneNotSupportedException, IllegalArgumentException;

  /**
   * Applies sepia filtering to the input image with the specified split
   * percentage and saves the result as a new image.
   *
   * @param inputImageName   The name of the input image.
   * @param outputImageName  The name of the output image where the
   *                         sepia-filtered image will be saved.
   * @param splitPercentage  The percentage of the image to apply sepia filtering.
   * @throws CloneNotSupportedException If cloning is not supported.
   * @throws IllegalArgumentException    If the provided arguments are invalid or out of range.
   */
  void sepia(String inputImageName, String outputImageName, double splitPercentage)
          throws CloneNotSupportedException, IllegalArgumentException;

  /**
   * Computes luma values for the input image with the specified
   * split percentage and saves the result as a new image.
   *
   * @param inputImageName   The name of the input image.
   * @param outputImageName  The name of the output image where the
   *                         computed luma image will be saved.
   * @param splitPercentage  The percentage of the image to compute luma values.
   * @throws CloneNotSupportedException If cloning is not supported.
   * @throws IllegalArgumentException    If the provided arguments are invalid or out of range.
   */
  void luma(String inputImageName, String outputImageName, double splitPercentage)
          throws CloneNotSupportedException, IllegalArgumentException;

}
