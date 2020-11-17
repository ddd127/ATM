if not exist .build mkdir .build
javac src\atm\Main.java src\atm\ATM.java -d .build
java -cp .build atm.Main
rmdir /q /s .build
