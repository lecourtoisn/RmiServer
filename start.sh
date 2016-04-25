#!/usr/bin/env bash

mvn package
cd core/target/classes
echo "Running rmiregistry on port 4000"
rmiregistry 4000