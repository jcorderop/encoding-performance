package com.jc.encoding.builder;

import com.jc.model.dto.TradeDto;

public interface Converter<T> {
    <T> T newTrade(TradeDto trades);
    byte[] toBytes(T t);
    <T> T fromBytes(byte[] bytes);
}
