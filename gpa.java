package computeGPA;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Jeff Offutt
 */
public class gpa
{
   // These lists hold the grades that are entered from the form.
   // For each class, a course name, the number of hours, and the grades
   // are entered. These values are sent here (through (setCourse())
   // and stored in these three arrays.
   // They should all three be in synch, that is, the nth element of
   // each should refer to the same class.
   private List<String>  courseNames  = new ArrayList<String>();
   private List<Integer> courseHours  = new ArrayList<Integer>();
   private List<String>  courseGrades = new ArrayList<String>();
   private int current = 0; // Lets the JSP iterate over the arraylists

   public gpa()
   {
   }

   public void gpaReset()
   {
      courseNames  = new ArrayList<String>();
      courseHours  = new ArrayList<Integer>();
      courseGrades = new ArrayList<String>();
      current = 0;
   }

   /**
    * @param courseName
    * @param courseCredit
    * @param courseGrade
    * Puts the name and credit into an arraylist,
    * computes the numeric grade and saves it into an arraylist
    */
   public void setCourse (String courseName, String courseCredit, String courseGrade)
   {
      int hours;
      courseNames.add (courseName);
      try
      {  // If the courseCredit is not a number, save the default, 3
         hours = Integer.parseInt (courseCredit);
         courseHours.add ((Integer)hours);
      }
      catch (Exception e)
      {
         courseHours.add (3);
      }
      courseGrades.add (courseGrade);
   }

   /**
    * Returns the current course name
    */
   public String getCourseName ()
   {
      return (courseNames.get (current));
   }

   /**
    * Returns the current course hours
    */
   public int getCourseHours ()
   {
      return (courseHours.get (current));
   }

   /**
    * Returns the current course grade
    */
   public String getCourseGrade ()
   {
      return (courseGrades.get (current));
   }

   /**
    * Goes to the next course
    */
   public void setNextCourse ()
   {
      current++;
   }

   /**
    * Do we have more courses?
    */
   public boolean moreCourses()
   {
      return (current < courseNames.size());
   }

   /**
    * returns the current GPA
    */
   public Float getGpa ()
   {
      Float qualityPoints = 0f;
      Float gradeValue;
      int totalHours = 0;
      int hours;
      Iterator<Integer> itrHours = courseHours.iterator();
      Iterator<String> itrGrade = courseGrades.iterator();

      while (itrHours.hasNext() & itrGrade.hasNext())
      {
         hours = itrHours.next();
         gradeValue = getGradeValue (itrGrade.next()) * hours;
         if (gradeValue > 0)
            qualityPoints = qualityPoints + gradeValue;
         else // An F grade (or something else) doesn't count)
         {
            hours = 0;
         }
         totalHours = totalHours + hours;
      }
      if (totalHours >0)
         return (qualityPoints / (new Float (totalHours)));
      else
         return (0f);
   }

   /**
    * returns the total number of hours
    */
   public int getHours ()
   {
      int totalHours = 0;
      Iterator<Integer> itrHours = courseHours.iterator();

      while (itrHours.hasNext())
      {
         totalHours = totalHours + (itrHours.next()).intValue();
      }
      return (totalHours);
   }

   /**
    * returns the total number of hours
    */
   public String getSummaryMessage ()
   {
      Iterator<Integer> itrHours = courseHours.iterator();
      Iterator<String>  itrGrade = courseGrades.iterator();
      Float qualityPoints = 0f;
      int totalHours = 0;
      Float gpa = 0f;
      int hours;
      Float gradeValue;
      Float gradeValueXHours;
      int numFs = 0; // Don't count F grades
      int numCs = 0; // Only count 2 C grades
      StringBuffer msg = new StringBuffer (1024);

      while (itrHours.hasNext() & itrGrade.hasNext())
      {
         hours = itrHours.next();
         gradeValue = getGradeValue (itrGrade.next());
         gradeValueXHours = gradeValue * new Float ((float) hours);
         if (gradeValue == 2.0f) // A grade of C
         {
	    numCs++;
	    if (numCs <= 2)
               qualityPoints = qualityPoints + gradeValueXHours;
            else
	       hours = 0; // More than 2 Cs, don't add to the number of hours
         }
         else if (gradeValueXHours > 0)
            qualityPoints = qualityPoints + gradeValueXHours;
         else // An F grade (or something else) doesn't count)
         {
	    hours = 0; // Don't add to the number of hours
            numFs++;
         }
         totalHours = totalHours + hours;
         gpa = qualityPoints / (new Float (totalHours));
      }

      if (numFs > 0)
      {
         msg.append ("Fs cannot be used to graduate, so are not included in the total number of hours accumulated.\n");
         msg.append ("Fs are also not included in your GPA calculation.<br/><br/>\n");
      }

      if (numCs > 2)
      {
         msg.append ("Only 2 grades of C are allowed for graduation and " + numCs + " were submitted, so the additional Cs are not included in the total number of hours accumulated.<br/><br/>\n");
      }

      if (totalHours >= 30)
      {
          msg.append ("You have " + totalHours + " hours, which is enough to graduate with an MS degree.<br/>\n");
          if (gpa < 3.0f)
             msg.append ("However, you do not have the minimum GPA of 3.0.<br/>\n");
      }
      else
      {
         msg.append ("You need " + (30-totalHours) + " more hours to graduate.<br/>\n");
	 Float gpaNeeded = (90f - qualityPoints) / (30 - totalHours);
	 if (gpaNeeded < 2.0f)
	    gpaNeeded = 2.0f; // always at least a C.
         msg.append ("You must average a GPA of " + gpaNeeded + " (at least a " + getLetterGrade (gpaNeeded) + " average) in those classes\n");
         msg.append ("to graduate with a GPA of 3.0 or above.<br/>\n");
         msg.append ("Also, you may only use two grades of C in your MS degree.<br/>\n");
      }
      return (msg.toString());
   }

   /**
    * returns the GPA needed to finish
    */
   public Float getNeededGPA ()
   {
      Float qualityPoints = 0f;
      int totalHours = 0;
      int hours;
      Iterator<Integer> itrHours = courseHours.iterator();
      Iterator<String> itrGrade = courseGrades.iterator();

      while (itrHours.hasNext() & itrGrade.hasNext())
      {
         hours = itrHours.next();
         qualityPoints = qualityPoints + (getGradeValue (itrGrade.next()) * hours);
         totalHours = totalHours + hours;
      }
      return ((90f - qualityPoints) / (30 - totalHours));
   }

   /**
    * Returns the letter grade that matches the value
    * An inexact match; actually the lower bound letter grade that matches.
    */
   public String getLetterGrade (Float value)
   {
      if (value > (3.67f)) // the value for an A-
         return ("A"); // lost the +?
      else if (value > (3.33f)) // The value for a B+
         return ("A-");
      else if (value > (3.0f)) // The value for a B
         return ("B+");
      else if (value > (2.67f)) // The value for a B-
         return ("B");
      else if (value > (2.0f)) // The value for a C
         return ("B-");
      else if (value > (0f)) // The value for an F
         return ("C");
      else if (value <= (0f))
         return ("F");
      else // Anything else is an invalid grade
         return ("invalid");
   }

   /**
    * @param courseGrade
    * Returns the numeric value for a letter grade
    */
   public Float getGradeValue (String courseGrade)
   {
      Float grade = 0f;

      if (courseGrade.equals ("A+") || courseGrade.equals ("A"))
      {
         grade = 4f;
      } else if (courseGrade.equals ("A-"))
      {
         grade = 3.67f;
      } else if (courseGrade.equals ("B+"))
      {
         grade = 3.33f;
      } else if (courseGrade.equals ("B"))
      {
         grade = 3f;
      } else if (courseGrade.equals ("B-"))
      {
         grade = 2.67f;
      } else if (courseGrade.equals ("C"))
      {
         grade = 2f;
      }
      else // anything else is equivalent to F
         grade = 0f;
      return (grade);
   }

   /**
    * For testing
    * *
    */
   public static void main (String [] args)
   {
      gpa mygpa = new gpa();
      mygpa.setCourse ("one", "3", "A");
      mygpa.setCourse ("two", "3", "B");
      mygpa.setCourse ("three", "3", "C");
      mygpa.setCourse ("four", "3", "B");
      System.out.println ("gpa: " + mygpa.getGpa());
      System.out.println ("==================");

      mygpa.gpaReset();
      mygpa.setCourse ("one", "3", "A");
      mygpa.setCourse ("two", "3", "A+");
      mygpa.setCourse ("three", "3", "A-");
      mygpa.setCourse ("four", "3", "B");
      mygpa.setCourse ("five", "3", "B+");
      mygpa.setCourse ("six", "3", "B-");
      mygpa.setCourse ("seven", "3", "C");
      mygpa.setCourse ("eight", "3", "F");

      while (mygpa.moreCourses())
      {
         System.out.println ("cn: " + mygpa.getCourseName());
         System.out.println ("ch: " + mygpa.getCourseHours());
         System.out.println ("cg: " + mygpa.getCourseGrade());
         mygpa.setNextCourse();
      }

      mygpa.gpaReset();
      mygpa.setCourse ("ee", "3", "A");
      mygpa.setCourse ("bb", "3", "A");
      mygpa.setCourse ("dd", "3", "A");

      while (mygpa.moreCourses())
      {
         System.out.println ("cn: " + mygpa.getCourseName());
         System.out.println ("ch: " + mygpa.getCourseHours());
         System.out.println ("cg: " + mygpa.getCourseGrade());
         mygpa.setNextCourse();
      }

   }  // end main()

}  // end class gpa
