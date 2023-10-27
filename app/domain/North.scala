package domain

case class North() extends Orientation {
  override def atLeft(): Orientation = West()

  override def atRight(): Orientation = East()

  override def toString: String = "North"
}
