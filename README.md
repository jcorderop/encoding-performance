# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.2/maven-plugin/reference/html/#build-image)

### Avro

Link:

    https://avro.apache.org/docs/current/gettingstartedjava.html 

To create the classes based on Trade.avsc

    mvn clean compile 

### Chronicle

Link:

    https://chronicle.software/

* For java 17 set the following jvm parameters

    --add-exports=java.base/jdk.internal.ref=ALL-UNNAMED 
    --add-exports=java.base/sun.nio.ch=ALL-UNNAMED
    --add-exports=jdk.unsupported/sun.misc=ALL-UNNAMED 
    --add-exports=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED 
    --add-opens=jdk.compiler/com.sun.tools.javac=ALL-UNNAMED 
    --add-opens=java.base/java.lang=ALL-UNNAMED 
    --add-opens=java.base/java.lang.reflect=ALL-UNNAMED 
    --add-opens=java.base/java.io=ALL-UNNAMED 
    --add-opens=java.base/java.util=ALL-UNNAMED 
    --add-opens=java.base/jdk.internal.util=ALL-UNNAMED

### Google protocol buffer

Link:

    https://developers.google.com/protocol-buffers/

* Install google protocol buffer and create the variable PROTOC_PATH
* To create the classes

    execute build_protoc.bat

### Simple Binary Encoding (SBE)

Link:

    https://github.com/real-logic/simple-binary-encoding 

To create the classes based on Trade.avsc

    mvn clean compile

# Results
    
    (Avro) -> Size 44 Bytes
    {"tradeId": 4879908246954430230, "customerId": -4250350058930287262, "qty": 100, "tradeType": "Buy", "symbol": "GOOGL", "exchange": "NYSE"}
    (Avro) -> Size 43 Bytes
    {"tradeId": -1449663617414539192, "customerId": 3834443944602810970, "qty": 100, "tradeType": "Buy", "symbol": "GOOGL", "exchange": "NYSE"}
    
    (chronicle) -> Size 35 Bytes
    Ï7²ê¸CbMf$¹ÅNYSEBuyGOOGLd   
    (chronicle) -> Size 35 Bytes
    HØ2àÂáëZº2­s®65NYSEBuyGOOGLd
    
    (proto) -> Size 36 Bytes
    tradeId: 4879908246954430230
    customerId: -4250350058930287262
    qty: 100
    symbol: "GOOGL"
    exchange: "NYSE"
    
    (proto) -> Size 36 Bytes
    tradeId: -1449663617414539192
    customerId: 3834443944602810970
    qty: 100
    symbol: "GOOGL"
    exchange: "NYSE"

    (sbe) -> Size 50 Bytes
    [SBETrade](sbeTemplateId=2|sbeSchemaId=1|sbeSchemaVersion=0|sbeBlockLength=25):tradeId=4879908246954430230|customerId=-4250350058930287262|qty=100|tradeType=Buy|symbol='GOOGL'|exchange='NYSE'
    (sbe) -> Size 50 Bytes
    [SBETrade](sbeTemplateId=2|sbeSchemaId=1|sbeSchemaVersion=0|sbeBlockLength=25):tradeId=-1449663617414539192|customerId=3834443944602810970|qty=100|tradeType=Buy|symbol='GOOGL'|exchange='NYSE'

    (apache) -> Size 299 Bytes
    com.jc.model.dto.TradeDto@5cdd09b1
    (apache) -> Size 299 Bytes
    com.jc.model.dto.TradeDto@7e8dcdaa

    (csv) -> Size 59 Bytes
    com.jc.model.dto.TradeDto@4535b6d5
    (csv) -> Size 59 Bytes
    com.jc.model.dto.TradeDto@1ecee32c
    
    (json) -> Size 128 Bytes
    com.jc.model.dto.TradeDto@31e4bb20
    (json) -> Size 128 Bytes
