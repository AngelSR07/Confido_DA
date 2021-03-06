package com.grupo3.confido.util.backgroundService;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.media.VolumeProviderCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.grupo3.confido.util.sendMessage.Contact;
import com.grupo3.confido.util.sendMessage.RxJava;

public class Service_Message extends Service {

    //Atributos
    private MediaSessionCompat mediaSessionCompat;
    private int cont;
    private BroadcastReceiver myBroadcast;
    private IntentFilter filter;
    private RxJava rxJava;

    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;


    //Manejamos todos los componentes al inicio una vez cargada la clase
    @Override
    public void onCreate() {
        Toast.makeText(this,"El servicio \"Confido\" ha sido creado", Toast.LENGTH_SHORT).show();

        mediaSessionCompat = new MediaSessionCompat(this,"Service_Message");

        mediaSessionCompat.setPlaybackState(new PlaybackStateCompat.Builder().setState(PlaybackStateCompat.STATE_PLAYING,0,0).build());

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(0);
        locationRequest.setFastestInterval(0);

        rxJava = new RxJava();
        rxJava.addContext(this);

        rxJava.addFused(fusedLocationClient);
        rxJava.addLocation(locationRequest);

        listContact();
    }




    //Permite iniciar el servicio en segundo plano
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this,"Servicio inciado " + startId, Toast.LENGTH_SHORT).show();

        filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);

        myBroadcast = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //Si la pantalla se encuentra suspendida o apagada
                if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
                    VolumeProviderCompat myVolumenProvider = new VolumeProviderCompat(VolumeProviderCompat.VOLUME_CONTROL_RELATIVE,50,50) {
                        @Override
                        public void onAdjustVolume(int direction) {
                            //Si presiona el boton "Volumen +"
                            if(direction == 1){
                                cont++;

                                if(cont == 3){
                                    rxJava.startEvent();//Iniciamos el proceso asincrono
                                    cont = 0;
                                }
                            }
                        }
                    };

                    mediaSessionCompat.setPlaybackToRemote(myVolumenProvider);
                    mediaSessionCompat.setActive(true);

                } else if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
                    Toast.makeText(Service_Message.this,"Se prendio la pantalla en 2do plano :D", Toast.LENGTH_LONG).show();
                    mediaSessionCompat.release();
                    cont = 0;
                }

            }
        };

        registerReceiver(myBroadcast,filter);

        return START_STICKY;//Bandera que indica que el servicio se est?? ejecuntado (1).
    }



    //Metodo para a??adir los contactos a notificar
    private void listContact(){

        Contact c1 = new Contact("Angel",918764904);
        /*Contact c2 = new Contact("Gaby",22222);
        Contact c3 = new Contact("Brenda",33333);
        Contact c4 = new Contact("Tracy",44444);
        Contact c5 = new Contact("Mery",55555);*/

        rxJava.addContacts(c1);
        /*rxJava.addContacts(c2);
        rxJava.addContacts(c3);
        rxJava.addContacts(c4);
        rxJava.addContacts(c5);*/
    }




    //Permite detener el servicio en 2do plano
    @Override
    public void onDestroy() {

        Toast.makeText(this,"Servicio detenido", Toast.LENGTH_SHORT).show();

        //rxJava.destroyDisposable();
        rxJava = null;//Limpiamos la lista de contactos

        unregisterReceiver(myBroadcast);
        mediaSessionCompat.release();

        super.onDestroy();
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
