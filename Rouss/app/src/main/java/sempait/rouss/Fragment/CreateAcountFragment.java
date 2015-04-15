package sempait.rouss.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sempait.rouss.R;

/**
 * Created by martin on 13/04/15.
 */
public class CreateAcountFragment extends BaseFragment {

    View mView;

    public static CreateAcountFragment newInstance() {

        return new CreateAcountFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = (ViewGroup) inflater.inflate(R.layout.fragment_create_account, container, false);

//        mButtonDone = (RelativeLayout) mView.findViewById(R.id.btn_done);
//        mButtonForgotPass = (RelativeLayout) mView.findViewById(R.id.btn_forgot_pass);
//        mMainScroll = (ScrollView) mView;
//        mEmailEditText = (EditText) mView.findViewById(R.id.et_email);
//        mPasswordEditText = (EditText) mView.findViewById(R.id.et_password);

        return mView;
    }
}
