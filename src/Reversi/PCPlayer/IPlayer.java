package Reversi.PCPlayer;

/**
* 局面解析インターフェース
* すべての自動手順作成プログラムはこのインターフェースを実装する必要があります。
**/

public interface IPlayer{
	/**
	* プレーヤーネームを返却します
	**/
	public String getName();
	
	/**
	* 手順を決定します。
	*
	* 返り値：int{X座標,Y座標}
	**/
	public int[] calc(byte stone, byte[][] bordData);
}