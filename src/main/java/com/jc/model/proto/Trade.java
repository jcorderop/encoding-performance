// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Trade.proto

package com.jc.model.proto;

public final class Trade {
  private Trade() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_encoding_TradeProtoc_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_encoding_TradeProtoc_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\013Trade.proto\022\010encoding\"\265\001\n\013TradeProtoc\022" +
      "\017\n\007tradeId\030\001 \001(\003\022\022\n\ncustomerId\030\002 \001(\003\022\013\n\003" +
      "qty\030\003 \001(\003\0222\n\ttradeType\030\004 \001(\0162\037.encoding." +
      "TradeProtoc.TradeType\022\016\n\006symbol\030\005 \001(\t\022\020\n" +
      "\010exchange\030\006 \001(\t\"\036\n\tTradeType\022\007\n\003Buy\020\000\022\010\n" +
      "\004Sell\020\001B\035\n\022com.jc.model.protoB\005TradeP\001b\006" +
      "proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_encoding_TradeProtoc_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_encoding_TradeProtoc_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_encoding_TradeProtoc_descriptor,
        new java.lang.String[] { "TradeId", "CustomerId", "Qty", "TradeType", "Symbol", "Exchange", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}