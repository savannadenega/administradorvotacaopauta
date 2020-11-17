package com.sicredi.administradorvotacaopauta.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pauta {

    @Id
    private String pautaId;
    private int sessaoVotacaoTempoMinutos;
    private int qtVotosSim;
    private int qtVotosNao;
    private String cpfAssociadosJaVotaram;

    public Pauta() {
    }

    public Pauta(String pautaId) {
        this.pautaId = pautaId;
    }

    public Pauta(String pautaId, int sessaoVotacaoTempoMinutos, int qtVotosSim, int qtVotosNao, String cpfAssociadosJaVotaram) {
        this.pautaId = pautaId;
        this.sessaoVotacaoTempoMinutos = sessaoVotacaoTempoMinutos;
        this.qtVotosSim = qtVotosSim;
        this.qtVotosNao = qtVotosNao;
        this.cpfAssociadosJaVotaram = cpfAssociadosJaVotaram;
    }

    public String getPautaId() {
        return pautaId;
    }

    public void setPautaId(String pautaId) {
        this.pautaId = pautaId;
    }

    public int getSessaoVotacaoTempoMinutos() {
        return sessaoVotacaoTempoMinutos;
    }

    public void setSessaoVotacaoTempoMinutos(int sessaoVotacaoTempoMinutos) {
        this.sessaoVotacaoTempoMinutos = sessaoVotacaoTempoMinutos;
    }

    public int getQtVotosSim() {
        return qtVotosSim;
    }

    public void setQtVotosSim(int qtVotosSim) {
        this.qtVotosSim = qtVotosSim;
    }

    public void adicionaVotoSim(){
        qtVotosSim++;
    }

    public int getQtVotosNao() {
        return qtVotosNao;
    }

    public void setQtVotosNao(int qtVotosNao) {
        this.qtVotosNao = qtVotosNao;
    }

    public void adicionaVotoNao(){
        qtVotosNao++;
    }

    public String getCpfAssociadosJaVotaram() {
        return cpfAssociadosJaVotaram;
    }

    public void setCpfAssociadosJaVotaram(String cpfAssociadosJaVotaram) {
        this.cpfAssociadosJaVotaram = cpfAssociadosJaVotaram;
    }
}
