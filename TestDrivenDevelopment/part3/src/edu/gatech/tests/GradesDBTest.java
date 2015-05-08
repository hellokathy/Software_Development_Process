package edu.gatech.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import edu.gatch.Assignment;
import edu.gatch.Constants;
import edu.gatch.GradesDB;
import edu.gatch.Project;
import edu.gatch.Student;
import junit.framework.TestCase;


public class GradesDBTest extends TestCase {
	GradesDB db = null;

	protected void setUp() throws Exception {
		db = new GradesDB(Constants.GRADES_DB);
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetNumStudents() throws IOException {
		try {
			int numStudents = db.getNumStudents();
			assertEquals(14, numStudents);
		} catch (Exception e) {
			fail("Exception");
		}
	}

	public void testGetNumAssignments() {
		try {
			int numAssignments = db.getNumAssignments();
			assertEquals(3, numAssignments);
		} catch (Exception e) {
			fail("Exception");
		}
	}

	public void testGetNumProjects() {
		int numProjects;
		try {
			numProjects = db.getNumProjects();
			assertEquals(3, numProjects);
		} catch (Exception e) {
			fail("Exception");
		}
	}

	public void testGetStudents1() throws IOException {
		HashSet<Student> students = null;
		try {
			students = db.getStudents();
		} catch (Exception e) {
			fail("Exception");
		}
		assertEquals(14, students.size());
	}

	public void testGetStudents2() {
		HashSet<Student> students = null;
		try {
			students = db.getStudents();
		} catch (Exception e) {
			fail("Exception");
		}
		boolean found = false;
		for (Student s : students) {
			if ((s.getName().compareTo("Cynthia Faast") == 0)
					&& (s.getGtid().compareTo("901234514") == 0)) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}

	public void testGetStudents3() {
		HashSet<Student> students = null;
		try {
			students = db.getStudents();
		} catch (Exception e) {
			fail("Exception");
		}
		Student stu;
		try {
			stu = db.getStudentByID("901234504");
			String email = stu.getEmail();
			assertEquals(email, "sw@gatech.edu");
		} catch (IOException e) {
			fail("Exception");
		}

	}

	public void testGetStudentsByName1() {
		Student student = null;
		try {
			student = db.getStudentByName("Rastus Kight");
		} catch (Exception e) {
			fail("Exception");
		}
		assertTrue(student.getGtid().compareTo("901234512") == 0);
	}

	public void testGetStudentsByName2() {
		Student student = null;
		try {
			student = db.getStudentByName("Grier Nehling");
		} catch (Exception e) {
			fail("Exception");
		}
		assertEquals(96, student.getAttendance());
	}

	public void testGetStudentsByID() {
		try {
			Student student = db.getStudentByID("901234504");
			assertTrue(student.getName().compareTo("Shevon Wise") == 0);
		} catch (Exception e) {
			fail("Exception");
		}
	}

	public void testGetProject() throws IOException {
		ArrayList<Project> projects = null;
		try {
			projects = db.getProjects();
		} catch (Exception e) {
			fail("Exception");
		}
		assertEquals(3, projects.size());
	}

	public void testGetProject2() throws IOException {
		ArrayList<Project> projects = null;
		try {
			projects = db.getProjects();
		} catch (Exception e) {
			fail("Exception");
		}
		boolean found = false;
		for (Project p : projects) {
			if ((p.getPrjName().compareTo("P1") == 0)
					&& (p.getPrjDesc().compareTo("WordCount in Java") == 0)) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}

	// test case modified due to minor changes in data structure in the Project class
	public void testGetProject3() throws IOException {
		ArrayList<Project> projects = null;
		try {
			projects = db.getProjects();
		} catch (Exception e) {
			fail("Exception");
		}
		boolean found = false;
		for (Project p : projects) {
			HashSet<String> stuNames = p.getPrjTeams().get(0).getStuNames(); 
			if (stuNames.contains("Freddie Catlay")){
				found = true;
				break;
			}
		}
		assertTrue(found);
	}
	
	
	// getProjectByNum method is not needed in the final product
	
//	public void testGetProjectsByNum() {
//		try {
//			Project p = db.getProjectsByNum("P1");
//			assertTrue(p.description.compareTo("WordCount in Java") == 0);
//		} catch (Exception e) {
//			fail("Exception");
//		}
//	}

	public void testGetAssignment() throws IOException {
		ArrayList<Assignment> assignments = null;
		try {
			assignments = db.getAssignments();
		} catch (Exception e) {
			fail("Exception");
		}
		assertEquals(3, assignments.size());
	}

	// test case modified due to minor changes in data structure in the Assignment class
	public void testGetAssignment2() throws IOException {
		ArrayList<Assignment> assignments = null;
		try {
			assignments = db.getAssignments();
		} catch (Exception e) {
			fail("Exception");
		}
			HashMap<String, Integer> stuGrades = assignments.get(0).getStuGrades();
			assertTrue(stuGrades.get("Freddie Catlay") == 100);
		}

// getAssignmentByNum method is not needed in the final product
	
//	public void testGetAssigmentsByNum() {
//		try {
//			Assignment a = db.getAssignmentByNum("Assignment 1");
//			assertEquals(a.assignmentNum,"Assignment 1");
//		} catch (Exception e) {
//			fail("Exception");
//		}
//	}

}
