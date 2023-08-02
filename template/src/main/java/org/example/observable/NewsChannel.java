package org.example.observable;

import lombok.Data;

@Data
public class NewsChannel implements Channel {
  private String news;

  @Override
  public void update(Object news) {
    this.setNews((String) news);
  }

}
