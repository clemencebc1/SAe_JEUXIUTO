# SAe_JEUXIUTO
developpement du modele de l'application JEUX IUTO dans le cadre de la SAe 2.01

Membres :
Clemence bocquet
Ambroise Boutrin
Lucas De Oliveira
Matheo Lobjois
Alexis TRY

compiler : javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -d bin/ src/bd/*.java src/participant/*.java src/sport/*.java src/autre/*.java src/comparateur/*.java src/vue/*.java
executer : java -cp ./bin:/usr/share/java/mariadb-java-client.jar --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls vue.FenetreAccueil
