package ime.controller.commands;

import ime.model.IMEFacade;
import ime.model.MIMEFacade;
import java.util.Map;

/**
 * The {@code SepiaWithParams} class extends {@code Sepia} and represents a specific
 * implementation of the sepia command with additional parameters. It is used in the
 * MIMECommandRepository to handle the sepia command with optional parameters such as 'split.'
 */
public class SepiaWithParams extends Sepia {

  private final Map<String, String> params;

  /**
   * Constructs a new {@code SepiaWithParams} command with the input and output image names
   * and additional parameters.
   *
   * @param inputImageName  The name of the input image to apply the sepia effect.
   * @param outputImageName The name of the output image where the sepia-toned image
   *                        will be saved.
   * @param params          The map of parameters for customizing the sepia effect.
   */
  public SepiaWithParams(String inputImageName, String outputImageName,
      Map<String, String> params) {
    super(inputImageName, outputImageName);
    this.params = params;
  }

  /**
   * Runs the sepia effect on the input image using the specified model.
   * If the "split" parameter is present, it performs a split sepia effect using the provided
   * split value; otherwise, it falls back to the default sepia effect behavior.
   *
   * @param model The IMEFacade model to apply the sepia effect.
   * @throws CloneNotSupportedException If cloning is not supported by the model.
   */
  @Override
  public void run(IMEFacade model) throws CloneNotSupportedException {
    if (params.containsKey("split")) {
      MIMEFacade model1 = (MIMEFacade) model;
      model1.sepia(inputImageName, outputImageName, Double.parseDouble(params.get("split")));
    } else {
      super.run(model);
    }
  }

}
