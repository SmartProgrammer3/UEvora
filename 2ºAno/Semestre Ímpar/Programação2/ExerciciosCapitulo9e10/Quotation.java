package ExerciciosCapitulo9e10;

import java.util.*;

public class Quotation {

    static void output(){
        System.out.println("Citar S/N?");
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        output();
        ArrayList<String> citacoes = new ArrayList<String>();

        citacoes.add("Ao céu faltam todas as pessoas interessantes.");
        citacoes.add("Somente quem faz aprende.");
        citacoes.add("Muitos teimam em seguir o caminho que escolheram, poucos o objetivo.");
        citacoes.add("Quem luta com monstros deve acautelar-se para não se tornar um.");
        citacoes.add("Enquanto olhas para o abismo, o abismo olha para ti.");
        citacoes.add("Para ver muita coisa é preciso despregar os olhos de si mesmo.");
        citacoes.add("Nada neste mundo consome mais rapidamente o homem que a paixão pelo ressentimento.");
        citacoes.add("Não é a intensidade dos sentimentos elevados que faz os homens superiores, mas a sua duração.");
        citacoes.add("Tudo evolui; não há realidades eternas: tal como não há verdades absolutas.");
        citacoes.add("Aquilo que se faz por amor está sempre além do bem e do mal.");
        citacoes.add("Você vive hoje uma vida que gostaria de viver por toda a eternidade?");

        
        String linha = s.nextLine();
        char caracter = linha.charAt(0);

        if( caracter.compare's'){
            int numero = random.nextInt(11);
            String citacao = citacoes.get(numero);
            citacao.toString();
        }else{
            System.out.println("ok");
        }
        

    }
}
