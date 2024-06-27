/**
 * The RGBPixel class represents a pixel with red, green, and blue color components.
 * It implements the Pixel interface and provides methods to manipulate and query
 * the color values.
 */

package ime.model;

import java.util.Objects;

/**
 * The RGBPixel class represents a pixel with red, green, and blue color components. It implements
 * the Pixel interface and provides methods to manipulate and query the color values.
 */
class RGBPixel implements Pixel {
  private int red;
  private int green;
  private int blue;
  private final int max;

  /**
   * Constructs an RGBPixel with the specified red, green, blue color components,
   * and maximum color value.
   *
   * @param red   The red color component.
   * @param green The green color component.
   * @param blue  The blue color component.
   * @param max   The maximum color value.
   */
  public RGBPixel(int red, int green, int blue, int max) {
    this.max = max;
    setRed(red);
    setGreen(green);
    setBlue(blue);
  }

  /**
   * Sets the red color component of the pixel, ensuring it is within the valid color range.
   *
   * @param red The red color component to set.
   */
  public void setRed(int red) {
    if (red > max) {
      red = max;
    } else if (red < 0) {
      red = 0;
    }
    this.red = red;
  }

  /**
   * Sets the green color component of the pixel, ensuring it is within the valid color range.
   *
   * @param green The green color component to set.
   */
  public void setGreen(int green) {
    if (green > max) {
      green = max;
    } else if (green < 0) {
      green = 0;
    }
    this.green = green;
  }

  /**
   * Sets the blue color component of the pixel, ensuring it is within the valid color range.
   *
   * @param blue The blue color component to set.
   */
  public void setBlue(int blue) {
    if (blue > max) {
      blue = max;
    } else if (blue < 0) {
      blue = 0;
    }
    this.blue = blue;
  }

  /**
   * Gets the red color component of the pixel.
   *
   * @return The red color component.
   */
  public int getRed() {
    return red;
  }

  /**
   * Gets the green color component of the pixel.
   *
   * @return The green color component.
   */
  public int getGreen() {
    return green;
  }

  /**
   * Gets the blue color component of the pixel.
   *
   * @return The blue color component.
   */
  public int getBlue() {
    return blue;
  }

  /**
   * Gets the maximum color value of the pixel.
   *
   * @return The maximum color value.
   */
  public int getMax() {
    return max;
  }

  /**
   * Calculates and returns the luma (brightness) value of the pixel based on the specified formula.
   *
   * @return The luma value of the pixel.
   */
  public int getLuma() {
    return (int) Math.round(0.2126 * red + 0.7152 * green + 0.0722 * blue);
  }

  /**
   * Calculates and returns the intensity value of the pixel by averaging its color components.
   *
   * @return The intensity value of the pixel.
   */
  public int getIntensity() {
    return (red + blue + green) / 3;
  }

  /**
   * Gets the maximum color value among the pixel's color components.
   *
   * @return The maximum color value among the pixel's color components.
   */
  public int getValue() {
    return Math.max(red, Math.max(blue, green));
  }

  /**
   * Creates and returns a new RGBPixel by increasing the color values by the specified increment.
   *
   * @param increment The amount by which to increase the color values.
   * @return A new RGBPixel with increased color values.
   */
  @Override
  public Pixel brighten(int increment) {
    return new RGBPixel(red + increment, green + increment, blue + increment, max);
  }

  /**
   * Compares this RGBPixel to another object. Two RGBPixels are considered equal if they have
   * the same red, green, blue, and maximum color value.
   *
   * @param other The object to compare with this RGBPixel.
   * @return true if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof RGBPixel)) {
      return false;
    }

    RGBPixel that = (RGBPixel) other;
    return (this.getRed() == that.getRed())
        && (this.getGreen() == that.getGreen())
        && (this.getBlue() == that.getBlue());
  }

  /**
   * Obtains the unique hashcode for the given RGBPixel object.
   * @return the unique hashcode for the given RGBPixel object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.red, this.green, this.blue, this.max);
  }

}
