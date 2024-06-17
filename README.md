# INTRO
Dans ce projet, nous avons créé une pointeuse et une application centrale de gestion pouvant être situées entre deux appareils communiquant par une connexion TCP.

# FONCTIONNEMENT
### L'application centrale contient 4 onglets: 
* ***TodayClocking*** : sert à consulter l'état des pointages quotidiens de tous les employés, et à cocher (ou décocher) manuellement l'arrivée et le départ d'une personne (nom, prenom, planing, check-in,check-out).
* ***HistoryOfAllTime*** : regroupe tous les pointages faits depuis le tout début (nom, statut du pointage, planning, heure du pointage, delta).
* ***EmployeeManagement*** : permet l'ajout, la suppression et la modification d'un.e employé.e (nom, prénom, tel, mail, poste...).
* ***Settings*** : pour faire le paramérage de la connexion TCP entre l'application centrale et la pointeuse.

> [!WARNING]
> L'affichage de TodayCloking ne se met pas à jour lors d'un pointage, il faut refermer l'application centrale pour voir le changement sur le status de validation de la personne
___________________
### La pointeuse :
* Un espace pour rentrer l'ID de lemployé
* Un bouton pour valider la saisie.
  
### TCP : 
* La communication TCP est utilisée pour assurer la transmission fiable des données entre l'application centrale et la pointeuse. 
* Protocole : Utilisation de TCP pour échanger les horaires de pointage et les configurations entre l'application centrale et la pointeuse. 
* TCPServer : Gère les connexions entrantes côté application centrale. 
* TCPClient : Connecte la pointeuse au serveur central pour envoyer le pointage.
* Sécurité et fiabilité : Mécanismes intégrés pour assurer une communication sécurisée et fiable, incluant la gestion des erreurs et la reprise après incidents.

### Lancer le projet
Nous avons créé dans IntelliJ deux configurations de lancement : Pointeuse et Application Centrale. Les applications peuvent être lancées dans l'ordre que l'on veut, sans nécessité que l'autre soit ouverte aussi.
# CHOIX DE CONCEPTION
### Design pattern Model-View-Controller

Nous avons passé plusieurs séances à réfléchir à l'implémentation du pattern MVC, pour finalement obtenir la structure actuelle :
* Vue : contient l'entièreté de ce qui concerne de près ou de loin JavaFX
* Modèle : contient donc les structures de données de base (employés, pointages, entreprise, etc)
* Contrôleur est un peu spécial car il fait aussi office de "sérialiseur". Comme la quasi totalité des échanges de données entre la vue et le modèle concernent la sauvegarde des données, nous avons fait en sorte que ces échanges consistent à sérialiser les données lorsqu'elles sont modifiées, et à les désérialiser lorsqu'il faut les afficher. Tout cela est encapsulé dans les classes EmployeeData et TimeClockingData qui servent donc d'intermédiaire entre la vue et le modèle.
Dans le code cela se traduit par 3 packages : View, Model, et Serialization (qui je le rappelle est le contrôleur).
Un dernier package TCP existe pour encapsuler tout ce qui concerne l'échange entre les applications.

# PERSPECTIVES DU PROJET
> [!IMPORTANT]
> * Utilisation du département
> * Faire en sorte que l'affichage ***TodayClocking*** se mette à jours lors d'un pointage.
> * L'ajout du delta de la personne et l'affichage du delta unique d'un pointage sur ***HistoryOfAllTime*** en fonction de l'avance ou du retard.
> * page ***Settings***.


