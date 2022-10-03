package modeldto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExpenseDto {
    private Long id;
    private BigDecimal amount;
    private String comment;
    private String category;

    public ExpenseDto(BigDecimal amount, String comment, String category) {
        this.amount = amount;
        this.comment = comment;
        this.category = category;
    }
}
