package tapl.untyped

import scala.scalajs.js
import org.scalajs.dom

object JSDemo extends js.JSApp {
  def main(): Unit = {
    val paragraph = dom.document.createElement("p")
    demo(program)
    paragraph.innerHTML = s"<pre>${output}</pre>"
    dom.document.getElementById("playground").appendChild(paragraph)
  }

  import Evaluator._
  import util.Print._
  import PrettyPrinter._

  val width = 60
  var output: String = ""

  def processCommand(ctx: Context, cmd: Command): Context = cmd match {
    case Eval(t1) =>
      val doc1 = g2(ptmATerm(true, ctx, t1) :: ";")
      val t2 = eval(ctx, t1)
      val doc2 = g2(ptmATerm(true, ctx, t2) :: ";")

      println("====================")
      println(print(doc1, width))
      println("""||""")
      println("""\/""")
      println(print(doc2, width))

      ctx

    case Bind(n, b) =>
      ctx.addBinding(n, b)
  }

  def println(s: String): Unit = {
    output += s + "\n"
  }

  def demo(s: String): Unit = {
    val (commands, _) = UntypedParsers.input(s)(Context())
    commands.foldLeft(Context())(processCommand)
  }

  val program : String =
    """
      |x/;
      |(x) x x;
      |(\a. a) x;
      |\a.\ b. a b;
      |\a.\b. b a;
      |(\a.\b. b a) x x;
      |(\y. y) (\z. z);
    """.stripMargin
}
