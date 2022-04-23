package com.bridgelabz.employeePayrollService;

import java.util.Arrays;

import org.junit.Test;

import com.bridgelabz.employeePayrollService.Employee;
import com.bridgelabz.employeePayrollService.EmployeePayrollService.IOStream;

public class EmployeePayrollTest {

	@Test
	public void givenEntriesWriteToFile() {
		int empId1 = (int) (Math.random() * 900 + 100);
		int empId2 = (int) (Math.random() * 900 + 100);
		int empId3 = (int) (Math.random() * 900 + 100);

		Employee[] emps = new Employee[] { new Employee(empId1, "Monday", 10000),
				new Employee(empId2, "Tuesday", 15000), new Employee(empId3, "Wednesday", 20000), };

		EmployeePayrollService service = new EmployeePayrollService(IOStream.FILE_IO);
		service.writeEmployeePayrollData(Arrays.asList(emps));
		service.printList();
	}
}
