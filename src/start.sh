#!/usr/bin/env bash

mvn package
cd target/classes
rmiregistry 4000