package com.pedalclecle.lifeplan.service;

/**
 * ライフプランシミュレータのイベント用のインタフェース
 * @author tetsuji0617
 *
 * @param <S> 基準金額(円)
 * @return <T> 計算結果(円)
 */
public interface LifePlanCalcInterface<S, T> {

	T calc(S s);

}
