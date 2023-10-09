package test;

import static org.junit.Assert.assertEquals;

import model.TargetCharacter;
import org.junit.Before;
import org.junit.Test;

/**
 * class for testing target character.
 */

public class TargetCharacterTest {

  private TargetCharacter character;

  @Before
  public void setUp() {
    // Initialize a sample TargetCharacter
    character = new TargetCharacter(100, "John");
  }

  @Test
  public void testGetHealth() {
    // Test getting character health
    assertEquals(100, character.getHealth());
  }

  @Test
  public void testSetHealth() {
    // Test setting character health
    character.setHealth(80);
    assertEquals(80, character.getHealth());
  }

  @Test
  public void testGetName() {
    // Test getting character name
    assertEquals("John", character.getName());
  }

  @Test
  public void testSetName() {
    // Test setting character name
    character.setName("Alice");
    assertEquals("Alice", character.getName());
  }

  @Test
  public void testGetCharacterPositionIndex() {
    // Test getting character position index
    assertEquals(0, character.getCharacterPositionIndex());
  }

  @Test
  public void testMoveCharacterForward() {
    // Test moving the character forward
    character.moveCharacterForward();
    assertEquals(1, character.getCharacterPositionIndex());
  }

  @Test
  public void testMoveCharacterBackward() {
    // Test moving the character backward
    character.moveCharacterBackward();
    assertEquals(-1, character.getCharacterPositionIndex());
  }

}
