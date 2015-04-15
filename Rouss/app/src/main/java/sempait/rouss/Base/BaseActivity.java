package sempait.rouss.Base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import sempait.rouss.R;


/**
 * Created by martin on 18/03/15.
 */
public class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle bundle) {

        super.onCreate(bundle);

        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

//        TextView title = (TextView) findViewById(getResources().getIdentifier("action_bar_title", "id", "android"));
//        if (title != null)
//            title.setTypeface(Typeface.createFromAsset(getAssets(), "HelveticaNeueLTStd-Md.otf"));
    }

    public void showLoadingView() {
        View loadingView = findViewById(R.id.loadingView);
        if (loadingView != null)
            loadingView.setVisibility(View.VISIBLE);
    }

    public void dismissLoadingView() {

        final View loadingView = findViewById(R.id.loadingView);
        if (loadingView != null)
            if (loadingView.getVisibility() != View.GONE) {
                Animation fadeInAnimation = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
                fadeInAnimation.setStartOffset(200);
                fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        if (loadingView != null)
                            loadingView.setVisibility(View.GONE);
                    }
                });

                if (loadingView != null)
                    loadingView.startAnimation(fadeInAnimation);
            }
    }

    @Override
    public void onBackPressed() {

        backWithAnimation();
    }

    protected void backWithAnimation() {

        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish();
            this.overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }


    public void replaceInnerFragment(Fragment fragment) {
        replaceInnerFragment(fragment, true);
    }

    public void replaceInnerFragment(Fragment fragment, Boolean addToBackstack) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out, R.anim.push_right_out, R.anim.push_right_in);
        transaction.replace(R.id.container, fragment);

        if (addToBackstack)
            transaction.addToBackStack(null);
        transaction.commit();
    }


    public void replaceInnerFragmentFromBotton(Fragment fragment, Boolean addToBackstack) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fast_push_bottom_in, R.anim.fast_push_top_out, R.anim.fast_push_bottom_in, R.anim.fast_push_top_out);
        transaction.replace(R.id.container, fragment);

        if (addToBackstack)
            transaction.addToBackStack(null);
        transaction.commit();
    }


    public void addInnerFragment(Fragment fragment) {

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out, R.anim.push_right_out, R.anim.push_right_in)
                .add(R.id.container, fragment).addToBackStack(null).commit();
    }

    public void setSectionTitle(String title) {

        getActionBar().setTitle(title);
    }

}