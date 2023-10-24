package driver;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import model.Image;
import model.Item;
import model.Room;
import model.TargetCharacter;
import model.World;


/**
 * The GameDriver class is a driver class to test out runs.
 */
public class GameDriver {

  /**
   * The main driver class.
   * 
   * @param args Object class arguments.
   */
  public static void main(String[] args) {

    @SuppressWarnings("resource")
    Scanner scanner = new Scanner(System.in);

    System.out.println("Please provide the path to the World file");
    String inputString = scanner.nextLine();

    // Create a World object by specifying the world file
    World world = new World(new File(inputString));

    if (world.isEnd()) {
      System.out.println("File Read Successfully");
      System.out.println();

      System.out.println("Mansion Name: " + world.getWorldName());
      System.out.println();

      TargetCharacter targetCharacter = world.getTargetCharacter();
      System.out.println("Target Character Name: " + targetCharacter.getName());
      System.out.println("Target Character Health: " + targetCharacter.getHealth());
      System.out.println();

      System.out.println("Select Option");
      System.out.println("1- Describe room by room index.");
      System.out.println("2- See neighbours by room index.");
      System.out.println("3- See items by room index.");
      System.out.println("4- Move target forward");
      System.out.println("5- Show Info of all Rooms");
      System.out.println("6- Create mansion image");

      int option = scanner.nextInt();

      if (option == 1) {

        System.out.println("Provide the index number");
        int index = scanner.nextInt();
        Room room = world.getRoomByIndex(index);

        //        room.displayRoomInfo(world.getRooms(), world.getItems());

        if (room != null) {
          // Print room information
          System.out.println("Room Name: " + room.getName());
          System.out.println("Room Index: " + room.getIndex());
          System.out.println("Upper Left Row: " + room.getUpperLeftRow());
          System.out.println("Upper Left Column: " + room.getUpperLeftColumn());
          System.out.println("Lower Right Row: " + room.getLowerRightRow());
          System.out.println("Lower Right Column: " + room.getLowerRightColumn());
        } else {
          System.out.println("Room not found for index: " + index);
        }
      } else if (option == 2) {

        System.out.println("Provide the index number");
        int index = scanner.nextInt();
        List<Room> neighboringRooms = world.getNeighborsByRoomIndex(index);

        if (!neighboringRooms.isEmpty()) {
          System.out.println("Neighbors of Room with Index " + index + ":");
          for (Room room : neighboringRooms) {
            System.out.print(room.getName() + ", ");
          }
          System.out.println();
        } else {
          System.out.println("No neighbors found for Room with Index " + index);
        }

      } else if (option == 3) {

        System.out.println("Provide the index number");
        int index = scanner.nextInt();
        List<Item> itemsInRoom = world.getItemsByRoomIndex(index);

        if (!itemsInRoom.isEmpty()) {
          System.out.println("Items in Room with Index " + index + ":");
          for (Item item : itemsInRoom) {
            System.out.println("Item Name: " + item.getName());
            System.out.println("Item Damage: " + item.getDamage());
          }
          System.out.println();
        } else {
          System.out.println("No items found in Room with Index " + index);
        }
      } else if (option == 4) {
        targetCharacter.moveCharacterForward();
        targetCharacter.moveCharacterForward();

        int indexOfCharacter = targetCharacter.getCharacterPositionIndex();
        System.out.println("Target character is now in " + indexOfCharacter);
      } else if (option == 5) {
        for (int i = 0; i < 21; i++) {
          Room room = world.getRoomByIndex(i);
          //       room.displayRoomInfo(world.getRooms(), world.getItems(), world.getPlayers(), world.getBot());
        }
      } else {
        Image image = new Image(inputString);
      }
    }
  }
}
