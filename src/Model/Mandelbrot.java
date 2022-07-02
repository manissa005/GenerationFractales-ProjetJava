package Model;

import javafx.scene.paint.Color;

import java.util.function.Function;

public class Mandelbrot extends GestionColor{
    /**
     * Le constructeur
     *
     * @param maxIter
     * @param limite
     * @param fun
     */
    public Mandelbrot(int maxIter, double limite, Function<NombreComplexe, NombreComplexe> fun) {
        super(maxIter, limite, fun);
    }

    /**
     * fonction qui trouve l'indice de divergence de chaque nombre complexe du plan en utilisant le nombre max d'iter et la limite
     */
    @Override
    Color createColorFromDivergenceIndex(NombreComplexe z0) {
        NombreComplexe zn = new NombreComplexe.BuilderComplexe(0,0).build();
        int iter =0;
        while(iter < this.getMaxIteration() && zn.module()<this.getLimite()){
            zn=this.getFun().apply(zn).somme(z0);
            iter++;
        }
        return createColorFromInt(iter);
    }

}
