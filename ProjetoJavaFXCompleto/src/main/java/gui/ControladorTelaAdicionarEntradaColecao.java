package gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import modelo.*;

public class ControladorTelaAdicionarEntradaColecao {

    @FXML
    private ComboBox<String> comboColecoes;

    @FXML
    private ComboBox<PdfEntry> comboEntradas;   

    private GerenciadorDeColecoes gerenciador;
    private Biblioteca biblioteca;

    @FXML
    public void initialize() {
        gerenciador = SessaoCompartilhada.getGerenciador();
        biblioteca = SessaoCompartilhada.getBiblioteca();

        comboColecoes.getItems().addAll(gerenciador.getNomesColecoes());
        comboEntradas.getItems().addAll(biblioteca.getEntradas());
    }

    @FXML
    private void handleAdicionar() {
        String nomeColecao = comboColecoes.getValue();
        PdfEntry entrada = comboEntradas.getValue();

        if (nomeColecao == null || entrada == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Selecione uma coleção e uma entrada.");
            return;
        }

        boolean sucesso = gerenciador.adicionarEntrada(nomeColecao, entrada);
        if (sucesso) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Entrada adicionada com sucesso!");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Não foi possível adicionar a entrada.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
