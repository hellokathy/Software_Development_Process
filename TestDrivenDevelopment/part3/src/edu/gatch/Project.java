package edu.gatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Project {
	private int prjNum;
	private String prjName;
	private String prjDesc;
	private ArrayList<Team> prjTeams;
	private HashMap<String, Double> prjStuAveContributions;  // map student name to his/her average contribution
	private HashMap<String, Integer> teamGrades;  // map team number to its grade
	private int prjAveGrade;

	public Project() {
		super();
	}

	public int getPrjNum() {
		return prjNum;
	}

	public void setPrjNum(int prjNum) {
		this.prjNum = prjNum;
	}

	public String getPrjName() {
		return prjName;
	}

	public void setPrjName(String prjName) {
		this.prjName = prjName;
	}

	public String getPrjDesc() {
		return prjDesc;
	}

	public void setPrjDesc(String prjDesc) {
		this.prjDesc = prjDesc;
	}

	public ArrayList<Team> getPrjTeams() {
		return prjTeams;
	}

	public void setPrjTeams(ArrayList<Team> prjTeams) {
		this.prjTeams = prjTeams;
	}

	public HashMap<String, Double> getPrjStuAveContributions() {
		return prjStuAveContributions;
	}

	public void setPrjStuAveContributions(
			HashMap<String, Double> prjStuAveContributions) {
		this.prjStuAveContributions = prjStuAveContributions;
	}

	public HashMap<String, Integer> getTeamGrades() {
		return teamGrades;
	}

	public void setTeamGrades(HashMap<String, Integer> teamGrades) {
		this.teamGrades = teamGrades;
	}

	public int getPrjAveGrade() {
		return prjAveGrade;
	}

	public void setPrjAveGrade(int prjAveGrade) {
		this.prjAveGrade = prjAveGrade;
	}

	// Calculate average project grade across teams
	public int calculatePrjAveGrade(HashMap<String, Integer> teamGrades) {
		int gradeSum = 0;

		for (Map.Entry entry : teamGrades.entrySet()) {
			gradeSum += (int) entry.getValue();
		}

		if (teamGrades.size() != 0) {
			return gradeSum / teamGrades.size();
		} else {
			return 0;
		}
	}
	
	// Get team for a selected student by his/her name
	public Team getTeamByStuName (String stuName){
		Team tm = new Team();
		for (Team t : this.prjTeams){
			if (t.getStuNames().contains(stuName)){
				tm = t;
			}
		}
		return tm;		
	}
	
	// Get team grade for a selected student by his/her name 
	public int getTeamGradeByStuName (String stuName){
		Team tm = this.getTeamByStuName(stuName);
		return this.teamGrades.get(tm.getTeamNumber());		
	}
	
	// Get average contribution for a selected student by student's name
	public double getStuAveContributionByStuName (String stuName){
		if (this.prjStuAveContributions.containsKey(stuName)){
		return this.prjStuAveContributions.get(stuName);
		} else {
			System.out.println("No data found for this student!");
			return 0;
		}
	}
}
