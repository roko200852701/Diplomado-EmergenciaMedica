package taller.molroj.emergencia.ampaciente.Utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import taller.molroj.emergencia.ampaciente.Presentacion.Chat_Medico;
import taller.molroj.emergencia.ampaciente.R;

//import taller.molroj.emergencia.amparamedico.Presentacion.Ubicar;
//import taller.molroj.emergencia.amparamedico.R;


public class Notificacion {
	
	private Context context;
	private String nombre;
	private int codigo;
	private NotificationManager notificationManager;
    private String MensajeCorto;
	
	public void setContext(Context context) {
		this.context = context;
	}
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre(){
        return nombre;
    }
    public void setMensajeCorto(String mensajeCorto) {
        this.MensajeCorto = mensajeCorto;
    }
    public String getMensajeCorto(){
        return MensajeCorto;
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
                        .setContentTitle("Mensaje de "+ getNombre())
                        .setContentText(getMensajeCorto())//nombre del pasiente
                        .setTicker(getNombre())//mensaje corto
                        .setAutoCancel(true)
                                //.setVibrate(new long[]{ 0, 100, 200, 300 })
                        .setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.ambulancia));


        Intent resultIntent = new Intent(context, Chat_Medico.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(Chat_Medico.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //notificationManager.notify(Integer.parseInt(Estatica.cordenadaPaciente.getMatricula()), mBuilder.build());
        notificationManager.notify(0, mBuilder.build());

    }
    public void notificarNuevoChat() {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_notificacionemergencia)
                        .setContentTitle("Mensaje de "+ getNombre())
                        .setContentText(getMensajeCorto())//nombre del pasiente
                        .setTicker(getNombre())//mensaje corto
                        .setAutoCancel(true)
                                //.setVibrate(new long[]{ 0, 100, 200, 300 })
                        .setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.ambulancia));


        Intent resultIntent = new Intent(context, Chat_Medico.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(Chat_Medico.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //notificationManager.notify(Integer.parseInt(Estatica.cordenadaPaciente.getMatricula()), mBuilder.build());
        notificationManager.notify(0, mBuilder.build());

    }
}
