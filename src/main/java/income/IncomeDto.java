package income;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncomeDto {
    private Long id;
    private BigDecimal amount;
    private String comment;

    public IncomeDto(BigDecimal amount, String comment) {
        this.amount = amount;
        this.comment = comment;
    }
}
