// generated with ast extension for cup
// version 0.8
// 5/1/2023 18:32:34


package rs.ac.bg.etf.pp1.ast;

public class ClassDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String I1;
    private ExtendsType ExtendsType;
    private VarDeclarations VarDeclarations;
    private ClassDeclarations ClassDeclarations;

    public ClassDecl (String I1, ExtendsType ExtendsType, VarDeclarations VarDeclarations, ClassDeclarations ClassDeclarations) {
        this.I1=I1;
        this.ExtendsType=ExtendsType;
        if(ExtendsType!=null) ExtendsType.setParent(this);
        this.VarDeclarations=VarDeclarations;
        if(VarDeclarations!=null) VarDeclarations.setParent(this);
        this.ClassDeclarations=ClassDeclarations;
        if(ClassDeclarations!=null) ClassDeclarations.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public ExtendsType getExtendsType() {
        return ExtendsType;
    }

    public void setExtendsType(ExtendsType ExtendsType) {
        this.ExtendsType=ExtendsType;
    }

    public VarDeclarations getVarDeclarations() {
        return VarDeclarations;
    }

    public void setVarDeclarations(VarDeclarations VarDeclarations) {
        this.VarDeclarations=VarDeclarations;
    }

    public ClassDeclarations getClassDeclarations() {
        return ClassDeclarations;
    }

    public void setClassDeclarations(ClassDeclarations ClassDeclarations) {
        this.ClassDeclarations=ClassDeclarations;
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
        if(ExtendsType!=null) ExtendsType.accept(visitor);
        if(VarDeclarations!=null) VarDeclarations.accept(visitor);
        if(ClassDeclarations!=null) ClassDeclarations.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExtendsType!=null) ExtendsType.traverseTopDown(visitor);
        if(VarDeclarations!=null) VarDeclarations.traverseTopDown(visitor);
        if(ClassDeclarations!=null) ClassDeclarations.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExtendsType!=null) ExtendsType.traverseBottomUp(visitor);
        if(VarDeclarations!=null) VarDeclarations.traverseBottomUp(visitor);
        if(ClassDeclarations!=null) ClassDeclarations.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDecl(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(ExtendsType!=null)
            buffer.append(ExtendsType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclarations!=null)
            buffer.append(VarDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassDeclarations!=null)
            buffer.append(ClassDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDecl]");
        return buffer.toString();
    }
}
