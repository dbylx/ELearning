# ELearning

E-Learning App design
 
 
![title](https://github.com/dbylx/ELearning/blob/master/screenshots/20.JPG)


**1.User Login/Register Model**

1.1.UI design

![p](https://github.com/dbylx/ELearning/blob/master/screenshots/1.png)
![p](https://github.com/dbylx/ELearning/blob/master/screenshots/2.png)
![p](https://github.com/dbylx/ELearning/blob/master/screenshots/3.png)

1.2. Use case

![p](https://github.com/dbylx/ELearning/blob/master/screenshots/21.png)

1.3. function Design

1.3.1. Login

User can login our E-Learning platform by username and password. Also, users can log in through a third party, we support facebook to login our platform.
1.3.2.Register
User can register a account number, If the username which user registering exists, We will remand user to change another username to register.
1.3.3.Save the login status.

When user login in our platform, our platform will save the user’ login status. So when user open the app next time, APP will return the last status.
We use shared preferences api to save the user information and status.
1.3.4Personal center

User can view their message in this module, for example, they can look through their username ,Signature and Avatar.

**2.Course display module**

2.1. UI Design

2.1.1.course list window

![p](https://github.com/dbylx/ELearning/blob/master/screenshots/4.png)
![p](https://github.com/dbylx/ELearning/blob/master/screenshots/5.png)

2.1.2.course detail message window

![p](https://github.com/dbylx/ELearning/blob/master/screenshots/6.png)
![p](https://github.com/dbylx/ELearning/blob/master/screenshots/7.png)
![p](https://github.com/dbylx/ELearning/blob/master/screenshots/8.png)

2.1.3.course shared window

![p](https://github.com/dbylx/ELearning/blob/master/screenshots/9.png)

2.2.Use Case

 ![p](https://github.com/dbylx/ELearning/blob/master/screenshots/22.png)
 
2.3.function design

2.3.1.browse course list

User can browse the all Brief course message by list.
We user recyclerView to achieve this function, In the recyclerView, We achieve some Animation. For example users can move entries up and down to swap their places. Also, If use don’t like some course, they can delete them by swiping right or left.
We design three type to show course message, In the UI Design model, we can see the three pattern. And as for each item, in order to make them become beautiful, we user the CardView Layout.
As for loading the message, we use multithreads to load the data form server. So that the window is frequent.

2.3.2. browse course detail Message.

In the detail Message Module, We can view the course detail message, teacher detail message and the material message.
We use the TabView PageView, fragment to achieve the function.

2.3.3. share the course.

User could shared the course through many third platform.

**3. Caching the user browsed data**

When user first browse the data such as course message, and teacher message, APP will catch those data into local database. So when user browse this page again, the APP firstly detect if local database has this data. If local database have no data or data in local database is not the latest, App will send request to server to get the latest data.

**4.BoardCast.**

![p](https://github.com/dbylx/ELearning/blob/master/screenshots/10.png)

4.2.function

We use access the home page, APP will connect to server to request if the APP have a new version. If having new version. App will notify users by notification like the above photo.

**5.Home Module**


5.1.UI Design


![p](https://github.com/dbylx/ELearning/blob/master/screenshots/11.png)
![p](https://github.com/dbylx/ELearning/blob/master/screenshots/12.png)
![p](https://github.com/dbylx/ELearning/blob/master/screenshots/13.png)
![p](https://github.com/dbylx/ELearning/blob/master/screenshots/14.png)


5.2 Use case
 
![p](https://github.com/dbylx/ELearning/blob/master/screenshots/23.png)
 
5.3function

5.3.1Search function

In this function, Users can input some key words to search the course. When clicking the search button, App will Jump up to the course list window to show users the course he want to search.

5.3.2 Advertising
There is a Ad column in the home window. The ad bar automatically rotates different ads for users.
We use ViewPager to achieve this function.

5.3.2 Popular applications

In this Module, There are some different applications, such as setting and course list applications. When user click the setting button, App will jump to the Setting window. When user click the course button, App will jump to course list window.

5.3.3Excellent Course

In this Module, we collect six best courses for users, users can click it to see the course video. If user don’t like some course, he or she could choose swipe left or right to delete them. Also, user can change their position by sliding up and down the items.

**6.Introduction slide page**

6.1UI design

![p](https://github.com/dbylx/ELearning/blob/master/screenshots/15.png)
![p](https://github.com/dbylx/ELearning/blob/master/screenshots/16.png)
![p](https://github.com/dbylx/ELearning/blob/master/screenshots/17.png)

6.2function
It is a introduce page when users first open the app, witch is simple introduce our app.

**7. Unit test**

7.1unit test cases: 

7.1.1Test Goal

go through the user first launch app process until login

7.1.2Test environment

Android Studio

7.1.2Test Data

username;123 password：123

7.1.3Test step

the user first launch the app, the screen show the introduction slide, the user click the next button to get the next introduction slide, after 3 slides, app launch the login page. The user first need to register the account. Click the sign up to go to the register page. Type the username and the password, click the sign up button, register successfully. After sign up, click the sign in to go to the sign in page. Type the username and password, click the sign in button to go to the main page.
7.1.4Test result

success login the app

7.2 Implement

use expresso method withView(withId(R.id.example)) to get the UI item. Use the perform simulate the operation like click, type and so on.

![p](https://github.com/dbylx/ELearning/blob/master/screenshots/18.png)
