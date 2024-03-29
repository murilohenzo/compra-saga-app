# Padrão de Sagas com Orquestração e Coreografia para Comunicação de Transação entre Microservices

## Introdução

Em sistemas distribuídos baseados em microservices, é comum que diversas operações relacionadas a uma transação ocorram em diferentes serviços. Garantir a consistência entre essas operações pode ser desafiador. O padrão de Sagas surge como uma abordagem eficaz para lidar com essa complexidade, permitindo a coordenação de transações distribuídas.

No entanto, a natureza distribuída das transações apresenta desafios adicionais. Para garantir a exatidão de uma transação, é essencial manter os princípios do ACID: Atomicidade, Consistência, Isolamento e Durabilidade. 

- A **atomicidade** assegura que todas as etapas de uma transação sejam concluídas com sucesso ou nenhuma delas seja executada, evitando estados intermediários inconsistentes.

- A **consistência** garante a transição dos dados de um estado válido para outro, mantendo a integridade do sistema em todos os momentos.

- O **isolamento** assegura que transações simultâneas não interfiram umas nas outras, produzindo o mesmo resultado que se fossem executadas sequencialmente.

- A **durabilidade** garante que as transações confirmadas permaneçam assim, mesmo diante de falhas do sistema, evitando perda de dados.

Em um cenário de transação distribuída, como aquelas que abrangem múltiplos serviços, manter a integridade do ACID é crucial. Isso demanda uma cuidadosa coordenação e implementação dos processos de execução e compensação das transações, garantindo a consistência e confiabilidade do sistema como um todo.

Existem duas formas principais de implementar o padrão de Sagas: com Orquestração e com Coreografia.

Para adicionar os pontos sobre transação compensatória no README, vamos incluir informações sobre a idempotência e a retentibilidade:

### Transação Compensatória

Ao lidar com transações distribuídas, é importante garantir que o processo de compensação seja confiável e seguro. Duas propriedades-chave a serem consideradas são a idempotência e a retentibilidade da transação compensatória.

- **Idempotência**: Uma transação compensatória deve ser idempotente, o que significa que pode ser aplicada várias vezes sem alterar o resultado além do efeito da primeira aplicação. Isso é crucial para lidar com falhas de rede, timeouts e outras condições adversas que podem levar à execução repetida da compensação.

- **Retentibilidade**: A transação compensatória deve ser retentível, o que significa que deve ser capaz de ser armazenada e repetidamente tentada até que seja concluída com sucesso. Isso é importante para garantir que as compensações sejam aplicadas mesmo em cenários de falha temporária e que não sejam perdidas mesmo em caso de falha total do sistema.

Garantir a idempotência e a retentibilidade das transações compensatórias é fundamental para a integridade e consistência do sistema em face de falhas e condições imprevistas.

Adicionalmente, podemos incluir exemplos de como esses conceitos podem ser implementados nos serviços do sistema, bem como as práticas recomendadas para garantir sua eficácia e robustez.

Se precisar de mais informações ou detalhes sobre como implementar esses conceitos em seus serviços, estou à disposição para ajudar.

### Orquestração de Sagas

Na Orquestração de Sagas, um componente central, muitas vezes chamado de "Coordenador", é responsável por coordenar as etapas individuais da saga. O Coordenador determina quais serviços precisam ser chamados e em que ordem. Ele também lida com a lógica de compensação em caso de falha.

![COMPRA_ORQUESTRADA_DIAGRAMA](https://github.com/murilohenzo/compra-saga-app/assets/28688721/ccfa6e2e-e24e-4de9-82e7-b88f8bd08e73)

### Coreografia de Sagas

Na Coreografia de Sagas, não há um coordenador central. Em vez disso, cada serviço é responsável por sua própria parte da saga. Cada serviço reage a eventos e aciona suas próprias ações, geralmente emitindo eventos para notificar outros serviços sobre suas ações. Isso leva a um acoplamento mais fraco, mas pode tornar a coordenação mais complexa.

![COMPRA_COREOGRAFADA_DIAGRAMA](https://github.com/murilohenzo/compra-saga-app/assets/28688721/86105f16-f552-4938-8a6d-8111d5d0fcea)


## Processo de "Do" e "Undo" para Compensação de Transação

Ao lidar com transações distribuídas, é crucial ter um mecanismo de compensação para lidar com falhas e garantir a consistência do sistema. O processo de "do" e "undo" é uma abordagem comum para isso.

- **"Do"**: Refere-se à etapa inicial da transação, na qual as operações são executadas nos diversos serviços. Por exemplo, ao processar um pedido, as operações "debitar estoque", "cobrar pagamento" e "confirmar pedido" podem ser realizadas.

- **"Undo"**: Se uma falha ocorrer em qualquer ponto da transação, as operações já executadas precisam ser desfeitas para garantir a consistência. Aqui, o processo de "undo" entra em ação, revertendo as operações realizadas anteriormente. Usando o exemplo do pedido, se a confirmação do pedido falhar, a operação "debitar estoque" pode ser revertida, o pagamento pode ser reembolsado e assim por diante.

Implementar o processo de "undo" geralmente é mais complexo do que o processo de "do", pois requer considerações adicionais, como a reversão de operações assíncronas e o tratamento de possíveis falhas durante a compensação.

## Conclusão

O padrão de Sagas com Orquestração e Coreografia oferece abordagens flexíveis para lidar com transações distribuídas em sistemas baseados em microservices. Ao escolher entre esses modelos, é essencial considerar as necessidades específicas do sistema em termos de consistência, complexidade de coordenação e tolerância a falhas. Além disso, implementar um robusto processo de "do" e "undo" para compensação de transações é fundamental para garantir a integridade dos dados e a consistência do sistema em face de falhas inevitáveis.

## Referências:
[Distributed transaction patterns for microservices compared](https://developers.redhat.com/articles/2021/09/21/distributed-transaction-patterns-microservices-compared#choreography)
