package com.jc.encoding.builder.text;

import com.jc.encoding.builder.Converter;
import com.jc.model.dto.TradeDto;

public class CSVTradeRecordBuilder implements Converter<TradeDto> {

    public static final String SEPARATOR = ";";

    @Override
    public TradeDto newTrade(TradeDto tradeDto) {
        return tradeDto;
    }

    @Override
    public byte[] toBytes(TradeDto t) {
        return new StringBuilder()
                .append(t.getTradeId()).append(SEPARATOR)
                .append(t.getCustomerId()).append(SEPARATOR)
                .append(t.getExchange()).append(SEPARATOR)
                .append(t.getTradeType().name()).append(SEPARATOR)
                .append(t.getSymbol()).append(SEPARATOR)
                .append(t.getQty())
                .toString().getBytes();
    }

    @Override
    public TradeDto fromBytes(byte[] b) {
        String[] parts = new String(b).split(SEPARATOR);
        int index = 0;
        TradeDto trade = new TradeDto();
        trade.setTradeId(Long.parseLong(parts[index++]));
        trade.setCustomerId(Long.parseLong(parts[index++]));
        trade.setExchange(parts[index++]);
        trade.setTradeType(TradeDto.TradeType.valueOf(parts[index++]));
        trade.setSymbol(parts[index++]);
        trade.setQty(Integer.parseInt(parts[index++]));
        return trade;
    }
}
