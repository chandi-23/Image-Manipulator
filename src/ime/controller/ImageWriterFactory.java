/**
 * The ImageWriterFactory interface defines a contract for classes that
 * provide ImageWriter instances based on the file path or image information.
 */

package ime.controller;

/**
 * The ImageWriterFactory interface specifies a method for obtaining an ImageWriter
 * instance based on the given image extension.
 */
public interface ImageWriterFactory {

  /**
   * A factory method that gets an ImageWriter instance suitable for the specified image extension.
   *
   * @param imagePath The path to the image for which an ImageWriter is needed.
   * @return An ImageWriter instance that can handle the specified image.
   */
  ImageWriter getImageWriter(String imagePath);
}
