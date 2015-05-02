package sempait.rouss.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import sempait.rouss.Base.BaseActivity;
import sempait.rouss.Drawer.NavigationMenuDrawerFragment;
import sempait.rouss.R;


public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private FrameLayout mLeftDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationMenuDrawerFragment mNavigationMenuDrawerFragment;
    private Boolean firstRun = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpActionBarsAndDrawers();
    }

    private void setUpActionBarsAndDrawers() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mLeftDrawer = (FrameLayout) findViewById(R.id.drawer_left);


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };


        mNavigationMenuDrawerFragment = new NavigationMenuDrawerFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.drawer_left, mNavigationMenuDrawerFragment).commit();

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setTitle("Ruleta");

    }


    @Override
    protected void onResume() {

        super.onResume();


        mNavigationMenuDrawerFragment.setUp(R.id.drawer_left, mDrawerLayout);

        if (firstRun) {
            mNavigationMenuDrawerFragment.openSection(mNavigationMenuDrawerFragment.mSelectedSection);
            firstRun = false;
        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                if (mDrawerLayout.isDrawerOpen(mLeftDrawer)) {
                    mDrawerLayout.closeDrawer(mLeftDrawer);
                } else {
                    mDrawerLayout.openDrawer(mLeftDrawer);

                }
                return true;


            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onBackPressed() {


        new AlertDialog.Builder(this).setMessage("Desea salir de la aplicación?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {


                                finish();

                            }

                        }


                ).

                setNegativeButton("No",

                        new DialogInterface.OnClickListener()

                        {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }

                ).

                show();
    }
}
