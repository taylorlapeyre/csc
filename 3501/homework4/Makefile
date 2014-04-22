EXEd = /classes/cs3501/cs3501_wha/bin/
RCWdir = /classes/cs3501/cs3501_wha/classes/cs3501_S14/p4_cachesim
#RCWdir = ./
trace = $(RCWdir)/trace.in
profexe = $(RCWdir)/xcachesim
conf = -b 32 -s 128 -W t -a 2

CC = gcc
CCFLAGS = -g -m32
IFLAGS = -I$(RCWdir)

xcachesim : cachesim.o
	$(CC) $(CCFLAGS) -o $@ cachesim.o
cachesim.o : cachesim.c
	$(CC) $(CCFLAGS) $(IFLAGS) -c cachesim.c
clean:
	rm -f stud.out prof.out cachesim.o

kill:
	rm -f stud.out prof.out cachesim.o xcachesim

diff: xcachesim
	rm -f stud.out prof.out
	./xcachesim $(conf) < $(trace) > stud.out
	$(profexe) $(conf) < $(trace) > prof.out
	- diff stud.out prof.out
xcachesim_strip:
	gcc -m32 -static -O3 -c cachesim.c
	gcc -m32 -static -static-libgcc -o xcachesim_strip cachesim.o
	objcopy -S xcachesim_strip xcachesim_stripped
	rm cachesim.o 
	chmod a-rw,u+rw xcachesim_stripped
	rm -f xcachesim_strip
xarraygen:
	$(CC) $(CCFLAGS) -o $@ arraygen.c
#
# This rule uses our submission program to submit code that works!
#
submit:
	make kill
	$(EXEd)/p_copy 4

