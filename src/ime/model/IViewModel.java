package ime.model;

/**
 * The IViewModel interface defines methods for obtaining image data represented
 * as a three-dimensional array of integers. Each element in the array corresponds
 * to a pixel in the image, containing information about its color components.
 */
public interface IViewModel {

  /**
   * Retrieves the image data for the specified image name.
   *
   * @param imageName The name or identifier of the image.
   * @return A three-dimensional array representing the image, where each element
   *         contains color component information for a pixel.
   *         The first dimension represents rows, the second dimension represents
   *         columns, and the third dimension represents color components (e.g., RGB).
   */
  int[][][] getImage(String imageName);

}
