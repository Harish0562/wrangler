ackage io.cdap.wrangler.directive;

import io.cdap.wrangler.api.*;
import io.cdap.wrangler.api.parser.ByteSize;
import io.cdap.wrangler.api.parser.TimeDuration;

public class AggregateStats implements Directive {
  private String byteCol;
  private String timeCol;
  private String outputByteCol;
  private String outputTimeCol;

  @Override
  public UsageDefinition define() {
    return UsageDefinition.builder("aggregate-stats")
      .define("byteCol", TokenType.STRING)
      .define("timeCol", TokenType.STRING)
      .define("outputByteCol", TokenType.STRING)
      .define("outputTimeCol", TokenType.STRING)
      .build();
  }

  @Override
  public void initialize(Arguments args) {
    byteCol = args.value("byteCol");
    timeCol = args.value("timeCol");
    outputByteCol = args.value("outputByteCol");
    outputTimeCol = args.value("outputTimeCol");
  }

  @Override
  public List<Row> execute(List<Row> rows, ExecutorContext ctx) {
    long totalBytes = 0;
    long totalMillis = 0;
    for (Row row : rows) {
      totalBytes += new ByteSize(row.getValue(byteCol).toString()).getBytes();
      totalMillis += new TimeDuration(row.getValue(timeCol).toString()).getMilliseconds();
    }
    double mb = totalBytes / (1024.0 * 1024);
    double sec = totalMillis / 1000.0;
    return List.of(new Row(outputByteCol, mb).add(outputTimeCol, sec));
  }
}
