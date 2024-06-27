/**
 * The PPMImageReader class implements the ImageReader interface and is used
 * to read P3 format PPM images from a file. PPM (Portable Pixmap) is a
 * file format for storing images, and this class handles the reading of P3
 * format PPM images.
 */

package ime.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The PPMImageReader class is responsible for reading P3 format PPM images from
 * a specified file and providing the image data as an input stream.
 */
public class PPMImageReader implements ImageReader {

  // The P3 token used in PPM images.
  private final String token;

  /**
   * Constructs a new PPMImageReader object and initializes the P3 token.
   */
  public PPMImageReader() {
    this.token = "P3";
  }

  /**
   * Reads an image from the specified file and provides it as an input stream.
   *
   * @param filepath The path to the file from which the image should be read.
   * @return An input stream containing the image data.
   * @throws IOException If there is an error during the image reading process or if the
   *        provided file path is invalid.
   */
  @Override
  public InputStream readImage(String filepath) throws IOException {
    try {
      String fileContent = FileUtil.cleanFileRead(filepath);

      Scanner sc = new Scanner(fileContent);
      String token = sc.next();
      StringBuilder imageData = new StringBuilder();

      if (!token.equals(this.token)) {
        throw new IOException("Invalid PPM file: plain RAW file should begin with P3");
      }

      while (sc.hasNext()) {
        imageData.append(sc.next()).append(System.lineSeparator());
      }

      return new ByteArrayInputStream(imageData.toString().getBytes());
    } catch (IOException | NoSuchElementException e) {
      throw new IOException("Please input a valid file");
    }

  }
}
