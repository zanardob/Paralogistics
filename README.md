# Paralogistics

### UI Management
The following packages have the classes for showing and controlling the interface of the software using javafx and MVC pattern.

##### model
Contains the models needed. The models are a translation from the database's table for simplicity.
##### view
Contains the ".fxml"s files for the views.
##### controller
Contains the controllers for the each scene created. The controller determines the scene's behavior and changes scenes.

The three packages have three corresponding sub-packages with the related models, views, controllers: insertions, queries, and viewtables

### Database Access
##### database
All objects that access the database are in the "database" package. The class *DatabaseManager* is responsible for connecting with the database. The database accessing classes do all the interested "selects" and "inserts" refering to it. The class's name follows the pattern _**modelname**DAO_ and has several methods with the respective SQL queries.

### SQL
##### sql
All SQL queries are in the sql folder. The data definition and inserts to populate the database.
Project for the Databases Class at USP São Carlos
