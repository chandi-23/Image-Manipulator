/**
 * The HaarWaveletImageCompressor class implements the ImageCompressor interface and provides
 * functionality for compressing an image using the Haar wavelet transformation.
 * <p>
 * This class is designed to be used within the IME framework for image processing.
 * </p>
 *
 * @see ImageCompressor
 */

package ime.model;

import java.util.Arrays;

/**
 * The HaarWaveletImageCompressor class implements the ImageCompressor interface and provides
 * functionality for compressing an image using the Haar wavelet transformation.
 * This class is designed to be used within the IME framework for image processing.
 *
 * @see ImageCompressor
 */
class HaarWaveletImageCompressor implements ImageCompressor {
  private final Image image;

  /**
   * Constructs a new HaarWaveletImageCompressor with the specified image.
   *
   * @param image The image to be compressed using the Haar wavelet transformation.
   */
  public HaarWaveletImageCompressor(Image image) {
    this.image = image;
  }

  protected double[][][] getChannels() {

    int width = image.getWidth();
    int height = image.getHeight();

    double[][] redChannel = new double[height][width];
    double[][] greenChannel = new double[height][width];
    double[][] blueChannel = new double[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        RGBPixel rgbPixel = (RGBPixel) image.getPixel(i, j);
        int red = rgbPixel.getRed();
        int green = rgbPixel.getGreen();
        int blue = rgbPixel.getBlue();
        redChannel[i][j] = red;
        greenChannel[i][j] = green;
        blueChannel[i][j] = blue;
      }
    }

    return new double[][][]{redChannel, greenChannel, blueChannel};

  }

  protected double[][][] transformChannels(double[][][] channels) {
    double[][][] transformed =  new double[channels.length][][];
    for (int i = 0; i < channels.length; i++) {
      transformed[i] = HaarWaveletUtil.transform(channels[i]);
    }
    return transformed;
  }

  protected double[][][] invertChannels(double[][][] channels) {
    double[][][] inverted =  new double[channels.length][][];
    for (int i = 0; i < channels.length; i++) {
      inverted[i] = HaarWaveletUtil.invert(channels[i], image.getHeight(), image.getWidth());
    }
    return inverted;
  }

  protected double getThreshold(double[][][] channels, double compressionPercentage) {
    double[] unique = Arrays.stream(new double[][][]{channels[0], channels[1], channels[2]})
        .flatMap(Arrays::stream)
        .flatMapToDouble(Arrays::stream)
        .map(Math::abs)
        .distinct()
        .sorted()
        .toArray();
    return unique[Math.max(0,
            (int)Math.round(unique.length * (compressionPercentage / 100.0)) - 1)];
  }

  protected void thresholdChannels(double[][][] channels, double threshold) {
    for (int i = 0; i < channels.length; i++) {
      for (int j = 0; j < channels[i].length; j++) {
        for (int k = 0; k < channels[i][0].length; k++) {
          if (Math.abs(channels[i][j][k]) <= threshold) {
            channels[i][j][k] = 0;
          }

        }
      }
    }
  }

  protected Image getCompressedImage(double[][][] channels) {
    Image outputImage = new RGBImage(image.getWidth(), image.getHeight(), image.getMax());
    for (int i = 0 ; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        outputImage.setPixel(i, j, new RGBPixel(
            (int) Math.max(0, Math.round(channels[0][i][j])),
            (int) Math.max(0, Math.round(channels[1][i][j])),
            (int) Math.max(0, Math.round(channels[2][i][j])),
            255
        ));
      }
    }
    return outputImage;
  }

  @Override
  public Image compress(double compressionPercentage) {
    double[][][] channels = getChannels();
    double[][][] transformed = transformChannels(channels);
    double threshold = getThreshold(transformed, compressionPercentage);
    thresholdChannels(transformed, threshold);
    double[][][] inverted = invertChannels(transformed);
    return getCompressedImage(inverted);
  }

}
