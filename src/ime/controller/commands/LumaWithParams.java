/**
 * The LumaWithParams class extends the Luma command, providing a specific
 * luma computation command with additional parameters for customizable luma operations.
 * <p>
 * Designed to be used within the IME framework for image processing.
 * </p>
 *
 * @see Luma
 */

package ime.controller.commands;

import ime.model.IMEFacade;
import ime.model.MIMEFacade;
import java.util.Map;

/**
 * The LumaWithParams class extends the Luma command, providing a specific
 * luma computation command with additional parameters for customizable luma operations.
 * This class is designed to be used within the IME framework for image processing.
 *
 * @see Luma
 */
public class LumaWithParams extends Luma {

  /**
   * The parameters for customizing the luma computation operation.
   */
  private final Map<String, String> params;

  /**
   * Constructs a new LumaWithParams command with the input and output image names
   * and additional parameters.
   *
   * @param inputImageName  The name of the input image to compute the luma.
   * @param outputImageName The name of the output image where the computed luma image
   *                        will be saved.
   * @param params          The map of parameters for customizing the luma computation operation.
   */
  public LumaWithParams(String inputImageName, String outputImageName, Map<String, String> params) {
    super(inputImageName, outputImageName);
    this.params = params;
  }

  /**
   * Runs the luma computation on the input image using the specified model.
   * If the "split" parameter is present, it performs a split luma computation using the provided
   * split value; otherwise, it falls back to the default luma computation behavior.
   *
   * @param model The IMEFacade model to perform the luma computation operation.
   * @throws CloneNotSupportedException If cloning is not supported by the model.
   */
  @Override
  public void run(IMEFacade model) throws CloneNotSupportedException {
    if (params.containsKey("split")) {
      MIMEFacade model1 = (MIMEFacade) model;
      model1.luma(inputImageName, outputImageName, Double.parseDouble(params.get("split")));
    }
    else {
      super.run(model);
    }
  }

}
