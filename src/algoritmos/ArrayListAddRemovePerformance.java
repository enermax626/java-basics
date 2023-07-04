package algoritmos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListAddRemovePerformance {

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();

        for(int i=0; i<400000; i++){
            integers.add(i);
        }

        // Quando removemos a última posição do array, o que o método 'remove' da ArrayList faz é
        // usar o System.arraycopy e copiar todas as posicoes seguintes a posicao i(que esta sendo removida)
        // para a propria posicao i(que foi removida) até n-1, ou seja, move todos os elementos
        // que estavam na frente do elemento na posicao i pra trás <<<
        // isso requer algum processamento.. mas ainda sim está melhor que a próxima solução, removendo sempre a posicao zero do array
        // que nesse caso sempre vai ter que mover o array inteiro pra esquerda.
        Instant nowLastElement = Instant.now();
        for(int i=399999; i>=0; i--){
            integers.remove(i);
        }
        Instant nowLastElementAfter = Instant.now();
        System.out.println("ARRAY LIST: Removendo a posicao i(que nesse caso do for representa sempre a ultima posicao temos:");
        System.out.println(nowLastElementAfter.toEpochMilli() - nowLastElement.toEpochMilli() +" milisegundos");


        for(int i=0; i<400000; i++){
            integers.add(i);
        }

        Instant now = Instant.now();
        for(int i=0; i<400000; i++){
            integers.remove(0);
        }
        Instant now1 = Instant.now();
        System.out.println("ARRAY LIST: Removendo sempre a posicao 0 e tendo que mover todos os elementos na frente pra uma posicao atrás temos:");
        System.out.println(now1.toEpochMilli() - now.toEpochMilli()+" milisegundos");

        List<Integer> linked = new LinkedList<>();

        for(int i=0; i<400000; i++){
            linked.add(i);
        }

        Instant nowLastElementLinked = Instant.now();
        for(int i=399999; i>=0; i--){
            linked.remove(i);
        }
        Instant nowLastElementAfterLinked = Instant.now();
        System.out.println("LINKED LIST: Removendo a posicao i(que nesse caso do for representa sempre a ultima posicao temos:");
        System.out.println(nowLastElementAfterLinked.toEpochMilli() - nowLastElementLinked.toEpochMilli() +" milisegundos");

        for(int i=0; i<400000; i++){
            linked.add(i);
        }

        Instant nowLinked = Instant.now();
        for(int i=0; i<400000; i++){
            linked.remove(0);
        }
        Instant nowAfterLinked = Instant.now();
        System.out.println("LINKED LIST: Removendo sempre a posicao 0 e nesse caso o que e feito e só apontar a variavel para a proxima posição da linked list");
        System.out.println(nowAfterLinked.toEpochMilli() - nowLinked.toEpochMilli()+" milisegundos");

        int size = 10000000;

        for(int i=0; i<size; i++){
            integers.add(i);
        }

        for(int i=0; i<size; i++){
            linked.add(i);
        }

        Instant getArrayList = Instant.now();
        for(int i=0; i<10000; i++){
            Integer integer = integers.get(size - i-1);
        }
        Instant getArrayListAfter = Instant.now();

        Instant getLinked = Instant.now();
        for(int i=0; i<10000; i++){
            Integer integer = linked.get(size - i-1);
        }
        Instant getLinkedAfter = Instant.now();
        //O arraylist, por ser uma tira de memória continua permite o acesso direto dos elementos pelo index
        //Supondo integer 4bytes, queremos buscar a posicao 20
        //posicao na memoria do array iniciando no 10000, teriamos 4bytes*20(numero da posicao)
        //entao é possivel só com essa multiplicação saber que a posicao 20 esta na memoria 10080 e acessar o valor direto
        System.out.println("ARRAY LIST: Buscando a ultima posicao do arraylist(acesso direto pelo index)");
        System.out.println(getArrayListAfter.toEpochMilli() - getArrayList.toEpochMilli()+" milisegundos");

        //a variavel da linked list só guarda a posição da memoria do primeiro elemento
        //e esse elemento contem o valor dele e também a posicao pro proximo elemento na lista
        //entao i1 -> i2 -> i3.... dai a jvm tem que percorrer todas as posicoes até chegar no i399999
        System.out.println("LINKED LIST: Buscando a ultima posicao da linkedlist(percorre i1->i2..in) onde n é a posicao buscada");
        System.out.println(getLinkedAfter.toEpochMilli() - getLinked.toEpochMilli()+" milisegundos");
    }
}
