# loja-online
Projeto - Exemplo Loja Online 

~~~
sudo docker-compose up -d
~~~

Todas as imagens utilizadas estão no docker hub  

## Front Angular

Front Angular
web-loja-online

~~~
ng serve
~~~

URL:
~~~
http://localhost:4200/
~~~

## mysql

Montado via docker-compose  

Volumes:  

~~~
- ./mysql/volume:/var/lib/mysql
- ./mysql/scripts:/docker-entrypoint-initdb.d
- ./mysql/conf/my.cnf:/etc/mysql/my.cnf
~~~

Montar esse volume dentro da pasta docker-compose-loja  
~~~
mysql/volume
~~~

Configuração, arquivo my.conf:
~~~
!includedir /etc/mysql/conf.d/
!includedir /etc/mysql/mysql.conf.d/
[mysqld]
max_connections=1000
~~~

Scripts de Incicialização de bases de dados:
~~~
scripts/db_sales.sql -> Base utilizada pela aplicação springboot sales-taxes-service
scripts/keycloak.sql -> Base utilizada pelo keycloak
~~~

São criados dois usuários:

~~~
- root/root
- lojaonline/lojaonline
~~~

Dados:  
~~~
Imagem: mysql:5.7.29
Porta: 3306
~~~

## phpmyadmin

Montado via docker-compose  

URL da aplicação:  
~~~
http://localhost:8183/
~~~

Dados:  
~~~
Imagem: phpmyadmin/phpmyadmin
Porta: 8183
~~~

## mongo

Montar esse volume dentro da pasta docker-compose-loja  
~~~
mongo
~~~

Dados:  
~~~
Imagem: mongo:3.4.23-xenial
Porta: 27017
Autenticação: off
~~~

## redis

Dados:  
~~~
Imagem: redis:5.0.6-alpine
Porta: 6379
Autenticação: off
~~~

## keycloak

Utiliza o mysql como base de dados.

Dados:  
~~~
Imagem: jboss/keycloak:9.0.2 

environment:
        DB_VENDOR: MYSQL
        DB_ADDR: mysql
        DB_DATABASE: KEYCLOAK
        DB_USER: lojaonline
        DB_PASSWORD: lojaonline
        KEYCLOAK_USER: admin
        KEYCLOAK_PASSWORD: admin
        JDBC_PARAMS: "useSSL=false"
Portas:        
        - "8180:8080" -> Porta HTTP
        - "8443:8443"        
~~~

## jbm

Utiliza o mysql como base de dados e utiliza o keycloak para autenticação de usuários.

Imagem montada a partir da versão jbpm-server-7.51.0.final-dist do jbm. Incluídos nessa imagem o driver do MySQL e pluging do keycloak.  

Mais detalhes sobre a integração, aqui:
http://blog.athico.com/2016/03/keycloak-sso-integration-into-jbpm-and.html

~~~
https://www.jbpm.org/
~~~

Configurações:  
~~~
standalone.xml -> Configurado para usar o mysql, keycloak e cors habilitado.
~~~

Montar esse volume dentro da pasta docker-compose-loja  
~~~
jbpm-server/volumes
~~~

Dados:  
~~~
Imagem: fernandoreb/jbpm-server-7.51.0.final-dist:1.0.3 

volumes:
        - ./jbpm-server/standalone/configuration/standalone.xml:/jbpm/jbpm-server-7.51.0.Final-dist/standalone/configuration/standalone.xml
        - ./jbpm-server/standalone/configuration/standalone.conf:/jbpm/jbpm-server-7.51.0.Final-dist/standalone/configuration/standalone.conf
~~~

## sales-taxes-service

Projeto Java feito com SpringBoot. É o banckend responsável por manter os produtos e taxas. Usa o mysql como base de dados

As configurações estão dentro da própria imagem.

URL Base do Serviço:
~~~
http://localhost:8081/sales/v1
~~~

URL do Swagger da aplicação:
~~~
http://localhost:8081/swagger-ui.html#
~~~

Dados:  
~~~
Imagem: fernandoreb/sales-taxes-service:1.0.0
Porta: 8081
~~~

Utilize o swagger para fazer o input de dados nas tabelas

## sales-taxes-service

Projeto nodejs versão 10. É o backend responsável por manter os comentários dos produtos. Usa o mongodb e redis.

As configurações estão dentro da própria imagem.

## Setup das aplicações

Uma vez inciado o ambiente, na primeira vez, executar os passos:

### Setup do RH-SSO

1 Criar um usuário administrativo

~~~
sugestão: admin/admin
~~~

2 Criar um realm chamado ***loja_online***

2.1 Criar o client de conexão da aplicação web: ***lojaOnline***  

~~~
Access_Type: confidential
Valid Redirect URIs: *
Web Origins: *
~~~

**Obs:** Não esquecer de copiar o valor da Secret do Client para colocar na aplicação web.

2.2 Criar o client de conexão da aplicação jbpm: ***kie***  

~~~
Access_Type: confidential
Standard Flow Enabled: true
Implicit Flow Enabled: true
Direct Access Grants Enabled: true
Service Accounts Enabled: true
Valid Redirect URIs: *
Web Origins: *
~~~

**Obs:** Não esquecer de copiar o valor da Secret do Client para colocar no arquivo de configuração do jbpm (standalone.xml).


2.3 Criar as roles abaixo, usadas pela aplicação web:

~~~
- NormalUser - Usuário padrão da aplicação web
- SuperUser - Usuário admin da aplicação web
~~~

2.4 Criar as roles abaixo, usadas pela aplicação jbpm:

~~~
- admin - Perfil admin
- analyst - Perfil genério de analista
- developer - Perfil de desenvolvedor (tem permissão de criar processos)
- kie-server - perfil do servidor de processos
- kiemgmt - perfil do servidor de processos
- manager - pefil genérico de gestão
- rest-all - perfil para consumo de apis rest
- rest-project - perfil para consumo de apis rest
- user - perfil de usuário de processos (pode acessar o busness central)
- COMPRA_ONLINE_BACK - usuário associado a tarefa de backoffice para o processo de compra.
~~~

3 Criação dos usuários no realm loja_online   

Lista de usuários/senha -> perfis -> Observação

Obs: as roles default foram mantidas para cada usuário.
~~~
admin/admin -> admin,kiemgmt,resl-all -> usuário administrativo do jbpm
backloja/backloja -> COMPRA_ONLINE_BACK, rest-all, user -> Usuário de backoffice de processos.
usuariocompra/usuariocompra -> user, NormalUser, rest-all -> Usuário da aplicação com permissão de criar um processo (usuário de testes)
kieserver/kieserver1! -> admin, kie-server, rest-all -> Usuário admministrativo do business central e kieserver.
wbadmin/wbadmin -> admin,kiemgmt,resl-all -> usuário administrativo do jbpm
### 

4 Criação de produtos de teste

Usando API Swagger Backend:  
http://localhost:8081/swagger-ui.html#


Produto01:  
~~~
name: Zenfone Shot Plus Silver
description: Zenfone Shot Plus Silver Asus 4g 128gb
price 1046
sku: 123456
url: https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcTuJliwG9LFyRNAkImH9aH76xuTxN6LaiPl1sK8cXw3Rv5j44wwYKxisd-AWTw4m_f6Nz_NtjQrJQ&usqp=CAc
~~~

Produto 02:
~~~
name: Asus Zenfone Zoom
description: Smartphone Asus Zenfone Zoom ZX551M Preto 32GB
price 1000
sku: 789456
url: https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcTnsYEuiV5hJEgr4NLbEUkwFLJzXNR_fEswMF3MsWHVrGDsKPRzo0dGWRt3PhiLXMUVNd4pRLJUBzA&usqp=CAc
~~~

Criar uma taxa e associar aos produtos  

5 Criação de um comentário  

Chamar a API de comentários

Usando Postman:
~~~
Verbo: Post
URL: http://localhost:3000/api/productsComments

Body
{
	"sku":"12345",
	"description":"Very Good!!"
}
~~~

## Montagem das imagens Docker





