@ECHO OFF

REM create bin directory if it doesn't exist
if not exist "..\bin" mkdir "..\bin"

REM delete output and TEST data from previous run
if exist ACTUAL.TXT del ACTUAL.TXT
if exist data\test_nimbus.txt del data\test_nimbus.txt

REM compile the code into the bin folder by specifying all packages
javac -cp "..\src\main\java" -Xlint:none -d "..\bin" "..\src\main\java\nimbus\*.java" "..\src\main\java\nimbus\command\*.java" "..\src\main\java\nimbus\exceptions\*.java" "..\src\main\java\nimbus\storage\*.java" "..\src\main\java\nimbus\tasks\*.java" "..\src\main\java\nimbus\ui\*.java"
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file, pass the test file path, and redirect the output to ACTUAL.TXT
java -classpath "..\bin" nimbus.Nimbus "data/test_nimbus.txt" < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT