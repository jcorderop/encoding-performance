package com.jc.encoding;

import com.google.common.primitives.Doubles;
import com.jc.encoding.builder.TradeConverter;
import com.jc.encoding.builder.binary.*;
import com.jc.encoding.builder.text.GsonTradeBuilder;
import com.jc.model.avro.Trade;
import com.jc.model.dto.TradeDto;
import com.jc.model.proto.TradeProtoc;
import lombok.extern.slf4j.Slf4j;
import net.openhft.chronicle.wire.Wire;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class SerializationTimeTest {

    public static final int NUMBER_OF_ROWS = 100_000_000;

    public static final int ROWS_SNAPSHOT = 5_000_000;

    public static final List<Double> percentiles = List.of(1.0, 10.0, 20.0, 30.0, 40.0, 50.0,
            60.0, 70.0, 80.0, 90.0, 95.0, 98.0, 99.0, 100.0);

    public static final int WAITING = 0;//60_000;

    @Test
    void loadListOfRawWireTradeRecord() {
        TradeConverter<Wire> serializable = new ChronicleRawWireTradeBuilder();
        process(serializable);
    }

    @Test
    void loadListOfProtocTradeRecord() {
        TradeConverter<TradeProtoc> serializable = new ProtocTradeBuilder();
        process(serializable);
    }

    @Test
    void loadListOfSerializableTradeRecord() {
        TradeConverter<TradeDto> serializable = new SerializableTradeBuilder();
        process(serializable);
    }

    @Test
    void loadListOfSbeTradeRecord() {
        TradeConverter<Object> serializable = new SbeTradeBuilder();
        process(serializable);
    }

    @Test
    void loadListOfAvroTradeRecord() {
        TradeConverter<Trade> serializable = new AvroTradeBuilder();
        process(serializable);
    }

    @Test
    void loadListOfGsonTradeRecord() {
        TradeConverter<TradeDto> serializable = new GsonTradeBuilder();
        process(serializable);
    }

    @Test
    void loadListOfJacksonTradeRecord() {
        TradeConverter<TradeDto> serializable = new GsonTradeBuilder();
        process(serializable);
    }

    private static void process(TradeConverter serializable) {
        log.info("Starting...");
        List<Double> latencies = new ArrayList<>();
        var totalLat = System.currentTimeMillis();
        var latency = totalLat;
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            Optional<byte[]> result = serialize(serializable, i);
            if (result.isEmpty()) {
                throw new RuntimeException("Something went wrong...");
            } else {
                if (i % ROWS_SNAPSHOT == 0) {
                    double latencyCalcUs = (System.currentTimeMillis() - latency) ;
                    log.info("Rows: "+ROWS_SNAPSHOT+ " Latency ms: " + latencyCalcUs);
                    latencies.add(latencyCalcUs);
                    latency = System.currentTimeMillis();
                }
            }
        }
        log.info("Total Latency in ms: " + (System.currentTimeMillis() - totalLat));
        calculatePercentile(latencies);
    }

    private static Optional<byte[]> serialize(final TradeConverter serializable, final int id) {
        return Optional.of(CompletableFuture
                .completedFuture(getTradeDtoFunction())
                .thenApply(integerTradeDtoFunction -> getTradeDtoFunction().apply(id))
                .thenApply(serializable::newTrade)
                .thenApply((Object t) -> serializable.toBytes(t))
                .getNow(null));
    }

    private static void calculatePercentile(final List<Double> latencies) {
        log.info("Percentile: ");
        double[] data = Doubles.toArray(latencies);
        Arrays.sort(data);
        Percentile percentile = new Percentile();
        percentile.setData(data);
        percentiles.forEach(percent -> System.out.println(percent + ","+ String.format("%.3f", percentile.evaluate(percent))));
    }

    private static void processList(TradeConverter serializable) {
        try {
            Thread.sleep(WAITING*1);
            System.out.println("Starting...");
            var latency = System.currentTimeMillis();
            var collect = IntStream.range(0, NUMBER_OF_ROWS)
                    .mapToObj(getTradeDtoIntFunction())
                    .map(serializable::newTrade)
                    .map((Object t) -> serializable.toBytes(t)).toList();
            System.out.println("Lat: " + (System.currentTimeMillis() - latency));
            System.out.println("Data loaded...");
            Thread.sleep(WAITING*5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static IntFunction<TradeDto> getTradeDtoIntFunction() {
        return operand -> createTrade ();
    }

    private static Function<Integer, TradeDto> getTradeDtoFunction() {
        return operand -> createTrade();
    }

    private static TradeDto createTrade () {
        return new TradeDto(new Random().nextLong(),
                new Random().nextLong(),
                100,
                TradeDto.TradeType.Buy,
                "GOOGL",
                "NYSE");
    }
}
