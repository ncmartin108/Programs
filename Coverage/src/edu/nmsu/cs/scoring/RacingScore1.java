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
  * Improvements and modifications made to the source code.
  * @author Nasley Chumacero-Martin
  * 
  ***/

public class RacingScore1    {
   
   int score1;
   int score2;
   int score3;
   
   public RacingScore1()    {
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
      int s;
      if (score1 < score2)    {
         //Error in the source program; it was saving score2 in s.
         s = score1; //Changed to save the lowest score.
      }
      else    {
         //Error in the source program; it should store score2.
         s = score2;  //Changed to store score2.
      }
      //Added a case if the scores are equal
      if (s > score3 || s == score3)    {
         s = score3;
      }
      //The lowest score s, gets discarded here.
      s = (score1 + score2 + score3) - s;
      return s;
   }
   
   //This program, unlike RacingScore2 does not validate the input, so a user could
   //enter negative scores or scores greater than 50 and the program would still
   //accept them.  This is a known functional error for invalid input.
   
   public static void main(String args[])
   {
      int s1, s2, s3;
      if (args.length != 3)
      {
         System.err.println("Error: must supply three arguments!");
         return;
      }
      try
      {
         s1 = Integer.parseInt(args[0]);
         s2 = Integer.parseInt(args[1]);
         s3 = Integer.parseInt(args[2]);
      }
      catch (Exception e)
      {
         System.err.println("Error: arguments must be integers!");
         return;
      }
      RacingScore1 score = new RacingScore1();
      score.recordScores(s1, s2, s3);
      System.out.println("Overall score: " + score.overallScore());
      return;
   }
   
} // end class
