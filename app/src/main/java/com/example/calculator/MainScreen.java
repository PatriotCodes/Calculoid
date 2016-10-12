package com.example.calculator;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class MainScreen extends Activity implements View.OnClickListener {

    Button one, two, three, four, five, six, seven, eight,
            nine, zero, add, sub, mul, div, equal, dot, percent, clear,
            erase, mAdd, mSub, mReg, mClear, brackets;
    Vibrator vibe;
    TextView inputStr, outputStr;
    HorizontalScrollView inputScroll;
    boolean firstBracket = true, bracketSet = false;
    StringBuilder tmpStr = new StringBuilder();
    String appVersion = "alpha build v0.41";
    private static final int vibrationIntensity = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        vibe = (Vibrator) MainScreen.this.getSystemService(Context.VIBRATOR_SERVICE);
        Toast toast = Toast.makeText(getApplicationContext(), appVersion, Toast.LENGTH_SHORT);
        toast.show();

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

        inputStr = (TextView) findViewById(R.id.input_string);
        outputStr = (TextView) findViewById(R.id.output_string);

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

        mAdd.setOnClickListener(this);
        mSub.setOnClickListener(this);
        mReg.setOnClickListener(this);
        mClear.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        vibe.vibrate(vibrationIntensity);
        tmpStr.setLength(0);
        inputScroll.postDelayed(new Runnable() {
            @Override
            public void run() {
                inputScroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        }, 100L);
        switch (v.getId()) {
            case R.id.button1:
                if (outputStr.length() != 0) {
                    Clear();
                }
                if (inputStr.length() != 0) {
                    if (inputStr.getText().toString().trim().charAt(inputStr.length() - 1) == ')') {
                        inputStr.append("*");
                    }
                }
                inputStr.append("1");
                if (bracketSet) {
                    firstBracket = false;
                }
                break;
            case R.id.button2:
                if (outputStr.length() != 0) {
                    Clear();
                }
                if (inputStr.length() != 0) {
                    if (inputStr.getText().toString().trim().charAt(inputStr.length() - 1) == ')') {
                        inputStr.append("*");
                    }
                }
                inputStr.append("2");
                if (bracketSet) {
                    firstBracket = false;
                }
                break;
            case R.id.button3:
                if (outputStr.length() != 0) {
                    Clear();
                }
                if (inputStr.length() != 0) {
                    if (inputStr.getText().toString().trim().charAt(inputStr.length() - 1) == ')') {
                        inputStr.append("*");
                    }
                }
                inputStr.append("3");
                if (bracketSet) {
                    firstBracket = false;
                }
                break;
            case R.id.button4:
                if (outputStr.length() != 0) {
                    Clear();
                }
                if (inputStr.length() != 0) {
                    if (inputStr.getText().toString().trim().charAt(inputStr.length() - 1) == ')') {
                        inputStr.append("*");
                    }
                }
                inputStr.append("4");
                if (bracketSet) {
                    firstBracket = false;
                }
                break;
            case R.id.button5:
                if (outputStr.length() != 0) {
                    Clear();
                }
                if (inputStr.length() != 0) {
                    if (inputStr.getText().toString().trim().charAt(inputStr.length() - 1) == ')') {
                        inputStr.append("*");
                    }
                }
                inputStr.append("5");
                if (bracketSet) {
                    firstBracket = false;
                }
                break;
            case R.id.button6:
                if (outputStr.length() != 0) {
                    Clear();
                }
                if (inputStr.length() != 0) {
                    if (inputStr.getText().toString().trim().charAt(inputStr.length() - 1) == ')') {
                        inputStr.append("*");
                    }
                }
                inputStr.append("6");
                if (bracketSet) {
                    firstBracket = false;
                }
                break;
            case R.id.button7:
                if (outputStr.length() != 0) {
                    Clear();
                }
                if (inputStr.length() != 0) {
                    if (inputStr.getText().toString().trim().charAt(inputStr.length() - 1) == ')') {
                        inputStr.append("*");
                    }
                }
                inputStr.append("7");
                if (bracketSet) {
                    firstBracket = false;
                }
                break;
            case R.id.button8:
                if (outputStr.length() != 0) {
                    Clear();
                }
                if (inputStr.length() != 0) {
                    if (inputStr.getText().toString().trim().charAt(inputStr.length() - 1) == ')') {
                        inputStr.append("*");
                    }
                }
                inputStr.append("8");
                if (bracketSet) {
                    firstBracket = false;
                }
                break;
            case R.id.button9:
                if (outputStr.length() != 0) {
                    Clear();
                }
                if (inputStr.length() != 0) {
                    if (inputStr.getText().toString().trim().charAt(inputStr.length() - 1) == ')') {
                        inputStr.append("*");
                    }
                }
                inputStr.append("9");
                if (bracketSet) {
                    firstBracket = false;
                }
                break;
            case R.id.button0:
                if (outputStr.length() != 0) {
                    Clear();
                }
                if (inputStr.length() != 0) {
                    if (inputStr.getText().toString().trim().charAt(inputStr.length() - 1) == ')') {
                        inputStr.append("*");
                    }
                }
                inputStr.append("0");
                if (bracketSet) {
                    firstBracket = false;
                }
                break;
            case R.id.buttonDot:
                if (outputStr.length() != 0) {
                    Clear();
                }
                if (inputStr.length() >= 1) {
                    if (inputStr.getText().charAt(inputStr.length() - 1) == '.') {
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
                if (outputStr.length() == 0) {
                    if (!ValidityCheckers.IsWrongInput(inputStr.getText().toString())) {
                        inputStr.setText(ValidityCheckers.checkBrackets(inputStr.getText().toString()));
                        outputStr.setText(getString(R.string.result_string, PostfixOperations.countExpression(inputStr)));
                        //outputStr.setText(convertToPostfix(inputStr.getText()));
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
                            outputStr.setText(getString(R.string.result_string, PostfixOperations.countExpression(inputStr)));
                            //outputStr.setText("= " + performOperations(convertToPostfix(checkBrackets(inputStr.getText().toString().trim()))));
                        }
                    }
                }
                break;
            case R.id.brackets:   // BRACKETS BUTTON
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
            case R.id.buttonErase:  // ERASE BUTTON
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
                        if (PostfixOperations.checkOperatorCoins(tmpStr.charAt(tmpStr.length() - 2))) {
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
        }
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
}
