package model;

/**
 * Helper class to build target character.
 */

public class TargetCharacter {
  private int health;
  private String name;
  private int characterPositionIndex = 0;
  
  /**
   * Constructor for target character.
   * @param healthIn health of target.
   * @param nameIn name of target.
   */

  public TargetCharacter(int healthIn, String nameIn) {
    this.health = healthIn;
    this.name = nameIn;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int healthIn) {
    this.health = healthIn;
  }

  public String getName() {
    return name;
  }

  public void setName(String nameIn) {
    this.name = nameIn;
  }

  public int getCharacterPositionIndex() {
    return characterPositionIndex;
  }

  /**
   * moves character forward by 1 room.
   */
  
  public void moveCharacterForward() {

    if (characterPositionIndex > 21) {
      throw new IllegalArgumentException("Invalid Movement");
    }

    characterPositionIndex += 1;
  }
  
  /**
   * moves character backward by 1 room.
   */
  
  public void moveCharacterBackward() {

    if (characterPositionIndex < 0) {
      throw new IllegalArgumentException("Invalid Movement");
    }
    characterPositionIndex -= 1;

  }
}
