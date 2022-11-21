package com.github.deniskaydunov;

import static org.junit.jupiter.api.Assertions.*;

import com.google.common.eventbus.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventListenerTest {

  private EventBus eventBus;
  private EventListener listener;

  @BeforeEach
  void setUp() {
    eventBus = new EventBus();

    //We can subscribe to an event by registering our EventListener class on the EventBus:
    listener = new EventListener();
    eventBus.register(listener);
  }

  @Test
  public void givenStringEvent_whenEventHandled_thenSuccess() {
    //Given
    //When
    eventBus.post("String Event");
    //Then
    assertEquals(1, EventListener.getEventsHandled());
  }

  @Test
  public void givenCustomEvent_whenEventHandled_thenSuccess() {
    //Given
    CustomEvent customEvent = new CustomEvent("Custom Event");
    //When
    eventBus.post(customEvent);
    //Then
    assertEquals(1, EventListener.getEventsHandled());
  }
}