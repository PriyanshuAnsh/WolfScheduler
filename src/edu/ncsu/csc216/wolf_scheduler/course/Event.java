
package edu.ncsu.csc216.wolf_scheduler.course;

import java.util.Objects;

/**
 * Represents an Event, a specific type of Activity with additional details.
 * An Event includes a title, meeting days, start time, end time, and event specific details.
 * This class extends the Activity class.
 * @author Priyanshu Dongre
 */
public class Event extends Activity {

	/** Details specific to the event */
	
	private String eventDetails;
	
	/**
     * Constructs an Event with the provided title, meeting days, start time, end time, and event details.
     * 
     * @param title the title of the event
     * @param meetingDays the days the event is scheduled (e.g., "MWF" for Monday, Wednesday, Friday)
     * @param startTime the start time of the event in military time (e.g., 1330 for 1:30 PM)
     * @param endTime the end time of the event in military time
     * @param eventDetails additional details about the event
     * @throws IllegalArgumentException if event details are null or empty
     */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setEventDetails(eventDetails);
	}

	/**
     * Returns the event specific details.
     * 
     * @return the event details as a String
     */
	public String getEventDetails() {
		return eventDetails;
	}

	 /**
     * Sets the event specific details.
     * 
     * @param eventDetails the details to set for the event
     * @throws IllegalArgumentException if event details are null or empty
     */
	public void setEventDetails(String eventDetails) {
		
		if(eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		this.eventDetails = eventDetails;

	}

	
	 /**
     * Returns a short version of the display array for the event.
     * This method overrides the getShortDisplayArray method from Activity.
     * The display includes only the title and meeting string.
     * 
     * @return StringArray containing a brief representation of the event's details
     */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplay = new String[4];
		
		shortDisplay[0] = "";
		shortDisplay[1] = "";
		shortDisplay[2] = getTitle();
		shortDisplay[3] = getMeetingString();
		return shortDisplay;
	}

	
	/**
     * Returns a detailed version of the display array for the event.
     * This method overrides the getLongDisplayArray method from Activity.
     * The display includes the meeting string and event specific details.
     * 
     * @return a StringArray containing a detailed representation of the event's details
     */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplay = new String[7];
		longDisplay[0] = "";
		longDisplay[1] = "";
		longDisplay[2] = getTitle();
		longDisplay[3] = "";
		longDisplay[4] = "";
		longDisplay[5] = getMeetingString();
		longDisplay[6] = eventDetails;
		return longDisplay;
	}
	
	 /**
     * Returns a string representation of the event, including the title, meeting days, 
     * start time, end time, and event specific details.
     * 
     * @return a String representing the event's details in a comma separated format
     */
	@Override
	public String toString() {
		String toString = getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() +  "," + this.eventDetails;
		return toString;
	}

	
	 /**
     * Checks if the given Activity is a duplicate of this Event.
     * Two events are considered duplicates if they have the same title.
     * This method overrides the isDuplicate method from Activity.
     * 
     * @param obj activity the Activity object to compare against this Event
     * @return true if the given Activity has the same title, false otherwise
     */
	@Override
	public boolean isDuplicate(Activity obj) {
		if (!(obj instanceof Event))
			return false;
		Event other = (Event) obj;
		return Objects.equals(title, other.getTitle());
	
	}

}
