del /Q classes\Reversi\PCPlayer
del /Q classes\Reversi
del /Q classes
rd classes\Reversi\PCPlayer
rd classes\Reversi
rd classes
mkdir classes

javac -cp src -d classes ./src/Reversi/*.java
javac -cp src -d classes ./src/Reversi/util/*.java
javac -cp src -d classes ./src/Reversi/learning/*.java
javac -cp src -d classes ./src/Reversi/PCPlayer/*.java
REM /** Bordクラス単体テスト **/
REM java -cp classes Reversi.BordTest > test.log

REM /** 人間 vs com **/
java -cp classes Reversi.Reversi

REM /** 学習用棋譜生成 **/
REM java -cp classes Reversi.Reversi -lerning 999999

REM /** 棋譜からQ値を算出 **/
REM java -cp classes Reversi.Reversi -study kifu/kifu.log kifu/bord.db

REM /** ユーティリティのテスト **/
REM java -cp classes Reversi.util.FileSort kifu/bord.db

pause
