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
	private boolean errorFound = false;
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
	private int nestedWhile = 0;
	private int nestedForEach = 0;
	public int numOfVars;
	private boolean isClassAtr = false;
	private boolean isNestedExpr = false;
	private boolean arrayError = false;
	private boolean typeError = false;
	private Struct arrayAssignTypes = null;
	private ArrayList<String> forEachIdentList = new ArrayList<>();
	private boolean methodCallError = false;
	private boolean isVarArray = false;
	private boolean declaringMeths = false;
	private boolean exprTypeError = false;
	private HashMap<Obj, ArrayList<Struct>> methodParams = new HashMap<>();
	public void report_error(String message, SyntaxNode info) {
		errorFound = true;
		StringBuilder msg = new StringBuilder("Greska");
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line).append(": ");
		else 
			msg.append(": ");
		msg.append(message);
		log.error(msg.toString());
	}
	public boolean errorExists() {
		return errorFound;
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
		ArrayList<Struct> chrParams = new ArrayList<>();
		ArrayList<Struct> ordParams = new ArrayList<>();
		ArrayList<Struct> lenParams = new ArrayList<>();
		chrParams.add(new Struct(Struct.Int));
		ordParams.add(new Struct(Struct.Char));
		lenParams.add(new Struct(Struct.Array));
		Obj chr = Tab.find("chr");
		Obj ord = Tab.find("ord");
		Obj len = Tab.find("len");
		methodParams.put(chr, chrParams);
		methodParams.put(ord, ordParams);
		methodParams.put(len, lenParams);
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
    	numOfVars = Tab.currentScope.getnVars();
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
	   if(currMethod == null && declaringMeths == true) return null;
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
	   declaringMeths = true;
	   String methName = meth.getMethName();
	   if(!isFuncNameDeclared(methName)) {
		   Obj declaredMeth = declareFunction(methName, meth);
		   meth.obj = declaredMeth;
		   currMethod = declaredMeth;
		   Tab.openScope();
		   report_info("Inserted " + methName + " method!", meth);
	   }
	   else {
		   currMethod = null;
	   }
	   
	   
   }
   public void visit(MethodDecl meth) {
	 if(currMethod != null) {
		 currMethod.setLevel(currMethParamCnt);
		 if(currMethod.getLevel() == 0 && currMethod.getName().equals("main")  && currMethod.getType().getKind() == Struct.None) {
			 mainFound = true;
		 }
		 Tab.chainLocalSymbols(currMethod);
		 Tab.closeScope();
		 currMethParamCnt = 0;
	 }
	 
	 
   }
   public void visit(NonEndingTypeIdentListNoError param) {
	   if(currMethod!=null) {
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
	  
	   
	   
	   
	   
   }
   public void visit(EndingTypeIdentListNoError param) {
	   if(currMethod!=null) {
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
	  
   }
   public void visit(NoFormsParametars noformparams) {
	   if(currMethod!=null) {
		   methodParams.put(currMethod, new ArrayList<>());
	   }
   }
   public void visit(ReturnStatement returnStmt) {
	  
	   if(currMethod != null) {
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
	   
	   
   }
   
   public void visit(DesignatorFactor designatorFactor) {
	   if(currMethod!=null) {
		   numOfFactors++;
		   if(designatorFactor.getDesignator().obj.getKind() == Obj.Meth) {
			   report_error("Method call error!" , designatorFactor);
			   designatorFactor.struct = new Struct(Struct.None);
		   }
		   else {
			   if(designatorFactor.getDesignator() instanceof DesignatorIdent || designatorFactor.getDesignator().obj.getLevel() == -1) {
				   designatorFactor.struct = designatorFactor.getDesignator().obj.getType();
			   }
			   else if(designatorFactor.getDesignator() instanceof DesignatorExpr) {
				   designatorFactor.struct = designatorFactor.getDesignator().obj.getType().getElemType();
			   }
			   
		   }
	   }
	  
	   
	   
   }
   public void visit(DesignatorFactorAct designatorFactorAct) {
	   if(currMethod!=null) {
		   numOfFactors++;
		   if(methodCallError) {
			   report_error("Method error!", designatorFactorAct);
		   }
		   Obj designator = designatorFactorAct.getFuncCall().getDesignator().obj;
		   if(designator.getKind() != Obj.Meth) {
			   report_error("Symbol " + designator.getName() + " is not a method!" , designatorFactorAct);
			   designatorFactorAct.struct = designatorFactorAct.getFuncCall().getDesignator().obj.getType();
			   return;
		   }
		   int paramCnt = methodParams.get(designatorFactorAct.getFuncCall().getDesignator().obj).size();
		   if(paramCnt != designatorFactorAct.getActParsOpt().obj.getLevel()) {
			   report_error("Param count " + designatorFactorAct.getActParsOpt().obj.getLevel()+" doesnt match with method param count " +paramCnt+ "!", designatorFactorAct);
		   }
		   designatorFactorAct.struct = designatorFactorAct.getFuncCall().getDesignator().obj.getType();
		   methodCallError = false;
	   }
   }
   public void visit(DesignatorFactorNumber designatorFactorNumber) {
	   if(currMethod!=null) {
		   numOfFactors++;
		   designatorFactorNumber.struct = new Struct(Struct.Int);
	   }
	  
	   
   }
   public void visit(DesignatorFactorChar designatorFactorChar) {
	   if(currMethod!=null) {
		   numOfFactors++;
		   designatorFactorChar.struct = new Struct(Struct.Char);
	   }
   }
	public void visit(DesignatorFactorBool designatorFactorBool) {
		if(currMethod!=null) {
			numOfFactors++;
			designatorFactorBool.struct = new Struct(Struct.Bool); 
		}
	}
	public void visit(DesignatorFactorExpr designatorFactorExpr) {
		if(currMethod!=null) {
			numOfFactors++;
			designatorFactorExpr.struct = designatorFactorExpr.getExpr().struct;
		}
	}
	public void visit(DesignatorFactorNewExpr designatorFactorNewExpr) {
		if(currMethod!=null) {
			numOfFactors++;
			if(designatorFactorNewExpr.getExpr().struct.getKind() != Struct.Int) {
				report_error("Expression must be int type!", designatorFactorNewExpr);
				designatorFactorNewExpr.struct = new Struct(Struct.None);
				return;
			}
			designatorFactorNewExpr.struct = new Struct(Struct.Array);
			designatorFactorNewExpr.struct.setElementType(designatorFactorNewExpr.getType().struct);	
		}
				
				
	}
	public void visit(BeginMulOp beginMulOp) {
		if(currMethod!=null) {
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
		
		
	}
	public void visit(EndMulOp endMulOp) {
		if(currMethod!=null) {
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
		
	}
	public void visit(Term term) {
		if(currMethod!=null) {
			numOfFactors = 0;
			term.struct = exprType;
			exprType = null;
		}
	}
	public void visit(NotEndAddopTerm addopTerms) {
		if(currMethod!=null) {
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
	}
	public void visit(EndAddopTerm addopTerms) {
		if(currMethod!=null) {
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
	}
	public void visit(PlusExpr plusExpr) {
		if(currMethod!=null) {
			numOfTerms++;
			numOfFactors = 0;
			if(exprTermType.getKind() == Struct.None) {
				report_error("Expr error!", plusExpr);
			}
			plusExpr.struct = exprTermType;
			returnExprType = exprTermType;
			exprTermType = null;
		}
	
	}
	public void visit(MinusExpr minusExpr) {
		if(currMethod!=null) {
			numOfTerms++;
			numOfFactors = 0;
			if(exprTermType.getKind() != Struct.Int) {
				report_error("Expr error!", minusExpr);
			}
			minusExpr.struct = exprTermType;
			returnExprType = exprTermType;
			exprTermType = null;
		}
		
		
	}
	
	public void visit(ExpressionC expression) {
		if(currMethod!=null) {
			if(exprTypeError) {
				report_error("Expression in wrong format!", expression);
			}
			
			numOfFactors = 0;
		}
		
		
	}
	private String getCurrCalledMethName(SyntaxNode node) {
		while(!(node instanceof DesignatorFactorAct || node instanceof DesignatorStatementOne)) {
			node = node.getParent();
		}
		String methName = "";
		if(node instanceof DesignatorStatementOne) {
			methName = (((AssignOpExprParen)((DesignatorStatementOne)(node)).getAssignOpExpr()).getFuncCall().getDesignator()).obj.getName();
		}
		else {
			methName = (((DesignatorFactorAct)(node)).getFuncCall().getDesignator()).obj.getName();
		}
		
		return methName;
	}
	public void visit(ExprListC exprc) {
		if(currMethod!=null) {
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
				else if(params.get(params.size() - 1).getKind() == Struct.Array && params.get(params.size() - 1).getElemType() != null) {
					if(params.get(params.size() - 1).getElemType().getKind() != exprType.getElemType().getKind()) {
						report_error("Array type mismatch at pos: " + (params.size()- exprc.obj.getLevel()), exprc);
					}
				}
			
			}
			else {
				report_error("Undefined meth!", exprc);
			}
		}
	}
	public void visit(NoExprList noexprc) {
		if(currMethod!=null) {
			noexprc.obj = new Obj(Obj.Meth, null, arrayAssignTypes);
			noexprc.obj.setLevel(1);
			Obj func = Tab.find(getCurrCalledMethName(noexprc));
			Struct exprType = noexprc.getExpr().struct;
			ArrayList<Struct> params =  methodParams.get(func);
			if(params!=null) {
				if(params.get(params.size() - 1).getKind() != exprType.getKind()) {
					report_error("Type mismatch at pos: " + (params.size()- noexprc.obj.getLevel()), noexprc);
				}
				else if(params.get(params.size() - 1).getKind() == Struct.Array && params.get(params.size() - 1).getElemType() != null) {
					if(params.get(params.size() - 1).getElemType().getKind() != exprType.getElemType().getKind()) {
						report_error("Array type mismatch at pos: " + (params.size()- noexprc.obj.getLevel()), noexprc);
					}
				}
			}
			else {
				report_error("Undefined meth!", noexprc);
			}
		}
	}
	public void visit(ActPars actpars) {
		if(currMethod!=null) {
			actpars.obj = new Obj(Obj.Meth, null, arrayAssignTypes);
			actpars.obj.setLevel(actpars.getExprList().obj.getLevel());
		}
	}
	public void visit(ActParsOptC actparsoptc) {
		if(currMethod!=null) {
			actparsoptc.obj = new Obj(Obj.Meth, null, arrayAssignTypes);
			actparsoptc.obj.setLevel(actparsoptc.getActPars().obj.getLevel());
		}
	}
	public void visit(NoActParsOpt noactparsopt) {
		if(currMethod!=null) {
			noactparsopt.obj = new Obj(Obj.Meth, null, arrayAssignTypes);
			noactparsopt.obj.setLevel(0);
		}
	}
	public void visit(DesignatorIdent designatorIdent) {	
		if(currMethod!=null) {
			String designName = designatorIdent.getDesignName();
			Obj designator = Tab.find(designName);
			designatorIdent.obj = designator;
			if(designator == Tab.noObj) {
				exprTypeError = true;
				report_error("Var " + designName + " is not declared!", designatorIdent);
			}	
		}
	}
	public void visit(DesignatorExpr designatorExpr) {
		if(currMethod!=null) {
			String designName = ((DesignatorIdent)designatorExpr.getDesignator()).getDesignName();
			Obj designator = Tab.find(designName);
			designatorExpr.obj = new Obj(Obj.Elem, designator.getName(), designator.getType().getElemType());
			if(designator.getType().getKind() != Struct.Array) {
				report_error("Symbol "+ designName + " must be of array type!", designatorExpr);
				designatorExpr.obj.setLevel(-1);
				return;
			}
			
			if(designatorExpr.getExpr().struct.getKind() != Struct.Int) {
				arrayError = true;
				exprTypeError = true;
			}
			                   
			if(designator == Tab.noObj) {
				exprTypeError = true;
				report_error("Var " + designName + " is not declared!", designatorExpr);
			}
		}
		
		
	
		
	}

	public void visit(DesignatorStatementType designatorStatementType) {
		if(currMethod!=null) {
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
	}
	public Designator getDesignatorType(SyntaxNode node) {
		if(node instanceof AssignOpExprParen) {
			return ((AssignOpExprParen) node).getFuncCall().getDesignator();
		}
		else if(node instanceof AssignOpExprBase) {
			
			return ((AssignOpEqualNoError)((AssignOpExprBase) node).getAssignOpEqual()).getDesignator();
			
			
		}
		else if(node instanceof AssignOpExprInc) {
			return ((AssignOpExprInc)node).getDesignator();
		}
		return ((AssignOpExprDec)node).getDesignator();
		
	}
	public void visit(DesignatorStatementOne designatorStmtOne) {
		if(currMethod!=null) {
			SyntaxNode assignOp = designatorStmtOne.getAssignOpExpr();
			if(!(assignOp instanceof AssignOpExprBase) || (assignOp instanceof AssignOpExprBase && ((AssignOpExprBase)assignOp).getAssignOpEqual() instanceof AssignOpEqualNoError)) {
				Designator designator = getDesignatorType(assignOp);
				Struct designatorType = designator.obj.getType();
				if(designatorType.getKind() == Struct.Array && designator instanceof DesignatorExpr) {
					designatorType = designatorType.getElemType();
				}
				if(!(designatorStmtOne.getAssignOpExpr() instanceof AssignOpExprParen)) {
					if(designatorStmtOne.getAssignOpExpr().struct == null || (designatorStmtOne.getAssignOpExpr().struct.getKind()!=designatorType.getKind())) {
						typeError = true;
					}
					else if(designatorStmtOne.getAssignOpExpr().struct.getKind() == Struct.Array && designatorStmtOne.getAssignOpExpr().struct.getElemType().getKind() != designatorType.getElemType().getKind()) {
						typeError = true;
					}
					if(!(designator instanceof DesignatorExpr)) {
						Obj symbol = Tab.find(((DesignatorIdent)(designator)).getDesignName());
						if(symbol.getType().getKind() != Struct.Array && symbol.getKind() != Obj.Var) {
							
							report_error("Elem " + symbol.getName() + " not an array element or var type!", designatorStmtOne);
							return;
						}
						
					}
				}
			}
			
		}
		
	}
	public void visit(AssignOpExprParen assignOpExprParen) {
		if(currMethod!=null) {
			Obj designator = assignOpExprParen.getFuncCall().getDesignator().obj;
			if(designator.getKind() != Obj.Meth) {
				report_error("Symbol " + designator.getName() + " is not a method!", assignOpExprParen);
				return;
			}
			int paramCnt = methodParams.get(designator).size();
			if(paramCnt != assignOpExprParen.getActParsOpt().obj.getLevel()) {
				 report_error("Param count " + assignOpExprParen.getActParsOpt().obj.getLevel()+" doesnt match with method param count " +paramCnt+ " on method call!", assignOpExprParen);
			}
		}
	}
	public void visit(AssignOpExprBase assignOpExprBase) {
		if(currMethod!=null && assignOpExprBase.getAssignOpEqual() instanceof AssignOpEqualNoError) {
			assignOpExprBase.struct = ((AssignOpEqualNoError)assignOpExprBase.getAssignOpEqual()).getExpr().struct;
			if(forEachIdentList.contains(((AssignOpEqualNoError)assignOpExprBase.getAssignOpEqual()).getDesignator().obj.getName())) {
				report_error("Foreach designator cannot be assigned a value!", assignOpExprBase);
			}
		}
	}
	public void visit(AssignOpExprInc assignOpExprInc) {
		if(currMethod!=null) {
			assignOpExprInc.struct = new Struct(Struct.Int);
			if(forEachIdentList.contains(assignOpExprInc.getDesignator().obj.getName())) {
				report_error("Foreach designator cannot be assigned a value!", assignOpExprInc);
			}
		}
	}
	public void visit(AssignOpExprDec assignOpExprDec) {
		if(currMethod!=null) {
			assignOpExprDec.struct = new Struct(Struct.Int);
			if(forEachIdentList.contains(assignOpExprDec.getDesignator().obj.getName())) {
				report_error("Foreach designator cannot be assigned a value!", assignOpExprDec);
			}
		}
	}
	
	public void visit(DesignatorStatementMul designatorStatementMul) {
		if(currMethod!=null) {
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
		
	}
	public void visit(DesignatorOptC designatorOptC) {
		if(currMethod!=null) {
			if(forEachIdentList.contains(designatorOptC.getDesignator().obj.getName())) {
				report_error("Foreach designator cannot be assigned a value!", designatorOptC);
				//return;
			}
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
	public void visit(WhileStatement whileStmt) {
		if(currMethod!=null) {
			nestedWhile--;
		}
	}
	public void visit(While whiledecl) {
		if(currMethod!=null) {
			nestedWhile++;
		}
	}
	public void visit(ForeachStatement foreachStmt) {
		if(currMethod!=null) {
			nestedForEach--;
			forEachIdentList.remove(forEachIdentList.size()-1);
			if(foreachStmt.getDesignator().obj.getType().getKind() != Struct.Array) {
				report_error("Designator for foreach statement must be an array type!", foreachStmt);
				return;
			}
			Obj ident = Tab.find(foreachStmt.getIdentName());
			foreachStmt.obj = ident;
			if(ident.getType().getKind() != foreachStmt.getDesignator().obj.getType().getElemType().getKind()) {
				report_error("Ident in foreach statement must match types with designator array elements!", foreachStmt);
				return;
			}
		}
	}
	public void visit(Foreach foreachdecl) {
		if(currMethod!=null) {
			nestedForEach++;
			forEachIdentList.add(((ForeachStatement)foreachdecl.getParent()).getIdentName());
			
		}
	}
	public void visit(BreakStatement breakStmt) {
		if(currMethod!=null) {
			if(nestedWhile == 0 && nestedForEach == 0) {
				report_error("Break statement has no surrounding while or foreach loop!", breakStmt);
			}
		}
	}
	public void visit(ContinueStatement continueStmt) {
		if(currMethod!=null) {
			if(nestedWhile == 0 && nestedForEach == 0) {
				report_error("Continue statement has no surrounding while or foreach loop!", continueStmt);
			}
			
		}
	}
	public void visit(ReadStatement readStmt) {
		if(currMethod!=null) {
			Obj elem = readStmt.getDesignator().obj;
			if(elem.getKind() != Obj.Elem && elem.getKind() != Obj.Var) {
				report_error("Designator must be array elem or var!", readStmt);
			}
			else {
				Struct type = elem.getType();
				if(elem.getType().getKind() == Struct.Array) {
					if(readStmt.getDesignator() instanceof DesignatorIdent) {
						report_error("Designator must be array elem or var!", readStmt);
						return;
					}
					type = elem.getType().getElemType();
				}
				if(type.getKind() != Struct.Bool && type.getKind() != Struct.Int && type.getKind() != Struct.Char) {
					report_error("Designator must be int, bool or char type!", readStmt);
				}
				
			}
		}
	}
	public void visit(PrintStatement printStmt) {
		if(currMethod!=null) {
			if(printStmt.getExpr().struct.getKind() != Struct.Int && printStmt.getExpr().struct.getKind() != Struct.Char && printStmt.getExpr().struct.getKind() != Struct.Bool) {
				report_error("Print expr must be of int, char, bool type!", printStmt);
			}
		}
	}
	public void visit(OneCondFact onecondfact) {
		if(currMethod!=null) {
			onecondfact.struct = onecondfact.getExpr().struct;
		}
	}
	public void visit(TwoCondFact twocondfact) {
		if(currMethod!=null) {
			if(twocondfact.getExpr().struct.getKind() == twocondfact.getExpr1().struct.getKind() || twocondfact.getExpr().struct.compatibleWith(twocondfact.getExpr1().struct)) {
				if((twocondfact.getExpr().struct.getKind() == Struct.Array || twocondfact.getExpr1().struct.getKind() == Struct.Array) && !(twocondfact.getRelop() instanceof IsEqualOp) && !(twocondfact.getRelop() instanceof NotEqualOp)) {
					twocondfact.struct = new Struct(Struct.None);
					report_error("Reference type must use == or != relops!", twocondfact);
				}
				else {
					twocondfact.struct = new Struct(Struct.Bool);
				}
				
			}
			else {
				twocondfact.struct = new Struct(Struct.None);
				report_error("Types not compatable!", twocondfact);
			}
		}
	}
	public void visit(AndTermsC andTermsC) {
		if(currMethod!=null) {
			if(andTermsC.getAndTerms().struct.getKind() == andTermsC.getCondFact().struct.getKind()) {
				andTermsC.struct = andTermsC.getAndTerms().struct;
			}
			else {
				andTermsC.struct = new Struct(Struct.None);
			}
		}
	}
	public void visit(NoAndTerms noAndTerms) {
		if(currMethod!=null) {
			noAndTerms.struct = noAndTerms.getCondFact().struct;
		}
	}
	public void visit(CondTerm condTerm) {
		if(currMethod!=null) {
			condTerm.struct = condTerm.getAndTerms().struct;
		}
	}
	public void visit(ConditionNoError condition) {
		if(currMethod!=null) {
			condition.struct = condition.getOrTerms().struct;
			if(condition.struct.getKind() != Struct.Bool) {
				report_error("Condition must be bool type!", condition);
			}
		}
	}
	public void visit(OrTermsC orTermsC) {
		if(currMethod!=null) {
			if(orTermsC.getOrTerms().struct.getKind() == orTermsC.getCondTerm().struct.getKind()) {
				orTermsC.struct = orTermsC.getOrTerms().struct;
			}
			else {
				orTermsC.struct = new Struct(Struct.None);
			}
		}
	}
	public void visit(NoOrTerms noOrTerms) {
		if(currMethod!=null) {
			noOrTerms.struct = noOrTerms.getCondTerm().struct;
		}
	}
	public void visit(FuncCall funcCall) {
		funcCall.obj = funcCall.getDesignator().obj;
	}


	
	
	
	
}













