package sempait.rouss.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import sempait.rouss.R;
import sempait.rouss.Services.volley.Manager.ForgotPasswordTask;

/**
 * Created by martin on 25/04/15.
 */
public class ForgotPasswordFragemnt extends BaseFragment {


    private ViewGroup mView;
    private RelativeLayout mButtonDone;
    private EditText mEmailEditText;
    private Context context;
    private ForgotPasswordTask mForgotPasswordTask;


    public static ForgotPasswordFragemnt newInstance() {

        return new ForgotPasswordFragemnt();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = (ViewGroup) inflater.inflate(R.layout.fragment_forgot_password, container, false);

        mButtonDone = (RelativeLayout) mView.findViewById(R.id.btn_done);
        mEmailEditText = (EditText) mView.findViewById(R.id.et_email);
        context = mContext;

        return mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mButtonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeForgotPasswordService();
            }
        });

    }

    private void executeForgotPasswordService() {

        mForgotPasswordTask = new ForgotPasswordTask(mContext);
        mForgotPasswordTask.mEmail = mEmailEditText.getText().toString();

        mForgotPasswordTask.execute();


    }
}
