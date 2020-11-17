package com.sicredi.administradorvotacaopauta.api;

import com.sicredi.administradorvotacaopauta.service.AdministradorVotacaoPautaService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController("AdministradorVotacaoPautaApi")
@RequestMapping("/v1")
@Validated
public class AdministradorVotacaoPautaApi {

    @Autowired
    private AdministradorVotacaoPautaService service;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PutMapping("/cadastrarNovaPauta")
    @ApiOperation(value = "Cadastra uma nova pauta")
    public ResponseEntity<Object> cadastrarNovaPauta(String pautaId) {

        logger.info("PUT cadastrarNovaPauta - pautaId: {}", pautaId);
        service.cadastrarNovaPauta(pautaId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/abrirSessaoVotacaoPauta")
    @ApiOperation(value = "Abre sessão para votação da pauta")
    public ResponseEntity<Object> abrirSessaoVotacaoPauta(String pautaId, int tempoSessaoVotacaoMinutos) {

        logger.info("POST abrirSessaoVotacaoPauta - pautaId: {} - tempoSessaoVotacaoMinutos: {}", pautaId, tempoSessaoVotacaoMinutos);
        if (!service.abreSessaoVotacaoSePautaExistente(pautaId, tempoSessaoVotacaoMinutos)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/receberVotoPauta")
    @ApiOperation(value = "Receber voto de um associado para pauta")
    public ResponseEntity<Object> receberVotoPauta(String pautaId, String associadoCpf, String voto) {

        logger.info("POST receberVotoPauta - pautaId: {} - voto: {} - voto: {}", pautaId, associadoCpf, voto);

        if (!service.receberVotoSePautaExistente(pautaId, associadoCpf, voto)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/contabilizarVotosRetornarResultadoVotacaoPauta")
    @ApiOperation(value = "Contabilizar os votos e dar o resultado da votação na pauta")
    public ResponseEntity<String> contabilizarVotosRetornarResultadoVotacaoPauta(String pautaId) {

        logger.info("POST contabilizarVotosRetornarResultadoVotacaoPauta - pautaId: {}", pautaId);
        String resultado = service.contabilizarVotosRetornarResultadoVotacaoPauta(pautaId);

        return ResponseEntity.ok(resultado);
    }

}
