<p align="center">
  <img src="https://user-images.githubusercontent.com/83867936/224102056-00397730-3c88-4c21-b21f-e5fc14dca25a.png" alt="Ecommerce_Microservice">
  
</p>
<hr>
<h1> Visão geral: </h1>
<p> Projeto desenvolvido com o intuito de fazer o back-end de um e-commerce, utilizando carrinho, autentição e autorização com usuários, 
compras com clientes, atualizar pedido conforme o pagamento e a entrega, monitoramente de logs e metricas. 
<h2> Instalação do projeto: </h2>
<p> 1. Será necessário possuir o <a href="https://www.docker.com/">Docker</a> instalado na máquina.</p>
<p> 2. Adicionar o banco de dados no <b>application.properties</b>.</p>
<p> 3. Abrir a pasta logstash, abrir o arquivo <b>prometheus.yml</b>.</p>
<p> 3.1 Alterar o <b>targets</b> para localhost, ou o ip local de sua máquina. </p>
<img src=https://user-images.githubusercontent.com/83867936/224106801-a3535e59-60cc-4019-94fd-0cb2a11dba0d.png>
<p> 4. Executar o arquivo docker-compose.yml que está na raiz do projeto. </p>
<h1> Funções do Projeto </h1>
<br>
<p> Com o Prometheus e o Grafana é possível analisar todas as métricas do sistema, monitorar de forma eficiente e prática: </p>
<img src="https://user-images.githubusercontent.com/83867936/224107949-80de560c-591e-4dba-89dd-6a6989edb310.png">
<br>
<p> Com o Elasticsearch, Kibana e Logstash é possível fazer o monitoramente dos logs, salvar e exibir: </p>
<img src="https://user-images.githubusercontent.com/83867936/224110300-7e289ec8-26b8-41fd-92d5-5f4127381049.png">



