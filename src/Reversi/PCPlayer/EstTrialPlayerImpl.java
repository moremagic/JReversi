package Reversi.PCPlayer;


/**
* プレーヤークラス
* 戦術をランダムに変更するプレーヤ
**/

import Reversi.*;
import java.util.*;

public class EstTrialPlayerImpl implements IPlayer{
	public static Random _random = new Random();
	List<IPlayer> mPList = new ArrayList<IPlayer>();
	
	public EstTrialPlayerImpl(){
		mPList.add(new EstMinPlayerImpl());
		mPList.add(new EstMaxPlayerImpl());
		mPList.add(new EstOmiPlayerImpl());
		mPList.add(new EstRandPlayerImpl());
		mPList.add(new PlayerA_Impl());
		mPList.add(new PlayerB_Impl());
		mPList.add(new PlayerC_Impl());
	}
	
	public String getName(){
		return "戦術ランダムプレーヤー";
	}
	
	/**
	* 手順を決定します。
	**/
	public int[] calc(byte stone, byte[][] bordData){
		int cnt = gameProgress(bordData);
		
		IPlayer p = null;
		int rnd = _random.nextInt(mPList.size());
		p = mPList.get(rnd);
		
		System.out.println("[現在の設定：" + p.getName() + "]" + rnd);
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