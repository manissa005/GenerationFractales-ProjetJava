package Model;

import java.util.function.Function;

public final class Polynome {
	 /**
	  * cree un seul terme du polynome
	  * @param coeff
	  * @param n
	  * @return
	  */
	private static Function<NombreComplexe, NombreComplexe> terme(double coeff, int n) {
	    return (x -> new NombreComplexe.BuilderComplexe(coeff, 0).build().multiplication(x.puissance(n)));
	}
	/**
	 * fonction recursive qui cree terme par terme et qui ajoute a chaque fois les nouveaux termes
	 * @param coeff
	 * @param puissance
	 * @param constante
	 * @return
	 */
	private static Function<NombreComplexe, NombreComplexe> polynomeRec(double [] coeff,int puissance, NombreComplexe constante ){
		if (puissance <= 0)
            return x -> constante;
        return x -> polynomeRec(coeff, puissance - 1, constante).apply(x).somme(terme(coeff[puissance-1], puissance).apply(x));
	}
	/**
	 * appelle la fnction recursive 
	 * @param coeff
	 * @param constante
	 * @return
	 */
	static Function<NombreComplexe, NombreComplexe> polynome(double [] coeff, NombreComplexe constante){
		return polynomeRec(coeff, coeff.length,constante);
	}
	
}