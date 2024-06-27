/**
 * The PlotHistogram interface defines a contract for classes capable of plotting histograms
 * based on input histogram data.
 * <p>
 * Implementing classes are expected to provide a method for generating a visual representation
 * (e.g., an image) of the given histogram data.
 * </p>
 */

package ime.model;

import java.awt.image.BufferedImage;

interface PlotHistogram {

  /**
   * Plots a histogram using the provided histogram data and returns the resulting image.
   *
   * @param histogramData A two-dimensional array representing the histogram data.
   * @return A BufferedImage containing the visual representation of the histogram.
   */
  BufferedImage plot(int[][] histogramData);

}
