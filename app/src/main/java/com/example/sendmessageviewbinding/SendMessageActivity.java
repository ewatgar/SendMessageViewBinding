package com.example.sendmessageviewbinding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.sendmessageviewbinding.databinding.ActivitySendMessageBinding;
import com.example.sendmessageviewbinding.model.data.Message;
import com.example.sendmessageviewbinding.model.data.Person;

public class SendMessageActivity extends AppCompatActivity {

    private ActivitySendMessageBinding binding;
    public static final String TAG = "SendMessageActivity";

    //region METODOS CICLO DE VIDA
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //region VERSIÓN ANTIGUA - findViewById()
        /*
        setContentView(R.layout.activity_send_message);
        fab = findViewById(R.id.fab);
        edMessage = findViewById(R.id.edMessage);
        */
        //endregion

        //region VERSIÓN ANTIGUA - setOnClickListener fab
        /*
        OPCIÓN 1 - clase interna SendMessageOnClickListener:
        //se crea instancia de la clase creada
        onClickListener = new SendMessageOnClickListener();
        fab.setOnClickListener(onClickListener);

        OPCION 2 - clase anónima:
        //se debe especificar como SendMessageActivity.this, ya que no es parte de la clase
        //principal SendMessageActivity, y no hay visibilidad fuera de la clase anónima
        fab.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Toast.makeText(SendMessageActivity.this, "Se crea el texto en la clase anónima"+fab.getId(), Toast.LENGTH_SHORT).show();
        }

        OPCION 3 - expresión lambda:
        //forma más sencilla
        fab.setOnClickListener(view ->Toast.makeText(this, "Se crea el texto mediante una expresión lambda"+fab.getId(), Toast.LENGTH_SHORT).show());
        */
        //endregion

        binding = ActivitySendMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.fab.setOnClickListener(view -> sendMessage());

        Log.d(TAG, "MessageApplication -> onCreate()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_aboutas:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MessageApplication -> onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MessageApplication -> onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MessageApplication -> onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MessageApplication -> onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //liberar de la memoria tod.o lo que se ha creado en la aplicación
        binding=null;
    }
    //endregion

    /**
     * Método que envía un mensaje a ViewActivity a través de un Intent y un Bundle.
     * El mensaje se obtiene al escribir en el EditView edMessage. Se implementa con
     * un botón, como un fab, y se envía a la actividad ViewActivity al pulsar.
     * @author Ender Watts García
     * @version 1.0
     */
    public void sendMessage() {
        Intent intent = new Intent(this, ViewActivity.class);
        Bundle bundle = new Bundle();
        //ejemplo de mensaje
        //bundle.putString("user", "El usuario Eustaquio te manda el siguiente mensaje:");
        Person persone = new Person("Jose Luis", "Rodrigez Morón", "1");
        Person persond = new Person("Lourdes", "Benítez", "2");
        Message message = new Message(1, binding.edMessage.getText().toString(), persone, persond);

        //region VERSIÓN ANTIGUA - serializable
        //bundle.putSerializable(Message.KEY,message);
        //endregion

        //Versión parcelable
        bundle.putParcelable(Message.KEY, message);
        //IMPORTANTE: putExtras con 's' al final
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * @deprecated
     * Clase interna que implementa la interfaz View.OnClickListener.Se utiliza para dar funcionalidad
     * de pulsación a un botón, como por ejemplo fab. No se utiliza ya que se prefiere usar una expresión lambda.
     * @author Ender Watts García
     * @version 1.0
     */
    class SendMessageOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(SendMessageActivity.this, "Se ha pulsado sobre el botón enviar", Toast.LENGTH_LONG);
        }
    }
}