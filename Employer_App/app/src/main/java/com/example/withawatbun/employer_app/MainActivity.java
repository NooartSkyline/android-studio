package com.example.withawatbun.employer_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    private TextView txt_Employer, txt_Employee;
    private Button btn_Click;
    private EditText et_Amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_Employer = findViewById(R.id.txt_Employer);
        txt_Employee = findViewById(R.id.txt_Employee);
        et_Amount = findViewById(R.id.et_Amount);
        btn_Click = findViewById(R.id.btn_Click);
        btn_Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_Amount.getText().toString() != null && !et_Amount.getText().toString().equals("")) {

                    Double Amount = Double.parseDouble(et_Amount.getText().toString());
                    txt_Employer.setText(" " + Employer(Amount).toString());
                    txt_Employee.setText(" " + Employee(Amount).toString());
                } else {
                    new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("คำเตือน..!")
                            .setContentText("กรุณาใส่จำนวนเงิน")
                            .setConfirmText("OK")
                            .show();
                    txt_Employer.setText("");
                    txt_Employee.setText("");
                    et_Amount.isFocused();
                }
            }
        });
    }
//    public Double Employer(Double amount, int employer) {
//
//        // 2500x60 / 100  หรือ 2500× 0 .60
//        Double result_employer = amount * employer / 100;
//        return result_employer;
//    }
//
//    public Double Employee(Double amount, int employee) {
//
//        // 2500x40 / 100  หรือ 2500× 0 .40
//        Double result_employee = amount * employee / 100;
//        return result_employee;
//    }

    public Double Employer(Double amount) {

        // 2500x60 / 100  หรือ 2500× 0 .60
        Double result_employer = amount * 60 / 100;
        return result_employer;
    }

    public Double Employee(Double amount) {

        // 2500x40 / 100  หรือ 2500× 0 .40
        Double result_employee = amount * 40 / 100;
        return result_employee;
    }
}
