#!/bin/bash
if test "$#" != 1; then
  echo "Usage ./publish_conductor_custom.sh GIT_BRANCH_NAME"
  exit 1
fi
git clone https://github.com/CiscoM31/conductor-custom
cd conductor-custom
git checkout $1
if test $? != 0; then
  echo "Branch $1 does not exist. Defaulting to main"
  git checkout main
fi

./gradlew build publishToMavenLocal -x test
cd ..
rm -rf conductor-custom
