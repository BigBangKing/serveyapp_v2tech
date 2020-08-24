# surveyapp_rifatmahmud_v2tech

Goal:
**Develop a Survey App that allows a user to submit multiple surveys and store them.**
* Create a Project for a mobile phone
* Platform - Android (Kotlin preferable)

Functional Requirements
* Data fetched from a server. See below example request.
* Send timestamp in the header while fetching the data.
* Users must be able to see a survey form based on the format given in the API .
* Users will not be able to submit the form if required fields are not filled.
* Users can save multiple surveys. 
* Submitted surveys should be saved in cache or any localDB. (Saved on local DB.)
* Users should be able to see the previous submitted forms in a dashboard like manner.

What can be found on this project:
* An App that meets the Functional Requirements above
* Architecture, structured code, reusability.

This project was tested with following request:
[{"question":"Do you like our product ?","type":"Checkbox","options":"Yes, No","required":true},{"question":"How are you ?","type":"multiple choice","options":"Very Good, Good, Neutral, Bad, Very Bad","required":true},{"question":"Where do you live ?","type":"text","options":"","required":true},{"question":"How frequent you use our product ?","type":"dropdown","options":"Very often, often, Moderate, Not much, Never used","required":true},{"question":"Your contact number ?","type":"number","options":"","required":false}]


Screenshots:
![Home Screen](https://github.com/BigBangKing/serveyapp_rifatmahmud_v2tech/blob/BigBangKing-patch-1-Screenshots/device-2020-08-24-104803.png)

![SurveyScreen](https://github.com/BigBangKing/serveyapp_rifatmahmud_v2tech/blob/BigBangKing-patch-1-Screenshots/device-2020-08-23-021126.png)
![SurveyScreen](https://github.com/BigBangKing/serveyapp_rifatmahmud_v2tech/blob/BigBangKing-patch-1-Screenshots/device-2020-08-23-021059.png)

**Feedback is much appreciated.**
