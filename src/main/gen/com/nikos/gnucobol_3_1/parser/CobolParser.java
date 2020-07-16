// This is a generated file. Not intended for manual editing.
package com.nikos.gnucobol_3_1.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.nikos.gnucobol_3_1.psi.CobolTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class CobolParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return file(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(CONDITIONAL_ITEM_DECL_, ELEMENTARY_ITEM_DECL_, GROUP_ITEM_DECL_, ITEM_DECL_,
      RENAMES_ITEM_DECL_),
  };

  /* ********************************************************** */
  // ACCEPT item [FROM (IDENTIFIER|DATE [YYYYMMDD]|DAY [YYYYDDD]|DAY-OF-WEEK|TIME)]
  public static boolean accept_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accept_")) return false;
    if (!nextTokenIs(b, ACCEPT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ACCEPT_, null);
    r = consumeToken(b, ACCEPT);
    p = r; // pin = 1
    r = r && report_error_(b, item(b, l + 1));
    r = p && accept__2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [FROM (IDENTIFIER|DATE [YYYYMMDD]|DAY [YYYYDDD]|DAY-OF-WEEK|TIME)]
  private static boolean accept__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accept__2")) return false;
    accept__2_0(b, l + 1);
    return true;
  }

  // FROM (IDENTIFIER|DATE [YYYYMMDD]|DAY [YYYYDDD]|DAY-OF-WEEK|TIME)
  private static boolean accept__2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accept__2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FROM);
    r = r && accept__2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IDENTIFIER|DATE [YYYYMMDD]|DAY [YYYYDDD]|DAY-OF-WEEK|TIME
  private static boolean accept__2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accept__2_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = accept__2_0_1_1(b, l + 1);
    if (!r) r = accept__2_0_1_2(b, l + 1);
    if (!r) r = consumeToken(b, DAY_OF_WEEK);
    if (!r) r = consumeToken(b, TIME);
    exit_section_(b, m, null, r);
    return r;
  }

  // DATE [YYYYMMDD]
  private static boolean accept__2_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accept__2_0_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DATE);
    r = r && accept__2_0_1_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [YYYYMMDD]
  private static boolean accept__2_0_1_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accept__2_0_1_1_1")) return false;
    consumeToken(b, YYYYMMDD);
    return true;
  }

  // DAY [YYYYDDD]
  private static boolean accept__2_0_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accept__2_0_1_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DAY);
    r = r && accept__2_0_1_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [YYYYDDD]
  private static boolean accept__2_0_1_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accept__2_0_1_2_1")) return false;
    consumeToken(b, YYYYDDD);
    return true;
  }

  /* ********************************************************** */
  // ADD (add_corr_|add_not_corr)
  public static boolean add_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "add_")) return false;
    if (!nextTokenIs(b, ADD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ADD_, null);
    r = consumeToken(b, ADD);
    p = r; // pin = 1
    r = r && add__1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // add_corr_|add_not_corr
  private static boolean add__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "add__1")) return false;
    boolean r;
    r = add_corr_(b, l + 1);
    if (!r) r = add_not_corr(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // (CORR|CORRESPONDING) item TO item
  static boolean add_corr_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "add_corr_")) return false;
    if (!nextTokenIs(b, "", CORR, CORRESPONDING)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = add_corr__0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, item(b, l + 1));
    r = p && report_error_(b, consumeToken(b, TO)) && r;
    r = p && item(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // CORR|CORRESPONDING
  private static boolean add_corr__0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "add_corr__0")) return false;
    boolean r;
    r = consumeToken(b, CORR);
    if (!r) r = consumeToken(b, CORRESPONDING);
    return r;
  }

  /* ********************************************************** */
  // list_of_items_or_numbers (TO item GIVING list_of_items | TO list_of_items | GIVING list_of_items)
  static boolean add_not_corr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "add_not_corr")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = list_of_items_or_numbers(b, l + 1);
    p = r; // pin = 1
    r = r && add_not_corr_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // TO item GIVING list_of_items | TO list_of_items | GIVING list_of_items
  private static boolean add_not_corr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "add_not_corr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = add_not_corr_1_0(b, l + 1);
    if (!r) r = add_not_corr_1_1(b, l + 1);
    if (!r) r = add_not_corr_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // TO item GIVING list_of_items
  private static boolean add_not_corr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "add_not_corr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TO);
    r = r && item(b, l + 1);
    r = r && consumeToken(b, GIVING);
    r = r && list_of_items(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // TO list_of_items
  private static boolean add_not_corr_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "add_not_corr_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TO);
    r = r && list_of_items(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // GIVING list_of_items
  private static boolean add_not_corr_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "add_not_corr_1_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GIVING);
    r = r && list_of_items(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // multiply_or_divide_expr ((ADD_OP | SUBTRACT_OP) multiply_or_divide_expr)*
  static boolean add_or_subtract_expr_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "add_or_subtract_expr_")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = multiply_or_divide_expr(b, l + 1);
    r = r && add_or_subtract_expr__1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ((ADD_OP | SUBTRACT_OP) multiply_or_divide_expr)*
  private static boolean add_or_subtract_expr__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "add_or_subtract_expr__1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!add_or_subtract_expr__1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "add_or_subtract_expr__1", c)) break;
    }
    return true;
  }

  // (ADD_OP | SUBTRACT_OP) multiply_or_divide_expr
  private static boolean add_or_subtract_expr__1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "add_or_subtract_expr__1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = add_or_subtract_expr__1_0_0(b, l + 1);
    r = r && multiply_or_divide_expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ADD_OP | SUBTRACT_OP
  private static boolean add_or_subtract_expr__1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "add_or_subtract_expr__1_0_0")) return false;
    boolean r;
    r = consumeToken(b, ADD_OP);
    if (!r) r = consumeToken(b, SUBTRACT_OP);
    return r;
  }

  /* ********************************************************** */
  // all (null_|zero_|space_|low_value_|high_value_|quote_|STRING|INTEGER|FLOAT)
  static boolean all_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "all_")) return false;
    if (!nextTokenIs(b, ALL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ALL);
    r = r && all__1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // null_|zero_|space_|low_value_|high_value_|quote_|STRING|INTEGER|FLOAT
  private static boolean all__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "all__1")) return false;
    boolean r;
    r = null_(b, l + 1);
    if (!r) r = zero_(b, l + 1);
    if (!r) r = space_(b, l + 1);
    if (!r) r = low_value_(b, l + 1);
    if (!r) r = high_value_(b, l + 1);
    if (!r) r = quote_(b, l + 1);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, INTEGER);
    if (!r) r = consumeToken(b, FLOAT);
    return r;
  }

  /* ********************************************************** */
  // AUTHOR [DOT] [STRING] [DOT]
  public static boolean author_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "author_")) return false;
    if (!nextTokenIs(b, AUTHOR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AUTHOR);
    r = r && author__1(b, l + 1);
    r = r && author__2(b, l + 1);
    r = r && author__3(b, l + 1);
    exit_section_(b, m, AUTHOR_, r);
    return r;
  }

  // [DOT]
  private static boolean author__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "author__1")) return false;
    consumeToken(b, DOT);
    return true;
  }

  // [STRING]
  private static boolean author__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "author__2")) return false;
    consumeToken(b, STRING);
    return true;
  }

  // [DOT]
  private static boolean author__3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "author__3")) return false;
    consumeToken(b, DOT);
    return true;
  }

  /* ********************************************************** */
  // CALL identifier_or_string_ [USING IDENTIFIER]
  public static boolean call_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "call_")) return false;
    if (!nextTokenIs(b, CALL)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CALL_, null);
    r = consumeToken(b, CALL);
    p = r; // pin = 1
    r = r && report_error_(b, identifier_or_string_(b, l + 1));
    r = p && call__2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [USING IDENTIFIER]
  private static boolean call__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "call__2")) return false;
    parseTokens(b, 0, USING, IDENTIFIER);
    return true;
  }

  /* ********************************************************** */
  // if_condition_operand [IS] [not] (equals_|greater_|less_) if_condition_operand
  static boolean comp_condition_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comp_condition_")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = if_condition_operand(b, l + 1);
    r = r && comp_condition__1(b, l + 1);
    r = r && comp_condition__2(b, l + 1);
    r = r && comp_condition__3(b, l + 1);
    r = r && if_condition_operand(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [IS]
  private static boolean comp_condition__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comp_condition__1")) return false;
    consumeToken(b, IS);
    return true;
  }

  // [not]
  private static boolean comp_condition__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comp_condition__2")) return false;
    consumeToken(b, NOT);
    return true;
  }

  // equals_|greater_|less_
  private static boolean comp_condition__3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comp_condition__3")) return false;
    boolean r;
    r = equals_(b, l + 1);
    if (!r) r = greater_(b, l + 1);
    if (!r) r = less_(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // condition_ ((and|or) condition_)*
  static boolean composite_condition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "composite_condition")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = condition_(b, l + 1);
    r = r && composite_condition_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ((and|or) condition_)*
  private static boolean composite_condition_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "composite_condition_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!composite_condition_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "composite_condition_1", c)) break;
    }
    return true;
  }

  // (and|or) condition_
  private static boolean composite_condition_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "composite_condition_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = composite_condition_1_0_0(b, l + 1);
    r = r && condition_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // and|or
  private static boolean composite_condition_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "composite_condition_1_0_0")) return false;
    boolean r;
    r = consumeToken(b, AND);
    if (!r) r = consumeToken(b, OR);
    return r;
  }

  /* ********************************************************** */
  // COMPUTE list_of_items EQUALS_OP add_or_subtract_expr_
  public static boolean compute_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "compute_")) return false;
    if (!nextTokenIs(b, COMPUTE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, COMPUTE_, null);
    r = consumeToken(b, COMPUTE);
    p = r; // pin = 1
    r = r && report_error_(b, list_of_items(b, l + 1));
    r = p && report_error_(b, consumeToken(b, EQUALS_OP)) && r;
    r = p && add_or_subtract_expr_(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // not*  (parenthesis_condition|non_parenthesis_condition)
  public static boolean condition_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition_")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, CONDITION_, "<condition>");
    r = condition__0(b, l + 1);
    r = r && condition__1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // not*
  private static boolean condition__0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NOT)) break;
      if (!empty_element_parsed_guard_(b, "condition__0", c)) break;
    }
    return true;
  }

  // parenthesis_condition|non_parenthesis_condition
  private static boolean condition__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "condition__1")) return false;
    boolean r;
    r = parenthesis_condition(b, l + 1);
    if (!r) r = non_parenthesis_condition(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // '88' item_name_decl_ VALUE (literal_ THROUGH literal_ | list_of_literals)
  public static boolean conditional_item_decl_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditional_item_decl_")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CONDITIONAL_ITEM_DECL_, "<conditional item decl>");
    r = consumeToken(b, "88");
    p = r; // pin = 1
    r = r && report_error_(b, item_name_decl_(b, l + 1));
    r = p && report_error_(b, consumeToken(b, VALUE)) && r;
    r = p && conditional_item_decl__3(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // literal_ THROUGH literal_ | list_of_literals
  private static boolean conditional_item_decl__3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditional_item_decl__3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = conditional_item_decl__3_0(b, l + 1);
    if (!r) r = list_of_literals(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // literal_ THROUGH literal_
  private static boolean conditional_item_decl__3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditional_item_decl__3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = literal_(b, l + 1);
    r = r && consumeToken(b, THROUGH);
    r = r && literal_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // COPY STRING DOT
  public static boolean copy_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "copy_")) return false;
    if (!nextTokenIs(b, COPY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, COPY_, null);
    r = consumeTokens(b, 1, COPY, STRING, DOT);
    p = r; // pin = 1
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // DATA_DIVISION DOT
  //                ws_section_?
  //                ls_section_?
  //                linkage_section_?
  public static boolean data_division_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_division_")) return false;
    if (!nextTokenIs(b, DATA_DIVISION)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DATA_DIVISION, DOT);
    r = r && data_division__2(b, l + 1);
    r = r && data_division__3(b, l + 1);
    r = r && data_division__4(b, l + 1);
    exit_section_(b, m, DATA_DIVISION_, r);
    return r;
  }

  // ws_section_?
  private static boolean data_division__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_division__2")) return false;
    ws_section_(b, l + 1);
    return true;
  }

  // ls_section_?
  private static boolean data_division__3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_division__3")) return false;
    ls_section_(b, l + 1);
    return true;
  }

  // linkage_section_?
  private static boolean data_division__4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_division__4")) return false;
    linkage_section_(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // DATE-COMPILED [DOT] [STRING] [DOT]
  public static boolean date_compiled_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "date_compiled_")) return false;
    if (!nextTokenIs(b, DATE_COMPILED)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DATE_COMPILED);
    r = r && date_compiled__1(b, l + 1);
    r = r && date_compiled__2(b, l + 1);
    r = r && date_compiled__3(b, l + 1);
    exit_section_(b, m, DATE_COMPILED_, r);
    return r;
  }

  // [DOT]
  private static boolean date_compiled__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "date_compiled__1")) return false;
    consumeToken(b, DOT);
    return true;
  }

  // [STRING]
  private static boolean date_compiled__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "date_compiled__2")) return false;
    consumeToken(b, STRING);
    return true;
  }

  // [DOT]
  private static boolean date_compiled__3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "date_compiled__3")) return false;
    consumeToken(b, DOT);
    return true;
  }

  /* ********************************************************** */
  // DATE-WRITTEN [DOT] [STRING] [DOT]
  public static boolean date_written_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "date_written_")) return false;
    if (!nextTokenIs(b, DATE_WRITTEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DATE_WRITTEN);
    r = r && date_written__1(b, l + 1);
    r = r && date_written__2(b, l + 1);
    r = r && date_written__3(b, l + 1);
    exit_section_(b, m, DATE_WRITTEN_, r);
    return r;
  }

  // [DOT]
  private static boolean date_written__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "date_written__1")) return false;
    consumeToken(b, DOT);
    return true;
  }

  // [STRING]
  private static boolean date_written__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "date_written__2")) return false;
    consumeToken(b, STRING);
    return true;
  }

  // [DOT]
  private static boolean date_written__3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "date_written__3")) return false;
    consumeToken(b, DOT);
    return true;
  }

  /* ********************************************************** */
  // DISPLAY list_of_items_or_literals
  public static boolean display_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "display_")) return false;
    if (!nextTokenIs(b, DISPLAY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DISPLAY_, null);
    r = consumeToken(b, DISPLAY);
    p = r; // pin = 1
    r = r && list_of_items_or_literals(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // DIVIDE item_or_number_literal_ (divide_into|divide_by)
  public static boolean divide_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "divide_")) return false;
    if (!nextTokenIs(b, DIVIDE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DIVIDE_, null);
    r = consumeToken(b, DIVIDE);
    p = r; // pin = 1
    r = r && report_error_(b, item_or_number_literal_(b, l + 1));
    r = p && divide__2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // divide_into|divide_by
  private static boolean divide__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "divide__2")) return false;
    boolean r;
    r = divide_into(b, l + 1);
    if (!r) r = divide_by(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // BY item_or_number_literal_ GIVING (item REMAINDER item | list_of_items)
  static boolean divide_by(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "divide_by")) return false;
    if (!nextTokenIs(b, BY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, BY);
    p = r; // pin = 1
    r = r && report_error_(b, item_or_number_literal_(b, l + 1));
    r = p && report_error_(b, consumeToken(b, GIVING)) && r;
    r = p && divide_by_3(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // item REMAINDER item | list_of_items
  private static boolean divide_by_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "divide_by_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = divide_by_3_0(b, l + 1);
    if (!r) r = list_of_items(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // item REMAINDER item
  private static boolean divide_by_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "divide_by_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = item(b, l + 1);
    r = r && consumeToken(b, REMAINDER);
    r = r && item(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // INTO (item GIVING item REMAINDER item | item GIVING list_of_items | list_of_items)
  static boolean divide_into(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "divide_into")) return false;
    if (!nextTokenIs(b, INTO)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, INTO);
    p = r; // pin = 1
    r = r && divide_into_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // item GIVING item REMAINDER item | item GIVING list_of_items | list_of_items
  private static boolean divide_into_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "divide_into_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = divide_into_1_0(b, l + 1);
    if (!r) r = divide_into_1_1(b, l + 1);
    if (!r) r = list_of_items(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // item GIVING item REMAINDER item
  private static boolean divide_into_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "divide_into_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = item(b, l + 1);
    r = r && consumeToken(b, GIVING);
    r = r && item(b, l + 1);
    r = r && consumeToken(b, REMAINDER);
    r = r && item(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // item GIVING list_of_items
  private static boolean divide_into_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "divide_into_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = item(b, l + 1);
    r = r && consumeToken(b, GIVING);
    r = r && list_of_items(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // item_level_decl_ item_name_decl_ (normal_| redefines_)
  public static boolean elementary_item_decl_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "elementary_item_decl_")) return false;
    if (!nextTokenIs(b, INTEGER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = item_level_decl_(b, l + 1);
    r = r && item_name_decl_(b, l + 1);
    r = r && elementary_item_decl__2(b, l + 1);
    exit_section_(b, m, ELEMENTARY_ITEM_DECL_, r);
    return r;
  }

  // normal_| redefines_
  private static boolean elementary_item_decl__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "elementary_item_decl__2")) return false;
    boolean r;
    r = normal_(b, l + 1);
    if (!r) r = redefines_(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // END PROGRAM program_name_ DOT
  public static boolean end_program_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "end_program_")) return false;
    if (!nextTokenIs(b, END)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, END_PROGRAM_, null);
    r = consumeTokens(b, 2, END, PROGRAM);
    p = r; // pin = 2
    r = r && report_error_(b, program_name_(b, l + 1));
    r = p && consumeToken(b, DOT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // ENVIRONMENT_DIVISION DOT
  public static boolean environment_division_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "environment_division_")) return false;
    if (!nextTokenIs(b, ENVIRONMENT_DIVISION)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ENVIRONMENT_DIVISION, DOT);
    exit_section_(b, m, ENVIRONMENT_DIVISION_, r);
    return r;
  }

  /* ********************************************************** */
  // equal [TO] | EQUALS_OP
  static boolean equals_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "equals_")) return false;
    if (!nextTokenIs(b, "", EQUAL, EQUALS_OP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = equals__0(b, l + 1);
    if (!r) r = consumeToken(b, EQUALS_OP);
    exit_section_(b, m, null, r);
    return r;
  }

  // equal [TO]
  private static boolean equals__0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "equals__0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUAL);
    r = r && equals__0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [TO]
  private static boolean equals__0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "equals__0_1")) return false;
    consumeToken(b, TO);
    return true;
  }

  /* ********************************************************** */
  // (program_|copy_)*
  static boolean file(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file")) return false;
    while (true) {
      int c = current_position_(b);
      if (!file_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "file", c)) break;
    }
    return true;
  }

  // program_|copy_
  private static boolean file_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_0")) return false;
    boolean r;
    r = program_(b, l + 1);
    if (!r) r = copy_(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // greater [than] [or equal [TO]] |MORE_OP|MORE_EQUAL_OP
  static boolean greater_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "greater_")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = greater__0(b, l + 1);
    if (!r) r = consumeToken(b, MORE_OP);
    if (!r) r = consumeToken(b, MORE_EQUAL_OP);
    exit_section_(b, m, null, r);
    return r;
  }

  // greater [than] [or equal [TO]]
  private static boolean greater__0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "greater__0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GREATER);
    r = r && greater__0_1(b, l + 1);
    r = r && greater__0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [than]
  private static boolean greater__0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "greater__0_1")) return false;
    consumeToken(b, THAN);
    return true;
  }

  // [or equal [TO]]
  private static boolean greater__0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "greater__0_2")) return false;
    greater__0_2_0(b, l + 1);
    return true;
  }

  // or equal [TO]
  private static boolean greater__0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "greater__0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OR, EQUAL);
    r = r && greater__0_2_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [TO]
  private static boolean greater__0_2_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "greater__0_2_0_2")) return false;
    consumeToken(b, TO);
    return true;
  }

  /* ********************************************************** */
  // item_level_decl_ item_name_decl_
  public static boolean group_item_decl_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "group_item_decl_")) return false;
    if (!nextTokenIs(b, INTEGER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = item_level_decl_(b, l + 1);
    r = r && item_name_decl_(b, l + 1);
    exit_section_(b, m, GROUP_ITEM_DECL_, r);
    return r;
  }

  /* ********************************************************** */
  // high-value|high-values
  static boolean high_value_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "high_value_")) return false;
    if (!nextTokenIs(b, "", HIGH_VALUE, HIGH_VALUES)) return false;
    boolean r;
    r = consumeToken(b, HIGH_VALUE);
    if (!r) r = consumeToken(b, HIGH_VALUES);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFICATION_DIVISION DOT
  //                                program_id_
  //                                author_?
  //                                installation_?
  //                                date_written_?
  //                                date_compiled_?
  //                                security_?
  public static boolean identification_division_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identification_division_")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, IDENTIFICATION_DIVISION_, "<identification division>");
    r = consumeTokens(b, 1, IDENTIFICATION_DIVISION, DOT);
    p = r; // pin = 1
    r = r && report_error_(b, program_id_(b, l + 1));
    r = p && report_error_(b, identification_division__3(b, l + 1)) && r;
    r = p && report_error_(b, identification_division__4(b, l + 1)) && r;
    r = p && report_error_(b, identification_division__5(b, l + 1)) && r;
    r = p && report_error_(b, identification_division__6(b, l + 1)) && r;
    r = p && identification_division__7(b, l + 1) && r;
    exit_section_(b, l, m, r, p, not_other_division_parser_);
    return r || p;
  }

  // author_?
  private static boolean identification_division__3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identification_division__3")) return false;
    author_(b, l + 1);
    return true;
  }

  // installation_?
  private static boolean identification_division__4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identification_division__4")) return false;
    installation_(b, l + 1);
    return true;
  }

  // date_written_?
  private static boolean identification_division__5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identification_division__5")) return false;
    date_written_(b, l + 1);
    return true;
  }

  // date_compiled_?
  private static boolean identification_division__6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identification_division__6")) return false;
    date_compiled_(b, l + 1);
    return true;
  }

  // security_?
  private static boolean identification_division__7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identification_division__7")) return false;
    security_(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER|STRING
  public static boolean identifier_or_string_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifier_or_string_")) return false;
    if (!nextTokenIs(b, "<identifier or string>", IDENTIFIER, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, IDENTIFIER_OR_STRING_, "<identifier or string>");
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, STRING);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // if composite_condition [then] statement_+ [else statement_+] [end-if]
  public static boolean if_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_")) return false;
    if (!nextTokenIs(b, IF)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, IF_, null);
    r = consumeToken(b, IF);
    p = r; // pin = 1
    r = r && report_error_(b, composite_condition(b, l + 1));
    r = p && report_error_(b, if__2(b, l + 1)) && r;
    r = p && report_error_(b, if__3(b, l + 1)) && r;
    r = p && report_error_(b, if__4(b, l + 1)) && r;
    r = p && if__5(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [then]
  private static boolean if__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if__2")) return false;
    consumeToken(b, THEN);
    return true;
  }

  // statement_+
  private static boolean if__3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if__3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = statement_(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!statement_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "if__3", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // [else statement_+]
  private static boolean if__4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if__4")) return false;
    if__4_0(b, l + 1);
    return true;
  }

  // else statement_+
  private static boolean if__4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if__4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELSE);
    r = r && if__4_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // statement_+
  private static boolean if__4_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if__4_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = statement_(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!statement_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "if__4_0_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // [end-if]
  private static boolean if__5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if__5")) return false;
    consumeToken(b, END_IF);
    return true;
  }

  /* ********************************************************** */
  // add_or_subtract_expr_
  public static boolean if_condition_operand(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_condition_operand")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, IF_CONDITION_OPERAND, "<if condition operand>");
    r = add_or_subtract_expr_(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INITIALIZE list_of_items [replacing_]
  public static boolean initialize_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "initialize_")) return false;
    if (!nextTokenIs(b, INITIALIZE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, INITIALIZE_, null);
    r = consumeToken(b, INITIALIZE);
    p = r; // pin = 1
    r = r && report_error_(b, list_of_items(b, l + 1));
    r = p && initialize__2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [replacing_]
  private static boolean initialize__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "initialize__2")) return false;
    replacing_(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // INSTALLATION [DOT] [STRING] [DOT]
  public static boolean installation_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "installation_")) return false;
    if (!nextTokenIs(b, INSTALLATION)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INSTALLATION);
    r = r && installation__1(b, l + 1);
    r = r && installation__2(b, l + 1);
    r = r && installation__3(b, l + 1);
    exit_section_(b, m, INSTALLATION_, r);
    return r;
  }

  // [DOT]
  private static boolean installation__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "installation__1")) return false;
    consumeToken(b, DOT);
    return true;
  }

  // [STRING]
  private static boolean installation__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "installation__2")) return false;
    consumeToken(b, STRING);
    return true;
  }

  // [DOT]
  private static boolean installation__3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "installation__3")) return false;
    consumeToken(b, DOT);
    return true;
  }

  /* ********************************************************** */
  // item_usage_ of_item_usage*
  static boolean item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = item_usage_(b, l + 1);
    r = r && item_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // of_item_usage*
  private static boolean item_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!of_item_usage(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "item_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // elementary_item_decl_ | renames_item_decl_ | conditional_item_decl_ | group_item_decl_
  public static boolean item_decl_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_decl_")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, ITEM_DECL_, "<item decl>");
    r = elementary_item_decl_(b, l + 1);
    if (!r) r = renames_item_decl_(b, l + 1);
    if (!r) r = conditional_item_decl_(b, l + 1);
    if (!r) r = group_item_decl_(b, l + 1);
    exit_section_(b, l, m, r, false, not_dot_parser_);
    return r;
  }

  /* ********************************************************** */
  // PAREN_OPEN INTEGER PAREN_CLOSE
  public static boolean item_length_decl_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_length_decl_")) return false;
    if (!nextTokenIs(b, PAREN_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PAREN_OPEN, INTEGER, PAREN_CLOSE);
    exit_section_(b, m, ITEM_LENGTH_DECL_, r);
    return r;
  }

  /* ********************************************************** */
  // INTEGER
  static boolean item_level_decl_(PsiBuilder b, int l) {
    return consumeToken(b, INTEGER);
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean item_name_decl_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_name_decl_")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, ITEM_NAME_DECL_, r);
    return r;
  }

  /* ********************************************************** */
  // item|literal_
  static boolean item_or_literal_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_or_literal_")) return false;
    boolean r;
    r = item(b, l + 1);
    if (!r) r = literal_(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // item|number_literal_
  static boolean item_or_number_literal_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_or_number_literal_")) return false;
    boolean r;
    r = item(b, l + 1);
    if (!r) r = number_literal_(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // (PIC|PICTURE) [IS] (
  //                                          PIC_TYPE_ALPHA item_length_decl_ |
  //                                          PIC_TYPE_ALPHANUMERIC item_length_decl_ |
  //                                          PIC_TYPE_NUMERIC item_length_decl_ [PIC_TYPE_DECIMAL_NUMERIC item_length_decl_] |
  //                                          PIC_TYPE_SIGNED_NUMERIC item_length_decl_ [PIC_TYPE_DECIMAL_NUMERIC item_length_decl_]
  //                                        )
  public static boolean item_type_decl_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_type_decl_")) return false;
    if (!nextTokenIs(b, "<item type decl>", PIC, PICTURE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ITEM_TYPE_DECL_, "<item type decl>");
    r = item_type_decl__0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, item_type_decl__1(b, l + 1));
    r = p && item_type_decl__2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // PIC|PICTURE
  private static boolean item_type_decl__0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_type_decl__0")) return false;
    boolean r;
    r = consumeToken(b, PIC);
    if (!r) r = consumeToken(b, PICTURE);
    return r;
  }

  // [IS]
  private static boolean item_type_decl__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_type_decl__1")) return false;
    consumeToken(b, IS);
    return true;
  }

  // PIC_TYPE_ALPHA item_length_decl_ |
  //                                          PIC_TYPE_ALPHANUMERIC item_length_decl_ |
  //                                          PIC_TYPE_NUMERIC item_length_decl_ [PIC_TYPE_DECIMAL_NUMERIC item_length_decl_] |
  //                                          PIC_TYPE_SIGNED_NUMERIC item_length_decl_ [PIC_TYPE_DECIMAL_NUMERIC item_length_decl_]
  private static boolean item_type_decl__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_type_decl__2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = item_type_decl__2_0(b, l + 1);
    if (!r) r = item_type_decl__2_1(b, l + 1);
    if (!r) r = item_type_decl__2_2(b, l + 1);
    if (!r) r = item_type_decl__2_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PIC_TYPE_ALPHA item_length_decl_
  private static boolean item_type_decl__2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_type_decl__2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PIC_TYPE_ALPHA);
    r = r && item_length_decl_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PIC_TYPE_ALPHANUMERIC item_length_decl_
  private static boolean item_type_decl__2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_type_decl__2_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PIC_TYPE_ALPHANUMERIC);
    r = r && item_length_decl_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PIC_TYPE_NUMERIC item_length_decl_ [PIC_TYPE_DECIMAL_NUMERIC item_length_decl_]
  private static boolean item_type_decl__2_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_type_decl__2_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PIC_TYPE_NUMERIC);
    r = r && item_length_decl_(b, l + 1);
    r = r && item_type_decl__2_2_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [PIC_TYPE_DECIMAL_NUMERIC item_length_decl_]
  private static boolean item_type_decl__2_2_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_type_decl__2_2_2")) return false;
    item_type_decl__2_2_2_0(b, l + 1);
    return true;
  }

  // PIC_TYPE_DECIMAL_NUMERIC item_length_decl_
  private static boolean item_type_decl__2_2_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_type_decl__2_2_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PIC_TYPE_DECIMAL_NUMERIC);
    r = r && item_length_decl_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PIC_TYPE_SIGNED_NUMERIC item_length_decl_ [PIC_TYPE_DECIMAL_NUMERIC item_length_decl_]
  private static boolean item_type_decl__2_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_type_decl__2_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PIC_TYPE_SIGNED_NUMERIC);
    r = r && item_length_decl_(b, l + 1);
    r = r && item_type_decl__2_3_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [PIC_TYPE_DECIMAL_NUMERIC item_length_decl_]
  private static boolean item_type_decl__2_3_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_type_decl__2_3_2")) return false;
    item_type_decl__2_3_2_0(b, l + 1);
    return true;
  }

  // PIC_TYPE_DECIMAL_NUMERIC item_length_decl_
  private static boolean item_type_decl__2_3_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_type_decl__2_3_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PIC_TYPE_DECIMAL_NUMERIC);
    r = r && item_length_decl_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean item_usage_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_usage_")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, ITEM_USAGE_, r);
    return r;
  }

  /* ********************************************************** */
  // VALUE [IS] (literal_|null_)
  public static boolean item_value_decl_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_value_decl_")) return false;
    if (!nextTokenIs(b, VALUE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ITEM_VALUE_DECL_, null);
    r = consumeToken(b, VALUE);
    p = r; // pin = 1
    r = r && report_error_(b, item_value_decl__1(b, l + 1));
    r = p && item_value_decl__2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [IS]
  private static boolean item_value_decl__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_value_decl__1")) return false;
    consumeToken(b, IS);
    return true;
  }

  // literal_|null_
  private static boolean item_value_decl__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_value_decl__2")) return false;
    boolean r;
    r = literal_(b, l + 1);
    if (!r) r = null_(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // less [than] [or equal [TO]] |LESS_OP|LESS_EQUAL_OP
  static boolean less_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "less_")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = less__0(b, l + 1);
    if (!r) r = consumeToken(b, LESS_OP);
    if (!r) r = consumeToken(b, LESS_EQUAL_OP);
    exit_section_(b, m, null, r);
    return r;
  }

  // less [than] [or equal [TO]]
  private static boolean less__0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "less__0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LESS);
    r = r && less__0_1(b, l + 1);
    r = r && less__0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [than]
  private static boolean less__0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "less__0_1")) return false;
    consumeToken(b, THAN);
    return true;
  }

  // [or equal [TO]]
  private static boolean less__0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "less__0_2")) return false;
    less__0_2_0(b, l + 1);
    return true;
  }

  // or equal [TO]
  private static boolean less__0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "less__0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OR, EQUAL);
    r = r && less__0_2_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [TO]
  private static boolean less__0_2_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "less__0_2_0_2")) return false;
    consumeToken(b, TO);
    return true;
  }

  /* ********************************************************** */
  // LINKAGE_SECTION DOT
  //                        (item_decl_ DOT)*
  public static boolean linkage_section_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "linkage_section_")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, LINKAGE_SECTION_, "<linkage section>");
    r = consumeTokens(b, 1, LINKAGE_SECTION, DOT);
    p = r; // pin = 1
    r = r && linkage_section__2(b, l + 1);
    exit_section_(b, l, m, r, p, not_linkage_section_end_parser_);
    return r || p;
  }

  // (item_decl_ DOT)*
  private static boolean linkage_section__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "linkage_section__2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!linkage_section__2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "linkage_section__2", c)) break;
    }
    return true;
  }

  // item_decl_ DOT
  private static boolean linkage_section__2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "linkage_section__2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = item_decl_(b, l + 1);
    r = r && consumeToken(b, DOT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // <<item>> ([COMMA] <<item>>)*
  static boolean list(PsiBuilder b, int l, Parser _item) {
    if (!recursion_guard_(b, l, "list")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = _item.parse(b, l);
    r = r && list_1(b, l + 1, _item);
    exit_section_(b, m, null, r);
    return r;
  }

  // ([COMMA] <<item>>)*
  private static boolean list_1(PsiBuilder b, int l, Parser _item) {
    if (!recursion_guard_(b, l, "list_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!list_1_0(b, l + 1, _item)) break;
      if (!empty_element_parsed_guard_(b, "list_1", c)) break;
    }
    return true;
  }

  // [COMMA] <<item>>
  private static boolean list_1_0(PsiBuilder b, int l, Parser _item) {
    if (!recursion_guard_(b, l, "list_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = list_1_0_0(b, l + 1);
    r = r && _item.parse(b, l);
    exit_section_(b, m, null, r);
    return r;
  }

  // [COMMA]
  private static boolean list_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_1_0_0")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // <<list item>>
  static boolean list_of_items(PsiBuilder b, int l) {
    return list(b, l + 1, item_parser_);
  }

  /* ********************************************************** */
  // <<list item_or_literal_>>
  static boolean list_of_items_or_literals(PsiBuilder b, int l) {
    return list(b, l + 1, item_or_literal__parser_);
  }

  /* ********************************************************** */
  // <<list item_or_number_literal_>>
  static boolean list_of_items_or_numbers(PsiBuilder b, int l) {
    return list(b, l + 1, item_or_number_literal__parser_);
  }

  /* ********************************************************** */
  // <<list literal_>>
  static boolean list_of_literals(PsiBuilder b, int l) {
    return list(b, l + 1, literal__parser_);
  }

  /* ********************************************************** */
  // string_literal_|number_literal_
  public static boolean literal_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal_")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LITERAL_, "<literal>");
    r = string_literal_(b, l + 1);
    if (!r) r = number_literal_(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // low-value|low-values
  static boolean low_value_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "low_value_")) return false;
    if (!nextTokenIs(b, "", LOW_VALUE, LOW_VALUES)) return false;
    boolean r;
    r = consumeToken(b, LOW_VALUE);
    if (!r) r = consumeToken(b, LOW_VALUES);
    return r;
  }

  /* ********************************************************** */
  // LOCAL_STORAGE_SECTION DOT
  //                   (item_decl_ DOT)*
  public static boolean ls_section_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ls_section_")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, LS_SECTION_, "<ls section>");
    r = consumeTokens(b, 1, LOCAL_STORAGE_SECTION, DOT);
    p = r; // pin = 1
    r = r && ls_section__2(b, l + 1);
    exit_section_(b, l, m, r, p, not_ls_section_end_parser_);
    return r || p;
  }

  // (item_decl_ DOT)*
  private static boolean ls_section__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ls_section__2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ls_section__2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ls_section__2", c)) break;
    }
    return true;
  }

  // item_decl_ DOT
  private static boolean ls_section__2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ls_section__2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = item_decl_(b, l + 1);
    r = r && consumeToken(b, DOT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // item_or_number_literal_ | PAREN_OPEN add_or_subtract_expr_ PAREN_CLOSE
  static boolean math_operand(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "math_operand")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = item_or_number_literal_(b, l + 1);
    if (!r) r = math_operand_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PAREN_OPEN add_or_subtract_expr_ PAREN_CLOSE
  private static boolean math_operand_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "math_operand_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PAREN_OPEN);
    r = r && add_or_subtract_expr_(b, l + 1);
    r = r && consumeToken(b, PAREN_CLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // MOVE [CORR|CORRESPONDING] item_or_literal_ TO list_of_items
  public static boolean move_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "move_")) return false;
    if (!nextTokenIs(b, MOVE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MOVE_, null);
    r = consumeToken(b, MOVE);
    p = r; // pin = 1
    r = r && report_error_(b, move__1(b, l + 1));
    r = p && report_error_(b, item_or_literal_(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, TO)) && r;
    r = p && list_of_items(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [CORR|CORRESPONDING]
  private static boolean move__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "move__1")) return false;
    move__1_0(b, l + 1);
    return true;
  }

  // CORR|CORRESPONDING
  private static boolean move__1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "move__1_0")) return false;
    boolean r;
    r = consumeToken(b, CORR);
    if (!r) r = consumeToken(b, CORRESPONDING);
    return r;
  }

  /* ********************************************************** */
  // MULTIPLY item_or_number_literal_ BY (item GIVING list_of_items | list_of_items)
  public static boolean multiply_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiply_")) return false;
    if (!nextTokenIs(b, MULTIPLY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MULTIPLY_, null);
    r = consumeToken(b, MULTIPLY);
    p = r; // pin = 1
    r = r && report_error_(b, item_or_number_literal_(b, l + 1));
    r = p && report_error_(b, consumeToken(b, BY)) && r;
    r = p && multiply__3(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // item GIVING list_of_items | list_of_items
  private static boolean multiply__3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiply__3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = multiply__3_0(b, l + 1);
    if (!r) r = list_of_items(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // item GIVING list_of_items
  private static boolean multiply__3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiply__3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = item(b, l + 1);
    r = r && consumeToken(b, GIVING);
    r = r && list_of_items(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // power_expr_ ((MULTIPLY_OP | DIVIDE_OP ) power_expr_)*
  static boolean multiply_or_divide_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiply_or_divide_expr")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = power_expr_(b, l + 1);
    r = r && multiply_or_divide_expr_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ((MULTIPLY_OP | DIVIDE_OP ) power_expr_)*
  private static boolean multiply_or_divide_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiply_or_divide_expr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!multiply_or_divide_expr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "multiply_or_divide_expr_1", c)) break;
    }
    return true;
  }

  // (MULTIPLY_OP | DIVIDE_OP ) power_expr_
  private static boolean multiply_or_divide_expr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiply_or_divide_expr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = multiply_or_divide_expr_1_0_0(b, l + 1);
    r = r && power_expr_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // MULTIPLY_OP | DIVIDE_OP
  private static boolean multiply_or_divide_expr_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiply_or_divide_expr_1_0_0")) return false;
    boolean r;
    r = consumeToken(b, MULTIPLY_OP);
    if (!r) r = consumeToken(b, DIVIDE_OP);
    return r;
  }

  /* ********************************************************** */
  // type_condition_|sign_condition_|comp_condition_|truth_condition_
  static boolean non_parenthesis_condition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "non_parenthesis_condition")) return false;
    boolean r;
    r = type_condition_(b, l + 1);
    if (!r) r = sign_condition_(b, l + 1);
    if (!r) r = comp_condition_(b, l + 1);
    if (!r) r = truth_condition_(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // item_type_decl_ [item_value_decl_]
  static boolean normal_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "normal_")) return false;
    if (!nextTokenIs(b, "", PIC, PICTURE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = item_type_decl_(b, l + 1);
    r = r && normal__1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [item_value_decl_]
  private static boolean normal__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "normal__1")) return false;
    item_value_decl_(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // !DOT
  static boolean not_dot(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_dot")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, DOT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // !(end_program_)
  static boolean not_end_program(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_end_program")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !not_end_program_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (end_program_)
  private static boolean not_end_program_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_end_program_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = end_program_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // !(procedure_division_)
  static boolean not_linkage_section_end(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_linkage_section_end")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !not_linkage_section_end_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (procedure_division_)
  private static boolean not_linkage_section_end_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_linkage_section_end_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = procedure_division_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // !(linkage_section_|procedure_division_)
  static boolean not_ls_section_end(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_ls_section_end")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !not_ls_section_end_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // linkage_section_|procedure_division_
  private static boolean not_ls_section_end_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_ls_section_end_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = linkage_section_(b, l + 1);
    if (!r) r = procedure_division_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // !(environment_division_|data_division_|procedure_division_)
  static boolean not_other_division(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_other_division")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !not_other_division_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // environment_division_|data_division_|procedure_division_
  private static boolean not_other_division_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_other_division_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = environment_division_(b, l + 1);
    if (!r) r = data_division_(b, l + 1);
    if (!r) r = procedure_division_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // !(ls_section_|linkage_section_|procedure_division_)
  static boolean not_wd_section_end(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_wd_section_end")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !not_wd_section_end_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ls_section_|linkage_section_|procedure_division_
  private static boolean not_wd_section_end_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_wd_section_end_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ls_section_(b, l + 1);
    if (!r) r = linkage_section_(b, l + 1);
    if (!r) r = procedure_division_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // null|nulls
  static boolean null_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "null_")) return false;
    if (!nextTokenIs(b, "", NULL, NULLS)) return false;
    boolean r;
    r = consumeToken(b, NULL);
    if (!r) r = consumeToken(b, NULLS);
    return r;
  }

  /* ********************************************************** */
  // INTEGER|FLOAT|zero_|all_
  static boolean number_literal_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "number_literal_")) return false;
    boolean r;
    r = consumeToken(b, INTEGER);
    if (!r) r = consumeToken(b, FLOAT);
    if (!r) r = zero_(b, l + 1);
    if (!r) r = all_(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // (OF|IN) item_usage_
  static boolean of_item_usage(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "of_item_usage")) return false;
    if (!nextTokenIs(b, "", IN, OF)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = of_item_usage_0(b, l + 1);
    p = r; // pin = 1
    r = r && item_usage_(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // OF|IN
  private static boolean of_item_usage_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "of_item_usage_0")) return false;
    boolean r;
    r = consumeToken(b, OF);
    if (!r) r = consumeToken(b, IN);
    return r;
  }

  /* ********************************************************** */
  // PAREN_OPEN composite_condition PAREN_CLOSE
  static boolean parenthesis_condition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parenthesis_condition")) return false;
    if (!nextTokenIs(b, PAREN_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PAREN_OPEN);
    r = r && composite_condition(b, l + 1);
    r = r && consumeToken(b, PAREN_CLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // math_operand  (POWER_OP math_operand)*
  static boolean power_expr_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "power_expr_")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = math_operand(b, l + 1);
    r = r && power_expr__1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (POWER_OP math_operand)*
  private static boolean power_expr__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "power_expr__1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!power_expr__1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "power_expr__1", c)) break;
    }
    return true;
  }

  // POWER_OP math_operand
  private static boolean power_expr__1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "power_expr__1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, POWER_OP);
    r = r && math_operand(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // PROCEDURE_DIVISION [using_] DOT
  //                           (program_|sentence_)*
  public static boolean procedure_division_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedure_division_")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PROCEDURE_DIVISION_, "<procedure division>");
    r = consumeToken(b, PROCEDURE_DIVISION);
    p = r; // pin = 1
    r = r && report_error_(b, procedure_division__1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, DOT)) && r;
    r = p && procedure_division__3(b, l + 1) && r;
    exit_section_(b, l, m, r, p, not_end_program_parser_);
    return r || p;
  }

  // [using_]
  private static boolean procedure_division__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedure_division__1")) return false;
    using_(b, l + 1);
    return true;
  }

  // (program_|sentence_)*
  private static boolean procedure_division__3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedure_division__3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!procedure_division__3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "procedure_division__3", c)) break;
    }
    return true;
  }

  // program_|sentence_
  private static boolean procedure_division__3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedure_division__3_0")) return false;
    boolean r;
    r = program_(b, l + 1);
    if (!r) r = sentence_(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // identification_division_
  //               environment_division_?
  //               data_division_?
  //               procedure_division_?
  //               end_program_
  public static boolean program_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "program_")) return false;
    if (!nextTokenIs(b, IDENTIFICATION_DIVISION)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identification_division_(b, l + 1);
    r = r && program__1(b, l + 1);
    r = r && program__2(b, l + 1);
    r = r && program__3(b, l + 1);
    r = r && end_program_(b, l + 1);
    exit_section_(b, m, PROGRAM_, r);
    return r;
  }

  // environment_division_?
  private static boolean program__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "program__1")) return false;
    environment_division_(b, l + 1);
    return true;
  }

  // data_division_?
  private static boolean program__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "program__2")) return false;
    data_division_(b, l + 1);
    return true;
  }

  // procedure_division_?
  private static boolean program__3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "program__3")) return false;
    procedure_division_(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // PROGRAM_ID DOT program_name_decl_ DOT
  public static boolean program_id_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "program_id_")) return false;
    if (!nextTokenIs(b, PROGRAM_ID)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PROGRAM_ID_, null);
    r = consumeTokens(b, 1, PROGRAM_ID, DOT);
    p = r; // pin = 1
    r = r && report_error_(b, program_name_decl_(b, l + 1));
    r = p && consumeToken(b, DOT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // identifier_or_string_
  public static boolean program_name_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "program_name_")) return false;
    if (!nextTokenIs(b, "<program name>", IDENTIFIER, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROGRAM_NAME_, "<program name>");
    r = identifier_or_string_(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // identifier_or_string_
  public static boolean program_name_decl_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "program_name_decl_")) return false;
    if (!nextTokenIs(b, "<program name decl>", IDENTIFIER, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROGRAM_NAME_DECL_, "<program name decl>");
    r = identifier_or_string_(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // quote|quotes
  static boolean quote_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "quote_")) return false;
    if (!nextTokenIs(b, "", QUOTE, QUOTES)) return false;
    boolean r;
    r = consumeToken(b, QUOTE);
    if (!r) r = consumeToken(b, QUOTES);
    return r;
  }

  /* ********************************************************** */
  // REDEFINES item_usage_ item_type_decl_
  static boolean redefines_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "redefines_")) return false;
    if (!nextTokenIs(b, REDEFINES)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, REDEFINES);
    r = r && item_usage_(b, l + 1);
    r = r && item_type_decl_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '66' item_name_decl_ RENAMES item_usage_ [THROUGH] item_usage_
  public static boolean renames_item_decl_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "renames_item_decl_")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, RENAMES_ITEM_DECL_, "<renames item decl>");
    r = consumeToken(b, "66");
    p = r; // pin = 1
    r = r && report_error_(b, item_name_decl_(b, l + 1));
    r = p && report_error_(b, consumeToken(b, RENAMES)) && r;
    r = p && report_error_(b, item_usage_(b, l + 1)) && r;
    r = p && report_error_(b, renames_item_decl__4(b, l + 1)) && r;
    r = p && item_usage_(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [THROUGH]
  private static boolean renames_item_decl__4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "renames_item_decl__4")) return false;
    consumeToken(b, THROUGH);
    return true;
  }

  /* ********************************************************** */
  // REPLACING (replacing_by_ [COMMA])+
  static boolean replacing_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "replacing_")) return false;
    if (!nextTokenIs(b, REPLACING)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, REPLACING);
    p = r; // pin = 1
    r = r && replacing__1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (replacing_by_ [COMMA])+
  private static boolean replacing__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "replacing__1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = replacing__1_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!replacing__1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "replacing__1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // replacing_by_ [COMMA]
  private static boolean replacing__1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "replacing__1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = replacing_by_(b, l + 1);
    r = r && replacing__1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [COMMA]
  private static boolean replacing__1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "replacing__1_0_1")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // (ALPHABETIC|ALPHANUMERIC|NUMERIC) [DATA] BY item_or_literal_
  static boolean replacing_by_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "replacing_by_")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = replacing_by__0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, replacing_by__1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, BY)) && r;
    r = p && item_or_literal_(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ALPHABETIC|ALPHANUMERIC|NUMERIC
  private static boolean replacing_by__0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "replacing_by__0")) return false;
    boolean r;
    r = consumeToken(b, ALPHABETIC);
    if (!r) r = consumeToken(b, ALPHANUMERIC);
    if (!r) r = consumeToken(b, NUMERIC);
    return r;
  }

  // [DATA]
  private static boolean replacing_by__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "replacing_by__1")) return false;
    consumeToken(b, DATA);
    return true;
  }

  /* ********************************************************** */
  // SECURITY [DOT] [STRING] [DOT]
  public static boolean security_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "security_")) return false;
    if (!nextTokenIs(b, SECURITY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SECURITY);
    r = r && security__1(b, l + 1);
    r = r && security__2(b, l + 1);
    r = r && security__3(b, l + 1);
    exit_section_(b, m, SECURITY_, r);
    return r;
  }

  // [DOT]
  private static boolean security__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "security__1")) return false;
    consumeToken(b, DOT);
    return true;
  }

  // [STRING]
  private static boolean security__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "security__2")) return false;
    consumeToken(b, STRING);
    return true;
  }

  // [DOT]
  private static boolean security__3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "security__3")) return false;
    consumeToken(b, DOT);
    return true;
  }

  /* ********************************************************** */
  // statement_+ DOT
  public static boolean sentence_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sentence_")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SENTENCE_, "<sentence>");
    r = sentence__0(b, l + 1);
    p = r; // pin = 1
    r = r && consumeToken(b, DOT);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // statement_+
  private static boolean sentence__0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sentence__0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = statement_(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!statement_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "sentence__0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // SET list_of_items TO TRUE
  public static boolean set_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "set_")) return false;
    if (!nextTokenIs(b, SET)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SET_, null);
    r = consumeToken(b, SET);
    p = r; // pin = 1
    r = r && report_error_(b, list_of_items(b, l + 1));
    r = p && report_error_(b, consumeTokens(b, -1, TO, TRUE)) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // if_condition_operand [IS] [not] (positive|negative|zero_)
  static boolean sign_condition_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sign_condition_")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = if_condition_operand(b, l + 1);
    r = r && sign_condition__1(b, l + 1);
    r = r && sign_condition__2(b, l + 1);
    r = r && sign_condition__3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [IS]
  private static boolean sign_condition__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sign_condition__1")) return false;
    consumeToken(b, IS);
    return true;
  }

  // [not]
  private static boolean sign_condition__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sign_condition__2")) return false;
    consumeToken(b, NOT);
    return true;
  }

  // positive|negative|zero_
  private static boolean sign_condition__3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sign_condition__3")) return false;
    boolean r;
    r = consumeToken(b, POSITIVE);
    if (!r) r = consumeToken(b, NEGATIVE);
    if (!r) r = zero_(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // space|spaces
  static boolean space_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "space_")) return false;
    if (!nextTokenIs(b, "", SPACE, SPACES)) return false;
    boolean r;
    r = consumeToken(b, SPACE);
    if (!r) r = consumeToken(b, SPACES);
    return r;
  }

  /* ********************************************************** */
  // if_|accept_|display_|initialize_|move_|add_|subtract_|multiply_|divide_|compute_|call_|set_
  public static boolean statement_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATEMENT_, "<statement>");
    r = if_(b, l + 1);
    if (!r) r = accept_(b, l + 1);
    if (!r) r = display_(b, l + 1);
    if (!r) r = initialize_(b, l + 1);
    if (!r) r = move_(b, l + 1);
    if (!r) r = add_(b, l + 1);
    if (!r) r = subtract_(b, l + 1);
    if (!r) r = multiply_(b, l + 1);
    if (!r) r = divide_(b, l + 1);
    if (!r) r = compute_(b, l + 1);
    if (!r) r = call_(b, l + 1);
    if (!r) r = set_(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // STRING|FLOAT|zero_|space_|low_value_|high_value_|quote_|all_
  static boolean string_literal_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_literal_")) return false;
    boolean r;
    r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, FLOAT);
    if (!r) r = zero_(b, l + 1);
    if (!r) r = space_(b, l + 1);
    if (!r) r = low_value_(b, l + 1);
    if (!r) r = high_value_(b, l + 1);
    if (!r) r = quote_(b, l + 1);
    if (!r) r = all_(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // SUBTRACT (subtract_corr_|subtract_not_corr)
  public static boolean subtract_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subtract_")) return false;
    if (!nextTokenIs(b, SUBTRACT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SUBTRACT_, null);
    r = consumeToken(b, SUBTRACT);
    p = r; // pin = 1
    r = r && subtract__1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // subtract_corr_|subtract_not_corr
  private static boolean subtract__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subtract__1")) return false;
    boolean r;
    r = subtract_corr_(b, l + 1);
    if (!r) r = subtract_not_corr(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // (CORR|CORRESPONDING) item FROM item
  static boolean subtract_corr_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subtract_corr_")) return false;
    if (!nextTokenIs(b, "", CORR, CORRESPONDING)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = subtract_corr__0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, item(b, l + 1));
    r = p && report_error_(b, consumeToken(b, FROM)) && r;
    r = p && item(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // CORR|CORRESPONDING
  private static boolean subtract_corr__0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subtract_corr__0")) return false;
    boolean r;
    r = consumeToken(b, CORR);
    if (!r) r = consumeToken(b, CORRESPONDING);
    return r;
  }

  /* ********************************************************** */
  // list_of_items_or_numbers (FROM item GIVING list_of_items | FROM list_of_items)
  static boolean subtract_not_corr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subtract_not_corr")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = list_of_items_or_numbers(b, l + 1);
    p = r; // pin = 1
    r = r && subtract_not_corr_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // FROM item GIVING list_of_items | FROM list_of_items
  private static boolean subtract_not_corr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subtract_not_corr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = subtract_not_corr_1_0(b, l + 1);
    if (!r) r = subtract_not_corr_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // FROM item GIVING list_of_items
  private static boolean subtract_not_corr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subtract_not_corr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FROM);
    r = r && item(b, l + 1);
    r = r && consumeToken(b, GIVING);
    r = r && list_of_items(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // FROM list_of_items
  private static boolean subtract_not_corr_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subtract_not_corr_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FROM);
    r = r && list_of_items(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // [not] item
  static boolean truth_condition_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "truth_condition_")) return false;
    if (!nextTokenIs(b, "", IDENTIFIER, NOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = truth_condition__0(b, l + 1);
    r = r && item(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [not]
  private static boolean truth_condition__0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "truth_condition__0")) return false;
    consumeToken(b, NOT);
    return true;
  }

  /* ********************************************************** */
  // if_condition_operand [IS] [not] (numeric|alphabetic|alphabetic-lower|alphabetic-upper)
  static boolean type_condition_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_condition_")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = if_condition_operand(b, l + 1);
    r = r && type_condition__1(b, l + 1);
    r = r && type_condition__2(b, l + 1);
    r = r && type_condition__3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [IS]
  private static boolean type_condition__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_condition__1")) return false;
    consumeToken(b, IS);
    return true;
  }

  // [not]
  private static boolean type_condition__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_condition__2")) return false;
    consumeToken(b, NOT);
    return true;
  }

  // numeric|alphabetic|alphabetic-lower|alphabetic-upper
  private static boolean type_condition__3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_condition__3")) return false;
    boolean r;
    r = consumeToken(b, NUMERIC);
    if (!r) r = consumeToken(b, ALPHABETIC);
    if (!r) r = consumeToken(b, ALPHABETIC_LOWER);
    if (!r) r = consumeToken(b, ALPHABETIC_UPPER);
    return r;
  }

  /* ********************************************************** */
  // USING IDENTIFIER+
  static boolean using_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "using_")) return false;
    if (!nextTokenIs(b, USING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, USING);
    r = r && using__1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IDENTIFIER+
  private static boolean using__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "using__1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, IDENTIFIER)) break;
      if (!empty_element_parsed_guard_(b, "using__1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // WORKING_STORAGE_SECTION DOT
  //                   (item_decl_ DOT)*
  public static boolean ws_section_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ws_section_")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, WS_SECTION_, "<ws section>");
    r = consumeTokens(b, 1, WORKING_STORAGE_SECTION, DOT);
    p = r; // pin = 1
    r = r && ws_section__2(b, l + 1);
    exit_section_(b, l, m, r, p, not_wd_section_end_parser_);
    return r || p;
  }

  // (item_decl_ DOT)*
  private static boolean ws_section__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ws_section__2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ws_section__2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ws_section__2", c)) break;
    }
    return true;
  }

  // item_decl_ DOT
  private static boolean ws_section__2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ws_section__2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = item_decl_(b, l + 1);
    r = r && consumeToken(b, DOT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // zero|zeros|zeroes
  static boolean zero_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "zero_")) return false;
    boolean r;
    r = consumeToken(b, ZERO);
    if (!r) r = consumeToken(b, ZEROS);
    if (!r) r = consumeToken(b, ZEROES);
    return r;
  }

  static final Parser item_or_literal__parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return item_or_literal_(b, l + 1);
    }
  };
  static final Parser item_or_number_literal__parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return item_or_number_literal_(b, l + 1);
    }
  };
  static final Parser item_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return item(b, l + 1);
    }
  };
  static final Parser literal__parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return literal_(b, l + 1);
    }
  };
  static final Parser not_dot_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return not_dot(b, l + 1);
    }
  };
  static final Parser not_end_program_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return not_end_program(b, l + 1);
    }
  };
  static final Parser not_linkage_section_end_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return not_linkage_section_end(b, l + 1);
    }
  };
  static final Parser not_ls_section_end_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return not_ls_section_end(b, l + 1);
    }
  };
  static final Parser not_other_division_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return not_other_division(b, l + 1);
    }
  };
  static final Parser not_wd_section_end_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return not_wd_section_end(b, l + 1);
    }
  };
}
