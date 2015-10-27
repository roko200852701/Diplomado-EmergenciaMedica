package taller.molroj.emergencia.ampaciente.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.widget.Toast;

import taller.molroj.emergencia.ampaciente.R;


public class MyLocationListener implements LocationListener, Runnable {


    private LocationManager locationManager = null;
    private ProgressDialog pd;
    private Context context;
    private Activity activity;
    private Thread thread;


    public MyLocationListener(Context context) {
        this.context = context;
        this.activity = (Activity) context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public void setContext(Context context) {
        this.context = context;
        this.activity = (Activity) context;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            Estatica.cordenada = new Cordenada(Estatica.me.getMatricula(), Estatica.me.getNombre(), "R0H+", location.getLatitude(), location.getLongitude());
            Estatica.interprete.interpretar(Instruccion.enviarCordenadaPaciente);
            handler.sendEmptyMessage(0);
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
        if (pd.isShowing() && !locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            handler.sendEmptyMessage(0);
        }

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method
    }


    public boolean verificarEstadoGPS() {
        Estatica.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (!Estatica.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(context, R.string.gpsNecesario, Toast.LENGTH_SHORT).show();
            activity.startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), Constantes.codeGPS);
            return false;
        }
        return true;
    }


    public void buscandoSenalGPS() {

        DialogInterface.OnCancelListener dialogCancel = new DialogInterface.OnCancelListener() {

            public void onCancel(DialogInterface dialog) {
                Toast.makeText(context,
                        R.string.noGPS,
                        Toast.LENGTH_LONG).show();
                handler.sendEmptyMessage(0);


            }

        };

        pd = ProgressDialog.show(context, context.getResources().getString(R.string.sBuscando), context.getResources().getString(R.string.sBuscarSenal), true, false, dialogCancel);

        thread = new Thread(this);
        thread.start();

    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            pd.dismiss();
            locationManager.removeUpdates(Estatica.myLocationListener);
            handler.removeCallbacksAndMessages(thread);

        }
    };

    @Override
    public void run() {

        if (Estatica.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            Looper.prepare();

            Estatica.locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 0, 0, Estatica.myLocationListener);
            Looper.loop();
            Looper.myLooper().quit();

        } else {
            Toast.makeText(activity.getApplicationContext(),
                    activity.getString(R.string.noGPS),
                    Toast.LENGTH_LONG).show();

        }
    }

    public void onPause() {
        if (locationManager != null) {
            locationManager.removeUpdates(Estatica.myLocationListener);
        }
    }


}
