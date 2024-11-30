# Usando a imagem oficial do MySQL na versão mais recente
FROM mysql:latest

# Definindo variáveis de ambiente para configurar o MySQL
# Substitua "minha_senha_secreta" pela senha que deseja usar
ENV MYSQL_ROOT_PASSWORD root
ENV MYSQL_DATABASE db_client
ENV MYSQL_USER root
ENV MYSQL_PASSWORD root

# Expondo a porta padrão do MySQL
EXPOSE 3307

# Comando para iniciar o MySQL
CMD ["mysqld"]