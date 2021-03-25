package lotto.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoStaticResult {

  private final Map<LottoRank, Integer> lottoStaticResult;
  private static final int SCALE_VALUE = 2;
  private static final int ZERO_VALUE = 0;
  private static final int BENEFIT_CRETERION = 1;

  public LottoStaticResult() {
    this(new HashMap<>());
  }

  public LottoStaticResult(Map<LottoRank,Integer> lottoStaticResult) {
    this.lottoStaticResult = lottoStaticResult;
    init();
  }

  private void init() {
    for (LottoRank lottoRank : LottoRank.values()) {
      lottoStaticResult.put(lottoRank, ZERO_VALUE);
    }
  }

  public void put(LottoRank rank) {
    if (!LottoRank.isZero(rank)) {
      lottoStaticResult.put(rank, lottoStaticResult.get(rank) + 1);
    }
  }

  private int sum() {
    int sum = 0;
    for (LottoRank rank : lottoStaticResult.keySet()) {
      sum += rank.getPrice() * lottoStaticResult.get(rank);
    }
    return sum;
  }

  public double calculate(int amount) {
    BigDecimal totalBigDecimal = new BigDecimal(sum());
    BigDecimal amountBigDecimal = new BigDecimal(amount);
    return totalBigDecimal
        .divide(amountBigDecimal, SCALE_VALUE, BigDecimal.ROUND_DOWN)
        .doubleValue();
  }

  public boolean isBenefit(double yield) {
    if (yield < BENEFIT_CRETERION) {
      return false;
    }
    return true;
  }

  public Map<LottoRank, Integer> getlottoStaticResultMap() {
    return lottoStaticResult;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LottoStaticResult that = (LottoStaticResult) o;
    return Objects.equals(lottoStaticResult, that.lottoStaticResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lottoStaticResult);
  }

}
