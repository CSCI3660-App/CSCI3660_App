# CSCI3660_App
Reminders app with calendar tie-in for CSCI3660 final project


Provided that all of the settings and gradle files were downloaded correctly, the emulator 
that was used during the development of this project is strongly recommended to run this app
as it was intented.


STEPS FOR DOWNLOADING PROPER EMULATOR:
-----------------------------------------------------------------
1. Navigate to "Device Manager" in Android Studio

2. Click the "Create devide" button

3. Select the Pixel 4a emulator and click "Next"

4. If not already downloaded, download the R/api 30 system image

5. Once the R/api 30 system image has been downloaded, select that system image
and select "Next"

6. Nativigate to the "Graphics" section and change the graphics to "Hardware - GLES 2.0"

7. Select "Finish" and run the app with the newly installed emulator


- The reason for doing this is because some of the applandeo.material.calendarview features need an
emulator without the google play symbol listed next to the device name in the AVD manager to be able to
run properly due to a android emulator error.
