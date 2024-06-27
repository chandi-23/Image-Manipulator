/**
 * The MIMEIntegrationTest class contains integration test cases for the
 * MIME (Image Manipulation and Enhancement) system, validating the interaction and
 * functionality of key components, including the Controller, CommandController,
 * and MIMECommandRepository.
 */

package ime.model;

import ime.controller.CommandController;
import ime.controller.Controller;
import ime.controller.MIMECommandRepository;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * The MIMEIntegrationTest class contains integration test cases for the
 * MIME (Image Manipulation and Enhancement) system, validating the interaction and
 * functionality of key components, including the Controller, CommandController,
 * and MIMECommandRepository.
 */
public class MIMEIntegrationTest {

  @Test
  public void testMultipleOperations() throws IOException {

    IMEFacadeImpl mimeFacade = new MIMEFacadeImpl();
    Controller controller;
    String testImagePath = "C:\\Users\\pnava\\IdeaProjects\\PDP-Assignment-4\\test\\res\\";

    // Load
    StringBuilder out = new StringBuilder();
    controller = new CommandController(new StringReader("load "
            + testImagePath + "test2X2.png test"),
            out, new MIMECommandRepository());
    controller.goAhead(mimeFacade);
    assertEquals(out.toString().strip(), "Operation completed: load");

    // histogram
    out = new StringBuilder();
    controller = new CommandController(new StringReader("histogram test test-hist"),
            out, new MIMECommandRepository());
    controller.goAhead(mimeFacade);

    IMEFacadeImplTest.Helper helper2 = new IMEFacadeImplTest.Helper();
    mimeFacade.load(helper2.getImageInputStream(testImagePath + "test2X2-hist.png"),
            "expected-histogram");
    Image expected = mimeFacade.getImage("expected-histogram");


    // Image test = mimeFacade.getImage("test");
    Image testHist = mimeFacade.getImage("test-hist");

    assertEquals(expected.getMax(), testHist.getMax());
    assertEquals(expected.getWidth(), testHist.getWidth());
    assertEquals(expected.getHeight(), testHist.getHeight());

    for (int i = 0; i < expected.getHeight(); i++) {
      for (int j = 0; j < expected.getWidth(); j++) {
        assertEquals(testHist.getPixel(i, j), expected.getPixel(i, j));
      }
    }

    assertEquals(out.toString().strip(), "Operation completed: histogram");

    // ColorCorrect
    out = new StringBuilder();
    controller = new CommandController(new StringReader("color-correct test test-cc"),
            out, new MIMECommandRepository());
    controller.goAhead(mimeFacade);

    helper2 = new IMEFacadeImplTest.Helper();
    mimeFacade.load(helper2.getImageInputStream(testImagePath + "test2X2-cc.png"),
            "expected-cc");
    Image expectedCC = mimeFacade.getImage("expected-cc");


    // Image test = mimeFacade.getImage("test");
    Image testCC = mimeFacade.getImage("test-cc");

    assertEquals(255, testCC.getMax());
    assertEquals(expectedCC.getWidth(), testCC.getWidth());
    assertEquals(expectedCC.getHeight(), testCC.getHeight());

    for (int i = 0; i < expectedCC.getHeight(); i++) {
      for (int j = 0; j < expectedCC.getWidth(); j++) {
        assertEquals(testCC.getPixel(i, j), expectedCC.getPixel(i, j));
      }
    }

    assertEquals(out.toString().strip(), "Operation completed: color-correct");


    // ColorCorrect with Split
    out = new StringBuilder();
    controller = new CommandController(new StringReader("color-correct test "
            + "test-cc-split split 50"), out, new MIMECommandRepository());
    controller.goAhead(mimeFacade);

    // helper2 = new IMEFacadeImplTest.Helper();;
    mimeFacade.load(helper2.getImageInputStream(testImagePath + "test2X2-cc-split.png"),
            "expected-cc-split");
    Image expectedCCS = mimeFacade.getImage("expected-cc-split");

    Image testCCS = mimeFacade.getImage("test-cc-split");

    assertEquals(expectedCCS.getWidth(), testCCS.getWidth());
    assertEquals(expectedCCS.getHeight(), testCCS.getHeight());
    assertEquals(255, testCCS.getMax());

    for (int i = 0; i < testCCS.getHeight(); i++) {
      for (int j = 0; j < testCCS.getWidth(); j++) {
        assertEquals(testCCS.getPixel(i, j), expectedCCS.getPixel(i, j));
      }
    }

    // Compress
    out = new StringBuilder();
    controller = new CommandController(new StringReader("load " + testImagePath
            + "test3X1.ppm test-cmp"),
            out, new MIMECommandRepository());
    controller.goAhead(mimeFacade);
    assertEquals(out.toString().strip(), "Operation completed: load");

    out = new StringBuilder();
    controller = new CommandController(new StringReader("compress 50 test-cmp test-compress"),
            out, new MIMECommandRepository());
    controller.goAhead(mimeFacade);

    mimeFacade.load(helper2.getImageInputStream(testImagePath + "test-compress-50.ppm"),
            "expected-test-compress-50");
    Image expectedCmp = mimeFacade.getImage("expected-test-compress-50");

    Image testCompress = mimeFacade.getImage("test-compress");

    assertEquals(expectedCmp.getWidth(), testCompress.getWidth());
    assertEquals(expectedCmp.getHeight(), testCompress.getHeight());
    assertEquals(expectedCmp.getMax(), testCompress.getMax());

    for (int i = 0; i < testCompress.getHeight(); i++) {
      for (int j = 0; j < testCompress.getWidth(); j++) {
        assertEquals(testCompress.getPixel(i, j), expectedCmp.getPixel(i, j));
      }
    }

    // Blur with Split
    out = new StringBuilder();
    controller = new CommandController(new StringReader("blur test test-blr-split split 50"),
            out, new MIMECommandRepository());
    controller.goAhead(mimeFacade);

    mimeFacade.load(helper2.getImageInputStream(testImagePath + "test2X2-blr-split.png"),
            "expected-split-blur-image");
    Image expectedBS = mimeFacade.getImage("expected-split-blur-image");

    Image testBlrSplit = mimeFacade.getImage("test-blr-split");

    assertEquals(expectedBS.getWidth(), testBlrSplit.getWidth());
    assertEquals(expectedBS.getHeight(), testBlrSplit.getHeight());
    assertEquals(255, testBlrSplit.getMax());

    for (int i = 0; i < testBlrSplit.getHeight(); i++) {
      for (int j = 0; j < testBlrSplit.getWidth(); j++) {
        assertEquals(testBlrSplit.getPixel(i, j), expectedBS.getPixel(i, j));
      }
    }

    // LevelsAdjust
    out = new StringBuilder();
    controller = new CommandController(new StringReader("levels-adjust 10 100 180 test test-la"),
            out, new MIMECommandRepository());
    controller.goAhead(mimeFacade);

    mimeFacade.load(helper2.getImageInputStream(testImagePath + "test-lvl-adj.png"),
            "expected-test-lvl-adjust");
    Image expectedLA = mimeFacade.getImage("expected-test-lvl-adjust");

    Image testLA = mimeFacade.getImage("test-la");

    assertEquals(expectedLA.getWidth(), testLA.getWidth());
    assertEquals(expectedLA.getHeight(), testLA.getHeight());
    assertEquals(255, testLA.getMax());

    for (int i = 0; i < testLA.getHeight(); i++) {
      for (int j = 0; j < testLA.getWidth(); j++) {
        assertEquals(testLA.getPixel(i, j), expectedLA.getPixel(i, j));
      }
    }

  }

}
