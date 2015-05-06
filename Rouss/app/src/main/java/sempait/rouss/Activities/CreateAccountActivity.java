package sempait.rouss.Activities;

import android.os.Bundle;

import sempait.rouss.Base.BaseInnerActivity;
import sempait.rouss.Fragment.CreateAcountFragment;
import sempait.rouss.R;

/**
 * Created by martin on 04/05/15.
 */
public class CreateAccountActivity extends BaseInnerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, CreateAcountFragment.newInstance(null)).commit();
        setTitle("Log In");
    }
}
