package com.sicredi.administradorvotacaopauta.service;

import com.sicredi.administradorvotacaopauta.AdministradorvotacaopautaApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
//@WebMvcTest
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= AdministradorvotacaopautaApplication.class)
class AdministradorVotacaoPautaServiceTest {

    @Autowired
    AdministradorVotacaoPautaService administradorVotacaoPautaService;

    @Test
    void cadastrarNovaPauta() {

        administradorVotacaoPautaService.cadastrarNovaPauta(123L);
        assertTrue(administradorVotacaoPautaService.verificaPautaExistente(123L));

    }

    @Test
    void abreSessaoVotacaoSePautaExistente() {

        administradorVotacaoPautaService.cadastrarNovaPauta(123L);
        assertTrue(administradorVotacaoPautaService.abreSessaoVotacaoSePautaExistente(123L, 0));

    }

    @Test
    void receberVotoSePautaExistente() {

        administradorVotacaoPautaService.cadastrarNovaPauta(123L);
        administradorVotacaoPautaService.receberVotoSePautaExistente(123L, "03290895017", "Sim");

        // resultado da api user info é random

    }

    @Test
    void contabilizarVotosRetornarResultadoVotacaoPauta() {



    }
}