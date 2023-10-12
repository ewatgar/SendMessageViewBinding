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
        binding = ActivitySendMessageBinding.inflate(getLayoutInflater());

        //Se ha sustituido el "setContentView(R.layout.activity_send_message);" , ya que no hace
        // falta hacer referencia directa al XML porque ya tenemos el binding
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
        binding=null; //liberar en memoria tod_o lo que se ha creado en la aplicación
    }
    //endregion

    public void sendMessage() {
        Intent intent = new Intent(this, ViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("user", "El usuario Eustaquio te manda el siguiente mensaje:");
        Person persone = new Person("Jose Luis", "Rodrigez Morón", "1");
        Person persond = new Person("Lourdes", "Benítez", "2");
        Message message = new Message(1, binding.edMessage.getText().toString(), persone, persond);
        bundle.putParcelable(Message.KEY, message);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * OPCIÓN 1: Se crea una clase que implementa la interfaz View.OnClickListener
     */
    class SendMessageOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(SendMessageActivity.this, "Se ha pulsado sobre el botón enviar", Toast.LENGTH_LONG);
        }
    }
}