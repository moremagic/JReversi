package Reversi.PCPlayer;


/**
* �v���[���[�N���X
* Human�v���[��
**/

import Reversi.*;
import java.util.*;

public class HumanPlayerImpl implements IPlayer{
	public String getName(){
		return "Human�v���[���[";
	}
	
	/**
	* �菇�����肵�܂��B
	**/
	public int[] calc(byte stone, byte[][] bordData){
		int[] iData = null;
		
		System.out.print("X,Y�����W����͂��A�u���ꏊ���w�肵�Ă��������B>>");
		
		while(true){
			Scanner scan= new Scanner(System.in);
			try{
				iData = Bord.cnvKifu(scan.next());
				if(iData == null)iData = new int[]{-1,-1};
				
				int buf = Bord.countStone(iData[0], iData[1], stone, bordData);
				if(buf > 0){
					break;
				}else{
					System.out.print("���̏ꏊ�ɂ͒u���܂���B�u���ꏊ���w�肵�Ă��������B>>");
				}
			}catch(Exception err){
				System.out.print("�t�H�[�}�b�g�ُ�B�u���ꏊ��������x���͂��Ă��������B>>");
			}
		}
		
		
		return iData;
	}
	
	

}