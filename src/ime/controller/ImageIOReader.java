/**
 * The ImageIOReader class implements the ImageReader interface and is used to read images
 * using Java's ImageIO library. It converts image data from a specified file into an input stream.
 */

package ime.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import javax.imageio.ImageIO;

/**
 * The ImageIOReader class is responsible for reading images in various formats using Java's
 *        ImageIO library. Supported image extensions are "bmp", "jpg" and "png".
 */
public class ImageIOReader implements ImageReader {

  /**
   * Reads image data from the specified file and provides it as an input stream.
   *
   * @param filepath The path to the image file to be read.
   * @return An input stream containing the image data.
   * @throws IOException If there is an error during the image reading process or if the
   *        file path is invalid.
   */
  @Override
  public InputStream readImage(String filepath) throws IOException {
    try {
      BufferedImage bufferedImage = ImageIO.read(new File(filepath));
      StringBuilder imageRGB = new StringBuilder();
      int max = 0;
      int width = bufferedImage.getWidth();
      int height = bufferedImage.getHeight();

      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          int pixel = bufferedImage.getRGB(x, y);
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
    } catch (IOException | NoSuchElementException e) {
      throw new IOException("Please input a valid file");
    }

  }
}
