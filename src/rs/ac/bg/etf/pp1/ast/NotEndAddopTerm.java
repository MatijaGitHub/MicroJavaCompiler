// generated with ast extension for cup
// version 0.8
// 4/1/2023 1:1:55


package rs.ac.bg.etf.pp1.ast;

public class NotEndAddopTerm extends AddopTerms {

    private Term Term;
    private Addop Addop;
    private AddopTerms AddopTerms;

    public NotEndAddopTerm (Term Term, Addop Addop, AddopTerms AddopTerms) {
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
        this.Addop=Addop;
        if(Addop!=null) Addop.setParent(this);
        this.AddopTerms=AddopTerms;
        if(AddopTerms!=null) AddopTerms.setParent(this);
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public Addop getAddop() {
        return Addop;
    }

    public void setAddop(Addop Addop) {
        this.Addop=Addop;
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
        if(Addop!=null) Addop.accept(visitor);
        if(AddopTerms!=null) AddopTerms.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
        if(Addop!=null) Addop.traverseTopDown(visitor);
        if(AddopTerms!=null) AddopTerms.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Term!=null) Term.traverseBottomUp(visitor);
        if(Addop!=null) Addop.traverseBottomUp(visitor);
        if(AddopTerms!=null) AddopTerms.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NotEndAddopTerm(\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Addop!=null)
            buffer.append(Addop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AddopTerms!=null)
            buffer.append(AddopTerms.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NotEndAddopTerm]");
        return buffer.toString();
    }
}
