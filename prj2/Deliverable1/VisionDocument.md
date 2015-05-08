# Vision Document

## 1 - Introduction
Thanks to the raving success of the TODO On The Go in the Android ecosystem there arose a desire to expand. In order to capture a wider user base and foster continued growth in usage of the application, TODO On The Go will become a fully fledged online productivity suite. Along with the current features of the application a web version will be created. These tools will mirror each other in every way in order to promote a seamless experience between the two. The following document will detail this proposed expansion made possible by the people of Initech. This will be a very high level look at the new applications and provide the best starting point to understanding the goals of the final product that are detailed is subsequent documents. 
 
## 2 - Business Needs/Requirements
In order for this project to be a success several items must be addressed. Any and all needs will need to be approved by all involved parties which include the development team, the guidance team, and the clients. In the next few sections these needs will be broken down into the needs of each major component of the system followed by the system itself.

### 2.1 - The Android App
* The app will need to be modified requiring detailed planning and effort from developers.
* The updated app may require re-apporval for it's inclusion in the app store involving a brand new submission process.
* Testing the system will be far more expansive to cover all possible events that may occur with the apps interaction in the system as a whole.
### 2.2 - The Web Application
* The web application will need to be adapted from the Android App or designed from the ground up requiring significant developer commitment.
* The web application will need a place to reside once deployed, be it an in house server or one from an external provider.
* The application will require dedicated support ensuring it is always available for use online. This will require a way to inform users of any predictable down time.

### 2.3 - The TODO On The Go System
* Data persistence will need to be established in order to create the desired synchronicity between the Android app and web application. This may require significant effort in order to ensure for a successful deployment of the system.
* Considerable time and effort will be needed in order to ensure the integrity of the user data as well as making it readily recoverable in a worst case scenario.
* Any contact from the users will need to be consolidated from the two major components into a single place. This should include any user reported bugs, informational e-mails, etc.

## 3 - Product Overview
The rapid success of the TODO On The Go app was not without issue. There were several instances where the user base found the app lacking. This included migrating to a new device while preserving information from the app, accessing the app on multiple devices, and sharing list information. The idea of the web application was born from these needs.  The new system would need to retain its current feature set in both versions of the tool. From creating users to managing tasks this tool must not have any significant changes in the way that it works in order to provide an easy transition for the user into the new system. Moreover, it is clear that a TODO list not only has to be portable, but highly accessible. Each component of the new system too needs to provide a seamless experience. It should be as if you cannot tell the two applications apart. Such a system would garner the attention of a wider audience and make for a productivity application to rival that of our competitors. The needs of the user combined with our desire to expand in this market drove us forward to creating the major features discussed in the following section.

## 4 - Major Features
To meet our users expectations and provide for the best experience possible TODO On The Go will be designed with the following major features in mind.

### 4.1 - Requirements 
1. A TODO list consists of tasks that the users need to accomplish. The application must be capable of adding items to, deleting items from, and editing items in a list.
2. The TODO List Manager consists of two applications that share most of their functionality:
	1. The Mobile TODO List Manager (MTM), which runs on the Android platform.
	2. The Web TODO List Manager (WTM), which runs on any standard web browser.
3. In both applications, users must be able to add tasks to a list by specifying the tasks' name, priority, and due date.
4. In both applications, there should be a default value for priority and due date, so that users won't have to necessarily specify them.
5. In both applications, users must be able to check off items in a list (without deleting them).
6. In both applications, users must be able to hide or show checked-off items.
7. MTM must contain a local database (LDB)
8. WTM relies on a centralized database (CDB)
9. Information in the LDBs on the local phones is synchronized with the information in the CDB when the application starts and when users perform an explicit synchronization. The synchronization happens in both directions:
	1. TODO items updated on the phone should overwrite the corresponding items on the CDB.
	2. Analogously, items updated on the web (and thus the CDB) should overwrite the corresponding items on the phone.
	3. New items on the phone should be copied to the CDB.
	4. New items on the CDB should be copied on the phone.
	5. Conflicts must be suitably handled.
10. On MTM, TODO lists must be saved (locally) automatically and immediately after they are modified.
11. On MTM, lists are also saved on the CDB when the application starts and when users perform an explicit synchronization, as described above.
12. On WTM, lists are saved (in the CDB) when users perform an explicit save.
13. Both applications must support multiple users, each one with their own list.
14. The User Interface (UI) must be intuitive and responsive.

### 4.2 - Future Plans
Some features that would be nice to have in the system in future versions include:

1. Sharing tasks with specified users
2. Alerts when a task is nearing its due date
3. Ability to e-mail task reminders to users
4. Migrating data from on device to another
5. Synchronizing information to a set of devices specified by the user 

## 5 - Scope & Limitations
The TODO On The Go system will exist solely as an Android app and web application. It will exist on multiple devices all with their own local data. This data will synchronize with a main data store that lives alongside the web application. The web application itself will live on a web server that regularly backs up the application and data. The system as a whole will be limited in terms of user access to the application and data. Users will only be allowed to access their data using credentials they have selected during initial set up. Access to the web application will require these same credentials. The up time of the android app will depend on the device it is installed in. The web application will be up 99% of the time given the routine nature of web server maintenance.

## 6 - Other Needs
To ensure the success of this system other needs will include readily available user documentation. This may take the form of documents distributed along side the app or online in a wiki format. Friendly and available support is also necessary so that the user can quickly find some help with an issue or alert the team to any out of the ordinary situations within the system. Last, security will be a big need both in terms of the user information and that of the system itself. Data should be protected using the highest standards available. This will include encrypting user data and locking down access to this data using file permissions. In order to protect the system from unauthorized access user credentials will need to be encrypted and stored securely. Any access to the data will need to be verified and cleaned before it is allowed to be used in the system as well to prevent bad data from entering the system. With the right precautions this system will be stable and secure providing the best possible experience.
