package edu.gatech.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;

import junit.framework.TestCase;

import org.junit.Test;

import edu.gatch.Constants;
import edu.gatch.GradesDB;
import edu.gatch.Project;
import edu.gatch.Team;

public class ProjectTest extends TestCase {
	Project project = null;
	GradesDB db = null;

	protected void setUp() throws Exception {
		project = new Project();
		db = new GradesDB(Constants.GRADES_DB);

		super.setUp();
	}

	protected void tearDown() throws Exception {
		System.setOut(null);
		super.tearDown();
	}

	/*
	 * * test cases modified due to minor changes in data structure of Team and Project classes
	 */
	public void testGetAvrGradesForProject() {
		ArrayList<Project> projects = null;
		try {
			projects = db.getProjects();
		} catch (Exception e) {
			fail("Exception");
		}
		project = projects.get(0);
		int aveGrade = project.calculatePrjAveGrade(project.getTeamGrades());
		assertEquals(93, aveGrade);

	}

	public void testGetTeamGradeByStuName() {
		ArrayList<Project> projects = null;
		try {
			projects = db.getProjects();
		} catch (Exception e) {
			fail("Exception");
		}
		project = projects.get(0);
		Team tm = project.getTeamByStuName("Laraine Smith");
		int teamGrades = project.getTeamGrades().get(tm.getTeamNumber());
		assertEquals(96, teamGrades);
	}
	
	public void testGetStuAveContributionByStuName(){
		ArrayList<Project> projects = null;
		try {
			projects = db.getProjects();
		} catch (Exception e) {
			fail("Exception");
		}
		project = projects.get(0);
		double contri = project.getStuAveContributionByStuName("Laraine Smith");
		assertEquals(8.50, contri, 0.01);
		
	}
	
	// getTeamForProject method is not needed in the final product
	// public void testGetTeamForProject() {
	// Project p = db.getProjectsByNum("P1");
	// Team[] teams = p.getTeamForProject();
	// Team team1 = teams[0];
	// assertEquals(team1.teamGrade, 93);
	// }
	//
	// public void testGetTeamForProject2() {
	// Project p = db.getProjectsByNum("P1");
	// Team[] teams = p.getTeamForProject();
	// Team team1 = teams[0];
	// assertEquals(team1.studentNames[0], "Freddie Catlay");
	// }
}
