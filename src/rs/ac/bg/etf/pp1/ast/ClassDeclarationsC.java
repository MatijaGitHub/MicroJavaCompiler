// generated with ast extension for cup
// version 0.8
// 3/1/2023 18:14:20


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclarationsC extends ClassDeclarations {

    private ConstructorDeclarations ConstructorDeclarations;
    private MethodDeclarations MethodDeclarations;

    public ClassDeclarationsC (ConstructorDeclarations ConstructorDeclarations, MethodDeclarations MethodDeclarations) {
        this.ConstructorDeclarations=ConstructorDeclarations;
        if(ConstructorDeclarations!=null) ConstructorDeclarations.setParent(this);
        this.MethodDeclarations=MethodDeclarations;
        if(MethodDeclarations!=null) MethodDeclarations.setParent(this);
    }

    public ConstructorDeclarations getConstructorDeclarations() {
        return ConstructorDeclarations;
    }

    public void setConstructorDeclarations(ConstructorDeclarations ConstructorDeclarations) {
        this.ConstructorDeclarations=ConstructorDeclarations;
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
        if(ConstructorDeclarations!=null) ConstructorDeclarations.accept(visitor);
        if(MethodDeclarations!=null) MethodDeclarations.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstructorDeclarations!=null) ConstructorDeclarations.traverseTopDown(visitor);
        if(MethodDeclarations!=null) MethodDeclarations.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstructorDeclarations!=null) ConstructorDeclarations.traverseBottomUp(visitor);
        if(MethodDeclarations!=null) MethodDeclarations.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclarationsC(\n");

        if(ConstructorDeclarations!=null)
            buffer.append(ConstructorDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclarations!=null)
            buffer.append(MethodDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclarationsC]");
        return buffer.toString();
    }
}
