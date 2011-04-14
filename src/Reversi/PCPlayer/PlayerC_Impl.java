package Reversi.PCPlayer;


/**
* プレーヤークラス
* 最適な石を常に取り続けるプレーヤ
**/

import Reversi.*;
import Reversi.PCPlayer.Analyser.*;
import java.util.*;

public class PlayerC_Impl implements IPlayer, IAnalyser{
	public static Random _random = new Random();
	
	public String getName(){
		return "最適化プレーヤー(リーフノードのみを合計し判定に使用)";
	}
	
	/**
	* 盤面データから現在のスコアを算出します
	**/
	public int getScore(byte stone, byte[][] bordData){
		int score = 0;
		//単純な石数加算
		//score = AnalyserUtil.calcScoreA(stone, bordData);
		
		//石の差分
		score += AnalyserUtil.calcScoreF(stone, bordData);
		
		//角対角マスだった場合のスコア減算
		//score -= (AnalyserUtil.calcScoreE(stone, bordData) * 200);
		
		//角石だった場合のスコア加算
		score += AnalyserUtil.calcScoreC(stone, bordData)*100;
		
		
		/*
		//着手可能数によるスコア加算
		int score_b = AnalyserUtil.calcScoreB(stone, bordData);
		if(score_b != 0){
			score += (score_b * 10);
		}else{
			score -= 100;
		}
		*/
		
		return score;
	}
	

	/**
	* ツリーノードからのスコア算出用再帰関数
	* ・自石の場合＋スコア
	* ・他石の場合－スコア
	**/
	public static int getResult(PhaseNode node, byte stone){
		//リーフノードのみを合計し判定に使用するバージョン
		int retScore = 0;
		if(node.getNodes().length != 0){
			for(PhaseNode buf : node.getNodes()){
				retScore += getResult(buf, stone);
			}
		}else{
			int s = 0;
			if(stone == node.getStone()){
				s += node.getScore();
			}else{
				s -= node.getScore();
			}
			return s;
		}
		return retScore;
	}
	
	/**
	* 手順を決定します。
	**/
	public int[] calc(byte stone, byte[][] bordData){
		
		PhaseNode n = PhaseNode.createNode(stone, bordData, this);
		PhaseNode result = new PhaseNode(-1, -1, Bord._NON_STONE_, Integer.MIN_VALUE);
		
		for(PhaseNode buf : n.getNodes()){
			if( getResult(result, stone) < getResult(buf, stone) ){
				result = buf;
			}
		}
		
		System.out.println("[score:" + getResult(result, stone) + "]");
		return new int[]{result.getX(), result.getY()};
	}
}