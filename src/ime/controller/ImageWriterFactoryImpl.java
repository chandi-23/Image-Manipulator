/**
 * The ImageWriterFactoryImpl class implements the ImageWriterFactory
 * interface and is responsible for providing ImageWriter instances
 * based on the file extension of the image being written. It determines the
 * appropriate ImageWriter implementation to use based on the file extension.
 */

package ime.controller;

/**
 * The ImageWriterFactoryImpl class provides methods for creating ImageWriter instances
 * based on the file extension.
 */
public class ImageWriterFactoryImpl implements ImageWriterFactory {

  /**
   * A factory method that retrieves the appropriate ImageWriter based on the
   * file extension of the image.
   *
   * @param imagePath The path to the image for which an ImageWriter is needed.
   * @return An ImageWriter instance suitable for the specified image file extension.
   * @throws IllegalArgumentException If the file extension is not supported or if it
   *          cannot be determined from the given imagePath.
   */
  @Override
  public ImageWriter getImageWriter(String imagePath) {
    String fileExtension = FileUtil.getFileExtension(imagePath);
    switch (fileExtension) {
      case "ppm":
        return new PPMImageWriter();
      case "jpg":
      case "jpeg":
      case "png":
      case "bmp":
        return new ImageIOWriter();
      default:
        throw new IllegalArgumentException("File extension not supported : " + fileExtension);
    }
  }
}
