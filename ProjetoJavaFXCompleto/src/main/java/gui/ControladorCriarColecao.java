package gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modelo.*;

public class ControladorCriarColecao {

    @FXML private TextField campoNomeColecao;
    @FXML private TextField campoAutor;
    @FXML private TextField campoLimite;    
    @FXML private ChoiceBox<String> choiceTipo;

    @FXML
    private void initialize() {
        choiceTipo.setItems(FXCollections.observableArrayList("Livro", "NotaAula", "Slide"));
    }

   @FXML
private void handleCriar() {
    String nome = campoNomeColecao.getText();
    String autor = campoAutor.getText();
    String tipoSelecionado = choiceTipo.getValue();

    if (nome.isBlank() || autor.isBlank() || tipoSelecionado == null) {
        mostrarErro("Todos os campos devem ser preenchidos.");
        return;
    }

    int limite;
    try {
        limite = Integer.parseInt(campoLimite.getText());
        if (limite <= 0) {
            mostrarErro("O limite deve ser maior que zero.");
            return;
        }
    } catch (NumberFormatException e) {
        mostrarErro("Limite inválido. Digite um número.");
        return;
    }

    Class<? extends PdfEntry> tipo = switch (tipoSelecionado) {
        case "Livro" -> Livro.class;
        case "NotaAula" -> NotaAula.class;
        case "Slide" -> Slide.class;
        default -> null;
    };

    boolean criado = SessaoCompartilhada.getGerenciador().criarColecao(nome, autor, limite, tipo);
    if (criado) {
        mostrarInfo("Coleção criada com sucesso!");
        fecharJanela();
    } else {
        mostrarErro("Não foi possível criar a coleção. Já existe uma com esse nome ou autor inválido.");
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
