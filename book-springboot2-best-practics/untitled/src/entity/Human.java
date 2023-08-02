package entity;

public class Human {
  private int age;
  private String name;

  private int height;

  private int width;

  public Human(int age, String name, int height, int width) {
    this.age = age;
    this.name = name;
    this.height = height;
    this.width = width;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getHeight() {
    return height/100;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  @Override
  public String toString() {
    return "Human{" +
        "age=" + age +
        ", name='" + name + '\'' +
        ", height=" + height +
        ", width=" + width +
        '}';
  }
}
