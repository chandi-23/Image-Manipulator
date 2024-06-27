/**
 * The IMECommandRepository class implements the CommandRepository interface and serves
 * as a repository for IME (Image Manipulation and Enhancement) commands. It provides
 * a mapping of command names to functions that create corresponding IMECommand instances.
 */

package ime.controller;

import ime.controller.commands.BlueComponent;
import ime.controller.commands.Blur;
import ime.controller.commands.Brighten;
import ime.controller.commands.GreenComponent;
import ime.controller.commands.HorizontalFlip;
import ime.controller.commands.IMECommand;
import ime.controller.commands.Intensity;
import ime.controller.commands.Load;
import ime.controller.commands.Luma;
import ime.controller.commands.RGBCombine;
import ime.controller.commands.RGBSplit;
import ime.controller.commands.RedComponent;
import ime.controller.commands.RunScript;
import ime.controller.commands.Save;
import ime.controller.commands.Sepia;
import ime.controller.commands.Sharpen;
import ime.controller.commands.Value;
import ime.controller.commands.VerticalFlip;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * The IMECommandRepository class manages a collection of IME commands and their
 * associated factory functions.
 */
public class IMECommandRepository implements CommandRepository {

  // A mapping of command names to functions that create IMECommand instances.
  protected Map<String, Function<Scanner, IMECommand>> commands;

  /**
   * Constructs a new IMECommandRepository and initializes the mapping of command
   * names to factory functions.
   */
  public IMECommandRepository() {
    this.commands = new HashMap<>();
    commands.put("load", s -> new Load(s.next(), s.next()));
    commands.put("save", s -> new Save(s.next(), s.next()));
    commands.put("red-component", s -> new RedComponent(s.next(), s.next()));
    commands.put("green-component", s -> new GreenComponent(s.next(), s.next()));
    commands.put("blue-component", s -> new BlueComponent(s.next(), s.next()));
    commands.put("luma", s -> new Luma(s.next(), s.next()));
    commands.put("intensity", s -> new Intensity(s.next(), s.next()));
    commands.put("value", s -> new Value(s.next(), s.next()));
    commands.put("horizontal-flip", s -> new HorizontalFlip(s.next(), s.next()));
    commands.put("vertical-flip", s -> new VerticalFlip(s.next(), s.next()));
    commands.put("brighten", s -> new Brighten(s.nextInt(), s.next(), s.next()));
    commands.put("rgb-split", s -> new RGBSplit(s.next(), s.next(), s.next(), s.next()));
    commands.put("rgb-combine", s -> new RGBCombine(s.next(), s.next(), s.next(), s.next()));
    commands.put("blur", s -> new Blur(s.next(), s.next()));
    commands.put("sharpen", s -> new Sharpen(s.next(), s.next()));
    commands.put("sepia", s -> new Sepia(s.next(), s.next()));
    commands.put("run", s -> new RunScript(s.next()));
  }

  /**
   * Retrieves the factory function associated with the specified command name.
   *
   * @param commandName The name of the IME command for which to get the factory function.
   * @return The factory function for creating an IMECommand, or null if the command is not found.
   */
  @Override
  public Function<Scanner, IMECommand> get(String commandName) {
    return commands.getOrDefault(commandName, null);
  }

  /**
   * Associates a factory function with a specific command name in the repository.
   *
   * @param commandName     The name of the IME command to associate with the factory function.
   * @param commandMapping The factory function for creating an IMECommand.
   */
  @Override
  public void put(String commandName, Function<Scanner, IMECommand> commandMapping) {
    commands.put(commandName, commandMapping);
  }
}
