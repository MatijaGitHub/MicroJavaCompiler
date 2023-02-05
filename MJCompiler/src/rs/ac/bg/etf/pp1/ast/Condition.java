// generated with ast extension for cup
// version 0.8
// 4/1/2023 19:53:34


package rs.ac.bg.etf.pp1.ast;

public class Condition implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private OrTerms OrTerms;

    public Condition (OrTerms OrTerms) {
        this.OrTerms=OrTerms;
        if(OrTerms!=null) OrTerms.setParent(this);
    }

    public OrTerms getOrTerms() {
        return OrTerms;
    }

    public void setOrTerms(OrTerms OrTerms) {
        this.OrTerms=OrTerms;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OrTerms!=null) OrTerms.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OrTerms!=null) OrTerms.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OrTerms!=null) OrTerms.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Condition(\n");

        if(OrTerms!=null)
            buffer.append(OrTerms.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Condition]");
        return buffer.toString();
    }
}
