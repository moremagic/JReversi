package Reversi.PCPlayer;


/**
* リプレイクラス
*
* 機械学習データ作成用の棋譜から手順をなぞるクラスです。
* 手順を覚えておきデータをまとめる機能を持ちます
**/

import Reversi.*;
import Reversi.learning.*;
import java.util.*;

public class RePlayPlayerImpl implements IBordList{
	private byte m_stone = Bord._NON_STONE_;
	private String m_sKifu = "";
	
	//ボードデータ
	private ArrayList<byte[][]> m_bordList = new ArrayList<byte[][]>();
	
	public RePlayPlayerImpl(byte stone, String sKifu){
		m_stone = stone;
		m_sKifu = sKifu;
	}
	
	public String getName(){
		String name = "?";
		if(m_stone == Bord._WHITE_STONE_){
			name = "●";
		}else if(m_stone == Bord._BLACK_STONE_){
			name = "○";
		}else{
			name = "??";
		}
		
		return "再生プレーヤー[" + m_stone + "]";
	}
	
	/**
	* 手順を決定します。
	**/
	public int[] calc(byte stone, byte[][] bordData){
		int idx = gameProgress(bordData) * 2;
		String s = m_sKifu.substring(idx, idx+2);
		
		m_bordList.add(bordData);
		return Bord.cnvKifu(s);
	}
	
	/**
	* ゲーム進行度を評価します
	**/
	public int gameProgress(byte[][] bordData){
		int cnt = -4;//初期状態で4マス使用しているため
		
		for(int i = 0 ; i < bordData.length ; i++){
			for(int j = 0 ; j < bordData[i].length ; j++){
				if(bordData[i][j] != Bord._NON_STONE_){
					cnt++;
				}
			}
		}
		
		return cnt;
	}
	
	
	/**
	* ボードデータの返却
	**/
	public List<byte[][]> getBordList(){
		return m_bordList;
	}
	
}