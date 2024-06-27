/**
 * The RedComponent class implements the IMECommand interface and represents a specific
 * IME (Image Manipulation and Enhancement) command to extract the red component of an
 * image and save it as a new image.
 */

package ime.controller.commands;

import ime.model.IMEFacade;

/**
 * The RedComponent class is responsible for executing the "red-component" command on the model.
 */
public class RedComponent implements IMECommand {

  private final String inputImageName;
  private final String outputImageName;

  /**
   * Constructs a new RedComponent command with the input and output image names.
   *
   * @param inputImageName  The name of the input image.
   * @param outputImageName The name of the output image where the red component will be saved.
   */
  public RedComponent(String inputImageName, String outputImageName) {
    this.inputImageName = inputImageName;
    this.outputImageName = outputImageName;
  }

  /**
   * Executes the "red-component" command by invoking the corresponding method in the model.
   *
   * @param model The IMEFacade model on which to perform the red component extraction.
   */
  @Override
  public void run(IMEFacade model) {
    model.redComponent(inputImageName, outputImageName);
  }
}
