rm -Rf store-display store-camera
mkdir store-display store-camera

# now cd into the store display, build it, and then copy the artefacts back out into the container folder
cd ../store-display
./mvnw clean
./mvnw package -Dmaven.test.skip=true
cp -R target ../rhel-9.4-image-mode/store-display/
cp -R src/main/resources/META-INF/resources/index.html ../rhel-9.4-image-mode/store-display/target/app

# now cd into the store camera, build it, and then copy the artefacts back out into the container folder
cd ../store-camera
./mvnw clean
./mvnw package -Dmaven.test.skip=true
cp -R target ../rhel-9.4-image-mode/store-camera/
