// generated with ast extension for cup
// version 0.8
// 6/1/2023 16:41:21


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclarationsC extends MethodDeclarations {

    private MethodDecl MethodDecl;
    private MethodDeclarations MethodDeclarations;

    public MethodDeclarationsC (MethodDecl MethodDecl, MethodDeclarations MethodDeclarations) {
        this.MethodDecl=MethodDecl;
        if(MethodDecl!=null) MethodDecl.setParent(this);
        this.MethodDeclarations=MethodDeclarations;
        if(MethodDeclarations!=null) MethodDeclarations.setParent(this);
    }

    public MethodDecl getMethodDecl() {
        return MethodDecl;
    }

    public void setMethodDecl(MethodDecl MethodDecl) {
        this.MethodDecl=MethodDecl;
    }

    public MethodDeclarations getMethodDeclarations() {
        return MethodDeclarations;
    }

    public void setMethodDeclarations(MethodDeclarations MethodDeclarations) {
        this.MethodDeclarations=MethodDeclarations;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodDecl!=null) MethodDecl.accept(visitor);
        if(MethodDeclarations!=null) MethodDeclarations.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDecl!=null) MethodDecl.traverseTopDown(visitor);
        if(MethodDeclarations!=null) MethodDeclarations.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDecl!=null) MethodDecl.traverseBottomUp(visitor);
        if(MethodDeclarations!=null) MethodDeclarations.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclarationsC(\n");

        if(MethodDecl!=null)
            buffer.append(MethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclarations!=null)
            buffer.append(MethodDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclarationsC]");
        return buffer.toString();
    }
}
