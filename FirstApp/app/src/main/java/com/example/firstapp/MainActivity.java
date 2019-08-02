package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    String classCode;
    int grade;
    double[] avg = new double[8];
    String val = "";
    String listOfClass = "";
    private EditText Class;
    private EditText Grade;
    EditText change;
    int i = 0;
    private TextView output;
    private Button submitButton;
    private Button clearButton;
    Button editClass, editGrade, submitEdit;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        change = (EditText) findViewById(R.id.edit);
        change.setFocusable(false);
        change.setVisibility(View.GONE);
        Class = (EditText) findViewById(R.id.Class);
        Grade = (EditText) findViewById(R.id.Grade);
        submitEdit = (Button) findViewById(R.id.submitEdit);
        Class.setFocusable(true);
        Grade.setFocusable(true);
        submitButton = (Button) findViewById(R.id.Forward);
        clearButton = (Button) findViewById(R.id.clearButton);
        editClass = (Button) findViewById(R.id.EditClass);
        editGrade = (Button) findViewById(R.id.editGrade);





        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classCode = Class.getText().toString();
                output = (TextView) findViewById(R.id.class1);
                output.setText(classCode);
                listOfClass = listOfClass + classCode + "\n";

                grade = Integer.valueOf(Grade.getText().toString());
                output = (TextView) findViewById(R.id.grade1);
                if (chkAvg(grade)) {
                    output.setText(Grade.getText());
                } else {
                    output.setText("Ruh Roh, not an average");
                }
                val = val + grade + "\n";

                output = (TextView) findViewById(R.id.grade1);
                output.setText(val);
                output = (TextView) findViewById(R.id.class1);
                output.setText(listOfClass);
                avg[i] += (double) grade;


                i++;
                if (i == 8) {
                    output = (TextView) findViewById(R.id.Average);
                    output.setText(String.valueOf(average(avg)));
                    Class.setFocusable(false);
                    Grade.setFocusable(false);
                    submitButton.setEnabled(false);
                    i = 0;
                }
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class.setFocusable(true);
                submitEdit.setVisibility(View.INVISIBLE);
                Grade.setFocusable(true);
                Class.setFocusableInTouchMode(true);
                Grade.setFocusableInTouchMode(true);
                submitButton.setEnabled(true);
                change.setFocusable(false);
                change.setVisibility(View.GONE);
                i = 0;
                val = "";
                listOfClass = "";
                clearText();
            }
        });
        editClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitEdit.setVisibility(View.VISIBLE);
                clearButton.setVisibility(View.INVISIBLE);
                editClass.setVisibility(View.INVISIBLE);
                editGrade.setVisibility(View.INVISIBLE);
                change.setVisibility(View.VISIBLE);
                change.setFocusable(true);
                change.setFocusableInTouchMode(true);
                listOfClass = listOfClass.replace("\n",",");
                change.setText(listOfClass);
                editGrade.setEnabled(false);

                submitEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listOfClass = String.valueOf(change.getText());
                        editGrade.setEnabled(true);
                        editClass.setEnabled(true);
                        submitEdit.setVisibility(View.INVISIBLE);
                        change.setVisibility(View.INVISIBLE);
                        output = (TextView) findViewById(R.id.grade1);
                        output.setText(val);
                        output = (TextView) findViewById(R.id.class1);
                        listOfClass=listOfClass.replace(",","\n");
                        output.setText(listOfClass);
                        clearButton.setVisibility(View.VISIBLE);
                        editClass.setVisibility(View.VISIBLE);
                        editGrade.setVisibility(View.VISIBLE);
                    }
                });

            }
        });


        editGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearButton.setVisibility(View.INVISIBLE);
                editClass.setVisibility(View.INVISIBLE);
                editGrade.setVisibility(View.INVISIBLE);
                change.setVisibility(View.VISIBLE);
                submitEdit.setVisibility(View.VISIBLE);
                change.setFocusable(true);
                change.setFocusableInTouchMode(true);
                val = val.replace("\n",",");
                change.setText(val);
                editClass.setEnabled(false);
                submitEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        val = String.valueOf(change.getText());
                        editGrade.setEnabled(true);
                        editClass.setEnabled(true);
                        submitEdit.setVisibility(View.INVISIBLE);
                        change.setVisibility(View.INVISIBLE);
                        output = (TextView) findViewById(R.id.grade1);
                        val=val.replace(",","\n");
                        output.setText(val);
                        output = (TextView) findViewById(R.id.class1);
                        output.setText(listOfClass);
                        clearButton.setVisibility(View.VISIBLE);
                        editClass.setVisibility(View.VISIBLE);
                        editGrade.setVisibility(View.VISIBLE);
                    }
                });

            }
        });



    }

    public void clearText() {
        output = (TextView) findViewById(R.id.class1);
        output.setText("");
        output = (TextView) findViewById(R.id.grade1);
        output.setText("");
        output = (TextView) findViewById(R.id.Average);
        output.setText("");
        output = (TextView) findViewById(R.id.Grade);
        output.setText("");
        output = (TextView) findViewById(R.id.Class);
        output.setText("");
    }


    public boolean chkAvg(int i) {
        if (i > 100 && i < 0) {
            return false;
        }
        return true;

    }

    public double average(double[] num) {
        double avg = 0;
        for (int i = 0; i < 8; i++) {
            avg = avg + num[i];
            System.out.println(avg);
        }
        avg = avg / 8;
        return avg;
    }


}
