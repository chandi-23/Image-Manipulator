/**
 * The LevelsAdjustWithParams class extends the LevelsAdjust command, providing a specific
 * levels adjustment command with additional parameters for customizable
 * levels adjustment operations.
 * <p>
 * Designed to be used within the IME framework for image processing.
 * </p>
 *
 * @see LevelsAdjust
 */

package ime.controller.commands;

import ime.model.IMEFacade;
import ime.model.MIMEFacade;
import java.io.IOException;
import java.util.Map;


/**
 * The LevelsAdjustWithParams class extends the LevelsAdjust command, providing a specific
 * levels adjustment command with additional parameters for customizable
 * levels adjustment operations.
 * This class is designed to be used within the IME framework for image processing.
 *
 * @see LevelsAdjust
 */
public class LevelsAdjustWithParams extends LevelsAdjust {

  /**
   * The parameters for customizing the levels adjustment operation.
   */
  private final Map<String, String> params;


  /**
   * Constructs a new LevelsAdjustWithParams command with the black, mid, and white levels,
   * input/output image names, and additional parameters.
   *
   * @param black            The black level for adjustment.
   * @param mid              The mid level for adjustment.
   * @param white            The white level for adjustment.
   * @param inputImageName   The name of the input image for levels adjustment.
   * @param outputImageName  The name of the output image where the adjusted levels
   *                         image will be saved.
   * @param params           The map of parameters for customizing the levels adjustment operation.
   */
  public LevelsAdjustWithParams(int black, int mid, int white, String inputImageName,
      String outputImageName, Map<String, String> params) {
    super(black, mid, white, inputImageName, outputImageName);
    this.params = params;
  }

  /**
   * Runs the levels adjustment operation on the input image using the specified model.
   * If the "split" parameter is present, it performs a split levels adjustment using the provided
   * split value; otherwise, it falls back to the default levels adjustment behavior.
   *
   * @param model The IMEFacade model to perform the levels adjustment operation.
   * @throws IOException               If an I/O error occurs during levels adjustment.
   * @throws CloneNotSupportedException If cloning is not supported by the model.
   * @throws IllegalArgumentException  If the levels values are invalid.
   */
  @Override
  public void run(IMEFacade model) throws IOException, CloneNotSupportedException,
          IllegalArgumentException {
    if (params.containsKey("split")) {
      MIMEFacade model1 = (MIMEFacade) model;
      model1.adjustLevels(black, mid, white, inputImageName, outputImageName,
              Double.parseDouble(params.get("split")));
    }
    else {
      super.run(model);
    }
  }

}
