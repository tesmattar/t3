package gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modelo.*;

public class ControladorExportarBib {

    @FXML private TextField campoNomeColecao;
    @FXML private TextField campoCaminhoBib;

    @FXML
    private void handleExportar() {
        String nome = campoNomeColecao.getText();
        String caminho = campoCaminhoBib.getText();

        try {
            Colecao<?> col = SessaoCompartilhada.getGerenciador().buscarColecao(nome);
            if (col == null) {
                mostrarErro("Coleção não encontrada.");
                return;
            }

            if (!col.getTipo().equals(Livro.class)) {
                mostrarErro("A coleção não é composta por livros.");
                return;
            }

            @SuppressWarnings("unchecked")
            Colecao<Livro> livros = (Colecao<Livro>) col;
            livros.exportarBibTex(caminho);
            mostrarInfo("Exportado com sucesso!");
            fecharJanela();

        } catch (Exception e) {
            mostrarErro("Erro ao exportar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleVoltar() {
        fecharJanela();
    }

    private void mostrarErro(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }

    private void mostrarInfo(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }

    private void fecharJanela() {
        Stage stage = (Stage) campoNomeColecao.getScene().getWindow();
        stage.close();
    }
}