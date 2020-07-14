// This is a generated file. Not intended for manual editing.
package com.nikos.gnucobol_3_1.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.nikos.gnucobol_3_1.psi.impl.*;

public interface CobolTypes {

  IElementType ACCEPT_ = new CobolElementType("ACCEPT_");
  IElementType ADD_ = new CobolElementType("ADD_");
  IElementType AUTHOR_ = new CobolElementType("AUTHOR_");
  IElementType CALL_ = new CobolElementType("CALL_");
  IElementType COMPUTE_ = new CobolElementType("COMPUTE_");
  IElementType CONDITIONAL_ITEM_DECL_ = new CobolElementType("CONDITIONAL_ITEM_DECL_");
  IElementType CONDITION_ = new CobolElementType("CONDITION_");
  IElementType CONDITION_PART = new CobolElementType("CONDITION_PART");
  IElementType COPY_ = new CobolElementType("COPY_");
  IElementType DATA_DIVISION_ = new CobolElementType("DATA_DIVISION_");
  IElementType DATE_COMPILED_ = new CobolElementType("DATE_COMPILED_");
  IElementType DATE_WRITTEN_ = new CobolElementType("DATE_WRITTEN_");
  IElementType DISPLAY_ = new CobolElementType("DISPLAY_");
  IElementType DIVIDE_ = new CobolElementType("DIVIDE_");
  IElementType ELEMENTARY_ITEM_DECL_ = new CobolElementType("ELEMENTARY_ITEM_DECL_");
  IElementType ELSE_ = new CobolElementType("ELSE_");
  IElementType ELSE_IF = new CobolElementType("ELSE_IF");
  IElementType END_PROGRAM_ = new CobolElementType("END_PROGRAM_");
  IElementType ENVIRONMENT_DIVISION_ = new CobolElementType("ENVIRONMENT_DIVISION_");
  IElementType GROUP_ITEM_DECL_ = new CobolElementType("GROUP_ITEM_DECL_");
  IElementType IDENTIFICATION_DIVISION_ = new CobolElementType("IDENTIFICATION_DIVISION_");
  IElementType IDENTIFIER_OR_STRING_ = new CobolElementType("IDENTIFIER_OR_STRING_");
  IElementType IF_ = new CobolElementType("IF_");
  IElementType INITIALIZE_ = new CobolElementType("INITIALIZE_");
  IElementType INSTALLATION_ = new CobolElementType("INSTALLATION_");
  IElementType ITEM_DECL_ = new CobolElementType("ITEM_DECL_");
  IElementType ITEM_LENGTH_DECL_ = new CobolElementType("ITEM_LENGTH_DECL_");
  IElementType ITEM_NAME_DECL_ = new CobolElementType("ITEM_NAME_DECL_");
  IElementType ITEM_TYPE_DECL_ = new CobolElementType("ITEM_TYPE_DECL_");
  IElementType ITEM_USAGE_ = new CobolElementType("ITEM_USAGE_");
  IElementType ITEM_VALUE_DECL_ = new CobolElementType("ITEM_VALUE_DECL_");
  IElementType LINKAGE_SECTION_ = new CobolElementType("LINKAGE_SECTION_");
  IElementType LITERAL_ = new CobolElementType("LITERAL_");
  IElementType LS_SECTION_ = new CobolElementType("LS_SECTION_");
  IElementType MOVE_ = new CobolElementType("MOVE_");
  IElementType MULTIPLY_ = new CobolElementType("MULTIPLY_");
  IElementType NON_PARENTHESIS_CONDITION = new CobolElementType("NON_PARENTHESIS_CONDITION");
  IElementType PARENTHESIS_CONDITION = new CobolElementType("PARENTHESIS_CONDITION");
  IElementType PROCEDURE_DIVISION_ = new CobolElementType("PROCEDURE_DIVISION_");
  IElementType PROGRAM_ = new CobolElementType("PROGRAM_");
  IElementType PROGRAM_ID_ = new CobolElementType("PROGRAM_ID_");
  IElementType PROGRAM_NAME_ = new CobolElementType("PROGRAM_NAME_");
  IElementType PROGRAM_NAME_DECL_ = new CobolElementType("PROGRAM_NAME_DECL_");
  IElementType RENAMES_ITEM_DECL_ = new CobolElementType("RENAMES_ITEM_DECL_");
  IElementType SECURITY_ = new CobolElementType("SECURITY_");
  IElementType SENTENCE_ = new CobolElementType("SENTENCE_");
  IElementType SET_ = new CobolElementType("SET_");
  IElementType STATEMENT_ = new CobolElementType("STATEMENT_");
  IElementType SUBTRACT_ = new CobolElementType("SUBTRACT_");
  IElementType WS_SECTION_ = new CobolElementType("WS_SECTION_");

  IElementType ACCEPT = new CobolTokenType("ACCEPT");
  IElementType ADD = new CobolTokenType("ADD");
  IElementType ADD_OP = new CobolTokenType("ADD_OP");
  IElementType ALL = new CobolTokenType("all");
  IElementType ALPHABETIC = new CobolTokenType("alphabetic");
  IElementType ALPHABETIC_LOWER = new CobolTokenType("alphabetic-lower");
  IElementType ALPHABETIC_UPPER = new CobolTokenType("alphabetic-upper");
  IElementType ALPHANUMERIC = new CobolTokenType("ALPHANUMERIC");
  IElementType AND = new CobolTokenType("and");
  IElementType AUTHOR = new CobolTokenType("AUTHOR");
  IElementType BY = new CobolTokenType("BY");
  IElementType CALL = new CobolTokenType("CALL");
  IElementType COMMA = new CobolTokenType("COMMA");
  IElementType COMPUTE = new CobolTokenType("COMPUTE");
  IElementType COPY = new CobolTokenType("COPY");
  IElementType CORR = new CobolTokenType("CORR");
  IElementType CORRESPONDING = new CobolTokenType("CORRESPONDING");
  IElementType DATA = new CobolTokenType("DATA");
  IElementType DATA_DIVISION = new CobolTokenType("DATA_DIVISION");
  IElementType DATE = new CobolTokenType("DATE");
  IElementType DATE_COMPILED = new CobolTokenType("DATE-COMPILED");
  IElementType DATE_WRITTEN = new CobolTokenType("DATE-WRITTEN");
  IElementType DAY = new CobolTokenType("DAY");
  IElementType DAY_OF_WEEK = new CobolTokenType("DAY-OF-WEEK");
  IElementType DISPLAY = new CobolTokenType("DISPLAY");
  IElementType DIVIDE = new CobolTokenType("DIVIDE");
  IElementType DIVIDE_OP = new CobolTokenType("DIVIDE_OP");
  IElementType DOT = new CobolTokenType("DOT");
  IElementType ELSE = new CobolTokenType("else");
  IElementType END = new CobolTokenType("END");
  IElementType END_IF = new CobolTokenType("end-if");
  IElementType ENVIRONMENT_DIVISION = new CobolTokenType("ENVIRONMENT_DIVISION");
  IElementType EQUAL = new CobolTokenType("equal");
  IElementType EQUALS_OP = new CobolTokenType("EQUALS_OP");
  IElementType FLOAT = new CobolTokenType("FLOAT");
  IElementType FROM = new CobolTokenType("FROM");
  IElementType GIVING = new CobolTokenType("GIVING");
  IElementType GREATER = new CobolTokenType("greater");
  IElementType HIGH_VALUE = new CobolTokenType("high-value");
  IElementType HIGH_VALUES = new CobolTokenType("high-values");
  IElementType IDENTIFICATION_DIVISION = new CobolTokenType("IDENTIFICATION_DIVISION");
  IElementType IDENTIFIER = new CobolTokenType("IDENTIFIER");
  IElementType IF = new CobolTokenType("if");
  IElementType IN = new CobolTokenType("IN");
  IElementType INITIALIZE = new CobolTokenType("INITIALIZE");
  IElementType INSTALLATION = new CobolTokenType("INSTALLATION");
  IElementType INTEGER = new CobolTokenType("INTEGER");
  IElementType INTO = new CobolTokenType("INTO");
  IElementType IS = new CobolTokenType("IS");
  IElementType LESS = new CobolTokenType("less");
  IElementType LESS_EQUAL_OP = new CobolTokenType("LESS_EQUAL_OP");
  IElementType LESS_OP = new CobolTokenType("LESS_OP");
  IElementType LINKAGE_SECTION = new CobolTokenType("LINKAGE_SECTION");
  IElementType LOCAL_STORAGE_SECTION = new CobolTokenType("LOCAL_STORAGE_SECTION");
  IElementType LOW_VALUE = new CobolTokenType("low-value");
  IElementType LOW_VALUES = new CobolTokenType("low-values");
  IElementType MORE_EQUAL_OP = new CobolTokenType("MORE_EQUAL_OP");
  IElementType MORE_OP = new CobolTokenType("MORE_OP");
  IElementType MOVE = new CobolTokenType("MOVE");
  IElementType MULTIPLY = new CobolTokenType("MULTIPLY");
  IElementType MULTIPLY_OP = new CobolTokenType("MULTIPLY_OP");
  IElementType NEGATIVE = new CobolTokenType("negative");
  IElementType NOT = new CobolTokenType("not");
  IElementType NULL = new CobolTokenType("null");
  IElementType NULLS = new CobolTokenType("nulls");
  IElementType NUMERIC = new CobolTokenType("numeric");
  IElementType OF = new CobolTokenType("OF");
  IElementType OR = new CobolTokenType("or");
  IElementType PAREN_CLOSE = new CobolTokenType("PAREN_CLOSE");
  IElementType PAREN_OPEN = new CobolTokenType("PAREN_OPEN");
  IElementType PIC = new CobolTokenType("PIC");
  IElementType PICTURE = new CobolTokenType("PICTURE");
  IElementType PIC_TYPE_ALPHA = new CobolTokenType("PIC_TYPE_ALPHA");
  IElementType PIC_TYPE_ALPHANUMERIC = new CobolTokenType("PIC_TYPE_ALPHANUMERIC");
  IElementType PIC_TYPE_DECIMAL_NUMERIC = new CobolTokenType("PIC_TYPE_DECIMAL_NUMERIC");
  IElementType PIC_TYPE_NUMERIC = new CobolTokenType("PIC_TYPE_NUMERIC");
  IElementType PIC_TYPE_SIGNED_NUMERIC = new CobolTokenType("PIC_TYPE_SIGNED_NUMERIC");
  IElementType POSITIVE = new CobolTokenType("positive");
  IElementType POWER_OP = new CobolTokenType("POWER_OP");
  IElementType PROCEDURE_DIVISION = new CobolTokenType("PROCEDURE_DIVISION");
  IElementType PROGRAM = new CobolTokenType("PROGRAM");
  IElementType PROGRAM_ID = new CobolTokenType("PROGRAM_ID");
  IElementType QUOTE = new CobolTokenType("quote");
  IElementType QUOTES = new CobolTokenType("quotes");
  IElementType REDEFINES = new CobolTokenType("REDEFINES");
  IElementType REMAINDER = new CobolTokenType("REMAINDER");
  IElementType RENAMES = new CobolTokenType("RENAMES");
  IElementType REPLACING = new CobolTokenType("REPLACING");
  IElementType SECURITY = new CobolTokenType("SECURITY");
  IElementType SET = new CobolTokenType("SET");
  IElementType SPACE = new CobolTokenType("space");
  IElementType SPACES = new CobolTokenType("spaces");
  IElementType STRING = new CobolTokenType("STRING");
  IElementType SUBTRACT = new CobolTokenType("SUBTRACT");
  IElementType SUBTRACT_OP = new CobolTokenType("SUBTRACT_OP");
  IElementType THAN = new CobolTokenType("than");
  IElementType THEN = new CobolTokenType("then");
  IElementType THROUGH = new CobolTokenType("THROUGH");
  IElementType TIME = new CobolTokenType("TIME");
  IElementType TO = new CobolTokenType("TO");
  IElementType TRUE = new CobolTokenType("TRUE");
  IElementType USING = new CobolTokenType("USING");
  IElementType VALUE = new CobolTokenType("VALUE");
  IElementType WORKING_STORAGE_SECTION = new CobolTokenType("WORKING_STORAGE_SECTION");
  IElementType YYYYDDD = new CobolTokenType("YYYYDDD");
  IElementType YYYYMMDD = new CobolTokenType("YYYYMMDD");
  IElementType ZERO = new CobolTokenType("zero");
  IElementType ZEROES = new CobolTokenType("zeroes");
  IElementType ZEROS = new CobolTokenType("zeros");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ACCEPT_) {
        return new CobolAccept_Impl(node);
      }
      else if (type == ADD_) {
        return new CobolAdd_Impl(node);
      }
      else if (type == AUTHOR_) {
        return new CobolAuthor_Impl(node);
      }
      else if (type == CALL_) {
        return new CobolCall_Impl(node);
      }
      else if (type == COMPUTE_) {
        return new CobolCompute_Impl(node);
      }
      else if (type == CONDITIONAL_ITEM_DECL_) {
        return new CobolConditionalItemDecl_Impl(node);
      }
      else if (type == CONDITION_) {
        return new CobolCondition_Impl(node);
      }
      else if (type == CONDITION_PART) {
        return new CobolConditionPartImpl(node);
      }
      else if (type == COPY_) {
        return new CobolCopy_Impl(node);
      }
      else if (type == DATA_DIVISION_) {
        return new CobolDataDivision_Impl(node);
      }
      else if (type == DATE_COMPILED_) {
        return new CobolDateCompiled_Impl(node);
      }
      else if (type == DATE_WRITTEN_) {
        return new CobolDateWritten_Impl(node);
      }
      else if (type == DISPLAY_) {
        return new CobolDisplay_Impl(node);
      }
      else if (type == DIVIDE_) {
        return new CobolDivide_Impl(node);
      }
      else if (type == ELEMENTARY_ITEM_DECL_) {
        return new CobolElementaryItemDecl_Impl(node);
      }
      else if (type == ELSE_) {
        return new CobolElse_Impl(node);
      }
      else if (type == ELSE_IF) {
        return new CobolElseIfImpl(node);
      }
      else if (type == END_PROGRAM_) {
        return new CobolEndProgram_Impl(node);
      }
      else if (type == ENVIRONMENT_DIVISION_) {
        return new CobolEnvironmentDivision_Impl(node);
      }
      else if (type == GROUP_ITEM_DECL_) {
        return new CobolGroupItemDecl_Impl(node);
      }
      else if (type == IDENTIFICATION_DIVISION_) {
        return new CobolIdentificationDivision_Impl(node);
      }
      else if (type == IDENTIFIER_OR_STRING_) {
        return new CobolIdentifierOrString_Impl(node);
      }
      else if (type == IF_) {
        return new CobolIf_Impl(node);
      }
      else if (type == INITIALIZE_) {
        return new CobolInitialize_Impl(node);
      }
      else if (type == INSTALLATION_) {
        return new CobolInstallation_Impl(node);
      }
      else if (type == ITEM_DECL_) {
        return new CobolItemDecl_Impl(node);
      }
      else if (type == ITEM_LENGTH_DECL_) {
        return new CobolItemLengthDecl_Impl(node);
      }
      else if (type == ITEM_NAME_DECL_) {
        return new CobolItemNameDecl_Impl(node);
      }
      else if (type == ITEM_TYPE_DECL_) {
        return new CobolItemTypeDecl_Impl(node);
      }
      else if (type == ITEM_USAGE_) {
        return new CobolItemUsage_Impl(node);
      }
      else if (type == ITEM_VALUE_DECL_) {
        return new CobolItemValueDecl_Impl(node);
      }
      else if (type == LINKAGE_SECTION_) {
        return new CobolLinkageSection_Impl(node);
      }
      else if (type == LITERAL_) {
        return new CobolLiteral_Impl(node);
      }
      else if (type == LS_SECTION_) {
        return new CobolLsSection_Impl(node);
      }
      else if (type == MOVE_) {
        return new CobolMove_Impl(node);
      }
      else if (type == MULTIPLY_) {
        return new CobolMultiply_Impl(node);
      }
      else if (type == NON_PARENTHESIS_CONDITION) {
        return new CobolNonParenthesisConditionImpl(node);
      }
      else if (type == PARENTHESIS_CONDITION) {
        return new CobolParenthesisConditionImpl(node);
      }
      else if (type == PROCEDURE_DIVISION_) {
        return new CobolProcedureDivision_Impl(node);
      }
      else if (type == PROGRAM_) {
        return new CobolProgram_Impl(node);
      }
      else if (type == PROGRAM_ID_) {
        return new CobolProgramId_Impl(node);
      }
      else if (type == PROGRAM_NAME_) {
        return new CobolProgramName_Impl(node);
      }
      else if (type == PROGRAM_NAME_DECL_) {
        return new CobolProgramNameDecl_Impl(node);
      }
      else if (type == RENAMES_ITEM_DECL_) {
        return new CobolRenamesItemDecl_Impl(node);
      }
      else if (type == SECURITY_) {
        return new CobolSecurity_Impl(node);
      }
      else if (type == SENTENCE_) {
        return new CobolSentence_Impl(node);
      }
      else if (type == SET_) {
        return new CobolSet_Impl(node);
      }
      else if (type == STATEMENT_) {
        return new CobolStatement_Impl(node);
      }
      else if (type == SUBTRACT_) {
        return new CobolSubtract_Impl(node);
      }
      else if (type == WS_SECTION_) {
        return new CobolWsSection_Impl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
