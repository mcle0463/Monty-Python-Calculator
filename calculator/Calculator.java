/*
File name: [Calculator.java ]
Author: [ Greg McLeod, 040848835]
Course: CST8221 – JAP, Lab Section: [301]
Assignment: [1]
Date: [11/06/2017]
Professor: [Svillen Ranev]
Purpose: [main method]
 */

package calculator;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;

    /**
    * @author Greg McLeod
    * @version 1.1
    * @since 1.8.0_141
    */
public class Calculator {

    /**
     * Main method displays splash screen and creates new Calculator
     * View Controller Object to be displayed
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        CalculatorSplashScreen splash = new CalculatorSplashScreen(5000);
        splash.showSplashWindow();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run()
            {
               JFrame frame = new JFrame();
               // set up the Close button (X) of the frame
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setTitle("Calculator");
               frame.setMinimumSize(new Dimension(300,460));
               frame.setResizable(true);
               frame.setLocationRelativeTo(null);
               frame.setContentPane(new CalculatorViewController());
               frame.setLocation(screen.width/2, screen.height/2);
               frame.pack();
               // make the GUI visible
               frame.setVisible(true);	
            }
         });
                
    }
    
}
