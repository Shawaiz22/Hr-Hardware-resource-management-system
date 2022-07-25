package hr_hardware_resources_management_system;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		
		// This is the main class from where the execution of program starts.
		
	
	Scanner sc= new Scanner(System.in);	
	MachineService machineService= new MachineService();
	EmployeeService employeeService = new EmployeeService();
	MachineBookingService mbs= new MachineBookingService();
	
	while(true) {
		try {
			System.out.println("1. Enter new Machine information");
			System.out.println("2. Register new Employee");
			System.out.println("3. View Machine details");
			System.out.println("4. View Employee information");
			System.out.println("5. Book a Machine for an Employee");
			System.out.println("6. Return Machine");
			System.out.println("7. View machine Bookings");
			System.out.println("8. Update Resignation/Retirement Status of Employee");
			System.out.println("9. Update the existing machine information");
			System.out.println("10. View History of old Machine");
			System.out.println("11. Update employee information");
			System.out.println("12. Show report of employees and booked machines");
			System.out.println("13. Exit the program***");
			
			System.out.printf("\n\n Enter your choice(1/2/3/4/5/6/7/8/9/10/11/12/13): ");
			int choice=sc.nextInt();
			
			switch(choice) {
			
					case 1:
						System.out.println(machineService.insert());
						break;
					case 2:
						System.out.println(employeeService.insert());
						break;
					case 3:	
						System.out.println("MACHINE INFORMATION***");
						System.out.println("=============================================================================");
						System.out.println(machineService.getAllMachines());
						System.out.println("=============================================================================");
						break;
					case 4:
						System.out.println("EMPLOYEE INFORMATION***");
						System.out.println("=============================================================================");
						System.out.println(employeeService.getAllEmployees());						
						System.out.println("=============================================================================");
						break;
					case 5:
						System.out.println(mbs.insert());
						break;
					case 6:
						System.out.println(mbs.machineReturned());
						break;
					case 7:
						System.out.println("BOOKINGS***");
						System.out.println("=============================================================================");
						System.out.println(mbs.getAllMachineBookings());
						System.out.println("=============================================================================");
						break;
					case 8:
						System.out.println(employeeService.employeeRetirementResign());
						break;
					case 9:
						System.out.println(machineService.upgradeExistingMachine());
						break;
					case 10:
						System.out.println("MACHINE_HISTORY***");
						System.out.println("=============================================================================");
						System.out.println(machineService.getMachineHistory());
						System.out.println("=============================================================================");
						break;
					case 11:
						System.out.println(employeeService.updateEmployee());
						break;
					case 12:
						System.out.println("REPORT***");
						System.out.println("=============================================================================");
						System.out.println(machineService.machineReport());
						System.out.println("=============================================================================");
						break;
					case 13:	
						System.out.println("Thanks for using my Software***");
						System.exit(0);
					default:
						System.out.println("Wrong choice entered!!!");
						break;
					}
					

		}
		
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
}
}

/*
    This is a console application which is used for managing the hardware resources for the employees
 *  
  Program coded by: Shawaiz Siddiqui
  					shawaizsidd22@gmail.com
  					github: Shawaiz22
*/