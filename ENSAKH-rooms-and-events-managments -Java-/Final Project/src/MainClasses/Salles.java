package MainClasses;

import java.util.Objects;

public class Salles {
	
	// Class Attributes
	private String SalleNum;
	private int Capacity;
	private String Block;
	
	//Class Constructor
	public Salles() {
	}

	public Salles(String salleNum, int capacity, String block) {
		SalleNum = salleNum;
		Capacity = capacity;
		Block = block;
	}
	
	// Class Getters
	public String getSalleNum() {
		return SalleNum;
	}

	public int getCapacity() {
		return Capacity;
	}

	public String getBlock() {
		return Block;
	}
	
	// Class Setters
	public void setSalleNum(String salleNum) {
		SalleNum = salleNum;
	}

	public void setCapacity(int capacity) {
		Capacity = capacity;
	}

	public void setBlock(String block) {
		Block = block;
	}
	
	//Overriding ToString and equals Methods
	@Override
	public String toString() {
		return "SalleNum=" + SalleNum + "\nCapacity=" + Capacity + "\nBlock=" + Block;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Salles other = (Salles) obj;
		return Objects.equals(Block, other.Block) && Capacity == other.Capacity
				&& Objects.equals(SalleNum, other.SalleNum);
	}
	
	
}
