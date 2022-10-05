package modeldto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class SummaryDto {
    private Set<PrintIncomeDto> incomes;
    private Set<PrintExpenseDto> expenses;
}
