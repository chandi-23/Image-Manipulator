/**
 * The ImageReader interface defines a contract for classes that can read image data from
 * an input stream, typically from a file specified by its path.
 */

package ime.controller;

import java.io.IOException;
import java.io.InputStream;

/**
 * The ImageReader interface specifies a method for reading image data from an input stream,
 * which is usually loaded from a file.
 */
public interface ImageReader {

  /**
   * Reads image data from the specified file and provides it as an input stream.
   *
   * @param filepath The path to the file from which the image data should be read.
   * @return An input stream containing the image data.
   * @throws IOException If there is an error during the image reading process or if
   *        the file path is invalid.
   */
  InputStream readImage(String filepath) throws IOException;
}
