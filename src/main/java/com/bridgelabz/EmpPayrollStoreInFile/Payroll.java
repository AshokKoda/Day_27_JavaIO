package com.bridgelabz.EmpPayrollStoreInFile;

public class Payroll {

	int id;
	String name;
	double salary;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Payroll [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}

}
