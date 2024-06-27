/**
 * The ColorCorrect class implements the IMECommand interface and represents a command
 * for color correction on an input image, producing an output image.
 */

package ime.controller.commands;

import ime.model.IMEFacade;
import ime.model.MIMEFacade;

import java.io.IOException;

/**
 * The ColorCorrect class implements the IMECommand interface and represents a command
 * for color correction on an input image, producing an output image.
 */
public class ColorCorrect implements IMECommand {

  protected final String inputImageName;
  protected final String outputImageName;

  /**
   * Constructs a ColorCorrect object with the specified input and output image names.
   *
   * @param inputImageName  The name of the input image for color correction.
   * @param outputImageName The name of the output image after color correction.
   */
  public ColorCorrect(String inputImageName, String outputImageName) {
    this.inputImageName = inputImageName;
    this.outputImageName = outputImageName;
  }

  /**
   * Runs the color correction on the input image using the specified model.
   *
   * @param model The IMEFacade model to perform color correction.
   * @throws IOException               If an I/O error occurs during color correction.
   * @throws CloneNotSupportedException If cloning is not supported by the model.
   */
  @Override
  public void run(IMEFacade model) throws IOException, CloneNotSupportedException {
    if (model instanceof MIMEFacade) {
      MIMEFacade model1 = (MIMEFacade) model;
      model1.colorCorrect(inputImageName, outputImageName);
    }
  }
}
