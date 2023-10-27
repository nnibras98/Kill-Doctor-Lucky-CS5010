package test;

import static org.junit.Assert.assertTrue;

import controller.GameController;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.concurrent.TimeUnit;
import model.HumanPlayer;
import model.Room;
import model.World;
import model.Player;
import org.junit.Before;
import org.junit.Test;

/**
 * controller.
 */
public class GameControllerTest {
  private GameController gameController;

  /**
   * setup.
   */
  
  @Before
  public void setup() {
    String input = "2\nAlice\n5\n3\nBob\n4\n1\n"; // Simulate input for two human players and one
    // computer player
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    gameController = new GameController("mocked_file", new StringReader(input),
        new PrintWriter(System.out));
  }

  @Test
  public void testGameSetup() {
    gameController.startGame();
    //        List<HumanPlayer> humanPlayers = gameController.getHumanPlayers();
    //        List<ComputerPlayer> computerPlayers = gameController.getComputerPlayers();
    //        assertEquals(2, humanPlayers.size());
    //        assertEquals(1, computerPlayers.size());
  }

  @Test
  public void testMainGameLoop() {
    gameController.startGame();

    for (int i = 0; i < 3; i++) {
    //            gameController.getHumanPlayers().get(0).takeTurn();
    //            gameController.getComputerPlayers().get(0).takeTurn();
    }

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    System.setOut(new PrintStream(baos));
    try {
      TimeUnit.SECONDS.sleep(1); // Wait a moment for the output to be generated
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    String gameOutput = baos.toString();
    assertTrue(gameOutput.contains("Game over! Thank you for playing."));
  }

  @Test
  public void testDisplayRoomInfo() {
    Room mockRoom = new Room(1, 1, 1, 1, "Neighboring Room", 1);
    HumanPlayer mockHumanPlayer = new HumanPlayer("Mock Player", 10, 1,
        new World(new File("C:\\CS5010 WorkSpace\\kill-doctor-lucky\\res\\mansion.txt")));

    World mockWorld = new World(
        new File("C:\\CS5010 WorkSpace\\kill-doctor-lucky\\res\\mansion.txt"));
    //        mockWorld.addRoom(mockRoom);
    //        mockWorld.addItem(mockItem);
    //        gameController.setWorld(mockWorld);
    //
    //        gameController.getHumanPlayers().add(mockHumanPlayer);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    System.setOut(new PrintStream(baos));

    gameController.displayRoomInfoFromRoom(mockRoom);

    String output = baos.toString();
    assertTrue(output.contains("Mock Room"));
    assertTrue(output.contains("Mock Item"));
    assertTrue(output.contains("Mock Player"));
  }

  @Test
  public void testMainMethod() {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    System.setOut(new PrintStream(baos));

    String[] args = { "sample_world.txt", "5" };
    GameController.main(args);

    try {
      TimeUnit.SECONDS.sleep(1); // Wait a moment for the output to be generated
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    String output = baos.toString();
    assertTrue(output.contains("Game started with file: sample_world.txt and max turns: 5"));
  }
}
