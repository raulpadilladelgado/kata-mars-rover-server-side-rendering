package domain

trait Orientation {
  def atRight(): Orientation

  def atLeft(): Orientation
}
