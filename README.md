# Gameplay-Studio

**1. Compiler et exécuter l'application.**

Après avoir installé Java, vous pouvez compiler l'application avec un IDE ou en ligne de commande, car ce programme s'ouvre dans une interface console.

Téléchargez tout d'abord le fichier .zip contenant l'application. Puis, décompressez-le où vous le souhaitez sur votre disque local.

**a. En ligne de commande.**
    
Ouvrez l'invite de commandes et dirigez-vous vers le répertoire de l'application :

_Sous Windows :_  
    
    cd C:\[yourdirectory]\gameplay_studio_win\src\main\java
    
_Sous Mac ou Linux :_    
    
    cd /[yourdirectory]/gameplay_studio_macos_linux/src/main/java
    
_Puis, tapez ces deux commandes :_

    javac com/cursan/gameplay_studio/Main.java
    
    java com/cursan/gameplay_studio/Main
    
**b. Avec un environnement de développement.**

Ici, nous prendrons l'exemple d'IntelliJ, mais la procédure est sensiblement la même pour tous les environnements de développement.

Allez dans File > Open, puis ouvrez l'application que vous avez décompressée.

Dans l'arborescence du projet à gauche, ouvrez la classe Main (src > main > java > com > cursan > gameplay_studio > Main.java), puis exécutez la méthode _main_. L'IDE compile puis exécute l'application.

**2. Règles du jeu.**

Globalement, le but du jeu est de trouver un code à X chiffres. Il y a trois modes de jeu possibles. Vous pourrez choisir un de ces trois modes au lancement de l'application et à la fin de chaque partie.

**a. Le mode "Challenger".**

L'ordinateur génère un nombre à X chiffres aléatoirement et vous devez deviner ce nombre. Vous disposez d'un certain nombre d'essais. Après chacun de vos essais, l'ordinateur vous répond, pour chaque chiffre que vous avez donné, si le chiffre de la combinaison est inférieur, supérieur ou égal.

**b. Le mode "Défenseur".**

Vous choisissez une combinaison de X chiffres et l'ordinateur doit la deviner. Il dispose d'un nombre d'essais limités et après chacun de ses essais, pour chaque chiffre, vous lui répondez si le chiffre de votre combinaison est inférieur, supérieur ou égal.

**c. Le mode "Duel".**

Vous jouez contre l'ordinateur l'un après l'autre. Le premier qui trouve la combinaison de l'autre gagne la partie.

**3. Spécifications.**

Certains points peuvent être spécifiés dans le fichier "config.properties" que vous trouverez dans src > main > java > com > cursan > gameplay_studio. Ces points sont : le nombre de chiffres dans la combinaison à trouver (_digits_), le nombre d'essais possibles en modes Challenger et Défenseur (_tries_) et le mode Développeur (_developerMode_) qui permet de voir la solution en cours de partie.

Si vous souhaitez modifier ces paramètres, ouvrez le fichier config.properties avec un éditeur de texte et changez leur valeur. Pour activer le mode développeur, donnez à _developerMode_ la valeur _true_ et pour le désactiver, la valeur _false_.

**Bon jeu !**