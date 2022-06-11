package MainClasses;

import java.util.Objects;

public class Block {
	
	// Class Attributes
	private String CodeBlock;
	private String Departement;
	
	// Class Constructor
	public Block() {
	}

	public Block(String codeBlock, String departement) {
		CodeBlock = codeBlock;
		Departement = departement;
	}
	
	// Class Getters
	public String getCodeBlock() {
		return CodeBlock;
	}

	public String getDepartement() {
		return Departement;
	}
	
	// Class Setters
	public void setCodeBlock(String codeBlock) {
		CodeBlock = codeBlock;
	}

	public void setDepartement(String departement) {
		Departement = departement;
	}
	
	// Overriding toString and equals Methods
	@Override
	public String toString() {
		return "Block [CodeBlock=" + CodeBlock + ", Departement=" + Departement + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Block other = (Block) obj;
		return Objects.equals(CodeBlock, other.CodeBlock) && Objects.equals(Departement, other.Departement);
	}
	
	
}
