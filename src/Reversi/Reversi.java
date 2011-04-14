package Reversi;

import java.io.*;
import java.util.*;
import Reversi.util.*;
import Reversi.PCPlayer.*;
import Reversi.learning.*;
import Reversi.PCPlayer.Analyser.*;

/**
* Reversi
* ・ゲーム情報の管理クラス
* ・すべてのゲーム情報をこのクラスから取得できる
**/

public class Reversi{
	public static final String _KIFU_PATH_ = "kifu/kifu.log";
	
	private int m_iCnt = 0;					//ゲームカウント
	private IPlayer m_WhitePlayer = null;	//白プレイヤー
	private IPlayer m_BlackPlayer = null;	//黒プレイヤー
	private Bord m_Bord = null;				//ゲーム盤面
	private String m_sLog = "";				//棋譜文字列
	
	/**
	* コンストラクタ
	**/
	public Reversi(IPlayer bp, IPlayer wp){
		m_iCnt = 0;					//ゲームカウント
		m_BlackPlayer = bp;			//黒プレイヤー
		m_WhitePlayer = wp;			//白プレイヤー
		m_Bord = new Bord();		//ゲーム盤面初期化
		m_sLog = "";				//棋譜の初期化
	}
	
	/**
	* main!!
	**/
	public static void main(String[] args){
		if(args.length == 0){
			for(int i = 0 ; i < 1 ; i++){
				Reversi reversi = new Reversi(new PlayerA_Impl(), new HumanPlayerImpl());
				reversi.startGame(true);
			}
		}else{
			if(args.length >= 2 && args[0].equals("-lerning")){
				for(int i = 0 ; i < Integer.parseInt(args[1]) ; i++){
					Reversi reversi = new Reversi(new EstRandPlayerImpl(), new EstRandPlayerImpl());
					reversi.startGame(true);
				}
			}else if(args.length >= 2 && args[0].equals("-study")){
				//過学習を防ぐためにユニークな棋譜のみを学習の対象とします。
				try{
					String sKifuPath = FileSort.uniq(new File(args[1])).getPath();
					Q_learning.replayGame(sKifuPath, args[2]);
					
					System.out.println(args[2] + " 書き込み完了");
					
					File f = FileSort.mageDB(new File(args[2]));
					FileSort.copy(f, new File(args[2] + ".uniqdb"));
				}catch(IOException err){
					err.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	* ゲームの実行メソッド
	* 勝敗を返却します
	**/
	public byte startGame(){
		return startGame(false);
	}
	
	/**
	* ゲームの実行メソッド
	* 勝敗を返却します
	*
	* @param output 棋譜を出力する場合、画面表示を行う場合 True
	**/
	public byte startGame(boolean output){
		while(true){
			byte stone = Bord._NON_STONE_;
			String sStone = "[NULL]";
			IPlayer player = null;
			
			if(m_iCnt%2 == 0){
				stone = Bord._BLACK_STONE_;
				player = m_BlackPlayer;
				sStone = "○";
			}else if(m_iCnt%2 == 1){
				stone = Bord._WHITE_STONE_;
				player = m_WhitePlayer;
				sStone = "●";
			}
			
			
			showOutput(sStone + "[" + player.getName() +  "]のターン。\n", output);
			if(!m_Bord.isPass(stone)){
				int[] data = player.calc(stone, m_Bord.getData());
				m_Bord.setStone(data[0], data[1], stone);
				m_sLog += Bord.cnvKifu(data[0], data[1]);
				
				showOutput(">> [" + Bord.cnvKifu(data[0], data[1]) + "]\n", output);
				showOutput(m_Bord.toString() + "\n", output);
			}else{
				showOutput("Pass!!\n", output);
			}
			
			//置く場所がなくなった場合、ゲームオーバー
			if(m_Bord.isPass(Bord._WHITE_STONE_) && m_Bord.isPass(Bord._BLACK_STONE_)){
				break;
			}
			m_iCnt++;
		}
		
		//棋譜出力
		if(output){
			try{
				outLogger(m_sLog);
			}catch(IOException err){
				err.printStackTrace();
			}
		}
		
		byte win_stone = showResult(m_Bord); //勝利者
		if(win_stone == Bord._WHITE_STONE_){
			showOutput("※白の勝ちです。("+ m_iCnt + "回)\n", output);
		}else if(win_stone == Bord._BLACK_STONE_){
			showOutput("※黒の勝ちです。("+ m_iCnt + "回)\n", output);
		}else{
			showOutput("※引き分けです。("+ m_iCnt + "回)\n", output);
		}
		return win_stone;
	}
	
	/**
	* 結果照会
	* 一番多い石を優勝とし、返却します
	* ※引き分けの場合、_NON_STONE_を返却します。
	**/
	public static byte showResult(Bord gameBord){
		int cnt_b = 0;
		int cnt_w = 0;
		
		byte[][] data = gameBord.getData();
		for(int i = 0 ; i < data.length ; i++){
			for(int j = 0 ; j < data[i].length ; j++){
				if(data[i][j] == Bord._WHITE_STONE_){
					cnt_w++;
				}else if(data[i][j] == Bord._BLACK_STONE_){
					cnt_b++;
				}
			}
		}
		
		/**
		System.out.println("■結果");
		System.out.println("\t○　：　" + cnt_b + "個");
		System.out.println("\t●　：　" + cnt_w + "個");
		**/
		
		if(cnt_w > cnt_b){
			return Bord._WHITE_STONE_;
		}else if(cnt_b > cnt_w){
			return Bord._BLACK_STONE_;
		}else{
			return Bord._NON_STONE_;
		}
	}
	
	
	/**
	* 画面出力のためのユーティリティ
	**/
	public static void showOutput(String msg, boolean b){
		if(b){
			System.out.print(msg);
		}
	}
	
	/**
	* 棋譜出力のためのユーティリティ
	**/
	public static void outLogger(String sKifu) throws IOException{
		BufferedWriter bw = null;
		try{
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(_KIFU_PATH_), true)));
			bw.write(sKifu + "\n");
		}finally{
			if(bw != null)bw.close();
		}
	}
	
	
}