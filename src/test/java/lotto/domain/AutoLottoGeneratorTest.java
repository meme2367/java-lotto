package lotto.domain;

import lotto.domain.generator.AutoLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoLottoGeneratorTest {

  @Test
  @DisplayName("로또 자동 생산")
  public void autoGeneratedLottoList() {
    AutoLottoGenerator autoLottoGenerator = new AutoLottoGenerator();
    Money money = new Money(14000);
    LottoList lottoList = autoLottoGenerator.autoGeneratedLottoList(money);
    assertEquals(lottoList.getLottoCount(), 14);
  }



  @Test
  @DisplayName("랜덤으로 만든 로또 넘버가 1~45 사이의 숫자인지 확인")
  public void makeLottoNumber() {
    AutoLottoGenerator autoLottoGenerator = new AutoLottoGenerator();
    assertThat(autoLottoGenerator.createSeed().get(0)).isBetween(1,45);
    assertThat(autoLottoGenerator.createSeed().get(1)).isBetween(1,45);
    assertThat(autoLottoGenerator.createSeed().get(2)).isBetween(1,45);
    assertThat(autoLottoGenerator.createSeed().get(3)).isBetween(1,45);
    assertThat(autoLottoGenerator.createSeed().get(4)).isBetween(1,45);
    assertThat(autoLottoGenerator.createSeed().get(5)).isBetween(1,45);

  }

}
