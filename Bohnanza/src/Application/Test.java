package Application;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.*;

public class Test {
    
    public static void main(String[] args){

        JFrame frame = new JFrame();
        frame.setSize(500, 500);

        JButton button = new JButton("fuck");
        button.setBounds(50, 50, 100, 100);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                button.setVisible(false);
                System.out.println("fuck you");
            }
        });
        frame.add(button);
        frame.setVisible(true);
        
    }

    public static Font getFont(int size){

        // https://stackoverflow.com/questions/12998604/adding-fonts-to-swing-application-and-include-in-package
        InputStream is = Test.class.getResourceAsStream("TestFont.ttf");
        Font font = new Font(Font.SERIF, Font.PLAIN, 10);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont((float)size);
        } catch (FontFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return font;
    }
}
