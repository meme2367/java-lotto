package lotto.domain.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.Lotto;
import lotto.domain.Money;

public class AutoLottoGenerator implements LottoGenerator {

  private static final int START_NUMBER = 1;
  private static final int END_NUMBER = 46;
  private static final int FROM_INDEX = 0;
  private static final int TO_INDEX = 6;

  @Override
  public List<Lotto> generatedLottoList(Money money) {
    List<Lotto> lottoList = new ArrayList<>();
    for (int i = 0; i < money.calculateLottoCount(); i++) {
      lottoList.add(makeLotto());
    }
    return lottoList;
  }

  private Lotto makeLotto() {
    List<Integer> randomNumbers = createSeed();
    Collections.shuffle(randomNumbers);
    return Lotto.of(randomNumbers.subList(FROM_INDEX, TO_INDEX));
  }

  public List<Integer> createSeed() {
    return IntStream.range(START_NUMBER, END_NUMBER)
        .boxed()
        .sorted()
        .collect(Collectors.toList());
  }
}
