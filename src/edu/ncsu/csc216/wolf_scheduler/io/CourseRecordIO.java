package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.util.Scanner;


import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 * 
 * @author Sarah Heckman
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
	    Scanner fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
	    ArrayList<Course> courses = new ArrayList<Course>(); //Create an empty array of Course objects
	    while (fileReader.hasNextLine()) { //While we have more lines in the file
	        try { //Attempt to do the following
	            //Read the line, process it in readCourse, and get the object
	            //If trying to construct a Course in readCourse() results in an exception, flow of control will transfer to the catch block, below
	            Course course = readCourse(fileReader.nextLine()); 

	            //Create a flag to see if the newly created Course is a duplicate of something already in the list  
	            boolean duplicate = false;
	            //Look at all the courses in our list
	            for (int i = 0; i < courses.size(); i++) {
	                //Get the course at index i
	                Course current = courses.get(i);
	                //Check if the name and section are the same
	                if (course.getName().equals(current.getName()) &&
	                        course.getSection().equals(current.getSection())) {
	                    //It's a duplicate!
	                    duplicate = true;
	                    break; //We can break out of the loop, no need to continue searching
	                }
	            }
	            //If the course is NOT a duplicate
	            if (!duplicate) {
	                courses.add(course); //Add to the ArrayList!
	            } //Otherwise ignore
	        } catch (IllegalArgumentException e) {
	            //The line is invalid b/c we couldn't create a course, skip it!
	        }
	    }
	    //Close the Scanner b/c we're responsible with our file handles
	    fileReader.close();
	    //Return the ArrayList with all the courses we read!
	    return courses;
	}

	
	/**
	 * Reads a line of course data and returns a Course object.
	 * The line is processed as a comma separated string that includes details about the course
	 * such as its name, title, section, credits, instructor ID, meeting days, and times.
	 * 
	 * The method handles both courses with arranged meeting times (denoted by 'A') and regular courses with specific start and end times.
	 * 
	 * @param line the comma separated string representing a course's data
	 * @return a Course object constructed from the data in the provided line
	 * @throws IllegalArgumentException if the line contains invalid data or if an arranged course includes times
	 */
    private static Course readCourse(String line) {
    	Scanner lineProcessor = new Scanner(line).useDelimiter(",");
    	Course course = null;
    	try {
    		String courseName = "";
    		String courseTitle = "";
    		String section = "";
    		int credits = 0;
    		String instructorId = "";
    		String meetingDays = "";
    		int startTime = 0;
    		int endTime = 0;
    		
    		while(lineProcessor.hasNext()) {
    			courseName = lineProcessor.next();
    			courseTitle = lineProcessor.next();
    			section = lineProcessor.next();
    			credits = Integer.parseInt(lineProcessor.next());
    			instructorId = lineProcessor.next();
    			meetingDays = lineProcessor.next();
    			
    			if(!"A".equals(meetingDays)) {
    				startTime = Integer.parseInt(lineProcessor.next());
    				endTime = Integer.parseInt(lineProcessor.next());
    			} else if("A".equals(meetingDays) && lineProcessor.hasNext()) {
    				lineProcessor.close();
    				throw new IllegalArgumentException();
    			}
    			course = new Course(courseName, courseTitle, section, credits, instructorId, meetingDays, startTime, endTime);
    		}
    	} catch (Exception e) {
    		throw new IllegalArgumentException();
    	}
    	lineProcessor.close();
		return course;
    }

}