package com.carylsantiago.midtermsessionone;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity {
    private CheckBox burger, pizza, chicken;
    private Button btnOrder, btnCloseApp, btnGender;
    private RadioButton rMale, rFemale, rGender;
    private RadioGroup radioGroup;
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