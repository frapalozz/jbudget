package it.unicam.cs.mpgc.jbudget125914.models.services.manager;

import javafx.beans.property.SimpleIntegerProperty;
import lombok.NonNull;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface ServiceManager<T, A, C, G, TA, D, AC, TAGS, TR, CA, AM> {

    SimpleIntegerProperty getChanges();
    void setChanges(int changes);

    Long getGroupId();


    void setGroupId(@NonNull Long groupId);

    D getStartDate();
    void setStartDate(@NonNull D startDate);

    D getEndDate();
    void setEndDate(@NonNull D endDate);

    Set<AC> getAccounts();
    void setAccounts(Set<AC> account);

    Set<TAGS> getTags();
    void setTags(Set<TAGS> tags);

    List<TR> getTransactions();
    void setTransactions(List<TR> transactions);
    void updateTransactions();

    List<Map<CA, AM>> getCategoryBalance();
    void setCategoryBalance(List<Map<CA, AM>> categories);
    void updateCategoryBalance();


    T getTransactionService();

    void setTransactionService(@NonNull T transactionService);

    A getAccountService();

    void setAccountService(@NonNull A accountService);

    C getCategoryService();

    void setCategoryService(@NonNull C categoryService);

    G getGroupService();

    void setGroupService(@NonNull G groupService);

    TA getTagService();

    void setTagService(@NonNull TA tagService);
}
