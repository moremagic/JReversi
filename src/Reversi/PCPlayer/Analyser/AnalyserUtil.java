package Reversi.PCPlayer.Analyser;


/**
* �ǖʉ�̓N���X
* ���݂̃Q�[������͂���N���X
**/

import Reversi.*;
import java.util.*;

public class AnalyserUtil{
	
	/**
	* �Ֆʃf�[�^���猻�݂̃X�R�A���Z�o���܂�
	* �X�R�A�Z�o���@
	* �E������ * 1�_
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
	* �Ֆʃf�[�^���猻�݂̃X�R�A���Z�o���܂�
	* �X�R�A�Z�o���@
	* �E����\�� * 1�_
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
	* �Ֆʃf�[�^���猻�݂̃X�R�A���Z�o���܂�
	* �X�R�A�Z�o���@
	* �E�p�̎����� * 1�_
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
	* �Ֆʃf�[�^���猻�݂̃X�R�A���Z�o���܂�
	* �X�R�A�Z�o���@
	* �E�ӂ̊p����A�����鎝����
	* ���p�̓J�E���g���Ȃ�
	* ���d���J�E���g�����肠��
	**/
	public static int calcScoreD(byte stone, byte[][] bordData){
		int score = 0;
		{//X����������
			boolean bUp = true;
			boolean bDn = true;
			for(int i = 0 ; i < bordData.length ; i++){
				//��ӉE�����̌���
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
				//���ӉE�����̌���
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
		
		{//X����������
			boolean bUp = true;
			boolean bDn = true;
			for(int i = bordData.length-1 ; i >= 0 ; i--){
				//��ӉE�����̌���
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
				//���ӉE�����̌���
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

		{//Y����������
			boolean bUp = true;
			boolean bDn = true;
			for(int i = 0 ; i < bordData[0].length ; i++){
				//���Ӊ������̌���
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
				//�E�Ӊ������̌���
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
		
		{//Y����������
			boolean bUp = true;
			boolean bDn = true;
			for(int i = bordData[0].length-1 ; i >= 0 ; i--){
				//���ӏ�����̌���
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
				//�E�ӏ�����̌���
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
	* �Ֆʃf�[�^���猻�݂̃X�R�A���Z�o���܂�
	* �X�R�A�Z�o���@
	* �E�p�Ίp�̎����� * 1�_
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
	* �Ֆʃf�[�^���猻�݂̃X�R�A���Z�o���܂�
	* �X�R�A�Z�o���@
	* �E�΂̍� * 1�_
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
