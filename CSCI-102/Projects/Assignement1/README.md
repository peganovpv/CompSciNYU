Project 1:  Queue Processing
Due date:  Sunday   June 18 11:00 PM EST.


You may discuss any of the assignments with your classmates and tutors (or anyone else) but all work for all assignments must be entirely your own . Any sharing or copying of assignments will be considered cheating (this includes posting of partial or complete solutions on any public forum). If you get significant help from anyone, you should acknowledge it in your submission (and your grade will be proportional to the part that you completed on your own). You are responsible for every line in your program: you need to know what it does and why. You should not use any data structures and features of Java that have not been covered in class (or the prerequisite class). If you have doubts whether or not you are allowed to use certain structures, just ask your instructor.

In this project you will simulate the operating system’s selection of processes to send to the CPU. The operating system will select the next process from the of awaiting processes. Each process will require 1 or more the resources A, B and C. Some processes will require only B for example, while another might require A and B, yet another B and C. If the resource is available, the process can be started. If one or more of the resources are unavailable, then the process must wait one cycle. A process that is started will only use a resource for one cycle. A process can only start if all the previous processes have been started.  Here is a chart describing a possible scenario:
Starting process list with resources in ( ):  P1(A);P2(B); P3(B,C);P4(C);P5(A,B,C); P6(B,C);P7(A);P8(A);P9(B);P10(C) 

There are 2 parts to the assignment, both parts have the same output of the number of cycles , and final length of the queue.
Part A :  Read a one line from the Console  where the  line  has the format shown here (and  above): 
P1(A);P2(B); P3(B,C);P4(C);P5(A,B,C); P6(B,C) ;P7(A);P8(A);P9(B);P10(C) 
For each input string from the Console,  assign the processes to a list, then execute the list and determine the number of cycles to completely execute the processes. In our example the answer is 6