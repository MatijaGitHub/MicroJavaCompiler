// generated with ast extension for cup
// version 0.8
// 6/1/2023 20:57:28


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Mulop Mulop);
    public void visit(ClassDeclarations ClassDeclarations);
    public void visit(Relop Relop);
    public void visit(ActParsOpt ActParsOpt);
    public void visit(MethodDeclarations MethodDeclarations);
    public void visit(ConstructorDeclarations ConstructorDeclarations);
    public void visit(AssignOpEqual AssignOpEqual);
    public void visit(DesignatorOpt DesignatorOpt);
    public void visit(Addop Addop);
    public void visit(NumberConst NumberConst);
    public void visit(FormsParametars FormsParametars);
    public void visit(Factor Factor);
    public void visit(ConstList ConstList);
    public void visit(Designator Designator);
    public void visit(TypeIdentList TypeIdentList);
    public void visit(AndTerms AndTerms);
    public void visit(Condition Condition);
    public void visit(Statements Statements);
    public void visit(NonEndingTypeIdentList NonEndingTypeIdentList);
    public void visit(AddopTerms AddopTerms);
    public void visit(ConstDeclList ConstDeclList);
    public void visit(OrTerms OrTerms);
    public void visit(NonEndingConstDeclList NonEndingConstDeclList);
    public void visit(EndingTypeIdentList EndingTypeIdentList);
    public void visit(ElseStatement ElseStatement);
    public void visit(Brackets Brackets);
    public void visit(ExprList ExprList);
    public void visit(EndingConstDeclList EndingConstDeclList);
    public void visit(ExtendsType ExtendsType);
    public void visit(VarDeclList VarDeclList);
    public void visit(Expr Expr);
    public void visit(DesignatorList DesignatorList);
    public void visit(TypeOrVoid TypeOrVoid);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(Const Const);
    public void visit(Decl Decl);
    public void visit(Statement Statement);
    public void visit(Expression Expression);
    public void visit(ConstDecl ConstDecl);
    public void visit(CondFact CondFact);
    public void visit(VarDeclarations VarDeclarations);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(MulOperations MulOperations);
    public void visit(AssignOpExpr AssignOpExpr);
    public void visit(IdentExprList IdentExprList);
    public void visit(ModOperation ModOperation);
    public void visit(DivOperation DivOperation);
    public void visit(MulOperation MulOperation);
    public void visit(SubOperation SubOperation);
    public void visit(AddOperation AddOperation);
    public void visit(LoweqOp LoweqOp);
    public void visit(LowOp LowOp);
    public void visit(GrtEqOp GrtEqOp);
    public void visit(GrtOp GrtOp);
    public void visit(NotEqualOp NotEqualOp);
    public void visit(IsEqualOp IsEqualOp);
    public void visit(Assignop Assignop);
    public void visit(Label Label);
    public void visit(DesignatorThis DesignatorThis);
    public void visit(DesignatorIdent DesignatorIdent);
    public void visit(DesignatorExpr DesignatorExpr);
    public void visit(DesignatorDot DesignatorDot);
    public void visit(FuncCall FuncCall);
    public void visit(DesignatorFactorExpr DesignatorFactorExpr);
    public void visit(DesignatorFactorNewAct DesignatorFactorNewAct);
    public void visit(DesignatorFactorNewExpr DesignatorFactorNewExpr);
    public void visit(DesignatorFactorBool DesignatorFactorBool);
    public void visit(DesignatorFactorChar DesignatorFactorChar);
    public void visit(DesignatorFactorNumber DesignatorFactorNumber);
    public void visit(DesignatorFactorAct DesignatorFactorAct);
    public void visit(DesignatorFactor DesignatorFactor);
    public void visit(EndMulOp EndMulOp);
    public void visit(BeginMulOp BeginMulOp);
    public void visit(Term Term);
    public void visit(EndAddopTerm EndAddopTerm);
    public void visit(NotEndAddopTerm NotEndAddopTerm);
    public void visit(Minus Minus);
    public void visit(MinusExpr MinusExpr);
    public void visit(PlusExpr PlusExpr);
    public void visit(TwoCondFact TwoCondFact);
    public void visit(OneCondFact OneCondFact);
    public void visit(NoAndTerms NoAndTerms);
    public void visit(AndTermsC AndTermsC);
    public void visit(CondTerm CondTerm);
    public void visit(NoOrTerms NoOrTerms);
    public void visit(OrTermsC OrTermsC);
    public void visit(ConditionError ConditionError);
    public void visit(ConditionNoError ConditionNoError);
    public void visit(NoExprList NoExprList);
    public void visit(ExprListC ExprListC);
    public void visit(ActPars ActPars);
    public void visit(NoDesignatorList NoDesignatorList);
    public void visit(DesignatorListC DesignatorListC);
    public void visit(NoDesignatorOpt NoDesignatorOpt);
    public void visit(DesignatorOptC DesignatorOptC);
    public void visit(NoActParsOpt NoActParsOpt);
    public void visit(ActParsOptC ActParsOptC);
    public void visit(AssignOpEqualError AssignOpEqualError);
    public void visit(AssignOpEqualNoError AssignOpEqualNoError);
    public void visit(AssignOpExprDec AssignOpExprDec);
    public void visit(AssignOpExprInc AssignOpExprInc);
    public void visit(AssignOpExprParen AssignOpExprParen);
    public void visit(AssignOpExprBase AssignOpExprBase);
    public void visit(DesignatorStatementMul DesignatorStatementMul);
    public void visit(DesignatorStatementOne DesignatorStatementOne);
    public void visit(NoNumberConst NoNumberConst);
    public void visit(NumberConstC NumberConstC);
    public void visit(EndElseStatement EndElseStatement);
    public void visit(EndIfStatement EndIfStatement);
    public void visit(BeginElseStatement BeginElseStatement);
    public void visit(NoElseStatement NoElseStatement);
    public void visit(ElseStatementC ElseStatementC);
    public void visit(NoExpression NoExpression);
    public void visit(ExpressionC ExpressionC);
    public void visit(Foreach Foreach);
    public void visit(While While);
    public void visit(IfStart IfStart);
    public void visit(BeginWhile BeginWhile);
    public void visit(MultipleStatements MultipleStatements);
    public void visit(ForeachStatement ForeachStatement);
    public void visit(PrintStatement PrintStatement);
    public void visit(ReadStatement ReadStatement);
    public void visit(ReturnStatement ReturnStatement);
    public void visit(ContinueStatement ContinueStatement);
    public void visit(BreakStatement BreakStatement);
    public void visit(WhileStatement WhileStatement);
    public void visit(IfStatement IfStatement);
    public void visit(DesignatorStatementType DesignatorStatementType);
    public void visit(EndingTypeIdentListError EndingTypeIdentListError);
    public void visit(EndingTypeIdentListNoError EndingTypeIdentListNoError);
    public void visit(NonEndingTypeIdentListError NonEndingTypeIdentListError);
    public void visit(NonEndingTypeIdentListNoError NonEndingTypeIdentListNoError);
    public void visit(EndingTypeIdentListC EndingTypeIdentListC);
    public void visit(NonEndingTypeIdentListC NonEndingTypeIdentListC);
    public void visit(FormPars FormPars);
    public void visit(NoStatements NoStatements);
    public void visit(StatementsC StatementsC);
    public void visit(NoFormsParametars NoFormsParametars);
    public void visit(FormsParametarsC FormsParametarsC);
    public void visit(VoidType VoidType);
    public void visit(TypeType TypeType);
    public void visit(MethodName MethodName);
    public void visit(MethodDecl MethodDecl);
    public void visit(NoMethodDeclarations NoMethodDeclarations);
    public void visit(MethodDeclarationsC MethodDeclarationsC);
    public void visit(ConstructorDecl ConstructorDecl);
    public void visit(NoConstructorDeclarations NoConstructorDeclarations);
    public void visit(ConstructorDeclarationsC ConstructorDeclarationsC);
    public void visit(NoClassDeclarations NoClassDeclarations);
    public void visit(ClassDeclarationsC ClassDeclarationsC);
    public void visit(NoVarDeclarations NoVarDeclarations);
    public void visit(VarDeclarationsC VarDeclarationsC);
    public void visit(NoExtendsType NoExtendsType);
    public void visit(ExtendsTypeC ExtendsTypeC);
    public void visit(ClassDecl ClassDecl);
    public void visit(NoBrackets NoBrackets);
    public void visit(BracketsC BracketsC);
    public void visit(EndingVarDeclList EndingVarDeclList);
    public void visit(NonEndingVarDeclList NonEndingVarDeclList);
    public void visit(EndingVarDeclListC EndingVarDeclListC);
    public void visit(NonEndingVarDeclListC NonEndingVarDeclListC);
    public void visit(VarDecl VarDecl);
    public void visit(Type Type);
    public void visit(ConstChar ConstChar);
    public void visit(ConstBool ConstBool);
    public void visit(ConstNumber ConstNumber);
    public void visit(NonEndingConstDeclListError NonEndingConstDeclListError);
    public void visit(NonEndingConstDeclListC NonEndingConstDeclListC);
    public void visit(EndingConstDeclListError EndingConstDeclListError);
    public void visit(EndingConstDeclListC EndingConstDeclListC);
    public void visit(EndingConstDeclListC1 EndingConstDeclListC1);
    public void visit(NonEndingConstDeclListC1 NonEndingConstDeclListC1);
    public void visit(ConstDeclC ConstDeclC);
    public void visit(NoMethodDecl NoMethodDecl);
    public void visit(MethodDeclarationList MethodDeclarationList);
    public void visit(NoDecl NoDecl);
    public void visit(DeclClass DeclClass);
    public void visit(DeclVar DeclVar);
    public void visit(DeclConst DeclConst);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
