package Reversi.PCPlayer.Analyser;


/**
* 局面解析クラス
* 現在のゲームを解析し、点数をつけるインターフェース
**/

public interface IAnalyser{

	/**
	* 盤面データから現在のスコアを算出します
	**/
	public int getScore(byte stone, byte[][] bordData);
}
