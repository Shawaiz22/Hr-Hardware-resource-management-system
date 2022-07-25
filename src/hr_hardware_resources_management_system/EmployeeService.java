package hr_hardware_resources_management_system;
import java.io.*;
import java.util.*;

public class EmployeeService {
	
	ClassDb classDb= new ClassDb();
	Scanner sc=new Scanner(System.in);
	BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	
	// Function for registering a new employee
	
	public String insert() {
		String res="";
		
		Employee e= new Employee();
		try {
			System.out.print("Enter Employee Name: ");
			e.setName(sc.nextLine());
			System.out.print("Enter Employee Address: ");
			e.setAddress(sc.nextLine());
			System.out.print("Enter Employee Age: ");
			e.setAge(sc.nextInt());
			System.out.print("Enter Employee MobileNo: ");
			e.setMobileNo(sc.next());

			
			String query="insert into employee(name, age, address, mobileNo, currentstatus) "+
					" values('"+e.getName()+"', "+e.getAge()+", '"+e.getAddress()+"', '"+e.getMobileNo()+"', "+
					" 'employed')";
			
			res=classDb.dmlQuery(query);
			System.out.println();
					
		}
		catch(Exception e1) {
			res=e1.toString();
		}
		
		return "New Employee registered";
	}
	
	// Function for displaying information of all employees:
	
	public String getAllEmployees() {
		
		String res="";
		try {
		String query = "select * from employee";
		System.out.println("Emp-ID \t\tName \tAge \t\tAddress     \tMob.No \tStatus");
		res=classDb.selectQuery(query);
		}
		catch(Exception e) {
			res=e.toString();
		}
		return res;
	}
	
	// For setting the status of the employee if he/she retires/resigns
	public String employeeRetirementResign() {
		
		Employee e1= new Employee();
		
		String status;
		String res="";
		boolean res1=false;
		boolean res2=false;
		
		try {
			
			System.out.print("Enter employee id to update the resignation/retirement status of employee: ");
			e1.setEmp_id(sc.nextInt());
			
			String selectquery="select * from employee where emp_id="+e1.getEmp_id()+" and currentstatus='employed'";
			res1=classDb.checkRecordExists(selectquery);
			
			if(res1==true) {
				
				selectquery= "select * from machinebooking where emp_id="+e1.getEmp_id()+" and currentstatus='active'";
				res2=classDb.checkRecordExists(selectquery);
				
				if(res2==true) {
					
					System.out.println("First return the machine alloted to the employee!");
				}
				
				else {
					
					System.out.print("Has the employee resigned or he got retired?(enter status: 'resigned' or 'retired'): ");
					status=sc.next();
					
					//can be written in a single query
						if(status.equals("resigned")) {
					String query="update employee set currentstatus='*resigned' where emp_id="+e1.getEmp_id();
					res=classDb.dmlQuery(query);
					System.out.println("Employee status updated!!!");
						}
					else if(status.equals("retired")) {
					String query="update employee set currentstatus='*retired' where emp_id="+e1.getEmp_id();
					res=classDb.dmlQuery(query);
					System.out.println("Employee status updated!!!");
						}
					else {System.out.println("Invalid Entry!!!");}
					
				}	
				
			}
			
			else {System.out.println("The employee has already resigned/retired");}
		}
		catch(Exception e) {res=e.toString();}
		
		return res;
	}
	
	// Function for updating employee detalils(like mobileno, adress, age)
	public String updateEmployee() {
		
			 
		try {
			System.out.println("Enter employee id to update his information: ");
			int emp_id=sc.nextInt();
			
			String selectquery="select * from employee where emp_id="+emp_id+" and currentstatus=employed";
			boolean res=classDb.checkRecordExists(selectquery);
	
		if(res==true) {	
			System.out.println("Which information do you want to update? (address/mobile_no/age): ");
			String info=br.readLine();
			
			if(info=="address") {
				System.out.print("Enter new employee address: ");
				String address=br.readLine();
				String query="update employee set adress='"+address+"' where emp_id="+emp_id;
				classDb.dmlQuery(query);
				return "\nEmployee address updated";
			}
			else if(info=="mobile_no") {
				System.out.print("Enter new mobile_no: ");
				String mobile_no=sc.next();
				String query="update employee set mobileNo='"+mobile_no+"' where emp_id="+emp_id;
				classDb.dmlQuery(query);
				return "\nEmployee's mobile number updated";
			}
			else if(info=="age") {
				System.out.print("Enter new age: ");
				int age=sc.nextInt();
				String query="\nupdate employee set age="+age+" where emp_id="+emp_id;
				classDb.dmlQuery(query);
				return "\nEmployee's age updated";
			}
			else {
				return"\nInvalid Entry!!";
			}
		}
		else {
			return "\nEmployee has resigned/retired so, you cannot update his/her details";
		}
		
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return "Informations updated, you may check them in the table";
	}
	
}
