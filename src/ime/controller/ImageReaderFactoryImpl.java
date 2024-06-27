/**
 * The ImageReaderFactoryImpl class implements the ImageReaderFactory interface
 * and provides methods for creating ImageReader instances based on the file
 * extension of the image being read.
 */

package ime.controller;

/**
 * The ImageReaderFactoryImpl class offers a method to create an ImageReader
 * based on the file extension of the image file.
 */
public class ImageReaderFactoryImpl implements ImageReaderFactory {

  /**
   * A factory method that returns an appropriate ImageReader instance based on
   * the file extension of the image.
   *
   * @param imagePath The path to the image for which an ImageReader is needed.
   * @return An ImageReader instance suitable for the specified image file
   *         extension.
   * @throws IllegalArgumentException If the file extension is not supported or if
   *                                  it cannot be determined from the given
   *                                  imagePath.
   */
  @Override
  public ImageReader getImageReader(String imagePath) throws IllegalArgumentException {
    String fileExtension = FileUtil.getFileExtension(imagePath).strip();
    switch (fileExtension) {
      case "ppm":
        return new PPMImageReader();
      case "jpg":
      case "png":
      case "jpeg":
      case "bmp":
        return new ImageIOReader();
      default:
        throw new IllegalArgumentException("File extension not supported : " + fileExtension);
    }
  }
}
