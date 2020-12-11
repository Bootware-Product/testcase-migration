# Test Case Migration

## How to use

1.Create test cases with Java.

```
public class Test {

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
```

2.Run this application

3.Output json

```
[
    {
        "columns": [
            {
                "name": "casename",
                "content": "テストケース１"
            },
            {
                "name": "target",
                "content": "testCase1"
            },
            {
                "name": "condition",
                "content": "・テーブルにレコードが存在する\r\n・入力データが正しい"
            },
            {
                "name": "prediction",
                "content": "・正常終了する"
            }
        ]
    },
    {
        "columns": [
            {
                "name": "casename",
                "content": "テストケース２"
            },
            {
                "name": "target",
                "content": "testCase2"
            },
            {
                "name": "condition",
                "content": "・テーブルにレコードが存在しない\r\n・入力データが正しい"
            },
            {
                "name": "prediction",
                "content": "・例外が発生し異常終了する\r\n・例外コード：０００１が出力される"
            }
        ]
    }
]
```
