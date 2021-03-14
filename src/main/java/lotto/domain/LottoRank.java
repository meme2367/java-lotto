package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 1_500_000),
    THIRD(4, 50_000),
    FOURTH(3, 5_000),
    ZERO(0,0);

    private int matchingCount;
    private int matchingPrice;

    LottoRank (int matchingCount, int matchingPrice) {
        this.matchingCount = matchingCount;
        this.matchingPrice = matchingPrice;
    }

    public static boolean isMiss(LottoRank rank) {
        return rank.getMatchingCount() == 0;
    }

    public int getMatchingCount() {
        return matchingCount;
    }

    public int getMatchingPrice() {
        return matchingPrice;
    }

    public static LottoRank findByMatchingCount(int matchingCount) {

        return Arrays.stream(values())
                .filter(r -> r.match(matchingCount))
                .findFirst()
                .orElse(ZERO);
    }

    private boolean match(int matchingCount) {
        return this.matchingCount == matchingCount;
    }

    public static boolean isZero(LottoRank rank) {
        return rank.match(0);
    }
}
