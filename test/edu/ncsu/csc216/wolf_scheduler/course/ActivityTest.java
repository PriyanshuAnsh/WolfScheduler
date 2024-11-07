/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * A java Program to Test the Activity Class.
 */
class ActivityTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_scheduler.course.Activity#checkConflict(edu.ncsu.csc216.wolf_scheduler.course.Activity)}.
	 */
	@Test
	public void testCheckConflict() {
	    Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330, 1445);
	    
	    assertDoesNotThrow(() -> a1.checkConflict(a2));
	    assertDoesNotThrow(() -> a2.checkConflict(a1));
	}
	
	/**
	 * Tests checkConflict() with two activities that have a conflict on the same day and time.
	 */
	
	@Test
	public void testCheckConflictWithConflict() {
	    Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);
		
	    Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
		
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	}
	
	
	/**
	 * Tests checkConflict() when two activities have a time continuity conflict.
	 */
	@Test
	public void testCheckConflictWithConflictActivityContinuity() {
		    Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		    Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "W", 1445, 1600);
			
		    Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		    assertEquals("Schedule conflict.", e1.getMessage());
			
		    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		    assertEquals("Schedule conflict.", e2.getMessage());
	}
	
	
	/**
	 * Tests checkConflict() with activities that do not have overlapping times.
	 */
	@Test
	public void testNoConflict() {
			Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		    Activity a2 = new Event("Fair", "W", 1600, 1630, "Career Fair");
			
		    assertDoesNotThrow(() -> a1.checkConflict(a2));
		    assertDoesNotThrow(() -> a2.checkConflict(a1));
	}
	
	
	/**
	 * Tests checkConflict() with an activity scheduled in the middle of another activity.
	 */
	@Test
	public void testCheckConflictWithConflictActivityinBetweenActivity() {
		    Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		    Activity a2 = new Event("CSC Fair", "W", 1400, 1430, "CSC Career Fair");
			
		    Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		    assertEquals("Schedule conflict.", e1.getMessage());
			
		    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		    assertEquals("Schedule conflict.", e2.getMessage());
	}
	
	
	/**
	 * Tests checkConflict() with arranged courses that do not have specific meeting times.
	 */
	@Test
	public void testCheckConflictArrangedCourses() {
		 	Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "A");
		    Activity a2 = new  Course("CSC 217", "Software Development Fundamentals Lab", "204", 1, "sesmith5", "A");
		    
		    assertDoesNotThrow(() -> a1.checkConflict(a2));
		    assertDoesNotThrow(() -> a2.checkConflict(a1));
	}

}
