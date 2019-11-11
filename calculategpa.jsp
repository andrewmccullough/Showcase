<%@ page language="java" %>
<%@ page import="computeGPA.gpa" %>

<jsp:useBean id="thegpa" class="computeGPA.gpa" scope="page" />

<%
   // Original from Adam J Boltz
   // Greatly modified by Jeff Offutt
   // November 2009, homework 6 in SWE 642
   //
   // This version uses the Java class gpa.java
   //
   // If the parameter courseCount is 0, no other data is being sent,
   // so draw one blank row.
   // A JS function adds additional rows.
   // If courseCount is not 0, then draw the first row,
   // then perform the calculations, summarize the data, and compute GPA.
   //
   // Form parameters:
   //    courseNameX
   //    courseCreditX
   //    courseGradeX
   // One for each row, where 'X' is the row number.

   // How many courses are sent.
   // This is a hidden form field that is updated by JS when
   // a new course is added.
   Integer count = 0;
   String courseCount = request.getParameter ("courseCount");
   if (courseCount != null)
   {  // Convert to integer if not null.
      count = Integer.parseInt (request.getParameter ("courseCount"));
   }

   int defaultHours = 3; // Constant to be easy to change.
%>

<html>
<head>
  <title>Grade Calculator</title>
  <script language="javascript" type="text/javascript">
    // Returns true if the number is even, odd otherwise.
    function isEven (num)
    {
       return !(num % 2);
    }

    // Add a new course to the form, one additional row in the table
    // The form starts with 1 row
    function addCourse()
    {
       var form = document.getElementById ('theForm');
       var courseCount = document.getElementById ('courseCount');

       var firstRow = document.getElementById ('firstRow');
       var newRow = firstRow.cloneNode (true);
       var rowNum = firstRow.parentNode.getElementsByTagName ('tr').length;

       // Alternate background colors
       if (isEven (rowNum))
          newRow.setAttribute ('bgcolor', '#BBFFDD');
       else
          newRow.setAttribute ('bgcolor', '#CCEEDD');
       newRow.setAttribute ('id', '');
       var col1 = newRow.childNodes[1];
       var col2 = newRow.childNodes[3];
       var col3 = newRow.childNodes[5];

       // Course name text box
       col1.childNodes[1].setAttribute ('name', 'courseName' + rowNum);
       col1.childNodes[1].value = '';

       // Course credit text box
       col2.childNodes[1].setAttribute ('name', 'courseCredit' + rowNum);
       col2.childNodes[1].value = '<%= defaultHours %>';

       // Course grade radio buttons
       var radios = col3.getElementsByTagName ('input');
       for (var i=0; i < radios.length; i++)
       {
          radios[i].checked = false;
          radios[i].setAttribute ('name', 'courseGrade' + rowNum);
       }
       radios[1].checked = true;

       firstRow.parentNode.appendChild (newRow);

       courseCount.value = firstRow.parentNode.getElementsByTagName('tr').length - 1;
       return false;
    }

  </script>
</head>

<!-- ----------------------------------- -->

<body>
 <center>
  <b>George Mason University Grade Calculator</b>
  <br/><b>Calculates the current GPA and the minimum GPA needed in the remaining classes</b>
  <br/><b>Designed for MS students</b>
  <br/>Fall 2009
 </center>

 <table>
   <tr valign="top">
     <td>
       <p>
        Base requirements for an MS degree at GMU
        <ul>
        <li>GPA must be at least 3.0
        <li>10 courses
        <li>No more than 2 Cs allowed
        </ul>
       </p>
     </td>
     <td>
       <table border=1 bordercolor="#339933">
         <tr bgcolor="#339933"><td colspan=8 align="center"><b>Grading Chart</b></td></tr>
         <tr><td bgcolor="#BBFFDD">A+<td bgcolor="#CCEEDD">A<td bgcolor="#BBFFDD">A-
             <td bgcolor="#CCEEDD">B+<td bgcolor="#BBFFDD">B<td bgcolor="#CCEEDD">B-
             <td bgcolor="#BBFFDD">C<td bgcolor="#CCEEDD">F</tr>
         <tr><td bgcolor="#BBFFDD">4.0<td bgcolor="#CCEEDD">4.0<td bgcolor="#BBFFDD">3.7
             <td bgcolor="#CCEEDD">3.33<td bgcolor="#BBFFDD">3.0<td bgcolor="#CCEEDD">2.67
             <td bgcolor="#BBFFDD">2.0<td bgcolor="#CCEEDD">0.0</tr>
       </table>
     </td>
   </tr>
  </table>

<!-- ----------------------------------- -->

 <form id="theForm" action="calculategpa.jsp" method="POST">
   <!-- Hidden form field, updated by JS, to keep track of how many grades are being sent -->
 <% if (count == 0) {
    // if count is 0, we will start with one row,
    // otherwise, the  number of rows is whatever was sent to the JSP
 %>
   <input id="courseCount" type="hidden" name="courseCount" value="1"/>
 <% } else { %>
   <input id="courseCount" type="hidden" name="courseCount" value="<%= count %>"/>
 <% } %>

   <table border='1'>
     <tr bgcolor="#339933">
       <th>Course Name</th>
       <th>Credit Hours</th>
       <th>Grade</th>
     </tr>

 <% if (count == 0) {
    // Only print one row with blank values
 %>
     <!-- Print the first row in the table -->
     <tr id="firstRow" bgcolor="#CCEEDD">
       <td>
         <input type="text" name="courseName1" size="25"/>
       </td>
       <td align="center">
         <input type="text" name="courseCredit1" size="2" value="<%= defaultHours %>"/>
       </td>
       <td>
         <input type="radio" name="courseGrade1" value="A+"\>A+
         &nbsp;
         <input type="radio" name="courseGrade1" checked="checked" value="A"\>A
         &nbsp;
         <input type="radio" name="courseGrade1" value="A-"\>A-
         &nbsp;
         <input type="radio" name="courseGrade1" value="B+"\>B+
         &nbsp;
         <input type="radio" name="courseGrade1" value="B"\>B
         &nbsp;
         <input type="radio" name="courseGrade1" value="B-"\>B-
         &nbsp;
         <input type="radio" name="courseGrade1" value="C"\>C
         &nbsp;
         <input type="radio" name="courseGrade1" value="F"\>F
       </td>
     </tr>

 <% }
    else
    { // print rows based on form data that was sent

      // need to stock up the bean.
      for (int i=1; i <= count; i++)
      {
         // Each row is a base name concatenated with an integer
         String name  = request.getParameter ("courseName" + i);
         String hours = request.getParameter ("courseCredit" + i);
         String grade = request.getParameter ("courseGrade" + i);
         thegpa.setCourse (name, hours, grade);
      }

      // Print rows in the table depending on contents of the bean
      // Begin loop using iterating methods in the gpa class
      int iter = 1;
      // if (thegpa.moreCourses())
         // thegpa.setNextCourse(); // Start with the second row.
      while (thegpa.moreCourses())
      {
 %>
 
 <% if (iter==1) { %>
     <tr id="firstRow" bgcolor="#CCEEDD">
 <% } else if ((iter % 2) == 0) { // is Even %>
     <tr bgcolor="#BBFFDD">
 <% } else { %>
     <tr bgcolor="#CCEEDD">
 <% } %>
       <td>
         <input type="text" name="courseName<%= iter %>" size="25" value="<%= thegpa.getCourseName() %> "/>
       </td>
       <td align="center">
         <input type="text" name="courseCredit<%= iter %>" size="2" value="<%= thegpa.getCourseHours() %>"/>
       </td>
 <%   // Which grade should be checked?
      String curGrade = thegpa.getCourseGrade();
 %>
       <td>
 <%   if (curGrade.equals ("A+")) { %>
         <input type="radio" name="courseGrade<%= iter %>" checked="checked" value="A+"\>A+
         &nbsp;
 <%   } else { %>
         <input type="radio" name="courseGrade<%= iter %>" value="A+"\>A+
         &nbsp;
 <%   } if (curGrade.equals ("A")) { %>
         <input type="radio" name="courseGrade<%= iter %>" checked="checked" value="A"\>A
         &nbsp;
 <%   } else { %>
         <input type="radio" name="courseGrade<%= iter %>" value="A"\>A
         &nbsp;
 <%   } if (curGrade.equals ("A-")) { %>
         <input type="radio" name="courseGrade<%= iter %>" checked="checked" value="A-"\>A-
         &nbsp;
 <%   } else { %>
         <input type="radio" name="courseGrade<%= iter %>" value="A-"\>A-
         &nbsp;
 <%   } if (curGrade.equals ("B+")) { %>
         <input type="radio" name="courseGrade<%= iter %>" checked="checked" value="B+"\>B+
         &nbsp;
 <%   } else { %>
         <input type="radio" name="courseGrade<%= iter %>" value="B+"\>B+
         &nbsp;
 <%   } if (curGrade.equals ("B")) { %>
         <input type="radio" name="courseGrade<%= iter %>" checked="checked" value="B"\>B
         &nbsp;
 <%   } else { %>
         <input type="radio" name="courseGrade<%= iter %>" value="B"\>B
         &nbsp;
 <%   } if (curGrade.equals ("B-")) { %>
         <input type="radio" name="courseGrade<%= iter %>" checked="checked" value="B-"\>B-
         &nbsp;
 <%   } else { %>
         <input type="radio" name="courseGrade<%= iter %>" value="B-"\>B-
         &nbsp;
 <%   } if (curGrade.equals ("C")) { %>
         <input type="radio" name="courseGrade<%= iter %>" checked="checked" value="C"\>C
         &nbsp;
 <%   } else { %>
         <input type="radio" name="courseGrade<%= iter %>" value="C"\>C
         &nbsp;
 <%   } if (curGrade.equals ("F")) { %>
         <input type="radio" name="courseGrade<%= iter %>" checked="checked" value="F"\>F
         &nbsp;
 <%   } else { %>
         <input type="radio" name="courseGrade<%= iter %>" value="F"\>F
 <%   } %>
       </td>
     </tr>

 <%   // end loop using iterating methods
         thegpa.setNextCourse();
         iter++;
      }  // end while loop
 %>

 <% } // end if (count==0) %>

   </table>
   <a href="#" onclick="return addCourse();">Add another course</a>
   &nbsp; &nbsp; &nbsp;
   <button id="submitCourses" type="submit" name="submitCourses">Calculate GPA</button>
 </form>

 <%  // If count is 0, then we have no submission.
     // Print the blank form (above) and no response.
     if (count > 0)
     {
 %>
        <br/>
        <b>Your current GPA is: <%= thegpa.getGpa() %></b>
        <br/>
        <br/>
        <%= thegpa.getSummaryMessage() %>
 <%  }   %>

<p style="font-size:70%;font-family:monospace">
<br />Implementation by Jeff Offutt, based on a JSP by Adam Boltz
<br />&copy; 2009, all rights reserved.
<br />Last update: 05-November-2009
</p>
 
</body>
</html>
