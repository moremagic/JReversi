package Reversi;

/**
* 盤面を抽象化するクラス
*
* 現時点の盤面情報の保持
* 解析のため石を打ったときの結果
* その場所に石が打てるか
**/
public class Bord{
	/** 盤面のサイズ **/
	public static final int x_size = 8;//Xサイズ
	public static final int y_size = 8;//Yサイズ
	
	/** 持ち駒定数 **/
	public static final byte _NON_STONE_   = 0; //石がない
	public static final byte _BLACK_STONE_ = 1; //黒
	public static final byte _WHITE_STONE_ = 2; //白
	public static final byte _DEBUG_STONE_ = 3; //デバックストーン
	
	/** 現在の盤面データ **/
	private byte[][] m_nowData = new byte[x_size][y_size];
	
	/**
	* 通常のコンストラクタ
	**/
	public Bord(){
		///*
		m_nowData[3][3] = _WHITE_STONE_;
		m_nowData[4][3] = _BLACK_STONE_;
		m_nowData[3][4] = _BLACK_STONE_;
		m_nowData[4][4] = _WHITE_STONE_;
		//*/

		/*
		//6＊6スタート
		m_nowData[2][2] = _WHITE_STONE_;
		m_nowData[3][2] = _BLACK_STONE_;
		m_nowData[2][3] = _BLACK_STONE_;
		m_nowData[3][3] = _WHITE_STONE_;
		*/
		
		/*
		//4＊4スタート
		m_nowData[1][1] = _WHITE_STONE_;
		m_nowData[1][2] = _BLACK_STONE_;
		m_nowData[2][1] = _BLACK_STONE_;
		m_nowData[2][2] = _WHITE_STONE_;
		*/
	}
	
	/**
	* 盤面の状態を指定して初期化
	*
	* ※DEBUG、TEST用として作成
	* ※二次元配列をディープコピーする
	*/
	public Bord(byte[][] data){
		m_nowData = deepCopy(data);
	}
	
	/**
	* 盤面の状態を指定
	* ※二次元配列をディープコピーする
	*/
	private void setData(byte[][] data){
		m_nowData = deepCopy(data);
	}
	
	/**
	* 現在のデータを二次元配列の形で返却する
	* ※ディープコピーしなくてはならない
	**/
	public byte[][] getData(){
		return deepCopy(m_nowData);
	}
	
	/**
	* 指定した場所に石を置くメソッド
	* 石を置ける場合はデータモデルを更新しTrueを返却する
	**/
	public boolean setStone(int x, int y, byte stone){
		byte[][] oldData = this.getData();
		this.setData(calcStone(x, y, stone, oldData));
		
		return !getData().equals(oldData);
	}
	
	/**
	* 指定した場所に石を置いた場合のデータモデル作成
	**/
	public static byte[][] calcStone(int x, int y, byte stone, byte[][] bordData){
		boolean b = false;//石が置けるフラグ
		byte[][] retData = deepCopy(bordData);
		
		//その場所に石がすでに存在している場合
		if(bordData[x][y] != _NON_STONE_){
			return retData;
		}
		
		//X軸方向へのひっくり返し
		for(int i = x+1 ; i < x_size ; i++){
			if(retData[i][y] != _NON_STONE_){
				if(retData[i][y] == stone){
					for(int j = i ; j >= (x+1) ; j--){
						retData[j][y] = stone;
						b = true;
					}
					break;
				}
			}else{
				break;
			}
		}
		
		
		for(int i = x-1 ; i >= 0 ; i--){
			if(retData[i][y] != _NON_STONE_){
				if(retData[i][y] == stone){
					for(int j = i ; j <= (x-1) ; j++){
						retData[j][y] = stone;
						b = true;
					}
					break;
				}
			}else{
				break;
			}
		}
		
		//Y軸方向へのひっくり返し
		for(int i = y+1 ; i < y_size ; i++){
			if(retData[x][i] != _NON_STONE_){
				if(retData[x][i] == stone){
					for(int j = i ; j >= (y+1) ; j--){
						retData[x][j] = stone;
						b = true;
					}
					break;
				}
			}else{
				break;
			}
		}
		for(int i = y-1 ; i >= 0 ; i--){
			if(retData[x][i] != _NON_STONE_){
				if(retData[x][i] == stone){
					for(int j = i ; j <= (y-1) ; j++){
						retData[x][j] = stone;
						b = true;
					}
					break;
				}
			}else{
				break;
			}
		}
		
		//斜め軸方向へのひっくり返し
		//右下
		for(int i = x+1, j = y+1 ; i < x_size && j < y_size ; i++,j++){
			if(retData[i][j] != _NON_STONE_){
				if(retData[i][j] == stone){
					for(int k = i, l = j ; k >= (x+1) && l >= (y+1) ; k--,l--){
						retData[k][l] = stone;
						b = true;
					}
					break;
				}
			}else{
				break;
			}
		}
		
		//右上
		for(int i = x+1, j = y-1 ; i < x_size && j >= 0 ; i++,j--){
			if(retData[i][j] != _NON_STONE_){
				if(retData[i][j] == stone){
					for(int k = i, l = j ; k >= (x+1) && l <= (y-1) ; k--,l++){
						retData[k][l] = stone;
						b = true;
					}
					break;
				}
			}else{
				break;
			}
		}
		
		//左上
		for(int i = x-1, j = y-1 ; i >= 0 && j >= 0 ; i--,j--){
			if(retData[i][j] != _NON_STONE_){
				if(retData[i][j] == stone){
					for(int k = i, l = j ; k <= (x-1) && l <= (y-1) ; k++,l++){
						retData[k][l] = stone;
						b = true;
					}
					break;
				}
			}else{
				break;
			}
		}
		
		//左下
		for(int i = x-1, j = y+1 ; i >= 0 && j < y_size ; i--,j++){
			if(retData[i][j] != _NON_STONE_){
				if(retData[i][j] == stone){
					for(int k = i, l = j ; k <= (x-1) && l >= (y+1) ; k++,l--){
						retData[k][l] = stone;
						b = true;
					}
					break;
				}
			}else{
				break;
			}
		}
		
		if(b)retData[x][y] = stone;
		return retData;
	}
	
	/**
	* 石を置く場所があるかどうかを検索する
	* 石を置けない場合はTrueを返却。置ける場合はFalseを返却する
	**/
	public boolean isPass(byte stone){
		byte[][] data = this.getData();
		for(int i = 0 ; i < data.length ; i++){
			for(int j = 0 ; j < data[i].length ; j++){
				if( countStone(i, j, stone, data) > 0){
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	* 指定した場所に石を置いた場合の取得石数をカウントするメソッド
	* ※置いた石はカウントしない
	* ※データモデルの更新は行わない
	**/
	public static int countStone(int x, int y, byte stone, byte[][] oldData){
		byte[][] newData = calcStone(x, y, stone, oldData);
		
		//異なるマス数をカウント
		int cnt = 0;
		for(int i = 0 ; i < newData.length ; i++){
			for(int j = 0 ; j < newData[i].length ; j++){
				if(newData[i][j] != oldData[i][j])
					cnt++;
			}
		}
		
		return cnt-1;
	}
	
	/**
	* 二次元配列のディープコピーユーティリティ
	**/
	public static byte[][] deepCopy(byte[][] data){
		byte[][] ret = new byte[data.length][data[0].length];
		for(int i = 0 ; i < data.length ; i++){
			for(int j = 0 ; j < data[i].length ; j++){
				ret[i][j] = data[i][j];
			}
		}
		
		return ret;
	}
	
	/**
	* 棋譜フォーマット変換
	* X軸座標＋Y軸座標 から オセロ棋譜形式に変換するユーティリティ
	**/
	public static String cnvKifu(int x, int y){
		return ((char)(x+97) + Integer.toString(y+1));
	}
	
	/**
	* 棋譜フォーマット変換
	* オセロ棋譜形式 から X軸座標＋Y軸座標 に変換するユーティリティ
	*
	* ※[a-z][0-9] 範囲内で変換可能です。
	* ※変換不可能な場合nullを返却します。
	**/
	public static int[] cnvKifu(String s){
		int[] ret = null;
		try{
			ret = new int[]{s.charAt(0)-97 , new Integer(s.charAt(1)-'0')-1};
		}catch(Exception err){}
		
		return ret;
	}
	
	/**
	* 盤面表示
	**/
	public String toString(){
		String s = " ";
		//X軸インデクス：a～h
		for(int x = 0 ; x < x_size ; x++){
			s += " " + (char)(x+97) + " ";
		}
		s += "\n";
		
		for(int y = 0 ; y < y_size ; y++){
			s += " ";
			for(int x = 0 ; x < x_size ; x++){
				s += "+--";
			}
			s += "+\n";
			s += y+1;//Y軸インデクス：１～８
			for(int x = 0 ; x < x_size ; x++){
				if(m_nowData[x][y] == _BLACK_STONE_){
					s += "|○";
				}else if(m_nowData[x][y] == _WHITE_STONE_){
					s += "|●";
				}else if(m_nowData[x][y] == _DEBUG_STONE_){
					s += "|☆";
				}else{
					s += "|  ";
				}
			}
			s += "|\n";
		}
		
		s += " ";
		for(int x = 0 ; x < x_size ; x++){
			s += "+--";
		}
		
		s += "+\n";
		return s;
	}
	
	
	
}