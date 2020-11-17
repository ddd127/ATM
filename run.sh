#!/bin/bash

[ ! -d "./.build" ] && mkdir .build
javac src/atm/*.java -d .build/
java -cp .build/ atm.Main
rm -rf .build/
