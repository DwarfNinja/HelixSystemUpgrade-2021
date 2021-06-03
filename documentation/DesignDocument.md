Ontwerp
================
Helix System Upgrade
--------------------

&nbsp;

Datum : 16-05-2021\
Versie : 0.2\
Auteur : CendurOyib

&nbsp;

###Revisiehistorie

| **Datum**  | **Versie** | **Omschrijving** |
|------------|------------|------------------|
| 16-05-2021 |    0.2     |                  |
|            |            |                  |

##Inleiding
Neem hier op hoe het ontwerp tot stand gekomen is alsmede voor wie het bedoeld is 
met eventueel een lezersadvies.

##Overzicht
Korte beschrijving wat het systeem geacht wordt te doen. Eventueel indeling in subsystemen 
met een korte beschrijving van een subsysteem.

##Use cases
![Use Case Diagram](resources/Use Case Diagram.png)\
Use case diagram met een korte toelichting. Zie bij de cursus modelling hoe je dit model maakt.

##Actoren
Bij een actor hoort ook een beschrijving, de actor description. In een actor template wordt 
naast de rolnaam een beschrijving van de rol ingevuld en daarmee een role description geschreven.

##Use Case Templates
Use case templates, per use case een paragraaf met de template beschrijving e.d. zoals geleerd 
bij Modelling.

##Wireframes
De wireframes bij de use cases.

##Domeinmodel
![Use Case Diagram](resources/Domain Model.png)\
De meeste entiteiten uit het conceptueel datamodel hebben een tegenhanger in de vorm van 
een JAVA domeinklasse. Van de student wordt verwacht dat er een UML klassen diagram wordt 
opgeleverd voor de domeinklassen. Zorg dat je UML klassendiagram volledig is.  

### - Log into account
|  **ID**              |  0                                                             |
|----------------------|----------------------------------------------------------------|
|  **Name**            |  Log into account                                              |
|  **Actors**          |  Users                                                         |
|  **Description**     |  Logging into the account tied to the user.                    |
|  **Pre-condition**   |  The actor has an account registered to their name.            |
|  **Scenario**        |  1. The actor navigates to the log in page. <br> 2. The actor enters their user name and password in the specified fields. <br> 3.The system validates and authenticates the actor. <br> 4.The system brings the actor to their account page.  |
|  **Post-condition**  |  The actor has logged into their account.                      |

### - View account
|  **ID**              |  1                                                                    |
|----------------------|-----------------------------------------------------------------------|
|  **Name**            |  View Account                                                         |
|  **Actors**          |  Users                                                                |
|  **Description**     |  Viewing the account tied to the user .                               |
|  **Pre-condition**   |  The actor is logged in.                                              |
|  **Scenario**        |  1. The actor navigates to the account page. <br> 2. The actor can view their account.  |
|  **Post-condition**  |  The actor has viewed their account.                                  |

### - View inventory of HelixSystem
|  **ID**              |  2                                                             |
|----------------------|----------------------------------------------------------------|
|  **Name**            |  View inventory of HelixSystem                                 |
|  **Actors**          |  Users                                                         |
|  **Description**     |  Viewing the products and contents of a  HelixSystem.          |
|  **Pre-condition**   |  The actor has logged in, and has the rights to view the inventory of the chosen HelixSystem.
|  **Scenario**        |  1.The actor clicks on the HelixSystem inventory they want to view. <br> 2. The system brings the actor to the chosen inventory page. <br> 2. The actor can view the products in the chosen HelixSystem. |
|  **Post-condition**  |  The actor has viewed the products in the chosen HelixSystem.  |

### - View product
|  **ID**              |  3                                                                    |
|----------------------|-----------------------------------------------------------------------|
|  **Name**            |  View product page                                                    |
|  **Actors**          |  Users                                                                |
|  **Description**     |  Viewing a specific product page in the inventory of the HelixSystem. |
|  **Pre-condition**   |  The actor is on the chosen HelixSystem inventory page.               |
|  **Scenario**        |  1. The actor clicks on the specific product they want to view. <br> 2. The system brings the actor to the chosen product page <br> 3. The actor can view the chosen product.
|  **Post-condition**  |  The actor has viewed the product page the want to view.              |


## Wireframes
### - Log-in Page
![Domain Model](resources/Wireframes/Log-inPage.png)
### - Account Page
![Domain Model](resources/Wireframes/AccountPage.png)
### - Inventory Page
![Domain Model](resources/Wireframes/InventoryPage.png)
### - Product Page
![Domain Model](resources/Wireframes/ProductPage.png)
### - Notifications Page
![Domain Model](resources/Wireframes/NotificationsPage.png)

## Domain-model
![Domain Model](resources/DomainModel.png)

| **Entiteit**  | **Beschrijving** |
|---------------|------------------|
|  Hello World  |    Hello World   |
|  Hello World  |    Hello World   |

|  **Entity**     |  **Discription**                                                     |
|------------------|---------------------------------------------------------------------|
|  System          |  The main system, which has all the HelixSystems and Accounts.      |
|  HelixSystem     |  The HelixSystem class which holds all the data of a HelixSystem.   |
|  Account         |  The Account class which holds all the data of an account.          |
|  Product         |  The Product class, that holds all the data of an certain product.  |
|  InventoryEntry  |  The InventoryItem class, this class represents an InventoryEntry that can be added to a HelixSystem |

##Technologieën
Voor het realiseren van de applicatie wordt gebruik van de volgende methodieken 
en technieken: UML, Java, SQL, HTML, CSS, J2EE (Servlets), Rest (Jax-RS), Applicatieserver
& HTTP-protocol en een datastore. Beschrijf ook welke frameworks je gebruikt, 
hiervan neem je ook de versie en het licentiemodel op.

##Overdracht
De InstallatieHandleiding is nog niet beschikbaar

##Referenties
Geef hier de bronnenlijst. Gebruik de APA stijl om de bronnen te vermelden.
