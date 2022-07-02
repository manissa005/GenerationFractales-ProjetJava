package Model;
import javafx.scene.paint.Color;

import java.util.function.Function;

public class Julia extends GestionColor{


    /**
     * Le constructeur
     *
     * @param maxIter le nombre d'itération maximal
     * @param limite la limite de la fonction "fun"
     * @param fun la fonction du fractal
     */
    public Julia(int maxIter, double limite, Function<NombreComplexe, NombreComplexe> fun) {
        super(maxIter, limite, fun);
    }

    /**
     * fonction qui trouve l'indice de divergence de chaque nombre complexe du plan en utilisant le nombre max d'iter et la limite
     */
    @Override
    Color createColorFromDivergenceIndex(NombreComplexe z0) {
        int iter=0;
        NombreComplexe zn =  z0;
        while(iter< this.getMaxIteration()&& zn.module() < this.getLimite()){
            zn= this.getFun().apply(zn);
            iter ++;
        }
        return createColorFromInt(iter);
    }
}
