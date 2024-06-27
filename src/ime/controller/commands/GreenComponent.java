/**
 * The GreenComponent class implements the IMECommand interface and represents a specific
 * IME (Image Manipulation and Enhancement) command to extract the green component of
 * an image and save it as a new image.
 */

package ime.controller.commands;

import ime.model.IMEFacade;

/**
 * The GreenComponent class is responsible for executing the "green-component" command on the model.
 */
public class GreenComponent implements IMECommand {

  private final String inputImageName;
  private final String outputImageName;

  /**
   * Constructs a new GreenComponent command with the input and output image names.
   *
   * @param inputImageName  The name of the input image.
   * @param outputImageName The name of the output image where the green component will be saved.
   */
  public GreenComponent(String inputImageName, String outputImageName) {
    this.inputImageName = inputImageName;
    this.outputImageName = outputImageName;
  }

  /**
   * Executes the "green-component" command by invoking the corresponding method in the model.
   *
   * @param model The IMEFacade model on which to perform the green component extraction.
   */
  @Override
  public void run(IMEFacade model) {
    model.greenComponent(inputImageName, outputImageName);
  }
}
