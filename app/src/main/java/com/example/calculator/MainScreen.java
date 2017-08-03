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


import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;


public class MainScreen extends Activity implements View.OnClickListener {

    Button one, two, three, four, five, six, seven, eight,
            nine, zero, add, sub, mul, div, equal, dot, percent, clear,
            erase, mAdd, mSub, mReg, mClear, brackets;
    Vibrator vibe;
    TextView inputStr, outputStr;
    HorizontalScrollView inputScroll;
    boolean firstBracket = true, bracketSet = false;
    StringBuilder tmpStr = new StringBuilder();
    private static final int VIBRATION_INTENSITY = 60;
    private static final int MAX_DIGITS_AFTER_FPOINT = 9;
    private float TEXT_SIZE_DEFAULT;
    private MemoryOperations memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        vibe = (Vibrator) MainScreen.this.getSystemService(Context.VIBRATOR_SERVICE);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Pixel.ttf");

        one = (Button) findViewById(R.id.button1);
        two = (Button) findViewById(R.id.button2);
        three = (Button) findViewById(R.id.button3);
        four = (Button) findViewById(R.id.button4);
        five = (Button) findViewById(R.id.button5);
        six = (Button) findViewById(R.id.button6);
        seven = (Button) findViewById(R.id.button7);
        eight = (Button) findViewById(R.id.button8);
        nine = (Button) findViewById(R.id.button9);
        zero = (Button) findViewById(R.id.button0);

        add = (Button) findViewById(R.id.buttonAdd);
        sub = (Button) findViewById(R.id.buttonSub);
        mul = (Button) findViewById(R.id.buttonMul);
        div = (Button) findViewById(R.id.buttonDiv);
        equal = (Button) findViewById(R.id.buttonEquals);
        dot = (Button) findViewById(R.id.buttonDot);
        percent = (Button) findViewById(R.id.buttonPercent);
        clear = (Button) findViewById(R.id.buttonClear);
        erase = (Button) findViewById(R.id.buttonErase);
        brackets = (Button) findViewById(R.id.brackets);

        mAdd = (Button) findViewById(R.id.buttonMAdd);
        mSub = (Button) findViewById(R.id.buttonMSub);
        mReg = (Button) findViewById(R.id.buttonMReg);
        mClear = (Button) findViewById(R.id.buttonMClear);

        SetTypeface(typeface);

        inputStr = (TextView) findViewById(R.id.input_string);
        outputStr = (TextView) findViewById(R.id.output_string);

        inputStr.setTypeface(typeface);
        outputStr.setTypeface(typeface);
        TEXT_SIZE_DEFAULT = outputStr.getTextSize();

        inputScroll = (HorizontalScrollView) findViewById(R.id.inputScrollView);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        mul.setOnClickListener(this);
        div.setOnClickListener(this);
        equal.setOnClickListener(this);
        dot.setOnClickListener(this);
        percent.setOnClickListener(this);
        clear.setOnClickListener(this);
        erase.setOnClickListener(this);
        brackets.setOnClickListener(this);

        memory = new MemoryOperations();
        mAdd.setOnClickListener(this);
        mSub.setOnClickListener(this);
        mReg.setOnClickListener(this);
        mClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        vibe.vibrate(VIBRATION_INTENSITY);
        tmpStr.setLength(0);
        inputScroll.postDelayed(new Runnable() {
            @Override
            public void run() {
                inputScroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        }, 100L);
        switch (v.getId()) {

            /*
                Digit buttons
            */

            case R.id.button1:
                inputDigit("1");
                break;
            case R.id.button2:
                inputDigit("2");
                break;
            case R.id.button3:
                inputDigit("3");
                break;
            case R.id.button4:
                inputDigit("4");
                break;
            case R.id.button5:
                inputDigit("5");
                break;
            case R.id.button6:
                inputDigit("6");
                break;
            case R.id.button7:
                inputDigit("7");
                break;
            case R.id.button8:
                inputDigit("8");
                break;
            case R.id.button9:
                inputDigit("9");
                break;
            case R.id.button0:
                inputDigit("0");
                break;
            case R.id.buttonPercent:
                inputPercent();
                break;

            /*
                Operation buttons
            */

            case R.id.buttonDot:
                if (outputStr.length() != 0) {
                    Clear();
                }
                if (inputStr.length() >= 1) {
                    if (ValidityCheckers.symbolAlreadySet((inputStr.getText().toString()),'.')) {
                        break;
                    }
                    if (inputStr.getText().charAt(inputStr.length() - 1) == ')') {
                        inputStr.append("*");
                    }
                    if (!Character.isDigit(inputStr.getText().charAt(inputStr.length() - 1))) {
                        inputStr.append("0");
                    }
                }
                else {
                    inputStr.append("0");
                }
                inputStr.append(".");
                if (bracketSet) {
                    firstBracket = false;
                }
                break;
            case R.id.buttonAdd:
                if (outputStr.length() != 0) {
                    tmpStr.append(outputStr.getText().toString().trim());
                    tmpStr.delete(0, 1);
                    inputStr.setText(tmpStr);
                    ClearOutput();
                }
                if (!inputStr.getText().toString().isEmpty()) {
                    tmpStr.append(inputStr.getText().toString().trim());
                    if (tmpStr.length() == 1 && tmpStr.charAt(tmpStr.length() - 1) == '-') {
                        tmpStr = tmpStr.deleteCharAt(tmpStr.length() - 1);
                        inputStr.setText(tmpStr);
                        break;
                    }
                    if (tmpStr.charAt(tmpStr.length() - 1) == '(') {
                        break;
                    }
                    if (tmpStr.charAt(tmpStr.length() - 1) == '-' || tmpStr.charAt(tmpStr.length() - 1) == '*' || tmpStr.charAt(tmpStr.length() - 1) == '/') {
                        tmpStr = tmpStr.deleteCharAt(tmpStr.length() - 1);
                        if (tmpStr.charAt(tmpStr.length() - 1) == '-' || tmpStr.charAt(tmpStr.length() - 1) == '*' || tmpStr.charAt(tmpStr.length() - 1) == '/') {
                            tmpStr = tmpStr.deleteCharAt(tmpStr.length() - 1);
                        }
                    }
                    if (tmpStr.charAt(tmpStr.length() - 1) != '+' && tmpStr.charAt(tmpStr.length() - 1) != '(') {
                        tmpStr.append("+");
                    }
                    inputStr.setText(tmpStr);
                    firstBracket = true;
                }
                break;
            case R.id.buttonSub:
                if (outputStr.length() != 0) {
                    tmpStr.append(outputStr.getText().toString().trim());
                    tmpStr.delete(0, 1);
                    inputStr.setText(tmpStr);
                    ClearOutput();
                }
                if (!inputStr.getText().toString().isEmpty()) {
                    tmpStr.append(inputStr.getText().toString().trim());
                    if (tmpStr.charAt(tmpStr.length() - 1) == '+') {
                        tmpStr = tmpStr.deleteCharAt(tmpStr.length() - 1);
                    }
                    if (tmpStr.charAt(tmpStr.length() - 1) != '-') {
                        tmpStr.append("-");
                    }
                    inputStr.setText(tmpStr);
                    firstBracket = true;
                } else {
                    inputStr.append("-");
                }
                break;
            case R.id.buttonMul:
                if (outputStr.length() != 0) {
                    tmpStr.append(outputStr.getText().toString().trim());
                    tmpStr.delete(0, 1);
                    inputStr.setText(tmpStr);
                    ClearOutput();
                }
                if (!inputStr.getText().toString().isEmpty()) {
                    tmpStr.append(inputStr.getText().toString().trim());
                    if (tmpStr.length() == 1 && tmpStr.charAt(tmpStr.length() - 1) == '-') {
                        tmpStr = tmpStr.deleteCharAt(tmpStr.length() - 1);
                        inputStr.setText(tmpStr);
                        break;
                    }
                    if (tmpStr.charAt(tmpStr.length() - 1) == '(') {
                        break;
                    }
                    if (tmpStr.charAt(tmpStr.length() - 1) == '/' || tmpStr.charAt(tmpStr.length() - 1) == '+' || tmpStr.charAt(tmpStr.length() - 1) == '-') {
                        tmpStr = tmpStr.deleteCharAt(tmpStr.length() - 1);
                        if (tmpStr.charAt(tmpStr.length() - 1) == '/' || tmpStr.charAt(tmpStr.length() - 1) == '+' || tmpStr.charAt(tmpStr.length() - 1) == '-') {
                            tmpStr = tmpStr.deleteCharAt(tmpStr.length() - 1);
                        }
                    }
                    if (tmpStr.charAt(tmpStr.length() - 1) != '*' && tmpStr.charAt(tmpStr.length() - 1) != '(') {
                        tmpStr.append("*");
                    }
                    inputStr.setText(tmpStr);
                    firstBracket = true;
                }
                break;
            case R.id.buttonDiv:
                if (outputStr.length() != 0) {
                    tmpStr.append(outputStr.getText().toString().trim());
                    tmpStr.delete(0, 1);
                    inputStr.setText(tmpStr);
                    ClearOutput();
                }
                if (!inputStr.getText().toString().isEmpty()) {
                    tmpStr.append(inputStr.getText().toString().trim());
                    if (tmpStr.length() == 1 && tmpStr.charAt(tmpStr.length() - 1) == '-') {
                        tmpStr = tmpStr.deleteCharAt(tmpStr.length() - 1);
                        inputStr.setText(tmpStr);
                        break;
                    }
                    if (tmpStr.charAt(tmpStr.length() - 1) == '(') {
                        break;
                    }
                    if (tmpStr.charAt(tmpStr.length() - 1) == '*' || tmpStr.charAt(tmpStr.length() - 1) == '+' || tmpStr.charAt(tmpStr.length() - 1) == '-') {
                        tmpStr = tmpStr.deleteCharAt(tmpStr.length() - 1);
                        if (tmpStr.charAt(tmpStr.length() - 1) == '*' || tmpStr.charAt(tmpStr.length() - 1) == '+' || tmpStr.charAt(tmpStr.length() - 1) == '-') {
                            tmpStr = tmpStr.deleteCharAt(tmpStr.length() - 1);
                        }
                    }
                    if (tmpStr.charAt(tmpStr.length() - 1) != '/' && tmpStr.charAt(tmpStr.length() - 1) != '(') {
                        tmpStr.append("/");
                    }
                    inputStr.setText(tmpStr);
                    firstBracket = true;
                }
                break;
            case R.id.buttonEquals:
                SetDefaultTextSize();
                if (outputStr.length() == 0) {
                    if (!ValidityCheckers.IsWrongInput(inputStr.getText().toString())) {
                        inputStr.setText(ValidityCheckers.checkBrackets(inputStr.getText().toString()));
                        outputStr.setText(ResizeText(getString(R.string.result_string, PostfixOperations.countExpression(inputStr))));
                    } else {
                        Clear();
                    }
                } else {
                    tmpStr.append(inputStr.getText().toString().trim());
                    if (Character.isDigit(tmpStr.charAt(tmpStr.length() - 1))) {
                        StringBuilder error = new StringBuilder();//////////
                        StringBuilder tmp;/////////////
                        tmp = getLastOperation(tmpStr);
                        if (error != tmp) {
                            tmpStr.setLength(0);
                            tmpStr.append(outputStr.getText().toString());
                            tmpStr.deleteCharAt(0);
                            inputStr.setText(tmpStr.toString());
                            inputStr.append(tmp);
                            outputStr.setText(ResizeText(getString(R.string.result_string, PostfixOperations.countExpression(inputStr))));
                        }
                    }
                }
                break;
            case R.id.brackets:
                if (outputStr.length() != 0) {
                    Clear();
                }
                if (firstBracket) {
                    if (inputStr.length() == 0) {
                        inputStr.append("(");
                    } else if (Character.isDigit(inputStr.getText().charAt(inputStr.length() - 1)) || checkCloseBracketCoins(inputStr.getText().charAt(inputStr.length() - 1)) || inputStr.getText().charAt(inputStr.length() - 1) == '.') {
                        inputStr.append("*(");
                    } else {
                        inputStr.append("(");
                    }
                    bracketSet = true;
                } else {
                    if (ValidityCheckers.countSymbols(inputStr.getText().toString(), '(') > ValidityCheckers.countSymbols(inputStr.getText().toString(), ')')) {
                        inputStr.append(")");
                        if (ValidityCheckers.countSymbols(inputStr.getText().toString(), '(') == ValidityCheckers.countSymbols(inputStr.getText().toString(), ')')) {
                            bracketSet = false;
                            firstBracket = true;
                        }
                    }
                }
                break;
            case R.id.buttonErase:
                if (outputStr.length() != 0) {
                    Clear();
                } else if (inputStr.length() != 0) {
                    tmpStr.append(inputStr.getText().toString().trim());
                    if (inputStr.length() > 1) {
                        if (tmpStr.charAt(tmpStr.length() - 1) == ')') {
                            if (ValidityCheckers.countSymbols(inputStr.getText().toString(), '(') == ValidityCheckers.countSymbols(inputStr.getText().toString(), ')')) {
                                bracketSet = true;
                                firstBracket = false;
                            }
                        }
                        if (tmpStr.charAt(tmpStr.length() - 1) == '(') {
                            if (tmpStr.charAt(tmpStr.length() - 2) != '(') {
                                bracketSet = false;
                            }
                        }
                        if (ValidityCheckers.checkOperatorCoins(tmpStr.charAt(tmpStr.length() - 2))) {
                            firstBracket = true;
                        }
                        if (Character.isDigit(tmpStr.charAt(tmpStr.length() - 1))) {
                            if (tmpStr.charAt(tmpStr.length() - 2) == '(') {
                                firstBracket = true;
                            }
                        }
                    } else if (tmpStr.charAt(tmpStr.length() - 1) == '(') {
                        firstBracket = true;
                        bracketSet = false;
                    }
                    tmpStr = tmpStr.deleteCharAt(tmpStr.length() - 1);
                    inputStr.setText(tmpStr);
                }
                break;
            case R.id.buttonClear:
                Clear();
                break;

            /*
                Memory buttons
            */

            case R.id.buttonMAdd:
                memory.addToMemory(outputStr.getText().toString());
                break;
            case R.id.buttonMSub:
                memory.subFromMemory(outputStr.getText().toString());
                break;
            case R.id.buttonMReg:
                SetDefaultTextSize();
                outputStr.setText(getString(R.string.result_string, memory.printMemory()));
                break;
            case R.id.buttonMClear:
                memory.clearMemory();
                break;
        }
    }

    private void inputDigit(String digit) {
        if (outputStr.length() != 0) {
            Clear();
        }
        if (ValidityCheckers.digitsBeforeFPoint(inputStr.getText().toString()) == MAX_DIGITS_AFTER_FPOINT) {
            return;
        }
        if (inputStr.length() != 0) {
            if (inputStr.getText().toString().trim().charAt(inputStr.length() - 1) == ')') {
                inputStr.append("*");
            }
        }
        inputStr.append(digit);
        if (bracketSet) {
            firstBracket = false;
        }
    }

    private void inputPercent() {
        if (outputStr.length() != 0) {
            Clear();
        }
        if (inputStr.length() == 0) {
            return;
        }
        if (inputStr.length() >= 1) {
            if (ValidityCheckers.symbolAlreadySet((inputStr.getText().toString()), '%')) {
                return;
            }
            if (!Character.isDigit(inputStr.getText().charAt(inputStr.length() - 1))) {
                return;
            }
            if (ValidityCheckers.percentInPreviousOperand(inputStr.getText().toString())) {
                return;
            }
        }
        inputStr.append("%");
    }

    public boolean checkCloseBracketCoins(char symbol) {
        char bracket = ')';
        return bracket == symbol;
    }

    public void Clear() {
        inputStr.setText("");
        outputStr.setText("");
        firstBracket = true;
        bracketSet = false;
        tmpStr.setLength(0);
    }

    public void ClearOutput() {
        outputStr.setText("");
        firstBracket = true;
        bracketSet = false;
        tmpStr.setLength(0);
    }

    public StringBuilder getLastOperation(StringBuilder input) { // returns "error" if no sign
        int i = 1;
        StringBuilder output = new StringBuilder();
        StringBuilder error = new StringBuilder();
        error.append("error");
        while (Character.isDigit(input.charAt(input.length() - i))) {
            output.append(input.charAt(input.length() - i));
            i++;
            if (i > input.length()) {
                return error;
            }
        }
        output.append(input.charAt(input.length() - i));
        if (i != input.length() && Character.isDigit(input.charAt(input.length() - (i - 1)))) {
            return output.reverse();
        }
        return error;
    }

    private void SetTypeface(Typeface typeface) {
        one.setTypeface(typeface);
        two.setTypeface(typeface);
        three.setTypeface(typeface);
        four.setTypeface(typeface);
        five.setTypeface(typeface);
        six.setTypeface(typeface);
        seven.setTypeface(typeface);
        eight.setTypeface(typeface);
        nine.setTypeface(typeface);
        zero.setTypeface(typeface);

        add.setTypeface(typeface);
        sub.setTypeface(typeface);
        mul.setTypeface(typeface);
        div.setTypeface(typeface);
        equal.setTypeface(typeface);
        dot.setTypeface(typeface);
        percent.setTypeface(typeface);
        clear.setTypeface(typeface);
        erase.setTypeface(typeface);
        brackets.setTypeface(typeface);

        mAdd.setTypeface(typeface);
        mSub.setTypeface(typeface);
        mReg.setTypeface(typeface);
        mClear.setTypeface(typeface);
    }

    private String ResizeText(String str) {                            // TODO: refactor, count overflow based on \n
        int overflow = str.length() - MAX_DIGITS_AFTER_FPOINT;
        if (overflow > 0) {
            for (int i = 0; i < overflow; i++) {
                float textSize = outputStr.getTextSize();
                outputStr.setTextSize(TypedValue.COMPLEX_UNIT_PX,(float)(outputStr.getTextSize() - outputStr.getTextSize() * 0.05));
            }
        }
        return str;
    }

    private void SetDefaultTextSize() {
        outputStr.setTextSize(TypedValue.COMPLEX_UNIT_PX,TEXT_SIZE_DEFAULT);
    }
}
