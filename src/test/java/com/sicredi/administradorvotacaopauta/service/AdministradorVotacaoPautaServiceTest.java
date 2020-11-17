package com.sicredi.administradorvotacaopauta.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdministradorVotacaoPautaServiceTest {

    AdministradorVotacaoPautaService administradorVotacaoPautaService = new AdministradorVotacaoPautaService();

    @Test
    void cadastrarNovaPauta() {

        administradorVotacaoPautaService.cadastrarNovaPauta("123");

        administradorVotacaoPautaService.verificaPautaExistente("123");

    }

    @Test
    void abreSessaoVotacaoSePautaExistente() {
    }

    @Test
    void receberVotoSePautaExistente() {
    }

    @Test
    void contabilizarVotosRetornarResultadoVotacaoPauta() {
    }
}