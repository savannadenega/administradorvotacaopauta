package com.sicredi.administradorvotacaopauta.service;

import com.sicredi.administradorvotacaopauta.client.UserInfoClient;
import com.sicredi.administradorvotacaopauta.entity.Pauta;
import com.sicredi.administradorvotacaopauta.repository.PautaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AdministradorVotacaoPautaService {

    @Autowired
    private PautaRepository repository;
    @Autowired
    private UserInfoClient userInfoClient;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void cadastrarNovaPauta(Long pautaId) {
        Pauta pauta = new Pauta(pautaId);
        repository.save(pauta);
        logger.info("cadastrarNovaPauta: Pauta cadastrada com sucesso");
    }

    public boolean verificaPautaExistente(Long pautaId){
        Optional<Pauta> pauta = repository.findById(pautaId);
        return pauta.isPresent();
    }

    public boolean abreSessaoVotacaoSePautaExistente(Long pautaId, int sessaoVotacaoTempoMinutos) {

        if(!verificaPautaExistente(pautaId)){
            logger.info("receberVotoSePautaExistente: Pauta inexistente");
            return false;
        }

        Optional<Pauta> pauta = repository.findById(pautaId);
        Pauta pautaSessaoAberta = pauta.get();

        if(sessaoVotacaoTempoMinutos == 0){
            sessaoVotacaoTempoMinutos = 1;
        }
        pautaSessaoAberta.setSessaoVotacaoTempoMinutos(sessaoVotacaoTempoMinutos);
        repository.save(pautaSessaoAberta);
        logger.info("abreSessaoVotacaoSePautaExistente: Sessão da pauta aberta com sucesso");

        return true;
    }

    public boolean receberVotoSePautaExistente(Long pautaId, String associadoCpf, String voto) {

        if(!verificaPautaExistente(pautaId)){
            logger.info("receberVotoSePautaExistente: Pauta inexistente");
            return false;
        }

        Optional<Pauta> pauta = repository.findById(pautaId);
        Pauta pautaVoto = pauta.get();
        String[] votos = pautaVoto.getCpfAssociadosJaVotaram().split(";");
        List<String> cpfAssociadosJaVotaram = Arrays.asList(votos);
        List<String> votoJaRegistrado = cpfAssociadosJaVotaram.stream().filter(cpf -> cpf.equalsIgnoreCase(associadoCpf)).collect(Collectors.toList());
        if(!votoJaRegistrado.isEmpty()){
            logger.info("receberVotoSePautaExistente: Associado já votou");
            return false;
        }

        try {
            if(!userInfoClient.validarAssociadoPodeVotar(associadoCpf)){
                logger.info("receberVotoSePautaExistente: Associado não pode votar");
                return false;
            }
        } catch (Exception e) {
            logger.info("receberVotoSePautaExistente: Cpf do associado é inválido");
            return false;
        }

        if (voto.equalsIgnoreCase("Sim")){
            pautaVoto.adicionaVotoSim();
        } else if (voto.equalsIgnoreCase("Não")){
            pautaVoto.adicionaVotoNao();
        }
        String listaVotos = pautaVoto.getCpfAssociadosJaVotaram();
        pautaVoto.setCpfAssociadosJaVotaram(listaVotos.concat(associadoCpf+";"));
        repository.save(pautaVoto);

        logger.info("receberVotoSePautaExistente: Voto do associado contabilizado com sucesso");
        return true;
    }

    public String contabilizarVotosRetornarResultadoVotacaoPauta(Long pautaId) {

        String resultado = "Votação finalizada - Pauta: %s - Quantidade de votos Sim: %s - Quantidade de votos Não: %s";

        if(!verificaPautaExistente(pautaId)){
            logger.info("receberVotoSePautaExistente: Pauta inexistente");
            resultado = "Pauta inexistente";
        }

        Optional<Pauta> pauta = repository.findById(pautaId);
        Pauta pautaResultado = pauta.get();
        resultado = String.format(resultado, pautaId, pautaResultado.getQtVotosSim(), pautaResultado.getQtVotosNao());

        logger.info(resultado);
        return resultado;
    }
}
