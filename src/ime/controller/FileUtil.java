/**
 * The FileUtil class provides utility methods for working with files, including reading files
 * and extracting file extensions.
 */

package ime.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The FileUtil class offers methods for file-related operations, such as reading files and
 * extracting file extensions.
 */
public class FileUtil {

  /**
   * Reads the content of a file, ignoring comment lines and empty lines.
   *
   * @param filepath The path to the file to be read.
   * @return The cleaned content of the file as a string.
   * @throws FileNotFoundException If the specified file does not exist.
   */
  public static String cleanFileRead(String filepath) throws FileNotFoundException {

    Scanner sc = new Scanner(new FileInputStream(filepath));
    // Create a string builder to hold the file content
    StringBuilder fileContent = new StringBuilder();

    // read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNext()) {
      String line = sc.nextLine();
      // Ignore comments and empty lines
      if (!line.isEmpty() && !line.startsWith("#")) {
        fileContent.append(line).append(System.lineSeparator());
      }
    }

    return fileContent.toString();

  }

  /**
   * Extracts the file extension from a given file path.
   *
   * @param filepath The file path from which to extract the extension.
   * @return The file extension as a string.
   */
  public static String getFileExtension(String filepath) {
    String[] filePathParts = filepath.split("\\.");
    return filePathParts[filePathParts.length - 1];
  }

}
