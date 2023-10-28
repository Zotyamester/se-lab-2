package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    assertEquals(false, store.isEmpty());
    assertEquals(1, store.getTorpedoCount());
  }

  @Test
  void fire_Several() {
    TorpedoStore store = new TorpedoStore(10, 0);

    boolean result1 = store.fire(10);

    assertEquals(true, result1);
    assertEquals(true, store.isEmpty());
    assertEquals(0, store.getTorpedoCount());
  }

  @Test
  void fire_Zero() {
    TorpedoStore store = new TorpedoStore(69, 0);

    assertThrows(IllegalArgumentException.class, () -> store.fire(0));
  }

  @Test
  void fire_From_Empty_Store() {
    TorpedoStore store = new TorpedoStore(0, 0);

    assertThrows(IllegalArgumentException.class, () -> store.fire(1));
  }
}
