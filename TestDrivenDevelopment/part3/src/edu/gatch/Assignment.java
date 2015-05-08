package edu.gatch;

import java.util.HashMap;
import java.util.Map;

public class Assignment {
	private int assignNum;
	private String assignName;
	private String assignDesc;
	private HashMap<String, Integer> stuGrades;
	private int assignAveGrade;

	public Assignment() {
		super();
	}

	public int getAssignNum() {
		return assignNum;
	}

	public void setAssignNum(int assignNum) {
		this.assignNum = assignNum;
	}

	
	public String getAssignName() {
		return assignName;
	}

	public void setAssignName(String assignName) {
		this.assignName = assignName;
	}

	public String getAssignDesc() {
		return assignDesc;
	}

	public void setAssignDesc(String assignDesc) {
		this.assignDesc = assignDesc;
	}

	public HashMap<String, Integer> getStuGrades() {
		return stuGrades;
	}

	public void setStuGrades(HashMap<String, Integer> stuGrades) {
		this.stuGrades = stuGrades;
	}
	
	public int getAssignAveGrade() {
		return assignAveGrade;
	}

	public void setAssignAveGrade(int assignAveGrade) {
		this.assignAveGrade = assignAveGrade;
	}

	// Calculate average assignment grade for the class
	public int calculateAssignAveGrade(HashMap<String, Integer> stuGrades) {
		int gradeSum = 0;

		for (Map.Entry entry : stuGrades.entrySet()){
			gradeSum += (int) entry.getValue();
		}
		
		if (stuGrades.size()!=0){
		return gradeSum / stuGrades.size();
		} else {
			System.out.println("No data found for this assignment!");
			return 0;			
		}

		
		
	}

}
