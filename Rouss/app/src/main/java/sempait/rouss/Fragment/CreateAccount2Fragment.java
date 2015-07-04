package sempait.rouss.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import sempait.rouss.Activities.LoginActivity;
import sempait.rouss.Base.BaseActivity;
import sempait.rouss.Drawer.NavigationMenuDrawerFragment;
import sempait.rouss.R;
import sempait.rouss.Services.volley.Manager.CreateAcountTask;
import sempait.rouss.Utils.ConfigurationClass;
import sempait.rouss.Utils.DialogCatalog;

/**
 * Created by martin on 07/05/15.
 */
@SuppressLint("ValidFragment")
public class CreateAccount2Fragment extends BaseFragment {

    private EditText mPasswordEditText;
    private EditText mPasswordConfirmEditText;
    private EditText mTelefonoEditText;
    private EditText mDniEditText;
    private String mNombre;
    private String mEmail;
    private String mApellido;
    private TextView mBtnCrearCuenta;
    private CreateAcountTask mCreateTask;
    private NavigationMenuDrawerFragment mInstace;
    private CheckBox mCheckboxEdad;
    private View mView;
    private NavigationMenuDrawerFragment mInstance;


    public CreateAccount2Fragment(String nombre, String apellido, String email, NavigationMenuDrawerFragment mInstace) {
        super();
        this.mNombre = nombre;
        this.mApellido = apellido;
        this.mEmail = email;
        this.mInstace = mInstace;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = (ViewGroup) inflater.inflate(R.layout.fragment_create_account_2, container, false);

        mPasswordEditText = (EditText) mView.findViewById(R.id.et_password);
        mPasswordConfirmEditText = (EditText) mView.findViewById(R.id.et_password_confirm);
        mTelefonoEditText = (EditText) mView.findViewById(R.id.et_telefono);
        mDniEditText = (EditText) mView.findViewById(R.id.et_dni);
        mBtnCrearCuenta = (TextView) mView.findViewById(R.id.btn_crear_cuenta);
        mCheckboxEdad = (CheckBox) mView.findViewById(R.id.cb_mayor18);


        if (ConfigurationClass.getUserNameCompleted(mContext) != null) {
            loadDataUser();
            mBtnCrearCuenta.setText("Modificar");
            mCheckboxEdad.isChecked();
            mCheckboxEdad.setVisibility(View.GONE);
        }


        return mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mBtnCrearCuenta.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {

                                                   if (allFildCompleted()) {

                                                       if (mCheckboxEdad.isChecked() || mCheckboxEdad.getVisibility() == View.GONE) {

                                                           if (mPasswordEditText.getText().toString().equals(mPasswordConfirmEditText.getText().toString()))


                                                               executeServiceAccount();


                                                           else
                                                               DialogCatalog.mensajeError("Los campos de contraseña deben coincidir", mContext);


                                                       } else
                                                           DialogCatalog.mensajeError("Debes ser mayor de 18 años para participar", mContext);

                                                   } else
                                                       DialogCatalog.mensajeError("Debe completar todos los datos del formulario", mContext);


                                               }
                                           }

        );


    }

    private boolean allFildCompleted() {

        if (mPasswordEditText.getText().toString().isEmpty()
                || mPasswordConfirmEditText.getText().toString().isEmpty()
                || mTelefonoEditText.getText().toString().isEmpty()
                || mDniEditText.getText().toString().isEmpty()) {


            return false;

        } else
            return true;


    }

    private void executeServiceAccount() {
        mCreateTask = new CreateAcountTask(mContext) {


            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);


                if (result != null) {
                    switch (Integer.valueOf(result)) {


                        case 1:
                            if (ConfigurationClass.getUserNameCompleted(mContext) != null) {
                                setDataUser();
                                DialogCatalog.mensajeError("Los datos fueron modificados con éxito", mContext);
                            } else {
                                setDataUser();
                                DialogCatalog.mensajeError("La cuenta fue creada con éxito", mContext);
                                ((BaseActivity) mContext).startActivity(new Intent(((BaseActivity) mContext), LoginActivity.class));
                                ((BaseActivity) mContext).finish();
                            }
                            break;

                        case 2:
                            DialogCatalog.mensajeError("El DNI fue ya está en uso", mContext);
                            break;

                        case 3:
                            DialogCatalog.mensajeError("El email ya esta en uso", mContext);
                            break;


                    }
                } else

                    DialogCatalog.mensajeError("Hubo un problema, intentelo nuevamente mas tarde", mContext);

            }


        };

        if (ConfigurationClass.getUserNameCompleted(mContext) != null)
            mCreateTask.mCodigoUsuario = ConfigurationClass.getUserCod(mContext);
        else
            mCreateTask.mCodigoUsuario = "0";
        mCreateTask.mApellido = mApellido;
        mCreateTask.mNombre = mNombre;
        mCreateTask.mEmail = mEmail;
        mCreateTask.mTelefono = mTelefonoEditText.getText().toString();
        mCreateTask.mPassword = mPasswordEditText.getText().toString();
        mCreateTask.mDni = mDniEditText.getText().toString();

        mCreateTask.execute();


    }

    public void setDataUser() {

        ConfigurationClass.setUserNameCompleted(mContext, mApellido + " " + mNombre);
        ConfigurationClass.setFirstNameUser(mContext, mNombre);
        ConfigurationClass.setUserLastNameUser(mContext, mApellido);
        ConfigurationClass.setEmailUser(mContext, mEmail);
        ConfigurationClass.setTelUser(mContext, mTelefonoEditText.getText().toString());
        ConfigurationClass.setDNIUser(mContext, mDniEditText.getText().toString());
        ConfigurationClass.setPasswodUser(mContext, mPasswordEditText.getText().toString());

        if (mInstace != null)
            mInstace.setNameUser();


    }

    private void loadDataUser() {


        mPasswordEditText.setText(ConfigurationClass.getPasswordUser(mContext));
        mPasswordConfirmEditText.setText(ConfigurationClass.getPasswordUser(mContext));
        mTelefonoEditText.setText(ConfigurationClass.getTelUser(mContext));
        mDniEditText.setText(ConfigurationClass.getDNIUser(mContext));


    }
}