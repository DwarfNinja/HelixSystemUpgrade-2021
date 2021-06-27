READ-ME
================

Installation Guide
--------------------
### Prerequisites
* Java version 16 or higher
* Apache Tomcat version 9.0.45 or higher
* An IDE or development environment of your choice.

### Step-by-step guide

In this step-by-step guide I will be making use of JetBrains IntelliJ IDE, 
but the steps should be similar for other IDEs.

* Step 1. [Download](https://github.com/DwarfNinja/HelixSystemUpgrade-2021) or clone the project from GitHub.  
  (If you clone the project you can skip to Step 3.)
  
  ![Download zip from GitHub](documentation/resources/ReadMe/Installation/GitHub-Project-Download.png)

<hr>

* Step 2. Unpack the downloaded zip to your folder of choice.
  
  ![Extract the zip folder](documentation/resources/ReadMe/Installation/Extract-Zip.png)
  
<hr>

* Step 3. Open the project with your IDE or code editor of choice.
  
  ![Open the project](documentation/resources/ReadMe/Installation/Open-Project.png)
  
<hr>

* Step 4. Let Maven install all needed dependencies and plugins.\
  Install ⟶ Validate
  
  ![Maven Install and Validate](documentation/resources/ReadMe/Installation/Maven-Install.png)

<hr>

* Step 5. Setup Tomcat configuration. \
  Create a Local Tomcat configuration
  
  ![Create a Local Tomcat config](documentation/resources/ReadMe/Installation/Tomcat-Local.png)

  Set up the configuration as shown in the example below. Note the underlined elements.
  ![Configure Tomcat](documentation/resources/ReadMe/Installation/Tomcat-Configuration.png)

<hr>

* Step 6. Run the application.

![Run the application](documentation/resources/ReadMe/Installation/Run-Application.png)

<hr>

How to use the application
---------------------------
### - General
All pages, except the log-in page have two buttons on the header of the page, the left button (inbox icon)
can be clicked to be brought to the notifications page. Next to the notifications button there is a button that 
can be used to log out of the logged-in account (arrow moving away from a door).

<hr>

### - Log In page
Log in with one of the accounts noted in [all-accounts.json](src/main/resources/json/all-accounts.json) and 
click on the button to log in (arrow moving towards a door). \
For ease of use I have provided some account details below.

**User account:** \
Username: John Doe \
Password: johndoePassword

**Admin account:** \
Username: admin \
Password: admin

![Log-In Page](documentation/resources/ReadMe/Pages/Log-In.png)

<hr>

### - Account page
On the left side you can view a users profile and account details and on the right-hand side you can 
view a list of all the HelixSystems a user has access to. You can click on one of the HelixSystems to go
to view their inventory.

![Account Page](documentation/resources/ReadMe/Pages/Account.png)

<hr>

### - HelixSystem Inventory page
Here you can view the inventory of a HelixSystem with all the products that HelixSystem holds. Clicking on 
one of the products allows you to view more details of that product on the product page.

![HelixSystem Inventory Page](documentation/resources/ReadMe/Pages/HelixSystem-Inventory.png)

<hr>

### - Product page
This is the product page, here you can view more details about the product in question. At the bottom half of the
page you can also view recommendations of other products related to this product.

![Product Page](documentation/resources/ReadMe/Pages/Product.png)

<hr>

### - Notifications page
When you press on the "notifications" button you will arrive at the notifications page, 
here you can view the notifications of a user. By pressing on the left button (bel icon) on a 
notification a user can mark that notification as interesting. 
A user can also delete a notification by pressing the right button (trashcan icon).

![Notifications Page](documentation/resources/ReadMe/Pages/Notifications.png)
