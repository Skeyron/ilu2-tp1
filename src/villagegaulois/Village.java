package villagegaulois;

import java.security.PublicKey;
import java.util.Iterator;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

import jdk.internal.dynalink.beans.StaticClass;
import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	
	public Village(String nom, int nbVillageoisMaximum, int nbEtals){
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
	private static class Marche{
		private Etal[] etals;
		private int nbEtalsMax;
		
 		private Marche (int nbEtalsMax) {
 			etals = new Etal[nbEtalsMax];
 			for (int i = 0; i < etals.length; i++) {
 				etals[i] = new Etal();
			}
 		}
 		
 		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
 			if (indiceEtal < nbEtalsMax) {
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			}	
		}
 		
 		private int trouverEtalLibre() {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
 		
 		private Etal[] trouverEtals(String produit){
 			
 			int compteur=0;
 			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					compteur ++;
				}
			}
 			
 			Etal[] tab = new Etal[compteur];
 			compteur = 0;
 			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					tab[compteur] = etals[i];
					compteur ++;
				}
			}
 			return tab;
 		}
 		
 		private Etal trouverVendeur(Gaulois gaulois) {
 			for (int i = 0; i < etals.length; i++) {
				if (etals[i].getVendeur().equals(gaulois)) {
					return etals[i];
				}
			}
 			return null;
 		}
 		
 		private String afficherMarche() {
 			int libre = 0;
 			StringBuilder str = new StringBuilder();
 			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					str.append(etals[i].afficherEtal());
				} else {
					libre ++;
				}
			}
 			str.append("Il reste " + libre + " �tals non utilis�s dans le march�");
 			return str.toString();
 		}
	}
	
 	public String installerVendeur(Gaulois vendeur,String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + "cherche un endroit pour vendre " + nbProduit + produit + " . \n");
		int etalNum = marche.trouverEtalLibre();
		if (etalNum == -1) {
			chaine.append(" Aucun Etal disponible");
			return chaine.toString();
		} else {
			chaine.append("Le vendeur " + vendeur.getNom() + " vend des lyres � l'�tal " + etalNum);
		return chaine.toString();	
		}
 	}
 	
 	public String rechercherVendeursProduit(String produit) {
 		StringBuilder chaine = new StringBuilder();
 		Etal[] tab = marche.trouverEtals(produit);
 		for (int i = 0; i < tab.length; i++) {
			chaine.append("\n - " + tab[i].getVendeur().getNom());
		}
 		return chaine.toString();
 	}
 	
 	public Etal rechercherEtal(Gaulois vendeur) {
 		return marche.trouverVendeur(vendeur);

 	}
 	
 	public String partirVendeur(Gaulois vendeur) {
 		Etal vend = marche.trouverVendeur(vendeur);
 		if (vend != null) {
 			return vend.libererEtal();
 		} else {
 			return "Aucun vendeur de ce nom";
 		}	
 	}
 	
 	public String afficherMarche() {
		return marche.afficherMarche();
	}
	}
 		
 		
 

	
	