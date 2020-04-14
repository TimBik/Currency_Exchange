package ru.itis.jlab.model;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "")
public class EdgeCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "currency_from_id")
    private Currency CurrencyFrom;

    @ManyToOne
    @JoinColumn(name = "currency_to_id")
    private Currency CurrencyTo;

    private String urlFromData;
    private String parsingXPath;

    //подумать не может ли привести к микроошибкам
    //поменять на более точный инструмент
    private double costByOne;

    public boolean equalsId(EdgeCurrency edgeCurrency) {
        if (edgeCurrency == null) return false;
        if (this.getId() == edgeCurrency.getId()) {
            return true;
        }
        return false;
    }
}
