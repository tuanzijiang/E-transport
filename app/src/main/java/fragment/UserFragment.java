package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell2.e_transport.R;

/**
 * Created by dell2 on 2017/5/20.
 */

public class UserFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view=inflater.inflate(R.layout.user,container,false);
        return view;
    }
}
