package com.jc.encoding.builder.binary;

import com.jc.encoding.builder.Converter;
import com.jc.model.dto.TradeDto;
import org.apache.commons.lang3.SerializationUtils;

public class SerializableRecordBuilder implements Converter<TradeDto> {
    @Override
    public TradeDto newTrade(TradeDto trade) {
        return trade;
    }

    @Override
    public byte[] toBytes(TradeDto tradeDto) {
        return SerializationUtils.serialize(tradeDto);
    }

    @Override
    public TradeDto fromBytes(byte[] bytes) {
        return SerializationUtils.deserialize(bytes);
    }
}
