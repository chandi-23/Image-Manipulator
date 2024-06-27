/**
 * The RunScript class implements the IMECommand interface and represents a specific
 * IME (Image Manipulation and Enhancement) command to execute a series of IME commands stored
 * in a script file.
 */

package ime.controller.commands;

import ime.controller.Controller;
import ime.controller.CommandController;
import ime.controller.FileUtil;
import ime.controller.MIMECommandRepository;
import java.io.IOException;
import java.io.StringReader;
import java.util.Objects;
import ime.model.IMEFacade;

/**
 * The RunScript class is responsible for executing the "run" command on the model to
 * execute a script containing a series of IME commands.
 */
public class RunScript implements IMECommand {

  private final String scriptFilePath;

  /**
   * Constructs a new RunScript command with the path to the script file.
   *
   * @param scriptFileName The path to the script file containing IME commands to execute.
   */
  public RunScript(String scriptFileName) {
    this.scriptFilePath = scriptFileName;
  }

  /**
   * Executes the "run" command by reading and processing the script file, and executing
   * the contained IME commands.
   *
   * @param model The IMEFacade model on which to execute the series of IME commands
   *              from the script.
   * @throws IOException If an error occurs during the execution of the "run" command.
   */
  @Override
  public void run(IMEFacade model) throws IOException {
    Objects.requireNonNull(model);
    String commands = FileUtil.cleanFileRead(scriptFilePath);
    Controller controller = new CommandController(new StringReader(commands), System.out,
        new MIMECommandRepository());
    controller.goAhead(model);
  }
}
