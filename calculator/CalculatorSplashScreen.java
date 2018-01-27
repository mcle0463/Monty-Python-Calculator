/*
File name: [CalculatorSplashScreen.java ]
Author: [ provided by Svillen Ranev, edited by Greg McLeod, 040848835]
Course: CST8221 – JAP, Lab Section: [301]
Assignment: [1]
Date: [11/29/2017]
Professor: [Svillen Ranev]
Purpose: [Display Splash Screen before main program]
 */
package calculator;
import java.awt.*;
import javax.swing.*;

    /**
    * @author Greg McLeod, skeleton class/methods
    * @version 1.1
    * @see 
    * @since 1.8.0_141
    */
public class CalculatorSplashScreen extends JWindow {
    private final int duration;
    //default constructor for splash screen
    CalculatorSplashScreen(int x){
     this.duration = x;   
    }
    /**
     * Builds a splash window and sets visible
     */
    public void showSplashWindow() {
   //create content pane
     JPanel content = new JPanel(new BorderLayout());

     content.setBackground(Color.GRAY);
    
    // Set the window's size
    int width =  300;
    int height = 460;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screen.width-width)/2;
    int y = (screen.height-height)/2;
    //set the location of the splash screen
    setBounds(x,y,width,height);

    JLabel label = new JLabel(new ImageIcon(getClass().getResource("monty2.jpg"))); 
  
    JLabel demo = new JLabel("Greg McLeod, 040848835", JLabel.CENTER);
    demo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
    content.add(label, BorderLayout.CENTER);
    content.add(demo, BorderLayout.SOUTH);
 
    Color customColor = new Color(20,20,20);
    content.setBorder(BorderFactory.createLineBorder(customColor, 10));
    
     //add content to the window
     setContentPane(content);

    //show screen
    setVisible(true);

    try {
    	
    	 Thread.sleep(duration); }
    catch (InterruptedException e) {/* can use to log an error*/}
    //destroy window 
    dispose(); 
  }
}
