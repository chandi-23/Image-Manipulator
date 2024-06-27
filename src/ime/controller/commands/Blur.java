/**
 * The Blur class implements the IMECommand interface and represents a specific IME (Image
 * Manipulation and Enhancement) command to apply a blur effect to an image and save the result
 * as a new image.
 */

package ime.controller.commands;

import ime.model.IMEFacade;

/**
 * The Blur class is responsible for executing the "blur" command on the model.
 */
public class Blur implements IMECommand {

  protected final String inputImageName;
  protected final String outputImageName;

  /**
   * Constructs a new Blur command with the input and output image names.
   *
   * @param inputImageName  The name of the input image to be blurred.
   * @param outputImageName The name of the output image where the blurred image will be saved.
   */
  public Blur(String inputImageName, String outputImageName) {
    this.inputImageName = inputImageName;
    this.outputImageName = outputImageName;
  }

  /**
   * Executes the "blur" command by invoking the corresponding method in the model.
   *
   * @param model The IMEFacade model on which to apply the blur effect.
   */
  @Override
  public void run(IMEFacade model) throws CloneNotSupportedException {
    model.blur(inputImageName, outputImageName);
  }
}
