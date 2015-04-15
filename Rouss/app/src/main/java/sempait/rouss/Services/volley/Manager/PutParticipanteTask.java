package sempait.rouss.Services.volley.Manager;

import android.content.Context;
import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by martin on 09/04/15.
 */
public class PutParticipanteTask extends AsyncTask<Void, Void, String> {

    private String NAMESPACE = "http://sempait.com.ar/";
    private String URL = "http://rouss.sempait.com.ar/ServicioWeb.asmx";
    private String METHOD_NAME = "InsertarParticipante";
    private String SOAP_ACTION = "http://sempait.com.ar/InsertarParticipante";
    public String mCodigoUsuario;
    public String mCodigoSorteo;
    public String mCodigoPremio;


    private Context mContext;


    public PutParticipanteTask(Context ctx) {
        mContext = ctx;
    }

    @Override
    protected String doInBackground(Void... params) {
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        request.addProperty("codigoUsuario", mCodigoUsuario);
        request.addProperty("codigoSorteo", mCodigoSorteo);
        request.addProperty("codigoPremio", mCodigoPremio);


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
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //((BaseActivity) mContext).dismissLoadingView();

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //((BaseActivity) mContext).showLoadingView();
    }
}
