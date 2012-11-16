package code 
package snippet 

import scala.xml.{NodeSeq, Text}
import net.liftweb.util._
import net.liftweb.common._
import net.liftweb.http._
import java.util.Date
import code.lib._
import Helpers._

class HelloWorld {

 private var _policyLevel: Box[String] = Empty
 private def policyLevels = List("aaa","bbbb","ccc")
  
 private def renderItems: Seq[CssSel] = {
    val rendered = for {
      level <- _policyLevel.toList
      entry <- List("item 1", "item 2")
    } yield 
       ".entryName" #> entry & ".entryDescription" #> level
       
    rendered
  }
  
  def howdy = {
    val displayLevels: List[(Box[String], String)] = (Empty,"") :: policyLevels.map(l => (Full(l), l))
    
    "#fixed" #> SHtml.idMemoize {f => 
      "#level" #> SHtml.ajaxSelectObj(displayLevels, Full(_policyLevel), (p:Box[String] )=> {_policyLevel = p; f.setHtml}) &
      "#list *" #> renderItems
    }
  }

}

