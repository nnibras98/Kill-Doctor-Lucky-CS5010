package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import model.Item;
import model.Room;
import org.junit.Before;
import org.junit.Test;

/**
 * class for room test.
 */

public class RoomTest {

  private Room room;
  private List<Room> allRooms;
  private List<Item> allItems;
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

  /**
   * setUP.
   */
  
  @Before
  public void setUp() {
    // Initialize a sample room and lists of rooms and items
    room = new Room(0, 0, 1, 1, "Sample Room", 0);
    allRooms = new ArrayList<>();
    allRooms.add(room);
    allRooms.add(new Room(2, 2, 3, 3, "Adjacent Room", 1)); // Adjacent room
    allItems = new ArrayList<>();
    allItems.add(new Item(0, 0, "Item 1"));
    allItems.add(new Item(1, 1, "Item 2"));
      
    System.setOut(new PrintStream(outContent));
  }

  @Test
  public void testGetNeighbors() {
    // Test getting neighboring rooms
    List<Room> neighbors = room.getNeighbors(allRooms);
    assertEquals(1, neighbors.size()); // Expect 1 neighbor (the adjacent room)
    assertTrue(neighbors.contains(allRooms.get(1))); // Check if it's the adjacent room
  }

  @Test
  public void testAreNeighbors() {
    // Test checking if two rooms are neighbors
    Room adjacentRoom = allRooms.get(1);
    assertTrue(room.areNeighbors(adjacentRoom)); // Should be neighbors
  }

  @Test
  public void testAreNotNeighbors() {
    // Test checking if two rooms are not neighbors
    Room nonAdjacentRoom = new Room(10, 10, 11, 11, "Non-Adjacent Room", 2);
    assertFalse(room.areNeighbors(nonAdjacentRoom)); // Should not be neighbors
  }

  @Test
  public void testGetItemsInRoom() {
    // Test getting items in the room
    List<Item> itemsInRoom = room.getItemsInRoom(allItems);
    assertEquals(1, itemsInRoom.size()); // Expect 1 item in the room
    assertEquals("Item 1", itemsInRoom.get(0).getName());
  }

  @Test
  public void testGetVisibleRooms() {
    // Test getting visible rooms
    List<Room> visibleRooms = room.getVisibleRooms(allRooms);
    assertEquals(1, visibleRooms.size()); // Expect 1 visible room (the adjacent room)
    assertTrue(visibleRooms.contains(allRooms.get(1))); // Check if it's the adjacent room
  }
  
  @Test
  public void testDisplayRoomInfo() {
    // Test displaying room information
    room.displayRoomInfo(allRooms, allItems);

    // Capture the printed output
    String printedOutput = outContent.toString();

    // Verify the printed output contains expected information
    assertTrue(printedOutput.contains("Room Name: Sample Room"));
    assertTrue(printedOutput.contains("Items in the Room:"));
    assertTrue(printedOutput.contains("- Item 1"));
    assertTrue(printedOutput.contains("Spaces That Can Be Seen:"));
    assertTrue(printedOutput.contains("- Adjacent Room"));
  }



}
