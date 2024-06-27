import ime.controller.Features;
import ime.controller.GUIController;
import ime.model.MIMEFacade;
import ime.view.IView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The GUIControllerTest class serves as a test class for the GUIController, which manages
 * the interaction between the graphical user interface (GUI), the underlying image processing
 * model (IMEFacade), and the view (IView). It utilizes a MockModel and MockView implementation
 * for testing purposes.
 */
public class GUIControllerTest {

  private Features controller;
  private StringBuilder modelLogger;

  @Before
  public void setUp() {
    modelLogger = new StringBuilder();
    StringBuilder viewLogger = new StringBuilder();
    MIMEFacade model = new MockModel(modelLogger);
    IView view = new MockView(viewLogger);
    controller = new GUIController(model, view);
  }

  @Test
  public void testLoad() throws IOException, CloneNotSupportedException {
    controller.load("C:\\Users\\pnava\\IdeaProjects\\"
            + "PDP-Assignment-4\\images\\test.jpg");
    assertEquals("Input given: b1a34ce9-af60-489f-a0f3-9e20ca55789d"
            + System.lineSeparator() + "Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d fe25d39c-ed69-4816-a4e1-2d30db48b905"
            + System.lineSeparator(), modelLogger.toString());
  }

  @Test(expected = IOException.class)
  public void testLoadFailure() throws IOException, CloneNotSupportedException {
    controller.load("test.ppm");
  }

  @Test
  public void testSave() throws IOException, CloneNotSupportedException {
    controller.save("test.jpg");
    assertEquals("Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d" + System.lineSeparator() + "Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d fe25d39c-ed69-4816-a4e1-2d30db48b905"
            + System.lineSeparator(), modelLogger.toString());
  }

  @Test
  public void testRedComponent() throws IOException, CloneNotSupportedException {
    controller.redComponent();
    assertEquals("Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d b1a34ce9-af60-489f-a0f3-9e20ca55789d"
            + System.lineSeparator() + "Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d fe25d39c-ed69-4816-a4e1-2d30db48b905"
            + System.lineSeparator(), modelLogger.toString());
  }

  @Test
  public void testGreenComponent() throws IOException, CloneNotSupportedException {
    controller.greenComponent();
    assertEquals("Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d b1a34ce9-af60-489f-a0f3-9e20ca55789d"
            + System.lineSeparator() + "Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d fe25d39c-ed69-4816-a4e1-2d30db48b905"
            + System.lineSeparator(), modelLogger.toString());
  }

  @Test
  public void testBlueComponent() throws IOException, CloneNotSupportedException {
    controller.blueComponent();
    assertEquals("Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d b1a34ce9-af60-489f-a0f3-9e20ca55789d"
            + System.lineSeparator() + "Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d fe25d39c-ed69-4816-a4e1-2d30db48b905"
            + System.lineSeparator(), modelLogger.toString());
  }

  @Test
  public void testLuma() throws IOException, CloneNotSupportedException {
    controller.luma();
    assertEquals("Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d b1a34ce9-af60-489f-a0f3-9e20ca55789d"
            + System.lineSeparator() + "Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d fe25d39c-ed69-4816-a4e1-2d30db48b905"
            + System.lineSeparator(), modelLogger.toString());
  }

  @Test
  public void testFlipHorizontal() throws IOException, CloneNotSupportedException {
    controller.flipHorizontal();
    assertEquals("Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d b1a34ce9-af60-489f-a0f3-9e20ca55789d"
            + System.lineSeparator() + "Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d fe25d39c-ed69-4816-a4e1-2d30db48b905"
            + System.lineSeparator(), modelLogger.toString());
  }

  @Test
  public void testFlipVertical() throws IOException, CloneNotSupportedException {
    controller.flipVertical();
    assertEquals("Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d b1a34ce9-af60-489f-a0f3-9e20ca55789d"
            + System.lineSeparator() + "Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d fe25d39c-ed69-4816-a4e1-2d30db48b905"
            + System.lineSeparator(), modelLogger.toString());
  }

  @Test
  public void testBlur() throws IOException, CloneNotSupportedException {
    controller.blur();
    assertEquals("Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d b1a34ce9-af60-489f-a0f3-9e20ca55789d"
            + System.lineSeparator() + "Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d fe25d39c-ed69-4816-a4e1-2d30db48b905"
            + System.lineSeparator(), modelLogger.toString());
  }

  @Test
  public void testSharpen() throws IOException, CloneNotSupportedException {
    controller.sharpen();
    assertEquals("Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d b1a34ce9-af60-489f-a0f3-9e20ca55789d"
            + System.lineSeparator() + "Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d fe25d39c-ed69-4816-a4e1-2d30db48b905"
            + System.lineSeparator(), modelLogger.toString());
  }

  @Test
  public void testSepia() throws IOException, CloneNotSupportedException {
    controller.sepia();
    assertEquals("Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d b1a34ce9-af60-489f-a0f3-9e20ca55789d"
            + System.lineSeparator() + "Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d fe25d39c-ed69-4816-a4e1-2d30db48b905"
            + System.lineSeparator(), modelLogger.toString());
  }

  @Test
  public void testHistogram() throws IOException, CloneNotSupportedException {
    controller.histogram();
    assertEquals("Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d fe25d39c-ed69-4816-a4e1-2d30db48b905"
            + System.lineSeparator(), modelLogger.toString());
  }

  @Test
  public void testCompress() throws IOException, CloneNotSupportedException {
    controller.compress(65);
    assertEquals("Input given: "
            + "65.0 b1a34ce9-af60-489f-a0f3-9e20ca55789d b1a34ce9-af60-489f-a0f3-9e20ca55789d"
            + System.lineSeparator() + "Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d fe25d39c-ed69-4816-a4e1-2d30db48b905"
            + System.lineSeparator(), modelLogger.toString());
  }

  @Test
  public void testColorCorrect() throws IOException, CloneNotSupportedException {
    controller.colorCorrect();
    assertEquals("Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d b1a34ce9-af60-489f-a0f3-9e20ca55789d"
            + System.lineSeparator() + "Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d fe25d39c-ed69-4816-a4e1-2d30db48b905"
            + System.lineSeparator(), modelLogger.toString());
  }

  @Test
  public void testAdjustLevels() throws IOException, CloneNotSupportedException {
    controller.adjustLevels(10, 20, 245);
    assertEquals("Input given: "
            + "10 20 245 b1a34ce9-af60-489f-a0f3-9e20ca55789d b1a34ce9-af60-489f-a0f3-9e20ca55789d"
            + System.lineSeparator() + "Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d fe25d39c-ed69-4816-a4e1-2d30db48b905"
            + System.lineSeparator(), modelLogger.toString());
  }

  @Test
  public void testBlurWithSplit() throws IOException, CloneNotSupportedException {
    controller.blur(65);
    assertEquals("Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d ba2b6a3d-aece-489f-9228-90f5500c55e3 65.0"
            + System.lineSeparator(), modelLogger.toString());
  }

  @Test
  public void testSepiaWithSplit() throws IOException, CloneNotSupportedException {
    controller.sepia(65);
    assertEquals("Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d ba2b6a3d-aece-489f-9228-90f5500c55e3 65.0"
            + System.lineSeparator(), modelLogger.toString());
  }

  @Test
  public void testSharpenWithSplit() throws IOException, CloneNotSupportedException {
    controller.sharpen(65);
    assertEquals("Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d ba2b6a3d-aece-489f-9228-90f5500c55e3 65.0"
            + System.lineSeparator(), modelLogger.toString());
  }

  @Test
  public void testAdjustLevelsWithSplit() throws IOException, CloneNotSupportedException {
    controller.adjustLevels(10, 20, 245, 65);
    assertEquals("Input given: 10 20 245 "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d ba2b6a3d-aece-489f-9228-90f5500c55e3 65.0"
            + System.lineSeparator(), modelLogger.toString());
  }

  @Test
  public void testColorCorrectWithSplit() throws IOException, CloneNotSupportedException {
    controller.colorCorrect(65);
    assertEquals("Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d ba2b6a3d-aece-489f-9228-90f5500c55e3 65.0"
            + System.lineSeparator(), modelLogger.toString());
  }

  @Test
  public void testLumaWithSplit() throws IOException, CloneNotSupportedException {
    controller.luma(65);
    assertEquals("Input given: "
            + "b1a34ce9-af60-489f-a0f3-9e20ca55789d ba2b6a3d-aece-489f-9228-90f5500c55e3 65.0"
            + System.lineSeparator(), modelLogger.toString());
  }

  private static class MockView implements IView {

    private final StringBuilder logger;

    public MockView(StringBuilder logger) {
      this.logger = logger;
    }

    @Override
    public void refresh(String imageName, String histogramImageName) {
      logger.append("Input given:").append(" ").append(imageName).append(" ")
              .append(histogramImageName);
    }

    @Override
    public void addFeatures(Features controller) {
      logger.append("Input given:").append(" ").append(controller.getClass());
    }

    @Override
    public void displayErrorMessage(Exception ex, String message) {
      logger.append("Input given:").append(" ").append(message);
    }
  }

  private static class MockModel implements MIMEFacade {

    private final StringBuilder logger;

    public MockModel(StringBuilder logger) {
      this.logger = logger;
    }

    @Override
    public void load(InputStream imageData, String imageName) {
      logger.append("Input given:").append(" ").append(imageName).append(System.lineSeparator());
    }

    @Override
    public OutputStream save(String imageName) throws IOException {
      logger.append("Input given:").append(" ").append(imageName).append(System.lineSeparator());
      String image = "2 2 255 1 2 3 4 5 6 7 8 9 10 11 12";
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      outputStream.write(image.getBytes());
      return outputStream;
    }

    @Override
    public void redComponent(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName).append(System.lineSeparator());
    }

    @Override
    public void greenComponent(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName).append(System.lineSeparator());
    }

    @Override
    public void blueComponent(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName).append(System.lineSeparator());
    }

    @Override
    public void value(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName).append(System.lineSeparator());
    }

    @Override
    public void intensity(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName).append(System.lineSeparator());
    }

    @Override
    public void luma(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName).append(System.lineSeparator());
    }

    @Override
    public void luma(String inputImageName, String outputImageName, double splitPercentage)
            throws CloneNotSupportedException {
      logger.append("Input given:").append(" ").append(inputImageName)
              .append(" ").append(outputImageName).append(" ")
              .append(splitPercentage).append(System.lineSeparator());
    }

    @Override
    public void flipHorizontal(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName).append(System.lineSeparator());
    }

    @Override
    public void flipVertical(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName).append(System.lineSeparator());
    }

    @Override
    public void brighten(int increment, String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(increment).append(" ")
              .append(inputImageName).append(" ").append(outputImageName)
              .append(System.lineSeparator());
    }

    @Override
    public void rgbSplit(String inputImageName, String redComponentOutputImageName,
        String greenComponentOutputImageName, String blueComponentOutputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(redComponentOutputImageName).append(" ").append(greenComponentOutputImageName)
              .append(" ").append(blueComponentOutputImageName).append(System.lineSeparator());
    }

    @Override
    public void rgbCombine(String redComponentInputImageName, String greenComponentInputImageName,
        String blueComponentInputImageName, String outputImageName) {
      logger.append(redComponentInputImageName).append(" ").append(greenComponentInputImageName)
              .append(" ").append(blueComponentInputImageName).append(" ").append(outputImageName)
              .append(System.lineSeparator());
    }

    @Override
    public void blur(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName).append(System.lineSeparator());
    }

    @Override
    public void blur(String inputImageName, String outputImageName, double splitPercentage)
            throws CloneNotSupportedException {
      logger.append("Input given:").append(" ").append(inputImageName)
              .append(" ").append(outputImageName).append(" ").append(splitPercentage)
              .append(System.lineSeparator());
    }


    @Override
    public void sharpen(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName).append(System.lineSeparator());
    }

    @Override
    public void sharpen(String inputImageName, String outputImageName, double splitPercentage)
            throws CloneNotSupportedException {
      logger.append("Input given:").append(" ").append(inputImageName)
              .append(" ").append(outputImageName).append(" ")
              .append(splitPercentage).append(System.lineSeparator());
    }

    @Override
    public void sepia(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName).append(System.lineSeparator());
    }

    @Override
    public void sepia(String inputImageName, String outputImageName, double splitPercentage)
            throws CloneNotSupportedException {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName).append(" ").append(splitPercentage)
              .append(System.lineSeparator());
    }

    @Override
    public int[][][] getRawImageData(String imageName) {
      logger.append("Input given:").append(" ").append(imageName)
              .append(System.lineSeparator());
      return new int[][][]{
          {{1, 2, 3}, {4, 5, 6}},
          {{7, 8, 9}, {10, 11, 12}}
      };
    }

    @Override
    public void compress(double percentage, String inputImageName, String outputImageName)
        throws IllegalArgumentException {
      logger.append("Input given:").append(" ").append(percentage).append(" ")
              .append(inputImageName).append(" ").append(outputImageName)
              .append(System.lineSeparator());
    }

    @Override
    public int[][] histogram(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName).append(System.lineSeparator());
      return new int[0][];
    }

    @Override
    public void colorCorrect(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName).append(System.lineSeparator());
    }

    @Override
    public void colorCorrect(String inputImageName, String outputImageName, double splitPercentage)
        throws CloneNotSupportedException {
      logger.append("Input given:").append(" ").append(inputImageName)
              .append(" ").append(outputImageName).append(" ").append(splitPercentage)
              .append(System.lineSeparator());
    }

    @Override
    public void adjustLevels(int black, int mid, int white, String inputImageName,
        String outputImageName) throws IllegalArgumentException {
      logger.append("Input given:").append(" ").append(black).append(" ")
              .append(mid).append(" ").append(white).append(" ").append(inputImageName)
              .append(" ").append(outputImageName).append(System.lineSeparator());
    }

    @Override
    public void adjustLevels(int black, int mid, int white, String inputImageName,
        String outputImageName, double splitPercentage) throws CloneNotSupportedException {
      logger.append("Input given:").append(" ").append(black).append(" ")
              .append(mid).append(" ").append(white).append(" ").append(inputImageName)
              .append(" ").append(outputImageName).append(" ").append(splitPercentage)
              .append(System.lineSeparator());
    }

  }

}
