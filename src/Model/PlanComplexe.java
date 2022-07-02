package Model;


import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
public final class PlanComplexe {
	
	private final double largeur;
    private final double hauteur;
    private final GestionColor gestion;
    private final Image fractale;
    private File file;
    
    /**
     * classe interne builder pour créer un PlanComplexe
     * @author USER
     *
     */
    
    public Image getImage() {
    	return fractale;
    }
    public double getLargeur() {
    	return largeur;
    }
    public double getHauteur() {
    	return hauteur;
    }
    public GestionColor getGestion() {
    	return gestion;
    }
    public File getFile() {
    	return file;
    }
    public static class Builder{
    	private final double largeur;
        private final double hauteur;
        private final GestionColor gestion;
        private final Image fractale;
        private File file;
        
        public Builder (double largeur, double hauteur, GestionColor gestion, Image fractale,File file) {
        	// ici on initialise directement gestion en testant le typeFrac (juste dans le builder)
        	this.largeur=largeur;
        	this.hauteur=hauteur;
        	this.gestion = gestion;
        	this.fractale=fractale;
        	this.file=file;
      
        }
        public PlanComplexe build () {
        	return new PlanComplexe(this);
        }
   }
    
    private PlanComplexe(Builder builder) {
    	this.largeur=builder.largeur;
    	this.hauteur=builder.hauteur;
    	this.gestion = builder.gestion;
    	this.fractale=builder.fractale;
    	this.file=builder.file;
    			
    }

    /**
     * calcule la valeur reel de chaque nombre complexe du plan selon les dimensions de notre image
     * @param reel
     * @return
     */
    public double ReelImage (double reel) {
    	return 2.0*(reel-fractale.getWidth()/2)/(fractale.getWidth()/2);

    }
    /**
     * calcule la valeur imaginaire de chaque nombre complexe du plan selon les dimensions de notre image
     * @param reel
     * @return
     */
    public double ImaginaireImage( double imaginaire) {
    	return 1.33*(imaginaire-fractale.getHeight()/2)/(fractale.getHeight()/2);
    }
    /**
     * Crée nbThread de taches
     * @param nbThread
     * @return
     */
    private List<ForkJoinTask> creationTache(int nbThread) {
        List<ForkJoinTask> taches = new LinkedList<>();
        ForkJoinPool pool = new ForkJoinPool(nbThread);
        for (int i = 0; i < nbThread; i++) {
            taches.add(pool.submit( new PlanThread( i * ((int) fractale.getHeight()) / nbThread, (i + 1) * ((int) fractale.getHeight()) / nbThread, this) ));
        }
        return taches;
    }

    /**
     * genere l'image avec multithreading ou chaque thread colorie une partie de pixels de l'image
     *
     * @return l'image construite pixel par ppixel
     */
    public Image GenerationImageMultithreads() {
        for (ForkJoinTask task : creationTache(Runtime.getRuntime().availableProcessors() - 1)) {
            try {
                task.join();
            } catch (Exception e) {
                System.out.println("Error with multithreading");
            }
        }
		return fractale;
    }
    /**
     * sauvegarde l'image dans un fichier .png et renvoi le chemin vers le fichier
     * @param file
     * @return
     * @throws IOException
     */
    public String SauvegardeImage(File file) throws IOException {
    	BufferedImage buff = SwingFXUtils.fromFXImage(fractale, null);
        ImageIO.write(buff,"png", file );
        return  file.getAbsolutePath();
    }
    /**
     * genere un fichier description pour chaque image de fractale ou on sauvegarde les infos relié au fractale de l'image
     * @param gestion
     * @param constant
     * @param image
     * @param rectangle
     * @param iter
     * @param limite
     * @return
     * @throws IOException
     */
    public String SauvegardeFile(String gestion, String constant, String image, String rectangle, String iter, String limite ) throws IOException {
    	String description = gestion+"\n"+constant+"\n"+image+"\n"+rectangle+"\n"+iter+"\n"+limite;
    	BufferedWriter buffer = null;
		FileWriter writer = new FileWriter(file);
		  buffer = new BufferedWriter(writer);
		  buffer.write(description);
		  try{
	   	      if(buffer!=null)
	   		 buffer.close();
	   	   }catch(Exception ex){
	   	       System.out.println("Error in closing the BufferedWriter"+ex);
	   	    }
		  return  file.getAbsolutePath();
    }
}
    
    
    
    
