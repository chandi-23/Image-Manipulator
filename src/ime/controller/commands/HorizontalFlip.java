/**
 * The HorizontalFlip class implements the IMECommand interface and represents a specific
 * IME (Image Manipulation and Enhancement) command to horizontally flip an image and
 * save the result as a new image.
 */

package ime.controller.commands;

import ime.model.IMEFacade;

/**
 * The HorizontalFlip class is responsible for executing the "horizontal-flip" command on the model.
 */
public class HorizontalFlip implements IMECommand {

  private final String inputImageName;
  private final String outputImageName;

  /**
   * Constructs a new HorizontalFlip command with the input and output image names.
   *
   * @param inputImageName  The name of the input image to be horizontally flipped.
   * @param outputImageName The name of the output image where the flipped image will be saved.
   */
  public HorizontalFlip(String inputImageName, String outputImageName) {
    this.inputImageName = inputImageName;
    this.outputImageName = outputImageName;
  }

  /**
   * Executes the "horizontal-flip" command by invoking the corresponding method in the model.
   *
   * @param model The IMEFacade model on which to perform the horizontal flip.
   */
  @Override
  public void run(IMEFacade model) {
    model.flipHorizontal(inputImageName, outputImageName);
  }

}
