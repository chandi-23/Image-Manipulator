package ime.model;

/**
 * The {@code IntegerPair} class implements the {@code Pair} interface and represents
 * a simple pair of integer values.
 *
 * <p>This class provides methods to retrieve the first and second components of the pair.
 */
class IntegerPair implements Pair {

  private final int first;
  private final int second;

  /**
   * Constructs a new {@code IntegerPair} object with the specified integer values.
   *
   * @param first  The first integer value of the pair.
   * @param second The second integer value of the pair.
   */
  public IntegerPair(int first, int second) {
    this.first = first;
    this.second = second;
  }

  /**
   * Gets the first integer value of the pair.
   *
   * @return The first integer value.
   */
  @Override
  public int getFirst() {
    return first;
  }

  /**
   * Gets the second integer value of the pair.
   *
   * @return The second integer value.
   */
  @Override
  public int getSecond() {
    return second;
  }
}
