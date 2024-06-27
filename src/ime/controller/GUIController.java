package ime.controller;

import ime.controller.commands.BlueComponent;
import ime.controller.commands.Blur;
import ime.controller.commands.BlurWithParams;
import ime.controller.commands.ColorCorrect;
import ime.controller.commands.ColorCorrectWithParams;
import ime.controller.commands.Compress;
import ime.controller.commands.GreenComponent;
import ime.controller.commands.Histogram;
import ime.controller.commands.HorizontalFlip;
import ime.controller.commands.IMECommand;
import ime.controller.commands.Intensity;
import ime.controller.commands.LevelsAdjust;
import ime.controller.commands.LevelsAdjustWithParams;
import ime.controller.commands.Load;
import ime.controller.commands.Luma;
import ime.controller.commands.LumaWithParams;
import ime.controller.commands.RedComponent;
import ime.controller.commands.Save;
import ime.controller.commands.Sepia;
import ime.controller.commands.SepiaWithParams;
import ime.controller.commands.Sharpen;
import ime.controller.commands.SharpenWithParams;
import ime.controller.commands.Value;
import ime.controller.commands.VerticalFlip;
import ime.model.IMEFacade;
import ime.view.IView;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;

/**
 * GUIController is responsible for managing the interaction between the graphical user
 * interface (GUI), the underlying image processing model (IMEFacade), and the view (IView).
 * It implements the Features interface to provide image processing functionality to the GUI.
 */
public class GUIController implements Features {

  private final IMEFacade model;
  private final IView view;
  private final String currentImage;
  private final String currentHistogramImage;
  private final String currentSplitViewImage;

  /**
   * Constructs a new GUIController with the specified IMEFacade model and IView.
   *
   * @param model The underlying image processing model.
   * @param view  The view component for displaying images and interacting with the user.
   */
  public GUIController(IMEFacade model, IView view) {
    this.model = model;
    this.view = view;
    view.addFeatures(this);
    currentImage = "b1a34ce9-af60-489f-a0f3-9e20ca55789d";
    currentHistogramImage = "fe25d39c-ed69-4816-a4e1-2d30db48b905";
    currentSplitViewImage = "ba2b6a3d-aece-489f-9228-90f5500c55e3";
  }

  private void refresh(String currentImageName, String histogramImageName) {
    view.refresh(currentImageName, histogramImageName);
  }

  private void executeCommand(IMECommand command) throws IOException, CloneNotSupportedException {
    try {
      command.run(model);
    } catch (IllegalArgumentException | InputMismatchException e) {
      view.displayErrorMessage(e, e.getMessage());
    }
  }

  @Override
  public void load(String filepath) throws IOException, CloneNotSupportedException {
    executeCommand(new Load(filepath, currentImage));
    histogram();
    refresh(currentImage, currentHistogramImage);
  }

  @Override
  public void save(String filepath) throws IOException, CloneNotSupportedException {
    executeCommand(new Save(filepath, currentImage));
    histogram();
    refresh(currentImage, currentHistogramImage);
  }

  @Override
  public void redComponent() throws IOException, CloneNotSupportedException {
    executeCommand(new RedComponent(currentImage, currentImage));
    histogram();
    refresh(currentImage, currentHistogramImage);
  }

  @Override
  public void greenComponent() throws IOException, CloneNotSupportedException {
    executeCommand(new GreenComponent(currentImage, currentImage));
    histogram();
    refresh(currentImage, currentHistogramImage);
  }

  @Override
  public void blueComponent() throws IOException, CloneNotSupportedException {
    executeCommand(new BlueComponent(currentImage, currentImage));
    histogram();
    refresh(currentImage, currentHistogramImage);
  }

  @Override
  public void value() throws IOException, CloneNotSupportedException {
    executeCommand(new Value(currentImage, currentImage));
    histogram();
    refresh(currentImage, currentHistogramImage);
  }

  @Override
  public void intensity() throws IOException, CloneNotSupportedException {
    executeCommand(new Intensity(currentImage, currentImage));
    histogram();
    refresh(currentImage, currentHistogramImage);
  }

  @Override
  public void luma() throws IOException, CloneNotSupportedException {
    executeCommand(new Luma(currentImage, currentImage));
    histogram();
    refresh(currentImage, currentHistogramImage);
  }

  @Override
  public void luma(double splitPercentage) throws CloneNotSupportedException, IOException {
    executeCommand(new LumaWithParams(currentImage,
            currentSplitViewImage, new HashMap<>() {
              {
                put("split", String.valueOf(splitPercentage));
              }
            } ));
    refresh(currentSplitViewImage, currentHistogramImage);
  }

  @Override
  public void flipHorizontal() throws IOException, CloneNotSupportedException {
    executeCommand(new HorizontalFlip(currentImage, currentImage));
    histogram();
    refresh(currentImage, currentHistogramImage);
  }

  @Override
  public void flipVertical() throws IOException, CloneNotSupportedException {
    executeCommand(new VerticalFlip(currentImage, currentImage));
    histogram();
    refresh(currentImage, currentHistogramImage);
  }

  @Override
  public void blur() throws IOException, CloneNotSupportedException {
    executeCommand(new Blur(currentImage, currentImage));
    histogram();
    refresh(currentImage, currentHistogramImage);
  }

  @Override
  public void blur(double splitPercentage) throws CloneNotSupportedException, IOException {
    executeCommand(new BlurWithParams(currentImage,
            currentSplitViewImage, new HashMap<>() {
              {
                put("split", String.valueOf(splitPercentage));
              }
            } ));
    refresh(currentSplitViewImage, currentHistogramImage);
  }

  @Override
  public void sharpen() throws IOException, CloneNotSupportedException {
    executeCommand(new Sharpen(currentImage, currentImage));
    histogram();
    refresh(currentImage, currentHistogramImage);
  }

  @Override
  public void sharpen(double splitPercentage) throws CloneNotSupportedException, IOException {
    executeCommand(new SharpenWithParams(currentImage,
            currentSplitViewImage, new HashMap<>() {
              {
                put("split", String.valueOf(splitPercentage));
              }
            } ));
    refresh(currentSplitViewImage, currentHistogramImage);
  }

  @Override
  public void sepia() throws IOException, CloneNotSupportedException {
    executeCommand(new Sepia(currentImage, currentImage));
    histogram();
    refresh(currentImage, currentHistogramImage);
  }

  @Override
  public void sepia(double splitPercentage) throws CloneNotSupportedException, IOException {
    executeCommand(new SepiaWithParams(currentImage,
            currentSplitViewImage, new HashMap<>() {
              {
                put("split", String.valueOf(splitPercentage));
              }
            }));
    refresh(currentSplitViewImage, currentHistogramImage);
  }

  @Override
  public void compress(double percentage)
      throws IllegalArgumentException, IOException, CloneNotSupportedException {
    executeCommand(new Compress(percentage, currentImage, currentImage));
    histogram();
    refresh(currentImage, currentHistogramImage);
  }

  @Override
  public void histogram() throws IOException, CloneNotSupportedException {
    new Histogram(currentImage, currentHistogramImage).run(model);
  }

  @Override
  public void colorCorrect() throws IOException, CloneNotSupportedException {
    executeCommand(new ColorCorrect(currentImage, currentImage));
    histogram();
    refresh(currentImage, currentHistogramImage);
  }

  @Override
  public void colorCorrect(double splitPercentage) throws CloneNotSupportedException, IOException {
    executeCommand(new ColorCorrectWithParams(currentImage,
        currentSplitViewImage,
            new HashMap<>() {
        {
          put("split", String.valueOf(splitPercentage));
        }
      } ));
    refresh(currentSplitViewImage, currentHistogramImage);
  }

  @Override
  public void adjustLevels(int black, int mid, int white)
      throws IllegalArgumentException, IOException, CloneNotSupportedException {
    executeCommand(new LevelsAdjust(black, mid, white, currentImage, currentImage));
    histogram();
    refresh(currentImage, currentHistogramImage);
  }

  @Override
  public void adjustLevels(int black, int mid, int white, double splitPercentage)
      throws CloneNotSupportedException, IOException {
    executeCommand(new LevelsAdjustWithParams(black, mid, white, currentImage,
        currentSplitViewImage,
            new HashMap<>() {
        {
          put("split", String.valueOf(splitPercentage));
        }
      } ));
    refresh(currentSplitViewImage, currentHistogramImage);
  }

  @Override
  public void disableSplitView() {
    view.refresh(currentImage, currentHistogramImage);
  }

}
