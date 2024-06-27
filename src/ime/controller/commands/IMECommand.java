/**
 * The IMECommand interface represents a contract for specific image manipulation and enhancement
 * commands in the IME (Image Manipulation and Enhancement) application. Classes that
 * implement this interface are responsible for defining and executing a specific image
 * manipulation operation.
 */

package ime.controller.commands;

import java.io.IOException;
import ime.model.IMEFacade;

/**
 * This interface defines a single method, run, that takes an IMEFacade model as a parameter and
 * performs the corresponding image manipulation operation. Implementing classes are expected
 * to provide the implementation of this method based on the specific command they represent.
 */
public interface IMECommand {

  /**
   * Executes the image manipulation operation using the provided IMEFacade model.
   *
   * @param model The IMEFacade model on which the image manipulation operation is performed.
   * @throws IOException If an error occurs during the execution of the image manipulation command.
   */
  void run(IMEFacade model) throws IOException, CloneNotSupportedException;

}
