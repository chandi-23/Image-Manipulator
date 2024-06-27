/**
 * The Intensity class implements the IMECommand interface and represents a specific IME (Image
 * Manipulation and Enhancement) command to adjust the intensity of an image and save the result
 * as a new image.
 */

package ime.controller.commands;

import ime.model.IMEFacade;

/**
 * The Intensity class is responsible for executing the "intensity" command on the model.
 */
public class Intensity implements IMECommand {

  private final String inputImageName;
  private final String outputImageName;

  /**
   * Constructs a new Intensity command with the input and output image names.
   *
   * @param inputImageName  The name of the input image to adjust the intensity.
   * @param outputImageName The name of the output image where the adjusted image will be saved.
   */
  public Intensity(String inputImageName, String outputImageName) {
    this.inputImageName = inputImageName;
    this.outputImageName = outputImageName;
  }

  /**
   * Executes the "intensity" command by invoking the corresponding method in the model.
   *
   * @param model The IMEFacade model on which to perform the intensity adjustment.
   */
  @Override
  public void run(IMEFacade model) {
    model.intensity(inputImageName, outputImageName);
  }

}
