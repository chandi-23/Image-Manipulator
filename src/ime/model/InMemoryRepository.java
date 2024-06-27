/**
 * The InMemoryRepository class implements the ImageRepository interface, providing a simple
 * in-memory storage solution for image data.
 */

package ime.model;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

/**
 * The InMemoryRepository class serves as an in-memory image repository, storing images by their
 * associated names.
 */
class InMemoryRepository implements ImageRepository {

  private final Map<String, Image> images;

  /**
   * Constructs an instance of the InMemoryRepository class, initializing an empty image storage.
   */
  public InMemoryRepository() {
    this.images = new HashMap<>();
  }

  // Methods for retrieving and storing images:

  /**
   * Retrieves an image with the specified name from the in-memory repository.
   *
   * @param imageName The name of the image to retrieve.
   * @return The Image object associated with the provided image name.
   * @throws InputMismatchException If the image is not found in the repository.
   */
  @Override
  public Image getImage(String imageName) throws InputMismatchException {
    if (!images.containsKey(imageName)) {
      throw new InputMismatchException("Image with the given name not present");
    }
    return images.get(imageName);
  }

  /**
   * Stores an image in the in-memory repository, associating it with the specified image name.
   *
   * @param imageName The name to associate with the provided image.
   * @param image The Image object to be stored.
   */
  @Override
  public void putImage(String imageName, Image image) {
    images.put(imageName, image);
  }
}
