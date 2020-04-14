package ru.itis.jlab.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double approximateCost;

    @ManyToMany(mappedBy = "currencies")
    private List<Bank> banks;

    public boolean equalsId(Currency currency) {
        if (currency == null) return false;
        if (this.getId() == currency.getId()) {
            return true;
        }
        return false;
    }
}
