git clone https://github.com/CiscoM31/conductor-custom
cd conductor-custom
./gradlew build publishToMavenLocal -x test
cd ..
rm -rf conductor-custom
