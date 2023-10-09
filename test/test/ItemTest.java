package test;

import static org.junit.Assert.assertEquals;

import model.Item;
import org.junit.Test;

/**
 * Testing Item Class.
 */

public class ItemTest {
  @Test
  public void testGetRoomIndex() {
    Item item = new Item(1, 10, "Sword");
    assertEquals(1, item.getRoomIndex());
  }

  @Test
  public void testGetDamage() {
    Item item = new Item(1, 10, "Sword");
    assertEquals(10, item.getDamage());
  }

  @Test
  public void testGetName() {
    Item item = new Item(1, 10, "Sword");
    assertEquals("Sword", item.getName());
  }
}