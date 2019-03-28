package cpe.kku.pong.csuav_app;


import android.app.ProgressDialog;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListDataDron extends Fragment {


    ProgressDialog progressDialog;
    ListView listView1;
    ArrayList<HashMap<String, String>> mylist;
    Button button1;

    public ListDataDron() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_data_dron, container, false);
        listView1 = (ListView) v.findViewById(R.id.listView1);
        button1 = (Button)v.findViewById(R.id.button1);
        mylist = new ArrayList<HashMap<String, String>>();
        new ListDataDron.SigninActivity().execute("0");
        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                new ListDataDron.SigninActivity().execute("0");

            }
        });

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
                view.setSelected(true);
                HashMap<String, String> map = mylist.get(position);
                GBValue.id_uav = map.get("id");
                DetailDataDron fragment = new DetailDataDron();
                if (fragment != null) {
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.flContent, fragment);
                    ft.commit();
                }
            }
        });

        return v;
    }

    class SigninActivity extends AsyncTask<String,Void,JSONArray> {

        protected void onPreExecute(){
            progressDialog = ProgressDialog.show(getActivity(), "Please wait ...", "อัพเดทข้อมูล", true);
            progressDialog.setCancelable(true);
        }

        @Override
        protected JSONArray doInBackground(String... arg0) {
            String id = (String)arg0[0];
            JSONArray getresult = WriteDataDev();
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
                        String id_uav = json_data.getString("id_uav");
                        String 	name = json_data.getString("name");
                        String cd_uav = json_data.getString("cd_uav");
                        String uav_work	 = json_data.getString("uav_work");


                        HashMap<String, String> map = new HashMap<String, String>();

                        map.put("id", id_uav);
                        map.put("datacol1", name);
                        map.put("datacol2", cd_uav);
                        if(uav_work.equals("0")) {
                            map.put("datacol3", "เตรียมพร้อมทำงาน");
                        }else{
                            map.put("datacol3", "กำลังบินใช้งาน");
                        }


                        mylist.add(map);

                    }
                } catch (JSONException ex) {
                }
                ListAdapter adapter = new SimpleAdapter(
                        getActivity(), mylist,
                        R.layout.listdrondetail, new String[]{"datacol1", "datacol2","datacol3"}, new int[]{R.id.datacol1, R.id.datacol2,R.id.datacol3});

                listView1.setAdapter(adapter);
            }
            else
            {
                Toast.makeText(getActivity(), "ไม่พบข้อมูลของผู้ใช้", Toast.LENGTH_SHORT).show();
            }

        }
    }

    JSONArray WriteDataDev()
    {
        try {

            String USER_AGENT = "Mozilla/5.0";
            String url = GBValue.hostname + "get_datadron.php";

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
            JSONArray jArray = json.getJSONArray("uavregtag");

            return jArray;


        } catch (Exception e) {
            return null;
        }
    }


}
