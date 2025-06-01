package it.unicam.cs.mpgc.jbudget125914.model.category;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @Column(length = 20)
    private String category_name;

    @ManyToOne
    @JoinColumn(name = "category_name")
    private Category parent;
}
