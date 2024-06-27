package ime.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This is class to perform unit test for all the model methods.
 */
public class IMEFacadeImplTest {

  private IMEFacadeImpl imeFacade;
  private Helper helper;
  private Image image;
  private Image imageJPG;
  private Image imagePNG;
  private Image imageBMP;
  private String testImagePath;

  @Before
  public void setUp() {
    try {
      imeFacade = new IMEFacadeImpl();
      helper = new Helper();
      testImagePath = "C:\\Users\\pnava\\IdeaProjects\\PDP-Assignment-4\\test\\res\\test";
      imeFacade.load(helper.getImageInputStream(testImagePath + "2X2.ppm"), "test");
      imeFacade.load(helper.getImageInputStream(testImagePath + "2X3.ppm"), "test2X3");
      imeFacade.load(helper.getImageInputStream(testImagePath + "3X1.ppm"), "test3X1");
      imeFacade.load(helper.getImageInputStream(testImagePath + "2X2.png"), "test-png");
      imeFacade.load(helper.getImageInputStream(testImagePath + "2X2.jpg"), "test-jpg");
      imeFacade.load(helper.getImageInputStream(testImagePath + "2X2.bmp"), "test-bmp");
      image = imeFacade.getImage("test");
      imageJPG = imeFacade.getImage("test-jpg");
      imageBMP = imeFacade.getImage("test-bmp");
      imagePNG = imeFacade.getImage("test-png");
      Image image2X3 = imeFacade.getImage("test2X3");
      Image image3X1 = imeFacade.getImage("test3X1");
    } catch (IOException e) {
      fail("Failed to create the test image");
    }
  }

  @Test(expected = InputMismatchException.class)
  public void testGetNonExistentImage() {
    imeFacade.getImage("no-such-image");
  }

  @Test
  public void testValidPPMImage() throws IOException {

    int p3Line = 0;
    int widthAndHeightLine = 1;
    int maxLine = 2;

    try {

      BufferedReader br = new BufferedReader(new FileReader(testImagePath + "2X2.ppm"));
      int cur = 0;
      String line = br.readLine();

      while (line != null) {
        if (cur == p3Line) {
          assertEquals("P3", line);
        } else if (cur == maxLine) {
          assertEquals(Integer.parseInt(line), image.getMax());
        } else if (cur == widthAndHeightLine) {
          String[] temp = line.split(" ");
          int width = Integer.parseInt(temp[0]);
          int height = Integer.parseInt(temp[1]);
          assertEquals(width, image.getWidth());
          assertEquals(height, image.getHeight());
        }
        cur++;
        line = br.readLine();
      }

    } catch (Exception e) {
      fail(e.getMessage());
    }

  }

  @Test(expected = NoSuchElementException.class)
  public void testInvalidPPM0() throws IOException {
    String invalidPPMPath = "C:\\Users\\pnava\\IdeaProjects\\PDP-Assignment-4"
            + "\\test\\res\\invalid0.ppm";
    imeFacade.load(helper.getImageInputStream(invalidPPMPath), "invalid");
  }

  @Test(expected = NoSuchElementException.class)
  public void testInvalidPPM1() throws IOException {
    String invalidPPMPath = "C:\\Users\\pnava\\IdeaProjects\\PDP-Assignment-4"
            + "\\test\\res\\invalid1.ppm";
    imeFacade.load(helper.getImageInputStream(invalidPPMPath), "invalid");
  }

  @Test(expected = NoSuchElementException.class)
  public void testInvalidPPM2() throws IOException {
    String invalidPPMPath = "C:\\Users\\pnava\\IdeaProjects\\PDP-Assignment-4"
            + "\\test\\res\\invalid2.ppm";
    imeFacade.load(helper.getImageInputStream(invalidPPMPath), "invalid");
  }

  @Test(expected = NoSuchElementException.class)
  public void testInvalidPPM3() throws IOException {
    String invalidPPMPath = "C:\\Users\\pnava\\IdeaProjects\\PDP-Assignment-4"
            + "\\test\\res\\invalid3.ppm";
    imeFacade.load(helper.getImageInputStream(invalidPPMPath), "invalid");
  }

  @Test(expected = NoSuchElementException.class)
  public void testInvalidPPM4() throws IOException {
    String invalidPPMPath = "C:\\Users\\pnava\\IdeaProjects\\PDP-Assignment-4"
            + "\\test\\res\\invalid4.ppm";
    imeFacade.load(helper.getImageInputStream(invalidPPMPath), "invalid");
  }

  @Test(expected = NoSuchElementException.class)
  public void testInvalidPPM5() throws IOException {
    String invalidPPMPath = "C:\\Users\\pnava\\IdeaProjects\\PDP-Assignment-4"
            + "\\test\\res\\invalid5.ppm";
    imeFacade.load(helper.getImageInputStream(invalidPPMPath), "invalid");
  }

  @Test(expected = NoSuchElementException.class)
  public void testInvalidPPM6() throws IOException {
    String invalidPPMPath = "C:\\Users\\pnava\\IdeaProjects\\PDP-Assignment-4\\"
            + "test\\res\\invalid6.ppm";
    imeFacade.load(helper.getImageInputStream(invalidPPMPath), "invalid");
  }

  @Test(expected = NoSuchElementException.class)
  public void testInvalidPPM7() throws IOException {
    String invalidPPMPath = "C:\\Users\\pnava\\IdeaProjects\\PDP-Assignment-4"
            + "\\test\\res\\invalid7.ppm";
    imeFacade.load(helper.getImageInputStream(invalidPPMPath), "invalid");
  }

  @Test
  public void testRGBData() {
    try {
      BufferedReader br = new BufferedReader(new FileReader(testImagePath + "2X2.ppm"));
      int cur = 0;
      while (br.readLine() != null && cur < 2) {
        cur++;
      }
      for (int i = 0; i < image.getHeight(); i++) {
        for (int j = 0; j < image.getWidth(); j++) {
          assertEquals(Integer.parseInt(br.readLine()), ((RGBPixel)image.getPixel(i, j)).getRed());
          assertEquals(Integer.parseInt(br.readLine()),
                  ((RGBPixel)image.getPixel(i, j)).getGreen());
          assertEquals(Integer.parseInt(br.readLine()), ((RGBPixel)image.getPixel(i, j)).getBlue());
        }
      }
      if (br.readLine() != null) {
        fail("More pixels than expected in test2X2.ppm"); }
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  protected static class Helper {

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

    public Image getImageFromOutputStream(OutputStream out) {
      Scanner sc = new Scanner(out.toString());
      int width = sc.nextInt();
      int height = sc.nextInt();
      int max = sc.nextInt();
      Image image = new RGBImage(width, height, max);
      for (int i = 0 ; i < height; i++) {
        for (int j = 0; j < width; j++) {
          image.setPixel(i, j, new RGBPixel(sc.nextInt(), sc.nextInt(), sc.nextInt(), max));
        }
      }
      return image;
    }

  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelOutOfBoundsRow() {
    image.getPixel(image.getWidth() + 5, image.getHeight() - 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelOutOfBoundsCol() {
    image.getPixel(image.getWidth() - 1, image.getHeight() + 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelOutOfBoundsRowAndCol() {
    image.getPixel(image.getWidth(), image.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelOutOfBoundsNegative() {
    image.getPixel(-1, -1);
  }

  @Test
  public void testIntensity() {
    imeFacade.intensity("test", "test-intensity");
    Image intensity = imeFacade.getImage("test-intensity");
    assertEquals(image.getWidth(), intensity.getWidth());
    assertEquals(image.getHeight(), intensity.getHeight());
    assertEquals(image.getMax(), intensity.getMax());

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        RGBPixel imagePixel = (RGBPixel) image.getPixel(i, j);
        int expectedAverage = (imagePixel.getRed()
                + imagePixel.getGreen() + imagePixel.getBlue()) / 3;
        RGBPixel intensityPixel = (RGBPixel) intensity.getPixel(i, j);
        assertEquals(expectedAverage, intensityPixel.getRed());
        assertEquals(expectedAverage, intensityPixel.getGreen());
        assertEquals(expectedAverage, intensityPixel.getBlue());
      }
    }

  }

  @Test
  public void testValue() {
    imeFacade.value("test", "test-value");
    Image value = imeFacade.getImage("test-value");
    assertEquals(image.getWidth(), value.getWidth());
    assertEquals(image.getHeight(), value.getHeight());
    assertEquals(image.getMax(), value.getMax());

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        RGBPixel imagePixel = (RGBPixel) image.getPixel(i, j);
        int expectedValue = Math.max(imagePixel.getRed(),
                Math.max(imagePixel.getGreen(), imagePixel.getBlue()));
        RGBPixel valuePixel = (RGBPixel) value.getPixel(i, j);
        assertEquals(expectedValue, valuePixel.getRed());
        assertEquals(expectedValue, valuePixel.getGreen());
        assertEquals(expectedValue, valuePixel.getBlue());
      }
    }
  }

  @Test
  public void testLuma() {
    String[] imageNames = {"test", "test-png", "test-jpg", "test-bmp"};
    Image[] images = {image, imagePNG, imageJPG, imageBMP};
    for (int k = 0; k < 4; k++) {
      String imageName = imageNames[k];
      Image testImage = images[k];
      imeFacade.luma(imageName, "test-luma");
      Image luma = imeFacade.getImage("test-luma");
      assertEquals(testImage.getWidth(), luma.getWidth());
      assertEquals(testImage.getHeight(), luma.getHeight());
      assertEquals(testImage.getMax(), luma.getMax());

      for (int i = 0; i < testImage.getHeight(); i++) {
        for (int j = 0; j < testImage.getWidth(); j++) {
          RGBPixel imagePixel = (RGBPixel) testImage.getPixel(i, j);
          int expectedLuma = (int) Math.round(
              0.2126 * imagePixel.getRed() + 0.7152 * imagePixel.getGreen()
                  + 0.0722 * imagePixel.getBlue());
          RGBPixel lumaPixel = (RGBPixel) luma.getPixel(i, j);
          assertEquals(expectedLuma, lumaPixel.getRed());
          assertEquals(expectedLuma, lumaPixel.getGreen());
          assertEquals(expectedLuma, lumaPixel.getBlue());
        }
      }
    }
  }

  @Test
  public void testRedComponent() {
    imeFacade.redComponent("test", "test-red-component");
    Image redComponent = imeFacade.getImage("test-red-component");
    assertEquals(image.getWidth(), redComponent.getWidth());
    assertEquals(image.getHeight(), redComponent.getHeight());
    assertEquals(image.getMax(), redComponent.getMax());

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        RGBPixel imagePixel = (RGBPixel) image.getPixel(i, j);
        RGBPixel redComponentPixel = (RGBPixel) redComponent.getPixel(i, j);
        assertEquals(imagePixel.getRed(), redComponentPixel.getRed());
        assertEquals(0, redComponentPixel.getGreen());
        assertEquals(0, redComponentPixel.getBlue());
      }
    }
  }

  @Test
  public void testGreenComponent() {
    imeFacade.greenComponent("test", "test-green-component");
    Image greenComponent = imeFacade.getImage("test-green-component");
    assertEquals(image.getWidth(), greenComponent.getWidth());
    assertEquals(image.getHeight(), greenComponent.getHeight());
    assertEquals(image.getMax(), greenComponent.getMax());

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        RGBPixel imagePixel = (RGBPixel) image.getPixel(i, j);
        RGBPixel greenComponentPixel = (RGBPixel) greenComponent.getPixel(i, j);
        assertEquals(0, greenComponentPixel.getRed());
        assertEquals(imagePixel.getGreen(), greenComponentPixel.getGreen());
        assertEquals(0, greenComponentPixel.getBlue());
      }
    }
  }

  @Test
  public void testBlueComponent() {
    imeFacade.blueComponent("test", "test-blue-component");
    Image blueComponent = imeFacade.getImage("test-blue-component");
    assertEquals(image.getWidth(), blueComponent.getWidth());
    assertEquals(image.getHeight(), blueComponent.getHeight());
    assertEquals(image.getMax(), blueComponent.getMax());

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        RGBPixel imagePixel = (RGBPixel) image.getPixel(i, j);
        RGBPixel blueComponentPixel = (RGBPixel) blueComponent.getPixel(i, j);
        assertEquals(0, blueComponentPixel.getRed());
        assertEquals(0, blueComponentPixel.getGreen());
        assertEquals(imagePixel.getBlue(), blueComponentPixel.getBlue());
      }
    }
  }

  @Test
  public void testSplitAndCombine() {

    List<Image> components = new ArrayList<>();
    imeFacade.rgbSplit("test", "test-red", "test-green", "test-blue");
    components.add(imeFacade.getImage("test-red"));
    components.add(imeFacade.getImage("test-green"));
    components.add(imeFacade.getImage("test-blue"));

    for (Image component: components) {
      assertEquals(component.getWidth(), image.getWidth());
      assertEquals(component.getHeight(), image.getHeight());
      assertEquals(component.getMax(), image.getMax());
    }

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        RGBPixel imagePixel = (RGBPixel) image.getPixel(i, j);
        RGBPixel redComponentPixel = (RGBPixel) components.get(0).getPixel(i, j);
        RGBPixel greenComponentPixel = (RGBPixel) components.get(1).getPixel(i, j);
        RGBPixel blueComponentPixel = (RGBPixel) components.get(2).getPixel(i, j);
        assertEquals(imagePixel.getRed(), redComponentPixel.getRed());
        assertEquals(imagePixel.getGreen(), greenComponentPixel.getGreen());
        assertEquals(imagePixel.getBlue(), blueComponentPixel.getBlue());
        assertEquals(redComponentPixel.getBlue(), 0);
        assertEquals(redComponentPixel.getGreen(), 0);
        assertEquals(blueComponentPixel.getRed(), 0);
        assertEquals(blueComponentPixel.getGreen(), 0);
        assertEquals(greenComponentPixel.getRed(), 0);
        assertEquals(greenComponentPixel.getBlue(), 0);
      }
    }

    imeFacade.rgbCombine("test-red", "test-green", "test-blue", "test-combined");
    Image combinedImage = imeFacade.getImage("test-combined");
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        RGBPixel imagePixel = (RGBPixel) image.getPixel(i, j);
        RGBPixel combinedPixel = (RGBPixel) combinedImage.getPixel(i, j);
        assertEquals(imagePixel, combinedPixel);
      }
    }

  }

  @Test(expected = IllegalArgumentException.class)
  public void testCombineThreeImagesOfDifferentSize() {
    imeFacade.rgbCombine("test", "test2X3", "test3X1", "test-combined");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCombineWithTwoImagesOfDifferentSize() {
    imeFacade.rgbCombine("test", "test", "test3X1", "test-combined");
  }

  @Test
  public void testFlipOnce() {
    imeFacade.flipHorizontal("test", "test-horizontal");
    imeFacade.flipVertical("test", "test-vertical");
    Image vertical = imeFacade.getImage("test-vertical");
    Image horizontal = imeFacade.getImage("test-horizontal");

    assertEquals(image.getHeight(), vertical.getHeight());
    assertEquals(image.getWidth(), vertical.getWidth());
    assertEquals(image.getMax(), vertical.getMax());

    assertEquals(image.getHeight(), horizontal.getHeight());
    assertEquals(image.getWidth(), horizontal.getWidth());
    assertEquals(image.getMax(), horizontal.getMax());

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        assertEquals(horizontal.getPixel(i, j),
                image.getPixel(i, Math.abs(image.getWidth() - 1  - j)));
        assertEquals(vertical.getPixel(i, j),
                image.getPixel(Math.abs(image.getHeight() - 1 - i), j));
      }
    }

  }

  @Test
  public void testFlipTwiceSame() {
    imeFacade.flipHorizontal("test", "test-horizontal");
    imeFacade.flipHorizontal("test-horizontal", "test-horizontal-horizontal");
    imeFacade.flipVertical("test", "test-vertical");
    imeFacade.flipVertical("test-vertical", "test-vertical-vertical");
    Image vertical = imeFacade.getImage("test-vertical-vertical");
    Image horizontal = imeFacade.getImage("test-horizontal-horizontal");

    assertEquals(image.getHeight(), vertical.getHeight());
    assertEquals(image.getWidth(), vertical.getWidth());
    assertEquals(image.getMax(), vertical.getMax());

    assertEquals(image.getHeight(), horizontal.getHeight());
    assertEquals(image.getWidth(), horizontal.getWidth());
    assertEquals(image.getMax(), horizontal.getMax());

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        assertEquals(horizontal.getPixel(i, j), image.getPixel(i, j));
        assertEquals(vertical.getPixel(i, j), image.getPixel(i, j));
      }
    }

  }

  @Test
  public void testFlipFourTimes() {
    imeFacade.flipHorizontal("test", "test-horizontal");
    imeFacade.flipVertical("test-horizontal", "test-horizontal-vertical");
    imeFacade.flipHorizontal("test-horizontal-vertical", "test-horizontal-vertical-horizontal");
    imeFacade.flipVertical("test-horizontal-vertical-horizontal",
            "test-horizontal-vertical-horizontal-vertical");
    Image result = imeFacade.getImage("test-horizontal-vertical-horizontal-vertical");

    assertEquals(image.getHeight(), result.getHeight());
    assertEquals(image.getWidth(), result.getWidth());
    assertEquals(image.getMax(), result.getMax());

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        assertEquals(result.getPixel(i, j), image.getPixel(i, j));
      }
    }
  }

  @Test
  public void testBrighten() {

    imeFacade.brighten(5, "test", "test-brighten");
    imeFacade.brighten(-5, "test", "test-darken");

    Image brightened = imeFacade.getImage("test-brighten");
    Image darkened = imeFacade.getImage("test-darken");

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {

        RGBPixel imagePixel = (RGBPixel) image.getPixel(i, j);
        RGBPixel brightenedPixel = (RGBPixel) brightened.getPixel(i, j);
        RGBPixel darkenedPixel = (RGBPixel) darkened.getPixel(i, j);

        assertEquals(Math.min(imagePixel.getRed() + 5, 255), brightenedPixel.getRed());
        assertEquals(Math.min(imagePixel.getBlue() + 5, 255), brightenedPixel.getBlue());
        assertEquals(Math.min(imagePixel.getGreen() + 5, 255), brightenedPixel.getGreen());

        assertEquals(Math.max(0, imagePixel.getRed() - 5), darkenedPixel.getRed());
        assertEquals(Math.max(0, imagePixel.getBlue() - 5), darkenedPixel.getBlue());
        assertEquals(Math.max(0, imagePixel.getGreen() - 5), darkenedPixel.getGreen());

      }
    }

  }

  @Test
  public void testBrightenMinMax() {

    imeFacade.brighten(image.getMax(), "test", "test-brighten");
    imeFacade.brighten(-image.getMax(), "test", "test-darken");

    Image brightened = imeFacade.getImage("test-brighten");
    Image darkened = imeFacade.getImage("test-darken");

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {

        RGBPixel imagePixel = (RGBPixel) image.getPixel(i, j);
        RGBPixel brightenedPixel = (RGBPixel) brightened.getPixel(i, j);
        RGBPixel darkenedPixel = (RGBPixel) darkened.getPixel(i, j);

        assertEquals(image.getMax(), brightenedPixel.getRed());
        assertEquals(image.getMax(), brightenedPixel.getBlue());
        assertEquals(image.getMax(), brightenedPixel.getGreen());

        assertEquals(0, darkenedPixel.getRed());
        assertEquals(0, darkenedPixel.getBlue());
        assertEquals(0, darkenedPixel.getGreen());

      }
    }
  }

  @Test
  public void testSave() throws IOException {

    OutputStream out = imeFacade.save("test");
    Image read = helper.getImageFromOutputStream(out);

    assertEquals(image.getWidth(), read.getWidth());
    assertEquals(image.getHeight(), read.getHeight());
    assertEquals(image.getMax(), read.getMax());

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        assertEquals(image.getPixel(i, j), read.getPixel(i, j));
      }
    }

  }

  @Test
  public void testBlurTwice() throws IOException {
    imeFacade.blur("test", "test-blur-ppm");
    Image actual = imeFacade.getImage("test-blur-ppm");

    String filePath = "C:\\Users\\pnava\\IdeaProjects\\PDP-Assignment-4"
            + "\\test\\res\\test2X2-blur.ppm";
    imeFacade.load(helper.getImageInputStream(filePath), "expected-test-blur-ppm");
    Image expected = imeFacade.getImage("expected-test-blur-ppm");

    assertEquals(expected.getHeight(), actual.getHeight());
    assertEquals(expected.getWidth(), actual.getWidth());
    assertEquals(expected.getMax(), actual.getMax());

    for (int j = 0; j < image.getHeight(); j++) {
      for (int k = 0; k < image.getWidth(); k++) {
        assertEquals(actual.getPixel(j, k), expected.getPixel(j, k));
      }
    }

    imeFacade.blur("test-blur-ppm", "test-blur-blur-ppm");
    actual = imeFacade.getImage("test-blur-blur-ppm");

    filePath = "C:\\Users\\pnava\\IdeaProjects\\PDP-Assignment-4\\test\\res\\test2X2-blur-blur.ppm";
    imeFacade.load(helper.getImageInputStream(filePath), "expected-test-blur-blur-ppm");
    expected = imeFacade.getImage("expected-test-blur-blur-ppm");

    assertEquals(expected.getHeight(), actual.getHeight());
    assertEquals(expected.getWidth(), actual.getWidth());
    assertEquals(expected.getMax(), actual.getMax());

    for (int j = 0; j < image.getHeight(); j++) {
      for (int k = 0; k < image.getWidth(); k++) {
        assertEquals(actual.getPixel(j, k), expected.getPixel(j, k));
      }
    }

  }

  @Test
  public void testSepia() throws IOException {
    imeFacade.sepia("test", "test-sepia-ppm");
    Image actual = imeFacade.getImage("test-sepia-ppm");

    String filePath = "C:\\Users\\pnava\\IdeaProjects\\PDP-Assignment-4"
             + "\\test\\res\\test2X2-sepia.ppm";
    imeFacade.load(helper.getImageInputStream(filePath), "expected-test-sepia-ppm");
    Image expected = imeFacade.getImage("expected-test-sepia-ppm");

    assertEquals(expected.getHeight(), actual.getHeight());
    assertEquals(expected.getWidth(), actual.getWidth());
    assertEquals(expected.getMax(), actual.getMax());

    for (int j = 0; j < image.getHeight(); j++) {
      for (int k = 0; k < image.getWidth(); k++) {
        assertEquals(actual.getPixel(j, k), expected.getPixel(j, k));
      }
    }
  }

  @Test
  public void testSharpen() throws IOException {
    imeFacade.sharpen("test", "test-sharpen-ppm");
    Image actual = imeFacade.getImage("test-sharpen-ppm");

    String filePath = "C:\\Users\\pnava\\IdeaProjects\\PDP-Assignment-4"
            + "\\test\\res\\test2X2-sharpen.ppm";
    imeFacade.load(helper.getImageInputStream(filePath), "expected-test-sharpen-ppm");
    Image expected = imeFacade.getImage("expected-test-sharpen-ppm");

    assertEquals(expected.getHeight(), actual.getHeight());
    assertEquals(expected.getWidth(), actual.getWidth());
    assertEquals(expected.getMax(), actual.getMax());

    for (int j = 0; j < image.getHeight(); j++) {
      for (int k = 0; k < image.getWidth(); k++) {
        assertEquals(actual.getPixel(j, k), expected.getPixel(j, k));
      }
    }
  }

  @Test(expected = InputMismatchException.class)
  public void testBlurNonExistentImage() {
    imeFacade.blur("no-such-image", "blurred");
  }

  @Test(expected = InputMismatchException.class)
  public void testSharpenNonExistentImage() {
    imeFacade.sharpen("no-such-image", "blurred");
  }

  @Test(expected = InputMismatchException.class)
  public void testSepiaNonExistentImage() {
    imeFacade.sepia("no-such-image", "blurred");
  }

  @Test
  public void testGetRawImageData() {

    int[][][] expected = new int[][][] {
        {{243, 244, 255}, {90, 76, 44}},
        {{33, 205, 104}, {12, 35, 56}}
    };

    int[][][] actual = imeFacade.getRawImageData("test");

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(expected[i][j][k], actual[i][j][k]);
        }
      }
    }

  }

}