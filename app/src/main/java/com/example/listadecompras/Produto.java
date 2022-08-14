package com.example.listadecompras;

public class Produto    {
    private float preco;
    private String produto;
    private boolean urgente;

    public Produto(float preco, String produto, boolean urgente){

        this.preco = preco;
        this.setProduto(produto);
        this.setUrgente(urgente);
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public boolean isUrgente() {
        return urgente;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }
    public String toString(){
        String str = this.produto + "(" + preco + ")";
        if (urgente){
            str +=" *";
        }
        return str;
    }

}
