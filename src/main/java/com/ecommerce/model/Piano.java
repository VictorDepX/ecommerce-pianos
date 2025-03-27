package com.ecommerce.model;

import com.ecommerce.enumerator.TipoPiano;
import jakarta.persistence.*;

@Entity
public class Piano extends DefaultEntity {

    @Column(length = 60, nullable = false)
    private String modelo;

    private String fabricante;
    
    private Integer numeroDeTeclas;

    private boolean possuiPedais;

    private String material;

    @Enumerated(EnumType.STRING)
    private TipoPiano tipo;

    public TipoPiano getTipo() {
        return tipo;
    }

    public void setTipo(TipoPiano tipo) {
        this.tipo = tipo;
    }

    public String getModelo(){
        return modelo;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    public String getFabricante(){
        return fabricante;
    }

    public void setFabricante(String fabricante){
        this.fabricante = fabricante;
    }

    public Integer getNumerodeteclas(){
        return numeroDeTeclas;
    }

    public void setNumerodeteclas(Integer teclas){
        this.numeroDeTeclas = teclas;
    }

    public boolean getPossuipedais(){
        return possuiPedais;
    }

    public void setPossuipedais(Boolean alternativa){
        this.possuiPedais = alternativa;
    }

    public String getMaterial(){
        return material;
    }

    public void setMaterial(String material){
        this.material = material;
    }
}
