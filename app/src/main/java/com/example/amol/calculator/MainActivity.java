package com.example.amol.calculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private TextView txtBoxResults;
    private double result,inputOne,inputTwo = 0 ;
    private String operator = "";
    private boolean operatorSet = false;


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

        if(operatorSet){
            txtBoxResults.setText("");
            operatorSet = false;
        }
        txtBoxResults.setText(txtBoxResults.getText().toString().concat(buttonLabel));

    }

    private void getOutput(){
        if(inputOne != 0 && inputTwo != 0) {
            boolean flag = false;
            switch (operator) {
                case "-":
                    result = inputOne - inputTwo;
                    inputOne = result;
                    flag = true;
                    break;
                case "+":
                    result = inputOne + inputTwo;
                    inputOne = result;
                    flag = true;
                    break;
            }
            if (flag) {

                inputTwo = 0;
            }
            txtBoxResults.setText(String.valueOf(result));
        }
    }

    public void onOperatorClick(View view) {
        String buttonLabel = ((Button)view).getText().toString();
        String currentContent =  txtBoxResults.getText().toString();

        if (inputOne == 0) {
            inputOne = Double.valueOf(currentContent);
        }else if(inputTwo == 0) {
            inputTwo = Double.valueOf(currentContent);
        }
        System.out.println("First Number = " + inputOne + " Second Number =" + inputTwo );
        if(buttonLabel.equals("-") || buttonLabel.equals("+")){
            System.out.println("+ - operator clicked");
            getOutput();
            operator = buttonLabel;
            operatorSet =true;
        }else if(buttonLabel.equals("=")){
            getOutput();
        }else if(buttonLabel.equals("C")){
            txtBoxResults.setText("0");
            reset();
        }
//        switch(buttonLabel){
//            case "-":
//                operator = buttonLabel;
//                getOutput();
//                break;
//            case "+":
//                operator = buttonLabel;
//                break;
//            case "=":
//                getOutput();
//                txtBoxResults.setText(String.valueOf(result));
//                break;
//            case "C":
//                txtBoxResults.setText("0");
//                reset();
//                break;
//            default:
//
//        }
    }

    private void reset(){
        inputOne =0 ;
        inputTwo =0 ;
        result = 0;
        operator = "";

    }
}
