package app.sandbox34;

import java.util.Arrays;
import java.util.GregorianCalendar;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import app.sandbox34.inventor.Inventor;

public class Sandbox34Main {

	public static void main(String[] args) {
		ex03();
	}

	private static void ex01() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("'Hello World'");//シングルクォートで囲っている
		String message = (String) exp.getValue();
		System.out.println(message);
	}

	private static void ex02() {
		final ExpressionParser parser = new SpelExpressionParser();

		//メソッド呼び出しが実行できる。
		Expression exp1 = parser.parseExpression("'Hello World'.concat('!')");
		String message1 = (String) exp1.getValue();
		System.out.println(message1);

		// プロパティアクセス(getBytes())の実行
		Expression exp2 = parser.parseExpression("'Hello World'.bytes");
		byte[] message2 = (byte[]) exp2.getValue();
		System.out.println(Arrays.toString(message2));

		// ドットでネストしたプロパティにアクセスできる。
		// パブックフィールドもサポート
		Expression exp3 = parser.parseExpression("'Hello World'.bytes.length");
		int message3 = (int) exp3.getValue();
		System.out.println(message3);

		// コンストラクタ関数の実行
		Expression exp4 = parser.parseExpression("new String('Hello World').toUpperCase()");
		//getValueにはクラスを渡すジェネリクス版もある
		String message4 = exp4.getValue(String.class);
		System.out.println(message4);
	}

	private static void ex03() {
		// ルートのオブジェクト tesla に対して
		// SpELを使ってプロパティアクセスとか評価をしている

		GregorianCalendar c = new GregorianCalendar();
		c.set(1856, 7, 9);

		// The constructor arguments are name, birthday, and nationality.
		Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

		ExpressionParser parser = new SpelExpressionParser();

		Expression exp = parser.parseExpression("name"); // Parse name as an expression
		String name = (String) exp.getValue(tesla);
		System.out.println("name: " + name);

		Expression exp2 = parser.parseExpression("name == 'Nikola Tesla'");
		boolean result = exp2.getValue(tesla, Boolean.class);
		System.out.println("result: " + result);
	}
}
