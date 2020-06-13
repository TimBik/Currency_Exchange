package ru.itis.jlab.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "edge_currency")
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

    @Column(name = "url_from_data")
    private String urlFromData;

    @Column(name = "parsing_xpath")
    private String parsingXPath;


    private Boolean reverse;

    //подумать не может ли привести к микроошибкам
    //поменять на более точный инструмент
    @Column(name = "cost_by_one")
    private Double costByOne;

    @Column(name = "log_cost_by_one")
    private Double logCostByOne;

    public boolean equalsId(EdgeCurrency edgeCurrency) {
        if (edgeCurrency == null) return false;
        if (this.getId() == edgeCurrency.getId()) {
            return true;
        }
        return false;
    }
}
