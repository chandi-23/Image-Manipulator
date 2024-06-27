package ime.view;

import ime.controller.Features;

/**
 * The IView interface defines methods for interacting with the user interface (UI)
 * to display images, refresh views, add features, and handle error messages.
 */
public interface IView {

  /**
   * Refreshes the view with the specified image and histogram image names.
   *
   * @param imageName           The name or identifier of the main image to be displayed.
   * @param histogramImageName The name or identifier of the histogram image to be displayed.
   */
  void refresh(String imageName, String histogramImageName);

  /**
   * Provides the callback functions from the controller to the view.
   *
   * @param controller the callback functions from the controller to the view.
   */
  void addFeatures(Features controller);

  /**
   * Displays an error message in the view, along with information from the provided exception.
   *
   * @param ex      The exception that occurred and triggered the error message.
   * @param message The error message to be displayed in the UI.
   */
  void displayErrorMessage(Exception ex, String message);

}
