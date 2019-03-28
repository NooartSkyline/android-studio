package apps_dohomeapp.dohome.co.th.ping_server;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    public String s1, s2, s3, s4, s5, s6, s7, s8, sreturn;
    public TextView tv_status_12, tv_status_29, tv_status_104, tv_status_105,tv_status_106 ,tv_status_157, tv_status_159, tv_status_234;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_status_12 = findViewById(R.id.tv_status_12);
        tv_status_29 = findViewById(R.id.tv_status_29);
        tv_status_104 = findViewById(R.id.tv_status_104);
        tv_status_105 = findViewById(R.id.tv_status_105);
        tv_status_106 = findViewById(R.id.tv_status_106);
        tv_status_157 = findViewById(R.id.tv_status_157);
        tv_status_159 = findViewById(R.id.tv_status_159);
        tv_status_234 = findViewById(R.id.tv_status_234);
        swipeContainer = findViewById(R.id.swipeContainer);

        new Ping12().execute();
        new Ping29().execute();
        new Ping104().execute();
        new Ping105().execute();
        new Ping106().execute();
        new Ping157().execute();
        new Ping159().execute();
        new Ping234().execute();


        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tv_status_12.setText("DOWN");
                tv_status_12.setTextColor(getResources().getColor(R.color.colorRed));
                tv_status_29.setText("DOWN");
                tv_status_29.setTextColor(getResources().getColor(R.color.colorRed));
                tv_status_104.setText("DOWN");
                tv_status_104.setTextColor(getResources().getColor(R.color.colorRed));
                tv_status_105.setText("DOWN");
                tv_status_105.setTextColor(getResources().getColor(R.color.colorRed));
                tv_status_106.setText("DOWN");
                tv_status_106.setTextColor(getResources().getColor(R.color.colorRed));
                tv_status_157.setText("DOWN");
                tv_status_157.setTextColor(getResources().getColor(R.color.colorRed));
                tv_status_159.setText("DOWN");
                tv_status_159.setTextColor(getResources().getColor(R.color.colorRed));
                tv_status_234.setText("DOWN");
                tv_status_234.setTextColor(getResources().getColor(R.color.colorRed));

                new Ping12().execute();
                new Ping29().execute();
                new Ping104().execute();
                new Ping105().execute();
                new Ping106().execute();
                new Ping157().execute();
                new Ping159().execute();
                new Ping234().execute();
            }

        });
    }

    class Ping12 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light);
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            if (str != null) {
                tv_status_12.setText("DOWN");
                tv_status_12.setTextColor(getResources().getColor(R.color.colorRed));
                if (s1 != null) {
                    sreturn = s1;
                    Log.i("msg", "192.168.0.12 :" + sreturn);
                    tv_status_12.setText("UP");
                    tv_status_12.setTextColor(getResources().getColor(R.color.colorPrimary));

                    swipeContainer.setRefreshing(false);


//                    new Ping12().execute();
                }
            } else {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("เตือน!")
                        .setMessage("เกิดข้อผิดพลาดขณะโหลดข้อมูล")
                        .setIcon(R.drawable.ic_warning_)
                        .setCancelable(false)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }

        }

        @Override
        protected String doInBackground(String... params) {

            try {
                Process p12 = Runtime.getRuntime().exec("ping 192.168.0.12");
                BufferedReader inputStream12 = new BufferedReader(new InputStreamReader(p12.getInputStream()));
                s1 = inputStream12.readLine();
                return s1;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class Ping29 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light);
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            if (str != null) {

                tv_status_29.setText("DOWN");
                tv_status_29.setTextColor(getResources().getColor(R.color.colorRed));

                if (s2 != null) {
                    sreturn = s2;
                    Log.i("msg", "192.168.0.29 :" + sreturn);
                    tv_status_29.setText("UP");
                    tv_status_29.setTextColor(getResources().getColor(R.color.colorPrimary));

                    swipeContainer.setRefreshing(false);
//                    new Ping29().execute();
                }
            } else {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("เตือน!")
                        .setMessage("เกิดข้อผิดพลาดขณะโหลดข้อมูล")
                        .setIcon(R.drawable.ic_warning_)
                        .setCancelable(false)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }

        }

        @Override
        protected String doInBackground(String... params) {

            try {

                Process p29 = Runtime.getRuntime().exec("ping 192.168.0.29");
                BufferedReader inputStream29 = new BufferedReader(new InputStreamReader(p29.getInputStream()));
                s2 = inputStream29.readLine();
                return s2;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class Ping104 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light);
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            if (str != null) {
                tv_status_104.setText("DOWN");
                tv_status_104.setTextColor(getResources().getColor(R.color.colorRed));
                if (s3 != null) {
                    sreturn = s3;
                    Log.i("msg", "192.168.0.104 :" + sreturn);
                    tv_status_104.setText("UP");
                    tv_status_104.setTextColor(getResources().getColor(R.color.colorPrimary));

                    swipeContainer.setRefreshing(false);
//                    new Ping104().execute();
                }
            } else {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("เตือน!")
                        .setMessage("เกิดข้อผิดพลาดขณะโหลดข้อมูล")
                        .setIcon(R.drawable.ic_warning_)
                        .setCancelable(false)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }

        }

        @Override
        protected String doInBackground(String... params) {

            try {
                Process p104 = Runtime.getRuntime().exec("ping 192.168.0.104");
                BufferedReader inputStream104 = new BufferedReader(new InputStreamReader(p104.getInputStream()));
                s3 = inputStream104.readLine();
                return s3;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class Ping105 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light);
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            if (str != null) {
                tv_status_105.setText("DOWN");
                tv_status_105.setTextColor(getResources().getColor(R.color.colorRed));
                if (s4 != null) {
                    sreturn = s4;
                    Log.i("msg", "192.168.0.105 :" + sreturn);
                    tv_status_105.setText("UP");
                    tv_status_105.setTextColor(getResources().getColor(R.color.colorPrimary));

                    swipeContainer.setRefreshing(false);
//                    new Ping105().execute();
                }
            } else {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("เตือน!")
                        .setMessage("เกิดข้อผิดพลาดขณะโหลดข้อมูล")
                        .setIcon(R.drawable.ic_warning_)
                        .setCancelable(false)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }

        }

        @Override
        protected String doInBackground(String... params) {

            try {
                Process p105 = Runtime.getRuntime().exec("ping 192.168.0.105");
                BufferedReader inputStream105 = new BufferedReader(new InputStreamReader(p105.getInputStream()));
                s4 = inputStream105.readLine();
                return s4;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class Ping106 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light);
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            if (str != null) {
                tv_status_106.setText("DOWN");
                tv_status_106.setTextColor(getResources().getColor(R.color.colorRed));
                if (s8 != null) {
                    sreturn = s8;
                    Log.i("msg", "192.168.0.106 :" + sreturn);
                    tv_status_106.setText("UP");
                    tv_status_106.setTextColor(getResources().getColor(R.color.colorPrimary));

                    swipeContainer.setRefreshing(false);
//                    new Ping171().execute();
                }
            } else {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("เตือน!")
                        .setMessage("เกิดข้อผิดพลาดขณะโหลดข้อมูล")
                        .setIcon(R.drawable.ic_warning_)
                        .setCancelable(false)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }

        }

        @Override
        protected String doInBackground(String... params) {

            try {
                Process p106 = Runtime.getRuntime().exec("ping 192.168.0.106");
                BufferedReader inputStream106 = new BufferedReader(new InputStreamReader(p106.getInputStream()));
                s8 = inputStream106.readLine();
                return s8;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class Ping157 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light);
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            if (str != null) {
                tv_status_157.setText("DOWN");
                tv_status_157.setTextColor(getResources().getColor(R.color.colorRed));
                if (s5 != null) {
                    sreturn = s5;
                    Log.i("msg", "192.168.0.157 :" + sreturn);
                    tv_status_157.setText("UP");
                    tv_status_157.setTextColor(getResources().getColor(R.color.colorPrimary));

                    swipeContainer.setRefreshing(false);
//                    new Ping157().execute();
                }
            } else {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("เตือน!")
                        .setMessage("เกิดข้อผิดพลาดขณะโหลดข้อมูล")
                        .setIcon(R.drawable.ic_warning_)
                        .setCancelable(false)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }

        }

        @Override
        protected String doInBackground(String... params) {

            try {
                Process p157 = Runtime.getRuntime().exec("ping 192.168.0.157");
                BufferedReader inputStream157 = new BufferedReader(new InputStreamReader(p157.getInputStream()));
                s5 = inputStream157.readLine();
                return s5;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class Ping159 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light);
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            if (str != null) {
                tv_status_159.setText("DOWN");
                tv_status_159.setTextColor(getResources().getColor(R.color.colorRed));
                if (s6 != null) {
                    sreturn = s6;
                    Log.i("msg", "192.168.0.159 :" + sreturn);
                    tv_status_159.setText("UP");
                    tv_status_159.setTextColor(getResources().getColor(R.color.colorPrimary));

                    swipeContainer.setRefreshing(false);
//                    new Ping159().execute();
                }
            } else {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("เตือน!")
                        .setMessage("เกิดข้อผิดพลาดขณะโหลดข้อมูล")
                        .setIcon(R.drawable.ic_warning_)
                        .setCancelable(false)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }

        }

        @Override
        protected String doInBackground(String... params) {

            try {
                Process p159 = Runtime.getRuntime().exec("ping 192.168.0.159");
                BufferedReader inputStream159 = new BufferedReader(new InputStreamReader(p159.getInputStream()));
                s6 = inputStream159.readLine();
                return s6;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class Ping234 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light);
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            if (str != null) {
                tv_status_234.setText("DOWN");
                tv_status_234.setTextColor(getResources().getColor(R.color.colorRed));
                if (s7 != null) {
                    sreturn = s7;
                    Log.i("msg", "192.168.0.234 :" + sreturn);
                    tv_status_234.setText("UP");
                    tv_status_234.setTextColor(getResources().getColor(R.color.colorPrimary));

                    swipeContainer.setRefreshing(false);
//                    new Ping234().execute();
                }
            } else {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("เตือน!")
                        .setMessage("เกิดข้อผิดพลาดขณะโหลดข้อมูล")
                        .setIcon(R.drawable.ic_warning_)
                        .setCancelable(false)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }

        }

        @Override
        protected String doInBackground(String... params) {

            try {
                Process p234 = Runtime.getRuntime().exec("ping 192.168.0.234");
                BufferedReader inputStream234 = new BufferedReader(new InputStreamReader(p234.getInputStream()));
                s7 = inputStream234.readLine();
                return s7;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

