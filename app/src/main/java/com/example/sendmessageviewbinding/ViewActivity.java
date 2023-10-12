package com.example.sendmessageviewbinding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.sendmessageviewbinding.databinding.ActivityViewBinding;
import com.example.sendmessageviewbinding.model.data.Message;

public class ViewActivity extends AppCompatActivity {

    private ActivityViewBinding binding;

    private Message message;
    public static final String TAG = "MessageApplication";

    //region METODOS CICLO DE VIDA
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //region VERSIÓN ANTIGUA - findViewById()
        /*
        setContentView(R.layout.activity_view);
        tvUserInfo = findViewById(R.id.tvUserInfo);
        tvMessage = findViewById(R.id.tvMessage);
        */
        //endregion

        binding = ActivityViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Recoger directamente el Intent y el Bundle
        Bundle bundle = getIntent().getExtras();

        //region VERSIÓN ANTIGUA - serializable
        //hay que comprobar la versión del sdk, ya que el getSerializable antiguo no se
        // puede utilizar después de la versión tiramisu
        /*
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            message = bundle.getSerializable(Message.KEY, Message.class);
        } else{
            message = (Message) bundle.getSerializable(Message.KEY);
        }*/
        //endregion

        //Version parcelable
        message = bundle.getParcelable(Message.KEY);
        inicializeView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(this, "Se ha iniciado la Activity ViewActivity", Toast.LENGTH_SHORT).show();
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
        binding = null; //liberar en memoria tod_o lo que se ha creado en la aplicación
    }
    //endregion

    /**
     * Método que inicializa todas las vistas o widgets de la interfaz o del layout
     * @author Ender Watts García
     * @version 1.0
     */
    private void inicializeView() {
        binding.tvUserInfo.setText(String.format(getString(R.string.tvUserInfo_inicializeView), message.getSender().getName(), message.getSender().getSurname(), message.getSender().getDni()));
        binding.tvMessage.setText(message.getContent());
    }


}