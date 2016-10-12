package com.example.calculator;

/**
 * Created by Александр on 11.05.2016.
 */
public class ValidityCheckers {

    public static StringBuilder checkBrackets(String input) {
        StringBuilder output = new StringBuilder();
        output.append(input);
        int c = 1;
        if (output.length() != 0) {
            if (input.charAt(input.length() - c) == '(' || PostfixOperations.checkOperatorCoins(input.charAt(input.length() - c))) {
                while (input.charAt(input.length() - c) == '(' || PostfixOperations.checkOperatorCoins(input.charAt(input.length() - c))) {
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
                //if (countSymbols(output.toString(), '(') > countSymbols(output.toString(), ')')) {
                //for (int i = 0; i < output.length(); i++) {
                //    if (output.charAt(i) == ')') {
                //        for (int j = 0; j < (countSymbols(input, '(') - countSymbols(input, ')')); j++) {
                //            output.insert(i, ')');
                //        }
                //        return output;
                //    }
                //}
                for (int j = 0; j < (countSymbols(buff, '(') - countSymbols(buff, ')')); j++) {
                    output.insert(output.length(), ')');
                }
                //}
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
            if (PostfixOperations.checkOperatorCoins(tmp.charAt(tmp.length() - 1))) {
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
}
