#!/usr/bin/env bash
filelist=`(cd protos; ls *proto)`
protoc --java_out=./ --proto_path=./protos $filelist