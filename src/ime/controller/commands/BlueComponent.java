/**
 * The BlueComponent class implements the IMECommand interface and represents a specific
 * IME (Image Manipulation and Enhancement) command to extract the blue component
 * of an image and save it as a new image.
 */

package ime.controller.commands;

import ime.model.IMEFacade;

/**
 * The BlueComponent class is responsible for executing the "blue-component" command on the model.
 */
public class BlueComponent implements IMECommand {

  private final String inputImageName;
  private final String outputImageName;

  /**
   * Constructs a new BlueComponent command with the input and output image names.
   *
   * @param inputImageName  The name of the input image.
   * @param outputImageName The name of the output image where the blue component will be saved.
   */
  public BlueComponent(String inputImageName, String outputImageName) {
    this.inputImageName = inputImageName;
    this.outputImageName = outputImageName;
  }

  /**
   * Executes the "blue-component" command by invoking the corresponding method in the model.
   *
   * @param model The IMEFacade model on which to perform the blue component extraction.
   */
  @Override
  public void run(IMEFacade model) {
    model.blueComponent(inputImageName, outputImageName);
  }

}
