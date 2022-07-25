package hr_hardware_resources_management_system;

public class Machine {
	
	private int id;
	private String machine_brand, machine_model, ram, rom, processor, currentstatus;

	//Getter and setter methods for the information in machine table
	
	public String getMachinebrand() {
		return machine_brand;
	}
	public void setMachinebrand(String machine_brand) {
		this.machine_brand = machine_brand;
	}
	public String getMachinemodel() {
		return machine_model;
	}
	public void setMachinemodel(String machine_model) {
		this.machine_model = machine_model;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getRom() {
		return rom;
	}
	public void setRom(String rom) {
		this.rom = rom;
	}
	public String getProcessor() {
		return processor;
	}
	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public String getCurrentstatus() {
		return currentstatus;
	}
	public void setCurrentstatus(String currentstatus) {
		this.currentstatus = currentstatus;
	}

}
