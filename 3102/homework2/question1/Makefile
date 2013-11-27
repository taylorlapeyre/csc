#
# Makefile
#
# HDR       all header files (*.h) that you create
# SRC       all C++ source files (*.cpp) that you create
# OBJ       all object files (*.o) required to load your program
# EXE       the name of the executable
#
# Use the following make targets:
#
# all       (the default) to build your program (into ${EXE})
# clean     to remove unnecessary files
# veryclean to remove eveything but source files
# list      to list files changed since the last listing
# listall   to list your complete program
# run    	run program
# debug  	debug with gcc tools
# valgrind  valgrind debug
# memcheck  memcheck debug
# callgrind callgrind debug
# massif    massif debug
# depend    to update header file dependencies

HDR  =	
SRC  =	Main.cpp
OBJ  =	Main.o
EXE  =	kruskal

DEP =	${SRC}
CFLAGS = -g -pg -std=c++0x -pthread

# Use g++ as C compiler as well
CC =	g++
CPP =	g++

.PHONY: all clean veryclean depend

all: ${EXE}

${EXE}: ${OBJ}
	${CPP} ${CFLAGS} -o ${EXE} ${OBJ}

clean:
	rm -f ${OBJ} core
	rm -f *.out.*

veryclean:
	rm -f ${OBJ} core
	rm -f Makefile.bak ${EXE} list *~ *.out.*

run:
	./${EXE} -f graph.dat

valgrind:
	valgrind ./${EXE} -f dict.dat

memcheck:
	valgrind --tool=memcheck ./${EXE} -f dict.dat

callgrind:
	valgrind --tool=callgrind ./${EXE} -f dict.dat

massif:
	valgrind --tool=massif ./${EXE} -f dict.dat

.SUFFIXES: .cpp

%.o: %.cpp
	${CPP} ${CFLAGS} -c -o $*.o $*.cpp

depend: ${SRC} ${SRCg}
	cp -p Makefile Makefile.bak
	awk '{ print $0; } /(make) (depend)/ { exit; }' Makefile.bak > Makefile
	echo "" >> Makefile
	${CPP} ${CFLAGS} -MM ${SRC} ${SRCg} >> Makefile
	echo "" >> Makefile
	echo "# *** Do not add anything here - It will go away. ***" >>Makefile

# DO NOT DELETE THIS LINE - make depend DEPENDS ON IT

Main.o: Main.cpp

# *** Do not add anything here - It will go away. ***
