package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.Parent;

public class ControladorAdicionarEntrada {

    @FXML private ChoiceBox<String> choiceTipo;
    @FXML private TextField campoAutores;
    @FXML private TextField campoTitulo;
    @FXML private TextField campoSubtitulo;
    @FXML private TextField campoPDF;

    @FXML
    private void handleConfirmar() {
        String tipo = choiceTipo.getValue();
        String autores = campoAutores.getText();
        String titulo = campoTitulo.getText();
        String subtitulo = campoSubtitulo.getText();
        String pdf = campoPDF.getText();

        if (tipo == null || autores.isEmpty() || titulo.isEmpty() || pdf.isEmpty()) {
            mostrarAlerta("Erro", "Preencha todos os campos obrigatórios.");
            return;
        }

        
        mostrarAlerta("Sucesso", "Entrada adicionada com sucesso!");
    }

    @FXML
    private void handleVoltar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/TelaInicial.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            mostrarAlerta("Erro", "Erro ao voltar para tela inicial: " + e.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
