// generated with ast extension for cup
// version 0.8
// 17/11/2022 2:19:31


package rs.ac.bg.etf.pp1.ast;

public class MinusExpr extends Expr {

    private Term Term;
    private AddopTerms AddopTerms;

    public MinusExpr (Term Term, AddopTerms AddopTerms) {
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
        this.AddopTerms=AddopTerms;
        if(AddopTerms!=null) AddopTerms.setParent(this);
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public AddopTerms getAddopTerms() {
        return AddopTerms;
    }

    public void setAddopTerms(AddopTerms AddopTerms) {
        this.AddopTerms=AddopTerms;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Term!=null) Term.accept(visitor);
        if(AddopTerms!=null) AddopTerms.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
        if(AddopTerms!=null) AddopTerms.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Term!=null) Term.traverseBottomUp(visitor);
        if(AddopTerms!=null) AddopTerms.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MinusExpr(\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AddopTerms!=null)
            buffer.append(AddopTerms.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MinusExpr]");
        return buffer.toString();
    }
}
