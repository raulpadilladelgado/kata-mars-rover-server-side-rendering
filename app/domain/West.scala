package domain

case class West() extends Orientation {
  override def atLeft(): Orientation = South()

  override def atRight(): Orientation = North()

  override def toString: String = "West"
}
