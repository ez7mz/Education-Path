package MainTest;

import MainClasses.*;
public class TestPrin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Admin ad = new Admin(165456,"Kaddiri","Abdo","Director Adjoint");
		Admin ad2 = new Admin(165456,"Kaddiri","Abdo","Director Adjoint");
		
		System.out.println(ad.getFonction());
		System.out.println(ad2.getIdAdmin());
		
		System.out.println("\n"+ad.toString());
		System.out.println(ad.equals(ad2));
	}

}
