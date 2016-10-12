package com.example.calculator;

import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Stack;

/**
 * Created by Александр on 11.05.2016.
 */
public class PostfixOperations {

    private static final int MaxFractionDig = 10;

    public static String countExpression(TextView inputStr) {
        return PostfixOperations.performOperations(PostfixOperations.convertToPostfix(ValidityCheckers.checkBrackets(inputStr.getText().toString()), inputStr)).toString();
    }

    private static StringBuilder convertToPostfix(CharSequence input, TextView inputStr) {
        StringBuilder tmpStr = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int i = 0;
        Stack<Character> stack = new Stack<>();
        stack.push('M');
        boolean isNegative = false;
        while (i < input.length()) { // Parse input symbol by symbol
            if (i == 0 && input.charAt(i) == '-') {
                isNegative = true;
                i++;
            }
            if (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.') { // цифру пихаем в вых. строку
                while (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.') {
                    output.append(input.charAt(i));
                    i++;
                    if (i >= input.length()) {
                        break;
                    }
                }
                output.append('|');
                if (isNegative) {
                    output.append('!');
                    isNegative = false;
                }
                continue;
            }
            if (input.charAt(i) == '(') // Откр скобка
            {
                stack.push(input.charAt(i)); // откр скобку пихаем в стэк
                if (input.charAt(i + 1) == '-') { // если следующий символ '-'
                    isNegative = true;
                    i = i + 2; // пропускаем оба символа
                    continue;
                }
            }
            if (input.charAt(i) == ')')  // closing bracket
            {
                while (stack.peek() != '(')  // пока не откр скобка наверху все выпихиваем в вых строку
                {
                    output.append(stack.pop());
                }
                stack.pop();
            }
            if (checkOperatorCoins(input.charAt(i))) {
                if (i == (input.length() - 1)) {
                    tmpStr.append(inputStr.getText().toString().trim());
                    tmpStr.setLength(tmpStr.length() - 1);
                    inputStr.setText(tmpStr);
                    break;
                }
                if (!stack.empty()) {
                    while (getOpPriority(input.charAt(i)) <= getOpPriority(stack.peek())) {
                        output.append(stack.pop());
                    }
                }
                stack.push(input.charAt(i));
                if ((input.charAt(i) == '*' || input.charAt(i) == '/')) {
                    if (input.charAt(i + 1) == '-') {
                        isNegative = true;
                        i = i + 2;
                        continue;
                    }
                }
            }
            i++;
        }
        while (stack.peek() != 'M') {
            output.append(stack.pop());
        }
        return output;
    }

    private static StringBuilder performOperations(CharSequence input) {
        BigDecimal x, y, a, valB;
        Double val;
        Stack<BigDecimal> stack = new Stack<>();
        StringBuilder output = new StringBuilder();
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (checkOperatorCoins(input.charAt(i))) {
                if (input.charAt(i) == '+') {
                    y = stack.pop();
                    x = stack.pop();
                    a = x.add(y);
                    stack.push(a);
                }
                if (input.charAt(i) == '-') {
                    y = stack.pop();
                    x = stack.pop();
                    a = x.subtract(y);
                    stack.push(a);
                }
                if (input.charAt(i) == '*') {
                    y = stack.pop();
                    x = stack.pop();
                    a = x.multiply(y);
                    stack.push(a);
                }
                if (input.charAt(i) == '/') {
                    y = stack.pop();
                    x = stack.pop();
                    try {
                        a = x.divide(y, 10, BigDecimal.ROUND_HALF_UP);
                    } catch (ArithmeticException e) {
                        output.append("Infinity");
                        return output;
                    }
                    stack.push(a);
                }
                //if (input.charAt(i) == '^') {
                //    y = stack.pop();
                //    x = stack.pop();
                //    a = x ^ y;
                //    stack.push(a);
                //}
            } else {
                while (input.charAt(i) != '|') {
                    value.append(input.charAt(i));
                    i++;
                }
                val = Double.valueOf(value.toString()); /////////// CONVERT TO BIG DECIMAL
                valB = BigDecimal.valueOf(val);
                if (input.charAt(i + 1) == '!') {
                    valB = valB.negate();
                    i++;
                }
                stack.push(valB);
            }
            value.delete(0, value.length());
        }
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(MaxFractionDig);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);
        output.append(df.format(stack.pop().stripTrailingZeros()));
        return output;
    }

    public static boolean checkOperatorCoins(char symbol) {
        char[] operators = {'+', '-', '*', '/', '^'};
        for (char operator : operators) {
            if (operator == symbol) {
                return true;
            }
        }
        return false;
    }

    private static int getOpPriority(char symbol) {
        switch (symbol) {
            case '^':
                return 4;
            case '*':
                return 3;
            case '/':
                return 3;
            case '+':
                return 2;
            case '-':
                return 2;
            case '(':
                return 1;
            case ')':
                return 1;
            default:
                return 0;
        }
    }
}
