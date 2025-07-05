package it.unicam.cs.mpgc.jbudget125914;

import it.unicam.cs.mpgc.jbudget125914.controller.dao.GeneralDAO;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.FinancialGroup;

public class Main {

    public static void main(String[] args) {
        GeneralDAO<FinancialGroup> dao = new GeneralDAO<>(FinancialGroup.class);

        dao.delete(dao.findById(3L).getGroupId());
    }
}
