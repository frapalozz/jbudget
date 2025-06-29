package it.unicam.cs.mpgc.jbudget125914.models.entities.group;

import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.Tag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "group_table")
public class FinancialGroup implements Group<FinancialTag, FinancialCategory, FinancialAccount, String>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    @Setter
    private String name;

    @Setter
    private String currency;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "group_tag_relationship",
            joinColumns = @JoinColumn(name = "groupid"),
            inverseJoinColumns = @JoinColumn(name = "tagid")
    )
    @Setter
    private Set<FinancialTag> tags;

    @OneToMany(mappedBy = "group")
    @Setter
    private Set<FinancialAccount> accounts;

    @ManyToMany
    @JoinTable(
            name = "group_category_relationship",
            joinColumns = @JoinColumn(name = "groupid"),
            inverseJoinColumns = @JoinColumn(name = "categoryid")
    )
    @Setter
    private Set<FinancialCategory> categories;

    FinancialGroup(String groupName, String currency, Set<FinancialAccount> accounts) {

        this.name = groupName;
        this.currency = currency;
        this.accounts = accounts;
    }
}
