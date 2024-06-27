/**
 * The HistogramGenerator interface defines the contract for classes implementing
 * histogram generation functionality for an image.
 * <p>
 * This interface is designed to be used within the IME framework for image processing.
 * </p>
 */

package ime.model;

/**
 * The HistogramGenerator interface defines the contract for classes implementing
 * histogram generation functionality for an image. This interface is designed to be used
 * within the IME framework for image processing.
 */
interface HistogramGenerator {

  /**
   * Generates a histogram for the provided input image.
   *
   * @param inputImage The image for which to generate a histogram.
   * @return A two-dimensional array representing the histogram data.
   */
  int[][] generate(Image inputImage);

}
