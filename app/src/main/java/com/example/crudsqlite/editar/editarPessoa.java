package com.example.crudsqlite.editar;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.crudsqlite.R;
import com.example.crudsqlite.dao.PessoaDAO;
import com.example.crudsqlite.model.Pessoa;
import com.google.android.material.textfield.TextInputEditText;

public class editarPessoa extends AppCompatActivity {

    private Pessoa pessoaSelected = null;
    private TextInputEditText edtnome, edtphone, edtemail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_pessoa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();
        this.pessoaSelected = (Pessoa) bundle.getSerializable("objPessoa");

        edtnome = findViewById(R.id.edtNome);
        edtemail = findViewById(R.id.edtEmail);
        edtphone = findViewById(R.id.edtPhone);

        this.edtnome.setText(pessoaSelected.getNome());
        this.edtemail.setText(pessoaSelected.getEmail());
        this.edtphone.setText(pessoaSelected.getPhone());
    }

    public void confirmaEdicao(View view)
    {
        this.pessoaSelected.setNome(edtnome.getText().toString());
        this.pessoaSelected.setEmail(edtemail.getText().toString());
        this.pessoaSelected.setPhone(edtphone.getText().toString());

        PessoaDAO pessoaDAO = new PessoaDAO(this);
        pessoaDAO.update(this.pessoaSelected);

        finish();
    }

    public void cancelaEdicao(View view)
    {
        finish();
    }
}