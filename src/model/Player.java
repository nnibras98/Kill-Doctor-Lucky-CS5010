package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Player Class.
 */
public abstract class Player {

  private String name;
  private int currentRoomIndex;
  private int maxCarryCapacity;
  private List<Item> inventory;

  /**
   * Player Constructor.
   * 
   * @param nameIn             name.
   * @param maxCarryCapacityIn max capacity.
   * @param currentRoomIndexIn current room.
   */

  public Player(String nameIn, int maxCarryCapacityIn, int currentRoomIndexIn) {

    this.name = nameIn;
    this.maxCarryCapacity = maxCarryCapacityIn;
    this.currentRoomIndex = currentRoomIndexIn; // Initialize the player in a starting room
    this.inventory = new ArrayList<>();

  }

  /**
   * move method.
   * 
   * @param newRoomIndex index.
   */

  public void move(int newRoomIndex) {

    System.out.println();
    if (isValidRoomIndex(newRoomIndex)) {
      currentRoomIndex = newRoomIndex;
      System.out.println(name + " moved to room " + currentRoomIndex);

    } else {
      System.out.println("Invalid room index or it is not a neighbouring room. " + name
          + " remains in room " + currentRoomIndex);
    }
  }
  
  /**
   * pickup method.
   * @param item item.
   */
  
  public void pickUpItem(Item item) {
    if (inventory.size() < maxCarryCapacity) {
      inventory.add(item);
      System.out.println(name + " picked up " + item.getName());
    } else {
      System.out.println(name + " cannot carry more items. Inventory is full.");
    }

  }
  
  /**
   * look around method.
   * @param rooms room.
   */
  public void lookAround(List<Room> rooms) {
    System.out
        .println(name + " is in room " + currentRoomIndex + " and can see the following rooms:");

    Room currentRoom = rooms.get(currentRoomIndex); // Get the current room

    for (Room room : rooms) {
      if (room != currentRoom && currentRoom.areNeighbors(room)) {
        System.out.println("- " + room.getName());
      }
    }
  }
  
  /**
   * display player's info.
   */
  
  public void displayPlayerInfo() {
    System.out.println("Player Name: " + name);
    System.out.println("Current Room: " + currentRoomIndex);
    System.out.println("Carry Capacity: " + maxCarryCapacity);
    System.out.println("Inventory: " + Arrays.toString(inventory.toArray()));
  }

  private boolean isValidRoomIndex(int roomIndex) {

    if ((roomIndex >= 0 && roomIndex < 21)) {
      return true;

    }
    return false;
  }

  public String getName() {
    return name;
  }

  public void setName(String nameIn) {
    this.name = nameIn;
  }

  public int getCurrentRoomIndex() {
    return currentRoomIndex;
  }

  public void setCurrentRoomIndex(int currentRoomIndexIn) {
    this.currentRoomIndex = currentRoomIndexIn;
  }

  public int getMaxCarryCapacity() {
    return maxCarryCapacity;
  }

  public void setMaxCarryCapacity(int maxCarryCapacityIn) {
    this.maxCarryCapacity = maxCarryCapacityIn;
  }

  public List<Item> getInventory() {
    return inventory;
  }

  public void setInventory(List<Item> inventoryIn) {
    this.inventory = inventoryIn;
  }

  public abstract void takeTurn();

}
