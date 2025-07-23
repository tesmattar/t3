package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import modelo.*;

import java.util.List;
import java.util.stream.Collectors;

public class ControladorListarColecoes {

    @FXML private TableView<Colecao<?>> tabelaColecoes;
    @FXML private TableColumn<Colecao<?>, String> colNome;
    @FXML private TableColumn<Colecao<?>, String> colAutor;
    @FXML private TableColumn<Colecao<?>, String> colTipo;
    @FXML private TableColumn<Colecao<?>, String> colQtd;

    private GerenciadorDeColecoes gerenciador = SessaoCompartilhada.getGerenciador();

    @FXML
    private void initialize() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colTipo.setCellValueFactory(cell -> new SimpleStringProperty(
            cell.getValue().getTipo().getSimpleName()));
        colQtd.setCellValueFactory(cell -> new SimpleStringProperty(
            String.valueOf(cell.getValue().getEntradas().size()))
        );

        
     List<Colecao<?>> lista = gerenciador.getTodas().stream()
            .map(c -> (Colecao<?>) c)
            .collect(Collectors.toList());


        tabelaColecoes.getItems().addAll(lista);
    }

    @FXML
    private void handleVoltar(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/TelaInicial.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
