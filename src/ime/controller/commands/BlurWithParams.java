package ime.controller.commands;

import ime.model.IMEFacade;
import ime.model.MIMEFacade;
import java.util.Map;

/**
 * The {@code BlurWithParams} class extends {@code Blur} and adds support for additional
 * parameters. It is designed to be used with the MIMECommandRepository for handling the blur
 * command with optional parameters.
 */
public class BlurWithParams extends Blur {

  private final Map<String, String> params;

  /**
   * Constructs a new {@code BlurWithParams} command with the input and output image names and
   * additional parameters.
   *
   * @param inputImageName  The name of the input image to be blurred.
   * @param outputImageName The name of the output image where the blurred image will be saved.
   * @param params          Additional parameters for the blur command, such as 'split.'
   */
  public BlurWithParams(String inputImageName, String outputImageName, Map<String, String> params) {
    super(inputImageName, outputImageName);
    this.params = params;
  }

  /**
   * Runs the blur command with optional parameters. If the 'split' parameter is present, it
   * invokes the blur method on a MIMEFacade instance; otherwise, it falls back to the default
   * blur behavior.
   *
   * @param model The IMEFacade model used for image processing.
   * @throws CloneNotSupportedException If cloning of the model is not supported.
   */
  @Override
  public void run(IMEFacade model) throws CloneNotSupportedException {
    if (params.containsKey("split")) {
      MIMEFacade model1 = (MIMEFacade) model;
      model1.blur(inputImageName, outputImageName, Double.parseDouble(params.get("split")));
    } else {
      super.run(model);
    }
  }
}
