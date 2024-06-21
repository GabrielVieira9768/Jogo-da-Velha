package com.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean jogoAtivo = true;
        char iconeJogador = selecaoIcone(input);
        String modoJogo = selecaoModo(input);
        Tabuleiro tabuleiro = new Tabuleiro(iconeJogador);

        while (jogoAtivo) {
            if (modoJogo.equalsIgnoreCase("NAO")) {
                jogoAtivo = jogoLocal(input, tabuleiro, modoJogo);
            } else if (modoJogo.equalsIgnoreCase("2")) {
                jogoAtivo = nivel2(input, tabuleiro);
            } else if (modoJogo.equalsIgnoreCase("1")) {
                jogoAtivo = nivel1(input, tabuleiro);
            }
            tabuleiro.imprimirResumo();
        }

        input.close();
    }

    public static char selecaoIcone(Scanner input) {
        System.out.println("-------- Jogo da Velha --------");
        System.out.println("Qual jogador você gostaria de ser, X ou O?");
        char iconeJogador = input.nextLine().trim().toUpperCase().charAt(0);
        
        while (iconeJogador != 'X' && iconeJogador != 'O') {
            System.out.println("Caractere inválido! Digite novamente:");
            iconeJogador = input.nextLine().trim().toUpperCase().charAt(0);
        }
        
        return iconeJogador;
    }

    public static String selecaoModo(Scanner input) {
        System.out.println("Você gostaria de jogar contra a máquina? Digite Sim ou Nao.");
        String modoJogo = input.nextLine().trim().toUpperCase();

        while (!modoJogo.equalsIgnoreCase("SIM") && !modoJogo.equalsIgnoreCase("NAO")) {
            System.out.println("Resposta inválida! Tente novamente:");
            modoJogo = input.nextLine().trim().toUpperCase();
        }

        if (modoJogo.equalsIgnoreCase("SIM")) {
            System.out.println("Escolha o nível de dificuldade:\nDigite 1 para o nível normal e 2 para o mais difícil:");
            modoJogo = input.nextLine().trim().toUpperCase();

            while (!modoJogo.equals("1") && !modoJogo.equals("2")) {
                System.out.println("Nível inválido! Tente novamente:");
                modoJogo = input.nextLine().trim().toUpperCase();
            }
        }
         
        return modoJogo;
    }

    public static boolean jogoLocal(Scanner input, Tabuleiro tabuleiro, String modoJogo) {
        boolean jogoAtivo = true;
        while (jogoAtivo) {
            tabuleiro.imprimirTabuleiro();
            System.out.println("Jogador " + tabuleiro.getJogadorAtual() + ", insira sua jogada no formato (linha, coluna): ");
            String jogada = input.nextLine().trim();
    
            if (!tabuleiro.fazerJogada(jogada)) {
                System.out.println("Movimento inválido! Tente novamente.");
                continue;
            }
    
            if (tabuleiro.verificarVitoria()) {
                tabuleiro.imprimirTabuleiro();
                System.out.println("Jogador " + tabuleiro.getJogadorAtual() + " venceu!");
                jogoAtivo = false;
            } else if (tabuleiro.verificarEmpate()) {
                tabuleiro.imprimirTabuleiro();
                System.out.println("O jogo empatou!");
                jogoAtivo = false;
            } else {
                tabuleiro.alternarJogador();
            }
        }
        return jogoAtivo;
    }    

    public static boolean nivel1(Scanner input, Tabuleiro tabuleiro) {
        boolean jogoAtivo = true;
        Random rand = new Random();
        int linha, coluna;
    
        while (jogoAtivo) {
            tabuleiro.imprimirTabuleiro();
            System.out.println("Jogador " + tabuleiro.getJogadorAtual() + ", insira sua jogada no formato (linha, coluna): ");
            String jogada = input.nextLine().trim();
    
            if (!tabuleiro.fazerJogada(jogada)) {
                System.out.println("Movimento inválido! Tente novamente.");
                continue;
            }
    
            if (tabuleiro.verificarVitoria()) {
                tabuleiro.imprimirTabuleiro();
                System.out.println("Jogador " + tabuleiro.getJogadorAtual() + " venceu!");
                jogoAtivo = false;
                break;
            } else if (tabuleiro.verificarEmpate()) {
                tabuleiro.imprimirTabuleiro();
                System.out.println("O jogo empatou!");
                jogoAtivo = false;
                break;
            }
    
            tabuleiro.alternarJogador();

            do {
                linha = rand.nextInt(3);
                coluna = rand.nextInt(3);
            } while (!tabuleiro.fazerJogada("(" + (linha) + "," + (coluna) + ")"));
    
            if (tabuleiro.verificarVitoria()) {
                tabuleiro.imprimirTabuleiro();
                System.out.println("Jogador " + tabuleiro.getJogadorAtual() + " venceu!");
                jogoAtivo = false;
                break;
            } else if (tabuleiro.verificarEmpate()) {
                tabuleiro.imprimirTabuleiro();
                System.out.println("O jogo empatou!");
                jogoAtivo = false;
                break;
            }
    
            tabuleiro.alternarJogador();
        }
    
        return jogoAtivo;
    }    

    public static boolean nivel2(Scanner input, Tabuleiro tabuleiro) {
        System.out.println("Ainda não implementado!");
        return false;
    }
}