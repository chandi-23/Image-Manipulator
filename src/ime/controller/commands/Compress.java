/**
 * The Compress class implements the IMECommand interface and represents a command
 * for compressing an input image by a specified percentage and saving the result as
 * a new image.
 */

package ime.controller.commands;

import ime.model.IMEFacade;
import ime.model.MIMEFacade;
import java.io.IOException;

/**
 * The Compress class implements the IMECommand interface and represents a command
 * for compressing an input image by a specified percentage and saving the result as
 * a new image.
 */
public class Compress implements IMECommand {

  /**
   * The compression percentage for the image.
   */
  private final double percentage;

  /**
   * The name of the input image to be compressed.
   */

  private final String inputImageName;

  /**
   * The name of the output image where the compressed image will be saved.
   */
  private final String outputImageName;

  /**
   * Constructs a new Compress command with the compression percentage and
   * input/output image names.
   *
   * @param percentage       The percentage by which to compress the image.
   * @param inputImageName   The name of the input image to be compressed.
   * @param outputImageName  The name of the output image where the compressed
   *                         image will be saved.
   */
  public Compress(double percentage, String inputImageName, String outputImageName) {
    this.percentage = percentage;
    this.inputImageName = inputImageName;
    this.outputImageName = outputImageName;
  }

  /**
   * Runs the compression on the input image using the specified model.
   * If the model is an instance of MIMEFacade, it invokes the compress method.
   *
   * @param model The IMEFacade model to perform the compression.
   * @throws IOException               If an I/O error occurs during compression.
   * @throws IllegalArgumentException  If the compression percentage is invalid.
   */
  @Override
  public void run(IMEFacade model) throws IOException, IllegalArgumentException {
    if (model instanceof MIMEFacade) {
      MIMEFacade model1 = (MIMEFacade) model;
      model1.compress(percentage, inputImageName, outputImageName);
    }
  }
}
