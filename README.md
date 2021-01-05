# TodoApp

This app is used in a daily life as reminder for performing a task at a particular time.
 
# Introduction

The app helps user in their daily life to keep track or remind them of important tasks or schedule a reminder. The app can also be used as reminder for small things. The app is developed using MVVM design patterns. The app uses recycler view to show the list of task in dashboard. The app also uses fragment in main activity. The application uses sqlite to store datas which does not need extra database application. The app also gives notification at a scheduled time and date. The app allows editing and deleting task as well through click and swipe.

 
 # Architecture
 The architecture that was used to develop the application is MVVM which is shown below.
 
 ![](architecture.png)
 
 The model part contains database related class such as DAO, Task Model, Task Database class. The repository class is also related with model but it is the class that helps communicating between Model and View Model class. The viewmodel class provides live data to the View part or UI which basically include activity or fragments.
 
 
