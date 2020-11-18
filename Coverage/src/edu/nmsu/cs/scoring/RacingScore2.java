package edu.nmsu.cs.scoring;

/***
  * Olympic Dragon Racing Scoring Class
  *
  * For the Summer Olympics dragon racing event there are three judges, each of which gives a score
  * from 0 to 50 (inclusive), but the lowest score is thrown out and the competitor's overall score
  * is just the sum of the two highest scores. This class supports the recording of the three judge's
  * scores, and the computing of the competitor's overall score.
  * 
  * @author Jon Cook, Ph.D.
  * 
  * Improvements and modifications made to source program.
  * @author Nasley Chumacero-Martin
  ***/

public class RacingScore2    {
   
   int score1;
   int score2;
   int score3;
   
   public RacingScore2()    {
      score1 = 0;
      score2 = 0;
      score3 = 0;
   }
   
   public void recordScores(int s1, int s2, int s3)    {
      score1 = s1;
      score2 = s2;
      score3 = s3;
   }
   
   public int overallScore()    {
      //The variables s, s1, and s2 in the original program are confusing.
      //Each s1 and s2 was changed to add1 and add2 respectively and initialized.
      //The variable s was changed to sum and initialized here.
      int add1 = 0, add2 = 0, sum = 0;
      
      //This condition was added to check instaces where all 3 scores are equal.
      if (score1 == score2 && score1 == score3)  {
         add1 = score1;
         add2 = score2;
      }
     //Changed to an else-if to match the scheme because I added another
      //initial condition.
      else if (score1 < score2 && score1 < score3)     {
         add1 = score2;
         add2 = score3;
      }
      else if (score2 < score1 && score2 < score3)    {
         add1 = score1;
         add2 = score3;  //Error in the original program. Changed to score3
      }
      else if (score3 < score1 && score3 < score2)    {
         add1 = score1;
         add2 = score2;
      }
      //The else condition in the original program was odd. This condition was
      //changed to cover other valid inputs such as when 2 variables are equal
      //but the third is different.
      else   {
         //This nested if-else covers cases where only 2 variables are equal.
         if (score3 > score2)  {
            add1 = score2;
            add2 = score3;
         }
         else    {
            add1 = score1;
            add2 = score2;
         }
      }
      //Changed s to sum to make variables less confusing.
      sum = add1 + add2;
      return sum;
   }
   
   public static void main(String args[])   {
      
      int s1, s2, s3;
      
      //Changed the condition to check the length of the arguments instead.
      if (args.length == 0 || args.length != 3)   {
         System.err.println("Error: must supply three arguments!");
         return;
      }
      
      try    {
         s1 = Integer.parseInt(args[0]);
         s2 = Integer.parseInt(args[1]);
         s3 = Integer.parseInt(args[2]);
      }
      catch (Exception e)    {
         System.err.println("Error: arguments must be integers!");
         return;
      }
      if (s1 < 0 || s1 > 50 || s2 < 0 || s2 > 50 || s3 < 0 || s3 > 50)    {
         System.err.println("Error: scores must be between 0 and 50!");
         return;
      }
      
      RacingScore2 score = new RacingScore2();
      score.recordScores(s1, s2, s3);
      System.out.println("Overall score: " + score.overallScore());
      return;
   }
   
} // end class
