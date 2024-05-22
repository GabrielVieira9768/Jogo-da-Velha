package com.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean jogoAtivo = true;
        char iconeJogador = menu(input);
        Tabuleiro tabuleiro = new Tabuleiro(iconeJogador);

        while (jogoAtivo) {
            tabuleiro.imprimirTabuleiro();
            System.out.println("Jogador " + tabuleiro.getJogadorAtual() + ", insira sua jogada no formato (linha, coluna): ");
            String jogada = input.nextLine();

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

        input.close();
    }

    public static char menu(Scanner input) {
        System.out.println("-------- Jogo da Velha --------");

        System.out.println("Qual jogador você gostaria de ser, X ou O?");
        char iconeJogador = input.nextLine().trim().toUpperCase().charAt(0);
        while (iconeJogador != 'X' && iconeJogador != 'O'){
            System.out.println("Caractere inválido! Digite novamente:");
            iconeJogador = input.nextLine().trim().toUpperCase().charAt(0);
        }
        // System.out.println("Você gostaria de jogar contra a máquina?");
        
        return iconeJogador;
    }
}