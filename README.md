# GitCommitHistory
An Android application to retrieve commit history for a repo 

Steps followed in creating application
1. Created an Android project in Android studio with Empty activity
2. Added Recycler view to the layout with constrain as 1 and create List item layout
3. We will create a Recyler View Adapter 
4. We will populate recycler view with some dummy data -> To make sure UI work is done
5. We will get data from "https://api.github.com/repos/mojombo/grit/commits" and add depedant libraraies
	Retorfit, Rxjava, and GSON convertor
6. We will use dagger at the End, For now main goal is get data from end point and display it to user.
	a.) Added Retofit and Gson Instances to the activity.
	b.) Create POJO classes
	c.) Interfcae For API (FOr Fetch request)
	d.) Formulating API call through RX java printing out in the form of Logs, no update to View yet.
	e.) Added Internet permission
	f.) Disply the output by uisng data from API, For this replace String in List with "Data Class" that we created in Model 
7. Clean up MainActivy and Refactor code
	a) To access API application wide Create App class
	b)Initial Refactoring
8. Introduce Dagger 
	a) Add Dagger libraries
	b) Create An App module which will provide App level dependecies such as Retrofit and Gson
	c) Create App component which is a bridge between Module and dagger system
9.Dependecy Injection for adapter

