package ime.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import javax.imageio.ImageIO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * The MIMEFacadeImplTest class contains test cases for the MIMEFacadeImpl class,
 * ensuring the correctness of image manipulation and enhancement functionality.
 */
public class MIMEFacadeImplTest {

  private MIMEFacadeImpl mimeFacade;
  private String testImagePath;
  private Helper helper2;

  private static class Helper {

    public String readFile(String path) throws FileNotFoundException {

      Scanner sc = new Scanner(new FileInputStream(path));
      StringBuilder fileContent = new StringBuilder();

      while (sc.hasNext()) {
        String line = sc.nextLine();
        if (!line.isEmpty() && !line.startsWith("#")) {
          fileContent.append(line).append(System.lineSeparator());  }
      }

      return fileContent.toString();

    }

    public InputStream getImageInputStream(String imagePath) throws IOException {

      if (imagePath.endsWith("ppm")) {
        String fileContent = readFile(imagePath);
        Scanner sc = new Scanner(fileContent);
        String token = sc.next();
        StringBuilder builder = new StringBuilder();
        while (sc.hasNext()) {
          builder.append(sc.next()).append(System.lineSeparator());
        }
        return new ByteArrayInputStream(builder.toString().getBytes());

      } else {

        BufferedImage image = ImageIO.read(new File(imagePath));
        StringBuilder imageRGB = new StringBuilder();
        int max = 0;
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
            int pixel = image.getRGB(x, y);
            Color color = new Color(pixel);
            int red = color.getRed();
            int green = color.getGreen();
            int blue = color.getBlue();
            max = Math.max(Math.max(Math.max(max, red), green), blue);
            imageRGB.append(red).append(System.lineSeparator());
            imageRGB.append(green).append(System.lineSeparator());
            imageRGB.append(blue).append(System.lineSeparator());
          }
        }

        String imageData = width + " "
            + height + System.lineSeparator()
            + max + System.lineSeparator()
            + imageRGB;

        return new ByteArrayInputStream(imageData.getBytes());
      }
    }

  }

  @Before
  public void setUp() {
    try {
      mimeFacade = new MIMEFacadeImpl();
      helper2 = new Helper();
      testImagePath = "/Users/chandresh/Desktop/PDP/Assignment-6/PDP-Assignment-4/test/res/";
      mimeFacade.load(helper2.getImageInputStream(testImagePath + "test2X2.ppm"), "test");
      mimeFacade.load(helper2.getImageInputStream(testImagePath + "test2X3.ppm"), "test2X3");
      mimeFacade.load(helper2.getImageInputStream(testImagePath + "test3X1.ppm"), "test3X1");
      mimeFacade.load(helper2.getImageInputStream(testImagePath + "test2X2.png"), "test-png");
      mimeFacade.load(helper2.getImageInputStream(testImagePath + "test2X2.jpg"), "test-jpg");
      mimeFacade.load(helper2.getImageInputStream(testImagePath + "test2X2.bmp"), "test-bmp");
    } catch (IOException e) {
      fail("Failed to create the test image");
    }
  }

  // Histogram
  @Test
  public void testValidHistogram() throws IOException {

    mimeFacade.load(helper2.getImageInputStream(testImagePath + "test2X2-hist.png"),
            "expected-histogram");
    Image expected = mimeFacade.getImage("expected-histogram");

    mimeFacade.histogram("test-png", "actual-histogram");
    Image actual = mimeFacade.getImage("actual-histogram");

    assertEquals(expected.getWidth(), actual.getWidth());
    assertEquals(expected.getHeight(), actual.getHeight());
    assertEquals(expected.getMax(), actual.getMax());

    for (int i = 0; i < actual.getHeight(); i++) {
      for (int j = 0; j < actual.getWidth(); j++) {
        assertEquals(actual.getPixel(i, j), expected.getPixel(i, j));
      }
    }
  }

  // Color-Correct
  @Test
  public void testValidateColorCorrect() throws IOException {
    mimeFacade.load(helper2.getImageInputStream(testImagePath + "test2X2-cc.png"),
            "expected-cc-image");
    Image expected = mimeFacade.getImage("expected-cc-image");

    mimeFacade.colorCorrect("test-png", "actual-cc-image");
    Image actual = mimeFacade.getImage("actual-cc-image");

    assertEquals(expected.getWidth(), actual.getWidth());
    assertEquals(expected.getHeight(), actual.getHeight());
    assertEquals(255, actual.getMax());

    for (int i = 0; i < actual.getHeight(); i++) {
      for (int j = 0; j < actual.getWidth(); j++) {
        assertEquals(actual.getPixel(i, j), expected.getPixel(i, j));
      }
    }
  }

  @Test
  public void testValidateColorCorrectWithSplit() throws IOException, CloneNotSupportedException {
    mimeFacade.load(helper2.getImageInputStream(testImagePath + "test2X2-cc-split.png"),
            "expected-cc-split-image");
    Image expected = mimeFacade.getImage("expected-cc-split-image");

    mimeFacade.colorCorrect("test-png", "actual-cc-split-image", 40);
    Image actual = mimeFacade.getImage("actual-cc-split-image");

    assertEquals(expected.getWidth(), actual.getWidth());
    assertEquals(expected.getHeight(), actual.getHeight());
    assertEquals(255, actual.getMax());

    for (int i = 0; i < actual.getHeight(); i++) {
      for (int j = 0; j < actual.getWidth(); j++) {
        assertEquals(actual.getPixel(i, j), expected.getPixel(i, j));
      }
    }
  }


  @Test
  public void testCompress() throws IOException {
    mimeFacade.load(helper2.getImageInputStream(testImagePath + "test-compress-50.ppm"),
            "expected-test-compress-50");
    Image expected = mimeFacade.getImage("expected-test-compress-50");

    mimeFacade.compress(50, "test3X1", "actual-test-compress-50");
    Image actual = mimeFacade.getImage("actual-test-compress-50");

    assertEquals(expected.getWidth(), actual.getWidth());
    assertEquals(expected.getHeight(), actual.getHeight());
    assertEquals(expected.getMax(), actual.getMax());

    for (int i = 0; i < actual.getHeight(); i++) {
      for (int j = 0; j < actual.getWidth(); j++) {
        assertEquals(actual.getPixel(i, j), expected.getPixel(i, j));
      }
    }

  }

  @Test
  public void testCompressZero() throws IOException {
    mimeFacade.load(helper2.getImageInputStream(testImagePath + "test-compress-0.ppm"),
            "expected-test-compress-0");
    Image expected = mimeFacade.getImage("expected-test-compress-0");

    mimeFacade.compress(0, "test3X1", "actual-test-compress-0");
    Image actual = mimeFacade.getImage("actual-test-compress-0");

    assertEquals(expected.getWidth(), actual.getWidth());
    assertEquals(expected.getHeight(), actual.getHeight());
    assertEquals(expected.getMax(), actual.getMax());

    for (int i = 0; i < actual.getHeight(); i++) {
      for (int j = 0; j < actual.getWidth(); j++) {
        assertEquals(actual.getPixel(i, j), expected.getPixel(i, j));
      }
    }

  }

  @Test
  public void testCompressTwenty() throws IOException {
    mimeFacade.load(helper2.getImageInputStream(testImagePath + "test-compress-20.ppm"),
            "expected-test-compress-20");
    Image expected = mimeFacade.getImage("expected-test-compress-20");

    mimeFacade.compress(20, "test3X1", "actual-test-compress-20");
    Image actual = mimeFacade.getImage("actual-test-compress-20");

    assertEquals(expected.getWidth(), actual.getWidth());
    assertEquals(expected.getHeight(), actual.getHeight());
    assertEquals(expected.getMax(), actual.getMax());

    for (int i = 0; i < actual.getHeight(); i++) {
      for (int j = 0; j < actual.getWidth(); j++) {
        assertEquals(actual.getPixel(i, j), expected.getPixel(i, j));
      }
    }

  }

  @Test
  public void testCompressNinety() throws IOException {
    mimeFacade.load(helper2.getImageInputStream(testImagePath + "test-compress-90.ppm"),
            "expected-test-compress-90");
    Image expected = mimeFacade.getImage("expected-test-compress-90");

    mimeFacade.compress(90, "test3X1", "actual-test-compress-90");
    Image actual = mimeFacade.getImage("actual-test-compress-90");

    assertEquals(expected.getWidth(), actual.getWidth());
    assertEquals(expected.getHeight(), actual.getHeight());
    assertEquals(expected.getMax(), actual.getMax());

    for (int i = 0; i < actual.getHeight(); i++) {
      for (int j = 0; j < actual.getWidth(); j++) {
        assertEquals(actual.getPixel(i, j), expected.getPixel(i, j));
      }
    }

  }

  @Test
  public void testCompressHundred() throws IOException {
    mimeFacade.load(helper2.getImageInputStream(testImagePath + "test-compress-100.ppm"),
            "expected-test-compress-100");
    Image expected = mimeFacade.getImage("expected-test-compress-100");

    mimeFacade.compress(100, "test3X1", "actual-test-compress-100");
    Image actual = mimeFacade.getImage("actual-test-compress-100");

    assertEquals(expected.getWidth(), actual.getWidth());
    assertEquals(expected.getHeight(), actual.getHeight());
    assertEquals(expected.getMax(), actual.getMax());

    for (int i = 0; i < actual.getHeight(); i++) {
      for (int j = 0; j < actual.getWidth(); j++) {
        assertEquals(actual.getPixel(i, j), expected.getPixel(i, j));
      }
    }

  }

  @Test(expected = Exception.class)
  public void testCompressLessThanZero() {
    mimeFacade.compress(-1, "test3X1", "actual-test-compress--1");
    mimeFacade.getImage("actual-test-compress--1");
  }

  @Test(expected = Exception.class)
  public void testCompressGreaterThanHundred() {
    mimeFacade.compress(101, "test3X1", "actual-test-compress--1");
    mimeFacade.getImage("actual-test-compress--1");
  }


  @Test
  public void testBlurWithSplit() throws IOException, CloneNotSupportedException {
    mimeFacade.load(helper2.getImageInputStream(testImagePath + "test2X2-blr-split.png"),
            "expected-split-blur-image");
    Image expected = mimeFacade.getImage("expected-split-blur-image");

    mimeFacade.blur("test-png", "actual-split-blur-image", 50);
    Image actual = mimeFacade.getImage("actual-split-blur-image");

    assertEquals(expected.getWidth(), actual.getWidth());
    assertEquals(expected.getHeight(), actual.getHeight());
    assertEquals(255, actual.getMax());

    for (int i = 0; i < actual.getHeight(); i++) {
      for (int j = 0; j < actual.getWidth(); j++) {
        assertEquals(actual.getPixel(i, j), expected.getPixel(i, j));
      }
    }
  }

  @Test
  public void testTransformChannels() throws IOException {

    mimeFacade.load(helper2.getImageInputStream(testImagePath + "test2X2.ppm"), "test-HCompressor");
    Image expected = mimeFacade.getImage("test-HCompressor");
    HaarWaveletImageCompressor compressor = new HaarWaveletImageCompressor(expected);
    double[][][] channels2 = compressor.getChannels();

    double[][][] getChannels = {
            {{243, 90.0}, {33, 12.0}},
            {{244.0, 76.0 }, {205.0, 35.0 }},
            {{255.0, 44.0}, {104.0, 56.0 }}
    };
    int depth1 = channels2.length;
    int rows1 = channels2[0].length;
    int cols1 = channels2[0][0].length;

    for (int i = 0; i < depth1; i++) {
      for (int j = 0; j < rows1; j++) {
        for (int k = 0; k < cols1; k++) {
          assertEquals(getChannels[i][j][k], channels2[i][j][k], 0.01);
        }
      }
    }

    double[][][] expectedTransformedChannels = {
            {{189.0, 87.0 }, {144.0, 65.99}},
            {{279.999, 169.0 }, {40.0, -0.999}},
            {{229.49, 129.49}, {69.49, 81.499}}
    };
    double[][][] transformedChannels = compressor.transformChannels(channels2);

    assertEquals(expectedTransformedChannels.length, transformedChannels.length);
    assertEquals(expectedTransformedChannels[0].length, transformedChannels[0].length);
    assertEquals(expectedTransformedChannels[0][0].length, transformedChannels[0][0].length);

    int depth = transformedChannels.length;
    int rows = transformedChannels[0].length;
    int cols = transformedChannels[0][0].length;

    for (int i = 0; i < depth; i++) {
      for (int j = 0; j < rows; j++) {
        for (int k = 0; k < cols; k++) {
          assertEquals(expectedTransformedChannels[i][j][k], transformedChannels[i][j][k], 0.01);
        }
      }
    }

    compressor.invertChannels(transformedChannels);
    for (int i = 0; i < depth; i++) {
      for (int j = 0; j < rows; j++) {
        for (int k = 0; k < cols; k++) {
          assertEquals(getChannels[i][j][k], transformedChannels[i][j][k], 0.01);
        }
      }
    }

    // Check for threshold value
    double threshValue = compressor.getThreshold(transformedChannels, 50);
    assertEquals(76, threshValue, 0.01);

    compressor.thresholdChannels(transformedChannels, threshValue);
    double[][][] expectedTransformedChannelsThreshold = {
            {{242.99, 90.0  }, {0.0, 0.0}},
            {{243.99, 0.0  }, {204.999, 0.0 }},
            {{254.99, 0.0 }, {103.99, 0.0 }}
    };
    for (int i = 0; i < depth; i++) {
      for (int j = 0; j < rows; j++) {
        for (int k = 0; k < cols; k++) {
          assertEquals(expectedTransformedChannelsThreshold[i][j][k],
                  transformedChannels[i][j][k], 0.01);
        }
      }
    }

  }

  //LevelsAdjust

  @Test
  public void testLevelsAdjust() throws IOException, CloneNotSupportedException {
    // levels-adjust b m w image-name dest-image-name
    mimeFacade.load(helper2.getImageInputStream(testImagePath + "test-lvl-adj.png"),
            "expected-test-lvl-adjust");
    Image expected = mimeFacade.getImage("expected-test-lvl-adjust");

    mimeFacade.adjustLevels(10,100, 180, "test-png",
            "actual-lvl-adjust");
    Image actual = mimeFacade.getImage("actual-lvl-adjust");

    assertEquals(expected.getWidth(), actual.getWidth());
    assertEquals(expected.getHeight(), actual.getHeight());
    assertEquals(expected.getMax(), actual.getMax());

    for (int i = 0; i < actual.getHeight(); i++) {
      for (int j = 0; j < actual.getWidth(); j++) {
        assertEquals(actual.getPixel(i, j), expected.getPixel(i, j));
      }
    }

  }

  @Test(expected = Exception.class)
  public void testLevelAdjustInvalid() {
    mimeFacade.adjustLevels(-1, 10, 120, "test", "test-lvl");
  }

  @Test(expected = Exception.class)
  public void testLevelAdjustAscInvalid() {
    mimeFacade.adjustLevels(120, 10, 120, "test", "test-lvl");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeSplitPercentage() throws CloneNotSupportedException {
    mimeFacade.colorCorrect("test", "test-color-correct", -1);
  }

  @Test(expected = Exception.class)
  public void testLv() {
    mimeFacade.adjustLevels(12, 100, 270, "test", "test-lvl");
  }

  @Test
  public void testLevelsAdjustSplitSplit() throws IOException, CloneNotSupportedException {
    // levels-adjust b m w image-name dest-image-name
    mimeFacade.load(helper2.getImageInputStream(testImagePath + "test-lvl-adj-2split.png"),
            "expected-test-lvl-adjust");
    Image expected = mimeFacade.getImage("expected-test-lvl-adjust");

    mimeFacade.adjustLevels(10,100, 180, "test-png", "actual-lvl-adj-sp", 50);
    mimeFacade.adjustLevels(10,100, 240, "actual-lvl-adj-sp", "actual-lvl-adj-sp2", 80);

    Image actual = mimeFacade.getImage("actual-lvl-adj-sp2");

    assertEquals(expected.getWidth(), actual.getWidth());
    assertEquals(expected.getHeight(), actual.getHeight());
    assertEquals(expected.getMax(), actual.getMax());

    for (int i = 0; i < actual.getHeight(); i++) {
      for (int j = 0; j < actual.getWidth(); j++) {
        assertEquals(actual.getPixel(i, j), expected.getPixel(i, j));
      }
    }

  }
}