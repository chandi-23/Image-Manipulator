/**
 * The RGBSplit class implements the IMECommand interface and represents a specific IME (Image
 * Manipulation and Enhancement) command to split an image into its red, green, and
 * blue components and save them as separate images.
 */

package ime.controller.commands;

import ime.model.IMEFacade;

/**
 * The RGBSplit class is responsible for executing the "rgb-split" command on the model.
 */
public class RGBSplit implements IMECommand {

  private final String inputImageName;
  private final String redComponentOutputImageName;
  private final String greenComponentOutputImageName;
  private final String blueComponentOutputImageName;

  /**
   * Constructs a new RGBSplit command with the name of the input image and the names for the
   * output images of the red, green, and blue components.
   *
   * @param inputImageName              The name of the input image to be split.
   * @param redComponentOutputImageName The name of the output image for the red component.
   * @param greenComponentOutputImageName The name of the output image for the green component.
   * @param blueComponentOutputImageName The name of the output image for the blue component.
   */
  public RGBSplit(String inputImageName, String redComponentOutputImageName,
      String greenComponentOutputImageName, String blueComponentOutputImageName) {
    this.inputImageName = inputImageName;
    this.redComponentOutputImageName = redComponentOutputImageName;
    this.greenComponentOutputImageName = greenComponentOutputImageName;
    this.blueComponentOutputImageName = blueComponentOutputImageName;
  }

  /**
   * Executes the "rgb-split" command by invoking the corresponding method in the model.
   *
   * @param model The IMEFacade model on which to perform the RGB component splitting.
   */
  @Override
  public void run(IMEFacade model) {
    model.rgbSplit(inputImageName, redComponentOutputImageName, greenComponentOutputImageName,
        blueComponentOutputImageName);
  }
}
