// generated with ast extension for cup
// version 0.8
// 6/1/2023 1:50:19


package rs.ac.bg.etf.pp1.ast;

public class PlusExpr extends Expr {

    private AddopTerms AddopTerms;

    public PlusExpr (AddopTerms AddopTerms) {
        this.AddopTerms=AddopTerms;
        if(AddopTerms!=null) AddopTerms.setParent(this);
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
        if(AddopTerms!=null) AddopTerms.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AddopTerms!=null) AddopTerms.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AddopTerms!=null) AddopTerms.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PlusExpr(\n");

        if(AddopTerms!=null)
            buffer.append(AddopTerms.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PlusExpr]");
        return buffer.toString();
    }
}
