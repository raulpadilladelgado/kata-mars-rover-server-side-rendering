package domain

case class East() extends Orientation {
  override def atLeft(): Orientation = North()

  override def atRight(): Orientation = South()

  override def toString: String = "East"
}
