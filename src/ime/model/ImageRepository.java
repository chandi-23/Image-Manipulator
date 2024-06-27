/**
 * The ImageRepository interface represents a repository for managing images in the application.
 */

package ime.model;

import java.util.InputMismatchException;

/**
 * The ImageRepository interface defines the contract for managing images, allowing retrieval
 * and storage of images.
 */
interface ImageRepository {

  /**
   * Retrieves an Image by its name from the repository.
   *
   * @param imageName The name of the image to retrieve.
   * @return The Image object corresponding to the provided image name.
   * @throws InputMismatchException If the image retrieval operation encounters an error
   *                                or mismatch.
   */
  Image getImage(String imageName) throws InputMismatchException;

  /**
   * Stores an Image in the repository with the specified name.
   *
   * @param imageName The name to associate with the Image in the repository.
   * @param image     The Image object to store in the repository.
   */
  void putImage(String imageName, Image image);

}
