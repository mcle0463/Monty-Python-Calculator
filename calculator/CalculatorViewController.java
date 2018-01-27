/*
File name: [CalculatorViewController.java ]
Author: [ Greg McLeod, 040848835]
Course: CST8221 – JAP, Lab Section: [301]
Assignment: [1 - part 1,part 2]
Date: [11/29/2017]
Professor: [Svillen Ranev]
Purpose: [Build calculator GUI]
Class list: [Controller]
 */
package calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Greg McLeod
 * @version 1.1
 * @see JPanel
 * @since 1.8.0_141
 */
public class CalculatorViewController extends JPanel {

    private JTextField display1; // the calculator display1 field reference
    private JTextField display2; // the calculator display2 field reference
    private JLabel error; // the mode/error display label reference
    private JButton dotButton; // the decimal point (dot) button reference    

    private final Color defaultFG = Color.BLACK; //default foreground color
    private final Color defaultBG = Color.BLUE; //default background color

    /**
     * Default constructor for the calculator GUI.
     * Initializes all the GUI components to be added to the JFrame.
     * Main JPanels used are top, middle, and bottom
     */
    public CalculatorViewController() {

        String ac = null; //initialize to null which will be checked when creating a JButton
        CalculatorModel model = new CalculatorModel(); //model class instance to call calculations
        Controller defaultHandler = new Controller(model); // controller instance to handle gui events
        /*Create displays as non-editable text fields for calculator output*/
        display1 = new JTextField(); //top display field
        display1.setBorder(BorderFactory.createLineBorder(Color.white));
        display2 = new JTextField(); //bottom display field handles input text
        display2.setBorder(BorderFactory.createLineBorder(Color.white));
        error = new JLabel("F", SwingConstants.CENTER); //error and mode button with default float mode
        JButton backSpace = createButton("\u21B2", ac, Color.BLACK, null, defaultHandler); //backspace button for removing chars
        backSpace.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));
        backSpace.setPreferredSize(new Dimension(35, 55));
        backSpace.setOpaque(false);
        backSpace.setToolTipText("Alt-B (Backspace)");
        backSpace.setMnemonic('b');

        /*set display appearance*/
        display1.setBackground(Color.WHITE);
        display1.setEditable(false);
        display1.setColumns(16);
        display1.setHorizontalAlignment(JTextField.RIGHT);
        display1.setPreferredSize(new Dimension(display2.getWidth(), 30));
        display2.setBackground(Color.WHITE);
        display2.setEditable(false);
        display2.setColumns(16);
        display2.setHorizontalAlignment(JTextField.RIGHT);
        display2.setPreferredSize(new Dimension(display2.getWidth(), 30));
        display2.setText("0.0");

        /*put displays in a JPanel so they stay aligned under one column*/
        JPanel display = new JPanel();
        display.setLayout(new GridLayout(2, 1, 0, 0));
        display.setBackground(Color.WHITE);
        display.add(display1);
        display.add(display2);

        /*set top container (JFrame) layout manager as BorderLayout*/
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        /*Create error label*/
        error.setFont(new Font(error.getFont().getFontName(), error.getFont().getStyle(), 20));
        error.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));
        error.setPreferredSize(new Dimension(35, 55));
        error.setOpaque(false);

        /*add components to a new JPanel top
        top will hold the display, error, and backspace components
         */
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        top.setBackground(Color.YELLOW);
        top.add(display, BorderLayout.CENTER);
        top.add(error, BorderLayout.WEST);
        top.add(backSpace, BorderLayout.EAST);

        /*Create mode JPanel that will contain mode options*/
        JPanel mode = new JPanel();
        mode.setLayout(new BoxLayout(mode, BoxLayout.X_AXIS));

        /*Create mode options as Radio buttons*/
        JRadioButton button1 = new JRadioButton(".0");
        button1.setOpaque(false);
        button1.addActionListener(defaultHandler);
        JRadioButton button2 = new JRadioButton(".00");
        button2.setOpaque(false);
        button2.addActionListener(defaultHandler);
        JRadioButton button3 = new JRadioButton("Sci");
        button3.setOpaque(false);
        button3.addActionListener(defaultHandler);
        button2.setSelected(true);

        /*Assign buttons to a group*/
        ButtonGroup group = new ButtonGroup();
        group.add(button1);
        group.add(button2);
        group.add(button3);

        mode.add(button1);
        mode.add(button2);
        mode.add(button3);
        mode.setBackground(Color.YELLOW);

        JCheckBox checkBox = new JCheckBox("Int"); //checkbox changes mode to int when selected
        checkBox.addActionListener(defaultHandler);
        checkBox.setBackground(Color.GREEN);

        /*Create middle Panel to hold raido buttons and Int options 
        and add to the container*/
        JPanel middle = new JPanel();
        middle.setBackground(Color.BLACK);
        middle.setLayout(new FlowLayout());

        middle.add(checkBox);
        middle.add(Box.createHorizontalStrut(20));
        middle.add(Box.createVerticalStrut(30));
        middle.add(mode);
        middle.setPreferredSize(new Dimension(100, 20));

        /*Create a keypad with gridlayout for calculator buttons*/
        JPanel keyPad = new JPanel();
        keyPad.setLayout((new GridLayout(4, 4, 2, 2)));
        String buttonLabels = "0789/456*123-0.+";

        /*loop through # of required buttons and create a button with specified int or character
        * if else statement to check for unique buttons that require different settings
         */
        for (int i = 1; i <= 14; i++) {

            if ((i % 4) == 0) {
                keyPad.add(createButton(Character.toString(buttonLabels.charAt(i)), ac, defaultFG, Color.CYAN, defaultHandler));
            } else if (i == 14) {
                dotButton = createButton(Character.toString(buttonLabels.charAt(i)), ac, defaultFG, defaultBG, defaultHandler);
                keyPad.add(dotButton);
                keyPad.add(createButton("\u00B1", ac, defaultFG, Color.PINK, defaultHandler));
                keyPad.add(createButton("+", ac, defaultFG, Color.CYAN, defaultHandler));

            } else {
                keyPad.add(createButton(Character.toString(buttonLabels.charAt(i)), ac, defaultFG, defaultBG, defaultHandler));
            }
        }

        /*create JPanel for 'C' and '=' buttons which are bigger in height*/
        JPanel longButtons = new JPanel();
        longButtons.setLayout(new GridLayout(2, 1, 2, 2));
        longButtons.add(createButton("C", ac, defaultFG, Color.RED, defaultHandler));
        longButtons.add(createButton("=", ac, defaultFG, Color.MAGENTA, defaultHandler));
        longButtons.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));

        /*New JPanel which will hold keypad JPanel and Longbuttons JPanel*/
        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        bottom.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        bottom.add(keyPad, BorderLayout.CENTER);
        bottom.add(longButtons, BorderLayout.EAST);
        bottom.setPreferredSize(new Dimension(280, 310));

        /*Add top, middle, and bottom JPanel's to overlaying JFrame*/
        this.add(top, BorderLayout.NORTH);
        this.add(middle, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH);

        //
    }

    /**
     * Builds a new JButton with specified parameters
     *
     * @param text will be displayed on button created
     * @param ac action command for the new button
     * @param fg color of the foreground
     * @param bg color of the background
     * @param handler handler for events generated by the button
     * @return JButton reference to button created
     */
    private JButton createButton(String text, String ac, Color fg, Color bg,
            ActionListener handler) {

        JButton button = new JButton(); //create a new button
        //set new button properties
        button.setText(text);
        button.setFont(new Font(button.getFont().getFontName(), button.getFont().getStyle(), 20));
        button.setForeground(fg);
        button.setBackground(bg);
        button.addActionListener(handler);

        if (ac != null) {
            button.setActionCommand(ac);
        }
        return button;
    }

    /**
     * Acts as a switch disabling/enabling decimal button. Called
     * when the users changes operational modes
     */
    private void decimalControl() {
        //if the decimal button is enabled(Float mode), disable it
        if (dotButton.isEnabled()) {
            dotButton.setEnabled(false);
            dotButton.setBackground(new Color(178, 156, 250));
            error.setText("I");
            error.setBackground(Color.GREEN);
            error.setOpaque(true);
            //if the decimal button is disabled(Int mode), enable it
        } else {
            dotButton.setEnabled(true);
            dotButton.setBackground(defaultBG);
            error.setText("F");
            error.setBackground(Color.YELLOW);
        }
    }

    /**
     * Class Controller handles action commands from the GUI and calls the
     * corresponding CalculatorModel methods when required. The class also
     * contains functionality to temporarily alter GUI components such as Color
     * or text.
     *
     * @author Greg McLeod
     * @version 1.1
     * @see ActionListener
     * @since 1.8.0_141
     */
    private class Controller implements ActionListener {

        CalculatorModel model;  // CalculatorModel reference to be operated on
        String input = new String(); // will be used for validating input and passing to CalulatorModel
        
        //constructor creates Controller obj with model instance
        public Controller(CalculatorModel model) {
            this.model = model;
        }

        /**
         * Method regulates user input, calls CalculatorModel methods on
         * generated action commands, formats output in display2/display1, and
         * acts as a filter for all generated action commands. This method also
         * enables/disables the GUI during an Error Event
         *
         * @param ae action command generated from the GUI
         */
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (model.getErrorState() && !ae.getActionCommand().equals("C")) {
                return;
            }
            if (ae.getActionCommand() != null) {

                //Case for action command that will call multiple
                //methods in calculatorModel and handle setting display properly
                switch (ae.getActionCommand()) {
                    case "\u00B1":
                        //check that a number has been entered and
                        //that the number is not 0
                        if (!input.isEmpty() && !input.equals("0")) {
                            //check that there is not already a "-" sign
                            //to prevent multiple leading "-"
                            if (input.charAt(0) != '-') {
                                input = "-".concat(input);
                                display2.setText(input);
                            } else {
                                //delete "-", effectively changing the value
                                //from negative to positive when passed to calculate()
                                StringBuilder sb = new StringBuilder(input);
                                sb.deleteCharAt(0);
                                input = sb.toString();
                                display2.setText(input);
                            }
                        }
                        break;
                    case "+":
                        //If the current input is empty assume 0 for operand
                        if (input.isEmpty()) {
                            model.setFirstOperand("0");
                            display1.setText("0 + ");
                        } else {
                            model.setFirstOperand(display2.getText());
                            display1.setText(display2.getText().concat(" + "));
                        }
                        model.setOperator("+");
                        input = "";
                        break;
                    case "-":
                        //If the current input is empty assume 0 for operand
                        if (input.isEmpty()) {
                            model.setFirstOperand("0");
                            display1.setText("0 - ");
                        } else {
                            model.setFirstOperand(display2.getText());
                            display1.setText(display2.getText().concat(" - "));
                        }
                        model.setOperator("-");
                        input = "";
                        break;
                    case "/":
                        if (input.isEmpty()) {
                            model.setFirstOperand("0");
                            display1.setText("0 / ");
                        } else {
                            model.setFirstOperand(display2.getText());
                            display1.setText(display2.getText().concat(" / "));
                        }
                        model.setOperator("/");
                        input = "";
                        break;
                    case "*":
                        //If the current input is empty assume 0 for operand
                        if (input.isEmpty()) {
                            model.setFirstOperand("0");
                            display1.setText("0 * ");
                        } else {
                            model.setFirstOperand(display2.getText());
                            display1.setText(display2.getText().concat(" * "));
                        }
                        model.setOperator("*");
                        input = "";
                        break;
                    case "=":
                        //ensures user entered two operands before selecting "="
                        //clears previous entry next input is entered
                        if (display1.getText().isEmpty()) {
                            model.resetCalculator();
                            input = "";
                            break;
                        }
                        //preform calculation and ready calculator for next entry
                        model.setSecondOperand(display2.getText());
                        display1.setText("");
                        display2.setText(model.calculate());
                        //immediately check for error after calculation and set error state if true
                        if (model.getErrorState()) {
                            error.setOpaque(true);
                            error.setBackground(Color.RED);
                            error.setText("E");
                        } else {
                            model.resetCalculator();
                        }
                        input = "";

                        break;
                    case ".":
                        //prevent user from entering multiple decimals
                        if (!input.contains(".")) {
                            input = input.concat(".");
                            display2.setText(input);
                        }

                        break;
                    case "C":
                        //check for error state if calc needs to be reset
                        if (model.getErrorState()) {
                            model.resetCalculator();
                            display2.setText(model.getPrecision());
                            error.setText(model.getOperationalMode());
                            //if error state is true reset calc to the selected
                            //mode and precision before error occured
                            if (model.getOperationalMode().equals("F")) {
                                error.setBackground(Color.YELLOW);
                            } else {
                                error.setBackground(Color.GREEN);
                            }
                        }
                        model.resetCalculator();
                        display1.setText("");
                        display2.setText("0");
                        input = "";
                        break;
                    case "\u21B2":
                        //delete character at end of current input and
                        //reset the display to the new string
                        if (!input.isEmpty()) {
                            StringBuilder sb = new StringBuilder(input);
                            sb.deleteCharAt(input.length() - 1);
                            input = sb.toString();
                            display2.setText(input);
                        }
                        break;
                    case "Int":
                        //check dot button reference to see if
                        //Int mode is already enabled                     
                        model.setOperationalMode("I");
                        decimalControl();
                        if (display1.getText().contains(".") || display2.getText().contains(".")) {
                            display1.setText("");
                            display2.setText("0");
                            input = "";
                        }
                        break;
                    case ".0":
                        //check dot button reference to see if
                        //Int mode is enabled and enables dotButton if true
                        if (!dotButton.isEnabled()) {
                            decimalControl();
                        }
                        model.setOperationalMode("F");
                        model.setPrecision(".0");
                        break;
                    case ".00":
                        //check dot button reference to see if
                        //Int mode is enabled and enables dotButton if true
                        if (!dotButton.isEnabled()) {
                            decimalControl();
                        }
                        model.setOperationalMode("F");
                        model.setPrecision(".00");
                        break;
                    case "Sci":
                        //check dot button reference to see if
                        //Int mode is enabled and enables dotButton if true
                        if (!dotButton.isEnabled()) {
                            decimalControl();
                        }
                        model.setOperationalMode("F");
                        model.setPrecision("Sci");
                        break;
                    default:
                        //Default case left for numpad events
                        //adds the selected button number to the display text
                        input = input.concat(ae.getActionCommand());
                        display2.setText(input);
                        break;
                }

            }
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

}
