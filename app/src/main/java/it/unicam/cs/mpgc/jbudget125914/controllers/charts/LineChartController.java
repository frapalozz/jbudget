package it.unicam.cs.mpgc.jbudget125914.controllers.charts;

import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.FinancialServiceManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Labeled;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class LineChartController implements Initializable {

    @FXML
    private LineChart<String, Number> chart;

    private final FinancialServiceManager service = FinancialServiceManager.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        service.getChanges().addListener((observable, oldValue, newValue) ->
            Platform.runLater(() -> {
                chart.getData().clear();
                generateChart();
            })
        );
        generateChart();
    }

    private void generateChart() {
        XYChart.Series<String, Number> income = new XYChart.Series<>();
        XYChart.Series<String, Number> expenses = new XYChart.Series<>();
        income.setName("Income");
        expenses.setName("Expenses");

        buildSeries(income, expenses);
        addSeries(income, expenses);
        income.getNode().setStyle("-fx-stroke: #89b4fa; -fx-background-color: #89b4fa;");
        expenses.getNode().setStyle("-fx-stroke: #f38ba8");
        setColor("#89b4fa, #1e1e2e", income);
        setColor("#f38ba8, #1e1e2e", expenses);

        Parent legend = (Parent) chart.lookup(".chart-legend");
        if(legend != null) {
            legend.getChildrenUnmodifiable().forEach(node -> {
                if(node instanceof Labeled labeled) {
                    if(labeled.getText().equals("Income")) {
                        labeled.setGraphic(new javafx.scene.shape.Circle(5, javafx.scene.paint.Color.rgb(137, 180, 250)));
                    } else
                        labeled.setGraphic(new javafx.scene.shape.Circle(5, javafx.scene.paint.Color.rgb(243, 139, 168)));
                }
            });
        }

    }

    private void buildSeries(XYChart.Series<String, Number> a, XYChart.Series<String, Number> b) {
        if(service.getFetchManager().getTransactions() == null) return;
        service.getFetchManager().getTransactions().stream()
            .filter(t ->
                    t.getDate().isAfter(service.getFilterManager().getStartDate().minusDays(1)) &&
                            t.getDate().isBefore(service.getFilterManager().getEndDate().plusDays(1)))
            .sorted(Comparator.comparing(FinancialTransaction::getDate))
            .forEach(t -> {
                XYChart.Data<String, Number> newNode = new XYChart.Data<>(t.getDate().toString(), t.getAmount().getAmount().abs());
                if(t.getAmount().signum() > 0) {
                    a.getData().add(newNode);
                } else {
                    b.getData().add(newNode);
                }
            });
    }

    private void addSeries(XYChart.Series<String, Number> a, XYChart.Series<String, Number> b) {
        if(!a.getData().isEmpty() && !b.getData().isEmpty() && a.getData().getFirst().getXValue().compareTo(b.getData().getFirst().getXValue()) > 0) {
            chart.getData().add(b);
            chart.getData().add(a);
        } else{
            chart.getData().add(a);
            chart.getData().add(b);
        }
    }

    private void setColor(String color, XYChart.Series<String, Number> series) {
        for(XYChart.Data<String, Number> data: series.getData()) {
            data.getNode().setStyle("-fx-background-color: " + color);
        }
    }
}
