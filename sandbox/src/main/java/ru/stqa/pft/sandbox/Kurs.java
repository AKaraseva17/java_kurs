package ru.stqa.pft.sandbox;

public class Kurs {

  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Anastasia");

    Square s = new Square(5);
    System.out.println("площадь квадрата со стороной " + s.l + "=" + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("площадь прямоугольника со сторонами " + r.a + "и" + r.b+ r.area());

    Point p1 = new Point(90,67);
    Point p2 = new Point(78,43);
    System.out.println("Расстояние между двумя точками p1 и p2 " + "= " +  p1.distance(p2));
  }
    public static void hello(String somebody) {
      System.out.println("Hello," + somebody + "!");
    }


}


