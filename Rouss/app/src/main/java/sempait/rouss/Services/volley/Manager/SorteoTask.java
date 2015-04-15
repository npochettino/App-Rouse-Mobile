package sempait.rouss.Services.volley.Manager;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.List;

import sempait.rouss.Base.BaseActivity;
import sempait.rouss.Models.Sorteo;
import sempait.rouss.Utils.ConfigurationClass;

/**
 * Created by martin on 09/04/15.
 */
public class SorteoTask extends AsyncTask<Void, Void, String> {

    private String NAMESPACE = "http://sempait.com.ar/";
    private String URL = "http://rouss.sempait.com.ar/ServicioWeb.asmx";
    private String METHOD_NAME = "RecuperarSorteoYPremio";
    private String SOAP_ACTION = "http://sempait.com.ar/RecuperarSorteoYPremio";
    public String mCodigoUsuario;
    public String mCodigoSorteo;
    private Context mContext;
    public Sorteo mSorteoActual = new Sorteo();


    public SorteoTask(Context ctx) {
        mContext = ctx;
    }


    @Override
    protected String doInBackground(Void... params) {

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        request.addProperty("codigoUsuario", Integer.parseInt(mCodigoUsuario));
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


        if (result != null) {


            List<Sorteo> mSorteos = new Gson().fromJson(result.toString(), new TypeToken<List<Sorteo>>() {
            }.getType());

            mSorteoActual = mSorteos.get(0);

            ConfigurationClass.setCodigoSorteo(mContext, mSorteoActual.codigoSorteo);
            ConfigurationClass.setcantidadTirosPorUsuario(mContext, mSorteoActual.cantidadTirosPorUsuario);
            ConfigurationClass.setcantidadPremiosPorUsuario(mContext, mSorteoActual.cantidadPremiosPorUsuario);
            ConfigurationClass.setcantidadPremiosTotales(mContext, mSorteoActual.cantidadPremiosTotales);
            ConfigurationClass.setcodigoPremio(mContext, mSorteoActual.codigoPremio);


        }
    }

    ;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        ((BaseActivity) mContext).showLoadingView();
    }
}
