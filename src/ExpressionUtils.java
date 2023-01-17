/**
 * 验证表达式工具类
 */
public class ExpressionUtils {

	/**
	 * 验证四则运算表达式是否准确
	 * @param expression
	 * @return
	 */
	public static boolean validateExpression(String expression) {

		//第一步：去掉算式中所有的合法项替换为"?"字符，去掉替换后算式中所有的空格
		expression = expression.replaceAll("((K|Z|C)\\d\\$(\\d)+(\\.(\\d)*){0,1})", "?");
		expression = expression.replaceAll(" ", "");
		// 如果有两个相邻的项中间没有操作符，则算式不合法
		if (expression.matches("^??$")) {
			return false;
		}
		// 最后一步：判断仅剩的+-*/四则运算算式是否合法，字母数字（增加包含小数）
		if (expression.matches("^(([+-]+[a-zA-Z0-9\\.]+[-+*/])*[a-zA-Z0-9\\.]+([-+*/][a-zA-Z0-9\\.]+)*)$")) {
			return true;
		} else {
			return false;
		}

	}
	
}
