/*
 * Date: Nov 25th
 * Course: ICS4U1-02
 * Name: Saheer, Eshan
 * Significant help: none
 * Description: This is the gui class that most view classes will extend if not a JFrame. This idea
 * and implementation was Andrews.
 */
package View;

import Model.*;
import Controller.*;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class GUI extends JPanel{
	
	//attributes that every class will have
	private int width;
	private int height;
	private int X;
	private int Y;
	
	//constructor
	public GUI(int x, int y, int w, int h) {
		super();
		width = w;
		height = h;
		X = x;
		Y = y;
		this.setBounds(X,Y,width,height); //automatically sets bounds
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}
	
	

}
