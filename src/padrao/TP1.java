package padrao;

import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TP1 {

    int[][] memoriaInstrucoes;
    int[] memoriaDados = new int[1000];

    public static void main(String[] args) {
        new TP1();
    }

    public TP1() {
        int op = 0;
        do {
            //menu
            JFrame frame = new JFrame();
            frame.setAlwaysOnTop(true);

            Object[] options = {"Soma", "Subtração", "Divisão", "Multiplicação", "Raiz Quadrada", "Potência", "Fatorial", "Logaritmo", "Sair"};
            //...and passing `frame` instead of `null` as first parameter
            Object selectionObject = JOptionPane.showInputDialog(frame, "Escolha uma opção ou 'Sair'", "Menu", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            for (int i = 0; i < options.length; i++) {
                if (options[i].equals(selectionObject)) {
                    op = i;
                }
            }
            montarRam();

            int x, y;
            switch (op) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    x = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o valor do divisor: ", null, 1));
                    y = Integer.parseInt(JOptionPane.showInputDialog(null, "Agora, digite o valor do dividendo: ", null, 1));
                    divisao(x, y);
                    JOptionPane.showMessageDialog(null, "O resultado da divisão foi: " + memoriaDados[3] + "   ", null, 1);
                    break;
                case 3:
                    x = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o valor do multiplicando: ", null, 1));
                    y = Integer.parseInt(JOptionPane.showInputDialog(null, "Agora, digite o valor do multiplicador: ", null, 1));
                    multiplicacao(x, y);
                    JOptionPane.showMessageDialog(null, "O resultado da multiplicacão foi: " + memoriaDados[1] + "   ", null, 1);
                    break;
                case 4:
                    x = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o radicando da raiz: ", null, 1));
                    raizQuadrada(x);
                    JOptionPane.showMessageDialog(null, "O resultado da raiz quadrada de " + x + "  foi: " + memoriaDados[5] + "   ", null, 1);
                    break;
                case 5:
                    x = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o valor do base: ", null, 1));
                    y = Integer.parseInt(JOptionPane.showInputDialog(null, "Agora, digite o valor do expoente: ", null, 1));
                    potencia(x, y);
                    JOptionPane.showMessageDialog(null, "O resultado da potencia de " + x + " por " + y + " foi: " + memoriaDados[4] + "   ", null, 1);
                    break;
                case 6:
                    x = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o valor a conhecer o fatorial", null, 1));
                    fatorial(x);
                    JOptionPane.showMessageDialog(null, "O resultado da fatorial de " + x + " foi: " + memoriaDados[6] + "   ", null, 1);
                    break;
                case 7:
                    x = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o valor da base do log", null, 1));
                    y = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o valor a conhecer o log", null, 1));
                    log(y, x);
                    JOptionPane.showMessageDialog(null, "O resultado do log de " + y + " na base " + x + " foi: " + memoriaDados[7] + "   ", null, 1);
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        } while (op != 8);
    }

    void maquinaInterpretada(int[] umaInstrucao) {
        int opcode = umaInstrucao[0];
        switch (opcode) {
            //SOMAR
            case 0: {

                int end1 = umaInstrucao[1];
                int end2 = umaInstrucao[2];
                //buscar na RAM
                int conteudoRam1 = memoriaDados[end1];
                int conteudoRam2 = memoriaDados[end2];
                int soma = conteudoRam1 + conteudoRam2;
                //salvando resultado na RAM
                int end3 = umaInstrucao[3];
                memoriaDados[end3] = soma;
                break;
            }

            case 1: {
                //subtrair
                int end1 = umaInstrucao[1];
                int end2 = umaInstrucao[2];
                //buscar na RAM
                int conteudoRam1 = memoriaDados[end1]; //tp2
                int conteudoRam2 = memoriaDados[end2]; //tp2
                int sub = conteudoRam1 - conteudoRam2;
                //salvando resultado na RAM
                int end3 = umaInstrucao[3];
                memoriaDados[end3] = sub;
                break;
            }
            //levar para memoriaDados
            case 2: {

                int content = umaInstrucao[1];
                int end = umaInstrucao[2];
                memoriaDados[end] = content;
                break;
            }
            //trazer da memoriaDados
            case 3: {
                umaInstrucao[1] = memoriaDados[umaInstrucao[2]];
                break;
            }

        }
    }

    void maquina() {
        int PC = 0;
        int opcode = Integer.MAX_VALUE;
        while (opcode != -1) {
            int[] umaInstrucao = memoriaInstrucoes[PC];

            opcode = umaInstrucao[0];

            maquinaInterpretada(umaInstrucao);

            PC++;
        }

    }

    void montarRam() {
        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            memoriaDados[i] = r.nextInt(100);
        }
    }

    void multiplicacao(int multiplicando, int multiplicador) {
        memoriaInstrucoes = new int[multiplicador + 3][4];

        int[] umaInstrucao = new int[4];
        umaInstrucao[0] = 2;
        umaInstrucao[1] = multiplicando;
        umaInstrucao[2] = 0;
        umaInstrucao[3] = -1;
        memoriaInstrucoes[0] = umaInstrucao;

        umaInstrucao = new int[4];
        umaInstrucao[0] = 2;
        umaInstrucao[1] = 0;
        umaInstrucao[2] = 1;
        umaInstrucao[3] = -1;
        memoriaInstrucoes[1] = umaInstrucao;

        for (int i = 0; i < multiplicador; i++) {
            umaInstrucao = new int[4];
            umaInstrucao[0] = 0;
            umaInstrucao[1] = 0;
            umaInstrucao[2] = 1;
            umaInstrucao[3] = 1;
            memoriaInstrucoes[i + 2] = umaInstrucao;
        }

        //inserindo a ultima instrucao do programa que faz o HALT
        umaInstrucao = new int[4];
        umaInstrucao[0] = -1;
        umaInstrucao[1] = -1;
        umaInstrucao[2] = -1;
        umaInstrucao[3] = -1;

        memoriaInstrucoes[multiplicador + 2] = umaInstrucao;

        maquina();
    }

    void divisao(int dividendo, int divisor) {
        memoriaInstrucoes = new int[5][4];

        int[] umaInstrucao = null;
        umaInstrucao = new int[4];
        umaInstrucao[0] = 2;
        umaInstrucao[1] = divisor;
        umaInstrucao[2] = 0;
        umaInstrucao[3] = -1;
        memoriaInstrucoes[0] = umaInstrucao;
        //memoriaDados[0] = divisor

        umaInstrucao = new int[4];
        umaInstrucao[0] = 2;
        umaInstrucao[1] = dividendo;
        umaInstrucao[2] = 1;
        umaInstrucao[3] = -1;
        memoriaInstrucoes[1] = umaInstrucao;
        //memoriaDados[1] = dividendo

        umaInstrucao = new int[4];
        umaInstrucao[0] = 2;
        umaInstrucao[1] = 1;
        umaInstrucao[2] = 2;
        umaInstrucao[3] = -1;
        memoriaInstrucoes[2] = umaInstrucao;
        //memoriaDados[2] = 1
        //representa uma vari�vel de incremento

        umaInstrucao = new int[4];
        umaInstrucao[0] = 2;
        umaInstrucao[1] = 0;
        umaInstrucao[2] = 3;
        umaInstrucao[3] = -1;
        memoriaInstrucoes[3] = umaInstrucao;
        //memoriaDados[3] = 0
        //representa quantas subtra��es foram feitas
        //representa o resultado da divis�o

        umaInstrucao = new int[4];
        umaInstrucao[0] = -1;
        umaInstrucao[1] = -1;
        umaInstrucao[2] = -1;
        umaInstrucao[3] = -1;
        memoriaInstrucoes[4] = umaInstrucao;

        maquina();

        //trazer da memoriaDados[0]
        umaInstrucao = new int[4];
        umaInstrucao[0] = 3;
        umaInstrucao[1] = -1;
        umaInstrucao[2] = 0;
        umaInstrucao[3] = -1;
        maquinaInterpretada(umaInstrucao);
        int ram0 = umaInstrucao[1];

        //trazer da memoriaDados[1]
        umaInstrucao = new int[4];
        umaInstrucao[0] = 3;
        umaInstrucao[1] = -1;
        umaInstrucao[2] = 1;
        umaInstrucao[3] = -1;
        maquinaInterpretada(umaInstrucao);
        int ram1 = umaInstrucao[1];

        while (ram1 >= ram0) {
            //subtrair
            umaInstrucao = new int[4];
            umaInstrucao[0] = 1;
            umaInstrucao[1] = 1;
            umaInstrucao[2] = 0;
            umaInstrucao[3] = 1;
            maquinaInterpretada(umaInstrucao);

            //somar
            umaInstrucao = new int[4];
            umaInstrucao[0] = 0;
            umaInstrucao[1] = 2;
            umaInstrucao[2] = 3;
            umaInstrucao[3] = 3;
            maquinaInterpretada(umaInstrucao);

            //trazer da memoriaDados[0]
            umaInstrucao = new int[4];
            umaInstrucao[0] = 3;
            umaInstrucao[1] = -1;
            umaInstrucao[2] = 0;
            umaInstrucao[3] = -1;
            maquinaInterpretada(umaInstrucao);
            ram0 = umaInstrucao[1];

            //trazer da memoriaDados[1]
            umaInstrucao = new int[4];
            umaInstrucao[0] = 3;
            umaInstrucao[1] = -1;
            umaInstrucao[2] = 1;
            umaInstrucao[3] = -1;
            maquinaInterpretada(umaInstrucao);
            ram1 = umaInstrucao[1];
        }

    }

    void potencia(int base, int expoente) {
        int result = base;
        for (int i = 0; i < expoente - 1; i++) {
            multiplicacao(base, result);
            result = memoriaDados[1];
        }

        //levando para memoriaDados
        int[] umaInstrucao = new int[4];
        umaInstrucao[0] = 2;
        umaInstrucao[1] = result;
        umaInstrucao[2] = 4;
        umaInstrucao[3] = -1;
        maquinaInterpretada(umaInstrucao);

    }

    void raizQuadrada(int valor) {
        //fatoracao
        int divisor = 2, cont = 0;
        int result = 1;
        while (valor > 1) {
            if (valor % divisor == 0) {
                divisao(valor, divisor);
                valor = memoriaDados[3];
                cont++;
                if (cont == 2) {
                    result *= divisor;
                    cont = 0;
                }
            } else {
                divisor++;
                cont = 0;
            }
        }

        //levando para memoriaDados
        int[] umaInstrucao = new int[4];
        umaInstrucao[0] = 2;
        umaInstrucao[1] = result;
        umaInstrucao[2] = 5;
        umaInstrucao[3] = -1;
        maquinaInterpretada(umaInstrucao);

    }

    void fatorial(int valor) {
        int result = valor;

        for (int i = 1; (valor - i) > 1; i++) {
            //levando valores pra memoria
            int[] umaInstrucao = new int[4];
            umaInstrucao[0] = 2;
            umaInstrucao[1] = valor;
            umaInstrucao[2] = 1;
            umaInstrucao[3] = -1;
            maquinaInterpretada(umaInstrucao);

            umaInstrucao = new int[4];
            umaInstrucao[0] = 2;
            umaInstrucao[1] = i;
            umaInstrucao[2] = 2;
            umaInstrucao[3] = -1;
            maquinaInterpretada(umaInstrucao);

            //descobrindo qual sera o proximo a ser multiplicado
            // - 1
            umaInstrucao = new int[4];
            umaInstrucao[0] = 1;
            umaInstrucao[1] = 1;
            umaInstrucao[2] = 2;
            umaInstrucao[3] = 3;
            maquinaInterpretada(umaInstrucao);

            //multiplicando
            multiplicacao(result, memoriaDados[3]);
            result = memoriaDados[1];

        }

        //levando resultado pra memoria de dados
        int[] umaInstrucao = new int[4];
        umaInstrucao[0] = 2;
        umaInstrucao[1] = result;
        umaInstrucao[2] = 6;
        umaInstrucao[3] = -1;
        maquinaInterpretada(umaInstrucao);

    }

    void log(int valor, int base) {
        int i = 1, result = 0;

        do {
            potencia(base, i);
            if (memoriaDados[4] == valor) {
                result = i;
                i = -10;
            } else {
                i++;
            }
        } while (i > 0);

        //levando resultado pra memoria de dados
        int[] umaInstrucao = new int[4];
        umaInstrucao[0] = 2;
        umaInstrucao[1] = result;
        umaInstrucao[2] = 7;
        umaInstrucao[3] = -1;
        maquinaInterpretada(umaInstrucao);
    }

}
