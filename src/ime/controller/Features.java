package ime.controller;

import java.io.IOException;

/**
 * Features interface defines callback functions for loading, saving,
 * and manipulating images from the VIEW.
 */
public interface Features {

  /**
   * Load an image from the specified file path.
   *
   * @param filepath The path to the image file.
   * @throws IOException If an I/O error occurs while reading the image file.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   */
  void load(String filepath) throws IOException, CloneNotSupportedException;

  /**
   * Save the current image to the specified file path.
   *
   * @param filepath The path to save the image.
   * @throws IOException If an I/O error occurs while writing the image file.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   */
  void save(String filepath) throws IOException, CloneNotSupportedException;

  /**
   * Get the red component of the image.
   *
   * @throws IOException If an I/O error occurs.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   */
  void redComponent() throws IOException, CloneNotSupportedException;

  /**
   * Get the green component of the image.
   *
   * @throws IOException If an I/O error occurs.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   */
  void greenComponent() throws IOException, CloneNotSupportedException;

  /**
   * Get the blue component of the image.
   *
   * @throws IOException If an I/O error occurs.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   */
  void blueComponent() throws IOException, CloneNotSupportedException;

  /**
   * Get the value of the image.
   *
   * @throws IOException If an I/O error occurs.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   */
  void value() throws IOException, CloneNotSupportedException;

  /**
   * Get the intensity of the image.
   *
   * @throws IOException If an I/O error occurs.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   */
  void intensity() throws IOException, CloneNotSupportedException;

  /**
   * Get the luma value of the image.
   *
   * @throws IOException If an I/O error occurs.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   */
  void luma() throws IOException, CloneNotSupportedException;

  /**
   * Get the luma value of the image with a specified split percentage.
   *
   * @param splitPercentage The percentage at which to split the luma calculation.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   * @throws IOException If an I/O error
   */
  void luma(double splitPercentage) throws CloneNotSupportedException, IOException;

  /**
   * Flip the image horizontally.
   *
   * @throws IOException If an I/O error occurs.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   */
  void flipHorizontal() throws IOException, CloneNotSupportedException;

  /**
   * Flip the image vertically.
   *
   * @throws IOException If an I/O error occurs.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   */
  void flipVertical() throws IOException, CloneNotSupportedException;

  /**
   * Apply a blur effect to the image.
   *
   * @throws IOException If an I/O error occurs.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   */
  void blur() throws IOException, CloneNotSupportedException;

  /**
   * Apply a blur effect to the image with a specified split percentage.
   *
   * @param splitPercentage The percentage at which to split the blur effect.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   * @throws IOException If an I/O error occurs.
   */
  void blur(double splitPercentage) throws CloneNotSupportedException, IOException;

  /**
   * Apply a sharpening effect to the image.
   *
   * @throws IOException If an I/O error occurs.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   */
  void sharpen() throws IOException, CloneNotSupportedException;

  /**
   * Apply a sharpening effect to the image with a specified split percentage.
   *
   * @param splitPercentage The percentage at which to split the sharpening effect.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   * @throws IOException If an I/O error occurs.
   */
  void sharpen(double splitPercentage) throws CloneNotSupportedException, IOException;

  /**
   * Apply a sepia effect to the image.
   *
   * @throws IOException If an I/O error occurs.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   */
  void sepia() throws IOException, CloneNotSupportedException;

  /**
   * Apply a sepia tone effect to the image with a specified split percentage.
   *
   * @param splitPercentage The percentage at which to split the sepia effect.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   * @throws IOException If an I/O error occurs.
   */
  void sepia(double splitPercentage) throws CloneNotSupportedException, IOException;

  /**
   * Compress the image by a specified percentage.
   *
   * @param percentage The percentage by which to compress the image.
   * @throws IllegalArgumentException If the percentage is invalid.
   * @throws IOException If an I/O error occurs.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   */
  void compress(double percentage)
      throws IllegalArgumentException, IOException, CloneNotSupportedException;

  /**
   * Generate a histogram for the image.
   *
   * @throws IOException If an I/O error occurs.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   */
  void histogram() throws IOException, CloneNotSupportedException;

  /**
   * Perform color correction on the image.
   *
   * @throws IOException If an I/O error occurs.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   */
  void colorCorrect() throws IOException, CloneNotSupportedException;

  /**
   * Perform color correction on the image with a specified split percentage.
   *
   * @param splitPercentage The percentage at which to split the color correction.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   * @throws IOException If an I/O error occurs.
   */
  void colorCorrect(double splitPercentage) throws CloneNotSupportedException, IOException;

  /**
   * Adjust the levels of the image.
   *
   * @param black The black level.
   * @param mid   The mid level.
   * @param white The white level.
   * @throws IllegalArgumentException If the levels are invalid.
   * @throws IOException              If an I/O error occurs.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   */
  void adjustLevels(int black, int mid, int white)
      throws IllegalArgumentException, IOException, CloneNotSupportedException;

  /**
   * Adjust the levels of the image with a specified split percentage.
   *
   * @param black           The black level.
   * @param mid             The mid level.
   * @param white           The white level.
   * @param splitPercentage The percentage at which to split the level adjustment.
   * @throws CloneNotSupportedException If cloning the image is not supported.
   * @throws IOException If an I/O error occurs.
   */
  void adjustLevels(int black, int mid, int white, double splitPercentage)
      throws CloneNotSupportedException, IOException;

  /**
   * Disable the split view mode, if enabled.
   * This method deactivates any split view functionality in the image display.
   */
  void disableSplitView();
}
