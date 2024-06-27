/**
 * The VerticalFlip class implements the IMECommand interface and represents a specific
 * IME (Image Manipulation and Enhancement) command to perform a vertical flip on an
 * image and save it as a new image.
 */

package ime.controller.commands;

import ime.model.IMEFacade;

/**
 * The VerticalFlip class is responsible for executing the "vertical-flip" command on the model.
 */
public class VerticalFlip implements IMECommand {

  private final String inputImageName;
  private final String outputImageName;

  /**
   * Constructs a new VerticalFlip command with the input and output image names.
   *
   * @param inputImageName  The name of the input image to flip vertically.
   * @param outputImageName The name of the output image where the vertically flipped
   *                        image will be saved.
   */
  public VerticalFlip(String inputImageName, String outputImageName) {
    this.inputImageName = inputImageName;
    this.outputImageName = outputImageName;
  }

  /**
   * Executes the "vertical-flip" command by invoking the corresponding method in the model.
   *
   * @param model The IMEFacade model on which to perform a vertical flip and save the result.
   */
  @Override
  public void run(IMEFacade model) {
    model.flipVertical(inputImageName, outputImageName);
  }

}
