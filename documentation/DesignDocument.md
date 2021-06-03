Ontwerp
================
Helix System Upgrade
--------------------

&nbsp;

Datum : 16-05-2021\
Versie : 0.2\
Auteur : CendurOyib

&nbsp;

### Revisiehistorie

| **Datum**  | **Versie** | **Omschrijving** |
|------------|------------|------------------|
| 16-05-2021 |    0.2     |                  |

## Introduction
[ TO BE TRANSLATED TO ENGLISH ]\
Mijn vader is sales manager bij Promega, een laboratorium apparatuur leverancier voor biochemie en
microbiologie. Nu beschikt Promega beschikt over een systeem genaamd Helix, hiermee kunnen
doctoren, laboratorium ingenieurs, laboranten etc. in de life-science online zien wat voor producten op
voorraad zijn en eventueel als een product niet in het systeem zit, kan deze ge-request worden.

Momenteel beschikt het Helix Systeem nog niet over een notificatie systeem die een notificatie stuurt naar de
gebruiker nadat een product dat hij/zij heeft ge-request in het systeem is en zou Promega daarnaast ook
nog een “recommendation systeem” willen, dat op basis van het geselecteerd product andere producten
aanraad die bij dezelfde workflow horen.

Om dit te kunnen maken moet er ook een simpele versie van het huidig Helix systeem worden
nagebouwd, dit houdt in dat de gebruiker kan inloggen op zijn/haar account, kan zien wat hij/zij eerder
uit het Helix Systeem heeft gehaald, producten die niet in het systeem zitten kan requesten en de
huidige voorraad van het Helix systeem zien.
Nu is er al een tijdje gepraat over de implementatie van deze features, maar is Promega er nog niet uit of
ze dit willen implementeren. Het idee is dat na het developen van dit project mijn vader mijn project zal
voorleggen (en eventueel mij het laten presenteren/demonstreren) bij de CEO van de Benelux als
voorbeeld van wat de officiële implementatie zou kunnen zijn.

##  Overview/Rundown/Summary

## Use Cases
Below you can see the Use Cases of this project, with all the actions and events a user has access to.\
![Use Case Diagram](resources/UseCaseDiagram.png)

## Actors

## Use Case Templates

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

### Business Rules:
#### System:
- The `helixSystemList` must only contain unique HelixSystems.
- The `accountList` must only contain unique Accounts.
- Generated Accounts by `generateAccountList` can not be duplicates of one another.
- Generated HelixAccessList by `assignRandomHelixAcces` can not contain duplicates of the same HelixSystem.

#### HelixSystem:
- The `name` of the HelixSystem must be unique.
- The `inventoryList` can only contain unique InventoryEntries.
- `getInventoryEntryByID` should return an InventoryEntry or null if it does not exist.
- Each `InventoryEntry` generated by `createRandomInventoryEntry` must contain a Product.

#### Account:
- The `accountID` of an Account must be unique.
- The `helixAccesList` must not contain duplicates.
- `addHelixAcces` can only add to `helixAccesList` if it does not contain the given HelixSystem.

#### Product:
- The `productID` of the Product must be unique.
- The `productPrice` must be a number rounded to two decimal places.

#### InventoryEntry:
- The product must exist and can not be null.
- The amount must exist and must be higher than 0.

## Technologies

## Manual/Handbook/Guide

## References
