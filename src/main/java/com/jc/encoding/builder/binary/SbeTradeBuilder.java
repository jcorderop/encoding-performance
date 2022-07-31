package com.jc.encoding.builder.binary;

import com.jc.encoding.builder.TradeConverter;
import com.jc.model.dto.TradeDto;
import com.jc.model.sbe.*;
import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;

import java.nio.ByteBuffer;

public class SbeTradeBuilder implements TradeConverter<Object> {

    @Override
    public Object newTrade(final TradeDto tradeDto) {
        final SBETradeEncoder tradeEncoder = new SBETradeEncoder();
        final MessageHeaderEncoder messageHeaderEncoder = new MessageHeaderEncoder();

        final ByteBuffer buffer = ByteBuffer.allocate(100);
        final MutableDirectBuffer mutableBuffer = new UnsafeBuffer(buffer);
        tradeEncoder.wrapAndApplyHeader(mutableBuffer, 0, messageHeaderEncoder);
        return tradeEncoder
                .tradeId(tradeDto.getTradeId())
                .customerId(tradeDto.getCustomerId())
                .tradeType(SBETradeType.valueOf(tradeDto.getTradeType().name()))
                .qty(tradeDto.getQty())
                .symbol(tradeDto.getSymbol())
                .exchange(tradeDto.getExchange());
    }

    @Override
    public byte[] toBytes(final Object o) {
        SBETradeEncoder t = (SBETradeEncoder) o;
        byte[] bytes = new byte[t.limit()];
        t.buffer().getBytes(0, bytes);
        return bytes;
    }

    @Override
    public Object fromBytes(final byte[] b) {

        final SBETradeDecoder tradeDecoder = new SBETradeDecoder();
        final MessageHeaderDecoder messageHeaderDecoder = new MessageHeaderDecoder();

        final MutableDirectBuffer directBuffer = new UnsafeBuffer(ByteBuffer.wrap(b));

        messageHeaderDecoder.wrap(directBuffer, 0);
        tradeDecoder.wrap(directBuffer, MessageHeaderDecoder.ENCODED_LENGTH, messageHeaderDecoder.blockLength(), messageHeaderDecoder.version());

        return tradeDecoder;
    }
}
