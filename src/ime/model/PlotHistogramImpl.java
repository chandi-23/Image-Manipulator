/**
 * The PlotHistogramImpl class implements the PlotHistogram interface, providing a concrete
 * implementation for plotting histograms based on input histogram data. It uses the Java AWT
 * library for image creation and graphical rendering.
 */

package ime.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * The PlotHistogramImpl class implements the PlotHistogram interface, providing a concrete
 * implementation for plotting histograms based on input histogram data. It uses the Java AWT
 * library for image creation and graphical rendering.
 */
class PlotHistogramImpl implements PlotHistogram {

  private final int height;
  private final int width;
  private int maxFrequency;

  /**
   * Constructs a new PlotHistogramImpl with the specified height and width.
   * This class is responsible for plotting histograms based on the given dimensions.
   *
   * @param height The height of the histogram plot.
   * @param width  The width of the histogram plot.
   */
  public PlotHistogramImpl(int height, int width) {

    this.height = height;
    this.width = width;
  }

  /**
   * Plots a histogram using the provided histogram data and returns the resulting image.
   *
   * @param histogramData A two-dimensional array representing the histogram data.
   * @return A BufferedImage containing the visual representation of the histogram.
   */
  @Override
  public BufferedImage plot(int[][] histogramData) {

    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    Graphics g = image.getGraphics();

    // Set background color
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, width, height);

    maxFrequency = Math.max(findMax(histogramData[2]),
            Math.max(findMax(histogramData[0]), findMax(histogramData[1])));

    // Draw histograms with grid
    drawGrid(g, width, height);
    drawHistogram(g, histogramData[0], Color.RED);
    drawHistogram(g, histogramData[1], Color.GREEN);
    drawHistogram(g, histogramData[2], Color.BLUE);

    // Clean up
    g.dispose();

    return image;
  }

  private void drawHistogram(Graphics g, int[] data, Color color) {
    int histogramHeight = height;

    // Scale factor to fit the histogram within the image height

    double scaleHeight = (double) histogramHeight / maxFrequency;
    double scaleWidth = (double) width / data.length; // Adjusted for the width of your image

    // Draw the histogram as a line graph
    g.setColor(color);
    for (int i = 1; i < data.length; i++) {
      int x1 = (int) ((i - 1) * scaleWidth);
      int y1 = histogramHeight - (int) (data[i - 1] * scaleHeight);
      int x2 = (int) (i * scaleWidth);
      int y2 = histogramHeight - (int) (data[i] * scaleHeight);

      // Draw a line connecting the peaks of consecutive values
      g.drawLine(x1, y1, x2, y2);
    }
  }

  private void drawGrid(Graphics g, int width, int height) {
    // Draw a simple grid for easy reference
    g.setColor(Color.LIGHT_GRAY);

    // Vertical grid lines
    for (int i = 0; i <= width; i += 10) {
      g.drawLine(i, 0, i, height);
    }

    // Horizontal grid lines
    for (int i = 0; i <= height; i += 10) {
      g.drawLine(0, i, width, i);
    }
  }

  private int findMax(int[] data) {
    int max = Integer.MIN_VALUE;
    for (int value : data) {
      max = Math.max(max, value);
    }
    return max;
  }
}
