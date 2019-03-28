package apps_dohomeapp.dohome.co.th.sqlserver;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Search_Menu extends AppCompatActivity {

    EditText ed_Employee_Code, ed_Full_name, ed_Nick_name, ed_tel, ed_Position, ed_Department;
    Button btn_Search;
    String sGetCode, sGetFullName, sGetNickname, sGetTel, sGetPosition, sGetDepartment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_menu);

        ed_Employee_Code = findViewById(R.id.ed_Employee_Code);
        ed_Full_name = findViewById(R.id.ed_Full_name);
        ed_Nick_name = findViewById(R.id.ed_Nick_name);
        ed_tel = findViewById(R.id.ed_tel);
        ed_Position = findViewById(R.id.ed_Position);
        ed_Department = findViewById(R.id.ed_Department);

        btn_Search = findViewById(R.id.btn_Search);


        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sGetCode = ed_Employee_Code.getText().toString().trim();
                sGetFullName = ed_Full_name.getText().toString().trim();
                sGetNickname = ed_Nick_name.getText().toString().trim();
                sGetTel = ed_tel.getText().toString().trim();
                sGetPosition = ed_Position.getText().toString().trim();
                sGetDepartment = ed_Department.getText().toString().trim();

                if (sGetCode.equals("") && sGetFullName.equals("") && sGetNickname.equals("") && sGetTel.equals("") && sGetPosition.equals("") && sGetDepartment.equals("")) {

                    new AlertDialog.Builder(Search_Menu.this)
                            .setTitle("เตือน !")
                            .setMessage("กรอกข้อมูลก่อนจ้าสักอัน")
                            .setIcon(R.drawable.ic_warning)
                            .setCancelable(false)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }else {

                    Intent intent = new Intent(Search_Menu.this, List_Search.class);
                    intent.putExtra("sGetCode", sGetCode);
                    intent.putExtra("sGetFullName", sGetFullName);
                    intent.putExtra("sGetNickname", sGetNickname);
                    intent.putExtra("sGetTel", sGetTel);
                    intent.putExtra("sGetPosition", sGetPosition);
                    intent.putExtra("sGetDepartment", sGetDepartment);

                    startActivity(intent);
                }

            }
        });
    }
}
