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

public class ScientificNotationConverter {

    public String convertToExponentialForm(BigDecimal num, final int MAX_FRACTION) {
        String[] splitter = num.toString().split("\\.");
        StringBuilder result = new StringBuilder();
        if (splitter.length > 0) {
            if (splitter[0].length() >= MAX_FRACTION) {
                result.append(splitter[0].charAt(0));
                result.append(".");
                for (int i = 1; i < MAX_FRACTION; i++) {
                    result.append(splitter[0].charAt(i));
                }
                result = stripTrailingZeros(result);
                result.append("E+");
                result.append(splitter[0].length() - 1);
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


}
