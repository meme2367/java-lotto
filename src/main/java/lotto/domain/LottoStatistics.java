package lotto.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {

    private Map<LottoRank,Integer> lottoStaticResult;

    public LottoStatistics(Map<LottoRank, Integer> matchingCountResult) {
        this.lottoStaticResult = matchingCountResult;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (LottoRank rank : lottoStaticResult.keySet()) {
            sb.append(rank.getMatchingCount()+"개 일치 (");
            sb.append(rank.getMatchingPrice()+"원) - ");
            sb.append(lottoStaticResult.get(rank)+"개\n");
        }
        return sb.toString().trim();
    }

    private int sum() {
        int sum = 0;
        for (LottoRank rank : lottoStaticResult.keySet()) {
            sum += rank.getMatchingPrice() * lottoStaticResult.get(rank);
        }
        return sum;
    }

    public double calculate(int amount) {
        BigDecimal totalBigDecimal = new BigDecimal(sum());
        BigDecimal amountBigDecimal = new BigDecimal(amount);
        return totalBigDecimal
                .divide(amountBigDecimal,2,BigDecimal.ROUND_UP)
                .doubleValue();
    }
}
