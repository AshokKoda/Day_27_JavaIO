package com.bridgelabz.employeePayrollService;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Scanner;

public class JavaIOPrograms {

	static Scanner sc;
	static String path = "D:/Eclipse_LFP_112/Day_27_JavaFileIO/AllFiles";

	public void createNewFilesWithUserInput() {
		sc = new Scanner(System.in);
		System.out.print("Enter the desired name of your file: "); // Enter the filename with .extension
		String fileName = sc.nextLine();
		// fileName = fileName + ".txt";

		File file = new File(path);
		File actualFile = new File(file, fileName);
		try {
			if (actualFile.createNewFile()) {
				System.out.println("File created.");
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showAllFiles() {

		File directoryPath = new File(path);
		for (File file : directoryPath.listFiles()) {
			System.out.println(file.getName());
		}

		System.out.println("----------Total Files Count----------");
		int count = countFilesInDirectory(directoryPath);
		System.out.println("Total Files: " + count);

		System.out.println("\n<------------Show Text Files------------>");
		File[] txtFiles = directoryPath.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});

		for (File file : txtFiles) {
			System.out.println(file.getName());
		}

	}

	public void deleteFiles() {
		System.out.println("<-------------Delete Files and Directories------------->");
		File directoryPath = new File(path);
		for (File file : directoryPath.listFiles()) {
			System.out.println(file.getName());
		}

		sc = new Scanner(System.in);
		System.out.print("Enter the Filename to delete: ");
		String fileName = sc.nextLine();

		File file = new File(path);
		File actualFile = new File(file, fileName);

		if (actualFile.delete()) {
			System.out.println("File is deleted successfully...");
		} else {
			System.out.println("File is not found.!!!");
		}
	}

	public void createDirectories() {
		sc = new Scanner(System.in);
		System.out.print("Enter the name to create a directory: ");
		String directoryName = sc.nextLine();

		File file = new File(path);
		File actualFile = new File(file, directoryName);

		if (actualFile.mkdir()) {
			System.out.println("Directory is created successfully...");
		} else {
			System.out.println("Directory name is already existed.!!!");
		}
	}

	public int countFilesInDirectory(File directory) { // UC3
		int count = 0;
		for (File file : directory.listFiles()) {
			if (file.isFile()) {
				count++;
			}
			if (file.isDirectory()) {
				count += countFilesInDirectory(file);
			}
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println("***************** JAVA IO *****************");
		boolean exit = false;
		Scanner sc = new Scanner(System.in);
		JavaIOPrograms services = new JavaIOPrograms();

		while (!exit) {
			System.out.println("<----------------------------------------------------------->");
			System.out
					.println("1.Create New File\t 2.Show All Files\t 3.Delete Files\t 4.Create New Directory\t 5.Quit");
			System.out.println("<--------------------Choose your options-------------------->");
			int option = sc.nextInt();

			switch (option) {
			case 1:
				System.out.println("<-------------Create New File------------->");
				services.createNewFilesWithUserInput();
				break;
			case 2:
				System.out.println("<-------------Show All Files------------->");
				services.showAllFiles();
				break;
			case 3:
				System.out.println("<-------------Delete Files------------->");
				services.deleteFiles();
				break;
			case 4:
				System.out.println("<-------------Create New Directory------------->");
				services.createDirectories();
				break;
			case 5:
				exit = true;
				System.out.println("********Thank You********");
				break;
			default:
				System.out.println("Invalid option.Please try again!!!");
			}
		}

		sc.close();

	}

}
