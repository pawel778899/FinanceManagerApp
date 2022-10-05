package modeldto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrintIncomeDto {
    private Long id;
    private String amount;
    private String comment;
    private String incomeAddDate;
}
