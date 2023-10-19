package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Room class.
 */

public class Room {
  private int upperLeftRow;
  private int upperLeftColumn;
  private int lowerRightRow;
  private int lowerRightColumn;
  private int index;
  private String name;
  
  /**
   * constructor for room.
   * @param upperLeftRowIn walls of the room.
   * @param upperLeftColumnIn walls of the room.
   * @param lowerRightRowIn walls of the room.
   * @param lowerRightColumnIn walls of the room.
   * @param nameIn name of the room.
   * @param indexIn placement of the room.
   */

  public Room(int upperLeftRowIn, int upperLeftColumnIn, int lowerRightRowIn, 
      int lowerRightColumnIn,
      String nameIn, int indexIn) {

    this.upperLeftRow = upperLeftRowIn;
    this.upperLeftColumn = upperLeftColumnIn;
    this.lowerRightRow = lowerRightRowIn;
    this.lowerRightColumn = lowerRightColumnIn;
    this.name = nameIn;
    this.index = indexIn;
  }

  public int getUpperLeftRow() {
    return upperLeftRow;
  }

  public int getUpperLeftColumn() {
    return upperLeftColumn;
  }

  public int getLowerRightRow() {
    return lowerRightRow;
  }

  public int getLowerRightColumn() {
    return lowerRightColumn;
  }

  public String getName() {
    return name;
  }

  public int getIndex() {
    return index;
  }

  /**
   * get neighboring rooms by giving list of all rooms.
   * @param allRooms list of all the rooms
   * @return all the neighbors
   */
  // Get a list of all neighboring rooms
  public List<Room> getNeighbors(List<Room> allRooms) {
    List<Room> neighbors = new ArrayList<>();

    for (Room otherRoom : allRooms) {
      if (areNeighbors(otherRoom)) {
        neighbors.add(otherRoom);
      }
    }

    return neighbors;
  }
  
  /**
   * checks whether two rooms are neighbors or not.
   * @param otherRoom takes the other room
   * @return true or false
   */
  
  // Check if two rooms are neighbors
  //There is a bug which I am trying to fix, not sure if it will make it to the production tonight
  public boolean areNeighbors(Room otherRoom) {
    // Check if the rooms share a common "wall"
    boolean verticallyAdjacent = ((this.lowerRightRow == otherRoom.upperLeftRow - 1)
        || (this.upperLeftRow == otherRoom.lowerRightRow + 1));
    boolean horizontallyAdjacent = ((this.lowerRightColumn == otherRoom.upperLeftColumn - 1)
        || (this.upperLeftColumn == otherRoom.lowerRightColumn + 1));

    return (verticallyAdjacent && !horizontallyAdjacent) || (!verticallyAdjacent && horizontallyAdjacent);
  }

  /**
   * displays all the info of room.
   * @param allRooms list of rooms.
   * @param allItems list of items.
   */
  public void displayRoomInfo(List<Room> allRooms, List<Item> allItems) {
    System.out.println("Room Name: " + this.getName());

    // Display items in the room
    System.out.println("Items in the Room:");
    for (Item item : getItemsInRoom(allItems)) {
      System.out.println("- " + item.getName());
    }

    // Display spaces that can be seen from this room
    System.out.println("Spaces That Can Be Seen:");
    List<Room> visibleRooms = getVisibleRooms(allRooms);
    for (Room visibleRoom : visibleRooms) {
      System.out.println("- " + visibleRoom.getName());
    }
    
    System.out.println(" ");
  }

  /**
   * gets items from room.
   * @param allItems list of all items.
   * @return items in the room.
   */
  
  public List<Item> getItemsInRoom(List<Item> allItems) {
    List<Item> itemsInRoom = new ArrayList<>();
    for (Item item : allItems) {
      if (item.getRoomIndex() == getIndex()) {
        itemsInRoom.add(item);
      }
    }
    return itemsInRoom;
  }

  /**
   * gets all the neighbors.
   * @param allRooms list of all rooms.
   * @return list of visible room(s).
   */
  
  public List<Room> getVisibleRooms(List<Room> allRooms) {
    List<Room> visibleRooms = new ArrayList<>();
    for (Room room : allRooms) {
      if (room != this) {

        if (areNeighbors(room)) {
          visibleRooms.add(room);
        }
      }
    }
    return visibleRooms;
  }
  
  

}
