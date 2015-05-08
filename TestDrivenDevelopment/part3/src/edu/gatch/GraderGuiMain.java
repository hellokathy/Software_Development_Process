package edu.gatch;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFileChooser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;


/**
 * @author      Andy Yancheng Liu (yliu723@gatech.edu)
 * @version     1.0               
 * @since       2014-05-11 
 */
public class GraderGuiMain {
	/**
	 * Launch the application.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		GradesDB db = new GradesDB(Constants.GRADES_DB);

		Display display = Display.getDefault();
		final Shell shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Grade and info");

		final Label lblStudent = new Label(shell, SWT.NONE);
		lblStudent.setBounds(0, 29, 146, 15);
		lblStudent.setText("Choose a student by Name:");

		final StyledText styledText = new StyledText(shell, SWT.BORDER
				| SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		styledText.setBounds(154, 10, 280, 242);
		
		final Combo combo_name = new Combo(shell, SWT.READ_ONLY);
		combo_name.setBounds(10, 50, 121, 23);
		String[] items = db.getAllStudentNames();// add student names
		combo_name.setItems(items);
		
		final Combo combo_id = new Combo(shell, SWT.READ_ONLY);
		combo_id.setBounds(10, 114, 121, 23);
		String[] ids = db.getAllStudentGtids();// add student gtids
		combo_id.setItems(ids);
		
		// Choose to display student information by his/her name
		combo_name.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String selectedStuName = combo_name.getText();
				GradesDB db = new GradesDB(Constants.GRADES_DB);
				Student stu = null;
				try {
					stu = db.getStudentByName(selectedStuName);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String selectedStuId = stu.getGtid();
				combo_id.setText(selectedStuId);
				
				String display = "";
				try {
					display = this.getStudentInfo(selectedStuName);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				styledText.setText(display);

			}

			// Get information for the selected student from Grades database
			private String getStudentInfo(String selectedStuName)
					throws IOException {
				String name = selectedStuName;
				String gtid;
				String email;
				int attendance;

				GradesDB db = new GradesDB(Constants.GRADES_DB);

				Student stu = db.getStudentByName(name);
				gtid = stu.getGtid();
				email = stu.getEmail();
				attendance = stu.getAttendance();

				String output = "Basic Information: " + "\r\n" + "\r\n"
						+ " Name: " + name + "\r\n" + " GTID: " + gtid + "\r\n"
						+ " Email: " + email + "\r\n" + " Attendance: "
						+ attendance + "\r\n" + "\r\n"
						+ "Assignment Information: " + "\r\n" + "\r\n";

				ArrayList<Assignment> assignments = db.getAssignments();
				for (Assignment a : assignments) {

					HashMap<String, Integer> grades = a.getStuGrades();
					int grade = grades.get(name);

					output += "-" + a.getAssignName() + ": "
							+ a.getAssignDesc() + "\r\n"
							+ " Average grade for the class: "
							+ a.getAssignAveGrade() + "\r\n " + name
							+ "'s grade: " + grade + "\r\n";
				}

				output += "\r\n" + "Project Information: " + "\r\n";

				ArrayList<Project> projects = db.getProjects();
				for (Project p : projects) {

					output += "-" + p.getPrjName() + ": " + p.getPrjDesc()
							+ "\r\n" + " Average grade across teams: "
							+ p.calculatePrjAveGrade(p.getTeamGrades())
							+ "\r\n " + name + " is in "
							+ p.getTeamByStuName(name).getTeamNumber()
							+ "\r\n " + name + "'s team grade is "
							+ p.getTeamGradeByStuName(name) + "\r\n"
							+ " Average contribution of " + name + ": "
							+ p.getStuAveContributionByStuName(name) + "\r\n";
				}

				return output;
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				styledText.setText("set default");

			}
		});
		

		// Choose to display student information by his/her gtid
		combo_id.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				String selectedStuGtid = combo_id.getText();
				GradesDB db = new GradesDB(Constants.GRADES_DB);
				Student stu = null;
				try {
					stu = db.getStudentByID(selectedStuGtid);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String selectedStuName = stu.getName();
				combo_name.setText(selectedStuName);

				String display = "";
				try {
					display = this.getStudentInfo(selectedStuGtid);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				styledText.setText(display);
				
			}
			
			private String getStudentInfo(String selectedStuGtid) throws IOException {
				String name;
				String gtid= selectedStuGtid;
				String email;
				int attendance;

				GradesDB db = new GradesDB(Constants.GRADES_DB);

				Student stu = db.getStudentByID(gtid);
				name = stu.getName();
				email = stu.getEmail();
				attendance = stu.getAttendance();

				String output = "Basic Information: " + "\r\n" + "\r\n"
						+ " Name: " + name + "\r\n" + " GTID: " + gtid + "\r\n"
						+ " Email: " + email + "\r\n" + " Attendance: "
						+ attendance + "\r\n" + "\r\n"
						+ "Assignment Information: " + "\r\n" + "\r\n";

				ArrayList<Assignment> assignments = db.getAssignments();
				for (Assignment a : assignments) {

					HashMap<String, Integer> grades = a.getStuGrades();
					int grade = grades.get(name);

					output += "-" + a.getAssignName() + ": "
							+ a.getAssignDesc() + "\r\n"
							+ " Average grade for the class: "
							+ a.getAssignAveGrade() + "\r\n " + name
							+ "'s grade: " + grade + "\r\n";
				}

				output += "\r\n" + "Project Information: " + "\r\n";

				ArrayList<Project> projects = db.getProjects();
				for (Project p : projects) {

					output += "-" + p.getPrjName() + ": " + p.getPrjDesc()
							+ "\r\n" + " Average grade across teams: "
							+ p.calculatePrjAveGrade(p.getTeamGrades())
							+ "\r\n " + name + " is in "
							+ p.getTeamByStuName(name).getTeamNumber()
							+ "\r\n " + name + "'s team grade is "
							+ p.getTeamGradeByStuName(name) + "\r\n"
							+ " Average contribution of " + name + ": "
							+ p.getStuAveContributionByStuName(name) + "\r\n";
				}

				return output;
			
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		Button getInfo = new Button(shell, SWT.NONE);
		getInfo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					this.output();
					Label lblNewLabel = new Label(shell, SWT.NONE);
					lblNewLabel.setBounds(15, 198, 120, 15);
					lblNewLabel.setText("Information Exported");

				} catch (Exception exp) {
				}
			}

			// Output information for the selected student
			private void output() {
				
				final JFileChooser SaveAs = new JFileChooser();
				SaveAs.setApproveButtonText("Save");
				int actionDialog = SaveAs.showSaveDialog(SaveAs);
				if (actionDialog != JFileChooser.APPROVE_OPTION) {
					return;
				}

				FileWriter fileWriter = null;

				try {
					String content = styledText.getText();
					File newTextFile = new File(SaveAs.getSelectedFile() + ".txt");
					fileWriter = new FileWriter(newTextFile);
					fileWriter.write(content);
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		getInfo.setBounds(10, 165, 121, 23);
		getInfo.setText("Export as txt file");
		
		Label lblChooseAStudent = new Label(shell, SWT.NONE);
		lblChooseAStudent.setBounds(10, 93, 131, 15);
		lblChooseAStudent.setText("Choose a student by ID:");
		


		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}