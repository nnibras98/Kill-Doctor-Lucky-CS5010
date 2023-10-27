package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * HP. 
 * @param name name.
 * @param maxCarryLimit maxCarryLimit.
 * @param currentRoomIndex currentRoomIndex.
 * @param world world.
 */

public class HumanPlayer extends Player {
  private final String name;
  private final int maxCarryLimit;
  private int currentRoomIndex;
  private List<Item> inventory;
  private World world;
  

  
  /**
   * Human Player Constructor.
   * @param nameIn name.
   * @param maxCarryLimitIn max capacity.
   * @param currentRoomIndexIn current room.
   * @param worldIn world.
   */
  public HumanPlayer(String nameIn, int maxCarryLimitIn, int currentRoomIndexIn, World worldIn) {
    super(nameIn, maxCarryLimitIn, currentRoomIndexIn);
    this.name = nameIn;
    this.maxCarryLimit = maxCarryLimitIn;
    this.currentRoomIndex = currentRoomIndexIn;
    this.inventory = new ArrayList<>();
    this.world = worldIn;

  }

  public String getName() {
    return name;
  }

  public int getMaxCarryLimit() {
    return maxCarryLimit;
  }

  public int getCurrentRoom() {
    return currentRoomIndex;
  }

  public void setCurrentRoom(int index) {
    this.currentRoomIndex = index;
  }

  public List<Item> getInventory() {
    return inventory;
  }

  /**
   * add Item.
   * @param item item.
   */
  
  public void addItemToInventory(Item item) {
    if (inventory.size() < maxCarryLimit) {
      inventory.add(item);
    } else {
      System.out.println("Inventory is full. You cannot carry more items.");
    }
  }
  
  /**
   * display player's info.
   */
  
  public void displayPlayerInfo() {
    System.out.println("Player Name: " + name);
    System.out.println("Current Room: " + currentRoomIndex);
    System.out.println("Carry Capacity: " + maxCarryLimit);
    System.out.println("Inventory: " + Arrays.toString(inventory.toArray()));
  }

  @Override
  public void takeTurn() {

    Scanner scanner = new Scanner(System.in);
    // Display player's turn information, including current room and inventory
    System.out.println("It's " + getName() + "'s turn.");
    System.out.println("Current room index: " + getCurrentRoom());
    System.out.println("Inventory: " + super.getInventory());

    int choice = 0;

    // Use a try-catch block to handle potential input exceptions
    try {
      // Prompt the player for their action
      // You can use a Scanner to read user input
      System.out.println("Choose an action:");
      System.out.println("1. Move to a neighboring room");
      System.out.println("2. Pick up an item");
      System.out.println("3. Look around");
      choice = scanner.nextInt();

      // Implement the chosen action based on the player's input
      switch (choice) {
        case 1:
          moveCharacter();
          break;
        case 2:
          pickUpItem();
          break;
        case 3:
          lookAround();
          break;
        default:
          System.out.println("Invalid choice.");
      }

      world.getTargetCharacter().moveCharacterForward();
    } catch (InputMismatchException e) {
      System.out.println("Invalid input. Please enter a valid integer choice.");
      scanner.nextLine(); // Consume the invalid input
    }
  }

  //
  protected void moveCharacter() {

    System.out.println();
    Scanner scanner = new Scanner(System.in);
    int indexOfChoice;
    List<Room> neighboringRooms = world.getNeighborsByRoomIndex(currentRoomIndex);
    if (!neighboringRooms.isEmpty()) {
      System.out.println("Neighbors of Room with Index " + currentRoomIndex + ":");
      for (Room room : neighboringRooms) {
        System.out.println("- " + room.getName() + ", Index: " + room.getIndex());
      }
      System.out.println();

      System.out.println("Provide the index of the room you want to move to");

      indexOfChoice = scanner.nextInt();

      super.move(indexOfChoice);

      currentRoomIndex = indexOfChoice;

    } else {
      System.out.println("No neighbors found for Room with Index " + currentRoomIndex);
    }
  }

  //
  protected void moveCharacterComputer() {

    System.out.println();
    System.out.println("It's the bot's turn");
    Scanner scanner = new Scanner(System.in);
    List<Room> neighboringRooms = world.getNeighborsByRoomIndex(currentRoomIndex);

    if (!neighboringRooms.isEmpty()) {
      System.out.println("Neighbors of Room with Index " + currentRoomIndex + ":");
      for (Room room : neighboringRooms) {
        System.out.print(room.getName() + ", Index: " + room.getIndex());
      }
      System.out.println();

      // Create a list of valid neighbor room indices
      List<Integer> validNeighborIndices = new ArrayList<>();
      for (Room room : neighboringRooms) {
        validNeighborIndices.add(room.getIndex());
      }

      // Generate a random index within the range of valid neighbor indices
      Random random = new Random();
      int randomIndex = validNeighborIndices.get(random.nextInt(validNeighborIndices.size()));

      System.out.println("Randomly chosen index: " + randomIndex);
      super.move(randomIndex);
      currentRoomIndex = randomIndex;
    } else {
      System.out.println("No neighbors found for Room with Index " + currentRoomIndex);
    }
    System.out.println("No neighbors found for Room with Index " + currentRoomIndex);
  }

  //
  protected void pickUpItem() {
    System.out.println();

    List<Item> items = world.getItemsByRoomIndex(currentRoomIndex);
    if (items.isEmpty()) {
        System.out.println("There are no items in the room.");
        return;
    }

    System.out.println("Items in the room with Index " + currentRoomIndex + ":");
    for (Item item : items) {
        System.out.print(item.getName() + ", ");
    }
    System.out.println();

    Scanner scanner = new Scanner(System.in);

    Item itemOfChoice = null;
    boolean validItemPicked = false;

    while (!validItemPicked) {
        try {
            System.out.println("Type the name of the item you want to pick: ");
            String itemTyped = scanner.nextLine();

            for (Item item : items) {
                if (item.getName().equalsIgnoreCase(itemTyped)) {
                    itemOfChoice = item;
                    super.pickUpItem(itemOfChoice); // Add the item to the inventory
                    items.remove(item); // Remove the item from the room
                    System.out.println("You picked up the " + item.getName() + " and added it to your inventory.");
                    validItemPicked = true;
                    break; // Exit the loop once an item is picked up
                }
            }

            if (!validItemPicked) {
                System.out.println("The item you selected does not exist. Try again.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid item name.");
        }
    }

    scanner.close();
}


  //
  protected void pickUpItemComputer() {
    System.out.println();
    System.out.println("It's the bot's turn");
    Scanner scanner = new Scanner(System.in);

    List<Item> items = world.getItemsByRoomIndex(currentRoomIndex);
    if (!items.isEmpty()) {
      System.out.println("Items in the room with Index " + currentRoomIndex + ":");
      for (Item item : items) {
        System.out.print(item.getName() + ", ");
      }
      System.out.println();

      // Create a list of available item indices
      List<Integer> availableItemIndices = new ArrayList<>();
      for (int i = 0; i < items.size(); i++) {
        availableItemIndices.add(i);
      }

      // Generate a random index to select an item from the list
      Random random = new Random();
      int randomItemIndex = availableItemIndices.get(random.nextInt(availableItemIndices.size()));
      Item itemOfChoice = items.get(randomItemIndex);

      System.out.println("Randomly chosen item: " + itemOfChoice.getName());
      super.pickUpItem(itemOfChoice);
    } else {
      System.out.println("Error!!!");
    }

  }

  //
  protected void lookAround() {
    System.out.println();
    List<Room> rooms = world.getRooms();

    super.lookAround(rooms);

  }

}
