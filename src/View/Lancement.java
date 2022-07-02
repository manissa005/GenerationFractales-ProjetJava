package View;

import Controller.ControleurFractale;
import Controller.ControleurPrincipale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Lancement extends Application {
	Scene scene;
	ControleurPrincipale control=new ControleurPrincipale();
	
	@Override
	public void start(Stage stage) throws Exception {
		   control = new ControleurPrincipale();
		   Parent root = FXMLLoader.load(getClass().getResource("/FXML/PagePrincipale.fxml")); 
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("manissa");
            stage.show();
            
	}
	public void setPrincipale(ControleurPrincipale control) {
		this.control=control;
	}
	/**
	 * Dans le main, on initialise le lancement, et on donne la meme scene pour le lancement et le controleur principale 
	 * afin de pouvoir charger les differents fxml dans la meme scene (fenetre) 
	 * @param args
	 */
	public static void main (String [] args) {
		Lancement lancement = new Lancement();
		ControleurFractale fractale = new ControleurFractale();
		ControleurPrincipale Pcontrol= new ControleurPrincipale();
		Pcontrol.setJulia(fractale);
		Pcontrol.setScene(lancement.scene);
		lancement.setPrincipale(Pcontrol);
		
		launch();
	}
}
