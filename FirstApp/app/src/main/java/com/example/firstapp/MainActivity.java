package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp.SplashScreen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class MainActivity extends AppCompatActivity {
    String classCode;
    int grade;
    double[] avg = new double[8];
    String val = "";
    String listOfClass = "";
    EditText change;
    int i = 0;
    Button editClass, editGrade, submitEdit, saveGrade, saveClass, loadData, deleteFile;
    private EditText Class;
    private EditText Grade;
    private TextView output;
    private Button submitButton;
    private Button clearButton;
    private static final String fileName = "fileSave.txt";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        File directory = this.getFilesDir();
        final File file = new File(directory, fileName);

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
        editGrade = (Button) findViewById(R.id.EditSwitchClass);
        editClass = (Button) findViewById(R.id.EditSwitchGrade);
        saveClass = (Button) findViewById(R.id.saveClass);
        loadData = (Button) findViewById(R.id.loadData);
        deleteFile = (Button) findViewById(R.id.deleteFile);
        saveClass.setVisibility(View.INVISIBLE);
        saveGrade.setVisibility(View.INVISIBLE);

        String info = SplashScreen.info;

        System.out.println(info);

        try {
            listOfClass = info.substring(0, info.indexOf(','));
            val = info.substring(info.indexOf(',') + 1);
            output = (TextView) findViewById(R.id.class1);
            output.setText(listOfClass);
            output = (TextView) findViewById(R.id.grade1);
            output.setText(val);
        } catch (Exception e) {
            e.printStackTrace();
        }


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    classCode = Class.getText().toString();
                    output = (TextView) findViewById(R.id.class1);
                    output.setText(classCode);
                    grade = Integer.parseInt(String.valueOf(Grade.getText()));
                    listOfClass = listOfClass + classCode + "\n";
                    val = val + grade + "\n";
                    if (chkAvg(grade) == true) {
                        output = (TextView) findViewById(R.id.grade1);
                        output.setText(val);
                        output = (TextView) findViewById(R.id.class1);
                        output.setText(listOfClass);
                        avg[i] += (double) grade;
                    } else {
                        output = (TextView) findViewById(R.id.Average);
                        output.setText("Please enter a valid average between 0 and 100");
                        output = (TextView) findViewById(R.id.class1);
                        output.setText("");
                        editGrade.setVisibility(View.INVISIBLE);
                        editClass.setVisibility(View.INVISIBLE);
                        submitEdit.setVisibility(View.INVISIBLE);
                    }
                    i++;
                    if (i == 8) {
                        output = (TextView) findViewById(R.id.Average);
                        output.setText(String.valueOf(average(avg)));
                        Class.setFocusable(false);
                        Grade.setFocusable(false);
                        submitButton.setEnabled(false);
                        saveClass.setVisibility(View.VISIBLE);
                        saveGrade.setVisibility(View.VISIBLE);
                        i = 0;
                    }
                } catch (NumberFormatException e) {
                    output = (TextView) findViewById(R.id.Average);
                    output.setText("Please enter an average");
                }
            }
        });

        saveClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFile(fileName, listOfClass + "," + val);
            }
        });


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editGrade.setVisibility(View.VISIBLE);
                editClass.setVisibility(View.VISIBLE);
                submitEdit.setVisibility(View.VISIBLE);
                Class.setFocusable(true);
                submitEdit.setVisibility(View.INVISIBLE);
                Grade.setFocusable(true);
                Class.setFocusableInTouchMode(true);
                Grade.setFocusableInTouchMode(true);
                submitButton.setEnabled(true);
                change.setFocusable(false);
                change.setVisibility(View.GONE);
                grade = 0;
                classCode = "";
                for (int f = 0; f < 8; f++) {
                    avg[f] = 0;
                }
                val = "";
                listOfClass = "";
                clearText();
                i = 0;
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
                listOfClass = listOfClass.replace("\n", ",");
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
                        listOfClass = listOfClass.replace(",", "\n");
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
                val = val.replace("\n", ",");
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
                        val = val.replace(",", "\n");
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


        deleteFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file.delete();
            }
        });


        loadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String totalText = readFile(fileName);
                    listOfClass = totalText.substring(0, totalText.indexOf(','));
                    val = totalText.substring(totalText.indexOf(',') + 1);
                    output = (TextView) findViewById(R.id.class1);
                    output.setText(listOfClass);
                    output = (TextView) findViewById(R.id.grade1);
                    output.setText(val);
                }catch(Exception e){
                    Toast.makeText(MainActivity.this, "Error finding information",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
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
        if (i <= 100 && i >= 0) {
            return true;
        }
        return false;

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

    public void saveFile(String file, String text) {
        try {
            FileOutputStream fos = openFileOutput(file, MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
            Toast.makeText(MainActivity.this, "Saved to" + getFilesDir(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Error Saving File!", Toast.LENGTH_SHORT).show();
        }
    }

    public String readFile(String file) {
        String text = "";
        try {
            Toast.makeText(MainActivity.this, "Loading", Toast.LENGTH_SHORT).show();
            FileInputStream fis = openFileInput(file);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text = new String(buffer);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Error Loading File!", Toast.LENGTH_SHORT).show();
        }

        return text;
    }


}


