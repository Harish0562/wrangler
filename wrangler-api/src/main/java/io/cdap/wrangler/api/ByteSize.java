package io.cdap.wrangler.api.parser;

public class ByteSize extends Token {
  private final long bytes;

  public ByteSize(String value) {
    super(value);
    this.bytes = parseToBytes(value);
  }

  private long parseToBytes(String val) {
    val = val.toUpperCase();
    if (val.endsWith("KB")) return (long) (Double.parseDouble(val.replace("KB", "")) * 1024);
    if (val.endsWith("MB")) return (long) (Double.parseDouble(val.replace("MB", "")) * 1024 * 1024);
    if (val.endsWith("GB")) return (long) (Double.parseDouble(val.replace("GB", "")) * 1024 * 1024 * 1024);
    if (val.endsWith("TB")) return (long) (Double.parseDouble(val.replace("TB", "")) * 1024L * 1024L * 1024L * 1024L);
    return Long.parseLong(val.replace("B", ""));
  }

  public long getBytes() {
    return bytes;
  }
}
