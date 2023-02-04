// generated with ast extension for cup
// version 0.8
// 4/1/2023 1:1:55


package rs.ac.bg.etf.pp1.ast;

public class Term implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private MulOperations MulOperations;

    public Term (MulOperations MulOperations) {
        this.MulOperations=MulOperations;
        if(MulOperations!=null) MulOperations.setParent(this);
    }

    public MulOperations getMulOperations() {
        return MulOperations;
    }

    public void setMulOperations(MulOperations MulOperations) {
        this.MulOperations=MulOperations;
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
        if(MulOperations!=null) MulOperations.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MulOperations!=null) MulOperations.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MulOperations!=null) MulOperations.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Term(\n");

        if(MulOperations!=null)
            buffer.append(MulOperations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Term]");
        return buffer.toString();
    }
}
