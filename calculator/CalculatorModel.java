/*
File name: [CalculatorModel.java ]
Author: [ Greg McLeod, 040848835]
Course: CST8221 – JAP, Lab Section: [301]
Assignment: [1 - part 2]
Date: [11/29/2017]
Professor: [Svillen Ranev]
Purpose: [Perform calculator functions]
 */
package calculator;

import java.awt.event.*;

/**
 * Class handles calculations requested by the user and controls the internal
 * calculator values.
 *
 * @author Greg McLeod
 * @version 1.1
 * @see ActionListener
 * @since 1.8.0_141
 */
public class CalculatorModel {

    private String operand1 = ""; // first operand entered for calculation
    private String operand2 = ""; // second operand entered for calculation
    private String operator = null; // operator selected to be used in calculation
    private String precision = ".00"; // precision to be used in calculation with default .00
    private String opMode = "F"; // Operational mode that can be changed by the user with default Float
    private String result = "0"; // result variable to be displayed by the calculator
    private boolean errorState = false; // variable to confirm if error state is enabled/disabled 

    /**
     * Calculate() performs the calculation on the input operands with the
     * selected operator. This method will also handle error events which will
     * also be output as a String.
     *
     * @return String to return will be the final calculated result or error
     * result
     */
    public String calculate() {
        //check that a full expression was entered by the user. If only one operand was set
        //by the user then that is the result
        if (!operand1.isEmpty() && operand2.isEmpty()) {
            result = operand1;
            return getCalculation();
        }
        //if statement verifies if the calculator is is float or int mode
        //before calculations are performed
        if (opMode.equals("I")) {
            //parse string values to int to be used for calculation
            int tempOp1 = Integer.parseInt(operand1);
            int tempOp2 = Integer.parseInt(operand2);
            //exception handling for arithmetic errors
            try {
                //case to check the operator and perform the corresponding calculation
                switch (operator) {
                    case "*":
                        result = Integer.toString(tempOp1 * tempOp2);
                        break;
                    case "+":
                        result = Integer.toString(tempOp1 + tempOp2);
                        break;
                    case "-":
                        result = Integer.toString(tempOp1 - tempOp2);
                        break;
                    case "/":
                        result = Integer.toString(tempOp1 / tempOp2);
                        break;
                }
                //check for non int result which will enable ErrorState
                if (result.equals("NaN") || result.equals("Infinity")) {
                    setErrorState(true);
                    return getCalculation();
                }
                //specific catch for division by 0
            } catch (ArithmeticException e) {
                result = "Cannot divide by zero";
                setErrorState(true);
                return getCalculation();
            }
        } else {

            double tempOp1 = Double.parseDouble(operand1); //double to hold string value of operand1
            double tempOp2 = Double.parseDouble(operand2); //double to hold string value of operand2
            double floatResult = 0.00; //Double to hold the results of the calculation
            //case to check the operator and perform the corresponding calculation
            try {
                switch (operator) {
                    case "*":
                        floatResult = tempOp1 * tempOp2;
                        break;
                    case "+":
                        floatResult = tempOp1 + tempOp2;
                        break;
                    case "-":
                        floatResult = tempOp1 - tempOp2;
                        break;
                    case "/":
                        floatResult = tempOp1 / tempOp2;
                        break;
                }
                //check for non int result which will enable ErrorState
                if (Double.isNaN(floatResult) || Double.isInfinite(floatResult)) {
                    setErrorState(true);
                    result = Double.toString(floatResult);
                    return getCalculation();
                }
                //specific catch for division by 0
            } catch (ArithmeticException e) {
                result = "Cannot divide by zero";
                setErrorState(true);
                return getCalculation();
            }
            // This Switch statement takes floatResult and formats to the users
            // currently selected precision and assigns it as a String to result variable
            switch (precision) {
                case ".0":
                    result = String.format("%.1f", floatResult);
                    break;
                case ".00":
                    result = String.format("%.2f", floatResult);
                    break;
                case "Sci":
                    result = String.format("%.6E", floatResult);
                    break;
            }
        }
        return getCalculation();
    }

    /**
     * retrieves the final calculation
     *
     * @return Final calculation to be returned to controller
     */
    public String getCalculation() {
        if (result == null) {
            result = "0";
        }
        return result;
    }

    /**
     * Sets the opMode member (Float "F", or Int "I")
     *
     * @param c_mode is required as there is more than one operational mode that
     * can be set
     */
    public void setOperationalMode(String c_mode) {
        opMode = c_mode;
    }

    /**
     * Retrieves the operational mode
     *
     * @return The current operational mode
     */
    public String getOperationalMode() {
        return opMode;
    }

    /**
     * Sets the precision (Int,.0,.00,Sci)
     *
     * @param c_precision precision to be set
     */
    public void setPrecision(String c_precision) {
        precision = c_precision;
    }

    /**
     * retrieve the precision
     *
     * @return the current precision String value
     */
    public String getPrecision() {
        return precision;
    }

    /**
     * Sets the operator selected by the user
     *
     * @param c_operator string value of the selected operator
     */
    public void setOperator(String c_operator) {
        operator = c_operator;
    }

    /**
     * set the first operand to be used in calculation
     *
     * @param c_operand1 String value of the operand
     */
    public void setFirstOperand(String c_operand1) {
        operand1 = c_operand1;
    }

    /**
     * set the second operand to be used in calculation
     *
     * @param c_operand2 String value of the operand
     */
    public void setSecondOperand(String c_operand2) {
        operand2 = c_operand2;
    }

    /**
     * sets the error state value
     *
     * @param state boolean value of error state to be set
     */
    public void setErrorState(boolean state) {
        errorState = state;
    }

    /**
     * retrieve the current error state value
     *
     * @return boolean value of errorState variable
     */
    public boolean getErrorState() {
        return errorState;
    }

    /**
     * Method to reset internal calculator values. this method is often used in
     * conjunction with a "C" input to clear the calculator. Note: precision and
     * opMode are not included as these values default back to the last user
     * selected modes
     */
    public void resetCalculator() {
        operand1 = null;
        operand2 = null;
        operator = null;
        result = "0";
        errorState = false;
    }
}
