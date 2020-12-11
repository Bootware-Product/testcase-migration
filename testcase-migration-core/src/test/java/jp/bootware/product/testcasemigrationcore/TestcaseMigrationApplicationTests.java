package jp.bootware.product.testcasemigrationcore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestcaseMigrationApplicationTests {
	
	/**
	 * @casename テストケース１
	 * @target testCase1
	 * @condition ・テーブルにレコードが存在する
	 * ・入力データが正しい
	 * @prediction ・正常終了する
	 */
	@Test
	public void testCase1() {
		// Your test code
	}

	/**
	 * @casename テストケース２
	 * @target testCase2
	 * @condition ・テーブルにレコードが存在しない
	 * ・入力データが正しい
	 * @prediction ・例外が発生し異常終了する
	 * ・例外コード：０００１が出力される
	 */
	@Test
	public void testCase2() {
		// Your test code
	}
}
