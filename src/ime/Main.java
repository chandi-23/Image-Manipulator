package ime;

import ime.controller.Controller;
import ime.controller.CommandController;
import ime.controller.FileUtil;
import ime.controller.GUIController;
import ime.controller.MIMECommandRepository;
import ime.model.IViewModel;
import ime.model.MIMEFacadeImpl;
import ime.model.ViewModel;
import ime.view.View;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.NoSuchElementException;
import ime.model.IMEFacade;
import javax.swing.SwingUtilities;

/**
 * The ime.Main class serves as the entry point for the IME (Image Manipulation and Enhancement)
 * application. It initializes the model, controller, view and starts the user interaction loop.
 */
public class Main {

  /**
   * The main method is the entry point for the IME application. It initializes the model,
   * controller, view and begins user interaction.
   *
   * @param args Command-line arguments.
   */
  public static void main(String[] args) {

    try {
      IMEFacade model = new MIMEFacadeImpl();
      Readable readable = new InputStreamReader(System.in);
      if (args.length > 0) {
        if (args[0].equals("-file")) {
          String commands = FileUtil.cleanFileRead(args[1]);
          readable = new StringReader(commands);
        }
        Controller controller = new CommandController(readable, System.out,
            new MIMECommandRepository());
        controller.goAhead(model);
      } else {
        SwingUtilities.invokeLater(() -> {

          IViewModel viewModel = new ViewModel(model);
          View view = new View(viewModel);
          new GUIController(model, view);

        });
      }

    } catch ( IOException | IllegalArgumentException | NoSuchElementException e) {
      System.out.println(e.getMessage());
    }

  }

}
