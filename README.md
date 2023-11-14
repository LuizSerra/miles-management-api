<h3 align="center">
	MILES-MANAGEMENT-API
</h3>

<blockquote align="center">“Não espere resultados brilhantes se suas metas não forem claras”!</blockquote>

<p align="center">
  <img alt="License" src="https://img.shields.io/badge/license-MIT-%2304D361">
</p>

<p align="center">
  <a href="#memo-licença">Licença</a>
</p>

## :rocket: Sobre a API
## Gerenciamento de programas de pontos e milhas.
Programas de fidelidade de cartões e companhias aéreas oferecem pontos que podem ser adquiridos de diversas formas e em seguinda trocados por itens, passagens aéreas e até ser convertidos em dinheiro.
O sistema miles-management tem a finalidade de controlar o fluxo entre os diversos programas , permitindo ao usuário cadastrar os programas de fidelidade e através deles realizar transações sobre seus pontos e milhas.
As operações podem ser:
 - compra
 - venda (quando os pontos/milhas são vendidos e podem contar no saldo do programa, mas deduzido do saldo disponível)
 - uso (quando os pontos/milhas são efetivamente usados. Ex: Na emissão de uma passagem)
 - devolução (quando os pontos/milhas usados retornam ao saldo [mas não ao saldo disponível]. Por exemplo, quando uma passagem é cancelada)
 - bonificação
 - Transferência (dos pontos/milhas entre os programas)
 - Subscription (apenas para representar a assinatura)

# :hammer:  Backend

- Java 1.17
- SpringBoot
- Spring Data
- Banco de dados MYSQL
- Banco de dados H2 (Profile de Teste)
- FlywayDB
- Spring Docs/SWAGGER
- DOCKER
- JUNIT

## :hammer: Tecnologias (Frontend)

EM CONSTRUÇÃO
 
## :key: Como rodar esse projeto.

Para executar este sistema, você precisará de  Java 1.8,  instalado em seu computador;

### :sheep: Clonando o repositório.
```
# Clone este repositório
$ git clone git@github.com:LuizSerra/miles-management-api.git
```
### :computer: Rodando a aplicação - Backend

**Opção 1**
No terminal:
$ Execute o comando na raiz do projeto:

    mvn clean package

$ Ainda no terminal, vá até a pasta target e execute o comando:

    java -jar -DSpring.profiles.active=test git@github.com:LuizSerra/miles-management-api.git

**Opção 2**

 1. Importe o projeto miles-management-api na IDE de sua preferência.
 2. Execute o maven update a fim de atualizar as dependências.
 3. Rode a aplicação.

	O servidor será executado em http://localhost:8080

 O mapeamento objeto-relacional foi feito utilizando Spring Data JPA e a implementação é Hibernate.

### Funcionalidades da aplicação

Toda a documentação referente aos endpoints foi realizada utilizando SWAGGER e pode ser encontrada no endereço http://localhost:8080/swagger-ui/index.html#/. 
O Endereço estará acessível após subir a aplicação, sendo possível, inclusive, testar o endpoints através dessa interface gráfica.

#### Segurança

EM CONSTRUÇÃO

### :computer: Rodando a aplicação - Frontend

EM CONSTRUÇÃO

## :computer: Quer contribuir com o Projeto? Saiba como:

-   Faça um  **fork**  do projeto;
-   Crie uma nova branch com as suas alterações:  
		`git checkout -b my-feature`
-   Salve as alterações e crie uma mensagem de commit contando o que você fez:
		`git commit -m "feature: My new feature"`
-   Envie as suas alterações:  
		`git push origin my-feature`

> Caso tenha alguma dúvida confira este [guia de como contribuir no GitHub](https://github.com/firstcontributions/first-contributions)


## :memo: Licença

Esse projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

Feito com dedicação por Luiz Serra 👋🏽 [Entre em contato](https://www.linkedin.com/in/luizserra)!
