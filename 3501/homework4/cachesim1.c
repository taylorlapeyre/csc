#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <ctype.h>
#include <string.h>

/**
 * By Man Vo and Taylor Lapeyre
 * CSC 3501
 */

/**
 * Gets the flags from the user and updates the values if necessary
 */
void GetFlags(int nargs, char **args, int *b, int *s, int *a, char *W){
    int i;
    //set the default behavior of the parameters
    *b = 32;
    *s = 64;
    *a = 1;
    *W = 't';
    for (i=1; i < nargs; i++) {
        if (args[i][0] == '-') {
            switch(args[i][1]) {
            case 'b':
                // set the block size
                if (++i >= nargs) {
                    printf("Out of args. \n");
                                exit;
                                }
               // b should be a power of 2
                *b = atoi(args[i]);
                assert((*b & (*b - 1)) == 0);
                                break;
            case 's':
                // set the number of sets
                if (++i >= nargs) {
                    printf("Out of args");
                }
                // s should be a power of 2 unless a is set to 0
                *s = atoi(args[i]);
                                if(*a != 0) {
                        assert((*s & (*s - 1)) == 0);
                                }
                                break;
            case 'a':
                //set the associativity
                if (++i >= nargs) {
                    printf("Out of args");
                }
                *a = atoi(args[i]);
                break;
            case 'W':
                //set the write policy
                if (++i >= nargs) {
                    printf("Out of args");
                }
                *W = args[i][0];
            default:
                printf("something went real wrong\n");
                exit;
            }
        }
    }
}

/**
 * prints a character num times to help formatting
 */
void repeat(char c, int num){
        int i; //i needs to be decalred outside of the function for it to work with his make file
    for(i = 0; i < num; i++) {
        printf("%c",c);
    }
}

/**
 * Prints the header of the output body
 */
void PrintHeader(){
    printf("Hex");
    repeat(' ', 5);
    printf("Binary Address");
    repeat (' ',  23);
    printf("Set  Blk");
    repeat(' ', 15);
    printf("Memory\n");

    printf("Address (tag/index/offset)");
    repeat(' ',  14);
    printf("Tag Index off  Way UWay  Read Writ\n");

    repeat('=', 7);
    printf(" ");
    repeat('=', 26);
    printf(" ");
    repeat('=', 8);
    printf(" ");
    repeat('=', 5);
    printf(" ");
    repeat('=', 3);
    printf(" ");
    repeat('=', 4);
    printf(" ");
    repeat('=', 4);
    printf("  ");
    repeat('=', 4);
    printf(" ");
    repeat('=', 4);
    printf("\n");

}

/*
 * RETURNS: string indicating binary bit pattern in interger bits
 */
#define NBITS 24
char *Int2Bits(unsigned int bits, int tagBits, int indexBits)
{
    static char cbits[NBITS+1]; // NBITS macro defined elsewhere
    cbits[NBITS] = '\0';
    int i, j;

    for (i = 0; i < NBITS; i++) {
        //needs to add spaces
        cbits[NBITS-1-i] = (bits == ((1<<i) | bits)) ? '1' : '0';
    }

    static char fcbits[26 + 1];

    //take tagBits and indexBits, then add spaces accordingly
    //tagBits--; //to account for array starting at 0
    indexBits = tagBits + indexBits + 1;
    for (i = 0,j = 0; i < 26; i++){
        if (i == tagBits || i == indexBits) {
            fcbits[i] = ' ';
            continue;
        }

        fcbits[i] = cbits[j];
        j++;
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
    for (mask=i=0; i < nset; i++) {
        mask |= (1<<i);
    }
    #endif

    return(mask);
}

/**
 * uses bitwise operations to get the log2 of a number
 * RETURNS the log2 of x
 */
int bitwiseLog2(x){
    int result = 0;
    while (x >>= 1){
        result++;
    }
    return result;
}

/**
 * gets the decimal representation of a binary number with x amounts of 1s
 * this will be used to get the tag, set index, and blk off
 * ex: given x = 4, it will return the decimal representation of 1111 which equals 15.
 * RETURNS the decimal representation of the binary number with x 1s
 */
int genBinary1s(int x){
    int result = 1;
    int pow = 1;
    int i;

    for(i = 1; i < x; i++){
        //right shift pow and add it to the result x amount of times
        pow = pow << 1;
        result += pow;
    }
    return result;
}

/**
 * Main function that will act as the cache simulator
 */
int main(int nargs, char **args)
{
    int b,s,a;
    static char W;

    GetFlags(nargs, args, &b, &s, &a, &W);
    // 2KB 1-way associative cache: ...
        //cache size = blksz*nsets*assoc (in BITS)
        int cacheS = b*s*a;
        char cacheSize[10];
        if(cacheS % 1024 == 0){
                sprintf(cacheSize,"%dKB", cacheS / 1024);
        } else {
                sprintf(cacheSize,"%dB", cacheS);
        }

    //calculating tag, index, offset bits
    int indexBits = bitwiseLog2(s);
    int offsetBits = bitwiseLog2(b);
    int tagBits = 24 - indexBits - offsetBits;

    //tag space in bytes
    int tagSpace = (s*a*tagBits)/8;
    float tagPercent = ((float)tagSpace/(float)cacheS)*100;

    //print the rest of the header information
    printf("%s %d-way associative cache:\n",cacheSize, a);
        printf("   Block size = %d bytes\n",b);
        printf("   Number of [sets,blocks] = [%d,%d]\n",s, s*a);
    printf("   Extra space for tag storage = %d bytes (%5.2f%)\n",tagSpace, tagPercent);
        printf("   Bits for [tag,index,offset] = [%d, %d, %d] = %d\n", tagBits, indexBits, offsetBits, tagBits+indexBits+offsetBits);
        if(W=='t')
                printf("   Write policy = Write-through\n\n");
        else
                printf("   Write policy = Write-back\n\n");

        PrintHeader();


        //number of addresses
    int nref = 0;
    // count of reads and writes
    int nwrite = 0;
    int nread = 0;

    // count of hits and misses
    int hits = 0;
    int misses = 0;

    // count of memory reads and writes
    int mReads = 0;
    int mWrites = 0;
    //arrays containing the sets and tags of the input. size is initialized to the number of sets + 1
    int sets[s + 1];
    int tags[s + 1];

    //array holding the R/W bit for each row, will probablly not even need this array
    char readOrWrite[s+1];

    //holds the R/W bit to put into the array
    char RW;

    // create the cache table. rows = number of sets, columns = associtivity+1
    int cache[s][a+1];
    int i,j;
    //initialize cache to have all -1
    for(i=0; i < s; i++)
        for(j=0; j < (a+1); j++)
            cache[i][j] = -1;
    int curSetPointer = 0;

    //start the scan
    while(scanf("%[R|W]",&RW) == 1) {
        readOrWrite[nref] = RW;
        //scan then skip colons
        scanf(":");
        //get the hex address
        int address;
        scanf("%5x\n",&address);
        // right shift the address to get rid of everything except the tab bits
        int tag = address >> (offsetBits + indexBits);
        //then & with as many 1s as the number of bits
        tag = tag & genBinary1s(tagBits);

        int setIndex = address >> offsetBits;
        setIndex = setIndex & genBinary1s(indexBits);

        int blkOff = address & genBinary1s(offsetBits);
        int uway = 0;
        int way = -1;
        int read = 0;
        int writ = 0;

        //if the R/W bit is R, default read to 1, if W default writ to 1
        if (RW == 'R') {
          read = 1;
          nread++;
        } else if (RW == 'W' && W == 't') {
          writ = 1;
          nwrite++;
        }

            //if the cache has a set in it...
        if (cache[0][0] != -1) {
            // if write-through is selected...
            if(W == 't') {
                int i;
                //find the set
                for(i = 0; i <= curSetPointer; i++){
                    if(RW == 'W'){
                        way = -1;
                        uway = -1;
                        break;
                    }
                    if(setIndex == cache[i][0]){
                        //now find the tag
                        int j;
                        for(j = 1; j < (a+1); j++){
                          if(tag == cache[i][j]){
                                //if the tag is found, update way and uway
                                way = 0;
                                uway = -2;
                                read = 0;
                                break;
                            }else{
                                if(j == a){
                                    //if the tag was not found...
                                    way = -1;
                                    uway = 0;
                                    break;
                                }
                            }
                        }
                        break;
                    }else {
                        //if the set was NOT found
                        if(i == curSetPointer) {
                            way = -1;
                            //add the set to the cache
                            cache[curSetPointer][0] = setIndex;
                            //add the tag to the cache
                            cache[curSetPointer][1] = tag;
                            uway = 0;
                            curSetPointer++;
                            break;
                        }
                    }
                }
            }
             // if write-back is selected...
            else{
                int i;
                for(i = 0; i <= curSetPointer; i++){
                    if(setIndex == cache[i][0]){
                        //if the set index was found
                        int j;
                        for(j = 1; j < (a+1); j++){
                            if(tag == cache[i][j]){
                                //if the tag was found
                                read = 0;
                                writ = 0; //HIT!
                                way = 0;
                                uway = -2;
                                if(RW == 'W'){
                                    uway = 0;
                                }
                               break;
                            }else {
                                 //could not find tag
                                if(j==a){
                                    way = -1;
                                    uway = 0;
                                    break;
                                }

                            }
                        }
                        break;
                    }else{
                        //if the set index was NOT found
                        if(i == curSetPointer){
                            way = -1;
                            uway = 0;
                            cache[curSetPointer][0] = setIndex;
                            cache[curSetPointer][1] = tag;
                            read = 1;
                            curSetPointer++;
                            break;
                        }
                    }
                }

            }
        } else {
            //if this is the first address, add it to the cache
            if(RW == 'W' && W == 't') {
                //if it is write-through and the bit is write...
                uway = -1;
                way = -1;
            }else if(RW == 'W' && W == 'b') {
                uway = 0;
                way = -1;
                cache[curSetPointer][0] = setIndex;
                cache[curSetPointer][1] = tag;
                curSetPointer++;
            }else {
                //add the set into the cache
                cache[curSetPointer][0] = setIndex;
                cache[curSetPointer][1] = tag;
                way = -1;
                uway = 0;
                if(RW == 'R' && i)
                //update set pointer
                curSetPointer++;
            }
        }
        //update hits and misses
        if(way == 0) hits++;
        else misses++;

        // update memory reads and writes
        if(read == 1) mReads++;
        if(writ == 1) mWrites++;
        //print output lines
        printf("%6x%c %26s %8d %5d %3d %4d %4d  %4d %4d\n",address,RW, Int2Bits(address,tagBits,indexBits), tag, setIndex, blkOff, way, uway, read, writ);

        nref++;
    }
    //print nref stuff
    printf("nref=%d, nread=%d, nwrite=%d\n", nref, nread, nwrite);

    //print hits
    float hitRate = (float)hits/(float)nref;
    printf("   hits   =%10d, hit  rate = %.2f\n",hits, hitRate);
    //print misses
    float missRate = (float)misses/(float)nref;
    printf("   misses =%10d, miss rate = %.2f\n",misses, missRate);
    //print main memory read/writes
    printf("   main memory reads=%d, main memory writes=%d\n",mReads, mWrites);

    return 0;
}
