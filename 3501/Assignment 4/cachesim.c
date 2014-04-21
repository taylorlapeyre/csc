#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <ctype.h>
#include <string.h>

void GetFlags(int nargs, char **args, int *b, int *s, int *a, char *W){
    int i;
    //set the default behavior of the parameters
    *b = 32;
    *s = 64;
    *a = 1;
    *W = 't';
    for (i=1; i < nargs; i++)
    {
        if (args[i][0] == '-')
        {
                switch(args[i][1])
            {
                case 'b':
                    //set the block size
                    if(++i >= nargs)
                        printf("Out of args. \n");
                   //b should be a power of 2
                    *b = atoi(args[i]);
                    break;
                case 's':
                    //set the number of sets
                    if(++i >= nargs)
                        printf("Out of args");
                    //s should be a power of 2 unless a is set to 0
                    *s = atoi(args[i]);
                    break;
                case 'a':
                    //set the associativity
                    if(++i >= nargs)
                        printf("Out of args");
                    *a = atoi(args[i]);
                    break;
                case 'W':
                    //set the write policy
                    if(++i >= nargs)
                        printf("Out of args");
                    *W = args[i][0];
                default:
                    printf("something\n");
            }

        }
    }
    //print the values for testing...
    //printf("b:%d\ns:%d\na:%d\nW:%c\n",*b,*s,*a,*W);

}
//prints c num times
void repeat(char c, int num){
    int i;
    for(i=0; i < num; i++)
        printf("%c",c);
}

void PrintHeader(){
    printf("Hex");
    repeat(' ',5);
    printf("Binary Address");
    repeat (' ', 23);
    printf("Set  Blk");
    repeat(' ',14);
    printf("Memory\n");


    printf("Address (tag/index/offset)");
    repeat(' ', 14);
    printf("Tag Index off  Way UWay Read Writ\n");

    repeat('=',7);
    printf(" ");
    repeat('=',26);
    printf(" ");
    repeat('=',8);
    printf(" ");
    repeat('=',5);
    printf(" ");
    repeat('=',3);
    printf(" ");
    repeat('=',4);
    printf(" ");
    repeat('=',4);
    printf(" ");
    repeat('=',4);
    printf(" ");
    repeat('=',4);
    printf("\n");

}
#define NBITS 24
char *Int2Bits(unsigned int bits)
/*
 * RETURNS: string indicating binary bit pattern in interger bits
 */
{
    static char cbits[NBITS+1]; /* NBITS macro defined elsewhere */
    int i,j;
    cbits[NBITS] = '\0';
    for (i=0; i < NBITS; i++){
        //needs to add spaces
        cbits[NBITS-1-i] = (bits == ((1<<i) | bits)) ? '1' : '0';
    }
    static char fcbits[26+1];
    int tag,index;
    //HARDCODED:
    tag = 8 + 5;
    index = tag + 7;
    for (i=0,j=0; i < 26; i++){
        if(i==tag || i==index)
        {
            fcbits[i] = ' ';
            continue;
        }
        fcbits[i] = cbits[j];
        j++;
        //printf(":%c\n",fcbits[i]);
    }
    return(fcbits);

}


/*
* RETURNS: int with nset least significant bits set to 1, others to 0
*/
unsigned int GetMask(int nset)
{
    unsigned int mask, i;
    assert(nset <= 32); /* otherwise default int won't work */

    //Following two implementations are equivalent, but loop-based one may
    //be easier to understand at first
    #ifdef FAST
    mask = (((long long)1))<<nset) - 1;
    #else
    for (mask=i=0; i < nset; i++)
        mask |= (1<<i);
    #endif
    return(mask);
}


int main(int nargs, char **args)
{
    printf("\n\n");

    int b,s,a;
    char W;
    GetFlags(nargs, args, &b, &s, &a, &W);
    PrintHeader();

    //number of addresses
    int nref = 0;
    //arrays containing the sets and tags of the input. size is initialized to the number of sets + 1
    int sets[s+1];
    int tags[s+1];
    //array holding the R/W bit for each row, will probablly not even need this array
    char readOrWrite[s+1];
    //holds the R/W bit to put into the array
    char RW;
    while(scanf("%[R|W]",&RW) == 1){
        readOrWrite[nref] = RW;
        //printf("RW:%c\n",RW);
        //scan then skip colons
        scanf("%[:]");
        //get the hex address
        int address;
        scanf("%5x\n",&address);

        int tag = address >> 11;
        tag = tag & 31;
        tags[nref] = tag;

        int setIndex = address >> 5;
        setIndex = setIndex & 31;
        sets[nref] = setIndex;

        int blkOff = address & 31;
        printf("%6x%c %26s %8d %5d %3d\n",address,RW, Int2Bits(address), tags[nref], sets[nref], blkOff);

        nref++;
    }
    //printf("4: %x%c = %d = %s\n",addresses[3],readOrWrite[3],addresses[3],Int2Bits(addresses[3]));
    printf("nref:%d\n",nref);
    printf("\n\n");
    return 0;
}
