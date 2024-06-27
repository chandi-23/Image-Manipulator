/**
 * The Luma class implements the IMECommand interface and represents a specific IME (Image
 * Manipulation and Enhancement) command to compute the luma (brightness) of an image and
 * save the result as a new image.
 */

package ime.controller.commands;

import ime.model.IMEFacade;

/**
 * The Luma class is responsible for executing the "luma" command on the model.
 */
public class Luma implements IMECommand {

  protected final String inputImageName;
  protected final String outputImageName;

  /**
   * Constructs a new Luma command with the input and output image names.
   *
   * @param inputImageName  The name of the input image to compute the luma.
   * @param outputImageName The name of the output image where the computed
   *                        luma image will be saved.
   */
  public Luma(String inputImageName, String outputImageName) {
    this.inputImageName = inputImageName;
    this.outputImageName = outputImageName;
  }

  /**
   * Executes the "luma" command by invoking the corresponding method in the model.
   *
   * @param model The IMEFacade model on which to compute the luma of the input image.
   */
  @Override
  public void run(IMEFacade model) throws CloneNotSupportedException {
    model.luma(inputImageName, outputImageName);
  }

}
