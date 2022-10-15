package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Account;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "comment")
    private String comment;

    @Column(name = "income_add_date")
    private LocalDate incomeAddDate;

    public Income(BigDecimal amount, String comment) {
        this.amount = amount;
        this.comment = comment;
        this.incomeAddDate = LocalDate.now();
    }

    public Income(BigDecimal amount, String comment, Account account) {
        this.amount = amount;
        this.comment = comment;
        this.account = account;
        this.incomeAddDate = LocalDate.now();
    }

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
