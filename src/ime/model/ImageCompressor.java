package ime.model;

/**
 * The {@code ImageCompressor} interface defines the contract for compressing images with
 * a specified compression percentage.
 *
 * <p>Implementations of this interface are responsible for compressing an image according to the
 * provided compression percentage, resulting in a new compressed image.
 */
interface ImageCompressor {

  /**
   * Compresses the image with the specified compression percentage.
   *
   * @param compressionPercentage The percentage by which to compress the image.
   * @return A new {@code Image} representing the compressed version of the original image.
   */
  Image compress(double compressionPercentage);

}
