package hr_hardware_resources_management_system;
import java.util.*;
import java.io.*;

public class MachineService {

	private ClassDb classDb= new ClassDb();

	//function for inserting the new records of machine 
	
	public String insert() {
		String res="";
		Machine m=new Machine();
		
		try {
			Scanner sc=new Scanner(System.in);
			System.out.print("Enter machine Brand: ");
			m.setMachinebrand(sc.nextLine());
			System.out.print("Enter machine Model: ");
			m.setMachinemodel(sc.nextLine());
			System.out.print("Enter machine ram details: ");
			m.setRam(sc.nextLine());
			System.out.print("Enter machine rom details: ");
			m.setRom(sc.nextLine());
			System.out.print("Enter machine processor details: ");
			m.setProcessor(sc.nextLine());
			
			String query="insert into machine(machine_brand, machine_model, ram, rom, processor, currentstatus)"+
					" values("+" '"+m.getMachinebrand()+"', '"+m.getMachinemodel()+"', '"+m.getRam()+"', '"+m.getRom()+
					"', '"+m.getProcessor()+"', '"+"available' )";
			System.out.println("New Machine Information Registered Succesfully");
		
			
			//insert into machine(ram,rom,processor) values('<value>','<value>','<value>')
			// above query is written like this ^^
			
			      res=classDb.dmlQuery(query);
		}
		
		catch(Exception e) {
			res=e.toString();
		}
		return res;
		
	}
	
	// This function displays the information of all machines
	
	public String getAllMachines() {
		String res="";
		try {

		String query="select * from machine";
		System.out.println("M/c-ID M/c-Brand M/c-Model \tM/c-Ram M/c-Rom Processor Status");
		res=classDb.selectQuery(query);
		}
		catch(Exception e) {
			res=e.toString();
		}
		return res;
	}
	
	
	
	// This function is used for upgrading the machine configurations 
	
	public String upgradeExistingMachine() {
		
		Machine mac= new Machine();
		MachineHistory machinehistory= new MachineHistory();
		Scanner scan= new Scanner(System.in);
		
		boolean res=false;
		String update="";
		String insert="";
		
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			
			System.out.print("Enter the machine id to update the machine information: ");
			int machineid=Integer.parseInt(br.readLine());
			
			String selectquery="select * from machine where id="+machineid;
			res=classDb.checkRecordExists(selectquery);
			if(res==true) {
				
				Map<String, String> map= classDb.getOneRecordValue(selectquery);   // passing the query to get all machine details from machine table 
																				   					
				machinehistory.setMachine_id(machineid);     // now getting values according to column by passing column name as argument
				
				String mc_brand=map.get("machine_brand");
				machinehistory.setMachine_brand(mc_brand);
				
				String mc_model=map.get("machine_model");
				machinehistory.setMachine_model(mc_model);

				String mc_ram=map.get("ram");
				machinehistory.setRam(mc_ram);

				String mc_rom=map.get("rom");
				machinehistory.setRom(mc_rom);
				
				String mc_processor=map.get("processor");
				machinehistory.setProcessor(mc_processor);
				
				
				
				// input for new(updated) machine configurations
				
				System.out.print("Enter updated ram: ");
				mac.setRam(br.readLine());
				
				System.out.print("Enter updated rom: ");
				mac.setRom(br.readLine());
				
				System.out.print("Enter updated processor: ");
				mac.setProcessor(br.readLine());
				
				System.out.print("Enter updation date(dd/mm/yyyy): ");
				machinehistory.setModified_date(br.readLine());
				
				String query="insert into machinehistory(machine_id, machine_brand, machine_model, ram, rom, processor, modified_date) values("+
				  machinehistory.getMachine_id()+", '"+machinehistory.getMachine_brand()+"', '"+machinehistory.getMachine_model()+"', '"+machinehistory.getRam()+
				  "', '"+machinehistory.getRom()+"', '"+machinehistory.getProcessor()+"', '"+machinehistory.getModified_date()+"')";
				insert=classDb.dmlQuery(query);
				
				query="update machine set ram='"+mac.getRam()+"', rom='"+mac.getRom()+"', processor='"+mac.getProcessor()+"' where id="+machineid;
				update=classDb.dmlQuery(query);
				
			}
			else {
				System.out.println("\n Machine with this ID does not exists!");
			}
			
		}
		catch(Exception e) {
			update=e.toString();
		}
		
		return "\n Machine information updated!!"+"\n History table updated";
	}
	
	
	// This function displays the full history of the machine 
	
	public String getMachineHistory(){
	  	
		String res="";
		Scanner sc=new Scanner(System.in);
		try {
			System.out.println("Enter machine id to see its history: ");
			int machineid=sc.nextInt();
			
			String query="select * from machinehistory where machine_id="+machineid;
			System.out.println("Hist-ID M/c-ID M/c-Brand M/c-Model \tM/c-Ram M/c-Rom Processor DateModified");
			res=classDb.selectQuery(query);
		}
		catch(Exception e) {
			res=e.toString();
		}
		
		return res;
	} 
	
	// Function for setting the status to scrapped if a machine gets scrapped
	
	public String setScrapStatus() {
		Scanner sc=new Scanner(System.in);
		try {
			System.out.println("Enter machine id to set scrap status of a machine: ");
			int id=sc.nextInt();
			
			String selectquery="select * from machinebooking where machine_id="+id+" and currentstatus='closed'";
			boolean res=classDb.checkRecordExists(selectquery);
			
			if(res==true) {
				String query="update machine set currentstatus='scrapped' where id="+id;
				classDb.dmlQuery(query);
				return "\n Machine status set to scrapped!!!";
			}
			else {return "Machine is booked to a employee, can't be set to scrapped";
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return "";
	}
	
	///// FOR SHOWING TOTAL MACHINE REPORT:-
	
	public String machineReport() {
		String showreport;
		try {
		String query="select employee.emp_id, employee.name, employee.mobileNo, machine.machine_brand, machine.machine_model, "+
				"machine.ram, machine.rom, machine.processor "+
				"from machine, employee, machinebooking "+
				"where "+
					"machinebooking.machine_id=machine.id "+
					"and "+
					"machinebooking.emp_id=employee.emp_id "+
					"and "+
					"machinebooking.currentstatus='active' ";
		
		System.out.println("Emp-ID Name\t \tMob-No \t\tM/c-Brand M/c-Model \tM/c-Ram M/c-Rom Processor");
		showreport=classDb.selectQuery(query);
		}
		catch(Exception e) {
			showreport=e.toString();
		}
		return showreport;
		}
	}


