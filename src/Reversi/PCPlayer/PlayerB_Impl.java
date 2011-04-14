package Reversi.PCPlayer;


/**
* �v���[���[�N���X
* �œK�Ȑ΂���Ɏ�葱����v���[��
**/

import Reversi.*;
import Reversi.PCPlayer.Analyser.*;
import java.util.*;

public class PlayerB_Impl implements IPlayer, IAnalyser{
	public static Random _random = new Random();
	
	public String getName(){
		return "�œK���v���[���[(�΂̈ʒu�ɂ��d�݂�����{�c���[�̑S�m�[�h�����v���Ĕ���Ɏg�p)";
	}
	
	/**
	* �Ֆʃf�[�^���猻�݂̃X�R�A���Z�o���܂�
	**/
	public int getScore(byte stone, byte[][] bordData){
		int score = 0;
		
		//�΂̍���
		//score += AnalyserUtil.calcScoreF(stone, bordData);
		
		///*
		//����\���ɂ��X�R�A���Z
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
	* ���݂̎肩�猻�݂̃X�R�A���Z�o���܂�
	**/
	private static int posCalc(PhaseNode node){
		int pos_score = 0;
		if( (node.getX() == 0 && node.getY() == 0) ||
			(node.getX() == 0 && node.getY() == Bord.y_size-1) ||
			(node.getX() == Bord.x_size-1 && node.getY() == 0) ||
			(node.getX() == Bord.x_size-1 && node.getY() == Bord.y_size-1) 
			){
			//�p�΂̏ꍇ+100
			pos_score = 100;
		}else if(
			//����
			(node.getX()==0 && node.getY()==1) ||
			(node.getX()==1 && node.getY()==1) ||
			(node.getX()==1 && node.getY()==0) ||
			
			//�E��
			(node.getX()==Bord.x_size-2 && node.getY()==0) ||
			(node.getX()==Bord.x_size-2 && node.getY()==1) ||
			(node.getX()==Bord.x_size-1 && node.getY()==1) ||
			
			//����
			(node.getX()==0 && node.getY()==Bord.y_size-2) ||
			(node.getX()==1 && node.getY()==Bord.y_size-2) ||
			(node.getX()==1 && node.getY()==Bord.y_size-1) ||
			
			//�E��
			(node.getX()==Bord.x_size-2 && node.getY()==Bord.y_size-2) ||
			(node.getX()==Bord.x_size-2 && node.getY()==Bord.y_size-1) ||
			(node.getX()==Bord.x_size-1 && node.getY()==Bord.y_size-2)
		){
			//�p�΃��[�`�̏ꍇ�|�P�O�O
			//pos_score = -100;
		}
		
		return pos_score;
	}
	
	/**
	* �c���[�m�[�h����̃X�R�A�Z�o�p�ċA�֐�
	* �E���΂̏ꍇ�{�X�R�A
	* �E���΂̏ꍇ�|�X�R�A
	**/
	public static int getResult(PhaseNode node, byte stone){
		//�c���[�̑S�m�[�h�����v���Ĕ���Ɏg�p����
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
	* �菇�����肵�܂��B
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