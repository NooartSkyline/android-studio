package apps_dohomeapp.dohome.co.th.firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference myRef;
    private TextView txt_jao,txt_ake,txt_flim,txt_nut,txt_art;
    private Button btn_Send;
    private EditText ed_Chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_jao = findViewById(R.id.txt_jao);
        txt_ake = findViewById(R.id.txt_ake);
        txt_flim = findViewById(R.id.txt_flim);
        txt_nut = findViewById(R.id.txt_nut);
        txt_art = findViewById(R.id.txt_art);
        ed_Chat = findViewById(R.id.ed_Chat);
        btn_Send = findViewById(R.id.btn_Send);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
//อ่าน
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map map = (Map)dataSnapshot.getValue();
                String sAKE = String.valueOf(map.get("AKE"));
                String sART = String.valueOf(map.get("ART"));
                String sFLIM = String.valueOf(map.get("FLIM"));
                String sJAO = String.valueOf(map.get("JAO"));
                String sNUT = String.valueOf(map.get("NUT"));

                txt_ake.setText("คุณเอก :"+sAKE);
                txt_art.setText("คุณอาร์ต :"+sART);
                txt_flim.setText("คุณฟิม :"+sFLIM);
                txt_jao.setText("คุณเจา :"+sJAO);
                txt_nut.setText("คุณณัฐ :"+sNUT);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_Send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //เขียน
                Map<String, Object> value = new HashMap<>();
                value.put("ART",ed_Chat.getText().toString());
                myRef.updateChildren(value);
                ed_Chat.setText("");
            }
        });
    }
}
