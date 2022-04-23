package com.bridgelabz.employeePayrollService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {

	public enum IOStream {
		CONSOLE_IO, FILE_IO, DATABASE_IO, REST_IO;
	}

	static Scanner sc;
	static List<Employee> employeeList;
	IOStream ioStream;

	public EmployeePayrollService(IOStream ioStream) {
		employeeList = new ArrayList<Employee>();
		this.ioStream = ioStream;
	}

	public static void main(String[] args) {
		EmployeePayrollService service = new EmployeePayrollService(IOStream.CONSOLE_IO);
		sc = new Scanner(System.in);
		if (service.ioStream.equals(IOStream.CONSOLE_IO)) {
			boolean exit = false;
			while (!exit) {
				System.out.println("**************** Employee Payroll Service ****************");
				System.out.println("1. Add Employee\t 2. Show Employees\t 3. Exit");
				System.out.println("<-----------------Choose your options----------------->");
				int option = sc.nextInt();
				System.out.println("<------------------------------------------------------->");
				switch (option) {
				case 1:
					System.out.println("<-----------------Add Employee----------------->");
					service.readEmployeePayrollData();
					break;
				case 2:
					System.out.println("<-----------------Show Employees----------------->");
					service.writeEmployeePayrollData(employeeList);
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

	public void readEmployeePayrollData() {
		sc = new Scanner(System.in);
		if (ioStream.equals(IOStream.CONSOLE_IO)) {
			Employee emp = new Employee();
			int id = (int) (Math.random() * 900 + 100);
			emp.setId(id);
			System.out.println("Enter the employee name : ");
			emp.setName(sc.nextLine());
			System.out.println("Enter the employee salary : ");
			emp.setSalary(sc.nextDouble());
			employeeList.add(emp);
			System.out.println("Employee added successfully.!!!");
		}
	}

	public void writeEmployeePayrollData(List<Employee> list) {
		if (list.isEmpty()) {
			System.out.println("No records found.!!!");
		} else {
			for (Employee employee : list) {
				System.out.println(employee);
			}
		}
	}

	public List<Employee> printList() {
		for (Employee e : employeeList) {
			System.out.println(e);
		}
		return employeeList;

	}

	public int countEntries() {
		String path = "D:/Eclipse_LFP_112/Day_27_JavaFileIO/AllFiles/Payroll.txt";
		int count = 0;
		try {
			count = (int) Files.lines(Paths.get(path)).count();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return count;
	}

}
