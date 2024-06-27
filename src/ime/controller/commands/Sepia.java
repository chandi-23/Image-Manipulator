/**
 * The Sepia class implements the IMECommand interface and represents a specific IME (Image
 * Manipulation and Enhancement) command to apply a sepia filter to an image and save
 * it as a new image.
 */

package ime.controller.commands;

import ime.model.IMEFacade;

/**
 * The Sepia class is responsible for executing the "sepia" command on the model.
 */
public class Sepia implements IMECommand {

  protected final String inputImageName;
  protected final String outputImageName;

  /**
   * Constructs a new Sepia command with the input and output image names.
   *
   * @param inputImageName  The name of the input image.
   * @param outputImageName The name of the output image where the sepia-filtered image will
   *                        be saved.
   */
  public Sepia(String inputImageName, String outputImageName) {
    this.inputImageName = inputImageName;
    this.outputImageName = outputImageName;
  }

  /**
   * Executes the "sepia" command by invoking the corresponding method in the model.
   *
   * @param model The IMEFacade model on which to apply the sepia filter and save the result.
   */
  @Override
  public void run(IMEFacade model) throws CloneNotSupportedException {
    model.sepia(inputImageName, outputImageName);
  }

}
