package Reversi.learning;

/**
* �{�[�h�f�[�^�̕ԋp���\��Player�ł��B
* �ǖʏ���ێ����A�@�B�w�K�Ɏg�p���邱�Ƃ��ł��܂��B
**/

import java.util.*;
import Reversi.PCPlayer.*;

public interface IBordList extends IPlayer{
	/**
	* �{�[�h�f�[�^�̕ԋp
	**/
	public List<byte[][]> getBordList();
}