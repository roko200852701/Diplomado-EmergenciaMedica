package taller.molroj.emergencia.amparamedico.Utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import taller.molroj.emergencia.amparamedico.Presentacion.Ubicar;
import taller.molroj.emergencia.amparamedico.R;


public class Notificacion {
	
	private Context context;
	private String nombre;
	private int codigo;
	private NotificationManager notificationManager;
	
	public void setContext(Context context) {
		this.context = context;
	}
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

    public NotificationManager getNotificationManager() {
        return notificationManager;
    }

    public Notificacion(Context context){
		this.context=context;
        notificationManager =(NotificationManager)this.context.getSystemService(Context.NOTIFICATION_SERVICE);
	}




	public void notificar() {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_notificacionemergencia)
                        .setContentTitle(Estatica.cordenadaPaciente.getNombre())
                        .setContentText(context.getString(R.string.tipoSangre) + Estatica.cordenadaPaciente.getTipoSangre())
                        .setTicker(context.getString(R.string.nuevaSolicitudEmergencia))
                        .setAutoCancel(true)
                                //.setVibrate(new long[]{ 0, 100, 200, 300 })
                        .setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.ambulancia));


        Intent resultIntent = new Intent(context, Ubicar.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(Ubicar.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(Integer.parseInt(Estatica.cordenadaPaciente.getMatricula()), mBuilder.build());
    }
}
