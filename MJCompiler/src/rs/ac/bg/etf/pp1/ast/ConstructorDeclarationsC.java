// generated with ast extension for cup
// version 0.8
// 6/1/2023 1:50:19


package rs.ac.bg.etf.pp1.ast;

public class ConstructorDeclarationsC extends ConstructorDeclarations {

    private ConstructorDeclarations ConstructorDeclarations;
    private ConstructorDecl ConstructorDecl;

    public ConstructorDeclarationsC (ConstructorDeclarations ConstructorDeclarations, ConstructorDecl ConstructorDecl) {
        this.ConstructorDeclarations=ConstructorDeclarations;
        if(ConstructorDeclarations!=null) ConstructorDeclarations.setParent(this);
        this.ConstructorDecl=ConstructorDecl;
        if(ConstructorDecl!=null) ConstructorDecl.setParent(this);
    }

    public ConstructorDeclarations getConstructorDeclarations() {
        return ConstructorDeclarations;
    }

    public void setConstructorDeclarations(ConstructorDeclarations ConstructorDeclarations) {
        this.ConstructorDeclarations=ConstructorDeclarations;
    }

    public ConstructorDecl getConstructorDecl() {
        return ConstructorDecl;
    }

    public void setConstructorDecl(ConstructorDecl ConstructorDecl) {
        this.ConstructorDecl=ConstructorDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstructorDeclarations!=null) ConstructorDeclarations.accept(visitor);
        if(ConstructorDecl!=null) ConstructorDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstructorDeclarations!=null) ConstructorDeclarations.traverseTopDown(visitor);
        if(ConstructorDecl!=null) ConstructorDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstructorDeclarations!=null) ConstructorDeclarations.traverseBottomUp(visitor);
        if(ConstructorDecl!=null) ConstructorDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstructorDeclarationsC(\n");

        if(ConstructorDeclarations!=null)
            buffer.append(ConstructorDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstructorDecl!=null)
            buffer.append(ConstructorDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstructorDeclarationsC]");
        return buffer.toString();
    }
}
