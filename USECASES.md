## Casos de Uso

### 1. Compra

**Ator Principal:** Usuário

**Descrição:** O usuário deseja realizar uma compra, verificando a disponibilidade de estoque e crédito disponível.

#### Subcasos de Uso:

1. Verificar Disponibilidade de Estoque
   - O usuário verifica se há estoque disponível para o item desejado.
2. Verificar Crédito Disponível
   - O usuário verifica se possui crédito suficiente para realizar a compra.

### 2. Pedido-Service

**Ator Principal:** Usuário

**Descrição:** O usuário deseja utilizar o serviço de Pedido para realizar uma compra.

#### Subcasos de Uso:

1. Se Houver Saldo no Estoque
   - Debitar do Estoque
     - O serviço de Pedido verifica se há saldo disponível no estoque e realiza o débito correspondente.
2. Se Não Houver Saldo no Estoque
   - Retornar um Erro
     - Caso não haja saldo disponível no estoque, o serviço de Pedido retorna um erro.

### 3. Cartao-Service

**Ator Principal:** Usuário

**Descrição:** O usuário deseja utilizar o serviço de Cartão para realizar uma compra.

#### Subcasos de Uso:

1. Se Houver Crédito Disponível
   - Debitar do Cartão
     - O serviço de Cartão verifica se há crédito disponível e realiza o débito correspondente no cartão do usuário.
2. Se Não Houver Crédito Disponível
   - Retornar um Erro
     - Caso não haja crédito disponível, o serviço de Cartão retorna um erro.

### 4. Compra Orquestrada Service

**Ator Principal:** Usuário

**Descrição:** O usuário deseja orquestrar a comunicação entre os serviços de Pedido e Cartão para realizar uma compra.

#### Subcasos de Uso:

1. Orquestração Iniciando com Pedido-Service e Depois Cartao-Service
   - Pedido-Service
     - Se Houver Estoque Disponível
       - Debitar do Estoque
     - Se Não Houver Estoque Disponível
       - Retornar um Erro
     - Undo
       - Possibilita a reversão de ações realizadas pelo Pedido-Service, caso necessário. É importante considerar que os microservices podem ser chamados por outros serviços, portanto, a operação de "undo" é crucial para manter a consistência e a integridade do sistema.
   - Cartao-Service
     - Se Houver Crédito Disponível
       - Debitar do Cartão
     - Se Não Houver Crédito Disponível
       - Retornar um Erro
     - Undo
       - Possibilita a reversão de ações realizadas pelo Cartao-Service, caso necessário.

2. Orquestração Iniciando com Cartao-Service e Depois Pedido-Service
   - Cartao-Service
     - Se Houver Crédito Disponível
       - Debitar do Cartão
     - Se Não Houver Crédito Disponível
       - Retornar um Erro
     - Undo
       - Possibilita a reversão de ações realizadas pelo Cartao-Service, caso necessário. É importante considerar que os microservices podem ser chamados por outros serviços, portanto, a operação de "undo" é crucial para manter a consistência e a integridade do sistema.
   - Pedido-Service
     - Se Houver Estoque Disponível
       - Debitar do Estoque
     - Se Não Houver Estoque Disponível
       - Retornar um Erro
     - Undo
       - Possibilita a reversão de ações realizadas pelo Pedido-Service, caso necessário.