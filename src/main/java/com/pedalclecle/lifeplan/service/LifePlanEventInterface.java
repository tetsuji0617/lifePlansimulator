package com.pedalclecle.lifeplan.service;

/**
 * ライフ8プランシミュレータのイベント用のインタフェース
 * @author ttjsp
 *
 * @param <S> イベント用のインプット
 */
public interface LifePlanEventInterface<S> {

	void calc(S s);

}
