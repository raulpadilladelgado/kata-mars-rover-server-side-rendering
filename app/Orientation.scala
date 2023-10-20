sealed trait Orientation {
  def atRight(): Orientation

  def atLeft(): Orientation
}

case class North() extends Orientation {
  override def atLeft(): Orientation = West()

  override def atRight(): Orientation = East()
}

case class West() extends Orientation {
  override def atLeft(): Orientation = South()

  override def atRight(): Orientation = North()
}

case class East() extends Orientation {
  override def atLeft(): Orientation = North()

  override def atRight(): Orientation = South()
}

case class South() extends Orientation {
  override def atLeft(): Orientation = East()

  override def atRight(): Orientation = West()
}