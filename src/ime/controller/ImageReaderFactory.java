/**
 * The ImageReaderFactory interface defines a contract for classes that can create
 * ImageReader instances based on the file path or image information.
 */

package ime.controller;

/**
 * The ImageReaderFactory interface specifies a method for obtaining an ImageReader
 * instance based on the given image extension.
 */
public interface ImageReaderFactory {

  /**
   * A factory methods to get an ImageReader instance suitable for the specified image extension.
   *
   * @param imagePath The path to the image for which an ImageReader is needed.
   * @return An ImageReader instance capable of reading the specified image.
   * @throws IllegalArgumentException If the image path is invalid or not supported.
   */
  ImageReader getImageReader(String imagePath) throws IllegalArgumentException;
}
