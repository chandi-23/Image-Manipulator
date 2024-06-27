/**
 * The IMEFacadeImpl class implements the IMEFacade interface, providing functionality for image
 * processing operations and interactions with image data.
 */

package ime.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The IMEFacadeImpl class provides an implementation of the IMEFacade interface, offering methods
 * for image loading, saving, and various image processing operations.
 */
public class IMEFacadeImpl implements IMEFacade {

  final ImageRepository imageRepository;

  /**
   * Constructs an instance of the IMEFacadeImpl class.
   */
  public IMEFacadeImpl() {
    this.imageRepository = new InMemoryRepository();
  }

  // Methods for retrieving and saving images:

  /**
   * Retrieves an image with the specified name from the image repository.
   *
   * @param imageName The name of the image to retrieve.
   * @return The Image object associated with the provided image name.
   * @throws InputMismatchException If the image is not found in the repository.
   */
  Image getImage(String imageName) throws InputMismatchException {
    return imageRepository.getImage(imageName);
  }

  // Methods for loading and saving images:
  @Override
  public void load(InputStream imageData, String imageName) throws NoSuchElementException {
    Scanner sc = new Scanner(imageData);
    int width = sc.nextInt();
    int height = sc.nextInt();
    int max = sc.nextInt();
    if (width < 0 || height < 0 || max < 0) {
      throw new NoSuchElementException("Please input a valid file");
    }
    Image outputImage = new RGBImage(width, height, max);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = sc.nextInt();
        int green = sc.nextInt();
        int blue = sc.nextInt();
        if (red < 0 || red > max || green < 0 || green > max || blue < 0 || blue > max) {
          throw new NoSuchElementException("Please input a valid file");
        }
        outputImage.setPixel(i, j, new RGBPixel(red, green, blue, max));
      }
    }
    if (sc.hasNext()) {
      throw new NoSuchElementException("Please input a valid file"); }
    imageRepository.putImage(imageName, outputImage);
  }

  @Override
  public OutputStream save(String imageName) throws IOException {
    StringBuilder imageData = new StringBuilder();
    Image rgbImage = imageRepository.getImage(imageName);
    int width = rgbImage.getWidth();
    int height = rgbImage.getHeight();
    imageData.append(width).append(" ").append(height)
        .append(System.lineSeparator());
    imageData.append(rgbImage.getMax()).append(System.lineSeparator());
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        RGBPixel rgbPixel = (RGBPixel) rgbImage.getPixel(i, j);
        imageData.append(rgbPixel.getRed()).append(System.lineSeparator());
        imageData.append(rgbPixel.getGreen()).append(System.lineSeparator());
        imageData.append(rgbPixel.getBlue()).append(System.lineSeparator());
      }
    }
    OutputStream outputStream = new ByteArrayOutputStream();
    outputStream.write(imageData.toString().getBytes());
    return outputStream;
  }

  @Override
  public void redComponent(String inputImageName, String outputImageName) {
    Image inputImage = imageRepository.getImage(inputImageName);
    Image outputImage = new PixelTransformer(pixel -> {
      RGBPixel rgbPixel = (RGBPixel) pixel;
      return new RGBPixel(rgbPixel.getRed(), 0, 0, rgbPixel.getMax());
    }).process(inputImage);
    imageRepository.putImage(outputImageName, outputImage);
  }

  @Override
  public void greenComponent(String inputImageName, String outputImageName) {
    Image inputImage = imageRepository.getImage(inputImageName);
    Image outputImage = new PixelTransformer(pixel -> {
      RGBPixel rgbPixel = (RGBPixel) pixel;
      return new RGBPixel(0, rgbPixel.getGreen(), 0, rgbPixel.getMax());
    }).process(inputImage);
    imageRepository.putImage(outputImageName, outputImage);
  }

  @Override
  public void blueComponent(String inputImageName, String outputImageName) {
    Image inputImage = imageRepository.getImage(inputImageName);
    Image outputImage = new PixelTransformer(pixel -> {
      RGBPixel rgbPixel = (RGBPixel) pixel;
      return new RGBPixel(0, 0, rgbPixel.getBlue(), rgbPixel.getMax());
    }).process(inputImage);
    imageRepository.putImage(outputImageName, outputImage);
  }

  @Override
  public void value(String inputImageName, String outputImageName) {
    Image inputImage = imageRepository.getImage(inputImageName);
    Image outputImage = new PixelTransformer(pixel -> {
      RGBPixel rgbPixel = (RGBPixel) pixel;
      return new RGBPixel(rgbPixel.getValue(), rgbPixel.getValue(), rgbPixel.getValue(),
          rgbPixel.getMax());
    }).process(inputImage);
    imageRepository.putImage(outputImageName, outputImage);
  }

  @Override
  public void intensity(String inputImageName, String outputImageName) {
    Image inputImage = imageRepository.getImage(inputImageName);
    Image outputImage = new PixelTransformer(pixel -> {
      RGBPixel rgbPixel = (RGBPixel) pixel;
      return new RGBPixel(rgbPixel.getIntensity(), rgbPixel.getIntensity(), rgbPixel.getIntensity(),
          rgbPixel.getMax());
    }).process(inputImage);
    imageRepository.putImage(outputImageName, outputImage);
  }

  @Override
  public void luma(String inputImageName, String outputImageName) {
    Image inputImage = imageRepository.getImage(inputImageName);
    Image outputImage = new PixelTransformer(pixel -> {
      RGBPixel rgbPixel = (RGBPixel) pixel;
      return new RGBPixel(rgbPixel.getLuma(), rgbPixel.getLuma(), rgbPixel.getLuma(),
          rgbPixel.getMax());
    }).process(inputImage);
    imageRepository.putImage(outputImageName, outputImage);
  }

  @Override
  public void flipVertical(String inputImageName, String outputImageName) {
    Image inputImage = imageRepository.getImage(inputImageName);
    int width = inputImage.getWidth();
    int height = inputImage.getHeight();
    Image outputImage = new RGBImage(width, height, inputImage.getMax());
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        RGBPixel mirrorPixel = (RGBPixel) inputImage.getPixel(Math.abs(height - 1 - i), j);
        outputImage.setPixel(i, j,
            new RGBPixel(mirrorPixel.getRed(), mirrorPixel.getGreen(), mirrorPixel.getBlue(),
                mirrorPixel.getMax()));
      }
      imageRepository.putImage(outputImageName, outputImage);
    }
  }

  @Override
  public void flipHorizontal(String inputImageName, String outputImageName) {
    Image inputImage = imageRepository.getImage(inputImageName);
    int width = inputImage.getWidth();
    int height = inputImage.getHeight();
    Image outputImage = new RGBImage(width, height, inputImage.getMax());
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        RGBPixel mirrorPixel = (RGBPixel) inputImage.getPixel(i, Math.abs(width - 1 - j));
        outputImage.setPixel(i, j,
            new RGBPixel(mirrorPixel.getRed(), mirrorPixel.getGreen(), mirrorPixel.getBlue(),
                mirrorPixel.getMax()));
      }
      imageRepository.putImage(outputImageName, outputImage);
    }
  }

  @Override
  public void brighten(int increment, String inputImageName, String outputImageName) {
    Image inputImage = imageRepository.getImage(inputImageName);
    Image outputImage = new PixelTransformer(pixel -> pixel.brighten(increment)).process(
        inputImage);
    imageRepository.putImage(outputImageName, outputImage);
  }

  @Override
  public void rgbSplit(String inputImageName, String redComponentOutputImageName,
      String greenComponentOutputImageName, String blueComponentOutputImageName) {
    redComponent(inputImageName, redComponentOutputImageName);
    greenComponent(inputImageName, greenComponentOutputImageName);
    blueComponent(inputImageName, blueComponentOutputImageName);
  }

  @Override
  public void rgbCombine(String redComponentInputImageName, String greenComponentInputImageName,
      String blueComponentInputImageName, String outputImageName) {
    Image redComponentInputImage = imageRepository.getImage(redComponentInputImageName);
    Image greenComponentInputImage = imageRepository.getImage(greenComponentInputImageName);
    Image blueComponentInputImage = imageRepository.getImage(blueComponentInputImageName);
    int width = redComponentInputImage.getWidth();
    int height = redComponentInputImage.getHeight();
    int max = redComponentInputImage.getMax();
    if (greenComponentInputImage.getHeight() != height
        || greenComponentInputImage.getWidth() != width
        || blueComponentInputImage.getHeight() != height
        || blueComponentInputImage.getWidth() != width) {
      throw new IllegalArgumentException("Combine operation requires images of same dimension");
    }
    Image outputImage = new RGBImage(width, height, max);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        RGBPixel redComponentPixel = (RGBPixel) redComponentInputImage.getPixel(i, j);
        RGBPixel greenComponentPixel = (RGBPixel) greenComponentInputImage.getPixel(i, j);
        RGBPixel blueComponentPixel = (RGBPixel) blueComponentInputImage.getPixel(i, j);
        outputImage.setPixel(i, j,
            new RGBPixel(redComponentPixel.getRed(), greenComponentPixel.getGreen(),
                blueComponentPixel.getBlue(), max));
      }
    }
    imageRepository.putImage(outputImageName, outputImage);
  }

  @Override
  public void blur(String inputImageName, String outputImageName) {
    double[][] gaussianBlurKernel = {
        {1.0 / 16, 1.0 / 8, 1.0 / 16},
        {1.0 / 8, 1.0 / 4, 1.0 / 8},
        {1.0 / 16, 1.0 / 8, 1.0 / 16}
    };
    Image inputImage = imageRepository.getImage(inputImageName);
    Image outputImage = new Filter(gaussianBlurKernel).process(inputImage);
    imageRepository.putImage(outputImageName, outputImage);
  }

  @Override
  public void sharpen(String inputImageName, String outputImageName) {
    double[][] sharpeningKernel = {
        {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
        {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
        {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
        {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
        {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
    };
    Image inputImage = imageRepository.getImage(inputImageName);
    Image outputImage = new Filter(sharpeningKernel).process(inputImage);
    imageRepository.putImage(outputImageName, outputImage);
  }

  @Override
  public void sepia(String inputImageName, String outputImageName) {
    double[][] sepiaMatrix = {
        {0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}
    };
    Image inputImage = imageRepository.getImage(inputImageName);
    Image outputImage = new LinearColorTransformer(sepiaMatrix).process(inputImage);
    imageRepository.putImage(outputImageName, outputImage);
  }

  @Override
  public int[][][] getRawImageData(String imageName) {

    Image image = imageRepository.getImage(imageName);
    int height = image.getHeight();
    int width = image.getWidth();

    int[][][] rawImageData = new int[height][width][3];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        RGBPixel rgbPixel = (RGBPixel) image.getPixel(i, j);
        rawImageData[i][j][0] = rgbPixel.getRed();
        rawImageData[i][j][1] = rgbPixel.getGreen();
        rawImageData[i][j][2] = rgbPixel.getBlue();
      }
    }

    return rawImageData;

  }
}
