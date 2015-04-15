package sempait.rouss.Services.volley.Manager;

import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by martin on 05/04/15.
 */
public class BaseAsyncTask extends AsyncTask<Void, Void, String> {

    private String NAMESPACE = "http://tempuri.org/";
    private String URL = "http://rouss.sempait.com.ar/ServicioWeb.asmx";
    private String METHOD_NAME = "LoginUsuario";
    private String SOAP_ACTION = "http://tempuri.org/LoginUsuario";

    @Override
    protected String doInBackground(Void... params) {

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        request.addProperty("mail", "admin@sempait.com.ar");
        request.addProperty("contraseña", "admin");

        SoapSerializationEnvelope envelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(URL);


        try {
            transporte.call(SOAP_ACTION, envelope);
            SoapPrimitive resultado_xml = (SoapPrimitive) envelope.getResponse();

            return resultado_xml.toString();

        } catch (Exception e) {

        }
        return null;
    }


}
