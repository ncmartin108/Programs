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
   
   //
   // Stuff you want to do before each test case
   //
   @Before
   public void setup()    {
      System.out.println("\nTest starting...");
      circle2 = new Circle2(1, 1, 3);
   }
   
   //
   // Stuff you want to do after each test case
   //
   @After
   public void teardown()    {
      System.out.println("\nTest finished.");
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
   
}
