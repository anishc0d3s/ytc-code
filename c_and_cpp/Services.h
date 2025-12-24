// Define Interface
//

class IDatabase {
	public:
		virtual ~IDatabase() {}
		virtual int getUserPoints(int userId) = 0;
};

// business logic
float calculateDiscount(int userId, IDatabase &db) {
	int points = db.getUserPoints(userId);
	if (points > 100) return 0.20f;
	return 0.05f;
}
