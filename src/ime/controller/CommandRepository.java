/**
 * The CommandRepository interface defines a contract for classes that serve as repositories of IME
 * (Image Manipulation and Enhancement) commands. It provides methods for retrieving and associating
 * command factory functions based on command names.
 */

package ime.controller;

import ime.controller.commands.IMECommand;
import java.util.Scanner;
import java.util.function.Function;

/**
 * The CommandRepository interface specifies methods for managing a collection of IME commands
 * and their associated factory functions.
 */
public interface CommandRepository {

  /**
   * Retrieves the factory function associated with the specified command name.
   *
   * @param commandName The name of the IME command for which to get the factory function.
   * @return The factory function for creating the IMECommand.
   */
  Function<Scanner, IMECommand> get(String commandName);

  /**
   * Associates a factory function with a specific command name in the repository.
   *
   * @param commandName     The name of the IME command to associate with the factory function.
   * @param commandMapping The factory function for creating the IMECommand.
   */
  void put(String commandName, Function<Scanner, IMECommand> commandMapping);
}
