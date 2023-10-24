package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.ComputerPlayer;
import model.HumanPlayer;
import model.Image;
import model.Item;
import model.Player;
import model.Room;
import model.World;

/**
 * game controller.
 */

public class GameControllerModel {

  private World world;
  private List<HumanPlayer> hp;
  private List<ComputerPlayer> bot;
  
  /**
   * constructor.
   * @param file file.
   */

  public GameControllerModel(String file) {
    this.world = new World(new File(file));
    this.hp = new ArrayList<>();
    this.bot = new ArrayList<>();

  }
  
  /**
   * start game.
   */
  
  public void startGame() {

    Scanner scanner = new Scanner(System.in);
    // Print game information and rules
    System.out.println("Welcome to the Game: " + world.getWorldName());
    System.out.println("The target character is : " + world.getTargetCharacter().getName());
    System.out.println("How many turns do you want to have?");

    int maxTurns = scanner.nextInt(); // Set a maximum number of turns

    System.out.println("How many human players do you want to create?");

    int numPlayers = scanner.nextInt();

    for (int i = 1; i <= numPlayers; i++) {

      System.out.println("Enter the name of player " + i + ": ");
      String playerName = scanner.next();

      System.out.println("Enter the maximum carrying capacity of player " + i + ": ");
      int playerCapacity = scanner.nextInt();

      System.out.println("Enter the initial room of player " + i + ": ");
      int playerRoom = scanner.nextInt();

      // Create a new Player object with the entered name
      HumanPlayer hplyr = new HumanPlayer(playerName, playerCapacity, playerRoom, world);

      // Add the player to the list
      hp.add(hplyr);
    }

    System.out.println("How many computer players do you want to create?");

    int numBots = scanner.nextInt();

    for (int i = 1; i <= numBots; i++) {

      System.out.println("Enter the name of bot " + i + ": ");
      String playerName = scanner.next();

      System.out.println("Enter the maximum carrying capacity of bot " + i + ": ");
      int playerCapacity = scanner.nextInt();

      System.out.println("Enter the initial room of bot " + i + ": ");
      int playerRoom = scanner.nextInt();

      // Create a new Player object with the entered name
      ComputerPlayer cplyr = new ComputerPlayer(playerName, playerCapacity, playerRoom, world);

      // Add the player to the list
      bot.add(cplyr);
    }

    System.out.println("Select Option");
    System.out.println("1- Describe room by room index.");
    System.out.println("2- See Player info.");
    System.out.println("3- Create mansion image.");
    System.out.println("4- Start the game.");

    int selection = scanner.nextInt();
    if (selection == 1) {
      System.out.println("Provide the index number");
      int index = scanner.nextInt();
      displayRoomInfoFromRoom(world.getRoomByIndex(index));

    } else if (selection == 2) {
      //apply
    } else if (selection == 3) {
      Image image = new Image("C:\\CS5010 WorkSpace\\kill-doctor-lucky\\res\\mansion.txt");
    } else {
      // Main game loop
      for (int turn = 1; turn <= maxTurns; turn++) {
        System.out.println("\nTurn " + turn + ":");
        System.out.println(" ");

        // Iterate through all human players and have them take their turns
        // Check if there are human players to iterate through
        if (hp != null) {
          for (Player player : hp) {
            if (player instanceof HumanPlayer) {
              HumanPlayer humanPlayer = (HumanPlayer) player;
              humanPlayer.takeTurn();
            }
          }
        } else {
          // Handle the case when there are no human players
          System.out.println("No human players to take a turn.");
        }

        // Computer player's turn
        // Iterate through all human players and have them take their turns
        // Check if there are computer players to iterate through
        if (bot != null) {
          for (Player player : bot) {
            if (player instanceof ComputerPlayer) {
              ComputerPlayer computerPlayer = (ComputerPlayer) player;
              computerPlayer.takeTurn();
            }
          }
        } else {
          // Handle the case when there are no computer players
          System.out.println("No computer players to take a turn.");
        }

        System.out.println(
            "Dr Lucky is in room: " + world.getTargetCharacter().getCharacterPositionIndex());

      }

      // Game ended, perform any final actions or display results
      System.out.println("Game over! Thank you for playing.");
    }
  }

  public World getWorld() {
    return world;
  }

  /**
   * disp method.
   * 
   * @param room room.
   */

  public void displayRoomInfoFromRoom(Room room) {
    List<Room> allRooms = world.getRooms(); // Populate allRooms from the world
    List<Item> allItems = world.getItems(); // Populate allItems from the world
    List<HumanPlayer> allHp = hp; // Populate allHp from the world
    List<ComputerPlayer> allCp = bot; // Populate allCp from the world

    room.displayRoomInfo(allRooms, allItems, allHp, allCp);
  }

  /**
   * main.
   * 
   * @param args argument.
   */
  public static void main(String[] args) {
    // Initialize the world
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please provide the path to the World file");
    String inputString = scanner.nextLine();

    // Create the game controller and start the game
    GameControllerModel gameController = new GameControllerModel(inputString);
    gameController.startGame();
  }
}