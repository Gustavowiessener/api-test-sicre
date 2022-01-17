<h1>Teste Crud - Automação de Api Rest </h1>

<h5>Para inicialização do projeto, configure as variaveis de ambiente e configure o Path da aplicação e suas dependencias.</h5>

<h2>Dependencias:</h2>
<h4>RestAssured version: 4.1.0</h4>
<h4>Junit-Jupiter: 5.8.0</h4>
<h4>Apache-Maven: 3.8.4</h4>

lasse use
<hr>

<span>Na raiz do projeto, através de seu Prompt de Commando/Terminal/Console execute o seguinte comando:</span>

<ul> <li>mvn test</li>
        <li> Para executar todos os testes em uma determinada c: <b>mvn test -Dtest=classname</b> </li>
</ul>

<hr>

<h3>Cenários do CRUD:</h3>

<span>A Class, <b>*ConsultCpfInRestrict*</b> tem a finalidade de gerir as requisiçoes do tipo GET para consulta do <b>CPF</b> para automatizar a API:</span>


<span>_Cenários importantes_:</span>

<ul>
    <li>[x] Se o CPF não possui restrição do HTTP Status 204 é retornado.</li>
    <li>[x] Se possui restrição o HTTP Status 200 é retornado com a mensagem "O CPF
            99999999999 possui restrição"</li>
  </ul>

<hr>

<span>A Class <b>*CreateSimulation*</b> tem a finalidade de gerir a requisiçao do tipo POST, este endpoint é responsável por inserir uma nova simulação: </span>

<span>_Cenários importantes_:</span>

<ul>
     <li>[x] Uma simulação cadastrada com sucesso retorna o HTTP Status 201 e os dados inseridos como retorno.</li>
     <li>[x] Uma simulação com problema em alguma regra retorna o HTTP Status 400 com a lista de erros.</li>
     <li>[x] Uma simulação para um mesmo CPF retorna um HTTP Status <b>400</b> com a mensagem
            "CPF já existente." </li>
 
</ul>

<hr>

<span>A Class <b>*ChangeSimulations*</b> tem a finalidade de fazer a alteração no body da requisição, apartir de um atributo, usado CPF e Method PUT: <span>

<span>_Cenários importantes_:</span>

<ul>
    <li>[x] A alteração pode ser feita em qualquer atributo da simulação.</li>
    <li>[x] Se o CPF não possuir uma simulação o HTTP Status 404 é retornado com a
            mensagem "CPF não encontrado." </li>

</ul>

<hr>

<span>A Class <b>*ConsultSimulations*</b> tem a finalidade de retornar todas as simulações cadastrados, methodo GET:</span>

<span>_Cenários importantes_:</span>

<ul>
    <li>[x] Retorna a lista de simulações cadastradas e existir uma ou mais.</li>
    <li>[x] Retorna HTTP Status 204 se não existir simulações cadastradas.</li>
    <li>[x] Retorna a simulação cadastrada.</li>
    <li>[x] Se o CPF não possuir uma simulação o HTTP Status 404 é retornado <b>com mensagem.</b></li>
</ul>

<hr>



<span>A Class <b>*DeleteSimulations*</b> tem a finalidade de remover um simulação cadastrada pelo atributo ID:</span>

<span>_Cenários importantes_:</span>

<ul>
    <li>[x] Retorna o HTTP Status <b>204</b> se simulação for removida com sucesso.</li>
    <li>[x]  Retorna o HTTP Status 200 se não existir a simulação pelo ID informado.</li>

</ul>

