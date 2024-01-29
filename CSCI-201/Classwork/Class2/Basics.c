#include <stdio.h>
#define KMS_PER_MILE 1.609

int main(int argc, char *argv[]) {

    // declare variables
    double miles;
    double kms;

    // input
    printf("Enter the number of miles: ");
    scanf("%lf", &miles);

    // process
    kms = KMS_PER_MILE * miles;

    // output
    printf("That equals %f kilometers.\n", kms);
}



