package com.example.crudsqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.crudsqlite.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public PessoaDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public Long inserir(Pessoa pessoa){
        ContentValues values = new ContentValues();
        values.put("nome", pessoa.getNome());
        values.put("email", pessoa.getEmail());
        values.put("phone", pessoa.getPhone());
        return banco.insert("pessoa",null, values);
    }

    public Boolean update(Pessoa pessoa){

        long id = pessoa.getId();
        String nome = pessoa.getNome();
        String email = pessoa.getEmail();
        String phone = pessoa.getPhone();

        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("email", email);
        values.put("phone", phone);
        Cursor cursor = banco.rawQuery("Select * from pessoa where id = ?", new String[] {Long.toString(id)});
        if (cursor.getCount()>0)
        {
            long result = banco.update("pessoa",values,"id=?", new String[] {Long.toString(id)});
            if(result ==-1){
                return false;
            }else
            {
                return true;
            }
        }else
        {
            return false;
        }
    }

    public Pessoa getPessoaByID(long id){
        Pessoa result;
        Cursor cursor = banco.rawQuery("Select * from pessoa where id = ?", new String[] {Long.toString(id)});
        if (cursor.getCount()>0)
        {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(cursor.getInt(0));
            pessoa.setNome(cursor.getString(1));
            pessoa.setEmail(cursor.getString(2));
            pessoa.setPhone(cursor.getString(3));
            result = pessoa;
        }else
        {
            result = null;
        }
        return result;
    }



    public boolean remove(Pessoa pessoa){
        long id = pessoa.getId();
        Cursor cursor = banco.rawQuery("Select * from pessoa where id = ?", new String[] {Long.toString(id)});
        if (cursor.getCount()>0)
        {
            long result = banco.delete("pessoa","id=?", new String[] {Long.toString(id)});
            if(result ==-1){
                return false;
            }else
            {
                return true;
            }
        }else
        {
            return false;
        }
    }

    public List<Pessoa> obterTodos(){
        List<Pessoa> pessoas = new ArrayList<>();
        Cursor cursor = banco.query("pessoa", new String[]{"id", "nome", "email", "phone"},
                null, null, null, null, null);
        while(cursor.moveToNext()){
            Pessoa pessoa = new Pessoa();
            pessoa.setId(cursor.getInt(0));
            pessoa.setNome(cursor.getString(1));
            pessoa.setEmail(cursor.getString(2));
            pessoa.setPhone(cursor.getString(3));
            pessoas.add(pessoa);
        }
        return pessoas;

    }

}
