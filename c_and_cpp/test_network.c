// test_network.c

#include <stdio.h>
#include <string.h>

// This is our stub
int __wrap_send_data(const char* message) {
	printf("Mock: Intercepted message: %s\n", message);
    
    // Stubbing: Return a specific value for the test
    if (strcmp(message, "fail") == 0) {
        return -1;
    }
    return 1; // Success
}

// It will call the stub version
void test_logic() {
    // This call will be intercepted!
    int result = send_data("Hello World");
    
    if (result == 1) {
        printf("Test Passed!\n");
    }
}
