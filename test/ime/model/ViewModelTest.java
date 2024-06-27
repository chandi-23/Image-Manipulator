package ime.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The ViewModelTest class serves as a test class for the ViewModel, which is
 * responsible for providing image data through the IViewModel interface.
 * It utilizes a MockModel implementation for testing purposes.
 */
public class ViewModelTest {

  private IMEFacade model;

  @Before
  public void setUp() {
    StringBuilder modelLogger = new StringBuilder();
    model = new MockModel(modelLogger);
  }

  @Test
  public void testGetImage() {
    int[][][] expected = new int[][][]{
        {{1, 2, 3}, {4, 5, 6}},
        {{7, 8, 9}, {10, 11, 12}}
    };
    int[][][] actual = model.getRawImageData("dummy");
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(actual[i][j][k], expected[i][j][k]);
        }
      }
    }
  }

  private static class MockModel implements MIMEFacade {

    private final StringBuilder logger;

    public MockModel(StringBuilder logger) {
      this.logger = logger;
    }

    @Override
    public void load(InputStream imageData, String imageName) {
      logger.append("Input given:").append(" ").append(imageName);
    }

    @Override
    public OutputStream save(String imageName) throws IOException {
      logger.append("Input given:").append(" ").append(imageName);
      String image = "2 2 255 1 2 3 4 5 6 7 8 9 10 11 12";
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      outputStream.write(image.getBytes());
      return outputStream;
    }

    @Override
    public void redComponent(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName);
    }

    @Override
    public void greenComponent(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName);
    }

    @Override
    public void blueComponent(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName);
    }

    @Override
    public void value(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName);
    }

    @Override
    public void intensity(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName);
    }

    @Override
    public void luma(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName);
    }

    @Override
    public void luma(String inputImageName, String outputImageName, double splitPercentage)
            throws CloneNotSupportedException {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName).append(" ").append(splitPercentage);
    }

    @Override
    public void flipHorizontal(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName);
    }

    @Override
    public void flipVertical(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName);
    }

    @Override
    public void brighten(int increment, String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(increment).append(" ")
              .append(inputImageName).append(" ").append(outputImageName);
    }

    @Override
    public void rgbSplit(String inputImageName, String redComponentOutputImageName,
        String greenComponentOutputImageName, String blueComponentOutputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(redComponentOutputImageName).append(" ").append(greenComponentOutputImageName)
              .append(" ").append(blueComponentOutputImageName);
    }

    @Override
    public void rgbCombine(String redComponentInputImageName, String greenComponentInputImageName,
        String blueComponentInputImageName, String outputImageName) {
      logger.append(redComponentInputImageName).append(" ").append(greenComponentInputImageName)
              .append(" ").append(blueComponentInputImageName).append(" ")
              .append(outputImageName);
    }

    @Override
    public void blur(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName);
    }

    @Override
    public void blur(String inputImageName, String outputImageName, double splitPercentage)
            throws CloneNotSupportedException {
      logger.append("Input given:").append(" ").append(inputImageName)
              .append(" ").append(outputImageName).append(" ").append(splitPercentage);
    }

    @Override
    public void sharpen(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName);
    }

    @Override
    public void sharpen(String inputImageName, String outputImageName, double splitPercentage)
            throws CloneNotSupportedException {
      logger.append("Input given:").append(" ").append(inputImageName)
              .append(" ").append(outputImageName).append(" ").append(splitPercentage);
    }

    @Override
    public void sepia(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName);
    }

    @Override
    public void sepia(String inputImageName, String outputImageName, double splitPercentage)
            throws CloneNotSupportedException {
      logger.append("Input given:").append(" ").append(inputImageName)
              .append(" ").append(outputImageName).append(" ").append(splitPercentage);
    }

    @Override
    public int[][][] getRawImageData(String imageName) {
      logger.append("Input given:").append(" ").append(imageName);
      return new int[][][]{
          {{1, 2, 3}, {4, 5, 6}},
          {{7, 8, 9}, {10, 11, 12}}
      };
    }

    @Override
    public void compress(double percentage, String inputImageName, String outputImageName)
        throws IllegalArgumentException {
      logger.append("Input given:").append(" ").append(percentage).append(" ")
              .append(inputImageName).append(" ").append(outputImageName);
    }

    @Override
    public int[][] histogram(String inputImageName, String outputImageName) {
      System.out.println("Hello");
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName);
      return new int[0][];
    }

    @Override
    public void colorCorrect(String inputImageName, String outputImageName) {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName);
    }

    @Override
    public void colorCorrect(String inputImageName, String outputImageName, double splitPercentage)
        throws CloneNotSupportedException {
      logger.append("Input given:").append(" ").append(inputImageName).append(" ")
              .append(outputImageName).append(" ").append(splitPercentage);
    }

    @Override
    public void adjustLevels(int black, int mid, int white, String inputImageName,
        String outputImageName) throws IllegalArgumentException {
      logger.append("Input given:").append(" ").append(black).append(" ")
              .append(mid).append(" ").append(white).append(" ").append(inputImageName)
              .append(" ").append(outputImageName);
    }

    @Override
    public void adjustLevels(int black, int mid, int white, String inputImageName,
        String outputImageName, double splitPercentage) throws CloneNotSupportedException {
      logger.append("Input given:").append(" ").append(black).append(" ")
              .append(mid).append(" ").append(white).append(" ").append(inputImageName)
              .append(" ").append(outputImageName).append(" ").append(splitPercentage);
    }

  }

}
