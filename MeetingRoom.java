package cz.unicorncollege.bt.model;

public class MeetingRoom extends MeetingObject {
	private int capacity;
	private boolean hasVideoConference;
	private MeetingCentre meetingCentre;

	public MeetingRoom(String name, String code, String description, int capacity, boolean hasVideoConference,
			MeetingCentre meetingCentre) {
		super(name, code, description);
		this.capacity = capacity;
		this.hasVideoConference = hasVideoConference;
		this.meetingCentre = meetingCentre;
	}

	public String toCSV() {
		return this.name + "," + this.code + "," + this.description + "," + this.capacity + ","
				+ (this.hasVideoConference ? "YES" : "NO") + "," + this.meetingCentre.getCode() + "\n";
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isHasVideoConference() {
		return hasVideoConference;
	}

	public void setHasVideoConference(boolean hasVideoConference) {
		this.hasVideoConference = hasVideoConference;
	}

	public MeetingCentre getMeetingCentre() {
		return meetingCentre;
	}

	public void setMeetingCentre(MeetingCentre meetingCentre) {
		this.meetingCentre = meetingCentre;
	}
}
