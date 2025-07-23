package gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import modelo.*;

public class ControladorTelaRemoverEntradaColecao {

    @FXML
    private ComboBox<String> comboColecoes;

    @FXML
    private ComboBox<PdfEntry> comboEntradas;

    private GerenciadorDeColecoes gerenciador;

    @FXML
    public void initialize() {
        gerenciador = SessaoCompartilhada.getGerenciador();

        comboColecoes.getItems().addAll(gerenciador.getNomesColecoes());

        // Atualiza entradas ao selecionar coleção
        comboColecoes.setOnAction(e -> atualizarEntradas());
    }

    private void atualizarEntradas() {
        comboEntradas.getItems().clear();
        String nomeColecao = comboColecoes.getValue();
        if (nomeColecao != null) {
            Colecao<? extends PdfEntry> colecao = gerenciador.getColecao(nomeColecao);
            if (colecao != null) {
                comboEntradas.getItems().addAll(colecao.getEntradas());
            }
        }
    }

    @FXML
    private void handleRemover() {
        String nomeColecao = comboColecoes.getValue();
        PdfEntry entrada = comboEntradas.getValue();

        if (nomeColecao == null || entrada == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Selecione uma coleção e uma entrada.");
            return;
        }

        boolean sucesso = gerenciador.removerEntrada(nomeColecao, entrada);
        if (sucesso) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Entrada removida com sucesso!");
            atualizarEntradas();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Não foi possível remover a entrada.");
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
