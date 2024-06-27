/**
 * The Save class implements the IMECommand interface and represents a specific IME (Image
 * Manipulation and Enhancement) command to save an image using an appropriate ImageWriter
 * based on the image file format.
 */

package ime.controller.commands;

import ime.controller.ImageWriter;
import ime.controller.ImageWriterFactory;
import ime.controller.ImageWriterFactoryImpl;
import java.io.IOException;
import ime.model.IMEFacade;

/**
 * The Save class is responsible for executing the "save" command on the model.
 */
public class Save implements IMECommand {

  private final String imagePath;
  private final String imageName;

  /**
   * Constructs a new Save command with the path to the image file and the name for the saved image.
   *
   * @param imagePath The path to the image file to save.
   * @param imageName The name for the saved image.
   */
  public Save(String imagePath, String imageName) {
    this.imagePath = imagePath;
    this.imageName = imageName;
  }

  /**
   * Executes the "save" command by selecting an appropriate ImageWriter based on the image
   * file format, and saving the image with the specified name.
   *
   * @param model The IMEFacade model on which to execute the "save" command.
   * @throws IOException If an error occurs during the execution of the "save" command.
   */
  @Override
  public void run(IMEFacade model) throws IOException {
    ImageWriterFactory writerFactory = new ImageWriterFactoryImpl();
    ImageWriter imageWriter = writerFactory.getImageWriter(imagePath);
    imageWriter.writeImage(model.save(imageName), imagePath);
  }
}
