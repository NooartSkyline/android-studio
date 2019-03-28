package com.example.withawatbun.employer_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity2 extends AppCompatActivity {

    private TextView txt_Rubber_weight, txt_SumAmount;
    private EditText et_Garden_weight, et_Percentage_of_water, et_Price;
    private Button btn_Click, btn_Clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        et_Garden_weight = findViewById(R.id.et_Garden_weight);
        et_Percentage_of_water = findViewById(R.id.et_Percentage_of_water);
        et_Price = findViewById(R.id.et_Price);
        txt_Rubber_weight = findViewById(R.id.txt_Rubber_weight);
        txt_SumAmount = findViewById(R.id.txt_SumAmount);
        btn_Click = findViewById(R.id.btn_Click);
        btn_Clear = findViewById(R.id.btn_Clear);

        btn_Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_Garden_weight.getText() != null && !et_Garden_weight.getText().equals("") &&
                        et_Percentage_of_water.getText() != null && !et_Percentage_of_water.getText().equals("") &&
                        et_Price.getText() != null && !et_Price.getText().equals("")) {

                    Double Garden_weight = Double.parseDouble(et_Garden_weight.getText().toString());
                    Double Percentage_of_water = Double.parseDouble(et_Percentage_of_water.getText().toString());
                    Double Price = Double.parseDouble(et_Price.getText().toString());

                    txt_Rubber_weight.setText(weightToweight(Garden_weight, Percentage_of_water, Price).toString());
                    txt_SumAmount.setText(weightTomoney(Garden_weight, Percentage_of_water, Price).toString());
                } else {
//                    Toast.makeText(MainActivity2.this, "กรุณาใสข้อมูลให้ครบ..", Toast.LENGTH_SHORT).show();
                    new SweetAlertDialog(MainActivity2.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("คำเตือน..!")
                            .setContentText("กรุณาใส่จำนวนเงิน")
                            .setConfirmText("OK")
                            .show();

                }
            }
        });
        btn_Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_Garden_weight.setText("");
                et_Percentage_of_water.setText("");
                et_Price.setText("");
                txt_Rubber_weight.setText("0.0");
                txt_SumAmount.setText("0.0");


            }
        });
    }

    public Double weightTomoney(Double Garden_weight, Double Percentage_of_water, Double Price) {
        return (Garden_weight * Percentage_of_water / 100) * Price;
    }

    public Double weightToweight(Double Garden_weight, Double Percentage_of_water, Double Price) {
        return (Garden_weight * Percentage_of_water) / 100;
    }
}
