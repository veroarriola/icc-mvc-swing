PATH_TO_FX=/home/blackzafiro/Descargas/Aplicaciones/Java/openjfx-11.0.1_linux-x64_bin-sdk/javafx-sdk-11.0.1/lib/

compile:
	javac -d ./build --source-path ./src src/mvcswing/**/*.java

run:
	java -classpath build mvcswing.controlador.Controlador

.PHONY: clean
clean:
	rm -rf build
