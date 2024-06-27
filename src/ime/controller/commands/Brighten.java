/**
 * The Brighten class implements the IMECommand interface and represents a specific IME (Image
 * Manipulation and Enhancement) command to brighten an image by adjusting its brightness and saving
 * the result as a new image.
 */

package ime.controller.commands;

import ime.model.IMEFacade;

/**
 * The Brighten class is responsible for executing the "brighten" command on the model.
 */
public class Brighten implements IMECommand {

  private final int increment;
  private final String inputImageName;
  private final String outputImageName;

  /**
   * Constructs a new Brighten command with the brightness adjustment increment and
   *        input/output image names.
   *
   * @param increment        The amount by which to adjust the brightness.
   * @param inputImageName   The name of the input image to be brightened.
   * @param outputImageName  The name of the output image where the brightened image will be saved.
   */
  public Brighten(int increment, String inputImageName, String outputImageName) {
    this.increment = increment;
    this.inputImageName = inputImageName;
    this.outputImageName = outputImageName;
  }

  /**
   * Executes the "brighten" command by invoking the corresponding method in the model.
   *
   * @param model The IMEFacade model on which to apply the brightness adjustment.
   */
  @Override
  public void run(IMEFacade model) {
    model.brighten(increment, inputImageName, outputImageName);
  }

}
