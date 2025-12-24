// The code to test is moved in the same file. C does not support interfaces.
// So, we do linker wrapping to create a mock/stub object.
// wrap function is not supported in Mac, so this code will not compile.

#include <stdio.h>
#include <assert.h>

// 1. The "Real" function (usually in network.c)
int send_data(const char* message) {
    printf("REAL: Sending data to satellite...\n");
    return 0; // Success code in production
}

// 2. The "Mock" function (The Wrapper)
// The linker will redirect calls from send_data to here
int __wrap_send_data(const char* message) {
    printf("MOCK: Intercepted call with message: %s\n", message);
    
    // Stubbing logic: return a fake error if message is "fail"
    if (message[0] == 'f') {
        return -1; 
    }
    return 100; // Fake success code
}

int main() {
    printf("--- Test 1: Normal Message ---\n");
    int result1 = send_data("hello"); 
    printf("Result: %d\n", result1);
    assert(result1 == 100);

    printf("\n--- Test 2: Failure Message ---\n");
    int result2 = send_data("fail");
    printf("Result: %d\n", result2);
    assert(result2 == -1);

    printf("\nAll tests passed using Linker Wrapping!\n");
    return 0;
}
