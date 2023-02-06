// generated with ast extension for cup
// version 0.8
// 6/1/2023 1:50:19


package rs.ac.bg.etf.pp1.ast;

public class ConditionNoError extends Condition {

    private OrTerms OrTerms;

    public ConditionNoError (OrTerms OrTerms) {
        this.OrTerms=OrTerms;
        if(OrTerms!=null) OrTerms.setParent(this);
    }

    public OrTerms getOrTerms() {
        return OrTerms;
    }

    public void setOrTerms(OrTerms OrTerms) {
        this.OrTerms=OrTerms;
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
        buffer.append("ConditionNoError(\n");

        if(OrTerms!=null)
            buffer.append(OrTerms.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConditionNoError]");
        return buffer.toString();
    }
}
