package hr_hardware_resources_management_system;
import java.util.*;

public class MachineBookingService {
	
	ClassDb cd= new ClassDb();
	
	// Function for inserting booking details of machines by employees(It is a transaction table which adds record each booking)
	
	public String insert() {
		
		String result1="";
		String result2="";
		String res1="";
		String res2="";
		boolean res3=false;
	    
	    MachineBooking mb= new MachineBooking();
	    
	    
	    try {
	    	Scanner sc= new Scanner(System.in);
	        
	        System.out.print("Enter employee id to book a machine: ");
	        mb.setEmp_id(sc.nextInt());
	        System.out.print("Enter machine id to book a machine: ");
	        mb.setMachine_id(sc.nextInt());
	        
	        String selectquery= "select currentstatus from employee where emp_id= "+mb.getEmp_id();
	        res1=cd.getCurrentStatus(selectquery);
	        
	        selectquery= "select currentstatus from machinebooking where emp_id= "+mb.getEmp_id()+
	        		" and currentstatus='active'";
	        res3=cd.checkRecordExists(selectquery);
	        if(res3==true) {
	        	
	        return "Employee already booked with device!";
	        }
	        
	        selectquery= "select currentstatus from machine where id= "+mb.getMachine_id();
	        res2=cd.getCurrentStatus(selectquery);
	        
	        if(res1.equals("employed") && res2.equals("available") && res3==false )  {
	        	String query="insert into machinebooking(emp_id, machine_id, currentstatus) values("+mb.getEmp_id()+
	        			", "+mb.getMachine_id()+", "+"'active')";
	        	result1=cd.dmlQuery(query);
	        	
	        	query="update machine set currentstatus='booked' where id= "+mb.getMachine_id();
	        	result2=cd.dmlQuery(query);
	        	System.out.println();
	        }
	        else {System.out.println("\nInvalid Id or status of the employee is: "+res1);
	        	  System.out.println("Invalid Id or status of the machine is: "+res2);
	        }
	        
	        
	    }
	    catch(Exception e) {
	    	result1=e.toString();
	    }
	    return "\n Machine Booked to Employee!! "+"\n Machine status updated in machine table";
	}
	
	// Displays the full booking information
	
	public String getAllMachineBookings() {
		
		String res="";
		try {
			String query="select * from machinebooking";
			System.out.println("Book-ID Emp-ID Machine-ID Status");
			res=cd.selectQuery(query);
		}
		catch(Exception e) {
			res=e.toString();
		}
		return res;
	}
	
	// Updates status if a employee returns machine
	public String machineReturned() {
		
		MachineBooking mb1 = new MachineBooking();
		boolean res=false;
		String res1="";
		String res2="";
		try {
			Scanner sca= new Scanner(System.in);
		System.out.print("Enter Employee id to return machine: ");
		mb1.setEmp_id(sca.nextInt());
		
		String selectquery="select * from machinebooking where emp_id="+mb1.getEmp_id()+" and currentstatus='active'";
			res=cd.checkRecordExists(selectquery);
			
			if(res==true) {
				 selectquery="select * from machinebooking where emp_id="+mb1.getEmp_id()+" and currentstatus='active'";
				 int machine_id=cd.getIdByQuery(selectquery, "machine_id");
				 
				 String query="update machinebooking set currentstatus='closed' where emp_id="+mb1.getEmp_id()+
						 " and machine_id="+machine_id;
				 res1=cd.dmlQuery(query);
				 System.out.println("Machine returned by employee!");
				 
				 query="update machine set currentstatus='available' where id="+machine_id;
				 res2=cd.dmlQuery(query);
				 System.out.println("Machine Status Updated!");
			}
			
			
		}
		catch(Exception e) {
			res1=e.toString();
		}
		return res1;
	}
	

}
