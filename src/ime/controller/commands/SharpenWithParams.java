package ime.controller.commands;

import ime.model.IMEFacade;
import ime.model.MIMEFacade;
import java.util.Map;

/**
 * The {@code SharpenWithParams} class extends the {@code Sharpen} class and represents a specific
 * IME (Image Manipulation and Enhancement) command to apply a sharpening filter to an image with
 * additional parameters.
 */
public class SharpenWithParams extends Sharpen {

  private final Map<String, String> params;

  /**
   * Constructs a new {@code SharpenWithParams} command with the input and output image names
   * and additional parameters.
   *
   * @param inputImageName  The name of the input image to be sharpened.
   * @param outputImageName The name of the output image where the sharpened image
   *                        will be saved.
   * @param params          The map of parameters for customizing the sharpening filter.
   */
  public SharpenWithParams(String inputImageName, String outputImageName,
      Map<String, String> params) {
    super(inputImageName, outputImageName);
    this.params = params;
  }

  /**
   * Runs the sharpening filter on the input image using the specified model.
   * If the "split" parameter is present, it performs a split sharpening operation using the
   * provided split value; otherwise, it falls back to the default sharpening behavior.
   *
   * @param model The IMEFacade model to apply the sharpening filter.
   * @throws CloneNotSupportedException If cloning is not supported by the model.
   */
  @Override
  public void run(IMEFacade model) throws CloneNotSupportedException {
    if (params.containsKey("split")) {
      MIMEFacade model1 = (MIMEFacade) model;
      model1.sharpen(inputImageName, outputImageName, Double.parseDouble(params.get("split")));
    } else {
      super.run(model);
    }
  }

}
