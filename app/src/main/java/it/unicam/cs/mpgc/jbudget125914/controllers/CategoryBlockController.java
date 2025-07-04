package it.unicam.cs.mpgc.jbudget125914.controllers;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.FinancialServiceManager;
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

        service.getChanges().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                income.getChildren().clear();
                expenses.getChildren().clear();
                chartIncome.getData().clear();
                chartExpenses.getData().clear();

                generateCategoryBlocks();
            });
        });
    }

    private void generateCategoryBlocks() {
        List<Map<FinancialCategory, FinancialAmount>> categoryBalance = service.getFetchManager().getCategoryBalance();
        if(categoryBalance != null &&!categoryBalance.isEmpty()) {
            addValues(income, categoryBalance.getFirst(), chartIncome);
            addValues(expenses, categoryBalance.getLast(), chartExpenses);
        }

        catDate1.setText(service.getFilterManager().getStartDate().toString() + " | " + service.getFilterManager().getEndDate().toString());
        catDate2.setText(service.getFilterManager().getStartDate().toString() + " | " + service.getFilterManager().getEndDate().toString());
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
        FXMLLoader categoryLine = new FXMLLoader(getClass().getResource("/COMPONENTS/categoryline.fxml"));

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
