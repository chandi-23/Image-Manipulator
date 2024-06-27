/**
 * The Controller interface defines a contract for classes that act as controllers
 * in the MVC (Model-View-Controller) architectural pattern. Controllers are responsible
 * for handling user input and interacting with the model.
 */

package ime.controller;

import java.io.IOException;
import ime.model.IMEFacade;

/**
 * The Controller interface specifies a method for performing a specific action or operation
 * in response to user input, often in the context of an MVC application.
 */
public interface Controller {

  /**
   * Performs a specific action or operation using the provided model.
   *
   * @param model The model (IMEFacade) with which the controller interacts.
   * @throws IOException If there is an error during the operation or interaction with the model.
   */
  void goAhead(IMEFacade model) throws IOException;
}
