package com.example.crudsqlite.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudsqlite.R;
import com.example.crudsqlite.model.Pessoa;

import java.util.List;

public class AdapterPessoa extends RecyclerView.Adapter<AdapterPessoa.MyViewHolder>{

    private List<Pessoa> listaPessoas;

    public AdapterPessoa(List<Pessoa> lista){
        this.listaPessoas = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Converte o XML para um objeto do tipo View
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_pessoa,parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(itemLista);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Pessoa pessoa = listaPessoas.get(position);
        holder.txtvnome.setText(pessoa.getNome());
        holder.txtvID.setText(Long.toString(pessoa.getId()));
        holder.txtvemail.setText(pessoa.getEmail());
        holder.txtvphone.setText(pessoa.getPhone());

    }

    @Override
    public int getItemCount() {
        return this.listaPessoas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtvnome;
        TextView txtvemail;
        TextView txtvphone;
        TextView txtvID;
        public MyViewHolder(View itemView){
            super(itemView);
            txtvnome = itemView.findViewById(R.id.txtvNome);
            txtvID = itemView.findViewById(R.id.txtvID);
            txtvemail = itemView.findViewById(R.id.txtvEmail);
            txtvphone = itemView.findViewById(R.id.txtvPhone);


        }
    }
}
