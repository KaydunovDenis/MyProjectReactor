package org.example.observable;


import java.util.ArrayList;
import java.util.List;

public class NewsAgency {
  private String news;
  private List<NewsChannel> channels = new ArrayList<>();

  public void addObserver(NewsChannel channel) {
    this.channels.add(channel);
  }

  public void removeObserver(NewsChannel channel) {
    this.channels.remove(channel);
  }

  public void setNews(String news) {
    this.news = news;
    for (NewsChannel channel : this.channels) {
      channel.update(this.news);
    }
  }
}
