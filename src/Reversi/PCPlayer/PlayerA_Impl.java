package Reversi.PCPlayer;


/**
* プレーヤークラス
* 最適な石を常に取り続けるプレーヤ
**/

import Reversi.*;
import Reversi.PCPlayer.Analyser.*;
import java.util.*;

public class PlayerA_Impl implements IPlayer, IAnalyser{
	public static Random _random = new Random();
	
	public String getName(){
		return "最適化プレーヤー(ツリーの全ノードを合計して判定に使用)";
	}
	
	/**
	* 盤面データから現在のスコアを算出します
	**/
	public int getScore(byte stone, byte[][] bordData){
		int score = 0;
		//石数
		score += AnalyserUtil.calcScoreA(stone, bordData);
		return score;
	}
	
	/**
	* ツリーノードからのスコア算出用再帰関数
	* ・自石の場合＋スコア
	* ・他石の場合－スコア
	**/
	public static int getResult(PhaseNode node, byte stone){
		//ツリーの全ノードを合計して判定に使用する
		int retScore = 0;
		if(stone == node.getStone()){
			retScore += node.getScore();
		}else{
			retScore -= node.getScore();
		}
		
		for(PhaseNode buf : node.getNodes()){
			if(buf.getNodes().length == 0){
				retScore = getResult(buf, stone);
			}else{
				getResult(buf, stone);
			}
		}
		return retScore;
	}
	
	/**
	* 手順を決定します。
	**/
	public int[] calc(byte stone, byte[][] bordData){
		
		PhaseNode n = PhaseNode.createNode(stone, bordData, this);
		
		int score = 0;
		PhaseNode result = null;
		
		for(PhaseNode buf : n.getNodes()){
			if( result == null || score < getResult(buf, stone) ){
				result = buf;
			}
		}
		
		System.out.println("[score:" + getResult(result, stone) + "]");
		return new int[]{result.getX(), result.getY()};
	}
}