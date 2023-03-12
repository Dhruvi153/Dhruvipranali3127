#include<iostream>
using namespace std;

void sort(int f[7][3],int web){

    int temp[5];
    for (int i=0;i<web;i++){

        for (int j=0;j<web;j++){

            if(f[i][0]<f[j][0]){

                temp[0] = f[i][0];
                temp[1] = f[i][1];
                temp[2] = f[i][2];
                f[i][0] = f[j][0];
                f[i][1] = f[j][1];
                f[i][2] = f[j][2];
                f[j][0] = temp[0];
                f[j][1] = temp[1];
                f[j][2] = temp[2];

            }
             if(f[i][0]==f[j][0]){
                if(f[i][1] > f[j][1]){
                    temp[0] = f[i][0];
                    temp[1] = f[i][1];
                    temp[2] = f[i][2];
                    f[i][0] = f[j][0];
                    f[i][1] = f[j][1];
                    f[i][2] = f[j][2];
                    f[j][0] = temp[0];
                    f[j][1] = temp[1];
                    f[j][2] = temp[2];
                }

             }
               if(f[i][0]==f[j][0]){
                if(f[i][1]==f[j][1]){
                    if(f[i][2] < f[j][2]){
                        temp[0] = f[i][0];
                        temp[1] = f[i][1];
                        temp[2] = f[i][2];
                        f[i][0] = f[j][0];
                        f[i][1] = f[j][1];
                        f[i][2] = f[j][2];
                        f[j][0] = temp[0];
                        f[j][1] = temp[1];
                        f[j][2] = temp[2];

                    }
                }
          }
        }
    }
}

