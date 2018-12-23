# Facial_Verification_System_OrganizerApp

System Design and Implementation

FEATURES

Interactive mobile application to be used by organiser : Intuitive and easy to use
Facial recognition : Fast & accurate, Mobile devices already have camera, User Interface to display verification
Multi-factor authentication : Higher security

Our solution comes in three parts, a registration app, facial recognition model and backend app for administrators. Firstly, the registration app records the registrant’s details (an image and ID) by taking a picture of registrant as well as recording down their ID. The image will be uploaded to the Publit.io platform via their API and the URL created will be sent over to firebase.
This information is then sent over to Firebase and then analysed on the Python backend, with the Facenet model. After a result is obtained, the result is sent back to the registration app so that the user knows he is completed with the process. Lastly, we have another native Android application for administrators at the event. This app tracks the attendance of all registrants that are already present. This application also allows the administrator to do a manual confirmation of the registrant’s identify should the algorithm fail.

THIS APP: Administrator App

The administrator’s App aims to give an overview of the Event for which registrations are currently taking place. Organizers can keep track of every individual that has been registered, or has been rejected. Furthermore, the organizer can Manually Verify a registrant in the case where his/her face has been rejected by the facial verification model. Hence, enabling a multi layer verification system

The Administrator App has the following functionalities:
A home page that gives an insight into the event that is taking place
‘Registered’ page, that provides a ListView of individuals that have been verified, along with the ID and type(Student, Staff, Alumni)
Manually Registered List that provides a list of individuals that have been registered by the organizer himself
‘Blacklist’ page, that provides a ListView of individuals that have been rejected by the organizer due to impersonation(imposter)
Provides a platform that displays a list of individuals that have been rejected by the Facial Verification Model. Further, displays the Name, ID, and Photograph of individual to be manually verified for the organizer
Provides an overview of number of Students, Staff and Alumni present at the event

