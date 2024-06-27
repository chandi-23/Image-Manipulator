/**
 * The ImageProcessor interface represents a component capable of processing an input
 * image and producing an output image.
 */

package ime.model;

/**
 * The ImageProcessor interface defines the contract for components responsible for image
 * processing within the application.
 */
interface ImageProcessor {

  /**
   * Processes the input image and generates an output image as a result of the processing.
   *
   * @param inputImage The input Image object to be processed.
   * @return An Image object representing the result of the processing.
   */
  Image process(Image inputImage);
}
