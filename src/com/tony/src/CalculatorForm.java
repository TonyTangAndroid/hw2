package com.tony.src; /**
 * Author : Zhilu Tang
 * Date: 11-30-2015
 */

import org.mybeans.form.FormBean;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class CalculatorForm extends FormBean {

    private static final String OPERATOR_PLUS = "+";
    private static final String OPERATOR_MINUS = "-";
    private static final String OPERATOR_MULTIPLY = "*";
    private static final String OPERATOR_DIVIDE = "/";
    private static final String OPERATOR_EQUAL = "=";
    private String parameterX;
    private String parameterY;
    private String action;

    public String getParameterX() {
        return parameterX;
    }

    public String getParameterY() {
        return parameterY;
    }

    public void setParameterX(String s) {
        parameterX = s.trim();
    }

    public void setParameterY(String s) {
        parameterY = s.trim();
    }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (parameterX == null || parameterX.length() == 0) {
            errors.add("The input of X is empty");
        } else if (!isNumber(parameterX)) {
            errors.add("The input of X is invalid");
        }

        if (parameterY == null || parameterY.length() == 0) {
            errors.add("The input of Y is empty");
        } else if (!isNumber(parameterY)) {
            errors.add("The input of Y is invalid");
        }

        if (!OPERATOR_PLUS.equals(action) && !OPERATOR_MINUS.equals(action)
                && !OPERATOR_MULTIPLY.equals(action) && !OPERATOR_DIVIDE.equals(action)) {
            errors.add("The action is invalid");
        }

        if (OPERATOR_DIVIDE.equals(action) && isZero(parameterY)) {
            errors.add("Zero could not be divided.");
        }


        return errors;
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    private static boolean isZero(String parameter) {
        try {
            int result = Integer.parseInt(parameter);
            System.out.println(result);
            return result == 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isNumber(String parameter) {
        try {
            double result = Double.parseDouble(parameter);
            System.out.println(result);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String formatAnswer() {
        return format(Double.parseDouble(getParameterX()))
                + " " + getAction() + " "
                + format(Double.parseDouble(getParameterY()))
                + " " + OPERATOR_EQUAL + " " + calculateResult();
    }

    private String calculateResult() {
        double result = 0L;
        switch (action) {
            case OPERATOR_DIVIDE:
                result = Double.parseDouble(getParameterX()) / Double.parseDouble(getParameterY());
                break;
            case OPERATOR_PLUS:
                result = Double.parseDouble(getParameterX()) + Double.parseDouble(getParameterY());
                break;
            case OPERATOR_MINUS:
                result = Double.parseDouble(getParameterX()) - Double.parseDouble(getParameterY());
                break;
            case OPERATOR_MULTIPLY:
                result = Double.parseDouble(getParameterX()) * Double.parseDouble(getParameterY());
                break;
        }

        return format(result);
    }

    private String format(double tempAnswer) {
        NumberFormat formatter = new DecimalFormat("#,##0.00");
        return formatter.format(tempAnswer);
    }
}
