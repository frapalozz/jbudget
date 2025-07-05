package it.unicam.cs.mpgc.jbudget125914;

import it.unicam.cs.mpgc.jbudget125914.controller.dao.GroupDAO;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.FinancialGroup;

public class Main {

    public static void main(String[] args) {
        GroupDAO<FinancialGroup> dao = new GroupDAO<>(FinancialGroup.class);

        dao.delete(dao.findById(20L).getGroupId());
    }
}
