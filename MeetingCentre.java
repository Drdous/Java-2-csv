package cz.unicorncollege.bt.model;

import java.util.ArrayList;
import java.util.List;

public class MeetingCentre extends MeetingObject {
	private List<MeetingRoom> meetingRooms;

	public MeetingCentre(String name, String code, String description) {
		super(name, code, description);
		this.meetingRooms = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Meeting centre " + this.name + " with code: " + this.code + " and description: " + this.description;
	}

	public List<MeetingRoom> getMeetingRooms() {
		return meetingRooms;
	}

	public void setMeetingRooms(List<MeetingRoom> meetingRooms) {
		this.meetingRooms = meetingRooms;
	}

	public void addMeetingRooms(MeetingRoom meetingRoom) {
		this.meetingRooms.add(meetingRoom);
	}

	public String toCSV() {
		return this.name + "," + this.code + "," + this.description + ",,," + "\n";
	}
}
