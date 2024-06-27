/**
 * The ImageIOWriter class implements the ImageWriter interface and is used to write
 * images using Java's ImageIO library. It converts image data from an input stream
 * into a common image format and saves it to a specified file path.
 */

package ime.controller;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * The ImageIOWriter class is responsible for writing images in various formats using Java's
 *        ImageIO library. Supported image extensions are "bmp", "jpg" and "png".
 */
public class ImageIOWriter implements ImageWriter {

  /**
   * Writes image data from an input stream to a file using Java's ImageIO library.
   *
   * @param out      The input stream containing image data to be written.
   * @param filepath The path to the file where the image should be saved.
   * @throws IOException If there is an error during the image writing process or if the
   *        file path is invalid.
   */
  @Override
  public void writeImage(OutputStream out, String filepath) throws IOException {

    try {
      Scanner sc = new Scanner(out.toString());
      int width = sc.nextInt();
      int height = sc.nextInt();
      int max = sc.nextInt();
      BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      WritableRaster writableRaster = bufferedImage.getRaster();

      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          int red = sc.nextInt();
          int green = sc.nextInt();
          int blue = sc.nextInt();
          writableRaster.setPixel(x, y, new int[]{red, green, blue});
        }
      }

      String fileExtension = FileUtil.getFileExtension(filepath);

      ImageIO.write(bufferedImage, fileExtension, new File(filepath));
    } catch (IOException e) {
      throw new IOException("Please input a valid file");
    }

  }
}
