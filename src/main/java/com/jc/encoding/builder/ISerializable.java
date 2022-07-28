package com.jc.encoding.builder;

import com.jc.model.dto.TradeDto;

import java.util.List;

public interface ISerializable<T> {
    <T> T newTrade(TradeDto trades);
    byte[] toBytes(T t);
    <T> T fromBytes(byte[] bytes);
}
