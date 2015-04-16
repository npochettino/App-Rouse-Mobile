package sempait.rouss.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import sempait.rouss.Base.BaseActivity;
import sempait.rouss.R;
import sempait.rouss.Services.volley.Manager.CreateAcountTask;
import sempait.rouss.Utils.DialogCatalog;

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
    private RelativeLayout mBtnCrearCuenta;
    private RelativeLayout mBtnCancelar;
    private CreateAcountTask mCreateTask;


    public static CreateAcountFragment newInstance() {

        return new CreateAcountFragment();
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
        mBtnCrearCuenta = (RelativeLayout) mView.findViewById(R.id.btn_crear_cuenta);
        mBtnCancelar = (RelativeLayout) mView.findViewById(R.id.btn_relative_cancelar);

        return mView;
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

                    if (mPasswordEditText.getText().toString().equals(mPasswordConfirmEditText.getText().toString()))
                        executeServiceAccount();

                    else
                        DialogCatalog.mensajeError("Los campos de contraseña deben coincidir", mContext);


                } else
                    DialogCatalog.mensajeError("Debe completar todos los datos del formulario", mContext);

            }
        });


    }

    private void executeServiceAccount() {
        mCreateTask = new CreateAcountTask(mContext);

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

}