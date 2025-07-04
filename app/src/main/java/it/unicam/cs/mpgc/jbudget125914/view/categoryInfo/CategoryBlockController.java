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

package it.unicam.cs.mpgc.jbudget125914.view.categoryInfo;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.controller.manager.FinancialServiceManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * This class is the controller of CategoryBlock.fxml view
 */
public class CategoryBlockController implements Initializable {

    @FXML
    private VBox income;
    @FXML
    private VBox expenses;

    @FXML
    private Label catDate1;
    @FXML
    private Label catDate2;

    @FXML
    private PieChart chartIncome;
    @FXML
    private PieChart chartExpenses;

    private final FinancialServiceManager service = FinancialServiceManager.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateCategoryBlocks();

        service.getChanges().addListener((observable, oldValue, newValue) ->
            Platform.runLater(() -> {
                income.getChildren().clear();
                expenses.getChildren().clear();
                chartIncome.getData().clear();
                chartExpenses.getData().clear();

                generateCategoryBlocks();
            })
        );
    }

    private void generateCategoryBlocks() {
        List<Map<FinancialCategory, FinancialAmount>> categoryBalance = service.getFetchManager().getCategoryBalance();
        if(categoryBalance != null &&!categoryBalance.isEmpty()) {
            addValues(income, categoryBalance.getFirst(), chartIncome);
            addValues(expenses, categoryBalance.getLast(), chartExpenses);
        }

        setDate(catDate1);
        setDate(catDate2);
    }

    private void setDate(Label cat) {
        cat.setText(service.getFilterManager().getStartDate() + " | " + service.getFilterManager().getEndDate());
    }

    private void addValues(VBox table, Map<FinancialCategory, FinancialAmount> map, PieChart chart) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        map.forEach((key, value) -> {
            table.getChildren().add(generateLine(key.getName(), value));
            pieChartData.add(new PieChart.Data(key.getName(), value.getAmount().doubleValue()));
        });
        chart.setData(pieChartData);
    }

    private Parent generateLine(String categoryName, FinancialAmount amount) {
        FXMLLoader categoryLine = new FXMLLoader(getClass().getResource("/COMPONENTS/CategoryLine.fxml"));

        try {
            Parent line = categoryLine.load();
            CategoryLineController controller = categoryLine.getController();

            controller.setLabel(categoryName);
            controller.setAmount(amount.toString());

            return line;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
