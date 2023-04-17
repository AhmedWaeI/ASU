@echo off
javac Main.java
java Main NewtextDocument.ARXML //originalfile
java Main NewTextDocument(2).txt //wrongExtension
java Main NewTextDocument(3).ARXML //EmptyOne
