package com.bridgelabz.EmpPayrollStoreInFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bridgelabz.EmpPayrollStoreInFile.Test.JsonFiles;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EmployeePayrollService {

	static Scanner sc;
	static String path = "D:/Eclipse_LFP_112/Day_27_JavaFileIO/AllFiles/Payroll.json";
	List<Payroll> payrollList;

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

	public EmployeePayrollService(IOStream ioStream) {
		payrollList = new ArrayList<Payroll>();
	}

	public void createFileAndAddEmployeeService() {
		EmployeePayrollService service = new EmployeePayrollService(IOStream.Text_File_Name);
		File file = new File(IOStream.Text_File_Name.getConstant());
		try {
			if (file.createNewFile()) {
				System.out.println("New file is added successfully!!!");
				service.addEmployees();
			} else {
				System.out.println("Data is adding an existing file...");
				service.addEmployees();
			}
			System.out.println("JSON Data Read: ");
			readJsonFile();
		} catch (IOException e) {
			e.getMessage();
		}
	}
	
	public void addEmployees() {
		System.out.println("---------------------Add Employee--------------------");
		sc = new Scanner(System.in);
		Payroll payroll = new Payroll();
		int id = (int) (Math.random() * 900 + 100);
		payroll.setId(id);
		System.out.println("Enter the employee name : ");
		String eName = sc.nextLine();
		payroll.setName(eName);
		System.out.println("Enter the employee salary : ");
		double salary = sc.nextDouble();
		payroll.setSalary(salary);
		payrollList.add(payroll);
		
		System.out.println("JSON Data Write: ");
		writeJsonFile();
		System.out.println("Added Success!!!");
		System.out.println("--------------------------------------------------------------");
	}
	
	public void writeJsonFile() {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonString = gson.toJson(payrollList);
			System.out.println("Json data: " + jsonString);
			FileWriter writer = new FileWriter(JsonFiles.Json_File_Name.getConstant(), true);
			writer.write(jsonString);
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void readJsonFile() {
		String path = "D:/Eclipse_LFP_112/Day_27_JavaFileIO/AllFiles/Payroll.json";
		try {
			List<String> lines = Files.readAllLines(Paths.get(path),
			        StandardCharsets.UTF_8);
			lines.forEach(System.out::println);
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
			System.out.println("1. Add Employee \t0. Exit");
			System.out.println("<-----------------Choose your options----------------->");
			sc = new Scanner(System.in);
			int option = sc.nextInt();
			System.out.println("<------------------------------------------------------->");
			switch (option) {
			case 1:
				service.createFileAndAddEmployeeService();
				break;
			case 0:
				exit = true;
				System.out.println("***************Thank you***************");
				break;
			default:
				System.out.println("Invalid option.Please try again.!!!");
			}
		}
	}
}
