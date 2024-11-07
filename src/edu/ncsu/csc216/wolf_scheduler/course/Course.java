/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import java.util.Objects;

/**
 * Creates the Course object and checks that all fields are valid.
 * @author Priyanshu Dongre
 */
public class Course extends Activity {
    /** Min length of name field. */
    private static final int MIN_NAME_LENGTH = 5;
    /** Max length of name field. */
    private static final int MAX_NAME_LENGTH = 8;
    /** Min letter count in name */
    private static final int MIN_LETTER_COUNT = 1;
    /** Max letter count in name */
    private static final int MAX_LETTER_COUNT = 4;
    /** Number of digits in name */
    private static final int DIGIT_COUNT = 3;
    /** Length of section number */
    private static final int SECTION_LENGTH = 3;
    /** Max number of possible credits for a course */
    private static final int MAX_CREDITS = 5;
    /** Min number of possible credits for a course */
    private static final int MIN_CREDITS = 1;
    /** Course's name. */
    private String name;
    /** Course's section. */
    private String section;
    /** Course's credit hours */
    private int credits;
    /** Course's instructor */
    private String instructorId;
    /**
     * Constructs a Course object with values for all fields.
     * 
     * @param name         name of Course
     * @param title        title of Course
     * @param section      section of Course
     * @param credits      credit hours for Course
     * @param instructorId instructor's unity id
     * @param meetingDays  meeting days for Course as series of chars
     * @param startTime    start time for Course
     * @param endTime      end time for Course
     */
    public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
            int startTime, int endTime) {
        super(title, meetingDays, startTime, endTime);
		setName(name);
        setSection(section);
        setCredits(credits);
        setInstructorId(instructorId);
    }

    /**
     * Creates a Course with the given name, title, section, credits, instructorId,
     * and meetingDays for courses that are arranged.
     * 
     * @param name         name of Course
     * @param title        title of Course
     * @param section      section of Course
     * @param credits      credit hours for Course
     * @param instructorId instructor's unity id
     * @param meetingDays  meeting days for Course as series of chars
     */
    public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
        this(name, title, section, credits, instructorId, meetingDays, 0, 0);
    }

    /**
     * Returns the Course's name
     * 
     * @return the name
     */
    public String getName() {
        return name;

    }

    /**
     * Sets the Course's name
     * 
     * @param name the name to set
     */
    private void setName(String name) {
        // Throw exception if the name is null
        if (name == null) {
            throw new IllegalArgumentException("Invalid course name.");
        }
        // Throw exception if the name is an empty string
        // Throw exception if the name contains less than 5 character or greater than 8
        // characters
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Invalid course name.");
        }
        // Check for pattern of L[LLL] NNN
        int letterCount = 0;
        int digitCount = 0;
        boolean spaceFound = false;
        for (int i = 0; i < name.length(); i++) {
            if (!spaceFound) {
                if (Character.isLetter(name.charAt(i))) {
                    letterCount++;
                } else if (name.charAt(i) == ' ') {
                    spaceFound = true;
                } else {
                    throw new IllegalArgumentException("Invalid course name.");
                }
            } else  {
                if (Character.isDigit(name.charAt(i))) {
                    digitCount++;
                } else {
                    throw new IllegalArgumentException("Invalid course name.");
                }
            }
        }
        if (letterCount < MIN_LETTER_COUNT || letterCount > MAX_LETTER_COUNT) {
            throw new IllegalArgumentException("Invalid course name.");
        }
        if (digitCount != DIGIT_COUNT) {
            throw new IllegalArgumentException("Invalid course name.");
        }

        this.name = name;
    }

    /**
     * Returns the Course's section
     * 
     * @return the section
     */
    public String getSection() {
        return section;
    }

    /**
     * Sets the Course's section
     * 
     * @param section the section to set
     */
    public void setSection(String section) {
        if (section == null || section.length() != SECTION_LENGTH) {
            throw new IllegalArgumentException("Invalid section.");
        }
        for (int i = 0; i < section.length(); i++) {
            if (!Character.isDigit(section.charAt(i))) {
                throw new IllegalArgumentException("Invalid section.");
            }
        }
        this.section = section;
    }

    /**
     * Returns the Course's credits
     * 
     * @return the credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Sets the Course's credits
     * 
     * @param credits the credits to set
     */
    public void setCredits(int credits) {
        if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
            throw new IllegalArgumentException("Invalid credits.");
        }

        this.credits = credits;
    }

    /**
     * Returns the Course's instructor id
     * 
     * @return the instructorId
     */
    public String getInstructorId() {
        return instructorId;
    }

    /**
     * Sets the Course's instructor id
     * 
     * @param instructorId the instructorId to set
     */
    public void setInstructorId(String instructorId) {
        if (instructorId == null || "".equals(instructorId)) {
            throw new IllegalArgumentException("Invalid instructor id.");
        }

        this.instructorId = instructorId;
    }

 

    /**
     * Returns a comma separated value String of all Course fields.
     * 
     * @return String representation of Course
     */
    @Override
    public String toString() {
        if ("A".equals(meetingDays)) {
            return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
        }
        return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays + ","
                + getStartTime() + "," + getEndTime();
    }

    
    /**
     * Returns a short version of the display array for this `Course`.
     * This method overrides the `getShortDisplayArray` method and provides key details about the course 
     * including the course name, section, title, and meeting times.
     * 
     * @return StringArray containing a concise representation of the course's details
     */
	@Override
	public String[] getShortDisplayArray() {
		String[]  display = new String[4];
    	
    	display[0] = this.name;
    	display[1] = this.section;
    	display[2] = getTitle();
    	display[3] = getMeetingString();
    	
    	return display;
	}

	/**
	 * Returns a detailed version of the display array for this `Course`.
	 * This method overrides the `getLongDisplayArray` method and provides more detailed information 
	 * about the course, including the course name, section, title, credits, instructor ID, and meeting times.
	 * 
	 * @return StringArray containing an expanded representation of the course's details
	 */
	
	@Override
	public String[] getLongDisplayArray() {
		String[]  display = new String[7];
    	
    	display[0] = this.name;
    	display[1] = this.section;
    	display[2] = getTitle();
    	display[3] = "" + this.credits;
    	display[4] = this.instructorId;
    	display[5] = getMeetingString();
    	display[6] = "";
    	
    	return display;
	}
	
	
	/**
	 * Sets the meeting days and time for this Course. 
	 * This method validates that the provided meetingDays, startTime, and endTime are valid 
	 * and throws an IllegalArgumentException if the input is invalid.
	 * 
	 * Valid meeting days are represented by the characters 'M', 'T', 'W', 'H', and 'F' (Monday to Friday).
	 * 
	 * @param meetingDays a String representing the days of the week the course will meet
	 * @param startTime the start time of the course in military time (e.g., 1330 for 1:30 PM)
	 * @param endTime the end time of the course in military time (e.g., 1530 for 3:30 PM)
	 * @throws IllegalArgumentException if the input is invalid
	 */
	
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if ("A".equals(meetingDays)) {
			if (startTime == 0 && endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			if (startTime != 0 && endTime == 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			if (startTime != 0 && endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		} else {
			int countMonday = 0;
			int countTuesday = 0;
			int countWednesday = 0;
			int countThursday = 0;
			int countFriday = 0;

			for (int i = 0; i < meetingDays.length(); i++) {
				if (meetingDays.charAt(i) == 'M') {
					countMonday++;
				} else if (meetingDays.charAt(i) == 'T') {
					countTuesday++;
				} else if (meetingDays.charAt(i) == 'W') {
					countWednesday++;
				} else if (meetingDays.charAt(i) == 'H') {
					countThursday++;
				} else if (meetingDays.charAt(i) == 'F') {
					countFriday++;
				} else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}

			if (countMonday > 1 || countTuesday > 1 || countWednesday > 1 || countThursday > 1 || countFriday > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			int startHour = startTime / 100;
			int startMin = startTime % 100;
			int endHour = endTime / 100;
			int endMin = endTime % 100;

			if (startHour < 0 || startHour >= 24) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (startMin < 0 || startMin >= 60) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (endHour < 0 || endHour >= 24) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (endMin < 0 || endMin >= 60) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			if (startTime > endTime) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

		}
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Generates a hash code for this Course object based on its credits, instructorId, 
	 * name, and section. This method overrides the default implementation and uses a prime 
	 * number and Objects.hash to generate the hash.
	 * 
	 * @return an integer hash code value derived from the Course's fields
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(credits, instructorId, name, section);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Course))
			return false;
		Course other = (Course) obj;
		return credits == other.credits && Objects.equals(instructorId, other.instructorId)
				&& Objects.equals(name, other.name) && Objects.equals(section, other.section);
		
		
	}

	
	/**
	 * Compares this Course object with another object for equality. 
	 * Two Course objects are considered equal if they have the same credits, instructorId, 
	 * name, and section. This method overrides the equals method from the Object class.
	 * 
	 * @param obj activity the object to compare to this Course object
	 * @return true if the specified object is equal to this Course, false otherwise
	 */
	@Override
	public boolean isDuplicate(Activity obj) {
		if (!(obj instanceof Course))
			return false;
		Course other = (Course) obj;
		return this.name.equals(other.getName());
	}
	
    
    

}