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
REM /** Bord�N���X�P�̃e�X�g **/
REM java -cp classes Reversi.BordTest > test.log

REM /** �l�� vs com **/
java -cp classes Reversi.Reversi

REM /** �w�K�p�������� **/
REM java -cp classes Reversi.Reversi -lerning 999999

REM /** ��������Q�l���Z�o **/
REM java -cp classes Reversi.Reversi -study kifu/kifu.log kifu/bord.db

REM /** ���[�e�B���e�B�̃e�X�g **/
REM java -cp classes Reversi.util.FileSort kifu/bord.db

pause
