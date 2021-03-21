package lotto.domain;

import java.util.HashSet;
import java.util.List;
import lotto.domain.generator.LottoGenerator;

public class LottoMachine {

  private final List<Lotto> lottoList;
  private static final String DUPLICATED_LOTTO = "중복된 로또는 발급할 수 없습니다.";

  public LottoMachine(Money money, LottoGenerator lottoGenerator) {
    this.lottoList = lottoGenerator.generatedLottoList(money);
    validateDuplicated(lottoList);
  }

  public LottoMachine(List<Lotto> lottos) {
    validateDuplicated(lottos);
    this.lottoList = lottos;
  }

  public void validateDuplicated(List<Lotto> lottoList) {
    boolean isNotDuplicated = lottoList.stream()
        .allMatch(new HashSet<>()::add);

    if (!isNotDuplicated) {
      throw new IllegalArgumentException(DUPLICATED_LOTTO);
    }
  }

  public LottoStaticResult makeMatchingCount(LastWinningLotto lastWeekWinningLotto) {
    LottoStaticResult lottoStaticResult = new LottoStaticResult();
    for (Lotto lotto : lottoList) {
      lottoStaticResult.put(lastWeekWinningLotto.match(lotto));
    }
    return lottoStaticResult;
  }

  public int getLottoCount() {
    return lottoList.size();
  }

  public List<Lotto> getLottoList() {
    return lottoList;
  }
}
