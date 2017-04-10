package anupmak.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextView _screen;
    private String display = "";
    private String operator = "";
    private String result = "";
    private Double invalidValue = -1.0;
    private double precision = 1000.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _screen = (TextView) findViewById(R.id.result);
        _screen.setText(display);
    }

    private void setScreen() {
        _screen.setText(display);
    }

    public void onNumberClick(View view) {
        Button n = (Button) view;
        display += n.getText().toString();
        setScreen();
    }

    public void onOperatorClick(View view) {
        Button n = (Button) view;
        if (result.equals(""))
            display += n.getText().toString();
        else
            display = result + n.getText().toString();

        operator = n.getText().toString();
        setScreen();
    }

    public void onClearClick(View view) {
        display = "";
        operator = "";
        result = "";
        setScreen();
    }

    public void onEqualClick(View view) {
        String[] numbers = display.split(Pattern.quote(operator));
        if (numbers.length == 2) {
            Double d = operate(operator, numbers[0], numbers[1]);
            if(!d.equals(invalidValue)) {
                String x = String.valueOf(Math.round( d * precision ) / precision);
                display += "\n= " + x;
                result = x;
                operator = "";
            }
        }
        setScreen();
    }

    private double operate(String operator, String n1, String n2) {
        switch (operator) {
            case "+":
                return Double.valueOf(n1) + Double.valueOf(n2);
            case "-":
                return Double.valueOf(n1) - Double.valueOf(n2);
            case "*":
                return Double.valueOf(n1) * Double.valueOf(n2);
            case "/":
                try {
                    return Double.valueOf(n1) / Double.valueOf(n2);
                } catch (Exception e) {
                    Log.d("myApp", e.getMessage());
                }
            default:
                return invalidValue;
        }
    }
}
