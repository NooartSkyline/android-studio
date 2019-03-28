package th.co.dohome.apps_dohomeapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import th.co.dohome.apps_dohomeapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_substitute extends Fragment {

    public Fragment_substitute() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_substitute, container, false);

        return view;
    }

}
