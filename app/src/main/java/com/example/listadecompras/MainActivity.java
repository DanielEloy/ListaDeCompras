package com.example.listadecompras;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private TextView footer;
    private EditText editText;
    private CheckBox checkBox;
    private Spinner spinner;

    private ArrayList<Produto> produtos;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);
        spinner = (Spinner) findViewById(R.id.spinner);
        editText = (EditText) findViewById(R.id.edittext);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        footer = (TextView) findViewById(R.id.footer);

        produtos = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                produtos);
        listView.setAdapter(arrayAdapter);

        AdapterView.OnItemLongClickListener itemClickListener = new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> listview,
                                           View view,
                                           int position,
                                           long id){

                final int localPosition = position;
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Remover Produto da lista")
                        .setMessage("Você realmente deseja remover esse produto selecionado da lista?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton){

                                produtos.remove(localPosition);
                                arrayAdapter.notifyDataSetChanged();
                                updateFooter();

                            }})
                        .setNegativeButton(android.R.string.no, null).show();

                return true;
            }
        };
        listView.setOnItemLongClickListener(itemClickListener);

    }

    public void updateFooter(){
        float total = 0;
        float urgente = 0;
        for (int i =0; i < produtos.size(); i++){
            Produto produto = produtos.get(i);
            if (produto.isUrgente()){
                urgente += produto.getPreco();
            }
            total += produto.getPreco();
        }

        footer.setText("Total = "+total+ " : Urgente = "+urgente);
    }

    public void addProduto(View view){
        String item     = String.valueOf(spinner.getSelectedItem());
        boolean checked = checkBox.isChecked();
        float preco     = Float.parseFloat(editText.getText().toString());

        Produto produto = new Produto(preco, item, checked);
        produtos.add(produto);
        arrayAdapter.notifyDataSetChanged();
        updateFooter();
    }

}