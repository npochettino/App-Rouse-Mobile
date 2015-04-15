package sempait.rouss.Fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import sempait.rouss.Base.BaseActivity;

/**
 * Created by martin on 19/03/15.
 */
public class BaseFragment extends Fragment {

    public Context mContext;
    public BaseActivity mBaseActivity;

    @Override
    public void onAttach(Activity activity) {
        mContext = activity;
        mBaseActivity = (BaseActivity) activity;
        super.onAttach(activity);
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    public boolean isActive() {
        return !getActivity().isFinishing();
    }

    public void dismissLoadingView() {

        if (mBaseActivity != null)
            mBaseActivity.dismissLoadingView();
    }

    public void showLoadingView() {

        if (mBaseActivity != null)
            mBaseActivity.showLoadingView();
    }

    public void onBackPressed() {

        if (mBaseActivity != null)
            mBaseActivity.onBackPressed();
    }
}
