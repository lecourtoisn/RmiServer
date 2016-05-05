#!/usr/bin/env bash

cd csgo/target/classes
ls
echo "Starting CsGo class server"
java classserver.ClassFileServer 2001 .