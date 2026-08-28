@echo off
javac -d out src\*.java test\*.java
java -ea -cp out LabTestRunner