package test;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import model.ComputerPlayer;
import model.Room;
import model.World;
import org.junit.After;
import org.junit.Test;




/**
 * computer player class.
 */
public class ComputerPlayerTest {

  private ComputerPlayer computerPlayer;
  private World mockWorld;

  /**
   * setup.
   */
  public void setUp() {
    mockWorld = new World(new File("C:\\CS5010 WorkSpace\\kill-doctor-lucky\\res\\mansion.txt")); 
    // Initialize
                                                                                                  
    // your
                                                                                                 
    // mock
                                                                                                  
    // world
    computerPlayer = new ComputerPlayer("Bot", 5, 0, mockWorld); // Initialize the ComputerPlayer
  }

  @After
  public void tearDown() {
    mockWorld = null;
    computerPlayer = null;
  }

  @Test
  public void testTakeTurnMove() {
    // Ensure that the ComputerPlayer makes a valid move
    int initialRoom = computerPlayer.getCurrentRoom();
    computerPlayer.takeTurn();
    int newRoom = computerPlayer.getCurrentRoom();

    // Assert that the ComputerPlayer has moved to a valid neighboring room
    assertTrue(
        mockWorld.getNeighborsByRoomIndex(initialRoom).contains(mockWorld.getRoomByIndex(newRoom)));
  }

  @Test
  public void testTakeTurnPickUpItem() {
    // Ensure that the ComputerPlayer picks up an item correctly
    int initialRoom = computerPlayer.getCurrentRoom();
    int initialInventorySize = computerPlayer.getInventory().size();

    computerPlayer.takeTurn();

    // Assert that an item has been picked up and added to the inventory
    assertTrue(computerPlayer.getInventory().size() > initialInventorySize);

    // Ensure that the picked item has been removed from the room
    assertEquals(0, mockWorld.getItemsByRoomIndex(initialRoom).size());
  }

  @Test
  public void testTakeTurnLookAround() {
    // Ensure that the ComputerPlayer looks around correctly
    int initialRoom = computerPlayer.getCurrentRoom();

    // Set up neighboring rooms in the mock world
    Room neighboringRoom = new Room(1, 1, 1, 1, "Neighboring Room", 1); // Create a neighboring room

    computerPlayer.takeTurn();

    // Check that the ComputerPlayer's lookAround method correctly identifies
    // neighboring rooms
    String lookAroundOutput = getSystemOutContent(); // Capture the output

    // Assert that the output contains the name of the neighboring room
    assertTrue(lookAroundOutput.contains(neighboringRoom.getName()));
  }

  private String getSystemOutContent() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    return outContent.toString();
  }
}
