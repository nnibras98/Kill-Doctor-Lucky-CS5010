package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * This class implements the WorldReader class. It takes in a text file with
 * information about and the world and then creates the world.
 */
public class World implements WorldReader {

  private TargetCharacter targetCharacter;
  private List<Room> rooms;
  private List<Item> items;
  private int numRows;
  private int numColumns;
  private String worldName;
  private boolean end;

  /**
   * World object creation using text file.
   * 
   * @param file takes in a file.
   */
  public World(File file) {

    this.rooms = new ArrayList<>(); // Initialize the rooms list
    this.items = new ArrayList<>(); // Initialize the items list
    this.end = false;
    parseWorldFromFile(file);

  }

  @Override
  public void parseWorldFromFile(File file) {

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      // Read the first line to get the world description
      String worldDescription = reader.readLine();
      String[] worldDescriptionParts = worldDescription.split(" ");
      if (worldDescriptionParts.length >= 5) {
        this.numRows = Integer.parseInt(worldDescriptionParts[0]);
        this.numColumns = Integer.parseInt(worldDescriptionParts[1]);
        this.worldName = worldDescriptionParts[2] + " " + worldDescriptionParts[3] + " "
            + worldDescriptionParts[4];

      }

      // Read Target Character Description
      String targetCharacterDescription = reader.readLine();
      String[] targetCharacterDescriptionParts = targetCharacterDescription.split(" ");
      if (targetCharacterDescriptionParts.length == 3) {

        this.targetCharacter = new TargetCharacter(
            Integer.parseInt(targetCharacterDescriptionParts[0]),
            targetCharacterDescriptionParts[1] + " " + targetCharacterDescriptionParts[2]);
      }

      // Read the line with the number of rooms
      int numRooms = Integer.parseInt(reader.readLine());

      // Parse the rooms
      for (int i = 0; i < numRooms; i++) {
        String roomInfo = reader.readLine();
        String[] roomInfoParts = roomInfo.split(" ");
        if (roomInfoParts.length >= 5) {
          int upperLeftRow = Integer.parseInt(roomInfoParts[0]);
          int upperLeftColumn = Integer.parseInt(roomInfoParts[1]);
          int lowerRightRow = Integer.parseInt(roomInfoParts[2]);
          int lowerRightColumn = Integer.parseInt(roomInfoParts[3]);
          int index = i;

          // Combine the remaining parts to form the room name
          StringBuilder roomNameBuilder = new StringBuilder();
          for (int j = 4; j < roomInfoParts.length; j++) {
            roomNameBuilder.append(roomInfoParts[j]);
            if (j < roomInfoParts.length - 1) {
              roomNameBuilder.append(" "); // Add space between words
            }
          }
          String roomName = roomNameBuilder.toString();

          Room room = new Room(upperLeftRow, upperLeftColumn, lowerRightRow, lowerRightColumn,
              roomName, index);
          rooms.add(room);

        }
      }

      // Read the line with the number of items
      int numItems = Integer.parseInt(reader.readLine());

      // Parse the items
      for (int i = 0; i < numItems; i++) {
        // Read the line containing item information
        String itemInfo = reader.readLine();

        // Split the item information into parts
        String[] itemInfoParts = itemInfo.split(" ");

        // Check if there are enough parts to process
        if (itemInfoParts.length >= 3) {
          StringBuilder itemNameBuilder = new StringBuilder();
          for (int j = 2; j < itemInfoParts.length; j++) {
            itemNameBuilder.append(itemInfoParts[j]);
            if (j < itemInfoParts.length - 1) {
              itemNameBuilder.append(" ");
            }
          }
          String itemName = itemNameBuilder.toString();

          int roomIndex = Integer.parseInt(itemInfoParts[0]);
          int damage = Integer.parseInt(itemInfoParts[1]);

          // Create the Item object with the parsed values
          Item item = new Item(roomIndex, damage, itemName);

          // Add the item to the list
          items.add(item);
        }
      }

      end = true;
      
      

    } catch (IOException e) {
      // Handle exceptions
      System.err.println("An error occurred while reading the file");

    }

  }
  


  @Override
  public String toString() {
    return "World [targetCharacter=" + targetCharacter + ", rooms=" + rooms + ", items=" + items
        + ", numRows=" + numRows + ", numColumns=" + numColumns + ", worldName=" + worldName
        + ", end=" + end + "]";
  }

  @Override
  public int hashCode() {
    return Objects.hash(end, items, numColumns, numRows, rooms, targetCharacter, worldName);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    World other = (World) obj;
    return end == other.end && Objects.equals(items, other.items) && numColumns == other.numColumns
        && numRows == other.numRows && Objects.equals(rooms, other.rooms)
        && Objects.equals(targetCharacter, other.targetCharacter)
        && Objects.equals(worldName, other.worldName);
  }

  /**
   * Method to get Room by using index.
   * 
   * @param index position of the room.
   * @return room or null depending on validity of index
   */
  public Room getRoomByIndex(int index) {
    for (Room room : rooms) {
      if (room.getIndex() == index) {
        return room;
      }
    }

    return null;
  }

  /**
   * Method to get Neighbor Room by using index.
   * 
   * @param roomIndex position of the room.
   * @return neighboring rooms.
   */
  public List<Room> getNeighborsByRoomIndex(int roomIndex) {
    List<Room> neighboringRooms = new ArrayList<>();
    Room targetRoom = getRoomByIndex(roomIndex);

    if (targetRoom != null) {
      for (Room room : rooms) {
        if (room != targetRoom && room.areNeighbors(targetRoom)) {
          neighboringRooms.add(room);
        }
      }
    }

    return neighboringRooms;
  }


  /**
   * Method to get Item(s) by using index.
   * 
   * @param roomIndex position of the room.
   * @return list of item(s)
   */
  public List<Item> getItemsByRoomIndex(int roomIndex) {
    List<Item> itemsInRoom = new ArrayList<>();

    for (Item item : items) {
      if (item.getRoomIndex() == roomIndex) {
        itemsInRoom.add(item);
      }
    }

    return itemsInRoom;
  }
  


  @Override
  public int getWorldRows() {
    return numRows;
  }

  @Override
  public int getWorldColumns() {
    return numColumns;
  }

  @Override
  public String getWorldName() {
    return worldName;
  }

  public TargetCharacter getTargetCharacter() {

    return targetCharacter;
  }

  public List<Room> getRooms() {

    return rooms;
  }

  public List<Item> getItems() {

    return items;
  }

  public void removeItem(Item item) {
    items.remove(item);
  }

  public boolean isEnd() {
    return end;
  }





}
