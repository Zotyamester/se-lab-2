package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
  
  @Test
  void fire_Many() {
    final int TEST_COUNT = 1000;
    final double IDEAL_FAILURE_RATE = 0.5;
    final double DELTA = 0.05;

    TorpedoStore store = new TorpedoStore(TEST_COUNT, IDEAL_FAILURE_RATE);

    // Testing with Monte Carlo Method.
    
    int failureCount = 0;

    for (int i = 0; i < TEST_COUNT; i++) {
      if (!store.fire(1)) {
        failureCount++;
      }
    }

    double failureRate = (double)failureCount / TEST_COUNT;

    assertTrue(failureRate >= IDEAL_FAILURE_RATE - DELTA && failureRate <= IDEAL_FAILURE_RATE + DELTA);
  }
}
