package Reversi.PCPlayer;


/**
* プレーヤークラス
* 最適な石を常に取り続けるプレーヤ
**/

import Reversi.*;
import Reversi.PCPlayer.Analyser.*;
import java.util.*;

public class PlayerB_Impl implements IPlayer, IAnalyser{
	public static Random _random = new Random();
	
	public String getName(){
		return "最適化プレーヤー(石の位置により重みをつける＋ツリーの全ノードを合計して判定に使用)";
	}
	
	/**
	* 盤面データから現在のスコアを算出します
	**/
	public int getScore(byte stone, byte[][] bordData){
		int score = 0;
		
		//石の差分
		//score += AnalyserUtil.calcScoreF(stone, bordData);
		
		///*
		//着手可能数によるスコア加算
		int score_b = AnalyserUtil.calcScoreB(stone, bordData);
		if(score_b != 0){
			score += (score_b * 10);
		}else{
			score -= 100;
		}
		//*/
		
		return score;
	}
	
	/**
	* 現在の手から現在のスコアを算出します
	**/
	private static int posCalc(PhaseNode node){
		int pos_score = 0;
		if( (node.getX() == 0 && node.getY() == 0) ||
			(node.getX() == 0 && node.getY() == Bord.y_size-1) ||
			(node.getX() == Bord.x_size-1 && node.getY() == 0) ||
			(node.getX() == Bord.x_size-1 && node.getY() == Bord.y_size-1) 
			){
			//角石の場合+100
			pos_score = 100;
		}else if(
			//左上
			(node.getX()==0 && node.getY()==1) ||
			(node.getX()==1 && node.getY()==1) ||
			(node.getX()==1 && node.getY()==0) ||
			
			//右上
			(node.getX()==Bord.x_size-2 && node.getY()==0) ||
			(node.getX()==Bord.x_size-2 && node.getY()==1) ||
			(node.getX()==Bord.x_size-1 && node.getY()==1) ||
			
			//左下
			(node.getX()==0 && node.getY()==Bord.y_size-2) ||
			(node.getX()==1 && node.getY()==Bord.y_size-2) ||
			(node.getX()==1 && node.getY()==Bord.y_size-1) ||
			
			//右下
			(node.getX()==Bord.x_size-2 && node.getY()==Bord.y_size-2) ||
			(node.getX()==Bord.x_size-2 && node.getY()==Bord.y_size-1) ||
			(node.getX()==Bord.x_size-1 && node.getY()==Bord.y_size-2)
		){
			//角石リーチの場合－１００
			//pos_score = -100;
		}
		
		return pos_score;
	}
	
	/**
	* ツリーノードからのスコア算出用再帰関数
	* ・自石の場合＋スコア
	* ・他石の場合－スコア
	**/
	public static int getResult(PhaseNode node, byte stone){
		//ツリーの全ノードを合計して判定に使用する
		int pos_score = posCalc(node);
		int retScore = 0;
		if(stone == node.getStone()){
			retScore += (node.getScore() + pos_score);
		}else{
			retScore -= (node.getScore() + pos_score);
		}
		
		for(PhaseNode buf : node.getNodes()){
			retScore += getResult(buf, stone);
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