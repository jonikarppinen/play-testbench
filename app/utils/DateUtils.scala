package utils

import java.time.{Instant, ZoneId, ZonedDateTime}

object DateUtils {

  val UTC = ZoneId.of("UTC")

  def toUtcDateTime(epochMilli: Long) = ZonedDateTime.from(Instant.ofEpochMilli(epochMilli).atZone(UTC))

}
