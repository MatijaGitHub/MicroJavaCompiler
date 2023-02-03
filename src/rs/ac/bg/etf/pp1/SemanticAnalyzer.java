package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.text.TabExpander;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;
import rs.etf.pp1.symboltable.visitors.SymbolTableVisitor;
import rs.ac.bg.etf.pp1.ast.*;
public class SemanticAnalyzer extends VisitorAdaptor {

	private Logger log = Logger.getLogger(getClass());
	private boolean errorDetected = false;
	private boolean mainFound = false;
	private static int programVarNumber = 0;
	private Struct currType = null;
	private Struct exprType = null;
	private Struct exprTermType = null;
	private Struct returnExprType = null;
	private Struct localExprType = null;
	private Obj currMethod = null;
	private int currMethParamCnt = 0;
	private int numOfFactors = 0;
	private int numOfTerms = 0;
	private int methParamCnt = 0;
	private boolean isClassAtr = false;
	private boolean isNestedExpr = false;
	private boolean arrayError = false;
	private boolean typeError = false;
	private Struct arrayAssignTypes = null;
	private boolean methodCallError = false;
	private boolean isVarArray = false;
	private boolean exprTypeError = false;
	private HashMap<Obj, ArrayList<Struct>> methodParams = new HashMap<>();
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder("Greska");
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line).append(": ");
		else 
			msg.append(": ");
		msg.append(message);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
	
		StringBuilder msg = new StringBuilder("Info");
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line).append(": ");
		else 
			msg.append(": ");
		msg.append(message);
		log.info(msg.toString());
	}
	private static Struct boolType;
	public SemanticAnalyzer() {
		boolType = new Struct(Struct.Bool);
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
	}
	
	public String getType(Struct s) {

		switch (s.getKind()) {
			case Struct.None: return "none";
			case Struct.Int: return "int";
			case Struct.Char: return "char";
			case Struct.Array: return "array";
			case Struct.Bool: return "bool";
			case Struct.Class: return "class";
			case Struct.Interface: return "interface";
			case Struct.Enum: return "enum";
			default: return "";
		}
	
	}
	
    public void visit(ProgName progName) {
    	progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
    	Tab.openScope();
    }
    
    
    public void visit(Program program) {
    	programVarNumber = Tab.currentScope.getnVars();
		if(mainFound == false){
			report_error("Main method not found!", null);
		}
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	Tab.closeScope();
    }
    
    
    public void visit(Type type) {
    	Obj typeNode = Tab.find(type.getTypeName());
    	
    	if(typeNode == Tab.noObj) {
    		report_error("Tip " + type.getTypeName() + " nije pronadjen u tabeli simbola!", type);
    		type.struct = Tab.noType;
    	} else {
    		if(Obj.Type == typeNode.getKind()) {
    			type.struct = typeNode.getType();
    		} else {
    			report_error("Ime " + type.getTypeName() + " ne predstavlja tip!", type);
        		type.struct = Tab.noType;
    		}
    	}
    	
    	currType = type.struct;
    	
    }
    
    private boolean constantNameDeclared(String constantName, SyntaxNode info) {
    	Obj constNameNode = Tab.find(constantName);
    	if(constNameNode != Tab.noObj) {
			report_error("Ime " + constantName + " je vec deklarisano!", info);    		
			return false;
    	}
    	return true; 
    }
   private boolean constantTypeMatched(String cName, Struct cType, SyntaxNode info) {
    	if(!cType.equals(currType)) {
			report_error("Deklarisani tip konstante '" + getType(currType) + "' nije isti kao tip vrednosti koja se dodeljuje '" + getType(cType) + "'!", info);    		    		
    		return false;
    	}
    	
    	return true; 
   }
   public void visit(ConstChar charVal) {
		SyntaxNode nameOfConst = charVal.getParent();
		String identName = "";
		if(nameOfConst instanceof EndingConstDeclListC) {
			identName = ((EndingConstDeclListC) nameOfConst).getConstName();
		}
		else if(nameOfConst instanceof NonEndingConstDeclListC) {
			identName = ((NonEndingConstDeclListC) nameOfConst).getConstName();
		}
	   	if(!constantNameDeclared(identName, charVal)) {
	   		return;
	   	}
	   	if(!constantTypeMatched(identName, Tab.charType, charVal)) {
	   		return;
	   	}
	   	Obj constNode = Tab.insert(Obj.Con, identName, Tab.charType);
		report_info("Kreirana je konstanta " + getType(Tab.charType) + " " + identName + " = " + charVal.getC() + ".", charVal);
		constNode.setAdr(charVal.getC());
	}
   public void visit(ConstNumber numVal) {
		SyntaxNode nameOfConst = numVal.getParent();
		String identName = "";
		if(nameOfConst instanceof EndingConstDeclListC) {
			identName = ((EndingConstDeclListC) nameOfConst).getConstName();
		}
		else if(nameOfConst instanceof NonEndingConstDeclListC) {
			identName = ((NonEndingConstDeclListC) nameOfConst).getConstName();
		}
	   	if(!constantNameDeclared(identName, numVal)) {
	   		return;
	   	}
	   	if(!constantTypeMatched(identName, Tab.intType, numVal)) {
	   		return;
	   	}
	   	Obj constNode = Tab.insert(Obj.Con, identName, Tab.intType);
		report_info("Kreirana je konstanta " + getType(Tab.intType) + " " + identName + " = " + numVal.getN() + ".", numVal);
		constNode.setAdr(numVal.getN());
	}
   public void visit(ConstBool boolVal) {
	   SyntaxNode nameOfConst = boolVal.getParent();
		String identName = "";
		if(nameOfConst instanceof EndingConstDeclListC) {
			identName = ((EndingConstDeclListC) nameOfConst).getConstName();
		}
		else if(nameOfConst instanceof NonEndingConstDeclListC) {
			identName = ((NonEndingConstDeclListC) nameOfConst).getConstName();
		}
		if(!constantNameDeclared(identName, boolVal)) {
	   		return;
	   	}
	   	if(!constantTypeMatched(identName, boolType, boolVal)) {
	   		return;
	   	}
	   	Obj constNode = Tab.insert(Obj.Con, identName, boolType);
		report_info("Kreirana je konstanta " + getType(boolType) + " " + identName + " = " + boolVal.getB() + ".", boolVal);
		constNode.setAdr(boolVal.getB()?1:0);


		
   }
   
   private Obj insertVarDecl(String varName, SyntaxNode info) {
	   Struct varType = currType;
	   if(isVarArray) {
		   varType = new Struct(Struct.Array, currType);
	   }
	   Obj insertedNode;
	   if(isClassAtr) {
		   insertedNode = Tab.insert(Obj.Fld, varName, varType);
	   }
	   else {
		   insertedNode = Tab.insert(Obj.Var, varName, varType);
	   }
	   report_info("Inserted " + varName + " variable!", info);
	   return insertedNode;
   }
   private boolean isVarDeclared(String varName) {
	   Obj var = Tab.find(varName);
	   if(var != Tab.noObj) {
		   if(Tab.currentScope.findSymbol(varName) != null) {
			   report_error("Variable " + varName + " already declared", null);
			   return true;
		   }
	   }
	   return false;
   }
   public void visit(NonEndingVarDeclList var) {
	   String varName = var.getVarName();
	   if(!isVarDeclared(varName)) {
		   Obj varDeclared = insertVarDecl(varName, var);
		   
	   }
   }
   public void visit(EndingVarDeclList var) {
	   String varName = var.getVarName();
	   if(!isVarDeclared(varName)) {
		   Obj varDeclared = insertVarDecl(varName, var);
	   }
   }
   public void visit(BracketsC brackets) {
	   isVarArray = true;
   }
   public void visit(NoBrackets noBrackets) {
	   isVarArray = false;
   }
   public void visit(VoidType voidType) {
	   currType = new Struct(Struct.None);
   }
   private boolean isFuncNameDeclared(String funcName) {
	   Obj func = Tab.find(funcName);
	   if(func != Tab.noObj) {
		   report_error("Function " + funcName + " already declared!", null);
		   return true;
	   }
	   return false;
	   
   }
   private Obj declareFunction(String funcName, SyntaxNode info) {
	   Struct funcType = currType;
	   Obj insertedNode;
	   insertedNode = Tab.insert(Obj.Meth, funcName, funcType);
	   return insertedNode;
   }
   public void visit(MethodName meth) {
	   String methName = meth.getMethName();
	   if(!isFuncNameDeclared(methName)) {
		   Obj declaredMeth = declareFunction(methName, meth);
		   currMethod = declaredMeth;
		   Tab.openScope();
		   report_info("Inserted " + methName + " method!", meth);
	   }
	   
	   
   }
   public void visit(MethodDecl meth) {
	 currMethod.setLevel(currMethParamCnt);
	 if(currMethod.getLevel() == 0 && currMethod.getName().equals("main")  && currMethod.getType().getKind() == Struct.None) {
		 mainFound = true;
	 }
	 Tab.chainLocalSymbols(currMethod);
	 Tab.closeScope();
	 currMethParamCnt = 0;
	 
   }
   public void visit(NonEndingTypeIdentList param) {
	   currMethParamCnt++;
	   String paramName = param.getIdentName();
	   Struct paramType;
	   if(param.getBrackets() instanceof BracketsC) {
		   paramType = new Struct(Struct.Array);
		   paramType.setElementType(currType);
	   }
	   else {
		   paramType = currType;
	   }
	   Tab.insert(Obj.Var, paramName, paramType);
	   if(!methodParams.containsKey(currMethod)) {
		   methodParams.put(currMethod, new ArrayList<>());
	   }
	   ArrayList<Struct> params = methodParams.get(currMethod);
	   params.add(paramType);
	   methodParams.put(currMethod, params);
	   
	   
	   
	   
   }
   public void visit(EndingTypeIdentList param) {
	   currMethParamCnt++;
	   String paramName = param.getIdentName();
	   Struct paramType;
	   if(param.getBrackets() instanceof BracketsC) {
		   paramType = new Struct(Struct.Array);
		   paramType.setElementType(currType);
	   }
	   else {
		   paramType = currType;
	   }
	   Tab.insert(Obj.Var, paramName, paramType);
	   if(!methodParams.containsKey(currMethod)) {
		   methodParams.put(currMethod, new ArrayList<>());
	   }
	   ArrayList<Struct> params = methodParams.get(currMethod);
	   params.add(paramType);
	   methodParams.put(currMethod, params);
   }
   public void visit(NoFormsParametars noformparams) {
	   methodParams.put(currMethod, new ArrayList<>());
   }
   public void visit(ReturnStatement returnStmt) {
	   if(currMethod.getType().getKind() == Struct.None && numOfTerms > 0) {
		   report_error("return type is void!", returnStmt);

	   }
	   if((currMethod.getType().getKind() != Struct.None)&&(currMethod.getType().getKind() != returnExprType.getKind())) {
		   if(!exprTypeError) {
			   report_error("return expression type " + getType(returnExprType) +  " does not match return type "+ getType(currMethod.getType()) +" !", returnStmt);
			   
		   }
	   }
	   //report_info("Num of terms: " + numOfTerms, null);
	   numOfTerms = 0;
	   exprTypeError = false;
   }
   
   public void visit(DesignatorFactor designatorFactor) {
	   numOfFactors++;
	   if(designatorFactor.getDesignator().obj.getKind() == Obj.Meth) {
		   report_error("Method call error!" , designatorFactor);
		   designatorFactor.struct = new Struct(Struct.None);
	   }
	   else {
		   if(designatorFactor.getDesignator() instanceof DesignatorIdent) {
			   designatorFactor.struct = designatorFactor.getDesignator().obj.getType();
		   }
		   else if(designatorFactor.getDesignator() instanceof DesignatorExpr) {
			   designatorFactor.struct = designatorFactor.getDesignator().obj.getType().getElemType();
		   }
	   }
	  
	   
	   
   }
   public void visit(DesignatorFactorAct designatorFactorAct) {
	   numOfFactors++;
	   if(methodCallError) {
		   report_error("Method error!", designatorFactorAct);
	   }
	   int paramCnt = methodParams.get(designatorFactorAct.getDesignator().obj).size();
	   if(paramCnt != designatorFactorAct.getActParsOpt().obj.getLevel()) {
		   report_error("Param count " + designatorFactorAct.getActParsOpt().obj.getLevel()+" doesnt match with method param count " +paramCnt+ "!", designatorFactorAct);
	   }
	   designatorFactorAct.struct = designatorFactorAct.getDesignator().obj.getType();
	   methodCallError = false;
   }
   public void visit(DesignatorFactorNumber designatorFactorNumber) {
	   numOfFactors++;
	   designatorFactorNumber.struct = new Struct(Struct.Int);
	  
	   
   }
   public void visit(DesignatorFactorChar designatorFactorChar) {
	   numOfFactors++;
	   designatorFactorChar.struct = new Struct(Struct.Char);
   }
	public void visit(DesignatorFactorBool designatorFactorBool) {
		numOfFactors++;
		designatorFactorBool.struct = new Struct(Struct.Bool); 
	}
	public void visit(DesignatorFactorExpr designatorFactorExpr) {
		numOfFactors++;
		designatorFactorExpr.struct = designatorFactorExpr.getExpr().struct;
	}
	public void visit(BeginMulOp beginMulOp) {
		if(exprType == null || exprType.getKind() != Struct.None) {
			if(beginMulOp.getFactor().struct.getKind() != Struct.Int) {
				report_error("All mulop factors must be of type int!", beginMulOp);
				exprType = new Struct(Struct.None);
				return;
			}
			if(exprType == null) {
				exprType = beginMulOp.getFactor().struct;
			}
			else if(exprType.getKind() != beginMulOp.getFactor().struct.getKind()) {
				report_error("All mulop factors must be of same type!", beginMulOp);
				exprType = new Struct(Struct.None);
			}
		}
		
		
	}
	public void visit(EndMulOp endMulOp) {
		if(exprType == null || exprType.getKind() != Struct.None) {
			if(exprType != null && endMulOp.getFactor().struct.getKind() != Struct.Int) {
				report_error("All mulop factors must be of type int!", endMulOp);
				exprType = new Struct(Struct.None);
				return;
			}
			if(exprType == null) {
				exprType = endMulOp.getFactor().struct;
			}
			else if(exprType.getKind() != endMulOp.getFactor().struct.getKind()) {
				report_error("All mulop factors must be of same type!", endMulOp);
				exprType = new Struct(Struct.None);
			}
		}
		
	}
	public void visit(Term term) {
		numOfFactors = 0;
		term.struct = exprType;
		exprType = null;
	}
	public void visit(NotEndAddopTerm addopTerms) {
		numOfTerms++;
		numOfFactors = 0;
		if(exprTermType == null || exprTermType.getKind() != Struct.None) {
			if(addopTerms.getTerm().struct.getKind() != Struct.Int) {
				report_error("All addop factors must be of type int!", addopTerms);
				exprTermType = new Struct(Struct.None);
				return;
			}
			if(exprTermType == null) {
				exprTermType = addopTerms.getTerm().struct;
			}
			else if(exprTermType.getKind() != addopTerms.getTerm().struct.getKind()) {
				report_error("All addop factors must be of same type!", addopTerms);
				exprTermType = new Struct(Struct.None);
			}
		}
	}
	public void visit(EndAddopTerm addopTerms) {
		numOfTerms++;
		numOfFactors = 0;
		if(exprTermType == null || exprTermType.getKind() != Struct.None) {
			if(exprTermType != null && addopTerms.getTerm().struct.getKind() != Struct.Int) {
				report_error("All addop factors must be of type int!", addopTerms);
				exprTermType = new Struct(Struct.None);
				return;
			}
			if(exprTermType == null) {
				exprTermType = addopTerms.getTerm().struct;
			}
			else if(exprTermType.getKind() != addopTerms.getTerm().struct.getKind()) {
				report_error("All addop factors must be of same type!", addopTerms);
				exprTermType = new Struct(Struct.None);
			}
		}
	}
	public void visit(PlusExpr plusExpr) {
		numOfTerms++;
		numOfFactors = 0;
		if(exprTermType.getKind() == Struct.None) {
			report_error("Expr error!", plusExpr);
		}
		plusExpr.struct = exprTermType;
		returnExprType = exprTermType;
		exprTermType = null;
		
	
	}
	public void visit(MinusExpr minusExpr) {
		numOfTerms++;
		numOfFactors = 0;
		if(exprTermType.getKind() != Struct.Int) {
			report_error("Expr error!", minusExpr);
		}
		minusExpr.struct = exprTermType;
		returnExprType = exprTermType;
		exprTermType = null;
		
		
	}
	
	public void visit(ExpressionC expression) {
		if(exprTypeError) {
			report_error("Expression in wrong format!", expression);
		}
		//numOfTerms = 0;
		numOfFactors = 0;
		
		
	}
	private String getCurrCalledMethName(SyntaxNode node) {
		while(!(node instanceof DesignatorFactorAct || node instanceof DesignatorStatementOne)) {
			node = node.getParent();
		}
		String methName = "";
		if(node instanceof DesignatorStatementOne) {
			methName = (((DesignatorStatementOne)(node)).getDesignator()).obj.getName();
		}
		else {
			methName = (((DesignatorFactorAct)(node)).getDesignator()).obj.getName();
		}
		
		return methName;
	}
	public void visit(ExprListC exprc) {
		exprc.obj = new Obj(Obj.Meth, null, arrayAssignTypes);
		exprc.obj.setLevel(exprc.getExprList().obj.getLevel() + 1);
		Struct exprType = exprc.getExpr().struct;
		Obj func = Tab.find(getCurrCalledMethName(exprc));
		ArrayList<Struct> params =  methodParams.get(func);
		if(params!=null) {
			if( exprc.obj.getLevel() <= params.size()) {
				if(params.get(params.size()- exprc.obj.getLevel()).getKind() != exprType.getKind()) {
					report_error("Type mismatch at pos: " + (params.size()- exprc.obj.getLevel()), exprc);
				}
			}
			else if(params.get(params.size() - 1).getKind() == Struct.Array) {
				if(params.get(params.size() - 1).getElemType().getKind() != exprType.getElemType().getKind()) {
					report_error("Array type mismatch at pos: " + (params.size()- exprc.obj.getLevel()), exprc);
				}
			}
		
		}
		else {
			report_error("Undefined meth!", exprc);
		}
	}
	public void visit(NoExprList noexprc) {
		noexprc.obj = new Obj(Obj.Meth, null, arrayAssignTypes);
		noexprc.obj.setLevel(1);
		Obj func = Tab.find(getCurrCalledMethName(noexprc));
		Struct exprType = noexprc.getExpr().struct;
		ArrayList<Struct> params =  methodParams.get(func);
		if(params!=null) {
			if(params.get(params.size() - 1).getKind() != exprType.getKind()) {
				report_error("Type mismatch at pos: " + (params.size()- noexprc.obj.getLevel()), noexprc);
			}
			else if(params.get(params.size() - 1).getKind() == Struct.Array) {
				if(params.get(params.size() - 1).getElemType().getKind() != exprType.getElemType().getKind()) {
					report_error("Array type mismatch at pos: " + (params.size()- noexprc.obj.getLevel()), noexprc);
				}
			}
		}
		else {
			report_error("Undefined meth!", noexprc);
		}
	}
	public void visit(ActPars actpars) {
		actpars.obj = new Obj(Obj.Meth, null, arrayAssignTypes);
		actpars.obj.setLevel(actpars.getExprList().obj.getLevel());
	}
	public void visit(ActParsOptC actparsoptc) {
		actparsoptc.obj = new Obj(Obj.Meth, null, arrayAssignTypes);
		actparsoptc.obj.setLevel(actparsoptc.getActPars().obj.getLevel());
	}
	public void visit(NoActParsOpt noactparsopt) {
		noactparsopt.obj = new Obj(Obj.Meth, null, arrayAssignTypes);
		noactparsopt.obj.setLevel(0);
	}
	public void visit(DesignatorIdent designatorIdent) {		
		String designName = designatorIdent.getDesignName();
		Obj designator = Tab.find(designName);
		designatorIdent.obj = designator;
		if(designator == Tab.noObj) {
			exprTypeError = true;
			report_error("Var " + designName + " is not declared!", designatorIdent);
		}	
	}
	public void visit(DesignatorExpr designatorExpr) {
		//numOfFactors++;
		
		String designName = ((DesignatorIdent)designatorExpr.getDesignator()).getDesignName();
		Obj designator = Tab.find(designName);
		designatorExpr.obj = designator;
		if(designatorExpr.getExpr().struct.getKind() != Struct.Int) {
			arrayError = true;
			exprTypeError = true;
		}
		                   
		if(designator == Tab.noObj) {
			report_error(designatorExpr.toString(), null);
			exprTypeError = true;
			report_error("Var " + designName + " is not declared!", designatorExpr);
		}
		else {
			if(exprType!=null && designator.getType().getElemType().getKind() != Struct.Int) {
				//exprType = new Struct(Struct.None);
			}
			else if (!(designatorExpr.getParent() instanceof DesignatorStatementOne)){
				//exprType = designator.getType().getElemType(); //sus
			}
		}
		
	
		
	}

	public void visit(DesignatorStatementType designatorStatementType) {
		if(arrayError) {
			report_error("Array error!", designatorStatementType);
		}
		if(typeError) {
			report_error("Type error!", designatorStatementType);
		}
		arrayError = false;
		exprTypeError = false;
		typeError = false;
	}
	public void visit(DesignatorStatementOne designatorStmtOne) {
		Struct designatorType = designatorStmtOne.getDesignator().obj.getType();
		if(designatorType.getKind() == Struct.Array) {
			designatorType = designatorType.getElemType();
		}
		if(!(designatorStmtOne.getAssignOpExpr() instanceof AssignOpExprParen)) {
			if(designatorStmtOne.getAssignOpExpr().struct == null || (designatorStmtOne.getAssignOpExpr().struct.getKind()!=designatorType.getKind())) {
				typeError = true;
			}
			if(!(designatorStmtOne.getDesignator() instanceof DesignatorExpr)) {
				Obj symbol = Tab.find(((DesignatorIdent)(designatorStmtOne.getDesignator())).getDesignName());
				if(symbol.getType().getKind() == Struct.Array || symbol.getKind() == Obj.Meth) {
					
					report_error("Elem " + symbol.getName() + " not an array element or var type!", designatorStmtOne);
					return;
				}
				
			}
		}
		
	}
	public void visit(AssignOpExprParen assignOpExprParen) {
		int paramCnt = methodParams.get(((DesignatorStatementOne)assignOpExprParen.getParent()).getDesignator().obj).size();
		if(paramCnt != assignOpExprParen.getActParsOpt().obj.getLevel()) {
			 report_error("Param count " + assignOpExprParen.getActParsOpt().obj.getLevel()+" doesnt match with method param count " +paramCnt+ " on method call!", assignOpExprParen);
		}
	}
	public void visit(AssignOpExprBase assignOpExprBase) {
		assignOpExprBase.struct = assignOpExprBase.getExpr().struct;
	}
	public void visit(AssignOpExprInc assignOpExprInc) {
		assignOpExprInc.struct = new Struct(Struct.Int);
	}
	public void visit(AssignOpExprDec assignOpExprDec) {
		assignOpExprDec.struct = new Struct(Struct.Int);
	}
	
	public void visit(DesignatorStatementMul designatorStatementMul) {
		Struct rvalue = designatorStatementMul.getDesignator().obj.getType();
		if(rvalue.getKind() != Struct.Array) {
			report_error("Right value must be of type array!", designatorStatementMul);
			arrayAssignTypes = null;
			return;
		}
		if(arrayAssignTypes == null) {
			report_error("No vars to assign value on left side!", designatorStatementMul);
			arrayAssignTypes = null;
			return;
		}
		if(arrayAssignTypes.getKind() == Struct.None) {
			report_error("Left side var types dont match!", designatorStatementMul);
			arrayAssignTypes = null;
			return;
		}
		if(arrayAssignTypes.getKind() != rvalue.getElemType().getKind()) {
			report_error("Left var types dont match with right type!", designatorStatementMul);
		}
		arrayAssignTypes = null;
		
	}
	public void visit(DesignatorOptC designatorOptC) {
		Obj symbol = Tab.find(designatorOptC.getDesignator().obj.getName());
		
		if(!(designatorOptC.getDesignator() instanceof DesignatorExpr)) {
			//symbol = Tab.find(((DesignatorIdent)(designatorOptC.getDesignator())).getDesignName());
			if(symbol.getType().getKind() == Struct.Array || symbol.getKind() == Obj.Meth) {
				report_error("Elem " + symbol.getName() + " not an array element or var type!", designatorOptC);
				return;
			}	
		}
		Struct typeToAssign = symbol.getType();
		if(symbol.getType().getKind() == Struct.Array) {
			typeToAssign = symbol.getType().getElemType();
		}
		if(arrayAssignTypes == null) {
			arrayAssignTypes = typeToAssign;
		}
		else if(arrayAssignTypes.getKind()!=typeToAssign.getKind()) {
			arrayAssignTypes = new Struct(Struct.None);
		}
		
	}
	
}
