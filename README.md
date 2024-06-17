# INTRO
Dans ce projet nous avons fait une pointeuse entre deux appareils relier par une connexion TCP.
On y trouve une application centrale et une pointeuse.

# COMPOSITION
L'application centrale à 4 onglets: 
* ***TodayClocking*** : sert à cocher manuellement l'arrivée et le départ d'une personne (nom, prenom, planing, check-in,check-out).
* ***HistoryOfAllTime*** : regroupant tous les pointages fait depuis le tout début (nom,status du pointage,planing,heure du pointage,delta).
* ***EmployeeManagement*** : permettant l'ajout, la suppression et la modification d'un employée (nom,prenom,tel,ail,post....).
* ***Settings*** : pour faire le paramérage de la connexion TCP entre l'application centrale et la pointeuse.

La ponteuse :
* Un espace pour rentrer l'ID de lemployé
* un bouton pour valider la saisie.
  
Model-view-Conroler : 
* ajouter
* ajouter

TCP : 
* ajouter
* ajouter

# FONCTIONNEMENT
Lors de l'ouverture de l'application centrale on se trouve sur la page TodayCloking avec la liste des employés ansi que l'information de si la personne à validé son pointage ou non.
Manuellement on peut changer le status du CHECK-IN et du CHECK-OUT

