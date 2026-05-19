// Generated from src/parser/TSmm.g4 by ANTLR 4.13.2
package parser;

import ast.definitions.*;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import ast.*;
import errorhandler.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TSmmParser}.
 */
public interface TSmmListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TSmmParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(TSmmParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(TSmmParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterDefinition(TSmmParser.DefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitDefinition(TSmmParser.DefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#var_definition}.
	 * @param ctx the parse tree
	 */
	void enterVar_definition(TSmmParser.Var_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#var_definition}.
	 * @param ctx the parse tree
	 */
	void exitVar_definition(TSmmParser.Var_definitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#func_definition}.
	 * @param ctx the parse tree
	 */
	void enterFunc_definition(TSmmParser.Func_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#func_definition}.
	 * @param ctx the parse tree
	 */
	void exitFunc_definition(TSmmParser.Func_definitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(TSmmParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(TSmmParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#main_definition}.
	 * @param ctx the parse tree
	 */
	void enterMain_definition(TSmmParser.Main_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#main_definition}.
	 * @param ctx the parse tree
	 */
	void exitMain_definition(TSmmParser.Main_definitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#func_body}.
	 * @param ctx the parse tree
	 */
	void enterFunc_body(TSmmParser.Func_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#func_body}.
	 * @param ctx the parse tree
	 */
	void exitFunc_body(TSmmParser.Func_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(TSmmParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(TSmmParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(TSmmParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(TSmmParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#recordFields}.
	 * @param ctx the parse tree
	 */
	void enterRecordFields(TSmmParser.RecordFieldsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#recordFields}.
	 * @param ctx the parse tree
	 */
	void exitRecordFields(TSmmParser.RecordFieldsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(TSmmParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(TSmmParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#switchbody}.
	 * @param ctx the parse tree
	 */
	void enterSwitchbody(TSmmParser.SwitchbodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#switchbody}.
	 * @param ctx the parse tree
	 */
	void exitSwitchbody(TSmmParser.SwitchbodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#switchcase}.
	 * @param ctx the parse tree
	 */
	void enterSwitchcase(TSmmParser.SwitchcaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#switchcase}.
	 * @param ctx the parse tree
	 */
	void exitSwitchcase(TSmmParser.SwitchcaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#func_invocation}.
	 * @param ctx the parse tree
	 */
	void enterFunc_invocation(TSmmParser.Func_invocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#func_invocation}.
	 * @param ctx the parse tree
	 */
	void exitFunc_invocation(TSmmParser.Func_invocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#expression_list}.
	 * @param ctx the parse tree
	 */
	void enterExpression_list(TSmmParser.Expression_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#expression_list}.
	 * @param ctx the parse tree
	 */
	void exitExpression_list(TSmmParser.Expression_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(TSmmParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(TSmmParser.BlockContext ctx);
}