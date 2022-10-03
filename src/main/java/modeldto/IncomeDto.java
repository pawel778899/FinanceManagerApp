package modeldto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class IncomeDto {
    private Long id;
    private BigDecimal amount;
    private String comment;

    private LocalDate date;

    public IncomeDto(BigDecimal amount, String comment) {
        this.amount = amount;
        this.comment = comment;
    }
}
