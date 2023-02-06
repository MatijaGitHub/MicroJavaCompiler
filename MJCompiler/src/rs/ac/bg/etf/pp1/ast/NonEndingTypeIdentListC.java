// generated with ast extension for cup
// version 0.8
// 6/1/2023 20:57:28


package rs.ac.bg.etf.pp1.ast;

public class NonEndingTypeIdentListC extends TypeIdentList {

    private NonEndingTypeIdentList NonEndingTypeIdentList;
    private TypeIdentList TypeIdentList;

    public NonEndingTypeIdentListC (NonEndingTypeIdentList NonEndingTypeIdentList, TypeIdentList TypeIdentList) {
        this.NonEndingTypeIdentList=NonEndingTypeIdentList;
        if(NonEndingTypeIdentList!=null) NonEndingTypeIdentList.setParent(this);
        this.TypeIdentList=TypeIdentList;
        if(TypeIdentList!=null) TypeIdentList.setParent(this);
    }

    public NonEndingTypeIdentList getNonEndingTypeIdentList() {
        return NonEndingTypeIdentList;
    }

    public void setNonEndingTypeIdentList(NonEndingTypeIdentList NonEndingTypeIdentList) {
        this.NonEndingTypeIdentList=NonEndingTypeIdentList;
    }

    public TypeIdentList getTypeIdentList() {
        return TypeIdentList;
    }

    public void setTypeIdentList(TypeIdentList TypeIdentList) {
        this.TypeIdentList=TypeIdentList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(NonEndingTypeIdentList!=null) NonEndingTypeIdentList.accept(visitor);
        if(TypeIdentList!=null) TypeIdentList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NonEndingTypeIdentList!=null) NonEndingTypeIdentList.traverseTopDown(visitor);
        if(TypeIdentList!=null) TypeIdentList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NonEndingTypeIdentList!=null) NonEndingTypeIdentList.traverseBottomUp(visitor);
        if(TypeIdentList!=null) TypeIdentList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NonEndingTypeIdentListC(\n");

        if(NonEndingTypeIdentList!=null)
            buffer.append(NonEndingTypeIdentList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TypeIdentList!=null)
            buffer.append(TypeIdentList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NonEndingTypeIdentListC]");
        return buffer.toString();
    }
}
