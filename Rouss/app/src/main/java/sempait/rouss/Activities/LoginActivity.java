package sempait.rouss.Activities;

import android.os.Bundle;

import sempait.rouss.Base.BaseInnerActivity;
import sempait.rouss.Fragment.LoginFragment;
import sempait.rouss.R;

/**
 * Created by martin on 29/03/15.
 */
public class LoginActivity extends BaseInnerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, LoginFragment.newInstance()).commit();
        setTitle("Log In");
    }
}
