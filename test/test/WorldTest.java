package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import model.Item;
import model.Room;
import model.World;
import org.junit.Before;
import org.junit.Test;



/**
 * World Test Class.
 */

public class WorldTest {

  private World world;
  
  /**
   * setUp method.
   */
  @Before
  public void setUp() {
    // Initialize the World instance with a sample world file
    File worldFile = new File("C:\\CS5010 WorkSpace\\CS5010 Project\\res\\res\\mansion.txt");
    world = new World(worldFile);
  }

  @Test
  public void testGetWorldRows() {
    int expectedRows = 36;
    assertEquals(expectedRows, world.getWorldRows());
  }

  @Test
  public void testGetWorldColumns() {
    int expectedColumns = 30;
    assertEquals(expectedColumns, world.getWorldColumns());
  }

  @Test
  public void testGetWorldName() {
    String expectedName = "Doctor Lucky's Mansion";
    assertEquals(expectedName, world.getWorldName());
  }

  @Test
  public void testGetTargetCharacter() {
    assertNotNull(world.getTargetCharacter());
    assertEquals("Doctor Lucky", world.getTargetCharacter().getName());
    assertEquals(50, world.getTargetCharacter().getHealth());
  }

  @Test
  public void testGetRooms() {
    List<Room> rooms = world.getRooms();
    assertNotNull(rooms);
    assertFalse(rooms.isEmpty());
  }

  @Test
  public void testGetItems() {
    List<Item> items = world.getItems();
    assertNotNull(items);
    assertFalse(items.isEmpty());

  }

  @Test
  public void testGetRoomByIndex() {
    int index = 0;
    Room room = world.getRoomByIndex(index);
    assertNotNull(room);
    assertEquals(index, room.getIndex());
  }

  @Test
  public void testGetNeighborsByRoomIndex() {
    int roomIndex = 0;
    List<Room> neighboringRooms = world.getNeighborsByRoomIndex(roomIndex);
    assertNotNull(neighboringRooms);

  }

  @Test
  public void testGetItemsByRoomIndex() {
    int roomIndex = 0;
    List<Item> itemsInRoom = world.getItemsByRoomIndex(roomIndex);
    assertNotNull(itemsInRoom);

  }

  @Test
  public void testIsEnd() {
    assertTrue(world.isEnd());
  }

  @Test
  public void testParseWorldFromFileIoException() throws IOException {
    // Test handling IOException when the file does not exist
    File nonExistentFile = new File("non_existent_file.txt");
    new World(nonExistentFile);
  }

  @Test
  public void testToString() {
    // Test the toString method
    String expectedString = "World [targetCharacter=" + world.getTargetCharacter() + ", rooms="
        + world.getRooms() + ", items=" + world.getItems() + ", numRows=" + world.getWorldRows()
        + ", numColumns=" + world.getWorldColumns() + ", worldName=" + world.getWorldName()
        + ", end=" + world.isEnd() + "]";
    assertEquals(expectedString, world.toString());
  }

  @Test
  public void testHashCode() {
    // Test the hashCode method
    int expectedHashCode = Objects.hash(world.isEnd(), world.getItems(), world.getWorldColumns(),
        world.getWorldRows(), world.getRooms(), world.getTargetCharacter(), world.getWorldName());
    assertEquals(expectedHashCode, world.hashCode());
  }

}
