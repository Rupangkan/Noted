# Android-Study-Jam

# Noted

![X1](https://user-images.githubusercontent.com/64887274/148688047-62386163-7cac-4fc3-970d-60853056bfae.jpg)
![X2](https://user-images.githubusercontent.com/64887274/148688077-67065389-5dd7-4d33-bec0-2bec08e4c5ce.jpg)
![X3](https://user-images.githubusercontent.com/64887274/148688088-0d47c110-8780-4303-ab81-856d49740534.jpg)
![X4](https://user-images.githubusercontent.com/64887274/148688096-16e7d2b0-3e51-40b3-9860-1bb4e0c10dae.jpg)


### Problem Statement: 

Over a great period, many issues have been faced by our students getting question papers and notes to prepare for their exams. Some store near colleges have previous years question papers and notes but they are not free of cost.
Technology plays a vital role in day-to-day life activities which in turn made great changes in many work fields and out of them Mobile Application is one of the major developments. Mobile Application can be used effectively for this job as they are widely used and are known for easy access.

### Proposed Solution : 

This project proposes a “Question Papers Sharing System” to keep track of our College/University question papers and provides it to the students free of cost. Its features include integrated PDF Viewer for ease of viewing the papers and a Starring system which helps students to star their preferred files and keep them in the Starred section for ease of access. This application uses Firebase's Storage system to Store the papers in the Database. Currently the app works for the engineering studenst of ASTU university and the papers are being uploaded constanty. The project's scope is to not only share question paper for the specific departments but also upload notes section which is being made in progress and also upload resources for entrance exams for engineering students.

<!-- <img width="559" alt="sampleimages" src="https://user-images.githubusercontent.com/18289261/142846646-a6858641-ad88-43aa-b8bb-b690fd7126f1.png"> -->
    	  	
### Functionality & Concepts used : 

- The App has a very simple and interactive interface which workes pretty well under both Dark Mode and Light Mode as shown in the App images. The App's simple interface allows the students select their desired Course and their desired Semester. The App allows users to starred their preferred files for ease of access. Following are few android concepts used to achieve the functionalities in app : 
- Constraint Layout : Most of the activities in the app uses a flexible constraint layout, which is easy to handle for different screen sizes.
- Simple & Easy Views Design : Use of familiar CardViews and Icons made it easier for students to select and and click on their desired Courses and Semesters. Apps also uses App Navigation to switch between different screens. 
- Fragments : Most of the App screens uses Fragments to travese from one Frament to another. The App uses an integrated PDFViewer to show the contents of the PDF which is done in a new Activity.
- Animations: Traversing through the App wouldn't feel good without animations, so traversing through the Fragments contains sliding in and out animations.
- RecyclerView : To present the list of Courses, Semesters and Papers, RecyclerView with GridViewLayout with two columns are used with CardViews to present different selections. The App also uses a FloatingActionButton on almost every Fragment for ease of access to the Starred section where students have saved their preferred files/question-papers
Firebase Storage : The App also uses Firebase Storage free version for storing the question papers and hopefully notes in the future. In future if the user base increases and the question papers and notes storage exceed the capacity of the free storage I will go for the upgraded plan for the Firebase Storage too.
- LiveData & Room Database : The App also uses LiveData to update & observe the Course, Semester and PDF Name selected by the User. The LiveDate also keeps track of the path of the PDF name for the Firebase Storage. The PDF's which are starred have their PDF name and Path which are updated in the local Database using Room. The Starred PDFs are then shown the Starred Fragment and can be removed by unstarring them as well.

### Application Link & Future Scope : 

The app is currently in the Beta testing phase with a limited no. of users from Assam Engineering College, You can access the app : [YOUR APP LINK HERE](either Github link or Google Play store link of published app or .apk file).
The app currently holds question paper for some departments and some semesters (Computer 3rd Sem, Chemical 5th Sem, etc) and the papers are being uploaded regurlary. Noted currently uses integrated PDFViewer to view the contents of the PDF but in the future it will also provide download feature for all it's files free of cost.
Noted provides question papers to the Engineering departments under ASTU but in the future it will also provide notes of subjects for better prepartions of the students.
Once the app is fully tested and functional in Assam Engineering, the next step is to launch it so the college under ASTU can use it for their uncomming preparations. We aim that by next 6 months most of the students under ASTU will use Noted to prepare for their upcoming exams. 
