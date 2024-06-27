/**
 * The CommandController class implements the Controller interface and acts as a
 * controller in the MVC (Model-View-Controller) architectural pattern. It processes
 * user input, executes IME (Image Manipulation and Enhancement) commands, and interacts
 * with the model.
 */

package ime.controller;

import ime.controller.commands.IMECommand;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;
import ime.model.IMEFacade;

/**
 * The CommandController class is responsible for processing user input, executing
 *        IME commands, and managing interactions with the model.
 */
public class CommandController implements Controller {

  private final Readable in;
  private final Appendable out;
  private final CommandRepository commandRepository;

  /**
   * Constructs a new CommandController with the specified input and output sources and
   *      a command repository.
   *
   * @param in                The Readable input source for reading user commands.
   * @param out               The Appendable output source for displaying operation results.
   * @param commandRepository The repository of IME commands.
   */
  public CommandController(Readable in, Appendable out, CommandRepository commandRepository) {
    this.in = in;
    this.out = out;
    this.commandRepository = commandRepository;
  }

  /**
   * Executes IME commands based on user input and interacts with the model to
   *      perform image operations.
   *
   * @param model The IMEFacade model to interact with.
   * @throws IOException If there is an error during the execution of IME commands or
   *        interaction with the model.
   */
  @Override
  public void goAhead(IMEFacade model) throws IOException {

    Objects.requireNonNull(model);
    Scanner sc = new Scanner(in);

    while (sc.hasNext()) {
      try {
        IMECommand c;
        String in = sc.next();
        if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
          return;
        }
        Function<Scanner, IMECommand> cmd =
            commandRepository.get(in);
        if (cmd == null) {
          throw new IllegalArgumentException("Please input a command that is supported");
        } else {
          c = cmd.apply(sc);
          c.run(model);
          out.append("Operation completed: ").append(in).append(System.lineSeparator());
        }
      } catch (IOException | IllegalArgumentException | NoSuchElementException
               | CloneNotSupportedException e) {
        out.append(e.getMessage()).append(System.lineSeparator());
      }
    }
  }
}
