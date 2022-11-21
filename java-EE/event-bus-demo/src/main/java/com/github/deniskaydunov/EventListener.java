package com.github.deniskaydunov;

import com.google.common.eventbus.Subscribe;

public class EventListener {

  private static int eventsHandled;

  @Subscribe
  public void stringEvent(String event) {
    eventsHandled++;
  }

  public static int getEventsHandled() {
    return eventsHandled;
  }

  @Subscribe
  public void someCustomEvent(CustomEvent customEvent) {
    eventsHandled++;
  }
}