package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText dateOB,name, weight, height;
    Button calcBtn;
    TextView ageTxt,txtBmi,txtStatus,nameTxt,genderTxt;
    private RadioGroup sexBtnGroup;
    private RadioButton sexBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        genderTxt =(TextView) findViewById(R.id.txtSex);
        nameTxt = (TextView) findViewById(R.id.txtname);
        txtStatus = (TextView)findViewById(R.id.bmiStatus);
        txtBmi = (TextView) findViewById(R.id.bmiIndex);
        ageTxt =(TextView) findViewById(R.id.txtAge);
        dateOB = (EditText) findViewById(R.id.dob);
        name = (EditText)  findViewById(R.id.name);
        weight = (EditText) findViewById(R.id.weight);
        height = (EditText) findViewById(R.id.height);
        calcBtn = (Button) findViewById(R.id.calcbtn);
        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Results.class);

                //startActivity(intent);
                addListenerOnButton();
                myAge();
                String  Weight = weight.getText().toString().trim();

                String Height = height.getText().toString().trim();

               float finalWeight = Float.parseFloat(Weight);
               float finalHeight = Float.parseFloat(Height)/100;
               float bmi = bmiResults(finalWeight,finalHeight);
               if (bmi <15 ){
                   txtStatus.setText("Very severely Underweight");
               }
               else if (bmi <16){
                   txtStatus.setText("Severely Underweight");
               }
               else if (bmi< 18.5){
                    txtStatus.setText("Underweight");
               }
               else if (bmi< 25){
                    txtStatus.setText("Normal");
               }
               else if (bmi< 30){
                   txtStatus.setText("Overweight");
               }
               else if (bmi< 35){
                    txtStatus.setText("Obese Class I");
               }
               else if (bmi< 40){
                   txtStatus.setText("Obese Class II");
               }
               else {
                   txtStatus.setText("Obese Class III");
               }
               txtBmi.setText("BMI: "+ bmi);
                String uname = name.getText().toString().trim();

                nameTxt.setText("NAME :" +uname);

            }
            private void addListenerOnButton() {
                sexBtnGroup = (RadioGroup) findViewById(R.id.radioSexBtn);
                //get selected id for the radio btn
                int selectedId = sexBtnGroup.getCheckedRadioButtonId();
                //find returned id
                sexBtn =(RadioButton) findViewById(selectedId);


                genderTxt.setText(sexBtn.getText());
            }


            private float bmiResults(float finalWeight,float finalHeight) {

                return finalWeight/(finalHeight * finalHeight);

            }

            private int myAge() {
                String dob = dateOB.getText().toString().trim();
                int year = Calendar.getInstance().get(Calendar.YEAR);
                if (dob.isEmpty()){
                    //Toast.makeText(MainActivity.this, "Enter year of birth", Toast.LENGTH_SHORT).show();
                    dateOB.setError("Enter YOB");
                    dateOB.requestFocus();
                }
                else if(dob.compareTo(String.valueOf(year))>0){
                    Toast.makeText(MainActivity.this, "less than current year", Toast.LENGTH_SHORT).show();
                }
                else {
                    int age = year - Integer.parseInt(dob);
                    ageTxt.setText("AGE : "+ age);
                }
                return 0;
            }
        });
    }
}