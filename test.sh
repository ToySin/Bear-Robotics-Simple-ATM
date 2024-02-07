#!/bin/bash

./gradlew :test --tests "atm.simple.ATMTestDriver"
echo
echo
echo vvvvvvvvvv Copy and paste the following path to your browser vvvvvvvvvv
echo
echo           $(pwd)/build/reports/tests/test/index.html
echo
echo ^^^^^^^^^^ Copy and paste the above path to your browser ^^^^^^^^^^

