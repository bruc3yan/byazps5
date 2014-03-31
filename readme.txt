/*
 * CS 133: Problem set 5 - Readme
 * 
 * Author: Bruce Yan, Angela Zhou
 * E-mail: byan@hmc.edu, azhou@hmc.edu
 * 
 */

Hello Grader, the following prerequisites are assumed about your machine: 
- Tomcat v7.0.52 or greater (this was tested on v7, v8 should work as well.)
    - Tomcat is running on default port 8080
- Java SE Runtime Environment v1.7 or greater
- MySQL Database v5.6.15 or greater
    - MySQL server is running on default port 3306
    - MySQL username: grader, password: allowme

1. Place byazps5.tgz into your ../apache-tomcat-7.0.52/webapps directory
2. Open Terminal and navigate to that directory.
    - Shell > tar -xzvf byazps5.tgz 
3. The 'byazps5' folder should have extracted into the following directory:
    ../apache-tomcat-7.0.52/webapps/byazps5
4. Ensure MySQL and Tomcat is running on your system and head to
    - http://localhost:8080/byazps5

Please note, we are also assuming the following per Professor Lee:
1. Our program does not handle error handling (i.e. blank input values) and thus
    our grader needs to be aware that we assume users of our program are nice
    individuals and will only input valid values.


Versions of our machines: 
Mac OS X 10.8 and 10.9
We are NOT using Eclipse

If the tarball is corrupted, you may grab a fresh copy from here:
    http://cs.hmc.edu/~byan/byazps5.tgz

Additionally, our entire project is accessible on github, if that is more 
preferred:
    https://github.com/bruc3yan/byazps5.git