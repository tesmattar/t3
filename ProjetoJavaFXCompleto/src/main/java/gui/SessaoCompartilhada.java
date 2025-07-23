package gui;

import modelo.Biblioteca;
import modelo.GerenciadorDeColecoes;



public class SessaoCompartilhada {
    private static Biblioteca biblioteca;
    private static GerenciadorDeColecoes gerenciador;

    public static Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public static void setBiblioteca(Biblioteca b) {
        biblioteca = b;
    }

    public static GerenciadorDeColecoes getGerenciador() {
        return gerenciador;
    }

    public static void setGerenciador(GerenciadorDeColecoes g) {
        gerenciador = g;
    }
}
