#!/usr/bin/env bash

echo "nique tes morts"
cd rmiRunDir
ls .
ls
echo "Running rmiregistry on port 4000"
rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false 4000