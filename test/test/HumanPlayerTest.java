package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import model.HumanPlayer;
import model.Item;
import model.World;
import org.junit.Before;
import org.junit.Test;



/**
 * humanplayer tests.
 */

public class HumanPlayerTest {

  private HumanPlayer humanPlayer;
  private World mockWorld;

  @Before
  public void setUp() {
    mockWorld = new World(new File("C:\\CS5010 WorkSpace\\kill-doctor-lucky\\res\\mansion.txt"));
    humanPlayer = new HumanPlayer("Alice", 5, 1, mockWorld);
  }

  @Test
  public void testGetName() {
    assertEquals("Alice", humanPlayer.getName());
  }

  @Test
  public void testGetMaxCarryLimit() {
    assertEquals(5, humanPlayer.getMaxCarryLimit());
  }

  @Test
  public void testGetCurrentRoom() {
    assertEquals(1, humanPlayer.getCurrentRoom());
  }

  @Test
  public void testSetCurrentRoom() {
    humanPlayer.setCurrentRoom(2);
    assertEquals(2, humanPlayer.getCurrentRoom());
  }

  @Test
  public void testGetInventory() {
    Item item1 = new Item(1, 2, "Item1");
    Item item2 = new Item(1, 2, "Item2");
    humanPlayer.addItemToInventory(item1);
    humanPlayer.addItemToInventory(item2);

    List<Item> inventory = humanPlayer.getInventory();
    assertTrue(inventory.contains(item1));
    assertTrue(inventory.contains(item2));
    assertEquals(2, inventory.size());
  }

  @Test
  public void testTakeTurnInvalidChoice() {
    String input = "5\n"; // Simulate user input with an invalid choice (not 1, 2, or 3)
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    // Capture System.out output
    String expectedOutput = "Invalid choice.\n";
    assertEquals(expectedOutput, captureSystemOut(() -> humanPlayer.takeTurn()));

    System.setIn(System.in); // Reset System.in
  }

  // Helper method to capture System.out output
  private String captureSystemOut(Runnable action) {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    action.run();
    System.setOut(System.out);
    return outputStream.toString();
  }
}
