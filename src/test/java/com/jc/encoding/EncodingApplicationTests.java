package com.jc.encoding;

import com.jc.encoding.builder.binary.*;
import com.jc.encoding.builder.text.CSVTradeRecordBuilder;
import com.jc.encoding.builder.text.JsonTradeRecordBuilder;
import com.jc.model.dto.TradeDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

//@SpringBootTest
class EncodingApplicationTests {

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

        var avro = new AvroTradeRecordBuilder();
        process("Avro",
                trades.stream(),
                avro::newTrade,
                avro::toBytes,
                avro::fromBytes);

       var chronocle = new ChronicleTradeRecordBuilder();
       process("chronicle",
               trades.stream(),
               chronocle::newTrade,
               chronocle::toBytes,
               chronocle::fromBytes);

        var proto = new ProtocTradeRecordBuilder();
        process("proto",
                trades.stream(),
                proto::newTrade,
                proto::toBytes,
                proto::fromBytes);

        var sbe = new SbeTradeRecordBuilder();
        process("sbe",
                trades.stream(),
                sbe::newTrade,
                sbe::toBytes,
                sbe::fromBytes);

        var apache = new SerializableRecordBuilder();
        process("apache",
                trades.stream(),
                apache::newTrade,
                apache::toBytes,
                apache::fromBytes);

        var csv = new CSVTradeRecordBuilder();
        process("csv",
                trades.stream(),
                csv::newTrade,
                csv::toBytes,
                csv::fromBytes);

        var json = new JsonTradeRecordBuilder();
        process("json",
                trades.stream(),
                json::newTrade,
                json::toBytes,
                json::fromBytes);

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
