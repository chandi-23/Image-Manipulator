/**
 * The Load class implements the IMECommand interface and represents a specific IME (Image
 * Manipulation and Enhancement) command to load an image from a specified file and give it
 * a unique name within the application.
 */

package ime.controller.commands;

import ime.controller.ImageReader;
import ime.controller.ImageReaderFactory;
import ime.controller.ImageReaderFactoryImpl;
import java.io.IOException;
import ime.model.IMEFacade;

/**
 * The Load class is responsible for executing the "load" command on the model.
 */
public class Load implements IMECommand {

  private final String imagePath;
  private final String imageName;

  /**
   * Constructs a new Load command with the file path and the name for the loaded image.
   *
   * @param imagePath The path to the image file to be loaded.
   * @param imageName The unique name to assign to the loaded image within the application.
   */
  public Load(String imagePath, String imageName) {
    this.imagePath = imagePath;
    this.imageName = imageName;
  }

  /**
   * Executes the "load" command by creating an ImageReader, reading the image from the
   * specified file, and loading it into the model with the assigned name.
   *
   * @param model The IMEFacade model on which to perform the image loading operation.
   * @throws IOException If an error occurs during the execution of the "load" command.
   */
  @Override
  public void run(IMEFacade model) throws IOException {
    ImageReaderFactory readerFactory = new ImageReaderFactoryImpl();
    ImageReader imageReader = readerFactory.getImageReader(imagePath);
    model.load(imageReader.readImage(imagePath), imageName);
  }
}
