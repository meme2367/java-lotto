package lotto.controller;

import java.util.ArrayList;
import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.ResultView;
import java.util.List;

public class LottoGameController {

  private InputView inputView;
  private ResultView resultView;
  private LottoMachine lottoMachine;

  public LottoGameController() {
    inputView = new InputView();
    resultView = new ResultView();
    lottoMachine = new LottoMachine();
  }

  public void runGame() {
    int purchasePrice = drawPurchasePrice();

    int lottoCount = lottoMachine.calculateLottoCount(purchasePrice);
    drawLottoCount(lottoCount);

    LottoList lottoList = lottoMachine.autoGeneratedLottoList(lottoCount);
    drawLottoList(lottoList);

    List<Integer> lottoNumberInt = drawWinningLotto();

    List<LottoNumber> lottoNumbers = new ArrayList<>();
    
    for (int lottoNumber : lottoNumberInt) {
      lottoNumbers.add(new LottoNumber(lottoNumber));
    }

    Lotto lastWeekWinningLotto = new Lotto(new LottoNumberList(lottoNumbers));

    LottoStatistics lottoStatistics
        = lottoList.makeMatchingCount(lastWeekWinningLotto);

    drawLottoStatics(lottoStatistics);

    double yield = lottoStatistics.calculate(purchasePrice);

    drawLottoEarningRate(yield, lottoStatistics.isBenefit(yield));
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

  private void drawLottoList(LottoList lottoList) {
    resultView.printLottoList(lottoList);
  }

  private void drawLottoCount(int lottoCount) {
    resultView.printLottoCount(lottoCount);
  }

  private int drawPurchasePrice() {
    return inputView.inputPurchasePrice();
  }
}
