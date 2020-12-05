package edu.nmsu.cs.circles;

/***
  * Example JUnit testing class for Circle1 (and Circle)
  *
  * - must have your classpath set to include the JUnit jarfiles - to run the test do: java
  * org.junit.runner.JUnitCore Circle1Test - note that the commented out main is another way to run
  * tests - note that normally you would not have print statements in a JUnit testing class; they are
  * here just so you see what is happening. You should not have them in your test cases.
  ***/

import org.junit.*;

public class Circle1Test
{
   // Data you need for each test case
   private Circle1 circle1;
   private Circle1 circle2;
   
   //
   // Stuff you want to do before each test case
   //
   @Before
   public void setup()   {
      System.out.println("\nTest starting...");
      //Changed the starting y-position for testing consistency. NOT an error.
      circle1 = new Circle1(1, 1, 3);
      //Added another circle to test intersections.
      circle2 = new Circle1(10,10,2);
   }
   
   //
   // Stuff you want to do after each test case
   //
   @After
   public void teardown()   {
      System.out.println("\nTest finished.");
   }
   
   //
   // Test to change the circle size by a factor of 20%
   //
   @Test
   public void changeScaleFactor()    {
      System.out.println("Running test changeScaleFactor.");
      circle1.scale(0.2);
      Assert.assertTrue(circle1.radius == 3.6);
   }
   
   //
   // Test to double the size of the circle.
   //
   @Test
   public void changeScaleSize()    {
      System.out.println("Running test changeScaleSize.");
      circle1.scale(2);
      Assert.assertTrue(circle1.radius == 6);
   }
   
   //
   // Test a simple positive move
   //
   @Test
   public void simpleMove()  {
      Point p;
      System.out.println("Running test simpleMove.");
      p = circle1.moveBy(1, 1);
      //Updated the p.y position for test. Not an error.
      Assert.assertTrue(p.x == 2 && p.y == 2);
   }
   
   //
   // Test a simple negative move
   //
   @Test
   public void simpleMoveNeg()  {
      Point p;
      System.out.println("Running test simpleMoveNeg.");
      p = circle1.moveBy(-1, -1);
      Assert.assertTrue(p.x == 0 && p.y == 0);
   }
   
   //
   // Test for movement only along x-axis and not y.
   //
   @Test
   public void moveOnlyX()   {
      Point p;
      System.out.println("Running test moveOnlyX.");
      p = circle1.moveBy(5,0);
      Assert.assertTrue(p.x == 6 && p.y == 1);
   }
   
   //
   // Test for movement only along y-axis and not x.
   //
   @Test
   public void moveOnlyY()   {
      Point p;
      System.out.println("Running test moveOnlyY.");
      p = circle1.moveBy(0,5);
      Assert.assertTrue(p.x == 1 && p.y == 6);
   }
   
   //
   // Test for negative movement only along x-axis and not y.
   //
   @Test
   public void moveOnlyXNeg()   {
      Point p;
      System.out.println("Running test moveOnlyXNeg.");
      p = circle1.moveBy(-2,0);
      Assert.assertTrue(p.x == -1 && p.y == 1);
   }
   
   //
   // Tests to verify that circles don't intersect.
   //
   @Test
   public void notIntersect()   {
      boolean inter = circle1.intersects(circle2);
      System.out.println("Running test notIntersect.");
      Assert.assertFalse(inter);
   }
   
   //
   // Tests to verify that circles do intersect.
   //
   @Test
   public void doesIntersect()   {
      System.out.println("Running test doesIntersect.");
      circle2.moveBy(-9, -9);
      boolean inter = circle1.intersects(circle2);
      Assert.assertTrue(inter);
   }
   
   //
   // Tests to verify that circles do intersect at one point.
   //
   @Test
   public void doesIntersectOnePoint()   {
      System.out.println("Running test doesIntersectOnePoint.");
      Circle1 circletouches = new Circle1(6, 1, 2);
      boolean inter = circle1.intersects(circletouches);
      Assert.assertTrue(inter);
   }
   
   //
   // Test to verify intersection when the circles are identical.
   //
   @Test
   public void sameCircleIntersection()   {
      System.out.println("Running test sameCircleIntersection.");
      //The circle I created above won't work here, unless I resize it properly.
      Circle1 sameCircle = new Circle1(1, 1, 3);
      boolean inter = circle1.intersects(sameCircle);
      Assert.assertTrue(inter);
   }

   /***
     * NOT USED but could be used as main method.
     * public static void main(String args[]) { try { org.junit.runner.JUnitCore.runClasses(
     * java.lang.Class.forName("Circle1Test")); } catch (Exception e) { System.out.println("Exception:
     * " + e); } }
     ***/
   
}