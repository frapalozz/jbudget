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

package it.unicam.cs.mpgc.jbudget125914.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Main controller for Main.fxml view
 */
public class MainController extends BaseController implements Initializable {

    @FXML
    private VBox dashboard;
    @FXML
    private VBox transactions;
    @FXML
    private VBox scheduler;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getService().update();

        generateDashboard();
        generateTransactions();
        generateScheduler();
    }

    @FXML
    private void generateDashboard() {

        addHorizontalDivider(dashboard);
        buildBlock("infoBlocks/InfoBlock", dashboard);
        addHorizontalDivider(dashboard);
        buildBlock("filterBar/FilterBar", dashboard);
        addHorizontalDivider(dashboard);
        buildBlock("charts/LineChart", dashboard);
        addHorizontalDivider(dashboard);
        buildBlock("categoryInfo/CategoryBlock", dashboard);
    }

    @FXML
    private void generateTransactions() {
        addHorizontalDivider(transactions);
        buildBlock("filterBar/FilterBarTransactions", transactions);
        addHorizontalDivider(transactions);
        buildBlock("tables/TransactionTable", transactions);
    }

    @FXML
    private void generateScheduler() {
        addHorizontalDivider(scheduler);
        buildBlock(("filterBar/FilterBarSchedule"), scheduler);
        addHorizontalDivider(scheduler);
        buildBlock("tables/ScheduleTable", scheduler);
    }

    private void addHorizontalDivider(VBox vbox) {
        Pane pane = new Pane();
        pane.setPrefWidth(Double.MAX_VALUE);
        pane.setMaxWidth(Double.MAX_VALUE);
        pane.setPrefHeight(1);
        pane.setMinHeight(1);
        pane.setStyle("-fx-background-color: white;");
        pane.setOpacity(0.25);
        vbox.getChildren().add(pane);
    }

    private void buildBlock(String filePath, VBox box) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/COMPONENTS/"+filePath+".fxml"));
        try {
            Parent root = loader.load();
            box.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
