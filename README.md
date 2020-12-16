# blockchainproj
The project consists of 2 modules and MySQL database: Mining-module and Web app module;

Web app module: It's Spring Web MVC app and is used by recipients to create e-wallets, to check wallet's balance,
view transactions and send funds from one wallet to another.

Mining-service handles transactions, which were created by users and generates blocks from transaction data. 
It generates one block per 5 seconds if there are pending transactions, and stops if there are some mistakes in blockchain(like wrong hashes).
It's realised via Spring Boot in separate JVM. 
Can be launched via jar-artifact.

All data is stored in MySQL database. Users, walllets, transactions and block - all is stored there.
