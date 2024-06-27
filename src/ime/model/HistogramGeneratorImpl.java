/**
 * The HistogramGeneratorImpl class implements the HistogramGenerator interface
 * and provides functionality for generating a histogram from an image.
 * <p>
 * This class is designed to be used within the IME framework for image processing.
 * </p>
 *
 * @see HistogramGenerator
 */

package ime.model;

import java.util.Map;

/**
 * The HistogramGeneratorImpl class implements the HistogramGenerator interface
 * and provides functionality for generating a histogram from an image.
 * This class is designed to be used within the IME framework for image processing.
 *
 * @see HistogramGenerator
 */
class HistogramGeneratorImpl implements HistogramGenerator {

  /**
   * Generates a histogram for the provided input image.
   *
   * @param inputImage The image for which to generate a histogram.
   * @return A two-dimensional array representing the histogram data.
   */
  public int[][] generate(Image inputImage) {

    int width = inputImage.getWidth();
    int height = inputImage.getHeight();
    int noOfChannels = 3;
    int[][] channelsArray = extractChannelsArray(inputImage, width, height, noOfChannels);

    FrequencyCounter[] frequencyCounters = createFrequencyCounters(channelsArray, noOfChannels);
    return  extractFrequencyData(frequencyCounters, noOfChannels);

  }

  private int[][] extractFrequencyData(FrequencyCounter[] frequencyCounters, int noOfChannels) {
    int[][] frequencyData = new int[noOfChannels][256];

    for (int channel = 0; channel < noOfChannels; channel++) {
      Map<Integer, Integer> frequencyMap = frequencyCounters[channel].getFrequency();

      for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
        int value = entry.getKey();
        int frequency = entry.getValue();
        frequencyData[channel][value] = frequency;
      }
    }

    return frequencyData;
  }

  private int[][] extractChannelsArray(Image inputImage, int width, int height, int noOfChannels) {
    int[][] channelsArray = new int[noOfChannels][width * height];

    for (int c = 0; c < noOfChannels; c++) {
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          RGBPixel pixel = (RGBPixel) inputImage.getPixel(i, j);
          int index = i * width + j;
          channelsArray[c][index] = getChannelValue(pixel, c);
        }
      }
    }

    return channelsArray;
  }

  private FrequencyCounter[] createFrequencyCounters(int[][] channelsArray, int noOfChannels) {
    FrequencyCounter[] frequencyCounters = new FrequencyCounterImpl[noOfChannels];

    for (int channel = 0; channel < noOfChannels; channel++) {
      int[] channelValues = channelsArray[channel];
      frequencyCounters[channel] = new FrequencyCounterImpl(channelValues);
    }

    return frequencyCounters;
  }

  private int getChannelValue(RGBPixel pixel, int channel) {
    switch (channel) {
      case 0:
        return pixel.getRed();
      case 1:
        return pixel.getGreen();
      case 2:
        return pixel.getBlue();
      default:
        throw new IllegalArgumentException("Invalid channel index");
    }
  }
}
