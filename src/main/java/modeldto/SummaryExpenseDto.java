package modeldto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SummaryExpenseDto {
    private String categoryName;
    private BigDecimal totalAmount;
    private Long numberOfExpanse;
}
