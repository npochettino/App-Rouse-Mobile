package sempait.rouss.Services.volley.Manager;

import android.content.Context;
import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import sempait.rouss.Base.BaseActivity;
import sempait.rouss.Utils.DialogCatalog;

/**
 * Created by martin on 16/04/15.
 */
public class CreateAcountTask extends AsyncTask<Void, Void, String> {


    private String NAMESPACE = "http://sempait.com.ar/";
    private String URL = "http://rouss.sempait.com.ar/ServicioWeb.asmx";
    private String METHOD_NAME = "InsertarActualizarUsuario";
    private String SOAP_ACTION = "http://sempait.com.ar/InsertarActualizarUsuario";
    public String mCodigoUsuario;
    public String mNombre;
    public String mApellido;
    public String mEmail;
    public String mPassword;
    public String mTelefono;
    public String mDni;
    private Context mContext;


    public CreateAcountTask(Context ctx) {
        mContext = ctx;
    }

    @Override
    protected String doInBackground(Void... params) {


        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


        request.addProperty("codigoUsuario", mCodigoUsuario);
        request.addProperty("nombre", mNombre);
        request.addProperty("apellido", mApellido);
        request.addProperty("dni", mDni);
        request.addProperty("mail", mEmail);
        request.addProperty("contraseña", mPassword);
        request.addProperty("telefono", mTelefono);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);


        try {
            httpTransportSE.call(SOAP_ACTION, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();


            return result.toString();

        } catch (Exception e) {


        }
        return null;


    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        ((BaseActivity) mContext).dismissLoadingView();

        if (result != null)

            ((BaseActivity) mContext).onBackPressed();

        else

            DialogCatalog.mensajeError("No pudo crearse la cuenta, intentelo nuevamente en unos minutos ", mContext);


    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        ((BaseActivity) mContext).showLoadingView();
    }
}
