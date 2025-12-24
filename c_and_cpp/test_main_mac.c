#include <stdio.h>

// 1. Redefine the function name BEFORE including headers
#define send_data mock_send_data

// 2. The Mock Implementation
int mock_send_data(const char* message) {
    printf("MOCK: Intercepted on Mac! Message: %s\n", message);
    return 100;
}

int main() {
    // This will now call mock_send_data
    int result = send_data("Hello Mac");
    printf("Result: %d\n", result);
    return 0;
}
