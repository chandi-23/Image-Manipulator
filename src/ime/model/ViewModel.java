package ime.model;

/**
 * ViewModel represents the view model responsible for providing image data
 * through the IViewModel interface. It is constructed with an IMEFacade adapter
 * for accessing image-related functionalities.
 */
public class ViewModel implements IViewModel {

  private final IMEFacade adapter;

  /**
   * Constructs a new ViewModel with the specified IMEFacade adapter.
   *
   * @param adapter The adapter providing access to image processing functionalities.
   */
  public ViewModel(IMEFacade adapter) {
    this.adapter = adapter;
  }

  @Override
  public int[][][] getImage(String imageName) {
    return adapter.getRawImageData(imageName);
  }

}
