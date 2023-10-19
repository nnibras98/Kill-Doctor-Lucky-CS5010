package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
  private String name;
  private int currentRoomIndex;
  private int maxCarryCapacity;
  private List<Item> inventory;

  public Player(String name, int maxCarryCapacity) {
      this.name = name;
      this.maxCarryCapacity = maxCarryCapacity;
      this.currentRoomIndex = 0; // Initialize the player in a starting room
      this.inventory = new ArrayList<>();
  }

  public void move(int newRoomIndex) {
      if (isValidRoomIndex(newRoomIndex)) {
          currentRoomIndex = newRoomIndex;
          System.out.println(name + " moved to room " + currentRoomIndex);
      } else {
          System.out.println("Invalid room index. " + name + " remains in room " + currentRoomIndex);
      }
  }

  public void pickUpItem(Item item) {
      if (inventory.size() < maxCarryCapacity) {
          inventory.add(item);
          System.out.println(name + " picked up " + item.getName());
      } else {
          System.out.println(name + " cannot carry more items. Inventory is full.");
      }
  }

  public void lookAround(List<Room> rooms) {
      System.out.println(name + " is in room " + currentRoomIndex + " and can see the following rooms:");
      for (Room room : rooms) {
          if (room.areNeighbors(rooms.get(currentRoomIndex))) {
              System.out.println("- " + room.getName());
          }
      }
  }

  public void displayPlayerInfo() {
      System.out.println("Player Name: " + name);
      System.out.println("Current Room: " + currentRoomIndex);
      System.out.println("Carry Capacity: " + maxCarryCapacity);
      System.out.println("Inventory: " + Arrays.toString(inventory.toArray()));
  }

  private boolean isValidRoomIndex(int roomIndex) {
      return roomIndex >= 0 && roomIndex < 21;
  }
}
