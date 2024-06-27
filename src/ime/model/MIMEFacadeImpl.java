/**
 * The MIMEFacadeImpl class implements the MIMEFacade interface and extends
 * the IMEFacadeImpl class, providing a concrete implementation for image manipulation
 * and enhancement within the IME (Image Manipulation and Enhancement) framework.
 * <p>
 * This class utilizes the Java AWT library for image processing operations.
 * </p>
 *
 * @see MIMEFacade
 * @see IMEFacadeImpl
 */

package ime.model;

import java.util.function.Function;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Map;
import java.util.HashMap;

/**
 * The MIMEFacadeImpl class implements the MIMEFacade interface and extends
 * the IMEFacadeImpl class, providing a concrete implementation for image manipulation
 * and enhancement within the IME (Image Manipulation and Enhancement) framework.
 * This class utilizes the Java AWT library for image processing operations.
 *
 * @see MIMEFacade
 * @see IMEFacadeImpl
 */
public class MIMEFacadeImpl extends IMEFacadeImpl implements MIMEFacade {
  
  private Map<Integer, Integer>[] offsetMap;

  /**
   * This is a constructor for the MimeFacade implementation which calls super().
   */
  public MIMEFacadeImpl() {
    super();
  }

  private void validatePercentage(double percentage) throws IllegalArgumentException {
    if (percentage < 0 || percentage > 100) {
      throw new IllegalArgumentException("Invalid percentage value");
    }
  }

  @Override
  public void adjustLevels(int black, int mid, int white, String inputImageName,
                           String outputImageName) throws IllegalArgumentException {

    if (black < 0 || black > 255 || mid < 0 || mid > 255 || white < 0 || white > 255) {
      throw new IllegalArgumentException("Invalid values provided for black / mid / white");
    }

    if (black > mid || mid > white) {
      throw new IllegalArgumentException("Invalid values provided for black / mid / white");
    }

    LevelAdjustCurve levelAdjustCurve = new LevelAdjustCurveImpl(black, mid, white);
    double a = levelAdjustCurve.getACoefficient();
    double b = levelAdjustCurve.getBCoefficient();
    double c = levelAdjustCurve.getCCoefficient();
    Function<Integer, Double> y = x -> a * x * x + b * x + c;

    Image inputImage = imageRepository.getImage(inputImageName);

    Image outputImage = new PixelTransformer(pixel -> {
      RGBPixel rgbPixel = (RGBPixel) pixel;
      return new RGBPixel(
          (int) Math.round(y.apply(rgbPixel.getRed())),
          (int) Math.round(y.apply(rgbPixel.getGreen())),
          (int) Math.round(y.apply(rgbPixel.getBlue())),
          255
      );
    }).process(inputImage);

    imageRepository.putImage(outputImageName, outputImage);

  }

  @Override
  public void adjustLevels(int black, int mid, int white, String inputImageName,
                           String outputImageName, double splitPercentage)
          throws CloneNotSupportedException, IllegalArgumentException {

    validatePercentage(splitPercentage);

    verticalSplit(inputImageName, splitPercentage);
    adjustLevels(black, mid, white, inputImageName
            + "-split-left", inputImageName + "split-left-levels-adjust");
    verticalSplitReplace(inputImageName, outputImageName,
            inputImageName + "split-left-levels-adjust");

  }

  @Override
  public void compress(double percentage, String inputImageName, String outputImageName)
          throws IllegalArgumentException {

    if (percentage < 0 || percentage > 100) {
      throw new IllegalArgumentException("Invalid percentage provided for compression");
    }

    Image inputImage = imageRepository.getImage(inputImageName);
    ImageCompressor compressor = new HaarWaveletImageCompressor(inputImage);
    imageRepository.putImage(outputImageName, compressor.compress(percentage));

  }

  @Override
  public int[][] histogram(String inputImageName, String outputImageName) {

    HistogramGeneratorImpl histogram = new HistogramGeneratorImpl();
    Image inputImage = imageRepository.getImage(inputImageName);
    int[][] frequencyData  = histogram.generate(inputImage);

    plotHistogramInMem(outputImageName, frequencyData);
    return frequencyData;
  }

  @Override
  public void colorCorrect(String inputImageName, String outputImageName) {

    int[][] histogramData = histogram(inputImageName,
            outputImageName + "-histogram");
    int numChannels = histogramData.length;

    int[][] maxValue = findMaxValues(histogramData, numChannels);
    int averagePeakIndex = calculateAveragePeakIndex(histogramData, maxValue, numChannels);

    offsetMap = initializeOffsetMap(numChannels);
    applyColorCorrection(histogramData, numChannels, averagePeakIndex);

    // for our verification only
    plotHistogramInMem(outputImageName + "-histogram", histogramData);

    colorCorrectedImage(inputImageName, outputImageName);
  }

  @Override
  public void colorCorrect(String inputImageName, String outputImageName, double splitPercentage)
          throws CloneNotSupportedException, IllegalArgumentException {

    validatePercentage(splitPercentage);

    verticalSplit(inputImageName, splitPercentage);
    colorCorrect(inputImageName + "-split-left",
            inputImageName + "split-left-color-correct");
    verticalSplitReplace(inputImageName, outputImageName,
            inputImageName + "split-left-color-correct");

  }

  private int[][] findMaxValues(int[][] histogramData, int numChannels) {
    int[][] maxValue = new int[numChannels][1];

    for (int i = 0; i < numChannels; i++) {
      for (int j = 0; j < histogramData[i].length; j++) {
        if (histogramData[i][j] > maxValue[i][0]) {
          maxValue[i][0] = histogramData[i][j];
        }
      }
    }

    return maxValue;
  }

  private int calculateAveragePeakIndex(int[][] histogramData, int[][] maxValue, int numChannels) {
    int sumMaxIndices = 0;

    for (int channel = 0; channel < numChannels; channel++) {
      sumMaxIndices += findIndexOfMaxValue(histogramData[channel], maxValue[channel][0]);
    }

    return sumMaxIndices / numChannels;
  }

  private Map<Integer, Integer>[] initializeOffsetMap(int numChannels) {
    Map<Integer, Integer>[] offsetMap = new HashMap[numChannels];

    for (int channel = 0; channel < numChannels; channel++) {
      offsetMap[channel] = new HashMap<>();
    }

    return offsetMap;
  }

  private void applyColorCorrection(int[][] histogramData, int numChannels, int averagePeakIndex) {
    for (int channel = 0; channel < numChannels; channel++) {
      int offset = averagePeakIndex - findIndexOfMaxValue(histogramData[channel]);
      int[] correctedHistogram = new int[256];

      for (int i = 0; i < histogramData[channel].length; i++) {
        int newIndex = i + offset;
        if (newIndex > 0 && newIndex < 256) {
          offsetMap[channel].put(i, newIndex);
          correctedHistogram[newIndex] = histogramData[channel][i];
        }
      }

      System.arraycopy(correctedHistogram, 0, histogramData[channel], 0, 256);
    }
  }

  private int findIndexOfMaxValue(int[] array, int maxValue) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] == maxValue) {
        return i;
      }
    }
    return -1; // Not found
  }

  private int findIndexOfMaxValue(int[] array) {
    int maxIndex = 0;
    int maxValue = array[0];
    for (int i = 1; i < array.length; i++) {
      if (array[i] > maxValue) {
        maxValue = array[i];
        maxIndex = i;
      }
    }
    return maxIndex;
  }

  private void colorCorrectedImage(String inputImageName, String outputImageName) {

    if (imageRepository.getImage(inputImageName) instanceof RGBImage) {
      RGBImage image = (RGBImage) imageRepository.getImage(inputImageName);

      BufferedImage bufferedImage = new BufferedImage(image.getWidth(),
              image.getHeight(),
              BufferedImage.TYPE_INT_RGB);
      WritableRaster writableRaster = bufferedImage.getRaster();

      for (int i = 0; i < image.getHeight(); i++) {
        for (int j = 0; j < image.getWidth(); j++) {
          RGBPixel pxIn = (RGBPixel) image.getPixel(i, j);
          int red = offsetMap[0].getOrDefault(pxIn.getRed(), 0);
          int green = offsetMap[1].getOrDefault(pxIn.getGreen(), 0);
          int blue = offsetMap[2].getOrDefault(pxIn.getBlue(), 0);
          writableRaster.setPixel(j, i, new int[]{red, green, blue});
        }
      }
      Image rg = convertToRGBImage(bufferedImage);
      imageRepository.putImage(outputImageName, rg);
    }

  }

  @Override
  public void blur(String inputImageName, String outputImageName, double splitPercentage)
      throws CloneNotSupportedException, IllegalArgumentException {

    validatePercentage(splitPercentage);

    verticalSplit(inputImageName, splitPercentage);
    blur(inputImageName + "-split-left", inputImageName + "split-left-blur");
    verticalSplitReplace(inputImageName, outputImageName, inputImageName + "split-left-blur");

  }

  @Override
  public void sharpen(String inputImageName, String outputImageName,
                      double splitPercentage)
      throws CloneNotSupportedException, IllegalArgumentException {

    validatePercentage(splitPercentage);

    verticalSplit(inputImageName, splitPercentage);
    sharpen(inputImageName + "-split-left",
            inputImageName + "split-left-sharpen");
    verticalSplitReplace(inputImageName, outputImageName,
            inputImageName + "split-left-sharpen");

  }

  @Override
  public void sepia(String inputImageName, String outputImageName,
                    double splitPercentage)
      throws CloneNotSupportedException, IllegalArgumentException {

    validatePercentage(splitPercentage);

    verticalSplit(inputImageName, splitPercentage);
    sepia(inputImageName + "-split-left",
            inputImageName + "split-left-sepia");
    verticalSplitReplace(inputImageName, outputImageName,
            inputImageName + "split-left-sepia");

  }

  private void verticalSplit(String inputImageName, double splitPercentage)
      throws CloneNotSupportedException {

    Image inputImage = imageRepository.getImage(inputImageName);
    int width = inputImage.getWidth();
    int splitPoint = (int) Math.round((splitPercentage / 100.0) * width);

    Image inputImageCopy = ((RGBImage)inputImage).clone();

    Image leftPortion = inputImageCopy.getPortion(0,
            inputImageCopy.getHeight() - 1, 0, splitPoint - 1);
    Image rightPortion = inputImageCopy.getPortion(0,
            inputImageCopy.getHeight() - 1, splitPoint, inputImageCopy.getWidth() - 1);

    imageRepository.putImage(inputImageName + "-split-left", leftPortion);
    imageRepository.putImage(inputImageName + "-split-right", rightPortion);

  }

  private void verticalSplitReplace(String inputImageName, String outputImageName,
                                    String replaceImageName)
      throws CloneNotSupportedException {
    Image replaceImage = imageRepository.getImage(replaceImageName);
    Image inputImage = imageRepository.getImage(inputImageName);
    Image inputImageCopy = ((RGBImage)inputImage).clone();
    inputImageCopy.replacePortion(0, 0, replaceImage);
    imageRepository.putImage(outputImageName, inputImageCopy);
  }

  @Override
  public void luma(String inputImageName, String outputImageName, double splitPercentage)
      throws CloneNotSupportedException, IllegalArgumentException {

    validatePercentage(splitPercentage);

    verticalSplit(inputImageName, splitPercentage);
    luma(inputImageName + "-split-left",
            inputImageName + "split-left-luma");
    verticalSplitReplace(inputImageName, outputImageName,
            inputImageName + "split-left-luma");

  }

  private RGBImage convertToRGBImage(BufferedImage bufferedImage) {
    int width = bufferedImage.getWidth();
    int height = bufferedImage.getHeight();
    int max = 255;

    RGBImage rgbImage = new RGBImage(width, height, max);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int rgb = bufferedImage.getRGB(j, i);

        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;

        RGBPixel pixel = new RGBPixel(red, green, blue, max);
        rgbImage.setPixel(i, j, pixel);
      }
    }
    return rgbImage;
  }

  private void plotHistogramInMem(String outputImageName, int[][] histogramData) {
    PlotHistogram plotHist = new PlotHistogramImpl(800, 800);
    BufferedImage bufferedImage = plotHist.plot(histogramData);
    Image rg = convertToRGBImage(bufferedImage);
    imageRepository.putImage(outputImageName, rg);
  }
}