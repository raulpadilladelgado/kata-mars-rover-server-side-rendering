case class Position(longitude: Int, latitude: Int) {
  def moveForwardBasedOn(orientation: Orientation): Position = {
    orientation match {
      case North() => Position(longitude, latitude + 1)
      case South() => Position(longitude, latitude - 1)
      case East() => Position(longitude + 1, latitude)
      case West() => Position(longitude - 1, latitude)
    }
  }
}
