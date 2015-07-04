package sempait.rouss.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sempait.rouss.Base.BaseActivity;
import sempait.rouss.Drawer.NavigationMenuDrawerFragment;
import sempait.rouss.R;
import sempait.rouss.Services.volley.Manager.CreateAcountTask;
import sempait.rouss.Utils.ConfigurationClass;
import sempait.rouss.Utils.DialogCatalog;
import sempait.rouss.Utils.RoussUtils;

/**
 * Created by martin on 13/04/15.
 */
public class CreateAcountFragment extends BaseFragment {

    private View mView;
    private EditText mNombreEditText;
    private EditText mApellidoEditText;
    private EditText mEmailEditText;
    private EditText mEmailConfirmEditText;
    private EditText mPasswordEditText;
    private EditText mPasswordConfirmEditText;
    private EditText mTelefonoEditText;
    private EditText mDniEditText;
    private TextView mBtnCrearCuenta;
    private RelativeLayout mBtnCancelar;
    private CreateAcountTask mCreateTask;
    private NavigationMenuDrawerFragment mInstace;
    private CheckBox mCheckboxEdad;


    public static CreateAcountFragment newInstance(NavigationMenuDrawerFragment mInstanceDrawer) {

        return new CreateAcountFragment(mInstanceDrawer);
    }


    public CreateAcountFragment() {


    }

    @SuppressLint("ValidFragment")
    public CreateAcountFragment(NavigationMenuDrawerFragment mInstanceDrawer) {

        mInstace = mInstanceDrawer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = (ViewGroup) inflater.inflate(R.layout.fragment_create_account, container, false);


        mNombreEditText = (EditText) mView.findViewById(R.id.et_nombre);
        mApellidoEditText = (EditText) mView.findViewById(R.id.et_apellido);
        mEmailEditText = (EditText) mView.findViewById(R.id.et_email);
        mEmailConfirmEditText = (EditText) mView.findViewById(R.id.et_email_confirm);
        mBtnCrearCuenta = (TextView) mView.findViewById(R.id.btn_crear_cuenta);


        if (ConfigurationClass.getUserNameCompleted(mContext) != null) {
            loadDataUser();
        }


        return mView;
    }

    private void loadDataUser() {

        mNombreEditText.setText(ConfigurationClass.getUserFirstNameUser(mContext));
        mApellidoEditText.setText(ConfigurationClass.getUserLastNameUserUser(mContext));
        mEmailEditText.setText(ConfigurationClass.getEmalUser(mContext));
        mEmailConfirmEditText.setText(ConfigurationClass.getEmalUser(mContext));


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mBtnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (allFildCompleted()) {

                    if (RoussUtils.validEmail(mEmailEditText.getText().toString()))

                        if (mEmailEditText.getText().toString().equals(mEmailConfirmEditText.getText().toString()))

                            ((BaseActivity) mContext).replaceInnerFragmentWhitFLip(new CreateAccount2Fragment(mNombreEditText.getText().toString(), mApellidoEditText.getText().toString(), mEmailEditText.getText().toString(),mInstace), true);

                        else
                            DialogCatalog.mensajeError("Los correos deben coincidir", mContext);

                    else
                        DialogCatalog.mensajeError("Ingrese un correo válido, por favor", mContext);


                } else
                    DialogCatalog.mensajeError("Debe completar todos los datos del formulario", mContext);


            }
        });


    }


    private boolean allFildCompleted() {

        if (mNombreEditText.getText().toString().isEmpty()
                || mApellidoEditText.getText().toString().isEmpty()
                || mEmailEditText.getText().toString().isEmpty()
                || mEmailConfirmEditText.getText().toString().isEmpty()) {


            return false;

        } else
            return true;


    }


    public void setDataUser() {


        ConfigurationClass.setUserNameCompleted(mContext, mApellidoEditText.getText() + " " + mNombreEditText.getText());
        ConfigurationClass.setFirstNameUser(mContext, mNombreEditText.getText().toString());
        ConfigurationClass.setUserLastNameUser(mContext, mApellidoEditText.getText().toString());
        ConfigurationClass.setEmailUser(mContext, mEmailEditText.getText().toString());
        ConfigurationClass.setTelUser(mContext, mTelefonoEditText.getText().toString());
        ConfigurationClass.setDNIUser(mContext, mDniEditText.getText().toString());
        ConfigurationClass.setPasswodUser(mContext, mPasswordEditText.getText().toString());

        if (mInstace != null)
            mInstace.setNameUser();


    }


}