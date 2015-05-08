package edu.gatech;

import java.util.HashSet;
import java.util.Map;

public class Team {
	private String teamNumber;
	private int teamGrade; // data held in Project class
	private HashSet<String> stuNames;
	private Map<String, Double> stuAveContri; // data held in Project class
	
	public String getTeamNumber() {
		return teamNumber;
	}
	public void setTeamNumber(String teamNumber) {
		this.teamNumber = teamNumber;
	}	

	public int getTeamGrade() {
		return teamGrade;
	}
	public void setTeamGrade(int teamGrade) {
		this.teamGrade = teamGrade;
	}
	public HashSet<String> getStuNames() {
		return stuNames;
	}
	public void setStuNames(HashSet<String> stuNames) {
		this.stuNames = stuNames;
	}
	public Map<String, Double> getStuAveContri() {
		return stuAveContri;
	}
	public void setStuAveContri(Map<String, Double> stuAveContri) {
		this.stuAveContri = stuAveContri;
	}

}
