/**
 * The FrequencyCounterTest class contains test cases for the FrequencyCounter class,
 * ensuring the correctness of frequency counting functionality.
 */

package ime.model;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;


/**
 * The FrequencyCounterTest class contains test cases for the FrequencyCounter class,
 * ensuring the correctness of frequency counting functionality.
 */
public class FrequencyCounterImplTest {

  @Test
  public void testFrequencyCounter() {
    // Create test data
    int[] testData = {1, 2, 3, 1, 2, 3, 1, 2, 1, 3};

    // Create a FrequencyCounterImpl instance
    FrequencyCounter frequencyCounter = new FrequencyCounterImpl(testData);
    // Test getFrequency method
    Map<Integer, Integer> expectedFrequencyMap = new HashMap<>();
    expectedFrequencyMap.put(1, 4);
    expectedFrequencyMap.put(2, 3);
    expectedFrequencyMap.put(3, 3);
    assertEquals(expectedFrequencyMap, frequencyCounter.getFrequency());

    assertEquals(1, frequencyCounter.getPeak().getFirst());
    assertEquals(4, frequencyCounter.getPeak().getSecond());
  }

}