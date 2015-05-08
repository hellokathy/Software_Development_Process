package edu.gatech;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) throws IOException {
		String name = "Freddie Catlay";
		String gtid;
		String email;
		int attendance;

		GradesDB db = new GradesDB(Constants.GRADES_DB);

		Student stu = db.getStudentByName(name);
		gtid = stu.getGtid();
		email = stu.getEmail();
		attendance = stu.getAttendance();

		System.out.println(name);
		System.out.println(gtid);
		System.out.println(email);
		System.out.println(attendance);

		System.out.println();

		ArrayList<Assignment> assignments = db.getAssignments();
		for (Assignment a : assignments) {
			System.out.println(a.getAssignNum());
			System.out.println(a.getAssignName() + ": " + a.getAssignDesc());
			System.out.println("Average grade for the class: "
					+ a.getAssignAveGrade());
			HashMap<String, Integer> grades = a.getStuGrades();
			int grade = grades.get(name);
			System.out.println(name + "'s grade: " + grade);
		}

		System.out.println();
		ArrayList<Team> p1Teams = db.getP1Teams();
		for (Team tm : p1Teams) {
			System.out.println(tm.getTeamNumber());
			System.out.println(tm.getStuNames());
		}
		System.out.println();
		ArrayList<Team> p2Teams = db.getP2Teams();
		for (Team tm : p2Teams) {
			System.out.println(tm.getTeamNumber());
			System.out.println(tm.getStuNames());
		}
		System.out.println();
		ArrayList<Team> p3Teams = db.getP3Teams();
		for (Team tm : p3Teams) {
			System.out.println(tm.getTeamNumber());
			System.out.println(tm.getStuNames());
		}

		System.out.println();

		ArrayList<Project> projects = db.getProjects();
		for (Project p : projects) {
			System.out.println(p.getPrjNum());
			System.out.println(p.getPrjName() + ": " + p.getPrjDesc());
			System.out.println("# of teams in this project: "
					+ p.getPrjTeams().size());
			System.out.println("Average grade for this project: "
					+ p.calculatePrjAveGrade(p.getTeamGrades()));
			System.out.println("Team grades break down: " + p.getTeamGrades());
			System.out.println("Student average contributions: " + p.getPrjStuAveContributions());
			System.out.println("Average contribution of " + name + " is: " + p.getStuAveContributionByStuName(name));
			System.out.println(name + " is in " + p.getTeamByStuName(name).getTeamNumber());
			System.out.println(name + "'s team grade is " + p.getTeamGradeByStuName(name));
		}
	}

}
