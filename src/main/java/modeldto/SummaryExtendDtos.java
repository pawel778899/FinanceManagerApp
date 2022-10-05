package modeldto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SummaryExtendDtos {
    private List<SummaryExpenseDto> summaryExpanseDtoList = new ArrayList<>();
}
