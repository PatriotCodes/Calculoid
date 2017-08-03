/*
Copyright (c) 2016 Alexander Triukhan
Calculoid: A simple android calculator application with operation priority and memory functions.

This file is part of Calculoid.

Calculoid is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.example.calculator;

import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Formatter;
import java.util.Locale;


public class PostfixOperations {

    private static final int MAX_FRACTION_DIG = 10;

    public static String countExpression(TextView inputStr) {
        return PostfixOperations.performOperations(PostfixOperations.convertToPostfix(ValidityCheckers.checkBrackets(inputStr.getText().toString()), inputStr));
    }

    private static StringBuilder convertToPostfix(CharSequence input, TextView inputStr) {
        StringBuilder tmpStr = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int i = 0;
        Deque<Character> stack = new ArrayDeque<>();
        stack.push('M');
        boolean isNegative = false;
        while (i < input.length()) { // Parse input symbol by symbol
            if (i == 0 && input.charAt(i) == '-') {
                isNegative = true;
                i++;
            }
            if (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.' || input.charAt(i) == '%') { // append character to output string
                while (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.' || input.charAt(i) == 'E' || input.charAt(i) == '%') {
                    output.append(input.charAt(i));
                    if (input.charAt(i) == 'E') {
                        i++;
                        if (input.charAt(i) == '-' || input.charAt(i) == '+') {
                            output.append(input.charAt(i));
                        }
                    }
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
            if (input.charAt(i) == '(')
            {
                stack.push(input.charAt(i));
                if (input.charAt(i + 1) == '-') { // if next symbol is '-' we skip both
                    isNegative = true;
                    i = i + 2;
                    continue;
                }
            }
            if (input.charAt(i) == ')')
            {
                while (stack.peek() != '(')  // while not an opening bracket at the top we append everything to output
                {
                    output.append(stack.pop());
                }
                stack.pop();
            }
            if (ValidityCheckers.checkOperatorCoins(input.charAt(i))) {
                if (i == (input.length() - 1)) {
                    tmpStr.append(inputStr.getText().toString().trim());
                    tmpStr.setLength(tmpStr.length() - 1);
                    inputStr.setText(tmpStr);
                    break;
                }
                if (!stack.isEmpty()) {
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

    private static String performOperations(CharSequence input) {
        BigDecimal x, y, a, valB;
        Deque<BigDecimal> stack = new ArrayDeque<>();
        StringBuilder output = new StringBuilder();
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (ValidityCheckers.checkOperatorCoins(input.charAt(i))) {
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
                        a = x.divide(y,MAX_FRACTION_DIG,BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
                    } catch (ArithmeticException e) {
                        output.append("Infinity");
                        return output.toString();
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
                if (stack.size() > 0) {
                    valB = checkPercent(value.toString(),stack.peek());
                } else {
                    valB = checkPercentOneOperand(value.toString());
                }
                if ((input.length() - 1) > i) {
                    if (input.charAt(i + 1) == '!') {
                        valB = valB.negate();
                        i++;
                    }
                }
                stack.push(valB);
            }
            value.delete(0, value.length());
        }
        ScientificNotationConverter converter = new ScientificNotationConverter();
        return converter.convertToExponentialForm(stack.pop(),MAX_FRACTION_DIG);
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

    private static BigDecimal checkPercent(String number,BigDecimal x) {
        if (number.charAt(number.length() - 1) == '%') {
            StringBuilder yStr = new StringBuilder(number);
            yStr.deleteCharAt(yStr.length() - 1);
            BigDecimal y = new BigDecimal(yStr.toString());
            BigDecimal oneHundred = new BigDecimal(100);
            y = (x.divide(oneHundred)).multiply(y);
            return y;
        } else {
            return new BigDecimal(number);
        }
    }

    private static BigDecimal checkPercentOneOperand(String number) {
        String[] splitter = number.split("%");
        if (splitter.length > 1) {
            BigDecimal x = new BigDecimal(splitter[0]);
            BigDecimal y = new BigDecimal(splitter[1]);
            BigDecimal oneHundred = new BigDecimal(100);
            BigDecimal result = (y.divide(oneHundred,MAX_FRACTION_DIG,BigDecimal.ROUND_HALF_UP)).multiply(x).stripTrailingZeros();
            return result;
        } else {
            return new BigDecimal(number);
        }
    }
}
