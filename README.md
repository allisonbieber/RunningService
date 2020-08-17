# RunningService
This repository is the backend service for my full stack Running App. The purpose of this application is to use APIs to obtain data from a 
local database and load them into a table in order to analyze the data. This application uses Spring Boot and Java for the backend. This project is
currently in progress. 

# Database

The database, 'events' that I have created using MySQL represents a single 'run' event. The features are as follows: 
  * Miles (double) - The number of miles ran
  * Elevation (double) - The total elevation gain
  * Hours (int) - The hours taken to complete the run
  * Minutes (int) - The minutes, after deducting hours, taken to complete the run
  * Seconds (int) - The seconds, after deducting hours and minutes, taken to complete the run
  * Pace (double) - The average pace during the run
  * Relative Effort (int) - The relative cardiovascular effort in relation to previous activities completed
  * Average Heart Rate (int) - The average heart rate for the duration of the run
  * Date (datetime) - The date of the run
  

# Sources Used

* https://medium.com/@mukundmadhav/build-and-deploy-react-app-with-spring-boot-and-mysql-6f888eb0c600

