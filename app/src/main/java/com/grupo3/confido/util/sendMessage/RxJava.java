package com.grupo3.confido.util.sendMessage;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxJava {

    private Disposable disposable;
    private final List<Contact> contacts;


    public RxJava() {
        contacts = new ArrayList<>();
    }



    public void addContacts(Contact c){
        contacts.add(c);
    }



    public int sizeListContacts(){
        return contacts.size();
    }



    public void startEvent(){
        Observable<Contact> observable = Observable.fromIterable(contacts);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createObserver());
    }



    private Observer<Contact> createObserver(){
        return new Observer<Contact>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Contact c) {
                sendMessage(c);
            }

            @Override
            public void onError(@NonNull Throwable e) {}

            @Override
            public void onComplete() {}
        };
    }



    private void sendMessage(Contact c){
        //Implementar lógica de enviar mensaje

        Log.e("Mensaje","El mensaje se ha enviado al número: " + c.getNumContact());
        getLocation();
    }


    private void getLocation(){
        //Implementar logica de obtener ubicación

        Log.i("Ubicación","La ubicación ha sido localizada");
    }



    public void destroyDisposable(){
        disposable.dispose();
    }
}
