package sempait.rouss.Drawer;

import android.app.Activity;
import android.app.ActionBar;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

/**
 * Created by martin on 28/03/15.
 */
public class BaseDrawerFragment extends sempait.rouss.Fragment.BaseFragment {

    protected ActionBarDrawerToggle mDrawerToggle;
    protected DrawerLayout mDrawerLayout;
    protected View mFragmentContainerView;
    protected ActionBar mActionBar;

    @Override
    public void onAttach(Activity activity) {

        mActionBar = activity.getActionBar();
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public boolean isDrawerOpen() {

        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout) {

        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        if (mDrawerLayout != null && isDrawerOpen()) {
            mActionBar.setDisplayShowTitleEnabled(true);
            mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void closeDrawer() {
        if (this.isDrawerOpen())
            mDrawerLayout.closeDrawers();
    }
}
