/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author carlos860920
 */
public class DetallesControl {

    int id_ueb;
    String realiado_por;
    String cargo;
    int aviso_vencimiento_contrato;
    int meses_promediar_lectura;

    public DetallesControl() {
    }

    public DetallesControl(int id_ueb, String realiado_por, String cargo, int aviso_vencimiento_contrato, int meses_promediar_lectura) {
        this.id_ueb = id_ueb;
        this.realiado_por = realiado_por;
        this.cargo = cargo;
        this.aviso_vencimiento_contrato = aviso_vencimiento_contrato;
        this.meses_promediar_lectura = meses_promediar_lectura;
    }

    public String getCargo() {
        return cargo;
    }

    public int getId_ueb() {
        return id_ueb;
    }

    public int getMeses_promediar_lectura() {
        return meses_promediar_lectura;
    }

    public String getRealiado_por() {
        return realiado_por;
    }

    public int getViso_vencimiento_contrato() {
        return aviso_vencimiento_contrato;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setId_ueb(int id_ueb) {
        this.id_ueb = id_ueb;
    }

    public void setMeses_promediar_lectura(int meses_promediar_lectura) {
        this.meses_promediar_lectura = meses_promediar_lectura;
    }

    public void setRealiado_por(String realiado_por) {
        this.realiado_por = realiado_por;
    }

    public void setViso_vencimiento_contrato(int aviso_vencimiento_contrato) {
        this.aviso_vencimiento_contrato = aviso_vencimiento_contrato;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
