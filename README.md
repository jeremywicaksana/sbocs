# Intro
This application is a lost and found simulation. The input of the data entry when losts item are added can be found
in "src/main/java/com/example/springbocs/entry". The data input from the entry directory then will be stored in an 
in-memory database H2. Keep in mind everytime the application is booted it will always refresh the database based on
the DDL script.

within this application can be run locally. Currently, there are 4 Rest apis available within this application. Further
explanation can be found in swagger API after the application is triggered.

# Requirements
- Using any kind of IDE (Intellij recommended)
- Using JDK version 17
- ensure maven is installed properly (recommended, using apache-maven-3.9.7)

# How to use the application
1. Try to ensure the java folder under test is marked as test source
2. ensure java folder under main is marked root source
3. install all of the dependencies under pom.xml by running 'mvn clean install'
4. Run the application by running 'mvn spring-boot:run' or run it through IDE GUI
5. Application is ready to be used.

# Sample on how to use
Once booted, there will be 3 table called activity, lost_item, and person. Person is the only table that is populated
with a mock user while the rest of the table are empty. Since the table is empty there is a standard directive that could be followed:
1. Populate the lost_item table with pdf file by triggering '/api/v1/lost-item/add'
2. the item then can be seen under 'api/v1/lost-item/all'
3. any user can claim the lost item in 'api/v1/lost-item/claim' endpoint
4. any claim rest api executed then it will be stored in activity table which can be found in 'api/v1/activity'

There is an example of api collection within the same directory of readme that is called as 'sbocs.postman_collection.json'
or use Swagger Api.

# References
- H2 database: http://localhost:<port>/h2-console
- Swagger API: http://localhost:<port>/swagger-ui/index.html 
- DDL script: 'src/main/resources/data.sql', This script will always execute whenever the app is booted
