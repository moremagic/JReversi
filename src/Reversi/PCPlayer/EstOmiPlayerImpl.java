package Reversi.PCPlayer;


/**
* プレーヤークラス
* 進行度によって戦術を変更するプレーヤ
**/

import Reversi.*;
import java.util.*;

public class EstOmiPlayerImpl implements IPlayer{
	IPlayer mA = new EstMinPlayerImpl();
	IPlayer mB = new EstMaxPlayerImpl();
	IPlayer mC = new PlayerA_Impl();
	
	public String getName(){
		return "Omiプレーヤー";
	}
	
	/**
	* 手順を決定します。
	**/
	public int[] calc(byte stone, byte[][] bordData){
		int cnt = gameProgress(bordData);
		
		IPlayer p = null;
		if(cnt < 10){
			p = mA;
		}else if(cnt < 20){
			p = mA;
		}else if(cnt < 30){
			p = mB;
		}else if(cnt < 40){
			p = mB;
		}else if(cnt < 50){
			p = mC;
		}else{
			p = mC;
		}
		
		System.out.println("[現在の設定：" + p.getName() + "]");
		return p.calc(stone, bordData);
	}
	
	
	
	/**
	* ゲーム進行度を評価します
	**/
	public int gameProgress(byte[][] bordData){
		int cnt = 0;
		
		for(int i = 0 ; i < bordData.length ; i++){
			for(int j = 0 ; j < bordData[i].length ; j++){
				if(bordData[i][j] != Bord._NON_STONE_){
					cnt++;
				}
			}
		}
		
		return cnt;
	}
}