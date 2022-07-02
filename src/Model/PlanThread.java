package Model;

import java.util.concurrent.RecursiveAction;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class PlanThread extends RecursiveAction {
	
	private int PremierPix;
	private int DernierPix;
	private PlanComplexe plan;
	
	public PlanThread(int PremierPix,int DernierPix, PlanComplexe plan ) {
		this.PremierPix=PremierPix;
		this.DernierPix=DernierPix;
		this.plan=plan;
	}
	/**
	 * initialise chaque pixel avec une couleur
	 * @param writer
	 * @param j
	 * @param i
	 * @param c
	 */
	private void writeColor(PixelWriter writer, int j, int i, Color c) {
    	writer.setColor(j, i, c);
     }
	/**
	 * Chaque thread va donner une couleur a une partie de pixel de notre image en commencant par le PremierPix jusquau DernierPix
	 */

	@Override
	protected void compute() {
		PixelWriter writer = ((WritableImage) plan.getImage()).getPixelWriter();
	    for (int i = PremierPix; i < DernierPix; i++) {
	        for (int j = 0; j < plan.getImage().getWidth(); j++) {
	        	NombreComplexe tmp = new NombreComplexe.BuilderComplexe(plan.ReelImage(i),plan.ImaginaireImage (j)).build();
	            Color c = plan.getGestion().createColorFromDivergenceIndex(tmp);
	            writeColor(writer, j, i, c);
	        }
	    }
		
	}
	}
	
