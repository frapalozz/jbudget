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

package it.unicam.cs.mpgc.jbudget125914.controller.filterBar;

import it.unicam.cs.mpgc.jbudget125914.controller.dialogs.ScheduleDialogController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

/**
 * This controller is used for FilterBarSchedule.fxml view
 */
public class FilterBarScheduleController extends FilterBarBase {

    /**
     * New schedule event handler
     */
    @FXML
    private void openNewScheduleDialog() {
        if(getInfo().getText().equals("Loading...")) return;
        openDialogBuilder("dialogs/NewScheduleDialog", "New Transaction");
    }

    /**
     * update schedule event handler
     */
    @FXML
    private void openUpdateScheduleDialog() {
        if(getService().getFilterManager().getSchedule() == null) return;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/COMPONENTS/dialogs/NewScheduleDialog.fxml"));

            Parent root = loader.load();
            ScheduleDialogController controller = loader.getController();
            setData(controller);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Update Schedule");
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
            dialog.getDialogPane().setContent(root);
            controller.setStage((Stage) dialog.getDialogPane().getScene().getWindow());

            dialog.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Delete schedule event handler
     */
    @FXML
    private void deleteSchedule() {
        if(getService().getFilterManager().getSchedule() == null) return;

        try {
            getService().getRepositoryManager().getScheduleRepository().delete(getService().getFilterManager().getSchedule().getTransactionId());
            getInfo().setText("Schedule deleted.");
        } catch (Exception e) {
            getInfo().setText("Error while deleting Schedule.");
        }
        getService().getFilterManager().setSchedule(null);
        getService().update();
    }

    private void setData(ScheduleDialogController controller) {

        if(getService().getFilterManager().getSchedule().getTags().stream().findFirst().isPresent())
            controller.setCategory(getService().getFilterManager().getSchedule().getTags().stream().findFirst().get().getCategory());
        controller.setDescription(getService().getFilterManager().getSchedule().getDescription());
        controller.setAccount(getService().getFilterManager().getSchedule().getAccount());
        controller.setActiveTags(getService().getFilterManager().getSchedule().getTags());
        controller.setDate(getService().getFilterManager().getSchedule().getDate());
        controller.setAmount(getService().getFilterManager().getSchedule().getAmount().getAmount().toString());
        controller.setApplyButton("Update");
    }
}
