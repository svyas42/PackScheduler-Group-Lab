
package edu.ncsu.csc216.pack_scheduler.course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;
/**
 * Creating a schedule with the course name, title, section, credits, instructorId, meetings days, and times
 * @author Sachi Vyas
 * @author Jason Maher
 * @author Musa Malik
 *
 */
public class Course extends Activity implements Comparable<Course> {
	/** Minimum length allowed for the course name */
	private static final int MIN_NAME_LENGTH = 4;
	/** Maximum length allowed for the course name */
	private static final int MAX_NAME_LENGTH = 8;
//	/** Minimum letter count allowed for the course name */
//	private static final int MIN_LETTER_COUNT = 1;
//	/** Maximum letter count allowed for the course name */
//	private static final int MAX_LETTER_COUNT = 4;
//	/** Number of digits that should be there in the course name */
//	private static final int DIGIT_COUNT = 3;
	/** Length of the course section */
	private static final int SECTION_LENGTH = 3;
	/** Maximum number of course credits allowed */
	private static final int MAX_CREDITS = 5;
	/** Minimum number of course credits allowed */
	private static final int MIN_CREDITS = 1;
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Course's roll */
	private CourseRoll roll;
//	/** Validator for course name */
//	private CourseNameValidator validator;
	
	
	
	/**
	 * Creates a Course with the given name, title, section, credits, instructorId, meetingDays, startTime, and endTime for 
	 * courses that are arranged.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param enrollmentCap the roll’s enrollment capacity
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime start time for Course
	 * @param endTime end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays, 
					int startTime, int endTime) {
	
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);	
		roll = new CourseRoll(this, enrollmentCap);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays for 
	 * courses that are arranged.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param enrollmentCap the roll’s enrollment capacity
	 * @param meetingDays meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
	    this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set's the Course's name.
	 * @param name the name to set
	 * @throws IllegalArgumentException if the course name is invalid.
	 */
	private void setName(String name) {
		
		if (name == null) {
			throw new IllegalArgumentException("Invalid course name.");			
		}
		if ("".equals(name)) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		if (name.length() < MIN_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		if (name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		if (name.charAt(0) == ' ') {
			throw new IllegalArgumentException("Invalid course name.");
		}
		if (name.contains(" ")) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		CourseNameValidator courseValidator = new CourseNameValidator();
		boolean isValid = false;
		try {
			isValid = courseValidator.isValid(name);
			isValid = true;
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		if (isValid) {
			this.name = name;
		}
		
	}
		
	/**
	 * Returns the section of the Course
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	
	/**
	 * Sets the section of the Course
	 * @param section the section to set
	 * @throws IllegalArgumentException if the section is the invalid
	 */
	public void setSection(String section) {
		if (section == null) {
			throw new IllegalArgumentException("Invalid section.");
		}
		if (section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}
		
		for (int i = 0; i < SECTION_LENGTH; i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		this.section = section;
	}
	
	/**
	 * Returns the credit hours for the Course
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}
	
	/**
	 * Sets the credit hours for the Course
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if the number of credits are invalid.
	 */
	public void setCredits(int credits) {
		
		if (credits < MIN_CREDITS || credits > MAX_CREDITS){
			throw new IllegalArgumentException("Invalid credits.");
		}
		
		this.credits = credits;
	}
	
	/**
	 * Returns the instructor of the Course
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}
	
	/**
	 * Sets the instructorId of the Course
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if the instructorId is invalid
	 */
	public void setInstructorId(String instructorId) {
//		if (instructorId == null || "".equals(instructorId)) {
//			throw new IllegalArgumentException("Invalid instructor id.");
//		}
		
		if (instructorId != null && "".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}
	
	/**
	 * Sets the meeting day and time of the event
	 * @param meetingDays the meeting days of the course
	 * @param startTime the start time of the course
	 * @param endTime the end time of the course
	 * @throws IllegalArgumentException if the meeting days or times are invalid
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times");
		}
	
		int m = 0;
		int t = 0;
		int w = 0;
		int h = 0;
		int f = 0;
			
		if (meetingDays.contains("A")) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			this.meetingDays = meetingDays;
			this.startTime = startTime;
			this.endTime = endTime;
		}
		else {
			for (int i = 0; i < meetingDays.length(); i++) {
				char a = meetingDays.charAt(i);
				if (a == 'M') {
					m++;
				}
				else if (a == 'T') {
					t++;
				}
				else if (a == 'W') {
					w++;
				}
				else if (a == 'H') {
					h++;
				}
				else if (a == 'F') {
					f++;
				}
				else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
		  }
			
			
		  if (m > 1) {
			  throw new IllegalArgumentException("Invalid meeting days and times.");
		  }
		  if (t > 1) {
			  throw new IllegalArgumentException("Invalid meeting days and times.");
		  }
		  if (w > 1) {
			  throw new IllegalArgumentException("Invalid meeting days and times.");
		  }
		  if (h > 1) {
			  throw new IllegalArgumentException("Invalid meeting days and times.");
		  }
		  if (f > 1) {
			  throw new IllegalArgumentException("Invalid meeting days and times.");
		  }
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		  
	}
	
	
	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	public String toString() {
	    if ("A".equals(meetingDays)) {
	        return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + meetingDays;
	    }
	    return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + meetingDays + "," + startTime + "," + endTime; 
	}
	
	/**
	 * Generates a hashCode for Course using all fields.
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}
	
	/**
	 * Compares a given object to this object for equality on all fields.
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}
	
	/**
	 * Returns an array with Course name, section, title, and meeting string needed for the short display
	 * in the WolfScheduler GUI
	 * @return shortArray a string containing name, section, title, and meeting string;
	 */
	public String[] getShortDisplayArray() {
		String[] shortArray = new String[5];
		shortArray[0] = name;
		shortArray[1] = section;
		shortArray[2] = title;
		shortArray[3] = getMeetingString();
		shortArray[4] = String.valueOf(roll.getOpenSeats());
		return shortArray;
	}
	
	/**
	 * Returns an array with Course name, section, title, credits, instructorId, meeting string,
	 * @return longArray a string containing name, section, title, credits, instructorId, meeting string
	 */
	public String[] getLongDisplayArray() {
		String[] longArray = new String[7];
		longArray[0] = name;
		longArray[1] = section;
		longArray[2] = title;
		longArray[3] = credits + "";
		longArray[4] = instructorId;
		longArray[5] = getMeetingString();
		longArray[6] = "";
		return longArray;
		
	}
	
	/**
	 * Checks if the activity is an instance of Course
	 * @param activity the activity to compare
	 * @return boolean based on if the activity is already in the schedule or not
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		Course courseIsInActivity;
		
		if(activity instanceof Course) {
			courseIsInActivity = (Course) activity;
		}
		else {
			return false;
		}
		
		return courseIsInActivity.getName().equals(this.getName());
			
	}
	
	/**
	 * Compares courses by name and section number
	 * @param c the course to compare
	 * @return integer based on the ordering of the courses
	 */
	@Override
	public int compareTo(Course c) {
		int sectionOrder = this.getSection().toUpperCase().compareTo(c.getSection().toUpperCase());
		int nameOrder = this.getName().toUpperCase().compareTo(c.getName().toUpperCase());
		
		if (nameOrder == 0) {
			return sectionOrder;
		} 
		else {
			return nameOrder;
		}
	}
	
	/**
	 * Gets the course roll
	 * @return roll of the course i.e. the updated roll of student in a particular course
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}

}
