package com.bridgelabz.EmpPayrollStoreInFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {

	static String path = "D:/Eclipse_LFP_112/Day_27_JavaFileIO/AllFiles/Payroll.txt";

	enum IOStream {
		Text_File_Name(path);

		final String fileName;

		private IOStream(String fileName) {
			this.fileName = fileName;
		}

		public String getConstant() {
			return fileName;
		}
	}

	static Scanner sc;
	List<Payroll> payrollList;
	IOStream ioStream;

	public EmployeePayrollService(IOStream ioStream) {
		payrollList = new ArrayList<Payroll>();
		this.ioStream = ioStream;
	}

	public void createFileAndAddEmployeeService() {
		EmployeePayrollService service = new EmployeePayrollService(IOStream.Text_File_Name);
		File file = new File(IOStream.Text_File_Name.getConstant());
		try {
			if (file.createNewFile()) {
				System.out.println("New file is added successfully!!!");
				service.readEmployeePayrollData();
			} else {
				System.out.println("Data is adding an existing file...");
				service.readEmployeePayrollData();
			}
			Path path = Paths.get(IOStream.Text_File_Name.getConstant());
			writeFile(path, service.getListOfPayroll().toString());
		} catch (IOException e) {
			e.getMessage();
		}
	}

	public void readEmployeePayrollData() {
		sc = new Scanner(System.in);
		Payroll payroll = new Payroll();
		int id = (int) (Math.random() * 900 + 100);
		payroll.setId(id);
		System.out.println("Enter the employee name : ");
		payroll.setName(sc.nextLine());
		System.out.println("Enter the employee salary : ");
		payroll.setSalary(sc.nextDouble());
		payrollList.add(payroll);
		System.out.println("Employee added successfully.!!!");
	}

	public List<Payroll> getListOfPayroll() throws NoSuchFileException {
		try {
			String payroll = Files.readString(Paths.get(IOStream.Text_File_Name.getConstant()));
			System.out.println(payroll);
		} catch (IOException e) {
			throw new NoSuchFileException("File not found in that directory!!!");
		}
		return payrollList;
	}

	public static void writeFile(Path path, String content) {
		try {
			Files.write(path, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("***************** Employee Payroll Service *****************");
		EmployeePayrollService service = new EmployeePayrollService(IOStream.Text_File_Name);

		boolean exit = false;
		while (!exit) {
			System.out.println("<------------------------------------------------------->");
			System.out.println("1. Add Employee\t 2. Show Employees\t 3. Exit");
			System.out.println("<-----------------Choose your options----------------->");
			sc = new Scanner(System.in);
			int option = sc.nextInt();
			System.out.println("<------------------------------------------------------->");
			switch (option) {
			case 1:
				System.out.println("<-----------------Create file and add employee----------------->");
				service.createFileAndAddEmployeeService();
				break;
			case 2:
				System.out.println("<-----------------Show Employees from Payroll.txt----------------->");
				try {
					service.getListOfPayroll();
				} catch (NoSuchFileException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				exit = true;
				System.out.println("***************Thank you***************");
				break;
			default:
				System.out.println("Invalid option.Please try again.!!!");
			}
		}
	}
}
