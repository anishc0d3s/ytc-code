/// Compile Test
// g++ test_services.cpp -lgmock -lgtest -lpthread -o test_suite
// ./test_suite
#include <gtest/gtest.h>
#include <gmock/gmock.h>

// --- The Logic ---
class IDatabase {
public:
    virtual ~IDatabase() {}
    virtual int getUserPoints(int userId) = 0;
};

float calculateDiscount(int userId, IDatabase& db) {
    int points = db.getUserPoints(userId);
    if (points > 100) return 0.20f;
    return 0.05f;
}

// --- The Mock ---
class MockDatabase : public IDatabase {
public:
    // If the 4-argument version fails, use this classic 1-argument macro:
    MOCK_METHOD1(getUserPoints, int(int userId));
};

// --- The Test ---
using ::testing::Return;

TEST(DiscountTest, HighPointsGetTwentyPercent) {
    MockDatabase mockDb;

    EXPECT_CALL(mockDb, getUserPoints(42))
        .WillOnce(Return(150));

    float result = calculateDiscount(42, mockDb);
    EXPECT_EQ(result, 0.20f);
}

int main(int argc, char **argv) {
    ::testing::InitGoogleMock(&argc, argv);
    return RUN_ALL_TESTS();
}
