/**
 * The IMEFacade interface represents a facade for managing various image processing
 * operations and interactions with image data.
 */

package ime.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The IMEFacade interface defines the contract for an application facade responsible
 * for managing image processing operations and interactions with image data.
 */
public interface IMEFacade {

  /**
   * Loads image data from an InputStream and associates it with a given image name.
   *
   * @param imageData The InputStream containing image data to be loaded.
   * @param imageName The name to associate with the loaded image.
   */
  void load(InputStream imageData, String imageName);

  /**
   * Saves an image with the specified name to an OutputStream.
   *
   * @param imageName The name of the image to save.
   * @return An OutputStream for writing the image data.
   * @throws IOException If an I/O error occurs during the saving process.
   */
  OutputStream save(String imageName) throws IOException;

  // Methods for image processing operations:
  /**
   * Applies the red component extraction operation to the input image
   * and saves the result to the output image.
   *
   * @param inputImageName  The name of the input image.
   * @param outputImageName The name of the output image where the
   *                        red component will be saved.
   */
  void redComponent(String inputImageName, String outputImageName);

  /**
   * Applies the green component extraction operation to the input image and
   * saves the result to the output image.
   *
   * @param inputImageName  The name of the input image.
   * @param outputImageName The name of the output image where the
   *                        green component will be saved.
   */
  void greenComponent(String inputImageName, String outputImageName);

  /**
   * Applies the blue component extraction operation to the input image and
   * saves the result to the output image.
   *
   * @param inputImageName  The name of the input image.
   * @param outputImageName The name of the output image where the blue
   *                        component will be saved.
   */
  void blueComponent(String inputImageName, String outputImageName);

  /**
   * Applies the value extraction operation to the input image and
   * saves the result to the output image.
   *
   * @param inputImageName  The name of the input image.
   * @param outputImageName The name of the output image where the value will be saved.
   */
  void value(String inputImageName, String outputImageName);

  /**
   * Applies the intensity extraction operation to the input image and saves the result to
   * the output image.
   *
   * @param inputImageName  The name of the input image.
   * @param outputImageName The name of the output image where the intensity will be saved.
   */
  void intensity(String inputImageName, String outputImageName);

  /**
   * Applies the luma extraction operation to the input image and saves the result to
   * the output image.
   *
   * @param inputImageName  The name of the input image.
   * @param outputImageName The name of the output image where the luma will be saved.
   */
  void luma(String inputImageName, String outputImageName);

  /**
   * Flips the input image horizontally and saves the result to the output image.
   *
   * @param inputImageName  The name of the input image.
   * @param outputImageName The name of the output image after horizontal flipping.
   */
  void flipHorizontal(String inputImageName, String outputImageName);

  /**
   * Flips the input image vertically and saves the result to the output image.
   *
   * @param inputImageName  The name of the input image.
   * @param outputImageName The name of the output image after vertical flipping.
   */
  void flipVertical(String inputImageName, String outputImageName);

  /**
   * Adjusts the brightness of the input image and saves the result to the output image.
   *
   * @param increment        The amount by which to adjust the brightness.
   * @param inputImageName   The name of the input image.
   * @param outputImageName  The name of the output image after brightness adjustment.
   */
  void brighten(int increment, String inputImageName, String outputImageName);

  /**
   * Splits the RGB components of the input image and saves them to separate output images.
   *
   * @param inputImageName               The name of the input image.
   * @param redComponentOutputImageName  The name of the output image for the red component.
   * @param greenComponentOutputImageName The name of the output image for the green component.
   * @param blueComponentOutputImageName  The name of the output image for the blue component.
   */
  void rgbSplit(String inputImageName, String redComponentOutputImageName,
      String greenComponentOutputImageName, String blueComponentOutputImageName);

  /**
   * Combines the RGB components from three input images and saves the result
   *        to the output image.
   *
   * @param redComponentInputImageName   The name of the input image for the red component.
   * @param greenComponentInputImageName The name of the input image for the green component.
   * @param blueComponentInputImageName  The name of the input image for the blue component.
   * @param outputImageName              The name of the output image after RGB
   *                                     component combination.
   */
  void rgbCombine(String redComponentInputImageName, String greenComponentInputImageName,
      String blueComponentInputImageName, String outputImageName);

  /**
   * Applies a blur operation to the input image and saves the result to the output image.
   *
   * @param inputImageName  The name of the input image.
   * @param outputImageName The name of the output image after the blur operation.
   */

  void blur(String inputImageName, String outputImageName);


  /**
   * Applies a sharpening operation to the input image and saves the result to the output image.
   *
   * @param inputImageName  The name of the input image.
   * @param outputImageName The name of the output image after the sharpening operation.
   */
  void sharpen(String inputImageName, String outputImageName);

  /**
   * Applies a sepia filter to the input image and saves the result to the output image.
   *
   * @param inputImageName  The name of the input image.
   * @param outputImageName The name of the output image after applying the sepia filter.
   */
  void sepia(String inputImageName, String outputImageName);

  int[][][] getRawImageData(String imageName);
}
