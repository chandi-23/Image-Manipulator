/**
 * The HaarWaveletUtil class provides utility methods for performing Haar wavelet transformations
 * on arrays and lists of numerical data.
 * <p>
 * This class is designed to be used within the IME framework for image processing.
 * </p>
 */

package ime.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The HaarWaveletUtil class provides utility methods for performing Haar wavelet transformations
 * on arrays and lists of numerical data. This class is designed to be used within the IME framework
 * for image processing.
 */
class HaarWaveletUtil {

  private static double[] transformSequence(double[] input) {

    int n = input.length;
    double[] result = new double[n];
    Arrays.fill(result, 0);

    if (n > 1) {

      List<Double> avg = new ArrayList<>();
      List<Double> diff = new ArrayList<>();

      int i = 0;
      while (i + 1 < n) {
        double a = input[i];
        double b = input[i + 1];
        double normalized_avg = (a + b) / Math.sqrt(2);
        double normalized_diff = (a - b) / Math.sqrt(2);
        avg.add(normalized_avg);
        diff.add(normalized_diff);
        i += 2;
      }

      avg.addAll(diff);
      for (int j = 0; j < n; j++) {
        result[j] = avg.get(j);
      }


    }
    else {
      result[0] = input[0];
    }

    return result;
  }

  private static double[] invertSequence(double[] input) {

    int n = input.length;
    double[] result = new double[n];
    Arrays.fill(result, 0);

    if (n == 1) {
      result[0] = input[0];
    }
    else {

      int i = 0;
      int j = n / 2;

      List<Double> avg = new ArrayList<>();
      List<Double> diff = new ArrayList<>();

      while (j < n) {
        double a = input[i];
        double b = input[j];
        double normalized_avg = (a + b) / Math.sqrt(2);
        double normalized_diff = (a - b) / Math.sqrt(2);
        avg.add(normalized_avg);
        diff.add(normalized_diff);
        i += 1;
        j += 1;
      }

      i = 0;
      j = 0;
      while (i + 1 < n) {
        result[i] = avg.get(j);
        result[i + 1] = diff.get(j);
        i += 2;
        j += 1;
      }

    }
    return result;

  }

  private static double[][] padInput(double[][] input, int maxSize) {
    double[][] paddedInput = new double[maxSize][maxSize];

    for (int i = 0; i < maxSize; i++) {
      Arrays.fill(paddedInput[i], 0);
    }

    for (int i = 0; i < input.length; i++) {
      System.arraycopy(input[i], 0, paddedInput[i], 0, input[i].length);
    }

    return paddedInput;
  }

  /**
   * Applies a 2D Haar wavelet transform to the given input matrix.
   *
   * @param input The input matrix to be transformed.
   * @return A matrix representing the result of the 2D Haar wavelet transform.
   */
  public static double[][] transform(double[][] input) {

    int height = input.length;
    int width = input[0].length;
    int maxSize = Math.max(MathUtil.getNearestPowerOfTwo(height),
            MathUtil.getNearestPowerOfTwo(width));

    double[][] paddedInput = padInput(input, maxSize);

    while (maxSize > 1) {
      transformRows(paddedInput, maxSize);
      transformColumns(paddedInput, maxSize);
      maxSize /= 2;
    }

    return paddedInput;
  }

  private static void transformRows(double[][] input, int size) {
    for (int i = 0; i < size; i++) {
      double[] temp = new double[size];
      Arrays.fill(temp, 0);
      System.arraycopy(input[i], 0, temp, 0, size);
      temp = transformSequence(temp);
      System.arraycopy(temp, 0, input[i], 0, size);
    }
  }

  private static void transformColumns(double[][] input, int size) {
    for (int i = 0; i < size; i++) {
      double[] temp = new double[size];
      Arrays.fill(temp, 0);
      for (int j = 0; j < size; j++) {
        temp[j] = input[j][i];
      }
      temp = transformSequence(temp);
      for (int j = 0; j < size; j++) {
        input[j][i] = temp[j];
      }
    }
  }

  private static void invertColumns(double[][] input, int size) {
    for (int i = 0; i < size; i++) {
      double[] temp = new double[size];
      Arrays.fill(temp, 0);
      for (int j = 0; j < size; j++) {
        temp[j] = input[j][i];
      }
      temp = invertSequence(temp);
      for (int j = 0; j < size; j++) {
        input[j][i] = temp[j];
      }
    }
  }

  private static void invertRows(double[][] input, int size) {
    for (int i = 0; i < size; i++) {
      double[] temp = new double[size];
      Arrays.fill(temp, 0);
      System.arraycopy(input[i], 0, temp, 0, size);
      temp = invertSequence(temp);
      System.arraycopy(temp, 0, input[i], 0, size);
    }
  }

  /**
   * Inverts a 2D Haar wavelet transform on the given input matrix.
   *
   * @param input  The input matrix containing the result of the Haar wavelet transform.
   * @param height The height of the original matrix before the transformation.
   * @param width  The width of the original matrix before the transformation.
   * @return A matrix representing the result of inverting the Haar wavelet transform.
   */
  public static double[][] invert(double[][] input, int height, int width) {
    int size = input.length;
    int currentSize = 2;

    while (currentSize <= size) {
      invertColumns(input, currentSize);
      invertRows(input, currentSize);
      currentSize *= 2;
    }

    double[][] result = new double[height][width];
    padOutput(input, result, height, width);

    return result;
  }

  private static void padOutput(double[][] input, double[][] result, int height, int width) {
    for (int i = 0; i < height; i++) {
      System.arraycopy(input[i], 0, result[i], 0, width);
    }
  }

}
