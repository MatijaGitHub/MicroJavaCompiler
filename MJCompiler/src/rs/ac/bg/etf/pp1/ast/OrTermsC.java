// generated with ast extension for cup
// version 0.8
// 6/1/2023 16:41:21


package rs.ac.bg.etf.pp1.ast;

public class OrTermsC extends OrTerms {

    private OrTerms OrTerms;
    private CondTerm CondTerm;

    public OrTermsC (OrTerms OrTerms, CondTerm CondTerm) {
        this.OrTerms=OrTerms;
        if(OrTerms!=null) OrTerms.setParent(this);
        this.CondTerm=CondTerm;
        if(CondTerm!=null) CondTerm.setParent(this);
    }

    public OrTerms getOrTerms() {
        return OrTerms;
    }

    public void setOrTerms(OrTerms OrTerms) {
        this.OrTerms=OrTerms;
    }

    public CondTerm getCondTerm() {
        return CondTerm;
    }

    public void setCondTerm(CondTerm CondTerm) {
        this.CondTerm=CondTerm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OrTerms!=null) OrTerms.accept(visitor);
        if(CondTerm!=null) CondTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OrTerms!=null) OrTerms.traverseTopDown(visitor);
        if(CondTerm!=null) CondTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OrTerms!=null) OrTerms.traverseBottomUp(visitor);
        if(CondTerm!=null) CondTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OrTermsC(\n");

        if(OrTerms!=null)
            buffer.append(OrTerms.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondTerm!=null)
            buffer.append(CondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OrTermsC]");
        return buffer.toString();
    }
}
