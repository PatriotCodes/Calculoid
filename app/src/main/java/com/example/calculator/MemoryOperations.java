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
import java.text.DecimalFormat;


public class MemoryOperations {

    private BigDecimal InMemory;
    private static final int MAX_FRACTION_DIG = 10;
    DecimalFormat df;

    public MemoryOperations() {
        InMemory = BigDecimal.ZERO;
        df = new DecimalFormat();
        df.setMaximumFractionDigits(MAX_FRACTION_DIG);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);
    }

    public void addToMemory(String number) {
        if (!number.isEmpty()) {
            StringBuilder tmp = new StringBuilder(number.trim());
            tmp.delete(0, 1);
            Double doubleValue = Double.valueOf(tmp.toString());
            BigDecimal BDValue = BigDecimal.valueOf(doubleValue);
            InMemory = InMemory.add(BDValue);
        }
    }

    public void subFromMemory(String number) {
        if (!number.isEmpty()) {
            StringBuilder tmp = new StringBuilder(number.trim());
            tmp.delete(0, 1);
            Double doubleValue = Double.valueOf(tmp.toString());
            BigDecimal BDValue = BigDecimal.valueOf(doubleValue);
            InMemory = InMemory.subtract(BDValue);
        }
    }

    public String printMemory() {
        return InMemory.stripTrailingZeros().toString();
    }

    public void clearMemory() {
        InMemory = BigDecimal.ZERO;
    }
}
