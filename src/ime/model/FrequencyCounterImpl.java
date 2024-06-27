package ime.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code FrequencyCounterImpl} class implements the {@code FrequencyCounter} interface and
 * represents a utility for computing the frequency of each unique value in an array.
 *
 * <p>This implementation uses a map to store the frequency of each unique value in the provided
 * array.
 */
class FrequencyCounterImpl implements FrequencyCounter {

  private final int[] data;
  private final Map<Integer, Integer> frequency;

  /**
   * Constructs a new {@code FrequencyCounterImpl} object with the specified array of data values.
   * It initializes the frequency map and computes the frequency of each unique value.
   *
   * @param data The array of data values.
   */
  public FrequencyCounterImpl(int[] data) {
    this.data = data;
    frequency = new HashMap<>();
    computeFrequency();
  }

  /**
   * Retrieves the peak value and its frequency in the array.
   *
   * @return A {@code Pair} object representing the peak value and its frequency.
   */
  @Override
  public Pair getPeak() {
    int maxFrequency = 0;
    int peakValue = 0;

    for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
      int value = entry.getKey();
      int currentFrequency = entry.getValue();

      if (currentFrequency > maxFrequency) {
        maxFrequency = currentFrequency;
        peakValue = value;
      }
    }

    return new IntegerPair(peakValue, maxFrequency);
  }

  // Private method to compute the frequency of each unique value in the array.
  private void computeFrequency() {
    for (int value : data) {
      frequency.put(value, frequency.getOrDefault(value, 0) + 1);
    }
  }

  /**
   * Retrieves the frequency map containing each unique value and its corresponding frequency.
   *
   * @return A {@code Map} containing the frequency of each unique value in the array.
   */
  @Override
  public Map<Integer, Integer> getFrequency() {
    return frequency;
  }

}
