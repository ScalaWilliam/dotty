import scala.reflect.runtime.universe._
import scala.reflect.runtime.{currentMirror => cm}
import scala.reflect.ClassTag

class Foo{
 import Test._
 def foo = {
  val classTag = implicitly[ClassTag[R.type]]
  val sym = cm.moduleSymbol(classTag.runtimeClass)
  try {
    val cls = cm.reflect(this).reflectModule(sym)
    cls.instance
    println("this indicates a failure")
  } catch {
    case ex: Throwable =>
      println(ex.getMessage)
  }
 }

}

object Test extends dotty.runtime.LegacyApp{
  object R { override def toString = "R" }
  val foo = new Foo
  println(foo.foo)
}
