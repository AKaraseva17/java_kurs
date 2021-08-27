package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testshouldCreatePoint() {
    Point p1 = new Point(345, 56);
    Point p2 = new Point(78, 456);
    Assert.assertNotNull((p1.distance(p2)));
  }
  @Test
  public void testreturntheСorrectvaluePoint() {
    Point p1 = new Point(87, 98);
    Point p2 = new Point(89, 87);
    Assert.assertEquals((p1.distance(p2)),11.180339887498949);
  }
}
