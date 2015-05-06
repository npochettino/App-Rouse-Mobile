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
        mPasswordEditText = (EditText) mView.findViewById(R.id.et_password);
        mPasswordConfirmEditText = (EditText) mView.findViewById(R.id.et_password_confirm);
        mTelefonoEditText = (EditText) mView.findViewById(R.id.et_telefono);
        mDniEditText = (EditText) mView.findViewById(R.id.et_dni);
        mBtnCrearCuenta = (TextView) mView.findViewById(R.id.btn_crear_cuenta);
        mBtnCancelar = (RelativeLayout) mView.findViewById(R.id.btn_relative_cancelar);
        mCheckboxEdad = (CheckBox) mView.findViewById(R.id.cb_mayor18);


        if (ConfigurationClass.getUserNameCompleted(mContext) != null) {
            loadDataUser();
            mBtnCrearCuenta.setText("Modificar");
            mCheckboxEdad.setVisibility(View.GONE);
        }


        return mView;
    }

    private void loadDataUser() {

        mNombreEditText.setText(ConfigurationClass.getUserFirstNameUser(mContext));
        mApellidoEditText.setText(ConfigurationClass.getUserLastNameUserUser(mContext));
        mEmailEditText.setText(ConfigurationClass.getEmalUser(mContext));
        mPasswordEditText.setText(ConfigurationClass.getPasswordUser(mContext));
        mPasswordConfirmEditText.setText(ConfigurationClass.getPasswordUser(mContext));
        mTelefonoEditText.setText(ConfigurationClass.getTelUser(mContext));
        mDniEditText.setText(ConfigurationClass.getDNIUser(mContext));


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mBtnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) mContext).onBackPressed();
            }
        });


        mBtnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (allFildCompleted()) {

                    if (mCheckboxEdad.isChecked()) {

                        if (mPasswordEditText.getText().toString().equals(mPasswordConfirmEditText.getText().toString()))

                            if (RoussUtils.validEmail(mEmailEditText.getText().toString()))

                                executeServiceAccount();

                            else
                                DialogCatalog.mensajeError("Ingrese un correo válido, por favor", mContext);

                        else
                            DialogCatalog.mensajeError("Los campos de contraseña deben coincidir", mContext);


                    } else
                        DialogCatalog.mensajeError("Debes ser mayor de 18 años para participar", mContext);

                } else
                    DialogCatalog.mensajeError("Debe completar todos los datos del formulario", mContext);


            }
        });


    }

    private void executeServiceAccount() {
        mCreateTask = new CreateAcountTask(mContext) {


            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);


                switch (Integer.valueOf(result)) {


                    case 1:
                        if (ConfigurationClass.getUserNameCompleted(mContext) != null) {
                            setDataUser();
                            DialogCatalog.mensajeError("Los datos fueron modificados con éxito", mContext);
                        } else {
                            setDataUser();
                            DialogCatalog.mensajeError("La cuenta fue creada con éxito", mContext);
                            ((BaseActivity) mContext).onBackPressed();
                        }
                        break;

                    case 2:
                        DialogCatalog.mensajeError("El DNI fue ya está en uso", mContext);
                        break;

                    case 3:
                        DialogCatalog.mensajeError("El email ya esta en uso", mContext);
                        break;


                }


            }


        };

        if (ConfigurationClass.getUserNameCompleted(mContext) != null)
            mCreateTask.mCodigoUsuario = ConfigurationClass.getUserCod(mContext);
        else
            mCreateTask.mCodigoUsuario = "0";
        mCreateTask.mApellido = mApellidoEditText.getText().toString();
        mCreateTask.mNombre = mNombreEditText.getText().toString();
        mCreateTask.mEmail = mEmailEditText.getText().toString();
        mCreateTask.mTelefono = mTelefonoEditText.getText().toString();
        mCreateTask.mPassword = mPasswordEditText.getText().toString();
        mCreateTask.mDni = mDniEditText.getText().toString();

        mCreateTask.execute();

    }

    private boolean allFildCompleted() {

        if (mNombreEditText.getText().toString().isEmpty()
                || mApellidoEditText.getText().toString().isEmpty()
                || mEmailEditText.getText().toString().isEmpty()
                || mPasswordEditText.getText().toString().isEmpty()
                || mPasswordConfirmEditText.getText().toString().isEmpty()
                || mTelefonoEditText.getText().toString().isEmpty()
                || mDniEditText.getText().toString().isEmpty()) {


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