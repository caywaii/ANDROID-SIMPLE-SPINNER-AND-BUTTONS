package com.carylsantiago.midtermsessionone;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.CheckBox;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.DatePickerDialog;
import java.util.Calendar;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;


public class MainActivity extends AppCompatActivity {
    private CheckBox burger, pizza, chicken;
    private Button btnOrder, btnCloseApp, btnGender, btnDate;
    private RadioButton rMale, rFemale, rGender;
    private RadioGroup radioGroup;
    private TextView tDate;
    private Spinner spin;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOrder = (Button) findViewById(R.id.order);
        btnCloseApp = (Button) findViewById(R.id.closeApp);
        burger = (CheckBox) findViewById(R.id.cbBurger);
        pizza = (CheckBox) findViewById(R.id.cbPizza);
        chicken = (CheckBox) findViewById(R.id.cbChicken);
        btnGender = (Button) findViewById(R.id.gender);
        radioGroup = (RadioGroup) findViewById(R.id.rdGroup);
        builder = new AlertDialog.Builder(this);
        btnDate = (Button) findViewById(R.id.pickDate);
        tDate = findViewById(R.id.tvDate);

        final String[] country = {"Please Select a Country", "USA", "Canada", "Japan", "Philippines", "UAE"};
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, country);
        spin = findViewById(R.id.spinner);
        spin.setAdapter(stringArrayAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), country[position] + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthofyear, int dayofmonth) {
                                tDate.setText(dayofmonth + " - " + (monthofyear + 1) + " - " + year);
                            }
                        },
               year, month, day );
                datePickerDialog.show();
            }
        });


        btnGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                rGender = (RadioButton) findViewById(selectedId);
                if(selectedId==-1){
                    Toast.makeText(MainActivity.this, "Nothing selected, please select 1", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, rGender.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCloseApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setMessage("You ordered the following:").setTitle("Pizza Ordering Form");
                int totalAmount = 0;
                StringBuilder result = new StringBuilder();
                result.append("Selected Items:");

                if(burger.isChecked()) {
                    builder.setMessage(result.append("\nBurger 60 Pesos"));
                    totalAmount += 60;
                }
                if(chicken.isChecked()){
                    builder.setMessage(result.append("\nChicken 50 PESOS"));
                    totalAmount += 50;

                }
                if(pizza.isChecked()){
                    builder.setMessage(result.append("\nPizza 100 PESOS"));
                    totalAmount += 100;
                }

                builder.setMessage(result.append("\nTotal Amount " + totalAmount));
                builder.setMessage(result.append("\n\n\t\t\tPay Now"));

                builder.setCancelable(true).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Payment Made", Toast.LENGTH_SHORT).show();
                        burger.setChecked(false);
                        chicken.setChecked(false);
                        pizza.setChecked(false);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    Toast.makeText(getApplicationContext(), "You choose to order more", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alert = builder.create();
                alert.setTitle("Confirm Orders");
                alert.show();
            }
        });


    }

}