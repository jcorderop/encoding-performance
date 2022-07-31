package com.jc.encoding.builder;

import com.jc.model.dto.TradeDto;

public interface TradeConverter<T> {
    <T> T newTrade(TradeDto trades);
    byte[] toBytes(T t);
    <T> T fromBytes(byte[] bytes);
}
