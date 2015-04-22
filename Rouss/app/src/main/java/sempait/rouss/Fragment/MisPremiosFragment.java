package sempait.rouss.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sempait.rouss.R;

/**
 * Created by martin on 17/04/15.
 */
public class MisPremiosFragment extends BaseFragment {

    private View mView;


    public static MisPremiosFragment newInstance() {

        return new MisPremiosFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_create_account, container, false);


        //Aca pido el servicio y en el on post del servicio seteo el adaptaer


        return mView;
    }






}
