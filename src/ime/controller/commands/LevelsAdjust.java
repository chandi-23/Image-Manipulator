/**
 * The LevelsAdjust class implements the IMECommand interface and represents a specific IME
 * (Image Manipulation and Enhancement) command to adjust the levels of an image by modifying the
 * black, mid, and white points.
 */

package ime.controller.commands;

import ime.model.IMEFacade;
import ime.model.MIMEFacade;
import java.io.IOException;

/**
 * The LevelsAdjust class implements the IMECommand interface and represents a specific IME
 * (Image Manipulation and Enhancement) command to adjust the levels of an image by modifying the
 * black, mid, and white points.
 */
public class LevelsAdjust implements IMECommand {

  /**
   * The black level for adjustment.
   */
  protected final int black;

  /**
   * The mid level for adjustment.
   */
  protected final int mid;

  /**
   * The white level for adjustment.
   */
  protected final int white;

  /**
   * The name of the input image to generate a histogram.
   */
  protected final String inputImageName;

  /**
   * The name of the output image where the histogram will be saved.
   */
  protected final String outputImageName;

  /**
   /**
   * Constructs a new LevelsAdjust command with the black, mid, and white levels,
   * and the input/output image names.
   *
   * @param black            The black level for adjustment.
   * @param mid              The mid level for adjustment.
   * @param white            The white level for adjustment.
   * @param inputImageName   The name of the input image for levels adjustment.
   * @param outputImageName  The name of the output image where the adjusted levels
   *                         image will be saved.
   */
  public LevelsAdjust(int black, int mid, int white, String inputImageName,
      String outputImageName) {
    this.black = black;
    this.mid = mid;
    this.white = white;
    this.inputImageName = inputImageName;
    this.outputImageName = outputImageName;
  }

  /**
   * Runs the levels adjustment on the input image using the specified model.
   * If the model is an instance of MIMEFacade, it invokes the adjustLevels method.
   *
   * @param model The IMEFacade model to perform levels adjustment.
   * @throws IOException               If an I/O error occurs during levels adjustment.
   * @throws CloneNotSupportedException If cloning is not supported by the model.
   * @throws IllegalArgumentException  If the levels values are invalid.
   */
  @Override
  public void run(IMEFacade model) throws IOException, CloneNotSupportedException,
          IllegalArgumentException {
    if (model instanceof MIMEFacade) {
      MIMEFacade model1 = (MIMEFacade) model;
      model1.adjustLevels(black, mid, white, inputImageName, outputImageName);
    }
  }

}
