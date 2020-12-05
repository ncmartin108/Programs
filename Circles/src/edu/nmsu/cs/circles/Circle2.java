package edu.nmsu.cs.circles;

public class Circle2 extends Circle  {

 public Circle2(double x, double y, double radius)    {
  //Error in the source code. The x and y were transposed.
    super(x, y, radius);
 }
 
 public boolean intersects(Circle other)    {
    double d;
    d = Math.sqrt(Math.pow(center.x - other.center.x, 2) +
                  Math.pow(center.y - other.center.y, 2));
    if (d < radius)    {
       return true;
    }
    // This method was missing some functionality to test for cases 
    // such as when the circles only touch at one point.
    // Formula for checking the tangency of two circles:
    // (x1 - x2)2 + (y1 - y2)2 = (r1 +- r2)2 (+ is outer tangency)
      else if (Math.pow((center.x - other.center.x) , 2) + Math.pow((center.y - other.center.y), 2) 
                  == Math.pow(radius + other.radius, 2) )   {
         return true;
      }
    else    {
       return false;
    }
 }

}