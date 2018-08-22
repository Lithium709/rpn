package com.olysyi.rpn;

import java.util.ArrayDeque;
import java.util.Deque;

public class Calculator {

    private Deque<Double> valuesStack = new ArrayDeque<>();
    private int currentTokenIndex = 0;

    private Double tryParseDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }

    /**
     * Processes a RPN string token
     *
     * @param token           RPN token
     * @throws CalculatorException calculator exception
     */
    private void processToken(String token) throws CalculatorException {
        Double value = tryParseDouble(token);
        if (value == null) {
            processOperator(token);
        } else {
            // it's a digit
            valuesStack.push(Double.parseDouble(token));
        }
    }

    /**
     * Executes an operation on the stack
     *
     * @param operatorString  RPN valid operator
     * @throws CalculatorException calculator exception
     */
    private void processOperator(String operatorString) throws CalculatorException {

        // check if there is an empty stack
        if (valuesStack.isEmpty()) {
            throw new CalculatorException("empty stack");
        }

        // searching for the operator
        Operator operator = Operator.getEnum(operatorString);
        if (operator == null) {
            throw new CalculatorException("invalid operator");
        }

        // Checking that there are enough operand for the operation
        if (operator.getOperandsNumber() > valuesStack.size()) {
            throwInvalidOperand(operatorString);
        }

        // getting operands
        Double firstOperand = valuesStack.pop();
        Double secondOperand = (operator.getOperandsNumber() > 1) ? valuesStack.pop() : null;
        // calculate
        Double result = operator.calculate(firstOperand, secondOperand);

        if (result != null) {
            valuesStack.push(result);
        }

    }

    private void throwInvalidOperand(String operator) throws CalculatorException {
        throw new CalculatorException(
                String.format("operator %s (position: %d): insufficient parameters", operator, currentTokenIndex));
    }

    /**
     * Returns the values valuesStack
     */
    public Deque<Double> getValuesStack() {
        return valuesStack;
    }

    /**
     * Evals a RPN expression and pushes the result into the valuesStack
     *
     * @param input           valid RPN expression
     * @throws CalculatorException   calculator exception
     */
    public void eval(String input) throws CalculatorException {
        if (input == null) {
            throw new CalculatorException("Input cannot be null.");
        }
        currentTokenIndex = 0;
        String[] result = input.split("\\s");
        for (String aResult : result) {
            currentTokenIndex++;
            processToken(aResult);
        }
    }
}
