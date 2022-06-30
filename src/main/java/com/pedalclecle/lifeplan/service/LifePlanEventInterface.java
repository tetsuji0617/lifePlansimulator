package com.pedalclecle.lifeplan.service;

/**
 * ライフプランシミュレータのイベント用のインタフェース
 * @author tetsuji0617
 *
 * @param <S> イベント用のインプット
 */
public interface LifePlanEventInterface<S> {

	void calc(S s);

}
