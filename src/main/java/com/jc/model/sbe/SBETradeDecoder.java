/* Generated SBE (Simple Binary Encoding) message codec. */
package com.jc.model.sbe;

import org.agrona.MutableDirectBuffer;
import org.agrona.DirectBuffer;


/**
 * Trade details
 */
@SuppressWarnings("all")
public final class SBETradeDecoder
{
    public static final int BLOCK_LENGTH = 25;
    public static final int TEMPLATE_ID = 2;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 0;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private final SBETradeDecoder parentMessage = this;
    private DirectBuffer buffer;
    private int initialOffset;
    private int offset;
    private int limit;
    int actingBlockLength;
    int actingVersion;

    public int sbeBlockLength()
    {
        return BLOCK_LENGTH;
    }

    public int sbeTemplateId()
    {
        return TEMPLATE_ID;
    }

    public int sbeSchemaId()
    {
        return SCHEMA_ID;
    }

    public int sbeSchemaVersion()
    {
        return SCHEMA_VERSION;
    }

    public String sbeSemanticType()
    {
        return "";
    }

    public DirectBuffer buffer()
    {
        return buffer;
    }

    public int initialOffset()
    {
        return initialOffset;
    }

    public int offset()
    {
        return offset;
    }

    public SBETradeDecoder wrap(
        final DirectBuffer buffer,
        final int offset,
        final int actingBlockLength,
        final int actingVersion)
    {
        if (buffer != this.buffer)
        {
            this.buffer = buffer;
        }
        this.initialOffset = offset;
        this.offset = offset;
        this.actingBlockLength = actingBlockLength;
        this.actingVersion = actingVersion;
        limit(offset + actingBlockLength);

        return this;
    }

    public SBETradeDecoder wrapAndApplyHeader(
        final DirectBuffer buffer,
        final int offset,
        final MessageHeaderDecoder headerDecoder)
    {
        headerDecoder.wrap(buffer, offset);

        final int templateId = headerDecoder.templateId();
        if (TEMPLATE_ID != templateId)
        {
            throw new IllegalStateException("Invalid TEMPLATE_ID: " + templateId);
        }

        return wrap(
            buffer,
            offset + MessageHeaderDecoder.ENCODED_LENGTH,
            headerDecoder.blockLength(),
            headerDecoder.version());
    }

    public SBETradeDecoder sbeRewind()
    {
        return wrap(buffer, initialOffset, actingBlockLength, actingVersion);
    }

    public int sbeDecodedLength()
    {
        final int currentLimit = limit();
        sbeSkip();
        final int decodedLength = encodedLength();
        limit(currentLimit);

        return decodedLength;
    }

    public int encodedLength()
    {
        return limit - offset;
    }

    public int limit()
    {
        return limit;
    }

    public void limit(final int limit)
    {
        this.limit = limit;
    }

    public static int tradeIdId()
    {
        return 1;
    }

    public static int tradeIdSinceVersion()
    {
        return 0;
    }

    public static int tradeIdEncodingOffset()
    {
        return 0;
    }

    public static int tradeIdEncodingLength()
    {
        return 8;
    }

    public static String tradeIdMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long tradeIdNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long tradeIdMinValue()
    {
        return 0x0L;
    }

    public static long tradeIdMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public long tradeId()
    {
        return buffer.getLong(offset + 0, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public static int customerIdId()
    {
        return 2;
    }

    public static int customerIdSinceVersion()
    {
        return 0;
    }

    public static int customerIdEncodingOffset()
    {
        return 8;
    }

    public static int customerIdEncodingLength()
    {
        return 8;
    }

    public static String customerIdMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long customerIdNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long customerIdMinValue()
    {
        return 0x0L;
    }

    public static long customerIdMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public long customerId()
    {
        return buffer.getLong(offset + 8, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public static int qtyId()
    {
        return 3;
    }

    public static int qtySinceVersion()
    {
        return 0;
    }

    public static int qtyEncodingOffset()
    {
        return 16;
    }

    public static int qtyEncodingLength()
    {
        return 8;
    }

    public static String qtyMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long qtyNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long qtyMinValue()
    {
        return 0x0L;
    }

    public static long qtyMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public long qty()
    {
        return buffer.getLong(offset + 16, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public static int tradeTypeId()
    {
        return 4;
    }

    public static int tradeTypeSinceVersion()
    {
        return 0;
    }

    public static int tradeTypeEncodingOffset()
    {
        return 24;
    }

    public static int tradeTypeEncodingLength()
    {
        return 1;
    }

    public static String tradeTypeMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public short tradeTypeRaw()
    {
        return ((short)(buffer.getByte(offset + 24) & 0xFF));
    }

    public SBETradeType tradeType()
    {
        return SBETradeType.get(((short)(buffer.getByte(offset + 24) & 0xFF)));
    }


    public static int symbolId()
    {
        return 5;
    }

    public static int symbolSinceVersion()
    {
        return 0;
    }

    public static String symbolCharacterEncoding()
    {
        return java.nio.charset.StandardCharsets.UTF_8.name();
    }

    public static String symbolMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static int symbolHeaderLength()
    {
        return 4;
    }

    public int symbolLength()
    {
        final int limit = parentMessage.limit();
        return (int)(buffer.getInt(limit, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
    }

    public int skipSymbol()
    {
        final int headerLength = 4;
        final int limit = parentMessage.limit();
        final int dataLength = (int)(buffer.getInt(limit, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
        final int dataOffset = limit + headerLength;
        parentMessage.limit(dataOffset + dataLength);

        return dataLength;
    }

    public int getSymbol(final MutableDirectBuffer dst, final int dstOffset, final int length)
    {
        final int headerLength = 4;
        final int limit = parentMessage.limit();
        final int dataLength = (int)(buffer.getInt(limit, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
        final int bytesCopied = Math.min(length, dataLength);
        parentMessage.limit(limit + headerLength + dataLength);
        buffer.getBytes(limit + headerLength, dst, dstOffset, bytesCopied);

        return bytesCopied;
    }

    public int getSymbol(final byte[] dst, final int dstOffset, final int length)
    {
        final int headerLength = 4;
        final int limit = parentMessage.limit();
        final int dataLength = (int)(buffer.getInt(limit, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
        final int bytesCopied = Math.min(length, dataLength);
        parentMessage.limit(limit + headerLength + dataLength);
        buffer.getBytes(limit + headerLength, dst, dstOffset, bytesCopied);

        return bytesCopied;
    }

    public void wrapSymbol(final DirectBuffer wrapBuffer)
    {
        final int headerLength = 4;
        final int limit = parentMessage.limit();
        final int dataLength = (int)(buffer.getInt(limit, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
        parentMessage.limit(limit + headerLength + dataLength);
        wrapBuffer.wrap(buffer, limit + headerLength, dataLength);
    }

    public String symbol()
    {
        final int headerLength = 4;
        final int limit = parentMessage.limit();
        final int dataLength = (int)(buffer.getInt(limit, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
        parentMessage.limit(limit + headerLength + dataLength);

        if (0 == dataLength)
        {
            return "";
        }

        final byte[] tmp = new byte[dataLength];
        buffer.getBytes(limit + headerLength, tmp, 0, dataLength);

        return new String(tmp, java.nio.charset.StandardCharsets.UTF_8);
    }

    public static int exchangeId()
    {
        return 6;
    }

    public static int exchangeSinceVersion()
    {
        return 0;
    }

    public static String exchangeCharacterEncoding()
    {
        return java.nio.charset.StandardCharsets.UTF_8.name();
    }

    public static String exchangeMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static int exchangeHeaderLength()
    {
        return 4;
    }

    public int exchangeLength()
    {
        final int limit = parentMessage.limit();
        return (int)(buffer.getInt(limit, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
    }

    public int skipExchange()
    {
        final int headerLength = 4;
        final int limit = parentMessage.limit();
        final int dataLength = (int)(buffer.getInt(limit, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
        final int dataOffset = limit + headerLength;
        parentMessage.limit(dataOffset + dataLength);

        return dataLength;
    }

    public int getExchange(final MutableDirectBuffer dst, final int dstOffset, final int length)
    {
        final int headerLength = 4;
        final int limit = parentMessage.limit();
        final int dataLength = (int)(buffer.getInt(limit, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
        final int bytesCopied = Math.min(length, dataLength);
        parentMessage.limit(limit + headerLength + dataLength);
        buffer.getBytes(limit + headerLength, dst, dstOffset, bytesCopied);

        return bytesCopied;
    }

    public int getExchange(final byte[] dst, final int dstOffset, final int length)
    {
        final int headerLength = 4;
        final int limit = parentMessage.limit();
        final int dataLength = (int)(buffer.getInt(limit, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
        final int bytesCopied = Math.min(length, dataLength);
        parentMessage.limit(limit + headerLength + dataLength);
        buffer.getBytes(limit + headerLength, dst, dstOffset, bytesCopied);

        return bytesCopied;
    }

    public void wrapExchange(final DirectBuffer wrapBuffer)
    {
        final int headerLength = 4;
        final int limit = parentMessage.limit();
        final int dataLength = (int)(buffer.getInt(limit, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
        parentMessage.limit(limit + headerLength + dataLength);
        wrapBuffer.wrap(buffer, limit + headerLength, dataLength);
    }

    public String exchange()
    {
        final int headerLength = 4;
        final int limit = parentMessage.limit();
        final int dataLength = (int)(buffer.getInt(limit, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
        parentMessage.limit(limit + headerLength + dataLength);

        if (0 == dataLength)
        {
            return "";
        }

        final byte[] tmp = new byte[dataLength];
        buffer.getBytes(limit + headerLength, tmp, 0, dataLength);

        return new String(tmp, java.nio.charset.StandardCharsets.UTF_8);
    }

    public String toString()
    {
        if (null == buffer)
        {
            return "";
        }

        final SBETradeDecoder decoder = new SBETradeDecoder();
        decoder.wrap(buffer, initialOffset, actingBlockLength, actingVersion);

        return decoder.appendTo(new StringBuilder()).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        if (null == buffer)
        {
            return builder;
        }

        final int originalLimit = limit();
        limit(initialOffset + actingBlockLength);
        builder.append("[SBETrade](sbeTemplateId=");
        builder.append(TEMPLATE_ID);
        builder.append("|sbeSchemaId=");
        builder.append(SCHEMA_ID);
        builder.append("|sbeSchemaVersion=");
        if (parentMessage.actingVersion != SCHEMA_VERSION)
        {
            builder.append(parentMessage.actingVersion);
            builder.append('/');
        }
        builder.append(SCHEMA_VERSION);
        builder.append("|sbeBlockLength=");
        if (actingBlockLength != BLOCK_LENGTH)
        {
            builder.append(actingBlockLength);
            builder.append('/');
        }
        builder.append(BLOCK_LENGTH);
        builder.append("):");
        builder.append("tradeId=");
        builder.append(tradeId());
        builder.append('|');
        builder.append("customerId=");
        builder.append(customerId());
        builder.append('|');
        builder.append("qty=");
        builder.append(qty());
        builder.append('|');
        builder.append("tradeType=");
        builder.append(tradeType());
        builder.append('|');
        builder.append("symbol=");
        builder.append('\'').append(symbol()).append('\'');
        builder.append('|');
        builder.append("exchange=");
        builder.append('\'').append(exchange()).append('\'');

        limit(originalLimit);

        return builder;
    }
    
    public SBETradeDecoder sbeSkip()
    {
        sbeRewind();
        skipSymbol();
        skipExchange();

        return this;
    }
}
