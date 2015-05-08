package edu.gatech.tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;

import junit.framework.TestCase;

import org.junit.Test;

import edu.gatch.Constants;
import edu.gatch.GradesDB;
import edu.gatch.Student;


public class StudentTest extends TestCase {
	Student stu = null;
	GradesDB db = null;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	protected void setUp() throws Exception {
		stu = new Student();
		db = new GradesDB(Constants.GRADES_DB);
		System.setOut(new PrintStream(outContent));
		super.setUp();
	}

	protected void tearDown() throws Exception {
		System.setOut(null);
		super.tearDown();
	}
	
/*
 * 	Tests for Team and contribution moved to ProjectTest class
 */
//	@Test
//	public void testGetTeam() {
//		try {
//			stu = db.getStudentByID("901234504");
//
//			String[] teams = stu.getTeam();
//			assertEquals(teams[0], "Team 1");
//		} catch (Exception e) {
//			fail("Exception");
//		}
//	}
//
//	public void testGetContribution() throws IOException {
//		try {
//			stu = db.getStudentByID("901234504");
//
//			float[] contribution = stu.getContribution();
//			assertEquals(contribution[0], 9.25, 0.0001);
//		} catch (Exception e) {
//			fail("Exception");
//		}
//	}

	
/*
 * 	Tests for Output is part of GUI, thus is optional
 */
//	public void testOutput() throws IOException {
//		stu = db.getStudentByID("901234504");
//		stu.Output();
//
//		FileReader fr = new FileReader("C:\\1.txt");
//		BufferedReader br = new BufferedReader(fr);
//		String name1 = br.readLine();
//		br.close();
//		assertEquals("student name: Shevon Wise", "student name: " + name1);
//	}

}
