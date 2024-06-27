package ime.model;

/**
 * The Pair interface defines the contract for objects representing a pair of integer values.
 * Implementing classes are expected to provide methods to retrieve the first and second elements
 * of the pair.
 */
interface Pair {

  /**
   * Gets the first integer value of the pair.
   *
   * @return The first integer value.
   */
  int getFirst();

  /**
   * Gets the second integer value of the pair.
   *
   * @return The second integer value.
   */
  int getSecond();

}
