package expense;

import category.Category;
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

    @ManyToOne(cascade = CascadeType.ALL) // dużo wydatków do 1 pozycji np. transport
    @JoinColumn(name = "category_id")

    private Category category;

    public Expense(BigDecimal amount, String comment, Category category) {
        this.amount = amount;
        this.comment = comment;
        this.category = category;
        this.expenseAddDate = LocalDate.now();
    }

}
