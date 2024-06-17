# INTRO
Dans ce projet nous avons fait une pointeuse entre deux appareils relier par une connexion TCP.
On y trouve une application centrale et une pointeuse.

# FONCTIONNEMENT
### L'application centrale à 4 onglets: 
* TimeCloking.cpp: sert à cocher (ou enlever) manuellement l'arrivée et le départ d'une personne (nom, prenom, planing, check-in,check-out).
* ***HistoryOfAllTime*** : regroupant tous les pointages fait depuis le tout début (nom,status du pointage,planing,heure du pointage,delta).
* ***EmployeeManagement*** : permettant l'ajout, la suppression et la modification d'un.e employé.e (nom,prenom,tel,ail,post....).
* ***Settings*** : pour faire le paramérage de la connexion TCP entre l'application centrale et la pointeuse.

> [!WARNING]
> L'affichage de TodayCloking ne se met pas à jour lors d'un pointage, il faut refermer l'application centrale pour voir le changement sur le status de validation de la personne
___________________
### La pointeuse :
* Un espace pour rentrer l'ID de lemployé
* un bouton pour valider la saisie.
  
### Model-view-Conroler : 
* ajouter
* ajouter

### TCP : 
* La communication TCP est utilisée pour assurer la transmission fiable des données entre l'application centrale et la pointeuse. 
* Protocole : Utilisation de TCP pour échanger les horaires de pointage et les configurations entre l'application centrale et la pointeuse. 
* TCPServer : Gère les connexions entrantes côté application centrale. 
* TCPClient : Connecte la pointeuse au serveur central pour envoyer les horaires de pointage. 
* Sécurité et fiabilité : Mécanismes intégrés pour assurer une communication sécurisée et fiable, incluant la gestion des erreurs et la reprise après incidents.


# PERSPECTIVES DU PROJET
> [!IMPORTANT]
> * Utilisation du département
> * Faire en sorte que l'affichage ***TodayCloking*** se mette à jours lors d'un pointage.
> * L'ajout du delta de la personne et l'affichage du delta unique d'un pointage sur ***HistoryOfAllTime*** en fonction de l'avance ou du retard.


