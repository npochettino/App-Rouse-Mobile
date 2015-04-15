package sempait.rouss.Services.volley.Manager;

import android.content.Context;
import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import sempait.rouss.Base.BaseActivity;

/**
 * Created by martin on 05/04/15.
 */
public class LoginTask extends AsyncTask<Void, Void, String> {

    private String NAMESPACE = "http://sempait.com.ar/";
    private String URL = "http://rouss.sempait.com.ar/ServicioWeb.asmx";
    private String METHOD_NAME = "LoginUsuario";
    private String SOAP_ACTION = "http://sempait.com.ar/LoginUsuario";
    public String mEmail;
    public String mPassword;
    private Context mContext;


    public LoginTask(Context ctx) {
        mContext = ctx;
    }

    @Override
    protected String doInBackground(Void... params) {

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        request.addProperty("mail", mEmail);
        request.addProperty("contraseña", mPassword);

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


    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        ((BaseActivity) mContext).showLoadingView();
    }


}
