import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Trade {
	// Be動詞の平叙文を疑問文に変えるプログラム
	// 固有名詞のときは主語は大文字のまま。(修正)

	final static String CST_SPACE=" ";

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		try {

			int be_verb_cnt =0;
			String[] word = new String[50]; // 単語
			String[] vl = {"is","am","are","was","were"}; // be動詞
			String s; // 文章
			//			String verblist = "is,am,are,was,were"; // Be動詞
			//			vl = verblist.split(",");

			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);

			// プログラム本体//
			System.out.println("【英文を疑問文に変換します】");
			System.out.println("英文を入力");
			s = br.readLine(); // 文章を入力

			// String型配列に格納
			word= s.split(CST_SPACE);	// String型をword単位で、別の配列に格納

			Search_BeVerb(word, vl, be_verb_cnt);

			int wordcount;
			wordcount = word.length; // wordcount="単語数"

			System.out.println(be_verb_cnt);

			// 先頭の主語と動詞の交換
			WordSwap(word, be_verb_cnt);

			// 交換後の文頭文字を大文字に変換
			word[0] = InitialUpperCase(word[0]);

			// 交換後の主語を大文字から小文字に変換("I"ならはじく)
			if ((word[1].length() > 1)) { // 単語が一文字以上の時
				//(I、A lot of をはじく)
				word[1] = InitialLowerCase(word[1]);
			} else if (!(word[1].equals("I"))) { // A lot ofならOK,Iははじく
				word[1] = word[1].toLowerCase();
			}

			// word[]のi番目の要素にある'.'を'?'に変換する。
			StringBuilder sb = new StringBuilder(word[wordcount - 1]);
			for (int i = 0; i < word[wordcount - 1].length(); i++) {
				// word[wordcount]は文末の単語。
				if (word[wordcount - 1].charAt(i) == '.') {
					sb.setCharAt(i, '?');
					word[wordcount - 1] = sb.toString();
					break;
				}
			}
			//			for (be_verb_cnt = 0; be_verb_cnt < wordcount; be_verb_cnt++) { // 完成した疑問文を出力
			//				System.out.println(" \t ");
			//				System.out.print(word[be_verb_cnt] + CST_SPACE);
			//				word[be_verb_cnt] = null; // 配列の初期化
			//			}
			System.out.print(String.join(CST_SPACE,word));

			br.close();

		} catch (IOException e) {
			System.out.println(e);
		} catch (NumberFormatException e) {
			System.out.println(e);
		}
	}

	public static String InitialUpperCase(String str) {
		// 交換後の文頭文字を大文字に変換
		str = str.substring(0, 1).toUpperCase()
				+ str.substring(1);
		return str;
	}

	public static String InitialLowerCase(String str) {
		// 交換後の文頭文字を大文字に変換
		str = str.substring(0, 1).toLowerCase()
				+ str.substring(1);
		return str;
	}

	public static void Search_BeVerb(String word[],String vl[],int be_verb_cnt) {
		// 文字列検索 //
		// Be動詞が何番目にあるのか
		int wordcount, wordcountj;
		wordcount = word.length; // wordcount="単語数"
		wordcountj = vl.length; // wordcount="単語数"
		System.out.print("単語数" + wordcount + " ");
		be_verb_cnt = 0;

		// 読み込んだ文をループ
		for(String w : word) {
			int FLUG = 0;
			for (String v : vl) {
				if (w.equals(v)) {
					FLUG = 1;
					break;
				}
				be_verb_cnt++;
			}
			if (FLUG == 1)
				break; // 文字列が全て一致したら比較終了
		}

		//		for (int i = 0; i < wordcount - 1; i++) { // 読み込んだ文の文字数だけループ、添字iの最大値は=wordcount
		//			int FLUG = 0;
		//			for (int j = 0; j < wordcountj - 1; j++) {
		//				if (word[i].equals(vl[j])) {
		//					be_verb_cnt = i;
		//					FLUG = 1;
		//					break;
		//				}
		//			}
		//			if (FLUG == 1)
		//				break; // 文字列が全て一致したら比較終了
		//		}
	}

	public static void WordSwap(String word[],int be_verb_cnt) {
		// 先頭の主語と動詞の交換
		String swap;
		swap = word[be_verb_cnt];
		while(be_verb_cnt>0){
			word[be_verb_cnt] = word[be_verb_cnt-1];	// 配列の要素をひとつずつ右にずらす。
			be_verb_cnt--;					// iが左端に到達したら(i=0)終了。
		}
		word[0] = swap;
	}
}