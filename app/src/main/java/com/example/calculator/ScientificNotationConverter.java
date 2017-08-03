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

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;

public class ScientificNotationConverter {

    public String convertToExponentialForm(BigDecimal num, final int MAX_FRACTION) {
        String[] splitter = num.toString().split("\\.");
        StringBuilder result = new StringBuilder();
        if (splitter.length > 0) {
            if (splitter[0].length() >= MAX_FRACTION) {
                result.append(splitter[0].charAt(0));
                result.append(".");
                for (int i = 1; i <= MAX_FRACTION; i++) {
                    result.append(splitter[0].charAt(i));
                }
                result = stripTrailingZeros(result);
                result.append("E+");
                result.append(splitter[0].length() - 1);
            } else if (splitter.length > 1){
                if ((splitter[1].length() - 3) > MAX_FRACTION) {
                    String expValue = getExponentValue(splitter[1]);
                    result.append(splitter[0]);
                    result.append(".");
                    for (int i = 1; i < MAX_FRACTION; i++) {
                        result.append(splitter[1].charAt(i));
                    }
                    result.append(expValue);
                } else {
                    result.append(num.toString());
                }
            } else {
                result.append(num.toString());
            }
        }
        return result.toString();
    }

    public StringBuilder stripTrailingZeros(StringBuilder str) {
        boolean zerosLeft = true;
        while (zerosLeft) {
            if (str.charAt(str.length() - 1) == '0') {
                str.deleteCharAt(str.length() - 1);
            }
            else {
                zerosLeft = false;
            }
        }
        return str;
    }

    public String getExponentValue (String number) {
        StringBuilder result = new StringBuilder();
        Deque<Character> expVal = new ArrayDeque<Character>();
        for (int i = number.length() - 1; i >= 0; i--) {
            if (number.charAt(i) != 'E') {
                expVal.push(number.charAt(i));
            } else if (i == 0) {
                return result.toString();
            } else {
                break;
            }
        }
        result.append("E");
        while (!expVal.isEmpty()) {
            result.append(expVal.pop());
        }
        return result.toString();
    }

    public String stripTrailingZerosPost(String str) {
        boolean zerosLeft = true;
        StringBuilder tmp = new StringBuilder(str);
        while (zerosLeft) {
            if (tmp.length() <= 1) {
                zerosLeft = false;
            }
            else if (tmp.charAt(tmp.length() - 1) == '.') {
                tmp.deleteCharAt(tmp.length() - 1);
                zerosLeft = false;
            }
            else if (tmp.charAt(tmp.length() - 1) == '0') {
                tmp.deleteCharAt(tmp.length() - 1);
            }
            else {
                zerosLeft = false;
            }
        }
        return tmp.toString();
    }

}
