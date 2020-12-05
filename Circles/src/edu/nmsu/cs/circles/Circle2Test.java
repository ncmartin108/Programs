package edu.nmsu.cs.circles;

/***
  * JUnit Class to Test Circle2
  * @author N. Chumacero-Martin
  * Modified from Circle1Test.java
  ***/

import org.junit.*;
import static org.junit.Assert.assertTrue; //This statment needs to be imported statically

public class Circle2Test    {
   
// Data you need for each test case
   private Circle2 circle2;
   private Circle2 circle1;
   
   //
   // Stuff you want to do before each test case
   //
   @Before
   public void setup()    {
      System.out.println("\nTest starting...");
      circle2 = new Circle2(1, 1, 3);
      //Added another circle to test intersections.
      circle1 = new Circle2(10,10,2);
   }
   
   //
   // Stuff you want to do after each test case
   //
   @After
   public void teardown()    {
      System.out.println("\nTest finished.");
   }
   
   //
   // Test to change the circle size by a factor of 20%
   //
   @Test
   public void changeScaleFactor()    {
      System.out.println("Running test changeScaleFactor.");
      circle2.scale(0.2);
      Assert.assertTrue(circle2.radius == 3.6);
   }
   
   //
   // Test to double the size of the circle.
   //
   @Test
   public void changeScaleSize()    {
      System.out.println("Running test changeScaleSize.");
      circle2.scale(2);
      Assert.assertTrue(circle2.radius == 6);
   }
   
   //
   // Test a simple positive move
   //
   @Test
   public void simpleMove()    {
      Point p;
      System.out.println("Running test simpleMove.");
      p = circle2.moveBy(1, 1);
      Assert.assertTrue(p.x == 2 && p.y == 2);
   }
   
   //
   // Test a simple negative move
   //
   @Test
   public void simpleMoveNeg()    {
      Point p;
      System.out.println("Running test simpleMoveNeg.");
      p = circle2.moveBy(-1, -1);
      Assert.assertTrue(p.x == 0 && p.y == 0);
   }
   
   //
   // Test for movement only along x-axis and not y.
   //
   @Test
   public void moveOnlyX()   {
      Point p;
      System.out.println("Running test moveOnlyX.");
      p = circle2.moveBy(5,0);
      Assert.assertTrue(p.x == 6 && p.y == 1);
   }
   
   //
   // Test for movement only along y-axis and not x.
   //
   @Test
   public void moveOnlyY()   {
      Point p;
      System.out.println("Running test moveOnlyY.");
      p = circle2.moveBy(0,5);
      Assert.assertTrue(p.x == 1 && p.y == 6);
   }
   
   //
   // Test for negative movement only along y-axis and not x.
   //
   @Test
   public void moveOnlyYNeg()   {
      Point p;
      System.out.println("Running test moveOnlyXNeg.");
      p = circle2.moveBy(0,-2);
      Assert.assertTrue(p.x == 1 && p.y == -1);
   }
   
   //
   // Tests to verify that circles don't intersect.
   //
   @Test
   public void notIntersect()   {
      boolean inter = circle2.intersects(circle1);
      System.out.println("Running test notIntersect.");
      Assert.assertFalse(inter);
   }
   
   //
   // Tests to verify that circles do intersect.
   //
   @Test
   public void doesIntersect()   {
      System.out.println("Running test doesIntersect.");
      circle1.moveBy(-9, -9);
      boolean inter = circle2.intersects(circle1);
      Assert.assertTrue(inter);
   }
   
   //
   // Tests to verify that circles do intersect at one point.
   //
   @Test
   public void doesIntersectOnePoint()   {
      System.out.println("Running test doesIntersectOnePoint.");
      Circle2 circletouches = new Circle2(6, 1, 2);
      boolean inter = circle2.intersects(circletouches);
      Assert.assertTrue(inter);
   }
   
    //
   // Test to verify intersection when the circles are identical.
   //
   @Test
   public void sameCircleIntersection()   {
      System.out.println("Running test sameCircleIntersection.");
      //The circle I created above won't work here, unless I resize it properly.
      Circle2 sameCircle = new Circle2(1, 1, 3);
      boolean inter = circle2.intersects(sameCircle);
      Assert.assertTrue(inter);
   }

}