package Controller;

import java.io.File;
import java.io.IOException;
import java.util.function.Function;
import Model.GestionColor;
import Model.Julia;
import Model.Mandelbrot;
import Model.NombreComplexe;
import Model.PlanComplexe;
import Model.PlanComplexe.Builder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ControleurFractale {
	private boolean typeFrac;
	
	@FXML
    private TextField reel;

    @FXML
    private TextField imaginaire;

    @FXML
    private TextField iter_max;

    @FXML
    private TextField radius;

    @FXML
    private TextField imageH;

    @FXML
    private TextField imageW;

    @FXML
    private TextField recH;

    @FXML
    private TextField recW;

    @FXML
    private TextField nomimage;
   
    @FXML
    private Button GenereM;
    @FXML
    private Button GenereJ;
    @FXML
    private AnchorPane PaneJulia;
    
    private PlanComplexe plan;
    private  NombreComplexe constant;
    private int iter;
    private double limite;
    private Stage stage;
	static double initX;
	static double initY;
	static int hauteur;
	static int largeur;
	static Scene ViewImage;
	static double offSetX,offSetY,zoomLevel;
	
	/**
	 * Lire les parametres choisis par l'utilisateur, les recuperer depuis l'interfcae graphique en controlant la validité des valeurs saisies,
	 * on affiche une erreur si c'est pas un nombre, ou si on donne 0 au dimensions de l'image
	 */

    @FXML
    void LectureParam() {
 	String recup = reel.getText();
 	double reel=0, imagin=0,recH=0, recW=0; 
 	int imageH=1, imageW=1;
 			try {
 				reel = Double.parseDouble(recup);
 				recup = imaginaire.getText();
 				 imagin = Double.parseDouble(recup);
 				recup = iter_max.getText();
 				this.iter = Integer.parseInt(recup);
 				recup = radius.getText();
 				this.limite= Double.parseDouble(recup);
 				try{
 					recup = this.imageH.getText();
 					imageH = Integer.parseInt(recup);
 					recup = this.imageW.getText();
 					imageW = Integer.parseInt(recup);
 					if(imageH==0 || imageW==0) {
 						throw new IllegalArgumentException();
 					}
 				}catch (IllegalArgumentException  e) {
 					this.Erreur2();
 				}
 				
 				recup = this.recH.getText();
 				recH = Double.parseDouble(recup);
 				recup = this.recW.getText();
 				recW = Double.parseDouble(recup);
 			} catch (NumberFormatException e) {
 				this.Erreur1();
 			}
 	Image image=new WritableImage(imageH,imageW);
 	this.constant = new NombreComplexe.BuilderComplexe(reel, imagin).build();
 	Function<NombreComplexe,NombreComplexe> poly =  (x) -> (x.carre()).somme(constant);
 	GestionColor gestion;
 	if(typeFrac) {gestion = new Julia(iter,limite,poly);}
 	else {gestion = new Mandelbrot(iter,limite,poly);}
 	File file=new File("desciprtion.txt");
 	this.plan = new Builder(recH,recW,gestion,image,file).build();
 }

    /**
     * Handler du bouton generer Julia, genere l'image des fractales en utilisant des multithreads
     * @param event
     * @throws IOException
     */
@FXML
void HandleGenereM(ActionEvent event) throws IOException {
	stage = new Stage();
	LectureParam();
	this.plan.GenerationImageMultithreads();
	Image img = plan.getImage();
	stage.setScene(initImageView(img));
	stage.show();
   }
/**
 * Handler du bouton generer Mandelbrot, genere l'image des fractales en utilisant des multithreads
 * @param event
 * @throws IOException
 */
@FXML
void HandleGenereJ(ActionEvent event) throws IOException {
	stage = new Stage();
	LectureParam();
	this.plan.GenerationImageMultithreads();
	Image img = plan.getImage();
	stage.setScene(initImageView(img));
	stage.show();
   }

	public void Erreur1() {
		Alert a = new Alert(Alert.AlertType.WARNING);
		Stage stage = new Stage();
		stage.setScene(new Scene(new Group()));
		a.initOwner(stage);
		a.setTitle("Erreur de Saisie");
		a.setHeaderText("ATTENTION ! Les valeurs saisis ne sont pas correctes, veuillez reessayer (les virgules s'ecrivent . et pas , ) ");

        a.show();
	}
	public void Erreur2() {
		Alert a = new Alert(Alert.AlertType.WARNING);
		Stage stage = new Stage();
		stage.setScene(new Scene(new Group()));
		a.initOwner(stage);
		a.setTitle("Erreur de Saisie");
		a.setHeaderText("ATTENTION ! Les dimensions de l'image doivent etre positives et differentes de 0");

        a.show();
	}

	/**
	 *
	 * @param img l'image du fractale
	 * @return  ViewImage qui contient l'image du fractale ainsi que les Scroller du zoom
	 */
	public static Scene initImageView(Image img){

		VBox Vbox = new VBox(20);
		Vbox.setAlignment(Pos.CENTER);

		ImageView image = new ImageView(img);
		double ratio = img.getWidth()/img.getHeight();

		/**
		 * on fixe la taille de l'ImageVIew
		 */
		if(500/ratio < 500) {
			largeur=500;
			hauteur=(int) (500/ratio);
		}else if(500*ratio < 500){
			hauteur=500;
			largeur=(int) (500*ratio);
		}else {
			hauteur=500;
			largeur=500;
		}
		image.setPreserveRatio(false);
		image.setFitWidth(largeur);
		image.setFitHeight(hauteur);
		hauteur = (int) img.getHeight();
		largeur = (int) img.getWidth();

		/** une box horizontale pour le scroller des valeur du zoom */
		HBox zoom = new HBox(10);
		zoom.setAlignment(Pos.CENTER);

		Slider zoomSlider = new Slider();
		zoomSlider.setMax(20);
		zoomSlider.setMin(1);
		zoomSlider.setMaxWidth(200);
		zoomSlider.setMinWidth(200);
		Label hint = new Label("Zoom Level");
		Label zoomValue = new Label("1.0");

		offSetX = largeur/2; /** Valeur du Slider horizontal */
		offSetY = hauteur/2; /** Valeur du Slider vertical */


		zoom.getChildren().addAll(hint,zoomSlider,zoomValue);
		/** Slider de déplacement horizontal*/
		Slider HSlider = new Slider();
		HSlider.setMin(0);
		HSlider.setMax(largeur);
		HSlider.setMaxWidth(image.getFitWidth());
		HSlider.setMinWidth(image.getFitWidth());
		HSlider.setTranslateY(-20);

		/** Slider de délacement vertical*/
		Slider VSlider = new Slider();
		VSlider.setMin(0);
		VSlider.setMax(hauteur);
		VSlider.setMaxHeight(image.getFitHeight());
		VSlider.setMinHeight(image.getFitHeight());
		VSlider.setOrientation(Orientation.VERTICAL);
		VSlider.setTranslateX(-20);


		BorderPane imageView = new BorderPane();
		BorderPane.setAlignment(HSlider, Pos.CENTER);
		BorderPane.setAlignment(VSlider, Pos.CENTER_LEFT);

		/** configuration du slider lié au déplacement horizontal sur l'image*/
		HSlider.valueProperty().addListener(e->{
			offSetX = HSlider.getValue();
			zoomLevel = zoomSlider.getValue();
			double newValue = (double)((int)(zoomLevel*10))/10;
			zoomValue.setText(newValue+"");
			if(offSetX<(largeur/newValue)/2) {
				offSetX = (largeur/newValue)/2;
			}
			if(offSetX>largeur-((largeur/newValue)/2)) {
				offSetX = largeur-((largeur/newValue)/2);
			}

			image.setViewport(new Rectangle2D(offSetX-((largeur/newValue)/2), offSetY-((hauteur/newValue)/2), largeur/newValue, hauteur/newValue));
		});

		/** configuration du slider lié au déplacement vertical sur l'image*/
		VSlider.valueProperty().addListener(e->{
			offSetY = hauteur-VSlider.getValue();
			zoomLevel = zoomSlider.getValue();
			double newValue = (double)((int)(zoomLevel*10))/10;
			zoomValue.setText(newValue+"");
			if(offSetY<(hauteur/newValue)/2) {
				offSetY = (hauteur/newValue)/2;
			}
			if(offSetY>hauteur-((hauteur/newValue)/2)) {
				offSetY = hauteur-((hauteur/newValue)/2);
			}
			image.setViewport(new Rectangle2D(offSetX-((largeur/newValue)/2), offSetY-((hauteur/newValue)/2), largeur/newValue, hauteur/newValue));
		});
		imageView.setCenter(image);
		imageView.setTop(HSlider);
		imageView.setRight(VSlider);
		/** Configuration du slider lié au zoom*/
		zoomSlider.valueProperty().addListener(e->{
			zoomLevel = zoomSlider.getValue();
			double newValue = (double)((int)(zoomLevel*10))/10;
			zoomValue.setText(newValue+"");
			if(offSetX<(largeur/newValue)/2) {
				offSetX = (largeur/newValue)/2;
			}
			if(offSetX>largeur-((largeur/newValue)/2)) {
				offSetX = largeur-((largeur/newValue)/2);
			}
			if(offSetY<(hauteur/newValue)/2) {
				offSetY = (hauteur/newValue)/2;
			}
			if(offSetY>hauteur-((hauteur/newValue)/2)) {
				offSetY = hauteur-((hauteur/newValue)/2);
			}
			HSlider.setValue(offSetX);
			VSlider.setValue(hauteur-offSetY);
			image.setViewport(new Rectangle2D(offSetX-((largeur/newValue)/2), offSetY-((hauteur/newValue)/2), largeur/newValue, hauteur/newValue));
		});
		imageView.setCursor(Cursor.OPEN_HAND);
		
		
		/**
		 * configuration des évènements liés à la souris
		 */
		/**  quand la souris est pressée, on récupère les coordonnées de l'évènement */
		image.setOnMousePressed(e->{
			initX = e.getSceneX();
			initY = e.getSceneY();
			imageView.setCursor(Cursor.CLOSED_HAND);
		});
		image.setOnMouseReleased(e->{
			imageView.setCursor(Cursor.OPEN_HAND);
		});

		/** quand on déplace le curseur de la souris, on change les valeurs des Slider de déplacement
		 * et on effectue un déplacement sur l'image
		 */
		image.setOnMouseDragged(e->{
			HSlider.setValue(HSlider.getValue()+(initX - e.getSceneX()));
			VSlider.setValue(VSlider.getValue()-(initY - e.getSceneY()));
			initX = e.getSceneX();
			initY = e.getSceneY();
		});
		Vbox.getChildren().addAll(imageView,zoom);

		return ViewImage = new Scene(Vbox,(image.getFitWidth())+70,(image.getFitHeight())+150);
	}
	 public void setType(boolean typeFrac) {
	    	this.typeFrac=typeFrac;
	    }
}
