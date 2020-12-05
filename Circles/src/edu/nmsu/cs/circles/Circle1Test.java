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
   
   //
   // Stuff you want to do before each test case
   //
   @Before
   public void setup()   {
      System.out.println("\nTest starting...");
      //Changed the starting y-position for testing consistency. NOT an error.
      circle1 = new Circle1(1, 1, 3);
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
   // Test for movement only along y-axis and notx.
   //
   @Test
   public void moveOnlyY()   {
      Point p;
      System.out.println("Running test moveOnlyY.");
      p = circle1.moveBy(0,5);
      Assert.assertTrue(p.x == 1 && p.y == 6);
   }
   
   /***
     * NOT USED but could be used as main method.
     * public static void main(String args[]) { try { org.junit.runner.JUnitCore.runClasses(
     * java.lang.Class.forName("Circle1Test")); } catch (Exception e) { System.out.println("Exception:
     * " + e); } }
     ***/
   
}
