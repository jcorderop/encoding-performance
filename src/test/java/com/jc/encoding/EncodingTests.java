package com.jc.encoding;

import com.jc.encoding.builder.binary.*;
import com.jc.encoding.builder.text.CSVTradeBuilder;
import com.jc.encoding.builder.text.GsonTradeBuilder;
import com.jc.encoding.builder.text.JacksonTradeBuilder;
import com.jc.model.dto.TradeDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

//@SpringBootTest
class EncodingTests {

    @Test
    void contextLoads() {
        List<TradeDto> trades = List.of(
                new TradeDto(new Random().nextLong(),
                    new Random().nextLong(),
                    100,
                    TradeDto.TradeType.Buy,
                    "GOOGL",
                    "NYSE"),
                new TradeDto(new Random().nextLong(),
                    new Random().nextLong(),
                    100,
                    TradeDto.TradeType.Buy,
                    "GOOGL",
                    "NYSE"));

        var avro = new AvroTradeBuilder();
        process("Avro",
                trades.stream(),
                avro::newTrade,
                avro::toBytes,
                avro::fromBytes);

       var chronocleByteBuffer = new ChronicleByteBufferTradeBuilder();
       process("chronicleByteBuffer",
               trades.stream(),
               chronocleByteBuffer::newTrade,
               chronocleByteBuffer::toBytes,
               chronocleByteBuffer::fromBytes);

        var chronocleRawWire = new ChronicleRawWireTradeBuilder();
        process("chronocleRawWire",
                trades.stream(),
                chronocleRawWire::newTrade,
                chronocleRawWire::toBytes,
                chronocleRawWire::fromBytes);

        var chronicleBinaryWire = new ChronicleBinaryWireTradeBuilder();
        process("chronicleBinaryWire",
                trades.stream(),
                chronicleBinaryWire::newTrade,
                chronicleBinaryWire::toBytes,
                chronicleBinaryWire::fromBytes);

        var chronicleTextWire = new ChronicleTextWireTradeBuilder();
        process("chronicleTextWire",
                trades.stream(),
                chronicleTextWire::newTrade,
                chronicleTextWire::toBytes,
                chronicleTextWire::fromBytes);

        var proto = new ProtocTradeBuilder();
        process("proto",
                trades.stream(),
                proto::newTrade,
                proto::toBytes,
                proto::fromBytes);

        var sbe = new SbeTradeBuilder();
        process("sbe",
                trades.stream(),
                sbe::newTrade,
                sbe::toBytes,
                sbe::fromBytes);

        var apache = new SerializableTradeBuilder();
        process("apache",
                trades.stream(),
                apache::newTrade,
                apache::toBytes,
                apache::fromBytes);

        var csv = new CSVTradeBuilder();
        process("csv",
                trades.stream(),
                csv::newTrade,
                csv::toBytes,
                csv::fromBytes);

        var json = new GsonTradeBuilder();
        process("json",
                trades.stream(),
                json::newTrade,
                json::toBytes,
                json::fromBytes);

        var jsonVertx = new JacksonTradeBuilder();
        process("jsonVertx",
                trades.stream(),
                jsonVertx::newTrade,
                jsonVertx::toBytes,
                jsonVertx::fromBytes);

    }

    static <T> void process(String formatType,
                            Stream<TradeDto> trades,
                            Function<TradeDto, T> recordBuilder,
                            Function<T, byte[]> toBytes,
                            Function<byte[], T> fromBytes) {
        trades.map(recordBuilder)
                .map(toBytes)
                .peek(x -> System.out.printf("(%s) -> Size %s Bytes%n", formatType, x.length))
                .map(fromBytes)
                .forEach(System.out::println);

        System.out.println();
    }

}
