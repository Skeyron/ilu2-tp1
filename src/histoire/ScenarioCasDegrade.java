package histoire;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.*;

public class ScenarioCasDegrade {
	
	
	public static void main(String[] args) {
		Etal etal = new Etal();
//		etal.libererEtal();
		Gaulois asterix = new Gaulois("Astérix", 5);
		
		etal.occuperEtal(asterix, "Thon", 2);
		etal.acheterProduit(-2,null );
		
		System.out.println("Fin du test");
		}

}
