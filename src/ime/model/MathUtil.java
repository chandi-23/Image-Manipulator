/**
 * The MathUtil class provides utility methods for mathematical operations.
 * <p>
 * This class includes static methods for common mathematical calculations.
 * </p>
 */

package ime.model;

/**
 * The MathUtil class provides utility methods for mathematical operations.
 * This class includes static methods for common mathematical calculations.
 */
class MathUtil {

  /**
   * Calculates and returns the nearest power of two greater than or equal to the given number.
   *
   * @param number The input number for which to find the nearest power of two.
   * @return The nearest power of two greater than or equal to the input number.
   */
  public static int getNearestPowerOfTwo(int number) {
    int nearest_power_of_two = 1;
    while (nearest_power_of_two < number) {
      nearest_power_of_two *= 2;
    }

    return nearest_power_of_two;
  }

}
