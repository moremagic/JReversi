package Reversi.learning;

/**
* ボードデータの返却が可能なPlayerです。
* 局面情報を保持し、機械学習に使用することができます。
**/

import java.util.*;
import Reversi.PCPlayer.*;

public interface IBordList extends IPlayer{
	/**
	* ボードデータの返却
	**/
	public List<byte[][]> getBordList();
}