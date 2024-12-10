package com.example.crudsqlite.cadastrar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.crudsqlite.view.ListarPessoasActivity;
import com.example.crudsqlite.model.Pessoa;
import com.example.crudsqlite.dao.PessoaDAO;
import com.example.crudsqlite.R;

public class CadastroPessoaMainActivity extends AppCompatActivity {

    private EditText edtnome;
    private EditText edtemail;
    private EditText edtphone;
    private PessoaDAO pessoaDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtnome = findViewById(R.id.editNome);
        edtemail= findViewById(R.id.editEmail);
        edtphone = findViewById(R.id.editPhone);
        pessoaDAO = new PessoaDAO(this);
    }


    public void salvar(View view){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(edtnome.getText().toString());
        pessoa.setEmail(edtemail.getText().toString());
        pessoa.setPhone(edtphone.getText().toString());
        long id = pessoaDAO.inserir(pessoa);
        Toast.makeText(this,"Contato inserido - id : "+ id, Toast.LENGTH_SHORT).show();
        finish();
    }

    public void fecharCadastro(View view){
        finish();
    }
}