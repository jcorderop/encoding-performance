package com.jc.encoding.builder.text;

import com.jc.encoding.builder.TradeConverter;
import com.jc.model.dto.TradeDto;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

public class JacksonTradeBuilder implements TradeConverter<JsonObject> {

    @Override
    public JsonObject newTrade(final TradeDto tradeDto) {
        return JsonObject.mapFrom(tradeDto);
    }

    public byte[] toBytes(final JsonObject t) {
        return t.toBuffer().getBytes();
    }

    public JsonObject fromBytes(final byte[] b) {
        return JsonObject.mapFrom(Json.decodeValue(Buffer.buffer(b), TradeDto.class));
    }
}
