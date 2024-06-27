package ime.model;

import java.util.Map;

/**
 * The FrequencyCounter interface defines the contract for objects responsible for counting the
 * frequency of values and identifying the peak value within a dataset. Implementing classes are
 * expected to provide methods for retrieving the peak value and the frequency distribution.
 */
interface FrequencyCounter {

  /**
   * Gets a Pair object representing the peak value and its frequency in the dataset.
   *
   * @return A Pair object with the peak value as the first element and its frequency as the
   *        second element.
   */
  Pair getPeak();

  /**
   * Gets a Map representing the frequency distribution of values in the dataset.
   *
   * @return A Map where keys are values in the dataset, and values are their respective
   *        frequencies.
   */
  Map<Integer, Integer> getFrequency();

}

