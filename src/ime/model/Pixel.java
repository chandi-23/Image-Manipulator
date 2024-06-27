/**
 * The Pixel interface represents a single pixel in an image. It provides methods for retrieving
 * pixel attributes and performing pixel-level operations.
 */

package ime.model;

/**
 * The Pixel interface defines methods for retrieving pixel attributes and performing pixel-level
 * operations such as brightening.
 */
interface Pixel {

  /**
   * Gets the luma value of the pixel. Luma represents the brightness or gray level of the pixel.
   *
   * @return The luma value of the pixel.
   */
  int getLuma();

  /**
   * Gets the value of the pixel. Value is a color attribute representing the grayscale
   * value of the pixel.
   *
   * @return The value of the pixel.
   */
  int getValue();

  /**
   * Gets the intensity of the pixel. Intensity represents the overall intensity of the
   * pixel's color.
   *
   * @return The intensity of the pixel.
   */
  int getIntensity();

  /**
   * Brightens the pixel by the specified increment.
   *
   * @param increment The amount by which to brighten the pixel.
   * @return A new Pixel object representing the brightened pixel.
   */
  Pixel brighten(int increment);

}
