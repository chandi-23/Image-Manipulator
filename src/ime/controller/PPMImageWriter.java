/**
 * The PPMImageWriter class implements the ImageWriter interface and is used
 * to write PPM images to a file. PPM (Portable Pixmap) is a file format for
 * storing images, and this class handles the writing of P3 format PPM images.
 */

package ime.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * The PPMImageWriter class is responsible for writing P3 format PPM
 * images to a specified file.
 */
public class PPMImageWriter implements ImageWriter {

  private final String token;

  /**
   * Constructs a new PPMImageWriter object. Initializes the P3 token.
   */
  // The P3 token used in PPM images.
  public PPMImageWriter() {
    token = "P3";
  }

  /**
   * Writes an image to the specified output stream and saves it to a file.
   *
   * @param out      The output stream to write the image data to.
   * @param filepath The path to the file where the image should be saved.
   * @throws IOException If there is an error during the image writing process,
   *         or if the provided file path is invalid.
   */
  @Override
  public void writeImage(OutputStream out, String filepath) throws IOException {
    try {
      Scanner sc = new Scanner(out.toString());
      StringBuilder imageData = new StringBuilder();

      int width = sc.nextInt();
      int height = sc.nextInt();
      int max = sc.nextInt();

      imageData.append(this.token).append(System.lineSeparator());
      imageData.append(width).append(" ").append(height).append(System.lineSeparator());
      imageData.append(max).append(System.lineSeparator());

      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          int red = sc.nextInt();
          int green = sc.nextInt();
          int blue = sc.nextInt();
          imageData.append(red).append(System.lineSeparator());
          imageData.append(green).append(System.lineSeparator());
          imageData.append(blue).append(System.lineSeparator());
        }
      }

      FileWriter writer = new FileWriter(filepath);
      writer.write(imageData.toString());
      writer.close();
    } catch (IOException e) {
      throw new IOException("Please input a valid file");
    }

  }
}
