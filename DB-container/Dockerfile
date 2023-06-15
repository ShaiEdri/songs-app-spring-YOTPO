FROM mysql
COPY 01_schema.sql /docker-entrypoint-initdb.d
COPY 02_configure-mysql.sql /docker-entrypoint-initdb.d
#COPY 03_import.sql /docker-entrypoint-initdb.d