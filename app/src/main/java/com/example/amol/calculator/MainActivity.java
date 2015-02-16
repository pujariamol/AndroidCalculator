package com.example.amol.calculator;

import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private TextView txtBoxResults;
    private int result,inputOne,inputTwo = 0 ;
    private String operator = "";
    private boolean isOperatorSet, isNewNumber = false;

    //isOperatorSet : used to check if an operator is set or not.
    // The text box will be cleared if this variable is set and the text box will accept new value.
    // isNewNumber : used to check if new value is added so that the program won't keep on adding/
    // subtracting on old result values.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        txtBoxResults = (TextView)findViewById(R.id.input_text);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onButtonClick(View view) {
        String buttonLabel = ((Button)view).getText().toString();
        String currentContent = txtBoxResults.getText().toString();
        if (isOperatorSet || Integer.valueOf(currentContent) == 0) {
            setTextBoxContent("");
            isOperatorSet = false;
        }
        currentContent = txtBoxResults.getText().toString().concat(buttonLabel);

        int length = currentContent.length();
        if(length <= 7 ) {
            setTextBoxContent(currentContent);
            isNewNumber = true;
        }else{
            Toast.makeText(this,"Length exceeded",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    private void getOutput(){
        if(inputOne != 0 && inputTwo != 0) {
                boolean flag = false;
                switch (operator) {
                    case "-":
                       result = inputOne - inputTwo;
                       flag = true;
                       break;
                    case "+":
                        result = inputOne + inputTwo;
                        flag = true;
                        break;
                }
            if (flag) {
                inputTwo = 0;
                inputOne = result;
                isNewNumber = false;
                isOperatorSet = false;
            }
            setTextBoxContent(String.valueOf(result));
        }
    }


    // this method is called on click of operator i.e. + - = and C
    public void onOperatorClick(View view) {
        String buttonLabel = ((Button)view).getText().toString();
        String currentContent =  txtBoxResults.getText().toString();

        if(inputOne == 0) {
            inputOne = Integer.valueOf(currentContent);
        }else if (inputTwo == 0 && isNewNumber) {
            inputTwo = Integer.valueOf(currentContent);

        }

        if(buttonLabel.equals("-") || buttonLabel.equals("+")){
            getOutput();
            operator = buttonLabel;
            isOperatorSet =true;
        }else if(buttonLabel.equals("=")){
            if(inputTwo == 0 && inputOne != 0 && operator.equals("-") && isNewNumber){
                result = -inputOne;
                setTextBoxContent(String.valueOf(result));
            }else {
                getOutput();
            }
        }else if(buttonLabel.equals("C")){
            setTextBoxContent("0");
            reset();
        }
        isNewNumber = false;
    }

    private void setTextBoxContent(String val){
        txtBoxResults.setText(val);
    }

    private void reset(){
        inputOne =0 ;
        inputTwo =0 ;
        result = 0;
        operator = "";
    }
}
