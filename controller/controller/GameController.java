package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.ComputerPlayer;
import model.HumanPlayer;
import model.Item;
import model.Player;
import model.Room;
import model.World;

/**
 * game controller.
 */
public class GameController {
  private World world;
  private List<HumanPlayer> hp;
  private List<ComputerPlayer> bot;
  private Reader reader;
  private PrintWriter writer;
  
  /**
   * game constructor.
   * @param file file.
   * @param input input.
   * @param output output.
   */
  
  public GameController(String file, Readable input, Appendable output) {
    this.world = new World(new File(file));
    this.hp = new ArrayList<>();
    this.bot = new ArrayList<>();
    this.reader = new BufferedReader(new InputStreamReader(System.in));
    this.writer = new PrintWriter(new OutputStreamWriter(System.out), true);
  }
  
  /**
   * start game.
   */
  
  public void startGame() {
    // Initialize input and output for user interactions
    

    // Print game information and rules
    writer.println("Welcome to the Game: " + world.getWorldName());
    writer.println("The target character is: " + world.getTargetCharacter());

    // Ask for the maximum number of turns
    
    Scanner scanner = new Scanner(reader);
    

    // Ask for the number of human players
    writer.println("How many human players do you want to create?");
    int numPlayers = Integer.parseInt(scanner.nextLine());

    for (int i = 1; i <= numPlayers; i++) {
      writer.println("Enter the name of player " + i + ": ");
      String playerName = scanner.nextLine();

      writer.println("Enter the maximum carrying capacity of player " + i + ": ");
      int playerCapacity = Integer.parseInt(scanner.nextLine());

      writer.println("Enter the initial room of player " + i + ": ");
      int playerRoom = Integer.parseInt(scanner.nextLine());

      // Create a new Player object with the entered name
      HumanPlayer hplyr = new HumanPlayer(playerName, playerCapacity, playerRoom, world);

      // Add the player to the list
      hp.add(hplyr);
    }

    writer.println("How many computer players do you want to create?");
    int numBots = Integer.parseInt(scanner.nextLine());

    for (int i = 1; i <= numBots; i++) {
      writer.println("Enter the name of bot " + i + ": ");
      String playerName = scanner.nextLine();

      writer.println("Enter the maximum carrying capacity of bot " + i + ": ");
      int playerCapacity = Integer.parseInt(scanner.nextLine());

      writer.println("Enter the initial room of bot " + i + ": ");
      int playerRoom = Integer.parseInt(scanner.nextLine());

      // Create a new Player object with the entered name
      ComputerPlayer cplyr = new ComputerPlayer(playerName, playerCapacity, playerRoom, world);

      // Add the player to the list
      bot.add(cplyr);
    }
    
    writer.println("How many turns do you want to have?");
    int maxTurns = Integer.parseInt(scanner.nextLine()); // Set a maximum number of turns
    
    // Main game loop
    for (int turn = 1; turn <= maxTurns; turn++) {
      writer.println("\nTurn " + turn + ":");
      writer.println(" ");

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
        writer.println("No human players to take a turn.");
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
        writer.println("No computer players to take a turn.");
      }

      writer.println(
          "Dr Lucky is in room: " + world.getTargetCharacter().getCharacterPositionIndex());
    }

    // Game ended, perform any final actions or display results
    writer.println("Game over! Thank you for playing.");
    writer.flush();
  }

  public World getWorld() {
    return world;
  }
  
  /**
   * disp method.
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
   * main method.
   * @param args arguments.
   */
  public static void main(String[] args) {
    // Check if command-line arguments are provided
    if (args.length < 2) {
      System.out.println("Usage: java GameController <world_file> <max_turns>");
      System.exit(1);
    }

    String worldFile = args[0];
    int maxTurns = Integer.parseInt(args[1]);

    // Create the game controller and start the game
    GameController gameController = new GameController(worldFile, (Readable) System.in, System.out);
    gameController.startGame();
  }
}
