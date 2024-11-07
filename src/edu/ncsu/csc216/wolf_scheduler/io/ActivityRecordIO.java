/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;

/**
 * Writes activities to the file
 * @author Priyanshu Dongre
 */
public class ActivityRecordIO {

	/**
	 * Writes a list of Activity objects to a file.
	 * Each activity is converted to a string using its toString method and written as a separate line in the file.
	 * 
	 * @param fileName the name of the file to write the activity records to
	 * @param activities the list of Activity objects to write to the file
	 * @throws IOException if an I/O error occurs while writing to the file
	 */
	public static void writeActivityRecords(String fileName, ArrayList<Activity> activities) throws IOException {
	    
		PrintStream fileWriter = new PrintStream(new File(fileName));
	
		for (Activity activity: activities) {
		    fileWriter.println(activity.toString());
		}
	
		fileWriter.close();
	    
	}

}
