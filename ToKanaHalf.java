import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ToKanaHalf{

  private static final char V_CO_MARK     = (char) 65438;
  private static final char HALF_SVS_MARK = (char) 65439;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();

		System.out.println(toKanaHalf(str));
	}
 
/**
* 全角カタカナを半角カタカナにして返す
* @param　String : str
* @return　String
* @example ホンジツハセイテンナリ => ﾎﾝｼﾞﾂﾊｾｲﾃﾝﾅﾘ
*/
	public static String toKanaHalf(String str) {

		StringBuilder strBuil = new StringBuilder();

		int la = str.length();
		char[] charArr = str.toCharArray();
		for (int i = 0; i < la; i++) {
			int charNum = (int) charArr[i];
			// ァ行
			if (charNum >= 12449 && charNum <= 12457 && charNum % 2 == 1) {
				strBuil.append(clacByCode1(charNum, 12449, 52934));
			// ガ行
			} else if (charNum >= 12460 && charNum <= 12468 && charNum % 2 == 0) {
				strBuil.append(clacByCode1(charNum, 12460, 52938));
				strBuil.append((char) V_CO_MARK);
			// ザ行
			} else if (charNum >= 12470 && charNum <= 12478 && charNum % 2 == 0) {
				strBuil.append(clacByCode1(charNum, 12470, 52933));
				strBuil.append((char) V_CO_MARK);
			// ダヂ
			} else if (charNum >= 12480 && charNum <= 12482 && charNum % 2 == 0) {
				strBuil.append(clacByCode1(charNum, 12480, 52928));
				strBuil.append((char) V_CO_MARK);
			// ヅデド
			} else if (charNum >= 12485 && charNum <= 12489 && charNum % 2 == 1) {
				strBuil.append(clacByCode1(charNum, 12485, 52925));
				strBuil.append((char) V_CO_MARK);
			// ッ
			} else if (charNum == 12483) {
				int y = charNum + 52908;
				strBuil.append((char) y);
			// ナ行
			} else if (charNum >= 12490 && charNum <= 12494) {
				int y = charNum + 52923;
				strBuil.append((char) y);
				// ハ行
			} else if (charNum >= 12495 && charNum <= 12509) {
				// ハ行
				if (charNum % 3 == 0) {
					strBuil.append(clacByCode2(charNum, 12495, 52923));
				// バ行
				} else if (charNum % 3 == 1) {
					strBuil.append(clacByCode2(charNum, 12496, 52922));
					strBuil.append((char) V_CO_MARK);
				// パ行
				} else if (charNum % 3 == 2) {
					strBuil.append(clacByCode2(charNum, 12497, 52921));
					strBuil.append((char) HALF_SVS_MARK);
				}
			// マ行
			} else if (charNum >= 12510 && charNum <= 12514) {
				strBuil.append(clacByCode3(charNum, 52913));
			// ヤ行
			} else if (charNum >= 12515 && charNum <= 12520) {
				if (charNum % 2 == 0) {
					strBuil.append(clacByCode1(charNum, 12516, 52912));
				// ャ行
				} else if (charNum % 2 == 1) {
					strBuil.append(clacByCode1(charNum, 12515, 52873));
				}
			// ラ行
			} else if (charNum >= 12521 && charNum <= 12525) {
				strBuil.append(clacByCode3(charNum, 52910));
			// ヲン
			} else if (charNum >= 12530 && charNum <= 12531) {
				int x = (charNum - 12530) * 54;
				int y = charNum + 52852 + x;
				strBuil.append((char) y);
			// ワ
			} else if (charNum == 12527) {
				strBuil.append(clacByCode3(charNum, 52909));
			// ー
			} else if (charNum == 12540) {
				strBuil.append(clacByCode3(charNum, 52852));
			// ヴ
			} else if (charNum == 12532) {
				strBuil.append(clacByCode3(charNum, 52863));
				strBuil.append((char) V_CO_MARK);
			// （）＊＋，ー．／０１２３４５６７８９
			} else if (charNum >= 65288 && charNum <= 65305) {
				int y = charNum - 65248;
				strBuil.append((char) y);
			// 全般
			} else if (charNum >= 12450 && charNum <= 12488) {
				strBuil.append(clacByCode1(charNum, 12450, 52943));
			}
		}

		return strBuil.toString();

	}
	private static char clacByCode1(int charNum, int num1, int num2) {
		int x = (charNum - num1) / 2;		
		return (char) (charNum + num2 - x);
	}
	
	private static char clacByCode2(int charNum, int num1, int num2) {
		int x = (charNum - num1) * 2 / 3;		
		return (char) (charNum + num2 - x);
	}
	
	private static char clacByCode3(int charNum, int num1) {
		return (char) (charNum + num1);
	}

}
