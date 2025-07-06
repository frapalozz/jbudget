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

package it.unicam.cs.mpgc.jbudget125914.view.filterBar;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FilterBarController for FilterBar.fxml view
 */
public class FilterBarController extends FilterBarBase implements Initializable {

    /**
     * Open new group dialog handler
     */
    @FXML
    public void openDialogGroup() {
        if(getInfo().getText().equals("Loading...")) return;
        openDialogBuilder("dialogs/NewGroupDialog", "New Group");
        getSelectGroup().setValue(getService().getFilterManager().getGroup());
    }

    /**
     * Delete group event handler
     */
    @FXML
    public void deleteGroup() {
        if(getInfo().getText().equals("Loading...") || getService().getFilterManager().getGroup() == null) return;
        try {
            getService().getDaoManager().getGroupDAO().delete(getService().getFilterManager().getGroup().getGroupId());
            getInfo().setText(getService().getFilterManager().getGroup().getName()+"'s group has been deleted.");
        } catch (Exception e) {
            getInfo().setText("Error while deleting group.");
        }
        getService().getFilterManager().setGroup(null);
        getService().update();
    }
}
