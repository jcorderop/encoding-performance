package com.jc.encoding.builder.binary;

import com.jc.encoding.builder.TradeConverter;
import com.jc.model.dto.TradeDto;
import net.openhft.chronicle.bytes.Bytes;
import net.openhft.chronicle.wire.RawWire;
import net.openhft.chronicle.wire.Wire;

import java.nio.ByteBuffer;

public class ChronicleRawWireTradeBuilder implements TradeConverter<Wire> {

    @Override
    public Wire newTrade(final TradeDto tradeDto) {
        final Wire wire = createWriteFormat(Bytes.elasticByteBuffer());
        wire.write(() -> "tradeId").int64(tradeDto.getTradeId())
                .write(() -> "customerId").int64(tradeDto.getCustomerId())
                .write(() -> "exchange").text(tradeDto.getExchange())
                .write(() -> "tradeType").text(tradeDto.getTradeType().name())
                .write(() -> "symbol").text(tradeDto.getSymbol())
                .write(() -> "qty").int32(tradeDto.getQty());
        return wire;
    }

    @Override
    public byte[] toBytes(final Wire o) {
        return o.<ByteBuffer>bytes().toByteArray();
    }

    @Override
    public Wire fromBytes(final byte[] b) {
        return createWriteFormat(Bytes
                .elasticByteBuffer(b.length)
                .write(b));
    }

    private static Wire createWriteFormat(final Bytes<ByteBuffer> bytes) {
        return new RawWire(bytes);
    }
}
