package sempait.rouss.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by martin on 29/03/15.
 */
public class ConfigurationClass {


    private static final String PREFS_NAME = "rouss_settings";
    private static final String USER_NAME = "user_name";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_COD = "codigo_user";
    private static final String CODIGO_SORTEO = "codigo_sorteo";
    private static final String CANTIDAD_TIROS_USUARIO = "cantidad_tiros_usuario";
    private static final String CANTIDAD_PREMIOS_USUARIO = "cantidad_premios_usuario";
    private static final String CANTIDAD_PREMIOS_TOTALES = "cantidad_premios_totales";
    private static final String CODIGO_PREMIO = "codigo_premio";


    private static SharedPreferences sharedPref(Context context) {
        return context.getSharedPreferences(PREFS_NAME, 0);
    }


    public static void setUserName(Context context, String userName) {
        sharedPref(context).edit().putString(USER_NAME, userName).commit();
    }

    public static String getUserName(Context context) {
        return sharedPref(context).getString(USER_NAME, null);
    }

    public static void setUserCod(Context context, String userCod) {
        sharedPref(context).edit().putString(USER_COD, userCod).commit();
    }

    public static String getUserCod(Context context) {
        return sharedPref(context).getString(USER_COD, null);
    }


    public static void setCodigoSorteo(Context context, String CodigoSorteo) {

        sharedPref(context).edit().putString(CODIGO_SORTEO, CodigoSorteo).commit();
    }

    public static String getCodigoSorteo(Context context) {
        return sharedPref(context).getString(CODIGO_SORTEO, null);
    }

    public static void setcantidadTirosPorUsuario(Context context, String CodigoSorteo) {

        sharedPref(context).edit().putString(CANTIDAD_TIROS_USUARIO, CodigoSorteo).commit();
    }

    public static String getcantidadTirosPorUsuario(Context context) {
        return sharedPref(context).getString(CANTIDAD_TIROS_USUARIO, null);
    }


    public static void setcantidadPremiosPorUsuario(Context context, String CodigoSorteo) {

        sharedPref(context).edit().putString(CANTIDAD_PREMIOS_USUARIO, CodigoSorteo).commit();
    }

    public static String getcantidadPremiosPorUsuario(Context context) {
        return sharedPref(context).getString(CANTIDAD_PREMIOS_USUARIO, null);
    }


    public static void setcantidadPremiosTotales(Context context, String CodigoSorteo) {

        sharedPref(context).edit().putString(CANTIDAD_PREMIOS_TOTALES, CodigoSorteo).commit();
    }

    public static String getcantidadPremiosTotales(Context context) {
        return sharedPref(context).getString(CANTIDAD_PREMIOS_TOTALES, null);
    }


    public static void setcodigoPremio(Context context, String CodigoSorteo) {

        sharedPref(context).edit().putString(CODIGO_PREMIO, CodigoSorteo).commit();
    }

    public static String getcodigoPremio(Context context) {
        return sharedPref(context).getString(CODIGO_PREMIO, null);
    }


}
