package hr_hardware_resources_management_system;

public class MachineHistory {
	
	private int history_id, machine_id;
	private String machine_brand, machine_model, ram, rom, processor, modified_date;
	public int getHistory_id() {
		return history_id;
	}
	public void setHistory_id(int history_id) {
		this.history_id = history_id;
	}
	public int getMachine_id() {
		return machine_id;
	}
	public void setMachine_id(int machine_id) {
		this.machine_id = machine_id;
	}
	public String getMachine_brand() {
		return machine_brand;
	}
	public void setMachine_brand(String machine_brand) {
		this.machine_brand = machine_brand;
	}
	public String getMachine_model() {
		return machine_model;
	}
	public void setMachine_model(String machine_model) {
		this.machine_model = machine_model;
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
	public String getModified_date() {
		return modified_date;
	}
	public void setModified_date(String modified_date) {
		this.modified_date = modified_date;
	}
	
	

}
