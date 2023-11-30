//Declaration
case class Ontology(declarations: List[Declaration]) {
  def printAll(): Unit = declarations.foreach(d => println(d.print()))
}

abstract class Declaration {
  def print(): String
}

case class IndividualDeclaration(individual: String) extends Declaration {
  def print() = s"Individual($individual)"
}

case class ConceptDeclaration(concept: String) extends Declaration {
  def print() = s"Concept($concept)"
}

case class RelationDeclaration(relation: String) extends Declaration {
  def print() = s"Relation($relation)"
}

case class PropertyDeclaration(property: String) extends Declaration {
  def print() = s"Property($property)"
}

case class Axiom(formula: Formula) extends Declaration {
  def print() = s"axiom ${formula.print()}"
}


// ************************************************************************************ \\
abstract class Expression {
  def print(): String
}

//Individuals
abstract class Individual extends Expression

case class AtomicIndividual(individual: String) extends Individual {
  def print() = s"Individual($individual)"
}

//Concepts
abstract class Concept extends Expression

case class AtomicConcept(concept: String) extends Concept {
  def print() = s"Concept($concept)"
}

case object Top extends Concept {
  def print(): String = "Top"
}

case object Bottom extends Concept {
  def print(): String = "Bottom"
}

case class Union(concept1: Concept, concept2: Concept) extends Concept {
  def print() = s"Union(${concept1.print()}, ${concept2.print()})"
}

case class Intersection(concept1: Concept, concept2: Concept) extends Concept {
  def print() = s"Intersection(${concept1.print()}, ${concept2.print()})"
}

case class UniversalRelativization(relation: Relation, concept: Concept) extends Concept {
  def print() = s"UniversalRelativization(${relation.print()}, ${concept.print()})"
}

case class ExistentialRelativization(relation: Relation, concept: Concept) extends Concept {
  def print() = s"ExistentialRelativization(${relation.print()}, ${concept.print()})"
}

case class DomainOfRelation(relation: Relation) extends Concept {
  def print() = s"DomainOfRelation(${relation.print()})"
}

case class RangeOfRelation(relation: Relation) extends Concept {
  def print() = s"RangeOfRelation(${relation.print()})"
}

//Relations
abstract class Relation extends Expression

case class AtomicRelation(relation: String) extends Relation {
  def print() = s"Relation($relation)"
}

case class UnionRelation(relation1: Relation, relation2: Relation) extends Relation {
  def print() = s"UnionRelation(${relation1.print()}, ${relation2.print()})"
}

case class IntersectionRelation(relation1: Relation, relation2: Relation) extends Relation {
  def print() = s"IntersectionRelation(${relation1.print()}, ${relation2.print()})"
}

case class CompositionRelation(relation1: Relation, relation2: Relation) extends Relation {
  def print() = s"CompositionRelation(${relation1.print()}, ${relation2.print()})"
}

case class TransitiveClosure(relation: Relation) extends Relation {
  def print() = s"TransitiveClosure(${relation.print()})"
}

case class DualRelation(relation: Relation) extends Relation {
  def print() = s"DualRelation(${relation.print()})"
}

case class IdentityRelation(concept: Concept) extends Relation {
  def print() = s"IdentityRelation(${concept.print()})"
}

//Property
case class Property(property: String) extends Expression {
  def print() = s"Property($property)"
}

//Formula
abstract class Formula extends Expression

case class ConceptEquality(concept1: Concept, concept2: Concept) extends Formula {
  def print() = s"ConceptEquality(${concept1.print()}, ${concept2.print()})"
}

case class ConceptSubsumption(concept1: Concept, concept2: Concept) extends Formula {
  def print() = s"ConceptSubsumption(${concept1.print()}, ${concept2.print()})"
}

case class Instance(individual: Individual, concept: Concept) extends Formula {
  def print() = s"Instance(${individual.print()}, ${concept.print()})"
}

case class RelationInstance(individual1: Individual, relation: Relation, individual2: Individual) extends Formula {
  def print() = s"RelationInstance(${individual1.print()}, ${relation.print()}, ${individual2.print()})"
}

case class PropertyInstance(individual: Individual, property: Property, value: Value) extends Formula {
  def print() = s"PropertyInstance(${individual.print()}, ${property.print()}, $value)"
}

//Value
abstract class Type(string: String) extends Declaration {
    def print() = string
}
case class IntType() extends Type("int")
case class StringType() extends Type("string")
case class BoolType() extends Type("bool")

abstract class Value(value: Any) extends Declaration {
    def print() = value.toString
}
case class IntVal(value: Int) extends Value
case class StringVal(value: String) extends Value
case class BoolVal(value: Boolean) extends Value

// ************************************************************************************ \\

class Checker(ontology: Ontology) {
  def Check() = {
    checkDeclaration()
  }

 private def checkDeclaration(ontology: Ontology,) {
  ontology match {

  }
}


  // Add more methods for checking other aspects of the ontology
}

object Main {
  def main(args: Array[String]): Unit = {
      
    val individualDeclaration1 = IndividualDeclaration("John")
    val conceptDeclaration1 = ConceptDeclaration("Person")
    val conceptDeclaration2 = ConceptDeclaration("Student")

    val atomicIndividual = AtomicIndividual("John")
    val atomicConcept1 = AtomicConcept("Person")
    val atomicConcept2 = AtomicConcept("Student")
    
    val conceptUnion = Union(atomicConcept1, atomicConcept2)
    val formula1 = Instance(atomicIndividual, atomicConcept1)
    val formula2 = ConceptSubsumption(atomicConcept2, atomicConcept1)
    
    val axiom = Axiom(formula1)

    val ontology = new Ontology(List(individualDeclaration1, conceptDeclaration1, conceptDeclaration2, axiom))
    ontology.printAll()

    val checker = new Checker(ontology)
    checker.check()
  }
}
