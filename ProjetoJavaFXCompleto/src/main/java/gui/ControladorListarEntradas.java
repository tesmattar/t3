package gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modelo.Biblioteca;
import modelo.PdfEntry;

public class ControladorListarEntradas {

    @FXML private TableView<PdfEntry> tabelaEntradas;
    @FXML private TableColumn<PdfEntry, String> colTitulo;
    @FXML private TableColumn<PdfEntry, String> colAutores;
    @FXML private TableColumn<PdfEntry, String> colArquivo;

    @FXML
    private void initialize() {
        colTitulo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
        colAutores.setCellValueFactory(cellData -> new SimpleStringProperty(String.join(", ", cellData.getValue().getAutores())));
        colArquivo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCaminhoPDF()));

        Biblioteca bib = SessaoCompartilhada.getBiblioteca();
        tabelaEntradas.setItems(FXCollections.observableArrayList(bib.getEntradas()));
    }

    @FXML
    private void handleVoltar() {
        Stage stage = (Stage) tabelaEntradas.getScene().getWindow();
        stage.close();
    }
}
