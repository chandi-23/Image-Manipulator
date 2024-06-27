package ime.model;

import ime.controller.CommandController;
import ime.controller.IMECommandRepository;
import ime.controller.Controller;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * This is class to perform integration test between model-controller.
 */
public class IMEIntegrationTest {

  @Test
  public void testInvalidOperation() throws IOException {

    IMEFacade imeFacade = new IMEFacadeImpl();
    StringBuilder out = new StringBuilder();
    Controller controller = new CommandController(new StringReader("load C:\\Users\\pnava\\"
            + "IdeaProjects\\PDP-Assignment-4\\test\\res\\test2X2.ppm test1"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    controller = new CommandController(new StringReader("load C:\\Users\\pnava\\"
            + "IdeaProjects\\PDP-Assignment-4\\test\\res\\test2X3.ppm test2"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    controller = new CommandController(new StringReader("load C:\\Users\\pnava\\"
            + "IdeaProjects\\PDP-Assignment-4\\test\\res\\test3X1.ppm test3"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    out = new StringBuilder();
    controller = new CommandController(new StringReader("rgb-combine combined "
            + "test1 test2 test3"), out, new IMECommandRepository());
    controller.goAhead(imeFacade);
    assertEquals(out.toString().strip(), "Combine operation requires images of same dimension");

  }

  @Test
  public void testMultipleOperations() throws IOException {

    IMEFacadeImpl imeFacade = new IMEFacadeImpl();
    Controller controller;

    // Load
    StringBuilder out = new StringBuilder();
    controller = new CommandController(new StringReader("load C:\\Users\\pnava\\"
            + "IdeaProjects\\PDP-Assignment-4\\test\\res\\test2X2.ppm test"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);
    assertEquals(out.toString().strip(), "Operation completed: load");

    // Brighten
    out = new StringBuilder();
    controller = new CommandController(new StringReader("brighten 10 test test-brighter"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    Image test = imeFacade.getImage("test");
    Image testBrighter = imeFacade.getImage("test-brighter");

    assertEquals(test.getMax(), testBrighter.getMax());
    assertEquals(test.getWidth(), testBrighter.getWidth());
    assertEquals(test.getHeight(), testBrighter.getHeight());

    for (int i = 0; i < test.getHeight(); i++) {
      for (int j = 0; j < test.getWidth(); j++) {
        assertEquals(test.getPixel(i, j).brighten(10), testBrighter.getPixel(i, j));
      }
    }

    assertEquals(out.toString().strip(), "Operation completed: brighten");

    // Flip
    out = new StringBuilder();
    controller = new CommandController(new StringReader("horizontal-flip test "
            + "test-horizontal"), out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    Image testHorizontal = imeFacade.getImage("test-horizontal");
    assertEquals(test.getMax(), testHorizontal.getMax());
    assertEquals(test.getWidth(), testHorizontal.getWidth());
    assertEquals(test.getHeight(), testHorizontal.getHeight());

    for (int i = 0; i < test.getHeight(); i++) {
      for (int j = 0; j < test.getWidth(); j++) {
        assertEquals(test.getPixel(i, Math.abs(test.getWidth() - 1 - j)),
                testHorizontal.getPixel(i, j));
      }
    }

    assertEquals(out.toString().strip(), "Operation completed: horizontal-flip");

    // Component
    out = new StringBuilder();
    controller = new CommandController(new StringReader("value test test-value"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    Image testValue = imeFacade.getImage("test-value");
    assertEquals(test.getMax(), testValue.getMax());
    assertEquals(test.getWidth(), testValue.getWidth());
    assertEquals(test.getHeight(), testValue.getHeight());

    for (int i = 0; i < test.getHeight(); i++) {
      for (int j = 0; j < test.getWidth(); j++) {
        RGBPixel imagePixel = (RGBPixel) test.getPixel(i, j);
        int expectedValue = Math.max(imagePixel.getRed(),
                Math.max(imagePixel.getGreen(), imagePixel.getBlue()));
        RGBPixel valuePixel = (RGBPixel) testValue.getPixel(i, j);
        assertEquals(expectedValue, valuePixel.getRed());
        assertEquals(expectedValue, valuePixel.getGreen());
        assertEquals(expectedValue, valuePixel.getBlue());
      }
    }

    assertEquals(out.toString().strip(), "Operation completed: value");

    // Split

    out = new StringBuilder();
    controller = new CommandController(new StringReader("rgb-split test test-red "
            + "test-green test-blue"), out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    List<Image> components = new ArrayList<>();
    components.add(imeFacade.getImage("test-red"));
    components.add(imeFacade.getImage("test-green"));
    components.add(imeFacade.getImage("test-blue"));

    for (Image component: components) {
      assertEquals(component.getWidth(), test.getWidth());
      assertEquals(component.getHeight(), test.getHeight());
      assertEquals(component.getMax(), test.getMax());
    }

    for (int i = 0; i < test.getHeight(); i++) {
      for (int j = 0; j < test.getWidth(); j++) {
        RGBPixel imagePixel = (RGBPixel) test.getPixel(i, j);
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

    assertEquals(out.toString().strip(), "Operation completed: rgb-split");

    // Combine

    out = new StringBuilder();
    controller = new CommandController(new StringReader("rgb-combine test-combined "
            + "test-red test-green test-blue"), out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    Image combinedImage = imeFacade.getImage("test-combined");
    for (int i = 0; i < test.getHeight(); i++) {
      for (int j = 0; j < combinedImage.getWidth(); j++) {
        RGBPixel imagePixel = (RGBPixel) test.getPixel(i, j);
        RGBPixel combinedPixel = (RGBPixel) combinedImage.getPixel(i, j);
        assertEquals(imagePixel, combinedPixel);
      }
    }

    assertEquals(out.toString().strip(), "Operation completed: rgb-combine");

  }

  @Test
  public void testMultipleOperationDifferentFileTypes() throws IOException {

    IMEFacadeImpl imeFacade = new IMEFacadeImpl();
    Controller controller;

    // Load
    StringBuilder out = new StringBuilder();
    controller = new CommandController(new StringReader("load C:\\Users\\pnava\\"
            + "IdeaProjects\\PDP-Assignment-4\\test\\res\\test2X2.ppm test"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    controller = new CommandController(new StringReader("save C:\\Users\\pnava\\"
             + "IdeaProjects\\PDP-Assignment-4\\test\\res\\test2X2.jpg test"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    controller = new CommandController(new StringReader("save C:\\Users\\pnava\\"
            + "IdeaProjects\\PDP-Assignment-4\\test\\res\\test2X2.bmp test"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    controller = new CommandController(new StringReader("save C:\\Users\\pnava\\"
            + "IdeaProjects\\PDP-Assignment-4\\test\\res\\test2X2.png test"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    controller = new CommandController(new StringReader("load C:\\Users\\pnava\\"
            + "IdeaProjects\\PDP-Assignment-4\\test\\res\\test2X2.jpg test-jpg"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    controller = new CommandController(new StringReader("load C:\\Users\\pnava\\"
            + "IdeaProjects\\PDP-Assignment-4\\test\\res\\test2X2.bmp test-bmp"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    controller = new CommandController(new StringReader("load C:\\Users\\pnava\\"
             + "IdeaProjects\\PDP-Assignment-4\\test\\res\\test2X2.png test-png"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    Image testPPM = imeFacade.getImage("test");
    Image testJPG = imeFacade.getImage("test-jpg");
    Image testPNG = imeFacade.getImage("test-png");
    Image testBMP = imeFacade.getImage("test-bmp");

    // Flip
    out = new StringBuilder();
    controller = new CommandController(new StringReader("horizontal-flip test-png "
            + "test-png-horizontal"), out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    Image testHorizontal = imeFacade.getImage("test-png-horizontal");
    assertEquals(testPNG.getMax(), testHorizontal.getMax());
    assertEquals(testPNG.getWidth(), testHorizontal.getWidth());
    assertEquals(testPNG.getHeight(), testHorizontal.getHeight());

    for (int i = 0; i < testPNG.getHeight(); i++) {
      for (int j = 0; j < testPNG.getWidth(); j++) {
        assertEquals(testPNG.getPixel(i, Math.abs(testPNG.getWidth() - 1 - j)),
                testHorizontal.getPixel(i, j));
      }
    }

    assertEquals(out.toString().strip(), "Operation completed: horizontal-flip");

    // Component
    out = new StringBuilder();
    controller = new CommandController(new StringReader("value test-jpg test-jpg-value"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    Image testValue = imeFacade.getImage("test-jpg-value");
    assertEquals(testJPG.getMax(), testValue.getMax());
    assertEquals(testJPG.getWidth(), testValue.getWidth());
    assertEquals(testJPG.getHeight(), testValue.getHeight());

    for (int i = 0; i < testJPG.getHeight(); i++) {
      for (int j = 0; j < testJPG.getWidth(); j++) {
        RGBPixel imagePixel = (RGBPixel) testJPG.getPixel(i, j);
        int expectedValue = Math.max(imagePixel.getRed(),
                Math.max(imagePixel.getGreen(), imagePixel.getBlue()));
        RGBPixel valuePixel = (RGBPixel) testValue.getPixel(i, j);
        assertEquals(expectedValue, valuePixel.getRed());
        assertEquals(expectedValue, valuePixel.getGreen());
        assertEquals(expectedValue, valuePixel.getBlue());
      }
    }

    assertEquals(out.toString().strip(), "Operation completed: value");

  }

  @Test
  public void testLoadAndSaveDifferentFileTypes() throws IOException {

    IMEFacadeImpl imeFacade = new IMEFacadeImpl();
    StringBuilder out = new StringBuilder();
    Controller controller = new CommandController(new StringReader("load C:\\Users\\"
            + "pnava\\IdeaProjects\\PDP-Assignment-4\\test\\res\\test2X2.ppm test"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    controller = new CommandController(new StringReader("save C:\\Users\\pnava\\"
             + "IdeaProjects\\PDP-Assignment-4\\test\\res\\test2X2.jpg test"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    controller = new CommandController(new StringReader("save C:\\Users\\pnava\\"
             + "IdeaProjects\\PDP-Assignment-4\\test\\res\\test2X2.bmp test"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    controller = new CommandController(new StringReader("save C:\\Users\\pnava\\"
            + "IdeaProjects\\PDP-Assignment-4\\test\\res\\test2X2.png test"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    assertTrue(new File("C:\\Users\\pnava\\IdeaProjects\\PDP-Assignment-4"
            + "\\test\\res\\test2X2.png").exists());
    assertTrue(new File("C:\\Users\\pnava\\IdeaProjects\\PDP-Assignment-4"
            + "\\test\\res\\test2X2.jpg").exists());
    assertTrue(new File("C:\\Users\\pnava\\IdeaProjects\\PDP-Assignment-4"
             + "\\test\\res\\test2X2.bmp").exists());

    controller = new CommandController(new StringReader("load C:\\Users\\pnava"
            + "\\IdeaProjects\\PDP-Assignment-4\\test\\res\\test2X2.jpg test-jpg"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    controller = new CommandController(new StringReader("load C:\\Users\\pnava\\"
            + "IdeaProjects\\PDP-Assignment-4\\test\\res\\test2X2.bmp test-bmp"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    controller = new CommandController(new StringReader("load C:\\Users\\pnava"
            + "\\IdeaProjects\\PDP-Assignment-4\\test\\res\\test2X2.png test-png"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    Image testPPM = imeFacade.getImage("test");
    Image testJPG = imeFacade.getImage("test-jpg");
    Image testPNG = imeFacade.getImage("test-png");
    Image testBMP = imeFacade.getImage("test-bmp");

    assertEquals(testJPG.getHeight(), testPPM.getHeight());
    assertEquals(testJPG.getWidth(), testPPM.getWidth());

    assertEquals(testPNG.getHeight(), testPPM.getHeight());
    assertEquals(testPNG.getWidth(), testPPM.getWidth());

    assertEquals(testBMP.getHeight(), testPPM.getHeight());
    assertEquals(testBMP.getWidth(), testPPM.getWidth());

    for (int i = 0 ; i < testPPM.getHeight(); i++) {
      for (int j = 0; j < testPPM.getWidth(); j++) {
        assertEquals(testBMP.getPixel(i, j), testPPM.getPixel(i, j));
        assertEquals(testPNG.getPixel(i, j), testPPM.getPixel(i, j));
      }
    }

  }

  @Test
  public void testValidAndInvalidSave() throws IOException {
    IMEFacade imeFacade = new IMEFacadeImpl();
    Controller controller;

    StringBuilder out = new StringBuilder();
    controller = new CommandController(new StringReader("load C:\\Users\\pnava"
             + "\\IdeaProjects\\PDP-Assignment-4\\test\\res\\test2X2.ppm test"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);

    out = new StringBuilder();
    controller = new CommandController(new StringReader("save res/test.ppm test1"),
            out, new IMECommandRepository());
    controller.goAhead(imeFacade);
    assertEquals(out.toString().strip(), "Image with the given name not present");

  }

  @Test
  public void testInvalidLoad() throws IOException {
    IMEFacade imeFacade;
    Controller controller;
    imeFacade = new IMEFacadeImpl();
    List<String> invalidPPMFilePaths = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      invalidPPMFilePaths.add("C:\\Users\\pnava\\IdeaProjects\\PDP-Assignment-4"
              + "\\test\\res\\invalid" + i + ".ppm");
    }
    for (String invalidPPMFilePath: invalidPPMFilePaths) {
      StringBuilder out = new StringBuilder();
      controller = new CommandController(new StringReader("load "
              + invalidPPMFilePath + " invalid"), out, new IMECommandRepository());
      controller.goAhead(imeFacade);
      assertEquals(out.toString().strip(), "Please input a valid file");
    }
  }

}
