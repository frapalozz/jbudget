/**
 * MIT License
 * Copyright (c) 2025 Francesco Palozzi
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package it.unicam.cs.mpgc.jbudget125914.controller.charts;

import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;
import it.unicam.cs.mpgc.jbudget125914.controller.BaseController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Labeled;

import java.net.URL;
import java.util.*;

/**
 * This controller is for LineChart.fxml view
 */
public class LineChartController extends BaseController implements Initializable {

    @FXML
    private LineChart<String, Number> chart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    private void addListener() {
        getService().getChanges().addListener((observable, oldValue, newValue) ->
                Platform.runLater(this::updateChart)
        );
    }

    private void updateChart() {
        chart.getData().clear();
        generateChart();
    }

    private void generateChart() {
        XYChart.Series<String, Number> income = new XYChart.Series<>();
        XYChart.Series<String, Number> expenses = new XYChart.Series<>();
        income.setName("Income");
        expenses.setName("Expenses");

        buildSeries(income, expenses);
        addSeries(income, expenses);
        setChartStyle(income, expenses);
    }

    private void buildSeries(XYChart.Series<String, Number> a, XYChart.Series<String, Number> b) {
        if(getService().getFetchManager().getTransactions() == null) return;
        getService().getFetchManager().getChartTransactions().stream()
            .filter(t ->
                    t.getDate().isAfter(getService().getFilterManager().getStartDate().minusDays(1)) &&
                            t.getDate().isBefore(getService().getFilterManager().getEndDate().plusDays(1)))
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

    private void setChartStyle(XYChart.Series<String, Number> income, XYChart.Series<String, Number> expenses) {
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
}
