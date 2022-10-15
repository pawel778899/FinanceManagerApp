package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "comment")
    private String comment;

    @Column(name = "expense_add_date")
    private LocalDate expenseAddDate;

    @ManyToOne // dużo wydatków do 1 pozycji np. transport
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Expense(BigDecimal amount, String comment, Category category, Account account) {
        this.amount = amount;
        this.comment = comment;
        this.category = category;
        this.account = account;
        this.expenseAddDate = LocalDate.now();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
    }

    public Category getCategory() {
        return category;
    }
}
