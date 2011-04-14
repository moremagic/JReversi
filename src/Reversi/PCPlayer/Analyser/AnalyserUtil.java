package Reversi.PCPlayer.Analyser;


/**
* 局面解析クラス
* 現在のゲームを解析するクラス
**/

import Reversi.*;
import java.util.*;

public class AnalyserUtil{
	
	/**
	* 盤面データから現在のスコアを算出します
	* スコア算出方法
	* ・持ち駒 * 1点
	**/
	public static int calcScoreA(byte stone, byte[][] bordData){
		int score = 0;
		for(int i = 0 ; i < bordData.length ; i++){
			for(int j = 0 ; j < bordData[i].length ; j++){
				if(bordData[i][j] == stone){
					score++;
				}
				
			}
		}
		return score;
	}
	
	/**
	* 盤面データから現在のスコアを算出します
	* スコア算出方法
	* ・着手可能数 * 1点
	**/
	public static int calcScoreB(byte stone, byte[][] bordData){
		int score = 0;
		for(int i = 0 ; i < bordData.length ; i++){
			for(int j = 0 ; j < bordData[i].length ; j++){
				int cnt = Bord.countStone(i, j, stone, bordData);
				if(cnt > 0){
					score++;
				}
			}
		}
		return score;
	}
	
	/**
	* 盤面データから現在のスコアを算出します
	* スコア算出方法
	* ・角の持ち駒 * 1点
	**/
	public static int calcScoreC(byte stone, byte[][] bordData){
		int score = 0;
		for(int i = 0 ; i < bordData.length ; i++){
			for(int j = 0 ; j < bordData[i].length ; j++){
				if(bordData[i][j] == stone){
					if(
						(i==0 && j==0) ||
						(i==0 && j==Bord.y_size-1) ||
						(i==Bord.x_size-1 && j==0) ||
						(i==Bord.x_size-1 && j==Bord.y_size-1)
					){
						score++;
					}
				}
				
			}
		}
		return score;
	}
	
	/**
	* 盤面データから現在のスコアを算出します
	* スコア算出方法
	* ・辺の角から連続する持ち駒
	* ※角はカウントしない
	* ※重複カウントする問題あり
	**/
	public static int calcScoreD(byte stone, byte[][] bordData){
		int score = 0;
		{//X軸方向検査
			boolean bUp = true;
			boolean bDn = true;
			for(int i = 0 ; i < bordData.length ; i++){
				//上辺右方向の検査
				if(bordData[i][0] == stone && bUp){
					if(i == 0){
						//score++;
					}else if(bordData[i-1][0] == stone){
						score++;
					}else{
						bUp = false;
					}
				}else{
					bUp = false;
				}
				//下辺右方向の検査
				if(bordData[i][Bord.y_size-1] == stone && bDn){
					if(i == 0){
						//score++;
					}else if(bordData[i-1][Bord.y_size-1] == stone){
						score++;
					}else{
						bDn = false;
					}
				}else{
					bDn = false;
				}
			}
		}
		
		{//X軸方向検査
			boolean bUp = true;
			boolean bDn = true;
			for(int i = bordData.length-1 ; i >= 0 ; i--){
				//上辺右方向の検査
				if(bordData[i][0] == stone && bUp){
					if(i == bordData.length-1){
						//score++;
					}else if(bordData[i+1][0] == stone){
						score++;
					}else{
						bUp = false;
					}
				}else{
					bUp = false;
				}
				//下辺右方向の検査
				if(bordData[i][Bord.y_size-1] == stone && bDn){
					if(i == bordData.length-1){
						//score++;
					}else if(bordData[i+1][Bord.y_size-1] == stone){
						score++;
					}else{
						bDn = false;
					}
				}else{
					bDn = false;
				}
			}
		}

		{//Y軸方向検査
			boolean bUp = true;
			boolean bDn = true;
			for(int i = 0 ; i < bordData[0].length ; i++){
				//左辺下方向の検査
				if(bordData[0][i] == stone && bUp){
					if(i == 0){
						//score++;
					}else if(bordData[0][i-1] == stone){
						score++;
					}else{
						bUp = false;
					}
				}else{
					bUp = false;
				}
				//右辺下方向の検査
				if(bordData[Bord.x_size-1][i] == stone && bDn){
					if(i == 0){
						//score++;
					}else if(bordData[Bord.x_size-1][i-1] == stone){
						score++;
					}else{
						bDn = false;
					}
				}else{
					bDn = false;
				}
			}
		}
		
		{//Y軸方向検査
			boolean bUp = true;
			boolean bDn = true;
			for(int i = bordData[0].length-1 ; i >= 0 ; i--){
				//左辺上方向の検査
				if(bordData[0][i] == stone && bUp){
					if(i == bordData.length-1){
						//score++;
					}else if(bordData[0][i+1] == stone){
						score++;
					}else{
						bUp = false;
					}
				}else{
					bUp = false;
				}
				//右辺上方向の検査
				if(bordData[Bord.x_size-1][i] == stone && bDn){
					if(i == bordData.length-1){
						//score++;
					}else if(bordData[Bord.x_size-1][i+1] == stone){
						score++;
					}else{
						bDn = false;
					}
				}else{
					bDn = false;
				}
			}
		}
		
		return score;
	}
	
	/**
	* 盤面データから現在のスコアを算出します
	* スコア算出方法
	* ・角対角の持ち駒 * 1点
	**/
	public static int calcScoreE(byte stone, byte[][] bordData){
		int score = 0;
		for(int i = 0 ; i < bordData.length ; i++){
			for(int j = 0 ; j < bordData[i].length ; j++){
				if(bordData[i][j] == stone){
					if(
						(i==1 && j==1) ||
						(i==1 && j==Bord.y_size-2) ||
						(i==Bord.x_size-2 && j==1) ||
						(i==Bord.x_size-2 && j==Bord.y_size-2)
					){
						score++;
					}
				}
				
			}
		}
		return score;
	}
	
	/**
	* 盤面データから現在のスコアを算出します
	* スコア算出方法
	* ・石の差 * 1点
	**/
	public static int calcScoreF(byte stone, byte[][] bordData){
		int score = 0;
		for(int i = 0 ; i < bordData.length ; i++){
			for(int j = 0 ; j < bordData[i].length ; j++){
				if(bordData[i][j] == stone){
					score++;
				}else if(bordData[i][j] != Bord._NON_STONE_){
					score--;
				}
			}
		}
		return score;
	}
}
