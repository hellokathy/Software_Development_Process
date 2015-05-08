package edu.gatech.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import junit.framework.TestCase;

import org.junit.Test;

import edu.gatch.Assignment;
import edu.gatch.Constants;
import edu.gatch.GradesDB;



public class AssignmentTest extends TestCase {
	Assignment assignment = null;
	GradesDB db = null;

	protected void setUp() throws Exception {
		assignment = new Assignment();
		db = new GradesDB(Constants.GRADES_DB);
		super.setUp();
	}

	protected void tearDown() throws Exception {
		System.setOut(null);
		super.tearDown();
	}

	public void testGetGradesForAssignment() {
		ArrayList<Assignment> assignments = null;
		try {
			assignments = db.getAssignments();
		} catch (Exception e) {
			fail("Exception");
		}
		assignment = assignments.get(0);
		HashMap<String, Integer> stuGrades = assignment.getStuGrades();
		assertTrue(stuGrades.get("Josepha Jube") == 100);

	}
	
	/**
	 *  This test case validates the the average grades of an assignment in one team.
	 */

	public void testGetAvrGradesForAssignment() {
		ArrayList<Assignment> assignments = null;
		try {
			assignments = db.getAssignments();
		} catch (Exception e) {
			fail("Exception");
		}
		assignment = assignments.get(0);
		HashMap<String, Integer> stuGrades = assignment.getStuGrades();
		int aveGrade = assignment.calculateAssignAveGrade(stuGrades);
		assertEquals(aveGrade, 99);
	}

}