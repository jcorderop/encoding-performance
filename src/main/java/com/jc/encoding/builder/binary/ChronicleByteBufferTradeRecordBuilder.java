package com.jc.encoding.builder.binary;

import com.jc.encoding.builder.Converter;
import com.jc.model.dto.TradeDto;
import net.openhft.chronicle.bytes.Bytes;
import net.openhft.chronicle.wire.RawWire;
import net.openhft.chronicle.wire.Wire;

import java.nio.ByteBuffer;

public class ChronicleByteBufferTradeRecordBuilder implements Converter<Bytes<ByteBuffer>> {

    @Override
    public Bytes<ByteBuffer> newTrade(final TradeDto tradeDto) {
        final Bytes<ByteBuffer> bytes = Bytes.elasticByteBuffer();
        final Wire wire = createWriteFormat(bytes);
        wire.write(() -> "tradeId").int64(tradeDto.getTradeId())
                .write(() -> "customerId").int64(tradeDto.getCustomerId())
                .write(() -> "exchange").text(tradeDto.getExchange())
                .write(() -> "tradeType").text(tradeDto.getTradeType().name())
                .write(() -> "symbol").text(tradeDto.getSymbol())
                .write(() -> "qty").int32(tradeDto.getQty());
        return bytes;
    }

    @Override
    public byte[] toBytes(final Bytes<ByteBuffer> o) {
        //System.out.println(""+createWriteFormat(o).read("tradeId").int64());
        return o.toByteArray();
    }

    @Override
    public Bytes<ByteBuffer> fromBytes(final byte[] b) {
        /*Wire writeFormat = createWriteFormat(Bytes
                .elasticByteBuffer(b.length)
                .write(b));
        System.out.println(""+writeFormat.read("tradeId").int64());
        */
        return Bytes.elasticByteBuffer(b.length).write(b);
    }

    private static Wire createWriteFormat(final Bytes<ByteBuffer> bytes) {
        return new RawWire(bytes);
    }
}
