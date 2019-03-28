package cpe.kku.pong.csuav_app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SendMedFragment extends Fragment {


    ProgressDialog progressDialog;
    Button button1,button2;
    EditText editText1,editText2;
    Spinner spinner;
    ArrayList<HashMap<String, String>> mylist;
    ListView listView1;
    public SendMedFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_send_med, container, false);
        // Inflate the layout for this fragment
        button1 = (Button) v.findViewById(R.id.button1);
        button2 = (Button) v.findViewById(R.id.button2);
        editText1 = (EditText) v.findViewById(R.id.editText1);
        editText2 = (EditText) v.findViewById(R.id.editText2);
        spinner = (Spinner) v.findViewById(R.id.spinner);
        listView1 = (ListView) v.findViewById(R.id.listView1);
        mylist = new ArrayList<HashMap<String, String>>();
        new SigninActivityDrug().execute(GBValue.id_mem);
        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("คำเตือน");
                builder.setMessage("การส่งคำร้องขอ ระบบจะดึงพิกัด ณ ปัจจุบันที่คุณอยู่ กรุณาอยู่ในที่โลงแจ้งไม่สิ่งได้มาบดบังและกีดขวาง การลงจอดของเครื่องนำส่งเวชภัณฑ์");
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String medicine = "";
                        int sizeitem = mylist.size();

                        if (sizeitem > 0) {
                            for (HashMap<String, String> menuItem : mylist) {
                                String iditem = menuItem.get("id");
                                String item1 = menuItem.get("datacol1");
                                String item2 = menuItem.get("datacol2");
                                medicine = medicine + item1 + " (" + item2 + "),";
                            }
                            medicine = medicine.substring(0, medicine.length() - 1);
                        }
                        String detailrec = String.valueOf(editText1.getText());
                        //Toast.makeText(getActivity(), medicine, Toast.LENGTH_SHORT).show();
                        double latitude_uav = 0.000000000;
                        double longitude_uav = 0.000000000;
                        GPSTracker gps = new GPSTracker(getActivity());
                        if (gps.canGetLocation()) {

                            latitude_uav = gps.getLatitude();
                            longitude_uav = gps.getLongitude();
                        }

                        if(latitude_uav == 0.000000000 && longitude_uav == 0.000000000)
                            msbox("ผลการทำงาน","GPS ไม่พร้อมทำงานกรุณารอและลองส่งอีกครั้ง");
                        else {
                            new SigninActivity().execute(GBValue.id_mem, medicine, detailrec, String.valueOf(latitude_uav), String.valueOf(longitude_uav));
                        }
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //TODO
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();



            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String stritem = String.valueOf(spinner.getSelectedItem());
                String numitem = editText2.getText().toString();
                if (!numitem.equals("")) {
                    int sizeitem = mylist.size();
                    HashMap<String, String> map = new HashMap<String, String>();

                    map.put("id", String.valueOf(sizeitem));
                    map.put("datacol1", stritem);
                    map.put("datacol2", numitem);
                    mylist.add(map);
                    listView1.setAdapter(null);
                    ListAdapter adapter = new SimpleAdapter(
                            getActivity(), mylist,
                            R.layout.medicinedetail, new String[]{"datacol1", "datacol2",}, new int[]{R.id.datacol1, R.id.datacol2});

                    listView1.setAdapter(adapter);
                    registerForContextMenu(listView1);
                    msbox("ผลการทำงาน","นำเข้า '" + stritem + "' ในรายการ");
                } else {
                    Toast.makeText(getActivity(), "เลือกจำนวนเป็นตัวเลข", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

    class SigninActivity extends AsyncTask<String,Void,String> {

        protected void onPreExecute(){
            progressDialog = ProgressDialog.show(getActivity(), "Please wait ...", "กำลังค้นหา GPS เพื่อความแม่นยำสูงและอัพเดทข้อมูล", true);
            progressDialog.setCancelable(true);
        }

        @Override
        protected String doInBackground(String... arg0) {
            String id_mem = (String) arg0[0];
            String medicine = (String) arg0[1];
            String detailrec = (String) arg0[2];
            String latitude_uav = (String) arg0[3];
            String longitude_uav = (String) arg0[4];
            // Get GPS

            String getresult = WriteDataDev(id_mem, medicine, detailrec,latitude_uav,longitude_uav);
            return getresult;
        }

        @Override
        protected void onPostExecute(String getresult) {
            progressDialog.dismiss();
            if(!getresult.equals("0") && !getresult.equals("-2")) {
                msbox("โปรดทราบ...", "ข้อมูลร้องขอถูกส่งแล้ว");
                editText1.setText("");
                hisfragment fragment = new hisfragment();
                if (fragment != null) {
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.flContent, fragment);
                    ft.commit();
                }
                mylist.clear();
            }
            else
            {
                msbox("โปรดทราบ...", "ไม่สามารถส่งข้อมูลร้องขอได้");
            }
            Toast.makeText(getActivity(), "สถานะการทำงาน " + getresult, Toast.LENGTH_SHORT).show();
        }
    }

    class SigninActivityDrug extends AsyncTask<String,Void,JSONArray> {

        protected void onPreExecute(){
            progressDialog = ProgressDialog.show(getActivity(), "Please wait ...", "อัพเดทข้อมูล", true);
            progressDialog.setCancelable(true);
        }

        @Override
        protected JSONArray doInBackground(String... arg0) {
            String id_mem = (String)arg0[0];
            JSONArray getresult = WriteDataDrug(id_mem);
            return getresult;
        }

        @Override
        protected void onPostExecute(JSONArray getresult) {
            progressDialog.dismiss();
            if(getresult != null) {
                try {
                    ArrayList<String> spinnerdata1 = new ArrayList<String>();
                    for (int i = 0; i < getresult.length(); i++) {
                        JSONObject json_data = getresult.getJSONObject(i);
                        String drug = json_data.getString("name_drug");
                        spinnerdata1.add(drug);
                    }
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerdata1);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter1);
                }
                catch (JSONException ex) {
                }
            }
            Toast.makeText(getActivity(), "สถานะการทำงาน ดึงข้อมูลเสร็จสิ้น" , Toast.LENGTH_SHORT).show();
        }
    }

    String WriteDataDev(String val1, String val2,String val3,String val4,String val5)
    {
        try {

            String USER_AGENT = "Mozilla/5.0";
            String url = GBValue.hostname + "addman_app.php?id_mem=" + val1
                    + "&medicine=" + val2 + "&detailrec=" + val3 + "&latw=" + val4 + "&lonw=" + val5;
            url = url.replaceAll(" ", "%20");

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
    JSONArray WriteDataDrug(String val1)
    {
        try {

            String USER_AGENT = "Mozilla/5.0";
            String url = GBValue.hostname + "get_medicine.php?id_mem="+val1;

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

            JSONObject json = new JSONObject(response.toString());
            JSONArray jArray = json.getJSONArray("drugtag");

            return jArray;


        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.listView1) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle("คุณต้องการลบรายการหรือไม่");
            String[] menuItems = getResources().getStringArray(R.array.menu);
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.menu);
        String menuItemName = menuItems[menuItemIndex];
        if(menuItemName.equals("Delete")) {
            int index = info.position;
            mylist.remove(index);

            listView1.setAdapter(null);
            ListAdapter adapter = new SimpleAdapter(
                    getActivity(), mylist,
                    R.layout.medicinedetail, new String[]{"datacol1", "datacol2",}, new int[]{R.id.datacol1, R.id.datacol2});

            listView1.setAdapter(adapter);
        }
        //text.setText(String.format("Selected %s for item %s", menuItemName, listItemName));
        return true;
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
