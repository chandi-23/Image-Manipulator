/**
 * The Value class implements the IMECommand interface and represents a specific IME (Image
 * Manipulation and Enhancement) command to apply a value adjustment to an image and save
 * it as a new image.
 */

package ime.controller.commands;

import ime.model.IMEFacade;

/**
 * The Value class is responsible for executing the "value" command on the model.
 */
public class Value implements IMECommand {

  private final String inputImageName;
  private final String outputImageName;

  /**
   * Constructs a new Value command with the input and output image names.
   *
   * @param inputImageName  The name of the input image.
   * @param outputImageName The name of the output image where the value-adjusted image
   *                        will be saved.
   */
  public Value(String inputImageName, String outputImageName) {
    this.inputImageName = inputImageName;
    this.outputImageName = outputImageName;
  }

  /**
   * Executes the "value" command by invoking the corresponding method in the model.
   *
   * @param model The IMEFacade model on which to apply the value adjustment and save the result.
   */
  @Override
  public void run(IMEFacade model) {
    model.value(inputImageName, outputImageName);
  }

}
