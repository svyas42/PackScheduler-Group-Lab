
package edu.ncsu.csc216.pack_scheduler.course;
/**
 * Outlines the information and behavior for the course and event sub-classes
 * @author Sachi Vyas
 */
public abstract class Activity implements Conflict {

	/** The upper integer value in hours */
	private static final int UPPER_HOUR = 24;
	/** The upper integer value in minutes */
	private static final int UPPER_MINUTE = 60;
	/** Course's title. */
	protected String title;
	/** Course's meeting days */
	protected String meetingDays;
	/** Course's starting time */
	protected int startTime;
	/** Course's ending time */
	protected int endTime;

	/**
	 * Constructor for super class of the course i.e. Activity 
	 * @param title the title of the course
	 * @param meetingDays the meeting days of the course
	 * @param startTime the start time of the course
	 * @param endTime the end time of the course
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
		setTitle(title);
	}

	/**
	 * Returns the title of the Course
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the Course
	 * @param title the title to set
	 * @throws IllegalArgumentException if the title is invalid.
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Invalid title.");
		}
		if ("".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		if (title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}
		
		this.title = title;
		
	}

	/**
	 * Returns the meeting days of the Course
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Course's starting time
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the ending time of the Course
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Setting the meeting days, start and end time for the Courses
	 * @param meetingDays the meeting days for the course
	 * @param startTime the start time for the course
	 * @param endTime the end time for the course
	 * @throws IllegalArgumentException if meeting days and time are invalid.
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		
			
		  	int startHour = startTime / 100;
		  	int startMin = startTime % 100;
		  	int endHour = endTime / 100;
		  	int endMin = endTime % 100;
		  	
			if (startHour < 0 || startHour >= UPPER_HOUR) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (startMin < 0 || startMin >= UPPER_MINUTE) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (endHour < 0 || endHour >= UPPER_HOUR) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (endMin < 0 || endMin >= UPPER_MINUTE) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (startTime > endTime) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		
			this.meetingDays = meetingDays;
			this.startTime = startTime;
			this.endTime = endTime;
			convertStandardAM(startTime);
			convertStandardPM(endTime);
	}

	/**
	 * Convert the military time given to standard time (AM)
	 * @param time the military time
	 * @return standard_AM
	 */
	private String convertStandardAM(int time) {
		int timee = time;
		if (time < UPPER_HOUR) {
			timee += 1200;			
		}
		String standardAM = timee + "";
		if (standardAM.length() == 4) {
			standardAM = standardAM.substring(0, 2) + ":" + standardAM.substring(2, standardAM.length());
		}
		if (standardAM.length() == 3) {
			standardAM = standardAM.substring(0, 1) + ":" + standardAM.substring(1, standardAM.length());
		}
	
		
		return standardAM;
	}

	/**
	 * Convert military time given to standard time (PM)
	 * @param time the military time
	 * @return standard_PM
	 */
	private String convertStandardPM(int time) {
		int time1 = time;
		if (time >= 1300) {
			time1 -= 1200;
		}
		String standardPM = time1 + "";
		
		if (standardPM.length() == 4) {
			standardPM = standardPM.substring(0, 2) + ":" + standardPM.substring(2, standardPM.length());
		}
		if (standardPM.length() == 3) {
			standardPM = standardPM.substring(0, 1) + ":" + standardPM.substring(1, standardPM.length());
		}
		
		return standardPM;
	}

	/**
	 * Helper method to help convert the standard start and end time.
	 * @return a string "startTimeStandard-endTimeStandard"
	 */
	private String formatTimes() {
		String startTimeStandard;
		String endTimeStandard;
		
		
		if (this.startTime < 1200) {
			startTimeStandard = convertStandardAM(startTime) + "AM";
		}
		else {
			startTimeStandard = convertStandardPM(startTime) + "PM";					
		}
		if (this.endTime < 1200) {
			endTimeStandard = convertStandardAM(endTime) + "AM";
		}
		else {
			endTimeStandard = convertStandardPM(endTime) + "PM";
		}
			
		return startTimeStandard + "-" + endTimeStandard;
	}

	/**
	 * Return the meeting information as a string in standard time format.
	 * @return String representation of the information
	 */
	public String getMeetingString() {
		String arranged = "Arranged";
		
		if ("A".equals(meetingDays)) {
			return arranged;
		}
	
		return meetingDays + " " + formatTimes();
	}
	
	/**
	 * Generates a hashCode for Course using all fields.
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	/**
	 * Returns an array with Course name, section, title, and meeting string needed for the short display
	 * in the WolfScheduler GUI
	 * @return shortArray a string containing name, section, title, and meeting string;
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * Returns an array with Course name, section, title, credits, instructorId, meeting string,
	 * @return longArray a string containing name, section, title, credits, instructorId, meeting string
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * Checks if an activity is a duplicate of activity that is already on the student's schedule
	 * @param activity the activity to compare to other activities already present in the schedule
	 * @return boolean based on if the activity is already present in the schedule or not
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * To check if there is a conflict between two events
	 * @param possibleConflictingActivity which is an instance of the activity class
	 * @throws ConflictException if two events conflict with each other
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		String day1 = possibleConflictingActivity.getMeetingDays().toUpperCase();
		String day2 = this.getMeetingDays().toUpperCase();
		
		try {
			
			if (day1.contains("M") && day2.contains("M") ||
					day1.contains("T") && day2.contains("T") ||
					day1.contains("W") && day2.contains("W") ||
					day1.contains("H") && day2.contains("H") ||
					day1.contains("F") && day2.contains("F") ||
					day1.contains("S") && day2.contains("S") ||
					day1.contains("U") && day2.contains("U")) {
				
			
		
				if (this.getStartTime() <= possibleConflictingActivity.getStartTime() && 
						this.getEndTime() <= possibleConflictingActivity.getEndTime() &&
						this.getEndTime() >= possibleConflictingActivity.getStartTime()) {
					throw new ConflictException();
				}
			
				if (this.getStartTime() >= possibleConflictingActivity.getStartTime() && 
						this.getEndTime() <= possibleConflictingActivity.getEndTime()) {
					throw new ConflictException();
				}
			
				if (this.getStartTime() >= possibleConflictingActivity.getStartTime() && 
						this.getEndTime() <= possibleConflictingActivity.getEndTime()) {
					throw new ConflictException();
				}
			
				if (this.getStartTime() >= possibleConflictingActivity.getStartTime() && 
						this.getEndTime() >= possibleConflictingActivity.getEndTime() &&
						this.getStartTime() <= possibleConflictingActivity.getEndTime()) {
					throw new ConflictException();
				}
				
				if (this.getStartTime() <= possibleConflictingActivity.getStartTime() && 
						this.getEndTime() >= possibleConflictingActivity.getEndTime() &&
						this.getEndTime() >= possibleConflictingActivity.getStartTime()) {
					throw new ConflictException();
				}
				
			}
		
		} catch (ConflictException e) {
			throw new ConflictException();
		}
	}
		
	

}
