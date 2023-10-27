package model;

import java.util.Random;

/**
 * ComputerPlayer class represents a computer-controlled player in the game.
 */
public class ComputerPlayer extends HumanPlayer {
  
  /**
   * CP Constructor.
   * @param name name.
   * @param maxCarryLimit maxCarryLimit.
   * @param currentRoomIndex currentRoomIndex.
   * @param world world.
   */
  
  public ComputerPlayer(String name, int maxCarryLimit, int currentRoomIndex, World world) {

    super(name, maxCarryLimit, currentRoomIndex, world);

  }

  @Override
  public void takeTurn() {

    Random random = new Random();
    int choice = random.nextInt(3) + 1;

    switch (choice) {
      case 1:

        super.moveCharacterComputer();

        break;
      case 2:

        super.pickUpItemComputer();

        break;
      case 3:

        super.lookAround();

        break;
      default:
        System.out.println("Invalid choice.");
    }

  }
}
