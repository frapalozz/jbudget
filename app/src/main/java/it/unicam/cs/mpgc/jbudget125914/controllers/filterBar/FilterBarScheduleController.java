package it.unicam.cs.mpgc.jbudget125914.controllers.filterBar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class FilterBarScheduleController extends FilterBarBase {

    @FXML
    private void openNewScheduleDialog() {
        openDialogBuilder("NewScheduleDialog", "New Transaction");
    }

    @FXML
    private void openUpdateScheduleDialog() {
        if(getService().getFilterManager().getSchedule() == null) return;
        /*
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/COMPONENTS/NewScheduleDialog.fxml"));

            Parent root = loader.load();
            ScheduleDialogController controller = loader.getController();

            controller.setCategory(getService().getFilterManager().getSchedule().getTags().stream().findFirst().get().getCategory());
            controller.setDescription(getService().getFilterManager().getSchedule().getDescription());
            controller.setAccount(getService().getFilterManager().getSchedule().getAccount());
            controller.setActiveTags(getService().getFilterManager().getSchedule().getTags());
            controller.setDate(getService().getFilterManager().getSchedule().getDate());
            controller.setAmount(getService().getFilterManager().getSchedule().getAmount().getAmount().toString());
            controller.setApplyButton("Update");

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Update Transaction");
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
            dialog.getDialogPane().setContent(root);

            dialog.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

         */
    }

    @FXML
    private void deleteSchedule() {
        if(getService().getFilterManager().getSchedule() == null) return;

        /*
        try {
            getService().getGeneralManager().getScheduleService().delete(getService().getFilterManager().getSchedule());
            getInfo().setText("Schedule deleted.");
        } catch (Exception e) {
            getInfo().setText("Error while deleting Schedule.");
        }
        getService().getFilterManager().setSchedule(null);
        getService().update();

         */
    }
}
