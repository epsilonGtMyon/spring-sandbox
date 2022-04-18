package app.section04.sandbox0402;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

public class Sandbox35Main {

	public static void main(String[] args) {
		ex03();
	}

	//--------------------------------

	private static class Simple {
		public List<Boolean> booleanList = new ArrayList<Boolean>();
	}

	//EvaluationContext
	private static void ex01() {

		Simple simple = new Simple();
		simple.booleanList.add(true);

		final ExpressionParser parser = new SpelExpressionParser();
		// SimpleEvaluationContext は機能が限定されている。
		// 自分でビルダーから 少しだけ選べる
		SimpleEvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();

		parser.parseExpression("booleanList[0]").setValue(context, simple, "false");

		Boolean b = simple.booleanList.get(0);
		System.out.println(b);

	}

	//--------------------------------

	private static class Demo {
		public List<String> list;
	}

	private static void ex02() {
		// 指定したインデックスがnullの場合とかの挙動を調整できる。
		SpelParserConfiguration config = new SpelParserConfiguration(true, true);

		ExpressionParser parser = new SpelExpressionParser(config);
		Expression expression = parser.parseExpression("list[3]");

		Demo demo = new Demo();

		Object o = expression.getValue(demo);
		System.out.println(o);
		System.out.println(Objects.equals(o, ""));
	}

	//--------------------------------
	private static class MyMessage {
		public String payload;
	}

	private static void ex03() {
		// 式の評価はデフォルトだと都度実行される
		// 通常はそれで問題ないが、パフォーマンスが必要な場面ではふさわしくない場合がある。
		// その時はコンパイルすることができる。
		// SpelCompilerModeがあって
		// OFF:デフォルト
		// IMMEDIATE:コンパイルする、 型の変更とかが原因で失敗する場合は例外が飛ぶ
		// MIXED：時間の経過とともに都度実行と、コンパイルが切り替わっていく
		// コンパイルモードの時に例外が起きた場合は 都度実行の方に切り替わる
		// 一見よさそうには見えるが上記の挙動によって、再実行されると困るような場合には適切ではなくなるのでそこは注意

		// 第2引数のクラスローダーがnullの場合は、使用する場面でClassUtils.getDefaultClassLoaderが使われる
		SpelParserConfiguration config = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE,
				Sandbox35Main.class.getClassLoader());

		SpelExpressionParser parser = new SpelExpressionParser(config);
		Expression expr = parser.parseExpression("payload");

		MyMessage message = new MyMessage();
		message.payload = "aaa";

		Object payload = expr.getValue(message);
		System.out.println(payload);

		// ここで設定する方法以外にも
		// spring.expression.compiler.modeでも設定できる。
	}

}
