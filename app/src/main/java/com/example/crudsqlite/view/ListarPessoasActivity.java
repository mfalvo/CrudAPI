package com.example.crudsqlite.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudsqlite.R;
import com.example.crudsqlite.adapter.AdapterPessoa;
import com.example.crudsqlite.cadastrar.CadastroPessoaMainActivity;
import com.example.crudsqlite.dao.PessoaDAO;
import com.example.crudsqlite.editar.editarPessoa;
import com.example.crudsqlite.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class ListarPessoasActivity extends AppCompatActivity {

    private RecyclerView recyclerview_listaPessoas;
    private PessoaDAO pessoaDAO;
    private List<Pessoa> pessoas;
    private List<Pessoa> listaPessoasFiltradas = new ArrayList<>();

    private Pessoa pessoaSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listar_pessoas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.pessoaDAO = new PessoaDAO(this);

        recyclerview_listaPessoas = findViewById(R.id.recyclerview_lista_pessoas);

        // Configurar RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview_listaPessoas.setLayoutManager(layoutManager);
        recyclerview_listaPessoas.setHasFixedSize(true);
        recyclerview_listaPessoas.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        this.carregarListaPessoas();
    }

    public void cadastrarPessoa(View view){
        Intent intentx = new Intent(ListarPessoasActivity.this, CadastroPessoaMainActivity.class);
        startActivity(intentx);
    }

    public void removerPessoa(View view){
        if (this.pessoaSelected != null){
            pessoaDAO.remove(this.pessoaSelected);
            this.pessoaSelected = null;
            this.carregarListaPessoas();
        }
    }

    public void editarPessoa(View view){
        if (this.pessoaSelected != null){

            Bundle bundle = new Bundle();
            bundle.putSerializable("objPessoa", this.pessoaSelected);

            Intent intent = new Intent(ListarPessoasActivity.this, editarPessoa.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Não há um registro selecionado!" , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Execute o método personalizado aqui
        this.carregarListaPessoas();

    }

    public void carregarListaPessoas(){

        this.pessoas = pessoaDAO.obterTodos();
        this.listaPessoasFiltradas.clear();
        this.listaPessoasFiltradas.addAll(pessoas);
        // Configurar Adaptador
        AdapterPessoa adapter_pessoa = new AdapterPessoa(listaPessoasFiltradas);
        recyclerview_listaPessoas.setAdapter(adapter_pessoa);

    }
    public void showItem(View view){

        TextView id, nome, email, phone;

        id = view.findViewById(R.id.txtvID);
        nome = view.findViewById(R.id.txtvNome);
        email = view.findViewById(R.id.txtvEmail);
        phone = view.findViewById(R.id.txtvPhone);

        this.pessoaSelected = new Pessoa();

        pessoaSelected.setId(Long.parseLong(id.getText().toString()));
        pessoaSelected.setNome(nome.getText().toString());
        pessoaSelected.setEmail(email.getText().toString());
        pessoaSelected.setPhone(phone.getText().toString());

        Toast.makeText(this, "Registro selecionado: "+id.getText().toString(),
                Toast.LENGTH_SHORT).show();
    }
}
