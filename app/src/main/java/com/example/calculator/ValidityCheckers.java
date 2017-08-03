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


public class ValidityCheckers {

    public static StringBuilder checkBrackets(String input) {
        StringBuilder output = new StringBuilder();
        output.append(input);
        int c = 1;
        if (output.length() != 0) {
            if (input.charAt(input.length() - c) == '(' || checkOperatorCoins(input.charAt(input.length() - c))) {
                while (input.charAt(input.length() - c) == '(' || checkOperatorCoins(input.charAt(input.length() - c))) {
                    c++;
                    if (c > input.length()) {
                        break;
                    }
                }
                c--;
                output.setLength(output.length() - c);
            }
            String buff = output.toString();
            if (output.length() != 0) {
                for (int j = 0; j < (countSymbols(buff, '(') - countSymbols(buff, ')')); j++) {
                    output.insert(output.length(), ')');
                }
            }
        }
        return output;
    }

    public static int countSymbols(String input, char symbol) {
        int output = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == symbol)
                output++;
        }
        return output;
    }

    public static boolean IsWrongInput(String inputText) {
        StringBuilder tmp = new StringBuilder();
        tmp.append(ValidityCheckers.checkBrackets(inputText));
        if (tmp.length() == 0) {
            return true;
        }
        if (tmp.length() == 1) {
            if (checkOperatorCoins(tmp.charAt(tmp.length() - 1))) {
                return true;
            }
        }
        return isOneOperand(tmp);
    }

    private static boolean isOneOperand(StringBuilder input) {
        int c;
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                c = i;
                while (Character.isDigit(input.charAt(c))) {
                    c++;
                    if (c > (input.length() - 1)) {
                        return true;
                    }
                }
                for (int j = c; j < input.length(); j++) {
                    if (Character.isDigit(input.charAt(j))) {
                        return false;
                    }
                    if (j == input.length() - 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean symbolAlreadySet(String input, char symbol) {
        if (input.length() != 0) {
            for (int i = input.length() - 1; i >= 0; i--) {
                if (input.charAt(i) == symbol) {
                    return true;
                }
                if (checkOperatorCoins(input.charAt(i))) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return false;
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

    public static int digitsBeforeFPoint(String input) {
        int digits = 0;
        for (int i = input.length()- 1; i >= 0; i--) {
            if (input.charAt(i) == '.') {
                return digits;
            }
            if (checkOperatorCoins(input.charAt(i))) {
                return digits;
            }
            digits++;
        }
        return digits;
    }

    public static boolean percentInPreviousOperand(String input) {   // TODO: implement
        return false;
    }
}
