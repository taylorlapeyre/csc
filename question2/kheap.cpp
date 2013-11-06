#include <iostream>
#include <stdlib.h>
#include <string>
#include <vector>
#include <fstream>
#include <iterator>
#include <time.h>

using namespace std;

class kheap {
public:
    kheap();
    kheap(int k);
    int size() { return heap.size(); }
    void insert(int element);
    int extract_min();
private:
    int asc(int desc);
    int left(int asc);
    int right(int asc);
    void kheapifyup(int pos);
    void kheapifydown(int pos);
private:
    vector<int> heap; //here // i have no idea
};

kheap::kheap(){
}

int kheap::asc(int desc) {
    if (desc != 0)
    {
        int i = (desc - 1) >> 1;
        return i;
    }
    return -1;
}

void kheap::kheapifyup(int pos) {
    while ( ( pos > 0 ) && ( asc(pos) >= 0 ) && ( heap[asc(pos)] > heap[pos] ) )
    {
        int tmp = heap[asc(pos)];
        heap[asc(pos)] = heap[pos];
        heap[pos] = tmp;
        pos = asc(pos);
    }
}

void kheap::kheapifydown(int pos) {
    int desc = left(pos);
    if ( ( desc > 0 ) && ( right(pos) > 0 ) && ( heap[desc] > heap[right(pos)] ) ) {
        desc = right(pos);
    }
    if ( desc > 0 ) {
        int tmp = heap[pos];
        heap[pos] = heap[desc];
        heap[desc] = tmp;
        kheapifydown(desc);
    }
}

void kheap::insert(int element) {
    heap.push_back(element);
    kheapifyup(heap.size() - 1);
}

int kheap::extract_min() {
    int min = heap.front();
    heap[0] = heap.at(heap.size() - 1);
    heap.pop_back();
    kheapifydown(0);
    return min;
}

int kheap::left(int asc) {
    int i = ( asc << 1 ) + 1;
    if ( i < heap.size() ) {
        return i;
    }
    else {
        return -1;
    }
    return -1;
}

int kheap::right(int asc) {
    int i = ( asc << 1 ) + 2;
    if ( i < heap.size() ) {
        return i;
    }
    else {
        return -1;
    }
    return -1;
}

int main() {
    kheap* mykheap = new kheap();
    const int SIZE = 3;
    char input[SIZE];
    ifstream datafile;
    datafile.open("input.txt");
    int temp;
    clock_t t1,t2;
    t1=clock();
    while (datafile.eof() == false)
    {
        datafile >> input;
        if (input[0] == 'I')
        {
            datafile >> input;
            temp = atoi(input);
            mykheap->insert(temp);
        }
        else if (input[0] == 'E')
            cout << mykheap->extract_min() << endl;
    }
    t2=clock();
    float diff ((float)t2-(float)t1);
    float miseconds = (diff / CLOCKS_PER_SEC) * 1000000;
    cout << "Microseconds: " << miseconds << endl;
}