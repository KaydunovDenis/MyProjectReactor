package org.example.observable;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NewsAgencyTest {

  @Test
  void addObserver() {
  }

  @Test
  void removeObserver() {
  }

  @Test
  void setNews() {
    NewsAgency observable = new NewsAgency();
    NewsChannel observer = new NewsChannel();

    observable.addObserver(observer);
    observable.setNews("news");
    assertEquals(observer.getNews(), "news");
  }
}