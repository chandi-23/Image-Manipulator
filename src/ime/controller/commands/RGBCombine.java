/**
 * The RGBCombine class implements the IMECommand interface and represents a specific IME (Image
 * Manipulation and Enhancement) command to combine the red, green, and blue components
 * of three images and save the combined result as a new image.
 */

package ime.controller.commands;

import ime.model.IMEFacade;

/**
 * The RGBCombine class is responsible for executing the "rgb-combine" command on the model.
 */
public class RGBCombine implements IMECommand {

  private final String outputImageName;
  private final String redComponentInputImageName;
  private final String greenComponentInputImageName;
  private final String blueComponentInputImageName;

  /**
   * Constructs a new RGBCombine command with the names of the input images and the name of the
   * output image where the combined result will be saved.
   *
   * @param outputImageName              The name of the output image to save the combined result.
   * @param redComponentInputImageName   The name of the input image for the red component.
   * @param greenComponentInputImageName The name of the input image for the green component.
   * @param blueComponentInputImageName  The name of the input image for the blue component.
   */
  public RGBCombine(String outputImageName, String redComponentInputImageName,
      String greenComponentInputImageName, String blueComponentInputImageName) {
    this.outputImageName = outputImageName;
    this.redComponentInputImageName = redComponentInputImageName;
    this.greenComponentInputImageName = greenComponentInputImageName;
    this.blueComponentInputImageName = blueComponentInputImageName;
  }

  /**
   * Executes the "rgb-combine" command by invoking the corresponding method in the model.
   *
   * @param model The IMEFacade model on which to perform the RGB component combination.
   */
  @Override
  public void run(IMEFacade model) {
    model.rgbCombine(redComponentInputImageName, greenComponentInputImageName,
        blueComponentInputImageName, outputImageName);
  }
}
