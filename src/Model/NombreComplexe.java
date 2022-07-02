package Model;

public final class NombreComplexe {
	
	private double reel;
	private double imaginaire;
	
	/**
	 * classe interne Builder afin de créer des nombres complexes 
	 * @author USER
	 *
	 */
	public static class BuilderComplexe {
		private final double reel;
		private final double img;
		/**
		 * Constructeur du builder
		 * @param partie reele
		 * @param partie imginaire
		 */
		public BuilderComplexe(double reel, double img) {
			this.reel=reel;
			this.img=img;
		}
		
		public NombreComplexe build() {
			return new NombreComplexe(this);
		}
		
	}
	private NombreComplexe(BuilderComplexe builder) {
		this.reel= builder.reel;
		this.imaginaire=builder.img;
	}
	/**
	 * getReel renvoi une copie defensive du reel
	 * @return la pertie reele du nombre complexe this
	 */
	double getReel () {
		double r = this.reel;
		return r;
	}
	/**
	 * etImaginaire renvoi une copie defensive du imaginaire
	 * @return la partie imaginaire du nombre complexe this
	 */
	double getImaginaire() {
		double i = this.imaginaire;
		return i;
	}
	/**
	 * somme ne modifie pas le nombre complexe mais cree une copie defensive
	 *
	 * @param nombre complexe
	 * @return somme du nombre complexe donne et this
	 */
	public NombreComplexe somme (NombreComplexe z) {
		return new BuilderComplexe(this.reel + z.getReel(),this.imaginaire+z.getImaginaire()).build();
	}
	
	public NombreComplexe multiplication(NombreComplexe z) {
		return new BuilderComplexe(this.reel*z.getReel()-this.imaginaire*z.getImaginaire(),this.reel*z.getImaginaire()+this.imaginaire*z.getReel()).build();
	}
	/**
	 * carre ne modifie pas e nombre complexe mais renvoi une copie defensive
	 * @return le carre du nombre complexe this
	 */
	public NombreComplexe carre() {	
		return new BuilderComplexe(reel*reel-imaginaire*imaginaire,imaginaire*reel*2).build();
	}
	/**
	 *,
	 * @return le module du nombre complexe this
	 */
	public double module () {
		return (Math.sqrt(Math.pow(reel,2) + Math.pow(imaginaire,2)));
	}
	
	public NombreComplexe puissance(int n) {
		if (n == 0||n==1)
            return this;
        if (n % 2 == 0)
            return (this.carre()).puissance(n / 2);
        else
            return (this.carre()).puissance((n - 1) / 2).multiplication(this);
	}
	public String toString() {
		return "partie reele :"+reel+" partie img : " +imaginaire;
	}

}