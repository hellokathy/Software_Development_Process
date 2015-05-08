package edu.gatch;

public class Student {

	private String name;
	private String gtid;
	private int attendance;
	private String email;
	private Team team;  // data held in Project class
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGtid() {
		return gtid;
	}
	public void setGtid(String gtid) {
		this.gtid = gtid;
	}
	public int getAttendance() {
		return attendance;
	}
	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
}

