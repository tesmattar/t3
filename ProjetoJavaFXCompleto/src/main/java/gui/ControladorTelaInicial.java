// ControladorTelaInicial.java
package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import modelo.Biblioteca;
import modelo.PdfEntry;
import modelo.Persistencia;
import modelo.GerenciadorDeColecoes;
import modelo.Colecao;
import modelo.Livro;
import modelo.NotaAula;
import modelo.Slide;

import java.io.IOException;
import java.util.List;

public class ControladorTelaInicial {
private Biblioteca biblioteca = new Biblioteca(); 


@FXML
public void initialize() {
    if (SessaoCompartilhada.getBiblioteca() == null) {
        SessaoCompartilhada.setBiblioteca(biblioteca);
    }

    
    if (SessaoCompartilhada.getGerenciador() == null) {
        SessaoCompartilhada.setGerenciador(new GerenciadorDeColecoes());
    }
}



    @FXML
private void handleAdicionarEntrada(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/TelaAdicionarEntrada.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    } catch (Exception e) {
        e.printStackTrace();
        mostrarAlerta("Erro", "Erro ao carregar a tela: " + e.getMessage());
    }
}

private void mostrarAlerta(String titulo, String mensagem) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(titulo);
    alert.setHeaderText(null);
    alert.setContentText(mensagem);
    alert.showAndWait();
}


    @FXML
private void handleListarEntradas(ActionEvent event) {
    try {


        SessaoCompartilhada.setBiblioteca(biblioteca);

        Parent root = FXMLLoader.load(getClass().getResource("/gui/TelaListarEntradas.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    @FXML
    private void handleBuscarPorTitulo(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar por Título");
        dialog.setHeaderText(null);
        dialog.setContentText("Digite o título:");

        dialog.showAndWait().ifPresent(titulo -> {
            Biblioteca biblioteca = SessaoCompartilhada.getBiblioteca();
            PdfEntry resultado = biblioteca.buscarPorTitulo(titulo);
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Resultado da busca");

            if (resultado != null) {
                alerta.setHeaderText("Entrada encontrada:");
                alerta.setContentText(resultado.toString());
            } else {
                alerta.setHeaderText("Entrada não encontrada.");
                alerta.setContentText("Nenhuma entrada com o título \"" + titulo + "\" foi localizada.");
            }

            alerta.showAndWait();
        });
    }

    @FXML
    private void handleBuscarPorAutor(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar por Autor");
        dialog.setHeaderText(null);
        dialog.setContentText("Digite o nome do autor:");

        dialog.showAndWait().ifPresent(autor -> {
            Biblioteca biblioteca = SessaoCompartilhada.getBiblioteca();
            List<PdfEntry> encontrados = biblioteca.buscarPorAutor(autor);

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Resultado da busca");

            if (encontrados.isEmpty()) {
                alerta.setHeaderText("Nenhuma entrada encontrada.");
                alerta.setContentText("Nenhuma entrada com o autor \"" + autor + "\" foi localizada.");
            } else {
                alerta.setHeaderText("Entradas encontradas:");
                StringBuilder conteudo = new StringBuilder();
                encontrados.forEach(e -> conteudo.append(e).append("\n"));
                alerta.setContentText(conteudo.toString());
            }

            alerta.showAndWait();
        });
    }

    @FXML
    private void handleSalvar(ActionEvent event) {
        Biblioteca biblioteca = SessaoCompartilhada.getBiblioteca();
        Persistencia persistencia = new Persistencia();
        persistencia.salvar(biblioteca);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Salvar Biblioteca");
        alerta.setHeaderText(null);
        alerta.setContentText("Biblioteca salva com sucesso.");
        alerta.showAndWait();
    }

    @FXML
    private void handleAlternarBiblioteca(ActionEvent event) {
        Persistencia persistencia = new Persistencia();
        Biblioteca nova = persistencia.carregar();
        SessaoCompartilhada.setBiblioteca(nova);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Biblioteca Atualizada");
        alerta.setHeaderText(null);
        alerta.setContentText("Biblioteca alternada com sucesso.");
        alerta.showAndWait();
    }

    @FXML
    private void handleCriarColecao(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/TelaCriarColecao.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  @FXML
    private void handleAdicionarEntradaColecao(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/TelaAdicionarEntradaColecao.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRemoverEntradaColecao(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/TelaRemoverEntradaColecao.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleExportarBib(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/TelaExportarBib.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleExportarZip(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/TelaExportarZip.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleListarColecoes(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/TelaListarColecoes.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSair(ActionEvent event) {
        System.exit(0);
    }
}
