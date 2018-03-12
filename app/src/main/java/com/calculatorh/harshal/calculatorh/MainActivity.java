package com.calculatorh.harshal.calculatorh;


import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.udojava.evalex.Expression;
import java.math.BigDecimal;
import android.widget.EditText;
import android.widget.Button;

import com.calculatorh.harshal.calculatorh.databinding.ActivityMainBinding;

import java.math.RoundingMode;
import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    public BigDecimal result = new BigDecimal(0.00000000000000000000000000000000000);

    private DecimalFormat decimalFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        decimalFormat = new DecimalFormat("#.##########");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        /* Disabled default keypad for edit text field. */
        EditText edittxt = (EditText) findViewById(R.id.editText);
        edittxt.setShowSoftInputOnFocus(false);

        EditText infoTextView = (EditText) findViewById(R.id.infoTextView);
        infoTextView.setShowSoftInputOnFocus(false);

        /* Disable * and / operator buttons when input text field is blank.*/
        binding.buttonMultiply.setEnabled(false);
        binding.buttonDivide.setEnabled(false);

        binding.buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Preventing from adding second dot in same number. */
                String exprStr = binding.editText.getText().toString();
                if(exprStr.length()>=1) {

                    for (int i = exprStr.length()-1; i>=0; i--){
                        if(exprStr.charAt(i)=='+' || exprStr.charAt(i)=='-' || exprStr.charAt(i)=='*' || exprStr.charAt(i)=='/'){
                            binding.editText.setText(binding.editText.getText() + ".");
                            buttonPressed();
                            break;
                        } else if(exprStr.charAt(i)=='.'){
                            break;
                        }else if(i==0){
                            binding.editText.setText(binding.editText.getText() + ".");
                            buttonPressed();
                            break;
                        }
                    }
                }else {
                    binding.editText.setText(binding.editText.getText() + ".");
                    buttonPressed();
                }
            }
        });

        binding.buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "0");
                buttonPressed();
            }
        });

        binding.buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "1");
                buttonPressed();
            }
        });

        binding.buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "2");
                buttonPressed();
            }
        });

        binding.buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "3");
                buttonPressed();
            }
        });

        binding.buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "4");
                buttonPressed();
            }
        });

        binding.buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "5");
                buttonPressed();
            }
        });

        binding.buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "6");
                buttonPressed();
            }
        });

        binding.buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "7");
                buttonPressed();
            }
        });

        binding.buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "8");
                buttonPressed();
            }
        });

        binding.buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "9");
                buttonPressed();
            }
        });

        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "+");
                buttonPressed();
            }
        });

        binding.buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "-");
                buttonPressed();
            }
        });

        binding.buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "*");
                buttonPressed();
            }
        });

        binding.buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "/");
                buttonPressed();
            }
        });

        binding.buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Handling divide by zero condition.*/
                /* Search for pattern "/number". Check the number. If it is zero the result is infinity and set the infinity flag.*/

                int startPos, endPos;
                boolean isInfinity = false;
                String dbzString, dbzResult="";
                String exprStr = binding.editText.getText().toString();

                if(exprStr.length()>=1) {

                    for (int i = 0; i<=exprStr.length()-1; i++){

                        if(exprStr.charAt(i)=='/'){
                            startPos = i;
                            endPos = -1;

                            for (int j = i+1; j<=exprStr.length()-1; j++){
                                if(exprStr.charAt(j)=='+' || exprStr.charAt(j)=='-' || exprStr.charAt(j)=='*' || exprStr.charAt(j)=='/'){
                                    endPos = j;
                                    break;
                                } else if(j==exprStr.length()-1){
                                    endPos = j+1;
                                    break;
                                }
                            }

                            if(endPos != -1){
                                dbzString = exprStr.substring(startPos+1,endPos);

                                if(0 == Double.parseDouble(dbzString)){
                                    dbzResult = "∞";
                                    binding.infoTextView.setText("= " + binding.editText.getText().toString());
                                    binding.editText.setText(dbzResult);
                                    isInfinity = true;
                                    /*Disable operators buttons.*/
                                    binding.buttonMultiply.setEnabled(false);
                                    binding.buttonDivide.setEnabled(false);
                                    binding.buttonSubtract.setEnabled(false);
                                    binding.buttonAdd.setEnabled(false);
                                    binding.buttonDot.setEnabled(false);

                                    break;
                                }
                            }

                        }

                    }

                }


                /* Check last character of expression. If its operator or its empty then
                 * prevent the evaluation of expression*/

                Character lastChar;
                Boolean flag=false;
                exprStr = binding.editText.getText().toString();
                if(exprStr.length()>=1) {
                    lastChar = exprStr.charAt(exprStr.length() - 1);
                }else{
                    lastChar = ' ';
                }

                if(exprStr.indexOf('+') != -1 || exprStr.indexOf('-') != -1 || exprStr.indexOf('*') != -1 || exprStr.indexOf('/') != -1 ){
                    flag=true;
                }

                if(lastChar!='.' && lastChar!='/' && lastChar!='*' && lastChar!='-' && lastChar!='+' && lastChar!=' ' && flag==true && isInfinity==false) {
                    binding.infoTextView.setText("= " + binding.editText.getText().toString());
                    Expression expression = new Expression(binding.editText.getText().toString());

                    /* result = expression.eval(); */
                    result = new Expression(binding.editText.getText().toString()).setPrecision(100).setRoundingMode(RoundingMode.UP).eval();
                    result = result.setScale(5, RoundingMode.CEILING).stripTrailingZeros();
                    binding.editText.setText(result.toPlainString());
                }

            }
        });

        binding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.editText.setText("");
                binding.infoTextView.setText("");

                /* Disable * and / buttons after clearing input text field.*/
                binding.buttonMultiply.setEnabled(false);
                binding.buttonDivide.setEnabled(false);
            }
        });

        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ExpressionStr = binding.editText.getText().toString();
                if (ExpressionStr.length() >=1 ) {
                    ExpressionStr = ExpressionStr.substring(0, ExpressionStr.length() - 1);
                    binding.editText.setText(ExpressionStr);
                } else if (ExpressionStr.length() <=1 ) {
                    binding.editText.setText("");
                }
                buttonPressed();
            }
        });
    }

    private void buttonPressed() {

        /* After each button press check the pressed button character. If its operator then
        * disable all operator buttons and if its number then enable all operator buttons.*/

        String expression;
        Character lastChar;
        expression = binding.editText.getText().toString();


        if(expression.length() != 0) {
            lastChar = expression.charAt(expression.length() - 1);

            /* Check if infinity is set in edittext. If it is then clear it and replace with pressed button character.*/
            if(expression.indexOf("∞")!=-1){
                binding.editText.setText(lastChar.toString());
            }

            if (lastChar == '.' || lastChar == '/' || lastChar == '*' || lastChar == '-' || lastChar == '+') {
                binding.buttonMultiply.setEnabled(false);
                binding.buttonDivide.setEnabled(false);
                binding.buttonSubtract.setEnabled(false);
                binding.buttonAdd.setEnabled(false);
                binding.buttonDot.setEnabled(false);
            } else {
                binding.buttonMultiply.setEnabled(true);
                binding.buttonDivide.setEnabled(true);
                binding.buttonSubtract.setEnabled(true);
                binding.buttonAdd.setEnabled(true);
                binding.buttonDot.setEnabled(true);
            }

        /* Set cursor position to right of the text.*/
            int pos = binding.editText.getText().length();
            binding.editText.setSelection(pos);
        }
        else{
            binding.buttonMultiply.setEnabled(false);
            binding.buttonDivide.setEnabled(false);
            binding.buttonAdd.setEnabled(false);
            binding.buttonDot.setEnabled(false);
        }


    }


}