package edu.gatch;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GradesDB {
	private String fileName;
	private ArrayList<Project> projects;
	private ArrayList<Assignment> assignments;
	private ArrayList<Team> p1Teams;
	private ArrayList<Team> p2Teams;
	private ArrayList<Team> p3Teams;

	

	public GradesDB(String gradesDb) {
		fileName = gradesDb;
	}

	public int getNumStudents() throws IOException {
		int studentNum = -1;
		FileInputStream file = new FileInputStream(new File(fileName));

		// Create Workbook instance holding reference to .xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		// Get first/desired sheet from the workbook
		XSSFSheet sheet = workbook.getSheetAt(0);

		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = sheet.iterator();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if (isRowEmpty(row) == false) {
				studentNum++;
			}
		}

		return studentNum;
	}

	public int getNumAssignments() throws IOException {
		int AssignmentNum = -1;

		FileInputStream file = new FileInputStream(new File(fileName));

		// Create Workbook instance holding reference to .xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		// Get first/desired sheet from the workbook
		XSSFSheet sheet = workbook.getSheetAt(1);

		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = sheet.iterator();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if (isRowEmpty(row) == false) {
				AssignmentNum++;
			}
		}

		return AssignmentNum;
	}

	public int getNumProjects() throws IOException {
		return getNumAssignments();
	}

	public HashSet<Student> getStudents() throws IOException {
		HashSet<Student> students = new HashSet<Student>();
		FileInputStream file = new FileInputStream(new File(fileName));

		// Create Workbook instance holding reference to .xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		// Get first/desired sheet from the workbook
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFSheet sheet2 = workbook.getSheetAt(2);

		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		Iterator<Row> rowIterator2 = sheet2.iterator();
		rowIterator2.next();
		rowIterator2.next();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			// For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();

			Cell cell = cellIterator.next();
			Student stu = new Student();
			stu.setName(cell.getStringCellValue());
			cell = cellIterator.next();
			stu.setGtid(String.valueOf((int) cell.getNumericCellValue()));
			cell = cellIterator.next();
			stu.setEmail(cell.getStringCellValue());

			Row row2 = rowIterator2.next();
			// For each row, iterate through all the columns
			Iterator<Cell> cellIterator2 = row2.cellIterator();

			Cell cell2 = cellIterator2.next();
			cell2 = cellIterator2.next();

			stu.setAttendance((int) (cell2.getNumericCellValue() * 100));

			students.add(stu);
		}
		return students;
	}

	public ArrayList<Assignment> getAssignments() throws IOException {
		assignments = new ArrayList<Assignment>();
		FileInputStream file = new FileInputStream(new File(fileName));
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		// Get first/desired sheet from the workbook
		XSSFSheet sheet1 = workbook.getSheetAt(1);
		XSSFSheet sheet3 = workbook.getSheetAt(3);

		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator1 = sheet1.iterator();
		rowIterator1.next();

		// Set the number, description, a map of student grades, and the average
		// grade for the class for an assignment.
		int counter = 1;
		while (rowIterator1.hasNext()) {
			Row row = rowIterator1.next();
			if (isRowEmpty(row) == false) {
				Assignment assign = new Assignment();
				assign.setAssignNum(counter++);

				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell = cellIterator.next();
				cell = cellIterator.next();
				cell = cellIterator.next();
				cell = cellIterator.next();
				assign.setAssignName(cell.getStringCellValue());
				cell = cellIterator.next();
				assign.setAssignDesc(cell.getStringCellValue());

				HashMap<String, Integer> stuGrades = new HashMap<String, Integer>();
				Iterator<Row> rowIterator3 = sheet3.iterator();
				rowIterator3.next();
				while (rowIterator3.hasNext()) {
					String stuName;
					int grade;
					Row row1 = rowIterator3.next();
					Iterator<Cell> cellIterator1 = row1.cellIterator();
					Cell cell1 = cellIterator1.next();
					stuName = cell1.getStringCellValue();
					for (int i = 0; i < counter; i++) {
						cell1 = cellIterator1.next();
					}
					grade = (int) cell1.getNumericCellValue();
					stuGrades.put(stuName, grade);
				}
				assign.setStuGrades(stuGrades);
				int aveGrade = assign.calculateAssignAveGrade(stuGrades);
				assign.setAssignAveGrade(aveGrade);

				assignments.add(assign);
			}
		}

		return assignments;
	}

	public ArrayList<Team> getP1Teams() throws IOException {
		p1Teams = new ArrayList<Team>();
		FileInputStream file = new FileInputStream(new File(fileName));
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		// Team sheet for project1
		XSSFSheet sheet4 = workbook.getSheetAt(4);

		// Get iterator to all the rows in the P1 Team sheet
		Iterator<Row> rowIterator4 = sheet4.iterator();
		rowIterator4.next();

		while (rowIterator4.hasNext()) {
			Team tm = new Team();
			Row row = rowIterator4.next();
			if (isRowEmpty(row) == false) {
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell = cellIterator.next();
				tm.setTeamNumber(cell.getStringCellValue());
				HashSet<String> stuNames = new HashSet<String>();
				while (cellIterator.hasNext()) {
					cell = cellIterator.next();
					if (isCellEmpty(cell) == false) {
						String stuName = cell.getStringCellValue();
						stuNames.add(stuName);
					}
				}
				tm.setStuNames(stuNames);
				p1Teams.add(tm);
			}
		}
		return p1Teams;
	}

	public ArrayList<Team> getP2Teams() throws IOException {
		p2Teams = new ArrayList<Team>();
		FileInputStream file = new FileInputStream(new File(fileName));
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		// Team sheet for project2
		XSSFSheet sheet5 = workbook.getSheetAt(5);

		// Get iterator to all the rows in the P2 Team sheet
		Iterator<Row> rowIterator5 = sheet5.iterator();
		rowIterator5.next();

		while (rowIterator5.hasNext()) {
			Team tm = new Team();
			Row row = rowIterator5.next();
			if (isRowEmpty(row) == false) {
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell = cellIterator.next();
				tm.setTeamNumber(cell.getStringCellValue());
				HashSet<String> stuNames = new HashSet<String>();
				while (cellIterator.hasNext()) {
					cell = cellIterator.next();
					if (isCellEmpty(cell) == false) {
						String stuName = cell.getStringCellValue();
						stuNames.add(stuName);
					}
				}
				tm.setStuNames(stuNames);
				p2Teams.add(tm);
			}
		}
		return p2Teams;
	}

	public ArrayList<Team> getP3Teams() throws IOException {
		p3Teams = new ArrayList<Team>();
		FileInputStream file = new FileInputStream(new File(fileName));
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		// Team sheet for project3
		XSSFSheet sheet6 = workbook.getSheetAt(6);

		// Get iterator to all the rows in the P3 Team sheet
		Iterator<Row> rowIterator6 = sheet6.iterator();
		rowIterator6.next();

		while (rowIterator6.hasNext()) {
			Row row = rowIterator6.next();
			Team tm = new Team();
			if (isRowEmpty(row) == false) {
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell = cellIterator.next();
				tm.setTeamNumber(cell.getStringCellValue());
				HashSet<String> stuNames = new HashSet<String>();
				while (cellIterator.hasNext()) {
					cell = cellIterator.next();
					if (isCellEmpty(cell) == false) {
						String stuName = cell.getStringCellValue();
						stuNames.add(stuName);
					}
				}
				tm.setStuNames(stuNames);
				p3Teams.add(tm);
			}
		}
		return p3Teams;
	}

	public ArrayList<Project> getProjects() throws IOException {
		projects = new ArrayList<Project>();
		FileInputStream file = new FileInputStream(new File(fileName));
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		// Data sheet for projects
		XSSFSheet sheet1 = workbook.getSheetAt(1);
		// Grades sheets for projects
		XSSFSheet sheet7 = workbook.getSheetAt(7);
		XSSFSheet sheet8 = workbook.getSheetAt(8);
		XSSFSheet sheet9 = workbook.getSheetAt(9);
		// Contribution sheets for projects
		XSSFSheet sheet10 = workbook.getSheetAt(10);
		XSSFSheet sheet11 = workbook.getSheetAt(11);
		XSSFSheet sheet12 = workbook.getSheetAt(12);

		// Get iterator to all the rows in the Data sheet
		Iterator<Row> rowIterator1 = sheet1.iterator();
		rowIterator1.next();

		// Set number, name, and description for the projects
		int counter = 1;
		while (rowIterator1.hasNext() && (counter <= 3)) {
			Project prj = new Project();
			prj.setPrjNum(counter++);
			// prj.setPrjTeams(getP1Teams());
			Row row = rowIterator1.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			Cell cell = cellIterator.next();
			prj.setPrjName(cell.getStringCellValue());
			cell = cellIterator.next();
			prj.setPrjDesc(cell.getStringCellValue());
			projects.add(prj);
		}

		// Set prjTeams for the projects
		projects.get(0).setPrjTeams(getP1Teams());
		projects.get(1).setPrjTeams(getP2Teams());
		projects.get(2).setPrjTeams(getP3Teams());

		// Set teamGrades for the project1
		HashMap<String, Integer> p1TeamGrades = new HashMap<String, Integer>();
		Iterator<Row> rowIterator7 = sheet7.iterator();
		Row row7 = null;
		for (int i = 0; i < sheet7.getLastRowNum(); i++) {
			row7 = rowIterator7.next();
		}
		Iterator<Cell> cellIterator7 = row7.cellIterator();
		Cell cell7 = cellIterator7.next();
		cell7 = cellIterator7.next();
		cell7 = cellIterator7.next();
		p1TeamGrades.put(getP1Teams().get(0).getTeamNumber(),
				(int) cell7.getNumericCellValue());
		cell7 = cellIterator7.next();
		p1TeamGrades.put(getP1Teams().get(1).getTeamNumber(),
				(int) cell7.getNumericCellValue());
		cell7 = cellIterator7.next();
		p1TeamGrades.put(getP1Teams().get(2).getTeamNumber(),
				(int) cell7.getNumericCellValue());
		projects.get(0).setTeamGrades(p1TeamGrades);

		// Set teamGrades for the project2
		HashMap<String, Integer> p2TeamGrades = new HashMap<String, Integer>();
		Iterator<Row> rowIterator8 = sheet8.iterator();
		Row row8 = null;
		for (int i = 0; i < sheet8.getLastRowNum(); i++) {
			row8 = rowIterator8.next();
		}
		Iterator<Cell> cellIterator8 = row8.cellIterator();
		Cell cell8 = cellIterator8.next();
		cell8 = cellIterator8.next();
		cell8 = cellIterator8.next();
		p2TeamGrades.put(getP2Teams().get(0).getTeamNumber(),
				(int) cell8.getNumericCellValue());
		cell8 = cellIterator8.next();
		p2TeamGrades.put(getP2Teams().get(1).getTeamNumber(),
				(int) cell8.getNumericCellValue());
		cell8 = cellIterator8.next();
		p2TeamGrades.put(getP2Teams().get(2).getTeamNumber(),
				(int) cell8.getNumericCellValue());
		projects.get(1).setTeamGrades(p2TeamGrades);

		// Set teamGrades for the project3
		HashMap<String, Integer> p3TeamGrades = new HashMap<String, Integer>();
		Iterator<Row> rowIterator9 = sheet9.iterator();
		Row row9 = null;
		for (int i = 0; i < sheet9.getLastRowNum(); i++) {
			row9 = rowIterator9.next();
		}
		Iterator<Cell> cellIterator9 = row9.cellIterator();
		Cell cell9 = cellIterator9.next();
		cell9 = cellIterator9.next();
		cell9 = cellIterator9.next();
		p3TeamGrades.put(getP1Teams().get(0).getTeamNumber(),
				(int) cell9.getNumericCellValue());
		cell9 = cellIterator9.next();
		p3TeamGrades.put(getP1Teams().get(1).getTeamNumber(),
				(int) cell9.getNumericCellValue());
		cell9 = cellIterator9.next();
		p3TeamGrades.put(getP1Teams().get(2).getTeamNumber(),
				(int) cell9.getNumericCellValue());
		projects.get(2).setTeamGrades(p3TeamGrades);
		
		// Set Average contribution of each student for project1
		HashMap<String, Double> p1StuAveContributions = new HashMap<String, Double>();
		Iterator<Row> rowIterator10 = sheet10.iterator();
		rowIterator10.next();
		while(rowIterator10.hasNext()){
			Row row = rowIterator10.next();
			if (isRowEmpty(row)==false){
				Iterator<Cell> cellIterator10 = row.cellIterator();
				Cell cell10 = cellIterator10.next();
				cell10 = cellIterator10.next();
				String stuName = cell10.getStringCellValue();
				cell10 = cellIterator10.next();
				double contri = (double) cell10.getNumericCellValue();
				// round up to 2 decimal places to match data in excel sheets
				double stuAveContribution = Math.round(contri*100.0)/100.0;
				p1StuAveContributions.put(stuName, stuAveContribution);
				// remove element if key is empty
				p1StuAveContributions.remove("");
			}
		}
		projects.get(0).setPrjStuAveContributions(p1StuAveContributions);
		
		// Set Average contribution of each student for project1
		HashMap<String, Double> p2StuAveContributions = new HashMap<String, Double>();
		Iterator<Row> rowIterator11 = sheet11.iterator();
		rowIterator11.next();
		while(rowIterator11.hasNext()){
			Row row = rowIterator11.next();
			if (isRowEmpty(row)==false){
				Iterator<Cell> cellIterator11 = row.cellIterator();
				Cell cell11 = cellIterator11.next();
				cell11 = cellIterator11.next();
				String stuName = cell11.getStringCellValue();
				cell11 = cellIterator11.next();
				double contri = (double) cell11.getNumericCellValue();
				// round up to 2 decimal places to match data in excel sheets
				double stuAveContribution = Math.round(contri*100.0)/100.0;
				p2StuAveContributions.put(stuName, stuAveContribution);
				// remove element if key is empty
				p2StuAveContributions.remove("");
				
			}
		}
		projects.get(1).setPrjStuAveContributions(p2StuAveContributions);
		
		// Set Average contribution of each student for project1
		HashMap<String, Double> p3StuAveContributions = new HashMap<String, Double>();
		Iterator<Row> rowIterator12 = sheet12.iterator();
		rowIterator12.next();
		while(rowIterator12.hasNext()){
			Row row = rowIterator12.next();
			if (isRowEmpty(row)==false){
				Iterator<Cell> cellIterator12 = row.cellIterator();
				Cell cell12 = cellIterator12.next();
				cell12 = cellIterator12.next();
				String stuName = cell12.getStringCellValue();
				cell12 = cellIterator12.next();
				double contri = (double) cell12.getNumericCellValue();
				// round up to 2 decimal places to match data in excel sheets
				double stuAveContribution = Math.round(contri*100.0)/100.0;
				p3StuAveContributions.put(stuName, stuAveContribution);
				// remove element if key is empty
				p3StuAveContributions.remove("");
			}
		}
		projects.get(2).setPrjStuAveContributions(p3StuAveContributions);
		

		return projects;
	}

	public Student getStudentByName(String string) throws IOException {
		HashSet<Student> students = getStudents();
		Student student = new Student();
		for (Student s : students) {
			if (s.getName().compareTo(string) == 0) {
				student = s;
				break;
			}
		}
		return student;
	}

	public Student getStudentByID(String string) throws IOException {
		HashSet<Student> students = getStudents();
		Student student = new Student();
		for (Student s : students) {
			if (s.getGtid().compareTo(string) == 0) {
				student = s;
				break;
			}
		}
		return student;
	}

	// Check if row is empty is a given sheet
	private boolean isRowEmpty(Row row) {
		for (int c = row.getFirstCellNum(); c <= row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
				return false;
		}
		return true;
	}

	// Check if cell is empty
	private boolean isCellEmpty(Cell cell) {
		if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
			return false;
		} else {
			return true;
		}
	}
	
	public String[] getAllStudentNames() throws IOException{
		HashSet<Student> students = this.getStudents();
		ArrayList<String> names = new ArrayList<String>();
		for (Student s : students){
			names.add(s.getName());
		}
		String[] stuNames = new String[names.size()];
		for (int i = 0; i < stuNames.length; i++) {
			stuNames[i]=names.get(i);
		}		
		return stuNames;			
	}
	
	public String[] getAllStudentGtids() throws IOException{
		HashSet<Student> students = this.getStudents();
		ArrayList<String> ids = new ArrayList<String>();
		for (Student s : students){
			ids.add(s.getGtid());
		}
		String[] stuGtids = new String[ids.size()];
		for (int i = 0; i < stuGtids.length; i++) {
			stuGtids[i]=ids.get(i);
		}	
		
		return stuGtids;			
	}
	

}