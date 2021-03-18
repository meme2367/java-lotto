package lotto.controller;

import java.util.ArrayList;
import lotto.domain.*;
import lotto.domain.generator.AutoLottoGenerator;
import lotto.view.InputView;
import lotto.view.ResultView;
import java.util.List;

public class LottoGameController {

  private final InputView inputView;
  private final ResultView resultView;

  public LottoGameController() {
    inputView = new InputView();
    resultView = new ResultView();
  }

  public void runGame() {
    int purchasePrice = drawPurchasePrice();
    Money money = new Money(purchasePrice);
    int lottoCount = money.calculateLottoCount();
    drawLottoCount(lottoCount);

    AutoLottoGenerator autoLottoGenerator = new AutoLottoGenerator();
    LottoMachine thisWeekLottoList = new LottoMachine(money,autoLottoGenerator);
    drawLottoList(thisWeekLottoList);

    LastWinningLotto lastWeekWinningLotto = LastWinningLotto.of(drawWinningLotto(),drawBonusBall());

    LottoStatistics lottoStatistics
        = thisWeekLottoList.makeMatchingCount(lastWeekWinningLotto);
    drawLottoStatics(lottoStatistics);

    double yield = lottoStatistics.calculate(purchasePrice);
    drawLottoEarningRate(yield, lottoStatistics.isBenefit(yield));
  }

  private int drawBonusBall() {
    return inputView.inputBonusBall();
  }

  private void drawLottoEarningRate(double earningRate, boolean isBenefit) {
    resultView.printLottoEarningRate(earningRate, isBenefit);
  }

  private List<Integer> drawWinningLotto() {
    return inputView.inputWinningLottoNumbers();
  }

  private void drawLottoStatics(LottoStatistics lottoStatistics) {
    resultView.printLottoStatics(lottoStatistics);
  }

  private void drawLottoList(LottoMachine lottoList) {
    resultView.printLottoList(lottoList);
  }

  private void drawLottoCount(int lottoCount) {
    resultView.printLottoCount(lottoCount);
  }

  private int drawPurchasePrice() {
    return inputView.inputPurchasePrice();
  }
}
