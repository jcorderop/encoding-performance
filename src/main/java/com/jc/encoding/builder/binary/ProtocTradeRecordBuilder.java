package com.jc.encoding.builder.binary;

import com.jc.encoding.builder.ISerializable;
import com.jc.model.dto.TradeDto;
import com.jc.model.proto.Trade;
import com.jc.model.proto.TradeProtoc;

import java.io.IOException;
import java.io.UncheckedIOException;

public class ProtocTradeRecordBuilder implements ISerializable<TradeProtoc>  {

    @Override
    public TradeProtoc newTrade(final TradeDto tradeDto) {
        return TradeProtoc.newBuilder()
                .setTradeId(tradeDto.getTradeId())
                .setCustomerId(tradeDto.getCustomerId())
                .setExchange(tradeDto.getExchange())
                .setTradeType(TradeProtoc.TradeType.valueOf(tradeDto.getTradeType().name()))
                .setSymbol(tradeDto.getSymbol())
                .setQty(tradeDto.getQty())
                .build();
    }

    @Override
    public TradeProtoc fromBytes(final byte[] bytes) {
        try {
            return TradeProtoc.parseFrom(bytes);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public byte[] toBytes(final TradeProtoc trade) {
        return trade.toByteArray();
    }
}
