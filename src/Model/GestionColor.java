package Model;
import javafx.scene.paint.Color;
import java.util.function.Function;

public abstract class GestionColor {

    /** classe pour la gestion de calcul des couleurs*/

    private final int maxIter;   /** nombre d'itération maximal*/
    private final double limite;  /** limite de la fonction fun */
    private final Function<NombreComplexe,NombreComplexe> fun;  /** la fonction du fractal*/

    /**
     * Le constructeur
     */

    GestionColor (int maxIter, double limite , Function<NombreComplexe,NombreComplexe> fun){
        this.maxIter=maxIter;
        this.limite=limite;
        this.fun=fun;
    }

    /**
     * fonction statique qui crée une couleur "javafx.Color" à partir d'un entier (nombre d'itérations) passé en paramètres
     * @param i : nombre d'itérations
     * @return une couleur
     */
    public Color createColorFromInt( int i){
    	if (i==maxIter) return Color.BLACK;
        ///return Color.rgb((i * 10) % 255, (i * 30) % 255, (i * 50) % 255);
    	return Color.rgb ((255*i)/maxIter,0,0);
    }

    /**
     * classe abstraite qui associe une couleur au pixel "z0"  selon l'indice de divergence
     * @param z0 le pixel pour lequel on calcule l'indice de divergence
     * @return la couleur associée à  z0
     */
    abstract Color createColorFromDivergenceIndex( NombreComplexe z0);

    /** les getteurs */

    /**
     *
     * @return le nombre d'itérations
     */

    public int getMaxIteration() {
        return this.maxIter;
    }

    /**
     *
     * @return la limite de la fonction du fractal
     */
    public double getLimite() {
        return this.limite;
    }

    /**
     *
     * @return la fonction du fractal
     */
    public Function<NombreComplexe,NombreComplexe> getFun() {
        return this.fun;
    }
}
