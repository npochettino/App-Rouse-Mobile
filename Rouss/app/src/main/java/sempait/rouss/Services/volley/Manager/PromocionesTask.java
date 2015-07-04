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
 * Created by martin on 27/06/15.
 */
public class PromocionesTask extends AsyncTask<Void, Void, String> {

    private String NAMESPACE = "http://sempait.com.ar/";
    private String URL = "http://rouss.sempait.com.ar/ServicioWeb.asmx";
    private String METHOD_NAME = "RecuperarImagenPublicidadVigente";
    private String SOAP_ACTION = "http://sempait.com.ar/RecuperarImagenPublicidadVigente";
    private Context mContext;


    public PromocionesTask(Context ctx) {
        mContext = ctx;
    }


    @Override
    protected String doInBackground(Void... params) {

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);


        try {
            httpTransportSE.call(SOAP_ACTION, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();


            return result.toString();

        } catch (Exception e) {

            String lala = e.getMessage();

        }
        return null;

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        ((BaseActivity) mContext).dismissLoadingView();
    }

    ;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        ((BaseActivity) mContext).showLoadingView();
    }
}