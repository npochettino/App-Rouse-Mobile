package sempait.rouss.Base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;

/**
 * Created by martin on 28/03/15.
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

//        if (mBaseActivity != null)
////            mBaseActivity.dismissLoadingView();
    }

    public void showLoadingView() {

//        if (mBaseActivity != null)
////            mBaseActivity.showLoadingView();
    }

    public void onBackPressed() {

        if (mBaseActivity != null)
            mBaseActivity.onBackPressed();
    }
}
