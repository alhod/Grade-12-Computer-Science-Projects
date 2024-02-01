/*
Names (Group 2): Anna, Zaid, Hayden, Andrew (Project Manager)

Roles/Tasks:
- Anna: Database feature
- Zaid: Authentication feature
- Hayden: Map feature
- Andrew: Matching feature, web scraping and Program class (in Database package), menu screen

Deliverables: Our project is an app called MyPortal, which assists high school students 
in finding the university and program that best suits them. MyPortal has four main features:
1. Authentication (Zaid):
    Allows users to make a user profile, and login/register using a username and password. 
    Users can keep track of important information related to graduation and university 
    applications, such as the courses they have/will take(n), their marks in these courses, 
    the school they go to, their community involvement hours, and more.
2. Database (Anna):
    A resourceful database of university programs in Ontario. Provides filtering options 
    to assist the user to search through the database, including filter by university, 
    location, experiential learning, category, and sorting options.
3. Matching (Andrew):
    The matching feature prompts the user to answer a survey. The user's answers are then 
    used to sort the database of university programs to cater to the user's survey answers.
4. Map (Hayden):
    The map feature provides an interactive map of all the universities in our database. 
    Users can choose to find the location of a specific university (or all universities), 
    set a specific location as your home, see different map views, and do other useful 
    operations.

Contributions:
- Anna: 25%
- Zaid: 25%
- Hayden: 25%
- Andrew: 25%
*/

package Application;

import Menu.Controller.*;

public class Application {

    public static void main(String[] args) {
        new Controller();
    }
}
