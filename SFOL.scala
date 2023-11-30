case class Theory(decls: List[Declaration])

abstract class Declaration

abstract class NamedDeclaration extends Declaration {
  val name: String
}

case class TypeDecl(name: String) extends NamedDeclaration
case class FunDecl(name: String, inputs: List[Type], output: Type) extends NamedDeclaration
case class PredDecl(name: String, inputs: List[Type]) extends NamedDeclaration
case class Axiom(form: Formula) extends Declaration

abstract class Expression

abstract class Type extends Expression
case class TypeRef(y: String) extends Type

abstract class Term extends Expression
case class VarRef(x: String) extends Term
case class FunRef(f: String, inputs: List[Term]) extends Term

abstract class Formula extends Expression
case class PredRef(p: String, inputs: List[Term]) extends Formula
case class True() extends Formula
case class Equality(tp: Type, left: Term, right: Term) extends Formula
case class And(left: Formula, right: Formula) extends Formula
case class Or(left: Formula, right: Formula) extends Formula
case class Implication(left: Formula, right: Formula) extends Formula
case class Equivalence(left: Formula, right: Formula) extends Formula
case class Not(formula: Formula) extends Formula
case class Forall(vr: String, tp: Type, body: Formula) extends Formula
case class Exists(vr: String, tp: Type, body: Formula) extends Formula

object SFOL {
  // Additional classes based on the ontology
  case class TypeExpr(y: String) extends Type
  case class TypeFun(f: String, inputs: List[Type]) extends Type

  case class TermFun(f: String, inputs: List[Term]) extends Term

  case class PredicateSymbol(p: String, inputs: List[Type]) extends NamedDeclaration
}
