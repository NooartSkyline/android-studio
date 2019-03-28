package th.co.dohome.apps_dohomeapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import th.co.dohome.apps_dohomeapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_detail extends Fragment {


    private TextView tv_displaygeneral_display_h;

    public Fragment_detail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_detail, container, false);

        tv_displaygeneral_display_h = getActivity().findViewById(R.id.tv_displaygeneral_display_h);
        tv_displaygeneral_display_h.setText("Detail");

        return view;
    }

}
