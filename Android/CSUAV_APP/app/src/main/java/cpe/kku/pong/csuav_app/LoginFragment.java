package cpe.kku.pong.csuav_app;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    ProgressDialog progressDialog;
    Button button1,button2;
    EditText editText1,editText2;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        // Inflate the layout for this fragment
       button1 = (Button)v.findViewById(R.id.button1);
        button2 = (Button)v.findViewById(R.id.button2);
        editText1 = (EditText)v.findViewById(R.id.editText1);
        editText2 = (EditText)v.findViewById(R.id.editText2);
        editText1.setText(GBValue.username);

        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(((MainActivity) getActivity()).CheckEnableGPS()) {
                    if (((MainActivity) getActivity()).isNetworkConnected()) {
                        String username = String.valueOf(editText1.getText());
                        String password = String.valueOf(editText2.getText());
                        new SigninActivity().execute(username, password);
                    } else {
                        msbox("คำเตือน", "แอพพลิเคชั่นต้องการใช้ระบบเครือข่ายอินเตอร์เน็ต กรุณาเชื่อมเครือข่ายอินเตอร์เน็ตก่อนการใช้งาน");
                    }
                }
                else
                {
                    msbox("คำเตือน", "แอพพลิเคชั่นต้องการใช้งาน GSP กรุณาเปิด GPS ก่อนการใช้งาน");
                }

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                getActivity().finish();
                System.exit(0);
            }
        });
        return v;
    }

    class SigninActivity extends AsyncTask<String,Void,String> {

        protected void onPreExecute(){
            progressDialog = ProgressDialog.show(getActivity(), "Please wait ...", "อัพเดทข้อมูล", true);
            progressDialog.setCancelable(true);
        }

        @Override
        protected String doInBackground(String... arg0) {
            String username = (String)arg0[0];
            String password = (String)arg0[1];
            String getresult = WriteDataDev(username, password);
            return getresult;
        }

        @Override
        protected void onPostExecute(String getresult) {
            progressDialog.dismiss();
            if(getresult.isEmpty())
            {
                msbox("ผลการเข้าระบบ", "เชื่อมต่อระบบไม่ได้");
            }
            else if(getresult.equals("0") || getresult.equals("-2") || getresult.equals("-1")) {
                msbox("ผลการเข้าระบบ", "รหัสผู้ใช้และรหัสผ่านไม่ถูกต้อง");
            }
            else
            {

                String[] myArray = getresult.split(",");
                GBValue.id_mem = myArray[0];
                GBValue.name = myArray[1];
                GBValue.username = editText1.getText().toString();
                ((MainActivity)getActivity()).SaveUserLogin();
                Toast.makeText(getActivity(), "ยินดีต้อนรับคุณ " + GBValue.name, Toast.LENGTH_SHORT).show();

                hisfragment fragment = new hisfragment();
                if (fragment != null) {
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.flContent, fragment);
                    ft.commit();
                }
            }
            Toast.makeText(getActivity(), "สถานะการทำงาน " + getresult, Toast.LENGTH_SHORT).show();
        }
    }

    String WriteDataDev(String val1, String val2)
    {
        try {

            String USER_AGENT = "Mozilla/5.0";
            String url = GBValue.hostname + "login_app.php?username="+val1+"&password="+val2;

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();

        } catch (Exception e) {
            return "-2";
        }
    }
    private void msbox(String str,String str2)
    {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(getActivity());
        dlgAlert.setTitle(str);
        dlgAlert.setMessage(str2);
        dlgAlert.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                return;
            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

}
