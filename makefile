# 312275746
# Naveh Marchoom

compile: bin
	find src -name "*.java" > sources.txt
	javac -d bin -cp biuoop-1.4.jar @sources.txt

jar:
	jar cfm ass6game.jar Manifest.mf -C bin . -C resources .

run:
	java -cp biuoop-1.4.jar:resources:bin Ass6Game
	
bin:
	mkdir bin
	
uber: uber-jar
	cd uber-jar; \
		jar -xf ../biuoop-1.4.jar; \
		rm -rf META-INF
	jar -uf ass6game.jar -C uber-jar .
	rm -rf uber-jar
	
uber-jar:
	mkdir uber-jar