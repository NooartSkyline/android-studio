package cpe.kku.pong.csuav_app;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class hisfragment extends Fragment {


    ProgressDialog progressDialog;
    TextView textView1;
    ListView listView1;
    ArrayList<HashMap<String, String>> mylist;
    public hisfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hisfragment, container, false);
        listView1 = (ListView) v.findViewById(R.id.listView1);
        textView1 = (TextView)v.findViewById(R.id.textView1);
        textView1.setText("คำร้องของเวชภัณฑ์ " + GBValue.name);
        mylist = new ArrayList<HashMap<String, String>>();
        new hisfragment.SigninActivity().execute(GBValue.id_mem);
        return v;
    }
    class SigninActivity extends AsyncTask<String,Void,JSONArray> {

        protected void onPreExecute(){
            progressDialog = ProgressDialog.show(getActivity(), "Please wait ...", "อัพเดทข้อมูล", true);
            progressDialog.setCancelable(true);
        }

        @Override
        protected JSONArray doInBackground(String... arg0) {
            String id_mem = (String)arg0[0];
            JSONArray getresult = WriteDataDev(id_mem);
            return getresult;
        }

        @Override
        protected void onPostExecute(JSONArray getresult) {
            progressDialog.dismiss();
            if(getresult != null) {
                mylist.clear();
                try {
                    for (int i = 0; i < getresult.length(); i++) {
                        JSONObject json_data = getresult.getJSONObject(i);
                        String id_man = json_data.getString("id_man");
                        String datetimedata = json_data.getString("datetimedata");
                        String latw = json_data.getString("latw");
                        String lonw = json_data.getString("lonw");
                        String detailrec = json_data.getString("detailrec");
                        String stt = json_data.getString("stt");
                        String 	status = json_data.getString("status");

                        HashMap<String, String> map = new HashMap<String, String>();

                        map.put("id", id_man);
                        map.put("datacol1", datetimedata);
                        map.put("datacol2", latw + "," + lonw);
                        map.put("datacol3", status);
                        map.put("datacol4", detailrec);
                        map.put("datacol5", stt);

                        mylist.add(map);

                    }
                } catch (JSONException ex) {
                }
                ListAdapter adapter = new SimpleAdapter(
                        getActivity(), mylist,
                        R.layout.historydetail, new String[]{"datacol1", "datacol2","datacol3"}, new int[]{R.id.datacol1, R.id.datacol2,R.id.datacol3});

                listView1.setAdapter(adapter);
                registerForContextMenu(listView1);
            }
            else
            {
                Toast.makeText(getActivity(), "ไม่พบข้อมูลของผู้ใช้", Toast.LENGTH_SHORT).show();
            }

        }
    }

    JSONArray WriteDataDev(String val1)
    {
        try {

            String USER_AGENT = "Mozilla/5.0";
            String url = GBValue.hostname + "get_management.php?id_mem="+val1;

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
            JSONArray jArray = json.getJSONArray("management");

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
            menu.setHeaderTitle("รายละเอียด");
            String[] menuItems = getResources().getStringArray(R.array.menu1);
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.menu1);
        String menuItemName = menuItems[menuItemIndex];
        if(menuItemName.equals("ShowDetail")) {
            if(mylist.size() > -1) {
                int index = info.position;
                HashMap<String, String> map = mylist.get(index);
                String item2 = map.get("datacol4");
                AlertDialog.Builder adb = new AlertDialog.Builder(
                        getActivity());
                adb.setTitle("รายการที่ขอ");
                adb.setMessage(item2);
                adb.setPositiveButton("Ok", null);
                adb.show();
            }
        }
        else if(menuItemName.equals("ShowMap")){

            Intent itn = new Intent(getActivity(), MapsActivity.class);
            int index = info.position;
            HashMap<String, String> map = mylist.get(index);
            String id_man = map.get("id");
            String stt = map.get("datacol5");
            if(stt.equals("2") || stt.equals("3") || stt.equals("4") || stt.equals("5")) {
                itn.putExtra(MapsActivity.keyid_man, id_man);
                startActivity(itn);
            }
            else
            {
                Toast.makeText(getActivity(), "ระบบยังไม่อยู่ในการบิน", Toast.LENGTH_SHORT).show();
            }
        }
        //text.setText(String.format("Selected %s for item %s", menuItemName, listItemName));
        return true;
    }

}
