# ProgramacaoModular

Trabalho prático referente à disciplina de Programação Modular 2017/1 - UFMG

## Objetivo 

Esta aplicação consiste em um sistema de gerenciamento de telefonia como sendo uma rede heterogênea que conecta as centrais telefônicas aos 
assinantes. Cada assinante é conectado a uma única central, que é capaz de receber seus pedidos e encaminhá-los para o restante da rede. 
A comunicação começa com o pedido de conexão, esta solicitação é roteada através da rede. Se existir caminho de conexão, a chamada será 
realizada e desta forma o caminho se torna exclusivo para o receptor e transmissor até que a ligação seja concluída. Se não existir 
caminho, ou a linha já estiver ocupada, ou ainda a central está temporariamente fora de serviço, esta ligação não pode ser completada. 
Além das ligações este sistema realiza o gerenciamento de criação e exclusão de novas linhas para assinantes e outras centrais. Outra 
parte imporante para um sistema de telefonia é a capacidade de gerar contas com periodicidade para seus assinantes.

##Informações importantes sobre o Sistema

### Main Features

- **Gerenciamento de Comunicação entre Assinantes;**
    - Ligar;
    - Desligar;
    - Reconectar Ligação;
- **Gerenciamento de Eventos da Rede;**
    - Adicionar Linha entre Centrais;
    - Adicionar Linha entre Assinante e Central;
    - Remover Linha entre Centrais;
    - Remover Linha entre Assinante e Central;
    - Suspender Linha entre Centrais;
    - Suspender Linha entre Assinante e Central;
    - Reativar Linha entre Centrais;
    - Reativar Linha entre Assinante e Central;
- **Emissão de Faturas;**
- **Captura de Sinais dos Eventos;**

### Documentação

Para mais informações e detalhes técnicos sobre o sistema de telefone implementado, consulte o arquivo "Documentação do Trabalho.pdf" 
dentro da pasta documentation, na raiz do projeto.

## Manual de Instalação

Para fazer a instalação do programa, siga os seguintes passos:

* Faça download do projeto compactado no link: https://github.com/BrunoLSousa/ProgramacaoModular.git ;

* Faça a extração do arquivo compactado em sua máquina;

* Abra o projeto no programa Netbeans 8.0.2 com a jdk 1.7;

* Limpe-o e construa para que seje gerado uma pasta chamada dist dentro da pasta do projeto;

* Pronto. O programa está instalado.

## Manual de Execução do programa

Para executar o programa, siga os seguintes passos:

* certifique-se que a máquina virtual Java (JVM), versão 7 ou superior esteja instalada em seu computador. Caso não esteja instalada, efetue sua instalação;
* abra o terminal Linux ou console do Mac ou Windows;
* pelo terminal ou console, vá até a pasta onde está localizado o arquivo .jar do programa;
* dentro desse diretório execute o comando de execução Java, passando o arquivo de entrada para o programa. Certifique-se que o arquivo de entrada fornecido está correto e não possui inconsistências. O comando java utilizado para executar a aplicação é mostrado no final dessa seção;
* após a execução do programa, são gerados três arquivos txt, no mesmo diretório cuja aplicação .jar está armazenada. Esses arquivos são as saídas emitidas pelo programa. Essas saídas referem-se a: eventos, faturas e sinais.

Pronto. Programa executado.

A utilizar essa aplicação, o usuário deve fornecer um arquivo txt contendo as informações de entrada. O comando utilizado para executar 
o arquivo jar é o seguinte:

- **java -jar ProgramacaoModular.jar arquivo_de_entrada**