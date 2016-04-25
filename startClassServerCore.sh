#!/usr/bin/env bash

cd core/target/classes
echo "Starting Core class server"
java classserver.ClassFileServer 2001 .