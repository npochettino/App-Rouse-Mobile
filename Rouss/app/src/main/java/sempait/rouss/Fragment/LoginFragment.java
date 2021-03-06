package sempait.rouss.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import sempait.rouss.Activities.LoginActivity;
import sempait.rouss.Activities.MainActivity;
import sempait.rouss.Base.BaseActivity;
import sempait.rouss.Models.User;
import sempait.rouss.R;
import sempait.rouss.Services.volley.Manager.LoginTask;
import sempait.rouss.Services.volley.Manager.SorteoTask;
import sempait.rouss.Utils.ConfigurationClass;
import sempait.rouss.Utils.DialogCatalog;

/**
 * Created by martin on 28/03/15.
 */
public class LoginFragment extends BaseFragment {


    private ViewGroup mView;
    private RelativeLayout mButtonDone;
    private RelativeLayout mButtonForgotPass;
    private RelativeLayout mButtonCrearCuenta;
    private ScrollView mMainScroll;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private LoginTask mLoginTask;
    private SorteoTask mSorteTask;
    private Context context;

    private User mUserActual = new User();

    public static LoginFragment newInstance() {

        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);

        mButtonDone = (RelativeLayout) mView.findViewById(R.id.btn_done);
        mButtonForgotPass = (RelativeLayout) mView.findViewById(R.id.btn_forgot_pass);
        mButtonCrearCuenta = (RelativeLayout) mView.findViewById(R.id.btn_crear_cuenta);
//        mMainScroll = (ScrollView) mView;
        mEmailEditText = (EditText) mView.findViewById(R.id.et_email);
        mPasswordEditText = (EditText) mView.findViewById(R.id.et_password);
        context = mContext;

        return mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mButtonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (allFildCompleted())
                    executeLoginService();
                else
                    DialogCatalog.mensajeError("Debe completar todos los datos del formulario", mContext);
            }
        });

        mButtonCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ((BaseActivity) mContext).replaceInnerFragmentWhitFLip(CreateAcountFragment.newInstance(null), true);


            }
        });


        mButtonForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((BaseActivity) mContext).replaceInnerFragmentWhitFLip(ForgotPasswordFragemnt.newInstance(), true);

            }
        });

    }


    public void executeLoginService() {


        mLoginTask = new LoginTask(mContext) {
            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);

                if (result != null && result.length() != 2) {


                    List<User> mUsers = new Gson().fromJson(result.toString(), new TypeToken<List<User>>() {
                    }.getType());

                    mUserActual = mUsers.get(0);

                    ConfigurationClass.setUserNameCompleted(mContext, mUserActual.getApellido() + " " + mUserActual.getNombre());
                    ConfigurationClass.setFirstNameUser(mContext, mUserActual.getNombre());
                    ConfigurationClass.setUserLastNameUser(mContext, mUserActual.getApellido());
                    ConfigurationClass.setUserCod(mContext, mUserActual.getCodigoUsuario());
                    ConfigurationClass.setEmailUser(mContext, mUserActual.getMail());
                    ConfigurationClass.setTelUser(mContext, mUserActual.getTelefono());
                    ConfigurationClass.setDNIUser(mContext, mUserActual.getDni());
                    ConfigurationClass.setPasswodUser(mContext, mUserActual.getContraseña());


                    executeSorteoService();
                } else {

                    DialogCatalog.logueoIncorrecto("El usuario y/o contraseña son incorrectos", mContext);
                }


            }
        };


        mLoginTask.mEmail = mEmailEditText.getText().toString();
        mLoginTask.mPassword = mPasswordEditText.getText().toString();


        mLoginTask.execute();

    }


    public void executeSorteoService() {


        mSorteTask = new SorteoTask(mContext) {


            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                mContext.startActivity(new Intent(mContext, MainActivity.class));

                ((LoginActivity) mContext).finish();

            }
        };
        mSorteTask.mCodigoUsuario = mUserActual.getCodigoUsuario();
        mSorteTask.mCodigoSorteo = ConfigurationClass.getCodigoSorteo(mContext);
        mSorteTask.execute();
    }


    private boolean allFildCompleted() {

        if (mEmailEditText.getText().toString().isEmpty()
                || mPasswordEditText.getText().toString().isEmpty()) {


            return false;

        } else
            return true;


    }


}
