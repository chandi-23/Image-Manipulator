package ime.model;

/**
 * The LevelAdjustCurve interface defines the contract for objects representing a mathematical
 * curve used in level adjustment for image manipulation. Implementing classes are expected to
 * provide coefficients for the curve equation.
 */

interface LevelAdjustCurve {

  /**
   * Gets the coefficient 'a' used in the level adjustment curve equation.
   *
   * @return The coefficient 'a'.
   */
  double getACoefficient();

  /**
   * Gets the coefficient 'b' used in the level adjustment curve equation.
   *
   * @return The coefficient 'b'.
   */
  double getBCoefficient();

  /**
   * Gets the coefficient 'c' used in the level adjustment curve equation.
   *
   * @return The coefficient 'c'.
   */
  double getCCoefficient();

}
