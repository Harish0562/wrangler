package io.cdap.wrangler.api.parser;

public class TimeDuration extends Token {
  private final long milliseconds;

  public TimeDuration(String value) {
    super(value);
    this.milliseconds = parseToMillis(value);
  }

  private long parseToMillis(String val) {
    val = val.toLowerCase();
    if (val.endsWith("ms")) return (long) (Double.parseDouble(val.replace("ms", "")));
    if (val.endsWith("s")) return (long) (Double.parseDouble(val.replace("s", "")) * 1000);
    if (val.endsWith("m")) return (long) (Double.parseDouble(val.replace("m", "")) * 60 * 1000);
    if (val.endsWith("h")) return (long) (Double.parseDouble(val.replace("h", "")) * 60 * 60 * 1000);
    return 0;
  }

  public long getMilliseconds() {
    return milliseconds;
  }
}
