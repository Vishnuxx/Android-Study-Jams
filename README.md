# BusTopper (MCBT)

Bus Tracking app

<b> Problem Statement: </b>

With the rise in covid cases, it is unsafe to stay in public places for a long duration. Public bus stops can be a source of spreading in terms of covid infections, avoiding contact and exposure is the best way, but unfortunately, due to heavy traffic in the cities – college buses arrive at varied intervals of time, leading students to come 15-20 minutes earlier. We at Marian Engineering College have devised a solution for our fellow students. A Mobile Application that is accessible by all students using the bus service. 

<b>Proposed Solution:</b>

“BusTopper” – A College Bus Tracking System helps students to continuously track the buses available via their route and notify them of any changes in the bus route. We are building two separate interface for drivers and students where they can sign up separately and connect with each other. A End user can either sign in the “Driver” mode or “Student Mode’. 
<b>Driver Features:</b>
Provide vehicle information, Bus number. 
Provide starting and ending locations with appropriate time schedules according to google traffic API. 


![photo_2022-01-15_22-45-36](https://user-images.githubusercontent.com/74808440/149631320-caa3597b-dd0a-4f87-9a58-5bfcdfac867b.jpg)
![photo_2022-01-15_22-45-38](https://user-images.githubusercontent.com/74808440/149631323-26d9902a-a455-4335-a64d-61cd62453220.jpg)

<b>Student Features:</b>

Single tap-in app location tracking feature using Google Maps API so students can track the bus in real time. 
Set custom boarding points at any time within schedule and be updated with a ETA of the bus to their boarding point. 

<b>Functionality and Concepts Used:</b>

- Livedata & ViewModel : To seperate business logic and data MVVM
- Room Database : To store Driver boarding points and cached datas
- Fragments : To view multiple screens in single activity
- Jetpack Navigation :  To navigate between fragments
- Custom Stepper View : To show boarding points as step by step
- Firebase Database and Auth : Used to connect and network between users (Drivers and Students)
- GoogleMaps : To preview bus location in Map
- Kotlin corutines : To process asynchronous data 
- Different types of ViewGroups and Views : To make interactive ui elements
- Location API : To get Gps position and speed values


<b>Application Link & Future Scope:</b>

The application is current in the alpha testing phase in our campus with a limited number of students with access.
</br>
Application Link: <a href="https://drive.google.com/file/d/1dYm9PkK9M-osGp7LUqbZNY6XVI8aliOX/view?usp=drivesdk"> App Link </a>

Once the application has been tested with all drivers and students in the test phase, we will begin rolling it out for all the students on campus and bundle it along with our academic tracker application. 

We aim to implement the following features by the end of this year as updates:
- Automatic Attendance Tracking System using NFC Stations on Bus.
- Bus Fee Payment System Integrated with the application. 
- Emergency Alert System
- Notifying Driver about skipping a student boarding point. 
- Informing Campus about late arrival with new ETA.
- Informs the nearby buses in emergency situations
- Build NFC and QR code system
- Build a new category of user which will be mainly used by bus administrators and institution owners to schedule and control drivers
- some security improvements and enhanced backend system design

