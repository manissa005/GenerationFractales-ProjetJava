package Model;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Function;

import Model.NombreComplexe.BuilderComplexe;
import Model.PlanComplexe.Builder;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class MainModeText {
	
	public static void main (String [] args) throws IOException {
		System.out.println("******************GENERATION DE FRACTALES******************\nQUELQUES VALEURS INTERESSANTES DE LA CONSTANTE C POUR LES FRACTALES DE JULIA ET MANDELBROT :\n");
		System.out.println("Julia :\n  c=-1+0i\n  c=0,285+0,01i\n  c=0,4+0,2i\n  c=0,3+0,5i\n  c=-0,4+0,6i\n  c=-0,72+0,11i");
		System.out.println("Mendelbrot :\n  c=-0,743643887037151+0,13182590420533i\n");
		System.out.println("Choissisez le type de fractales souhaité :\n 1-Fractales de Julia\n 2- Fractales de Mandelbrot ");
		Scanner sc=new Scanner(System.in);
		boolean correct = false;
		int choix=3;
		while (!correct) {
			try {
					choix = sc.nextInt();
					while (choix!=1 && choix !=2) {
						System.out.println("Le chiffre choisit doit être 1 ou 2 :");
						choix = sc.nextInt();
					}
					correct = true;
				
			}
			catch (InputMismatchException ex) {
				System.out.println("Pour choisir le type de fractales, veuillez taper 1 ou 2 (les caractéres ne sont pas acceptés) :");
			    String choix2=sc.nextLine();
		    }
		}
		System.out.println("Choissisez la constante c de votre polynome :");
		System.out.println("Partie reele : ");
		correct = false;
		double reel=0;
		while (!correct) {
			try {
				reel = sc.nextDouble();
				correct = true;
			} catch (InputMismatchException ex) {
				System.out.println("La partie réelle de la constante doit être un Double : les caractéres ne sont pas acceptés et la virule s'écrit , et non pas .:");
			    String choix2=sc.nextLine();
			}
		}
		System.out.println("Partie imaginaire : ");
		correct = false;
		double imagin=0;
		while (!correct) {
			try {
				imagin = sc.nextDouble();
				correct = true;
			} catch (InputMismatchException ex) {
				System.out.println("La partie imaginaire de la constante doit être un Double : les caractéres ne sont pas acceptés et la virule s'écrit , et non pas . :");
			    String choix2=sc.nextLine();
			}
		}
		NombreComplexe constant = new NombreComplexe.BuilderComplexe(reel,imagin).build();
		Function<NombreComplexe, NombreComplexe> poly = (x) -> (x.carre()).somme(constant);
		if (choix==1){

			System.out.println("Voulez vous choisir polynome de génération de Julia:\n1-OUI (introduire un nouveau polynome) 2-NON(utiliser le polynome par défaut : c+z^2 ) ");
			correct = false;
			int choix1=3;
			while (!correct) {
				try {
						choix1 = sc.nextInt();
						while (choix1!=1 && choix1 !=2) {
							System.out.println("Le chiffre choisit doit être 1 ou 2 :");
							choix1 = sc.nextInt();
						}
						correct = true;
					
				}
				catch (InputMismatchException ex) {
					System.out.println("Pour définir le choix du polynome, veuillez taper 1 ou 2 (les caractéres ne sont pas acceptés) :");
				    String choix2=sc.nextLine();
			    }
			}
				if (choix1==1) {
					System.out.println("Saisissez le nombre de termes de votre polynome SANS COMPTER LE TERME CONSTANT et en COMPTANT LES TERMES NULS : ");
					int nb = 0;
					correct = false;
					while (!correct) {
						try {
								nb= sc.nextInt();
								correct = true;
							
						}
						catch (InputMismatchException ex) {
							System.out.println("Le nombre du termes du polynome doit être un entier (les caractères ne sont pas acceptés) :");
						    String choix2=sc.nextLine();
					    }
					}
					System.out.println("Entrez les coefficients de chaque terme en commençant par le terme x^1 (donnez le coeff 0 pour les termes que vous ne voulez pas dans votre polynome :) ");
					double [] coeffs=new double[20];
					int pos = 0;
					for (int i = 0;i<nb;i++ ) {
						double coeff=0;
						correct = false;
						while (!correct) {
							try {
								coeff = sc.nextDouble();
									correct = true;
								
							}
							catch (InputMismatchException ex) {
								System.out.println("Les coefficients des termes du polynomes doivent être des nombres Double :  : les caractéres ne sont pas acceptés et la virule s'écrit , et non pas . :");
							    String choix2=sc.nextLine();
						    }
						}
						coeffs[pos]= coeff;
						pos++;
					}
					poly =  Polynome.polynome(coeffs,constant);
				}	
		}
		
		System.out.println("Veuillez introduire les paramétres suivants\nNombre d'itérations maximales (qui sera utilisé pour la vérification de la divergence de chaque point sur le plan complexe) : ");
		int max_iter = 0;
		correct = false;
		while (!correct) {
			try {
					max_iter= sc.nextInt();
					correct = true;	
			}
			catch (InputMismatchException ex) {
				System.out.println("Le nombre maximum d'itérations doit être un nombre entier (les caractéres ne sont pas acceptés) :");
			    String choix2=sc.nextLine();
		    }
		}
		System.out.println("La limite RADIUS : ");
		int radius = 0;
		correct = false;
		while (!correct) {
			try {
					radius= sc.nextInt();
					correct = true;	
			}
			catch (InputMismatchException ex) {
				System.out.println("La limite doit être un nombre entier (les caractéres ne sont pas acceptés) :");
			    String choix2=sc.nextLine();
		    }
		}
		System.out.println("La hauteur de l'image des fractales : ");
		int imH=0;
		correct = false;
		while (!correct) {
			try {
					imH= sc.nextInt();
					correct = true;	
			}
			catch (InputMismatchException ex) {
				System.out.println("La hauteur de l'image doit être un nombre entier (les caractéres ne sont pas acceptés) :");
			    String choix2=sc.nextLine();
		    }
		}
		System.out.println("La largeur de l'image des fractales : ");
		int imW = 0;
		correct = false;
		while (!correct) {
			try {
					imW= sc.nextInt();
					correct = true;	
			}
			catch (InputMismatchException ex) {
				System.out.println("La largeur de l'image doit être un nombre entier (les caractéres ne sont pas acceptés) :");
			    String choix2=sc.nextLine();
		    }
		}
		System.out.println("La hauteur du rectangle du plan complexe : ");
		int recH=0;
		correct = false;
		while (!correct) {
			try {
					recH= sc.nextInt();
					correct = true;	
			}
			catch (InputMismatchException ex) {
				System.out.println("La hauteur du rectangle doit être un nombre entier (les caractéres ne sont pas acceptés) :");
			    String choix2=sc.nextLine();
		    }
		}
		System.out.println("La largeur du rectangle du plan complexe : ");
		int recW = 0;
		correct = false;
		while (!correct) {
			try {
					recW= sc.nextInt();
					correct = true;	
			}
			catch (InputMismatchException ex) {
				System.out.println("La largeur du rectangle doit être un nombre entier (les caractéres ne sont pas acceptés) :");
			    String choix2=sc.nextLine();
		    }
		}
		String typeFrac;
		GestionColor gestion;
		if(choix==1) {
			 gestion = new Julia(max_iter,radius,poly);
			 typeFrac = "Type de fractales : JULIA";
		}
		else {
			gestion = new Mandelbrot(max_iter, radius,poly);
			typeFrac = "Type de fractales : MANDELBROT";
		}
	/*	System.out.println("Donnez un nom a votre image :");
		String nom1 = sc.nextLine();*/
		File f=new File("Fractale.png");
    	Image img=new WritableImage(imH,imW);
    	File file = new File("DescriptionFractale.txt");
		PlanComplexe plan = new Builder(recH,recW,gestion,img,file).build();
        plan.GenerationImageMultithreads();
        String cheminfichier = plan.SauvegardeImage(f);
        String cc = "La constante du polynome : "+String.valueOf(reel)+" "+String.valueOf(imagin);
        String im = "La taille de l'image :"+String.valueOf(imH)+" "+String.valueOf(imW);
        String rec = "La taille du rectangle du plan complexe : "+String.valueOf(recH)+" "+String.valueOf(recW);
        String iter = "Le nombre maximale d'itérations : "+String.valueOf(max_iter);
        String rad = "La limite de calcul : "+String.valueOf(radius);
        String chemindesc=plan.SauvegardeFile(typeFrac,cc,im,rec,iter,rad);
        System.out.println("Votre image a été sauvegardé dans : " + cheminfichier);
        System.out.println("Le fichier de la description de l'image fractales a été sauvegardé dans : " + chemindesc);
    	sc.close();
		
	}
}
