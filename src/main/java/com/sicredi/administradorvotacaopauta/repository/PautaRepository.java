package com.sicredi.administradorvotacaopauta.repository;

import com.sicredi.administradorvotacaopauta.entity.Pauta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends CrudRepository<Pauta, Long>{

}
