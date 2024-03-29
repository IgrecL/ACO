
# Algorithme de colonie de fourmis (*Ant Colony Optimization*)

## Présentation

Programme simulant le déplacement de fourmis basé sur le principe de *stigmergie*.
Interface graphique et panel de commandes.
Edition de cartes et modification des paramètres de visualisation.
Réalisé dans le contexte d'un projet scientifique d'algorithmie mettant à l'épreuve nos connaissances en informatique et en Java en deuxième année à l'**INSA Lyon**.

### Classes

- La classe ***Carte*** est une classe essentielle contenant l'ensemble des éléments de la carte, les variables de réglages et permet la gestion ainsi que l'interaction de l'utilisateur avec la carte.
- La classe ***Element*** dont d'autres classes dépendent permet d'accéder et de modifier la position de l'élement.
- La classe ***Fourmi*** contient les variables et fonctions communes aux 2 types de fourmis. Toutes les méthodes caractérisant le comportement de la fourmi y sont présentes.
- La classe ***FourmiA*** prolonge *Fourmi* tout en y ajoutant la méthode pour calculer le déplacement vers la nourriture.
- La classe ***FourmiB*** prolonge *Fourmi* comme précédemment mais calcule le déplacement vers la fourmilière.
- La classe ***Fourmiliere*** gère l'élément de la fourmilère.
- La classe ***GenerateurObstacle*** crée un nuage de points que l'on relie et rempli pour former un obstacle de manière aléatoire.
La carte aléatoire nécessite de passer par une autre carte puis de revenir dessus et valider pour obtenir de nouveaux obstacles.
- La classe ***LecteurCarte*** permet l'utilisation des fichiers *.txt* pour réaliser des cartes plus efficacement.
Pour exporter, il est nécessaire de préciser l'extension en sauvegardant en "*nom.txt*".
Pour importer, ouvrez le fichier correspondant.
- La classe ***Main*** permet l'éxécution du programme.
- La classe ***MainWindow*** est une classe essentielle permettant de créer et de mettre à jour la fenêtre où notre programme se déroule.
- La classe ***Nourriture*** gère l'élément de nourriture.
- La classe ***Obstacle*** permet la création d'obstacles grâce à des points et nous renvoie les murs issus.
- La classe ***Pheromone*** assure l'existence des éléments déposés par les fourmis et paramètre leur comportement.
- La classe ***Segment*** contient quelques méthodes essentielles au calcul du déplacement vectoriel comme l'existence ou non de points sécants.
- La classe ***Son*** est nécessaire au bruitage.
- La classe ***Vecteur*** est une classe nécessaire assurant les calculs vectoriels pour le comportement de déplacement des fourmis.

### Cartes

1. La carte par défaut permet de prendre en main le programme avec des bordures et des obstacles prédéfinies.
2. La carte "*Double Pont*" valide le *Double Bridge Experiment*, en montrant que le chemin le plus court sera choisi à partir d'un certain moment par les fourmis.
3. La carte aléatoire génère 3 obstacles aléatoirement. Pour ce faire, un nuage de point est généré via une loi normale, puis elles sont reliées et forment un polygone convexe à l'aide d'un algorithme d'enveloppe convexe. Le placement de ses obstacles est réalisé en prenant en compte les autres points lors de leur création afin d'éviter des situations de conflit.
4. La carte est vierge est un terrain vide.

### Ressources

Différentes assets sont utilisés :
- **cartes** contient les fichiers *.txt* qui sont lus par *LecteurCarte*.
- **images** contient les images *.png* utilisés dans tout le code pour les fourmis, le fond et les icônes.
Elles sont soit des images libres de droits ou réalisées en tant que *pixel art* par nous-mêmes.
- **sons** contient *.wav* des bruitages faits à l'aide d'un micro et du logiciel *Audacity*.
