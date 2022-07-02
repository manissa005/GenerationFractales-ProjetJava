PROJET GENERATION FRACTALES :

Ce projet fonctionne sous JAVA11
La technologie utilisé pour faire l'interface graphique est JAVAFX17 (le fichier javafx sdk est inclus dans le repértoire du projet, les bibliothèques externes (external jars) doivent être ajouté au projet) ainsi que SCENE BUILDER pour la construction des fichiers fxml.

Ce projet peut s'éxecute en mode graphique et en mode ligne de commande, l'utilisateur peut introduire les paramétres souhaités dans les deux modes, dans le mode graphique, l'image est affiché dans l'application, en mode ligne de commande, l'image sous forme ".png" de fractales générée est sauvegardée dans le repértoire du projet, un chemin vers l'image est donné, l'image est accompagné d'un fichier texte ".txt" présantant une description du type de fractales de l'image ainsi que ses paramétres.

IMPLENTATION :

Les images de fractales sont générés après la récupération des paramétres introduits par l'utilisateur.
La classe GestionColor calcule une couleur pour chaque pixel de l'image elon le nombre maximum d'itération, la limite ainsi que la fonction utilisé pour les calculs qui différe selon le type (Julia ou Mandelbrot).

Il y a possibilité de choisir le polynome de calcul des indices de divergence ou de garder le polynome par défaut pour les fractales de Julia.

Les calculs de couleurs des pixels de l'image sont faits en multithreading, ou chaque thread calcule un nombre de pixel, ils utilisent le maximum de coeurs disponibles. 

L'interface graphique est faite selon le design pattern MVC.

L'option zoom et déplacement sur l'image dans l'interface est possible.

