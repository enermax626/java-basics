package memoria;

public class PassagemReferenciaEValor {
    public static void main(String[] args) {
        Usuario x = new Usuario(30);
        //Váriavel x foi criada no endereço 10000F e o OBJETO(instancia de usuario) foi criada no endereço 15000F(esse é o endereço inicial e vai até 15050F);
        //Então x(10000F até 10004F) pq é uma váriavel e usa 4bytes guarda dentro desses 4 bytes o valor 15OOOF(e sabe que vai até 15050F, ou seja, aponta para o endereço do objeto criado)

        System.out.println(x.getIdade());
        //quando fazemos x.getIdade() o x pega o endereço guardado(15000F) dai o getIdade() acessa e retorna o this.idade(como o atributo do objeto
        // é um inteiro (primitivo) ele retorna o VALOR 30(não a posição de memoria pq é primitivo)


        int novaVariavel = x.getIdade();
        novaVariavel = 37;
        //como ele retornou o VALOR por ser um primitivo, ao fazermos novaVariavel = 37 como é inteiro nós não estamos fazendo novaVariavel apontar pra uma posição na memória mas sim só atribuindo um novo VALOR pra novaVariavel
        //por isso x.getIdade() continua ainda retornando 30, novaVariavel = 37 não alterou nada de memória e x continua guardando 30 no campo 'idade'
        System.out.println(x.getIdade());
        System.out.println(novaVariavel);

        //Quando você passa a variavel X(10000F) como parametro de um MÉTODO(esse é o comportamento que definiram pro java) o que acontece é que o que é passado
        //é de fato o VALOR que x guarda, então não é enviado o 10000F mas sim o valor que ele guarda, que é um endereço pro objeto, ou seja, 15000F
        criarNovoUsuario(x);

        System.out.println(x.getIdade());
    }

    //Dai esse 15000F está entrando aqui no método, o que acontece é que agora o parametro usuarioEntrando(um parametro é uma váriavel na chamada do método)
    //é instanciado e ganha um endereço, então usuarioEntrando é uma váriavel na posição 12430F que guarda o VALOR 15000F que é o endereço de memória da instancia de Usuario que foi passada como parametro na chamada do metodo
    //quando fazemos um usuarioEntrando = new Usuario(42) o que acontece é que a posição dessa variavel usuarioEntrando(12430F) passa a guardar um novo endereço de memória
    //dessa nova instancia de objeto, digamos 16320F ou seja, o x(10000F) que foi passado como parametro da função continua guardando a posição (15000F)
    // e o usuarioEntrando(12430F) que recebeu o VALOR que x guardava(15000F) passou a guardar o endereço de memória da nova instancia(16320F)
    //então aqui ficamos com esse cenário
    // #Chamada do método
    // x(10000F) guardando 15000F que foi passado como parametro na chamada do método
    // Método é chamado
    // usuarioEntrando váriavel é criada e ganha um endereço 12430F e passa a guardar o VALOR que x guardava ou seja
    // usuarioEntrando(12430F) guardando 15000F
    // Quando instanciamos o novo usuario o new Usuario cria um novo objeto Usuario com a idade 42 em uma nova posição de memória 16320F
    // então temos
    // x(10000F) apontando pra 15000F
    // usuarioEntrando(12430F) apontando pra 16320F
    //por isso que ao terminar o método criarNovoUsuario e retornar para o fluxo principal no main quando fazemos System.out.println(x.getIdade());
    // x continua apontando para o 15000F e busca o this.nome dentro desse endereço que continua '30'
    public static void criarNovoUsuario(Usuario usuarioEntrando){
        usuarioEntrando = new Usuario(42);
    }
}
