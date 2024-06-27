/**
 * The Histogram class implements the IMECommand interface and represents a command
 * for generating a histogram of an input image and saving the result as a new image.
 */

package ime.controller.commands;

import ime.model.IMEFacade;
import ime.model.MIMEFacade;

import java.io.IOException;

/**
 * The Histogram class implements the IMECommand interface and represents a command
 * for generating a histogram of an input image and saving the result as a new image.
 */
public class Histogram implements IMECommand {

  private final String inputImageName;
  private final String outputImageName;

  /**
   * Constructs a new Histogram command with the input and output image names.
   *
   * @param inputImageName   The name of the input image to generate a histogram.
   * @param outputImageName  The name of the output image where the histogram will be saved.
   */
  public Histogram(String inputImageName, String outputImageName) {
    this.inputImageName = inputImageName;
    this.outputImageName = outputImageName;
  }

  /**
   * Runs the histogram generation on the input image using the specified model.
   * If the model is an instance of MIMEFacadeImpl, it invokes the histogram method.
   *
   * @param model The IMEFacade model to perform histogram generation.
   * @throws IOException If an I/O error occurs during histogram generation.
   */
  @Override
  public void run(IMEFacade model) throws IOException {
    if (model instanceof MIMEFacade) {
      MIMEFacade model1 = (MIMEFacade) model;
      model1.histogram(inputImageName, outputImageName);
    }
  }
}
