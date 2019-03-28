package apps_dohomeapp.dohome.co.th.sqlserver;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import apps_dohomeapp.dohome.co.th.sqlserver.Adapter.Adapter_User;
import apps_dohomeapp.dohome.co.th.sqlserver.MODEL._Model_User;

public class List_Search extends AppCompatActivity {
    RecyclerView rv_list;
    String sGetCode, sGetFullName, sGetNickname, sGetTel, sGetPosition, sGetDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__search);

        rv_list = findViewById(R.id.rv_list);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            sGetCode = bundle.getString("sGetCode");
            sGetFullName = bundle.getString("sGetFullName");
            sGetNickname = bundle.getString("sGetNickname");
            sGetTel = bundle.getString("sGetTel");
            sGetPosition = bundle.getString("sGetPosition");
            sGetDepartment = bundle.getString("sGetDepartment");


        }

        GetUser106(sGetCode,sGetFullName,sGetNickname,sGetTel,sGetDepartment,sGetPosition);
    }

    public ArrayList<_Model_User> GetUser106(String EmployeeId, String Fullname, String Nickname, String Telephone, String Department, String Position) {

        ArrayList<_Model_User> models = new ArrayList<>(); //เหมือน data set

        try {
            Statement stm = Connection().createStatement();
            ResultSet rs = stm.executeQuery("select a.EmployeeId,a.Fullname,a.Nickname ,a.Telephone,a.Email " +
                    ",a.JobKeyId ,b.MYNAME as 'JOB_NAME'" +
                    ",a.PositionId ,c.MYNAME as 'POSOTION_NAME'" +
                    ",a.DepartmentId, d.NAME as 'DEPARTMENT_NAME'" +
                    ",a.PersonnelAreaId ,e.MYNAME as 'BRANCH_NAME' ,e.ADDRESS as 'ADDRESS_DOHOME'" +

                    " from [DBAUTHOR]..[Employees] a" +
                    " left join [DBMASTER]..[TBMaster_User_Job_Key] b on a.JobKeyId = b.CODE " +
                    " left join [DBMASTER]..[TBMaster_User_Position] c on a.PositionId = c.CODE " +
                    " left join [DBMASTER]..[TBMaster_Department_Info] d on a.DepartmentId = d.CODE " +
                    " left join [DBMASTER]..[TBMaster_Branch] e on a.PersonnelAreaId = e.OLD_CODE " +

                    " where a.EmployeeId like '%"+ EmployeeId +"%'" +
                    " and a.Fullname     like '%"+ Fullname +"%' " +
                    " and a.Nickname     like '%"+ Nickname +"%' " +
                    " and a.Telephone    like '%"+ Telephone +"%' " +
                    " and b.MYNAME       like '%"+ Department +"%' " +
                    " and d.NAME         like '%"+ Position +"%' ");

            while (rs.next()) {
                _Model_User model = new _Model_User();//เหมือน data Table

                model.EmployeeId = rs.getString("EmployeeId");
                model.Fullname = rs.getString("Fullname");
                model.Nickname = rs.getString("Nickname");
                model.Telephone = rs.getString("Telephone");
                model.Email = rs.getString("Email");
                model.JobKeyId = rs.getString("JobKeyId");
                model.JOB_NAME = rs.getString("JOB_NAME");
                model.PositionId = rs.getString("PositionId");
                model.POSOTION_NAME = rs.getString("POSOTION_NAME");
                model.DepartmentId = rs.getString("DepartmentId");
                model.DEPARTMENT_NAME = rs.getString("DEPARTMENT_NAME");
                model.PersonnelAreaId = rs.getString("PersonnelAreaId");
                model.BRANCH_NAME = rs.getString("BRANCH_NAME");
                model.ADDRESS_DOHOME = rs.getString("ADDRESS_DOHOME");

                models.add(model);
            }

            if (models != null) {

                Adapter_User adapter_user = new Adapter_User(models, List_Search.this);
                rv_list.setLayoutManager(new LinearLayoutManager(List_Search.this));
                rv_list.setAdapter(adapter_user);
            }
            rs.close();
            Connection().close();


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return models;
    }

    public Connection Connection() {
        Connection cnn = null;
        try {
            StrictMode.ThreadPolicy politica = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(politica);

            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            cnn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.0.106;databaseName=UWPOS;user=abc;password=abc;");

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return cnn;
    }
}
