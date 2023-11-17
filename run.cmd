@echo off
set JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8
chcp 65001 > nul

cls
java -classpath out\production\JavaProject_Team4;"lib\*" com.clashofcards.client.MainClass

@REM this is for the production
@REM javac -encoding UTF-8 YourClassName.java
@REM java -Dfile.encoding=UTF-8 YourClassName