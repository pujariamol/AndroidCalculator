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
        double val =  Double.valueOf(currentContent);
        if(val == 0){
            txtBoxResults.setText("");
        }
        txtBoxResults.setText(txtBoxResults.getText().toString().concat(buttonLabel));

    }

    private void getOutput(){
        boolean flag = false;
        switch(operator){
            case "-" :
                result = inputOne - inputTwo;
                flag = true;
                break;
            case "+" :
                result = inputOne + inputTwo;
                flag= true;
                break;
        }
        if(flag){
            inputOne = 0;
            inputTwo = 0;
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
        txtBoxResults.setText("0");
        switch(buttonLabel){
            case "=":
                if(inputOne != 0 && inputTwo != 0)
                    getOutput();
                txtBoxResults.setText(String.valueOf(result));
                break;
            case "C":
                txtBoxResults.setText("0");
                reset();
                break;
            default:
                operator = buttonLabel;
        }
    }

    private void reset(){
        inputOne =0 ;
        inputTwo =0 ;
        result = 0;
        operator = "";

    }
}
