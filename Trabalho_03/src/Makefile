all:
	javac *.java

cfiles: FILES_CLIENT/*
	rm -R FILES_CLIENT/*
clean:	
	rm *.class

rmi:
	rmiregistry

server:	
	java -Djava.server.rmi.codebaseile:// -Djava.security.policy=policy Servidor

client:
	java -Djava.security.policy=policy ShapeListClient execCli