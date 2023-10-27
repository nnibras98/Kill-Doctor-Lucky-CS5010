package model;

import java.io.File;

/**
 * Interface for creating and reading a World from a text file.
 */
public interface WorldReader {

  /**
     * Parses the world specification from a text file.
     *
     * @param file The File containing the world specification.
     */
  void parseWorldFromFile(File file);

  /**
     * Gets the number of rows in the world.
     *
     * @return The number of rows in the world.
     */
  int getWorldRows();

  /**
     * Gets the number of columns in the world.
     *
     * @return The number of columns in the world.
     */
  int getWorldColumns();

  /**
     * Gets the name of the world.
     *
     * @return The name of the world.
     */
  String getWorldName();
}