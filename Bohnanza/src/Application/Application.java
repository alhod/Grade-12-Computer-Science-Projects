/* 
Name(s): Andrew Deng(28%), Saheer Eshan(24%), Jay Yuan(24%), Stuart Toy(24%) 
Date: November 26th, 2023
Course Code: ICS4U1-02, Mr.Fernandes
Title: SDP1-Bohnanza, Bohnanza Card Game
Decription: Interactive card game based off of the card game "Bohnanza" in Java
Features: Adaptive endgame screen, depending on who wins, the background changes coin height accordingly
Features (cont): when the game is running, music plays continously, AI was designed to be able to face and AI
Features (cont): player.
Major Skills: Hashmaps, sounds, interfaces, array types (regular and arrayLists), file reading,
Major Skills (cont): class hierarchy and polymorphism, Stacks data type, final variables (constants).
Major Skills (cont): AI uses indirect recursion
Areas of concern: when you discard a card, there is no warning which may lead to confusion. There is no
major indication of the start of the next phase


Andrew did all Model and 90% of controller
Stuart and Saheer did all of View and 10% of controller
Jay did all of AI
*/

package Application;

import Controller.*;

public class Application {
    
    public static void main(String[] args) {
        new Controller();

        // new LaptopStoreSurveyFrame();
    
        // new Test();
    }
}
