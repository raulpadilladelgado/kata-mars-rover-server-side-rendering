package domain

case class South() extends Orientation {
  override def atLeft(): Orientation = East()

  override def atRight(): Orientation = West()
}
