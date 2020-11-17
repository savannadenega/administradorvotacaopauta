# Administrador de votação de pautas

Projeto que realiza a administração de pautas, sendo possível dar procedência a votação com resultado contabilizado.

## Tecnologias utilizadas
- Java 11
- Springboot
- RabbitMQ

## Sugestão de versionamento para para o projeto

O desenvolvimento de uma estrutura de versionamento já está implementado na classe `AdministradorVotacaoPautaApi`.
O acompanhamento da versão em que o administrador de votação de pautas se encontra pode ser identificado por parte da sua URI.
Como primeira versão a identificação pode ser feita por `/v1`. Exemplo: `http://localhost:8080/v1/cadastrarNovaPauta`

## Endpoints

Acesso `local` via: `http://localhost:8080`

- Cadastrar uma nova pauta:
`/v1/cadastrarNovaPauta/{pautaId}`

- Abre sessão para votação da pauta:
`/v1/abrirSessaoVotacaoPauta/{pautaId}/{tempoSessaoVotacaoMinutos}`

- Receber voto de um associado para pauta:
`/v1/receberVotoPauta/{pautaId}/{associadoCpf}/{voto}`

- Contabilizar os votos e dar o resultado da votação na pauta:
`/v1//contabilizarVotosRetornarResultadoVotacaoPauta/{pautaId}`