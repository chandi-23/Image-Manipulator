/**
 * The ImageWriter interface defines a contract for classes that can write
 * image data to an output stream and save it to a specified file path.
 */

package ime.controller;

import java.io.IOException;
import java.io.OutputStream;

/**
 * The ImageWriter interface specifies a method for writing image data to
 * an output stream and saving it to a file.
 */
public interface ImageWriter {

  /**
   * Writes the image data to the provided output stream and saves it to
   * the specified file path.
   *
   * @param out      The output stream to which the image data should be written.
   * @param filepath The path to the file where the image data should be saved.
   * @throws IOException If there is an error during the image writing process
   *          or if the file path is invalid.
   */
  void writeImage(OutputStream out, String filepath) throws IOException;
}
