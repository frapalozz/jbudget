package it.unicam.cs.mpgc.jbudget125914.models.services.manager;

import lombok.NonNull;

public interface ServiceManager<T, A, C, G, TA> {

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
