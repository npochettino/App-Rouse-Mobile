package sempait.rouss.Activities;

import android.os.Bundle;

import sempait.rouss.Base.BaseInnerActivity;
import sempait.rouss.Fragment.ResultFragment;
import sempait.rouss.R;

/**
 * Created by martin on 04/05/15.
 */
public class JugarActivity extends BaseInnerActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceInnerFragmentFromBotton(ResultFragment.newInstance(), false);
        setTitle("Log In");
    }


}
