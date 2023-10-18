package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

  @Test
  void fire_Success(){
    // Arrange
    TorpedoStore store = new TorpedoStore(1);

    // Act
    boolean result = store.fire(1);

    // Assert
    assertEquals(true, result);
  }

  @Test
  void fire_Zero_Failure_Rate() {
    TorpedoStore store = new TorpedoStore(1, 100);

    boolean result = store.fire(1);

    assertEquals(false, result);
  }
}
