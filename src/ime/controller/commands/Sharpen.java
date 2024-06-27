/**
 * The Sharpen class implements the IMECommand interface and represents a specific IME (Image
 * Manipulation and Enhancement) command to apply a sharpening filter to an image and
 * save it as a new image.
 */

package ime.controller.commands;

import ime.model.IMEFacade;

/**
 * The Sharpen class is responsible for executing the "sharpen" command on the model.
 */
public class Sharpen implements IMECommand {

  protected final String inputImageName;
  protected final String outputImageName;

  /**
   * Constructs a new Sharpen command with the input and output image names.
   *
   * @param inputImageName  The name of the input image.
   * @param outputImageName The name of the output image where the sharpened image will be saved.
   */
  public Sharpen(String inputImageName, String outputImageName) {
    this.inputImageName = inputImageName;
    this.outputImageName = outputImageName;
  }

  /**
   * Executes the "sharpen" command by invoking the corresponding method in the model.
   *
   * @param model The IMEFacade model on which to apply the sharpening filter and save the result.
   */
  @Override
  public void run(IMEFacade model) throws CloneNotSupportedException {
    model.sharpen(inputImageName, outputImageName);
  }
}
