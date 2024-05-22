package com.example;

public class Tabuleiro {
    private char[][] tabuleiro;
    private char jogadorAtual;

    public Tabuleiro(char iconeJogador) {
        tabuleiro = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = ' ';
            }
        }

        jogadorAtual = iconeJogador;
    }

    public boolean fazerJogada(String coordenada) {
        coordenada = coordenada.replaceAll("[()\\s]", "");
        String[] partes = coordenada.split(",");
        if (partes.length != 2) {
            return false;
        }
        
        int linha, coluna;
        linha = Integer.parseInt(partes[0]);
        coluna = Integer.parseInt(partes[1]);
        
        if (linha < 0 || linha > 3 || coluna < 0 || coluna > 3 || tabuleiro[linha][coluna] != ' ') {
            return false;
        }
        
        tabuleiro[linha][coluna] = jogadorAtual;
        return true;
    }

    public char getPosicao(int linha, int coluna) {
        return tabuleiro[linha][coluna];
    }

    public void setPosicao(int linha, int coluna, char jogador) {
        tabuleiro[linha][coluna] = jogador;
    }

    public void alternarJogador() {
        jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
    }

    public boolean verificarVitoria() {
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual) {
                return true;
            }
            if (tabuleiro[0][i] == jogadorAtual && tabuleiro[1][i] == jogadorAtual && tabuleiro[2][i] == jogadorAtual) {
                return true;
            }
        }
        if (tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual) {
            return true;
        }
        if (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual) {
            return true;
        }
        return false;
    }

    public boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public void imprimirTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("-----");
        }
    }

    public char getJogadorAtual() {
        return jogadorAtual;
    }
}