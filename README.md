## Описание

В этом репозитории находится консольное java приложение - модель банкомата.

### Запуск приложения:
Для запуска приложения необходимо иметь на компьютере java версии 11 и выше.

* Скачайте репозиторий в виде zip-архива или склонируйте его


Для запуска .jar - файла:

    * На Linux:

    Из терминала:
    Дайте файлу ATM.jar право на исполнение (chmod +x ATM.jar)
    Запустите его (./ATM.jar)


    * На Windows:

    Запустите файл из cmd командой
    `java -jar ATM.jar`

Для сборки и запуска из исходников:

    * На Linux:
    
    Дайте файлу run.sh право на исполнение(chmod +x run.sh)
    Выполните его (./run.sh).


    * На Windows:
    
    Выполните файл run.bat (например, двойным кликом мыши)

### Взаимодействие с приложением:
Банкомат принимает / выдает купюры следующего номинала:

`{1, 3, 5, 10, 25, 50, 100, 500, 1000, 5000}`

Банкомат поддерживает следующие команды:
* `put X Y` - положить в банкомат `Y` купюр номинала `X`
* `get X` - выдать сумму `X`. В случае, если выдать ровно `X` не удается, банкомат выдает ближайшую меньше либо равную `X` сумму
* `dump` - выводит состояние банкомата как набор строк вида `X Y`, где `X` - номинал купюры, `Y` - число купюр данного номинала
* `state` - выводит суммарное число денег в банкомате
* `quit` - останавливает работу банкомата (и всего приложения). Состояние банкомата не сохраняется
