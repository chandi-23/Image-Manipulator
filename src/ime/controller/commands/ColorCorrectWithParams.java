/**
 * The ColorCorrectWithParams class extends the ColorCorrect command, providing a specific
 * color correction command with additional parameters for customizable color correction operations.
 * <p>
 * Designed to be used within the IME framework for image processing.
 * </p>
 *
 * @see ColorCorrect
 */

package ime.controller.commands;

import ime.model.IMEFacade;
import ime.model.MIMEFacade;
import java.io.IOException;
import java.util.Map;

/**
 * The ColorCorrectWithParams class extends the ColorCorrect command, providing a specific
 * color correction command with additional parameters for customizable color correction operations.
 * This class is designed to be used within the IME framework for image processing.
 *
 * @see ColorCorrect
 */
public class ColorCorrectWithParams extends ColorCorrect {

  private final Map<String, String> params;

  /**
   * Constructs a new ColorCorrectWithParams command with the input and output image names
   * and additional parameters.
   *
   * @param inputImageName  The name of the input image for color correction.
   * @param outputImageName The name of the output image after color correction.
   * @param params          The map of parameters for customizing the color correction operation.
   */
  public ColorCorrectWithParams(String inputImageName, String outputImageName,
                                Map<String, String> params) {
    super(inputImageName, outputImageName);
    this.params = params;
  }

  /**
   * Runs the color correction operation on the input image using the specified model.
   * If the "split" parameter is present, it performs a split color correction using the provided
   * split value; otherwise, it falls back to the default color correction behavior.
   *
   * @param model The IMEFacade model to perform the color correction operation.
   * @throws IOException               If an I/O error occurs during color correction.
   * @throws CloneNotSupportedException If cloning is not supported by the model.
   */
  @Override
  public void run(IMEFacade model) throws IOException, CloneNotSupportedException {
    if (params.containsKey("split")) {
      MIMEFacade model1 = (MIMEFacade) model;
      model1.colorCorrect(inputImageName, outputImageName, Double.parseDouble(params.get("split")));
    }
    else {
      super.run(model);
    }
  }

}
