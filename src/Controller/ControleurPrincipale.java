package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControleurPrincipale {

	    @FXML
	    private AnchorPane PanePrincipale;
	
	    @FXML
	    private Button BoutonJulia;

	    @FXML
	    private Button BoutonMandelbrot;
	    
	    private Scene scene;
	    private ControleurFractale Jcontrol;
	    private Stage stage;
	    
	    public void setJulia(ControleurFractale Jcontrol) {
	    	this.Jcontrol = Jcontrol;
	    }
	    
	    /**
	     * Le handler du bouton Julia qui charge le fichier fxml Julia
	     * @param event
	     * @throws IOException
	     */

	     public void HandleJulia(ActionEvent event) throws IOException {
	    	Jcontrol=new ControleurFractale();
	    	Jcontrol.setType(true);
	    	 AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXML/FractaleJulia.fxml"));
	    	 PanePrincipale.getChildren().setAll(pane);
	            stage.setScene(scene);
	            stage.setResizable(false);
	            stage.setTitle("manissa");
	            stage.show();
	    }
	     /**
		     * Le handler du bouton Mandelbrot qui charge le fichier fxml Mandelbrot
		     * @param event
		     * @throws IOException
		     */

	    public void HandleMandelbrot(ActionEvent event) throws IOException {
	    	Jcontrol=new ControleurFractale();
	    	Jcontrol.setType(false);
	    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXML/FractaleMandelbrot.fxml"));
	    	PanePrincipale.getChildren().setAll(pane);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("manissa");
            stage.show();
	    } 
	    
	    public void setScene(Scene scene) {
	    	this.scene=scene;
	    }


}
