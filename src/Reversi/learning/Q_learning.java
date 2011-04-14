package Reversi.learning;

/**
* Qラーニングクラスです
* 以下２つの機能を持ちます。
*
* １．棋譜からQ値を計算しDBに保存します
* ２．盤面からQ値を返却します
**/

import java.io.*;
import java.util.*;
import Reversi.*;
import Reversi.PCPlayer.*;

public class Q_learning{
	
	/**
	* 棋譜ファイルからゲームをリプレイし、Q値を計算する
	* 棋譜からQ値を格納したDBファイルを作成します。
	**/
	public static void replayGame(String sKifuPath, String qdb_paht){
		try{
			int cnt = 0;
			
			
			BufferedReader r = new BufferedReader(new FileReader(new File(sKifuPath)));
			String sLine = "";
			while((sLine = r.readLine()) != null){
				RePlayPlayerImpl black_p = new RePlayPlayerImpl(Bord._BLACK_STONE_, sLine);
				RePlayPlayerImpl white_p = new RePlayPlayerImpl(Bord._WHITE_STONE_, sLine);
				
				Reversi reversi = new Reversi(black_p, white_p);
				try{
					byte win = reversi.startGame();
					
					
					/**
					//勝ったらすべての盤面は１、逆は-１点とする
					int point_black = (win == Bord._BLACK_STONE_)?1:-1;
					int point_white = (win == Bord._WHITE_STONE_)?1:-1;
					output( calcQ(black_p, point_black), qdb_paht);
					output( calcQ(white_p, point_white), qdb_paht);
					**/
					
					
					//勝者のみ出力。手数を点数とする
					if(win == Bord._BLACK_STONE_){
						output( calcQwin(black_p), qdb_paht);
						output( calcQlose(white_p), qdb_paht);
					}else{
						output( calcQwin(white_p), qdb_paht);
						output( calcQlose(black_p), qdb_paht);
					}
					
					
				}catch(Exception err){
					System.err.println("err:" + sLine);
					err.printStackTrace();
				}
				
				//ガイダンス
				if( cnt++ % 10 == 0){
					System.out.print(".");
				}
			}
		}catch(Exception err){
			err.printStackTrace();
		}
		
		System.out.println("ok.");
	}
	
	/**
	* DB出力のためのユーティリティ
	**/
	public int getQ(byte[][] bordData){
		return 0;
	}

	/**
	* ボードリスト出力
	* 一定の点数をボードにつける
	**/
	private static String calcQ(RePlayPlayerImpl p, int qPlus){
		StringBuilder ret = new StringBuilder();
		
		List<byte[][]> bl = p.getBordList();
		for(byte[][] bord_buf:bl){
			for(int i = 0 ; i < bord_buf.length ; i++){
				for(int j = 0 ; j < bord_buf[i].length ; j++){
					ret.append(bord_buf[i][j]);
				}
			}
			ret.append("\t" + qPlus + "\n");
		}
		
		return ret.toString();
	}

	/**
	* ボードリスト出力
	* 手数を点数としたデータを出力
	**/
	private static String calcQwin(RePlayPlayerImpl p){
		StringBuilder ret = new StringBuilder();
		
		int cnt = 0;
		
		List<byte[][]> bl = p.getBordList();
		for(byte[][] bord_buf:bl){
			for(int i = 0 ; i < bord_buf.length ; i++){
				for(int j = 0 ; j < bord_buf[i].length ; j++){
					ret.append(bord_buf[i][j]);
				}
			}
			ret.append("\t" + ++cnt + "\n");
		}
		
		return ret.toString();
	}
	
	/**
	* ボードリスト出力
	* 手数を点数としたデータを出力
	**/
	private static String calcQlose(RePlayPlayerImpl p){
		StringBuilder ret = new StringBuilder();
		
		int cnt = 0;
		
		List<byte[][]> bl = p.getBordList();
		for(byte[][] bord_buf:bl){
			for(int i = 0 ; i < bord_buf.length ; i++){
				for(int j = 0 ; j < bord_buf[i].length ; j++){
					ret.append(bord_buf[i][j]);
				}
			}
			ret.append("\t-" + ++cnt + "\n");
		}
		
		return ret.toString();
	}
	
	/**
	* DB出力のためのユーティリティ
	**/
	private static void output(String sDB, String qdb_paht) throws IOException{
		BufferedWriter bw = null;
		try{
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(qdb_paht), true)));
			bw.write(sDB + "\n");
		}finally{
			if(bw != null)bw.close();
		}
	}
	
}