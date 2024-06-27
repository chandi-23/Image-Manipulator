/**
 * The MIMECommandRepository class extends IMECommandRepository and represents a
 * repository for MIME-specific image processing commands with parameters.
 */

package ime.controller;

import ime.controller.commands.BlurWithParams;
import ime.controller.commands.ColorCorrectWithParams;
import ime.controller.commands.Compress;
import ime.controller.commands.Histogram;
import ime.controller.commands.LevelsAdjustWithParams;
import ime.controller.commands.LumaWithParams;
import ime.controller.commands.SepiaWithParams;
import ime.controller.commands.SharpenWithParams;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The class initializes command mappings using a Scanner for input parsing and parameter
 * extraction. Each command is associated with a lambda expression creating an instance
 * of the corresponding command class with parameters.
 *
 */
public class MIMECommandRepository extends IMECommandRepository {

  /**
   * Constructs a new MIMECommandRepository and initializes the command mappings
   * for MIME-specific image processing commands with parameters.
   */
  public MIMECommandRepository() {
    commands.put("compress", s -> new Compress(s.nextDouble(), s.next(), s.next()));
    commands.put("histogram", s -> new Histogram(s.next(), s.next()));
    commands.put("luma", s -> {
      Scanner sc = new Scanner(s.nextLine());
      String inputImageName = sc.next();
      String outputImageName = sc.next();
      Map<String, String> params = new HashMap<>();
      if (sc.hasNext("split")) {
        params.put(sc.next(), sc.next());
      }
      return new LumaWithParams(inputImageName, outputImageName, params);
    });
    commands.put("blur", s -> {
      Scanner sc = new Scanner(s.nextLine());
      String inputImageName = sc.next();
      String outputImageName = sc.next();
      Map<String, String> params = new HashMap<>();
      if (sc.hasNext("split")) {
        params.put(sc.next(), sc.next());
      }
      return new BlurWithParams(inputImageName, outputImageName, params);
    });
    commands.put("sharpen", s -> {
      Scanner sc = new Scanner(s.nextLine());
      String inputImageName = sc.next();
      String outputImageName = sc.next();
      Map<String, String> params = new HashMap<>();
      if (sc.hasNext("split")) {
        params.put(sc.next(), sc.next());
      }
      return new SharpenWithParams(inputImageName, outputImageName, params);
    });
    commands.put("sepia", s -> {
      Scanner sc = new Scanner(s.nextLine());
      String inputImageName = sc.next();
      String outputImageName = sc.next();
      Map<String, String> params = new HashMap<>();
      if (sc.hasNext("split")) {
        params.put(sc.next(), sc.next());
      }
      return new SepiaWithParams(inputImageName, outputImageName, params);
    });
    commands.put("levels-adjust", s -> {
      Scanner sc = new Scanner(s.nextLine());
      int black = sc.nextInt();
      int mid = sc.nextInt();
      int white = sc.nextInt();
      String inputImageName = sc.next();
      String outputImageName = sc.next();
      Map<String, String> params = new HashMap<>();
      if (sc.hasNext("split")) {
        params.put(sc.next(), sc.next());
      }
      return new LevelsAdjustWithParams(black, mid, white, inputImageName, outputImageName, params);
    });
    commands.put("color-correct", s -> {
      Scanner sc = new Scanner(s.nextLine());
      String inputImageName = sc.next();
      String outputImageName = sc.next();
      Map<String, String> params = new HashMap<>();
      if (sc.hasNext("split")) {
        params.put(sc.next(), sc.next());
      }
      return new ColorCorrectWithParams(inputImageName, outputImageName, params);
    });
  }

}
