/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * WolfScheduler creates a schedule for the current user (a student) and 
 * provides functionality for naming the schedule, 
 * adding a Course to the schedule, removing a Course from the schedule and
 * resetting the schedule
 * @author Priyanshu Dongre
 */
public class WolfScheduler {
	
	/**
	 * Default Title of the schedule is My Schedule
	 */
	private String title = "My Schedule";
	
	/**
	 * An ArrayList that stores All the Courses.
	 */
	ArrayList<Course> courses;
	
	/**
	 * An ArrayList that stores the schedule of a student.
	 */
	ArrayList<Activity> schedule;

	/**
	 * Constructor for WolfScheduler that constructs this class
	 * with required data structures.
	 * @param fileName the name of the file
	 */
	public WolfScheduler(String fileName) {
		
		this.courses  = new ArrayList<>();
		this.schedule = new ArrayList<>();
		
		try {
			this.courses = CourseRecordIO.readCourseRecords(fileName);
			
		} catch(Exception e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}

	/**
	 * 
	 * This method returns an array which is used in the GUI to create the table of 
	 * course catalog information. There is a row 
	 * for each Course and three columns for name, section, 
	 * and title. If there are no Courses in the catalog, 
	 * an empty 2D String array is returned.
	 * @return catalog a 2D String array of the catalog.
	 */
	public String[][] getCourseCatalog() {
		String [][] catalogArray = new String[courses.size()][4];
		for (int i = 0; i < courses.size(); i++) {
			Course c = courses.get(i);
			catalogArray[i] = c.getShortDisplayArray();
		}
		return catalogArray;
	}

	
	/**
	 * This method returns an array which is used in the GUI to create the 
	 * table of course catalog information. 
	 * There is a row for each Course and three columns 
	 * for name, section, and title. 
	 * If there are no Courses in the schedule, 
	 * an empty 2D String array is returned.
	 * @return a 2D String array of the schedule.
	 */
	public String[][] getScheduledActivities() {
		
		String[][] catalog = new String[schedule.size()][4];
		for(int i = 0; i < schedule.size(); i++) {
			Activity a = schedule.get(i);
			catalog[i] = a.getShortDisplayArray();
		}
		return catalog;
	}

	/**
	 * addCourseToSchedule() returns true 
	 * if the given Course (represented by the name and section) meets the following criteria: 
	 * 1) the course exists in the catalog and 2) the course is successfully added to the student’s schedule.
	 * If the Course is not in the catalog, it cannot be added to the schedule and the method returns false.
	 * A Course with the same name as another Course already in the schedule cannot be added to the schedule. 
	 * This means a student can’t be enrolled in both Section 001 and 002 of CSC216 at the same time. 
	 * If a Course with the same name is already in the schedule, 
	 * an IllegalArgumentException with the error message of 
	 * “You are already enrolled in {course name} is thrown.
	 * @param name -> Name of the course
	 * @param section -> Section of the course
	 * @return true if course is added, else false;
	 */
	public boolean addCourseToSchedule(String name, String section) {
		for(Course course: courses) {
			if(name.equals(course.getName()) && section.equals(course.getSection())) {
				if(!schedule.isEmpty()) {
					for(Activity a: schedule) {
						
						if(!a.isDuplicate(course)) {
							try {
								a.checkConflict(course);
								schedule.add(course);
								return true;
								
							} catch(ConflictException c) {
								throw new IllegalArgumentException("The course cannot be added due to a conflict.");
								
							}
						} else {
							throw new IllegalArgumentException("You are already enrolled in " + course.getName());
						}
					}
				} else {
					schedule.add(course);
					return true;
				}
			}
		}
		return false;
		
	}
	
	/**
	 * Method that adds the event to the Schedule.
	 * @param eventTitle        Title of the event
	 * @param eventMeetingDays  The day the event is happening
	 * @param eventStartTime    Time the event starts
	 * @param eventEndTime      Time the event ends
	 * @param eventDetails      Details of the event
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime, String eventDetails) {
		Event event = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);
		
		if(!schedule.isEmpty() ) {
			for(Activity a: schedule) {
				if(event.isDuplicate(a)) {
					throw new IllegalArgumentException("You have already created an event called " + a.getTitle());
				} else {
					try {
						a.checkConflict(event);
					}
					catch(ConflictException c) {
						throw new IllegalArgumentException("The event cannot be added due to a conflict.");
						
					}
				}
			}
			schedule.add(event);
			
		} else {
			schedule.add(event);
		}
		
	}

	/** 
	 * This array is used in the GUI to create the table of course catalog information. 
	 * There is a row for each Course and six columns for name, section, title, 
	 * credits, instructorId, and the meeting days string 
	 * (e.g., getMeetingString(). 
	 * If there are no Courses in the schedule, an empty 2D String array is returned.
	 * @return  a 2D String array of the schedule with all information.
	 */
	public String[][] getFullScheduledActivities() {
		String[][] catalog = new String[schedule.size()][7];
		for(int i = 0; i < schedule.size(); i++) {
			Activity a = schedule.get(i);
			catalog[i] = a.getLongDisplayArray();
		}
		return catalog;
	}

	/**
	 * This method removes courses from the schedule.
	 * @param idx index of the activity in the schedule.
	 * @return true if the given Course (represented by the name and section) can be removed from the student’s schedule. 
	 * The Course should be removed.
	 */
	public boolean removeActivityFromSchedule(int idx) {
		
		try {
			schedule.remove(idx);
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
		return true;
	}
	/**
	 * This method resets the schedule.
	 */
	public void resetSchedule() {
		
		this.schedule.clear();
	}

	/**
	 * exportSchedule() receives a String parameter that is the filename where the student’s schedule will be saved to.
	 * You will use the CourseRecordIO.writeCourseRecords() to export the file. 
	 * If CourseRecordIO.writeCourseRecords() throws an IOException, 
	 * catch it and throw a new IllegalArgumentException with the message of “The file cannot be saved.”.
	 * @param fileName -> name of the file to export the schedule.
	 * @throws IllegalArgumentException if file cannot be saved
	 */
	public void exportSchedule(String fileName) {
		try {
			ActivityRecordIO.writeActivityRecords(fileName, schedule);
		} catch(Exception e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}

	/**
	 * Sets Custom Title to schedule.
	 * @param title -> Custom Title
	 */
	public void setScheduleTitle(String title) {

		if(title == null) {
			throw new IllegalArgumentException("New Title");
		}
		
		this.title = title;
	}

	/**
	 * Returns Schedule title
	 * @return the title of the schedule.
	 */
	public String getScheduleTitle() {
		return title;
	}

	/**
	 * getCourseFromCatalog() has two parameters:
	 * a course name and section. 
	 * Since a Course in the catalog is distinct by name and section 
	 * we can use those two items to find a Course.
	 *  
	 * @param name -> name of the course
	 * @param section -> section of the course.
	 * @return required course.
	 * If the Course with the given name and section does not exist in the catalog, return null.
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for(Course course: courses) {
			if(name.equals(course.getName()) && section.equals(course.getSection())) {
				return course;
			}
		}
		return null;
	}

}
