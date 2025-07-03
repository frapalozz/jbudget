package it.unicam.cs.mpgc.jbudget125914.models.services.manager;

import javafx.beans.property.SimpleIntegerProperty;
import lombok.NonNull;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface ServiceManager<D, AC, TAGS, TR, CA, AM, G> {

    SimpleIntegerProperty getChanges();
    void setChanges(int changes);

    G getGroup();

    void setGroup(@NonNull G groupId);

    D getStartDate();
    void setStartDate(@NonNull D startDate);

    D getEndDate();
    void setEndDate(@NonNull D endDate);

    Set<AC> getAccounts();
    Set<AC> getGroupAccounts();
    void setAccounts(Set<AC> account);

    Set<TAGS> getTags();
    Set<TAGS> getGroupTags();
    void setTags(Set<TAGS> tags);

    void update();

    TR getTransaction();
    void setTransaction(TR transaction);

    List<TR> getTransactions();

    List<Map<CA, AM>> getCategoryBalance();
}
