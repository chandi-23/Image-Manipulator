package ime.model;

/**
 * The {@code Image} interface represents a generic image with basic properties and methods
 * for accessing and manipulating pixel data.
 *
 * <p>This interface provides a common foundation for working with images in the application.
 */
interface Image {

  /**
   * Gets the width of the image.
   *
   * @return The width of the image in pixels.
   */
  int getWidth();

  /**
   * Gets the height of the image.
   *
   * @return The height of the image in pixels.
   */
  int getHeight();

  /**
   * Gets the maximum value for pixel color components.
   *
   * @return The maximum value for pixel color components (e.g., 255 for 8-bit color channels).
   */
  int getMax();

  /**
   * Gets the pixel at the specified row and column.
   *
   * @param row The row of the pixel.
   * @param col The column of the pixel.
   * @return The {@code Pixel} object representing the color and attributes of the pixel
   *          at the given coordinates.
   */
  Pixel getPixel(int row, int col);

  /**
   * Sets the pixel at the specified row and column.
   *
   * @param row   The row of the pixel.
   * @param col   The column  of the pixel.
   * @param pixel The {@code Pixel} object representing the color and attributes to be set
   *              for the pixel.
   */
  void setPixel(int row, int col, Pixel pixel);

  /**
   * Retrieves a portion of the image specified by the given range of rows and columns.
   *
   * @param rowStart    The starting row of the portion.
   * @param rowEnd      The ending row of the portion.
   * @param columnStart The starting column of the portion.
   * @param columnEnd   The ending column of the portion.
   * @return A new {@code Image} representing the specified portion of the original image.
   */
  Image getPortion(int rowStart, int rowEnd, int columnStart, int columnEnd);

  /**
   * Replaces a portion of the image with the specified new portion.
   *
   * @param rowStart    The starting row of the portion to be replaced.
   * @param colStart    The starting column of the portion to be replaced.
   * @param newPortion  The new {@code Image} portion to replace the specified area.
   */
  void replacePortion(int rowStart, int colStart, Image newPortion);

}
