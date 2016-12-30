#!/bin/sh

echo "Building dependencies"
mvn install > /dev/null
echo "Built Successfully\n"

echo "\nRunning rmiregistry on port 7575"
cd target/classes
rmiregistry 7575 &
cd ../..
sleep 3
echo "Done\n"

echo "\nRunning Server"
java -cp "target/*" main.Server &
sleep 3

echo "\nRunning Client GUI\n"
java -cp "target/*" main.Main

echo "\nFinished"
