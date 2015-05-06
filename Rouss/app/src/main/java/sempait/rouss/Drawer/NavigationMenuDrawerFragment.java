package sempait.rouss.Drawer;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import sempait.rouss.Activities.LoginActivity;
import sempait.rouss.Base.BaseActivity;
import sempait.rouss.Fragment.CreateAcountFragment;
import sempait.rouss.Fragment.RouletteFragment;
import sempait.rouss.R;
import sempait.rouss.Services.volley.Manager.SorteoTask;
import sempait.rouss.Utils.ConfigurationClass;
import sempait.rouss.Utils.NavigationMenuItem;

/**
 * Created by martin on 28/03/15.
 */
public class NavigationMenuDrawerFragment extends BaseDrawerFragment {

    private View mView;

    private LinearLayout mProfileItem;
    private NavigationMenuItem mJugarEditText;
    private NavigationMenuItem mMisPremiosEditText;
    private NavigationMenuItem mAcercaDeItem;
    private Button mLogout;
    private SorteoTask mSorteTask;
    private Switch mSwitchSonido;
    private TextView mUserNameTextView;


    public Section mSelectedSection = Section.JUGAR;

    public static enum Section {

        JUGAR, MI_PERFIL
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);


        mJugarEditText = (NavigationMenuItem) mView.findViewById(R.id.invited_events_item);
        mMisPremiosEditText = (NavigationMenuItem) mView.findViewById(R.id.all_events_item);
        mLogout = (Button) mView.findViewById(R.id.btnLogout);
        mSwitchSonido = (Switch) mView.findViewById(R.id.switchSonido);
        mUserNameTextView = (TextView) mView.findViewById(R.id.user_name);

        if (ConfigurationClass.getSonidoState(mContext))
            mSwitchSonido.setChecked(true);
        else
            mSwitchSonido.setChecked(false);


        setNameUser();


        return mView;
    }

    public void setNameUser() {

        if (ConfigurationClass.getUserNameCompleted(mContext) != null) {
            mLogout.setVisibility(View.VISIBLE);
            ((TextView) mView.findViewById(R.id.user_name)).setText(ConfigurationClass.getUserNameCompleted(mContext));
        } else
            mLogout.setVisibility(View.GONE);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


        mJugarEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                executeSorteoService();

            }
        });

        mMisPremiosEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSection(Section.MI_PERFIL);
            }
        });


        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ConfigurationClass.setUserNameCompleted(mContext, null);

                SharedPreferences prefs = ConfigurationClass.sharedPref(mContext);
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.commit();


                mContext.startActivity(new Intent(mContext, LoginActivity.class));


                getActivity().finish();

            }
        });


        mSwitchSonido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ConfigurationClass.getSonidoState(mContext))

                    ConfigurationClass.setStateSonido(false, mContext);
                else
                    ConfigurationClass.setStateSonido(true, mContext);


            }
        });

        mUserNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSection(Section.MI_PERFIL);
            }
        });

    }

    public void openSection(Section section) {

        mSelectedSection = section;
        String mSection = "";

        if (mDrawerLayout != null)
            mDrawerLayout.closeDrawers();

        Fragment fragment = null;
        Bundle bundle = new Bundle();

        switch (section) {

            case JUGAR:
                fragment = RouletteFragment.newInstance();
                mSection = "Ruleta";
                ((BaseActivity) mContext).replaceInnerFragmentWhitFLip(fragment, false);
                break;

            case MI_PERFIL:
                fragment = CreateAcountFragment.newInstance(this);
                mSection = "Mi perfil";
                ((BaseActivity) mContext).replaceInnerFragment(fragment, false);
                break;
            default:
                break;
        }


        ((BaseActivity) mContext).getActionBar().setDisplayHomeAsUpEnabled(true);
        ((BaseActivity) mContext).getActionBar().setHomeButtonEnabled(true);
        ((BaseActivity) mContext).getActionBar().setTitle(mSection);

        refreshMenuVisual();


    }

    private void replaceFragmentSection(Fragment fragment) {

        FragmentTransaction transaction = getBaseActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    private void refreshMenuVisual() {

        mJugarEditText.setStatusSelected(false);
        mMisPremiosEditText.setStatusSelected(false);


        switch (mSelectedSection) {

            case JUGAR:
                mJugarEditText.setStatusSelected(true);
                break;

            case MI_PERFIL:
                mMisPremiosEditText.setStatusSelected(true);
                break;


            default:
                break;
        }
    }

    public void executeSorteoService() {


        mSorteTask = new SorteoTask(mContext) {


            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                openSection(Section.JUGAR);
            }
        };
        mSorteTask.mCodigoUsuario = ConfigurationClass.getUserCod(mContext);
        mSorteTask.mCodigoSorteo = ConfigurationClass.getCodigoSorteo(mContext);
        mSorteTask.execute();


    }
}
